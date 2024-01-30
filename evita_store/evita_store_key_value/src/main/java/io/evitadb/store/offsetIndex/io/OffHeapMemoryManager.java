/*
 *
 *                         _ _        ____  ____
 *               _____   _(_) |_ __ _|  _ \| __ )
 *              / _ \ \ / / | __/ _` | | | |  _ \
 *             |  __/\ V /| | || (_| | |_| | |_) |
 *              \___| \_/ |_|\__\__,_|____/|____/
 *
 *   Copyright (c) 2023
 *
 *   Licensed under the Business Source License, Version 1.1 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   https://github.com/FgForrest/evitaDB/blob/main/LICENSE
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package io.evitadb.store.offsetIndex.io;

import io.evitadb.utils.Assert;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import java.io.Closeable;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * OffHeapMemoryManager class is responsible for managing off-heap memory regions and providing
 * free regions to acquire OutputStreams for writing data.
 *
 * @author Jan Novotný (novotny@fg.cz), FG Forrest a.s. (c) 2023
 */
@Slf4j
public class OffHeapMemoryManager implements Closeable {
	/**
	 * Private final variable to store a reference to a ByteBuffer object.
	 * The AtomicReference class is used to provide thread-safe access to the memoryBlock.
	 */
	final AtomicReference<ByteBuffer> memoryBlock;
	/**
	 * Represents a concurrent fixed-size array of OffHeapMemoryOutputStream objects, used as regions. Each non-null
	 * output stream has single region block reserved in {@link #memoryBlock}.
	 */
	private final AtomicReferenceArray<OffHeapMemoryOutputStream> usedRegions;
	/**
	 * The size of single region of {@link #memoryBlock} in Bytes.
	 */
	private final int regionSize;
	/**
	 * The last index of the usedRegions array that was used to acquire a region. This is used to identify the first
	 * attempted index to acquire a region. We tested a randomization function to select it, but it's miss count varied
	 * a lot and this simple mechanism proved to be very easy to implement and not so worse than the randomization.
	 */
	private int lastIndex = 0;

	public OffHeapMemoryManager(long sizeInBytes, int regions) {
		if (sizeInBytes % regions != 0) {
			log.warn(
				"You're wasting memory - off heap memory block size is not divisible by number of regions without " +
					"remainder (" + sizeInBytes + " / " + regions + ")!");
		}
		this.regionSize = Math.toIntExact(sizeInBytes / regions);
		this.usedRegions = new AtomicReferenceArray<>(regions);
		// allocate off heap memory
		memoryBlock = new AtomicReference<>(ByteBuffer.allocateDirect((int) sizeInBytes));

	}

	/**
	 * Acquires a free region stream from the memory block.
	 *
	 * @return an {@link Optional} containing the acquired {@link OutputStream}, or an empty {@link Optional}
	 * if no free region is available.
	 */
	@Nonnull
	public Optional<OffHeapMemoryOutputStream> acquireRegionOutputStream() {
		final ByteBuffer byteBuffer = memoryBlock.get();
		if (byteBuffer == null) {
			return Optional.empty();
		}
		final int regionCount = usedRegions.length();
		final OffHeapMemoryOutputStream newOutputStream = new OffHeapMemoryOutputStream();
		final int occupiedIndex = findClearIndexAndSet(regionCount, lastIndex++, newOutputStream);
		lastIndex = occupiedIndex;
		if (occupiedIndex == -1) {
			return Optional.empty();
		} else {
			final ByteBuffer region = byteBuffer.slice(occupiedIndex * regionSize, regionSize);
			newOutputStream.init(occupiedIndex, region, (index, clearedReference) -> usedRegions.compareAndSet(index, clearedReference, null));
			return Optional.of(newOutputStream);
		}
	}

	/**
	 * Releases the stream associated with the given region index.
	 *
	 * @param regionIndex the index of the region whose stream is to be released
	 */
	public void releaseRegionStream(int regionIndex) {
		final OffHeapMemoryOutputStream stream = usedRegions.get(regionIndex);
		Assert.isPremiseValid(stream != null, "Stream at index " + regionIndex + " is already released!");
		stream.close();
	}

	@Override
	public void close() {
		// get rid of memory block - this should effectively stop acquiring new streams
		memoryBlock.set(null);
		// now free the output streams that were not released
		for (int i = 0; i < usedRegions.length(); i++) {
			final OffHeapMemoryOutputStream nonReleasedStream = usedRegions.getAndSet(i, null);
			if (nonReleasedStream != null) {
				nonReleasedStream.close();
			}
		}
	}

	/**
	 * Returns the number of free regions.
	 *
	 * @return the number of free regions
	 */
	public int getFreeRegions() {
		// iterate over the used regions and count the null values
		int freeRegions = 0;
		for (int i = 0; i < usedRegions.length(); i++) {
			if (usedRegions.get(i) == null) {
				freeRegions++;
			}
		}
		return freeRegions;
	}

	/**
	 * Finds a clear index in the usedRegions array by comparing and setting the value at the specified index with
	 * a newOutputStreamFactory object.
	 *
	 * @param regionCount     the total number of regions
	 * @param randomIndex     the random starting index
	 * @param newOutputStream the new OutputStream object
	 * @return the clear index found, or -1 if no clear index is found
	 */
	private int findClearIndexAndSet(int regionCount, int randomIndex, @Nonnull OffHeapMemoryOutputStream newOutputStream) {
		for (int i = 0; i < regionCount; i++) {
			final int index = Math.abs(randomIndex + i) % regionCount;
			if (usedRegions.compareAndSet(index, null, newOutputStream)) {
				return index;
			}
		}
		return -1;
	}

}