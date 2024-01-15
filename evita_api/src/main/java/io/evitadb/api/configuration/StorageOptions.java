/*
 *
 *                         _ _        ____  ____
 *               _____   _(_) |_ __ _|  _ \| __ )
 *              / _ \ \ / / | __/ _` | | | |  _ \
 *             |  __/\ V /| | || (_| | |_| | |_) |
 *              \___| \_/ |_|\__\__,_|____/|____/
 *
 *   Copyright (c) 2023-2024
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

package io.evitadb.api.configuration;

import lombok.Builder;
import lombok.ToString;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Configuration options related to the key-value storage.
 *
 * @param storageDirectory            Directory on local disk where Evita files are stored.
 *                                    By default, temporary directory is used - but it is highly recommended setting your own
 *                                    directory if you don't want to lose the data.
 * @param transactionWorkDirectory Directory on local disk where Evita creates temporary folders and files for
 *                                    transactional storage. By default, temporary directory is used - but it is
 *                                    recommended setting your own directory with dedicated disk space.
 * @param lockTimeoutSeconds          This timeout represents a time in seconds that is tolerated to wait for lock acquiring.
 *                                    Locks are used to get handle to open file. Set of open handles is limited to
 *                                    {@link #maxOpenedReadHandles} for read operations and single write handle for write
 *                                    operations (only single thread is expected to append to a file).
 * @param waitOnCloseSeconds          This timeout represents a time that will file offset index wait for processes to release their
 *                                    read handles to file. After this timeout files will be closed by force and processes may
 *                                    experience an exception.
 * @param outputBufferSize            The output buffer size determines how large a buffer is kept in memory for output
 *                                    purposes. The size of the buffer limits the maximum size of an individual record in the
 *                                    key/value data store.
 * @param maxOpenedReadHandles        Maximum number of simultaneously opened {@link java.io.InputStream} to file offset index file.
 * @param computeCRC32C               Contains setting that determined whether CRC32C checksums will be computed for written
 *                                    records and also whether the CRC32C checksum will be checked on record read.
 * @param transactionMemoryBufferLimitSizeBytes TODO JNO - document me
 * @param transactionMemoryRegionCount TODO JNO - document me
 * @author Jan Novotný (novotny@fg.cz), FG Forrest a.s. (c) 2021
 */
@Builder
public record StorageOptions(
	@Nullable Path storageDirectory,
	@Nullable Path transactionWorkDirectory,
	long lockTimeoutSeconds,
	long waitOnCloseSeconds,
	int outputBufferSize,
	int maxOpenedReadHandles,
	boolean computeCRC32C,
	long transactionMemoryBufferLimitSizeBytes,
	int transactionMemoryRegionCount,
	long walFileSizeBytes,
	int walFileCountKept
) {

	public static final int DEFAULT_OUTPUT_BUFFER_SIZE = 2_097_152;
	public static final Path DEFAULT_DIRECTORY = Paths.get("").resolve("data");
	public static final Path DEFAULT_TX_DIRECTORY = Paths.get("").resolve("transactions");
	public static final int DEFAULT_LOCK_TIMEOUT_SECONDS = 5;
	public static final int DEFAULT_WAIT_ON_CLOSE_SECONDS = 5;
	public static final int DEFAULT_MAX_OPENED_READ_HANDLES = Runtime.getRuntime().availableProcessors();
	public static final boolean DEFAULT_COMPUTE_CRC = true;
	public static final long DEFAULT_TRANSACTION_MEMORY_BUFFER_LIMIT_SIZE = 16_777_216;
	public static final int DEFAULT_TRANSACTION_MEMORY_REGION_COUNT = 256;
	public static final int DEFAULT_WAL_SIZE_BYTES = 16_777_216;
	public static final int DEFAULT_WAL_FILE_COUNT_KEPT = 8;

	/**
	 * Builder method is planned to be used only in tests.
	 */
	public static StorageOptions temporary() {
		return new StorageOptions(
			Path.of(System.getProperty("java.io.tmpdir"), "evita/data"),
			Path.of(System.getProperty("java.io.tmpdir"), "evita/transactions"),
			5, 5, DEFAULT_OUTPUT_BUFFER_SIZE,
			Runtime.getRuntime().availableProcessors(),
			true,
			DEFAULT_TRANSACTION_MEMORY_BUFFER_LIMIT_SIZE,
			DEFAULT_TRANSACTION_MEMORY_REGION_COUNT,
			DEFAULT_WAL_SIZE_BYTES,
			DEFAULT_WAL_FILE_COUNT_KEPT
		);
	}

	/**
	 * Builder for the storage options. Recommended to use to avoid binary compatibility problems in the future.
	 */
	public static StorageOptions.Builder builder() {
		return new StorageOptions.Builder();
	}

	/**
	 * Builder for the storage options. Recommended to use to avoid binary compatibility problems in the future.
	 */
	public static StorageOptions.Builder builder(StorageOptions storageOptions) {
		return new StorageOptions.Builder(storageOptions);
	}

	public StorageOptions() {
		this(
			DEFAULT_DIRECTORY,
			DEFAULT_TX_DIRECTORY,
			DEFAULT_LOCK_TIMEOUT_SECONDS,
			DEFAULT_WAIT_ON_CLOSE_SECONDS,
			DEFAULT_OUTPUT_BUFFER_SIZE,
			DEFAULT_MAX_OPENED_READ_HANDLES,
			DEFAULT_COMPUTE_CRC,
			DEFAULT_TRANSACTION_MEMORY_BUFFER_LIMIT_SIZE,
			DEFAULT_TRANSACTION_MEMORY_REGION_COUNT,
			DEFAULT_WAL_SIZE_BYTES,
			DEFAULT_WAL_FILE_COUNT_KEPT
		);
	}

	public StorageOptions(
		@Nullable Path storageDirectory,
		@Nullable Path transactionWorkDirectory,
		long lockTimeoutSeconds,
		long waitOnCloseSeconds,
		int outputBufferSize,
		int maxOpenedReadHandles,
		boolean computeCRC32C,
		long transactionMemoryBufferLimitSizeBytes,
		int transactionMemoryRegionCount,
		long walFileSizeBytes,
		int walFileCountKept
	) {
		this.storageDirectory = Optional.ofNullable(storageDirectory).orElse(DEFAULT_DIRECTORY);
		this.transactionWorkDirectory = Optional.ofNullable(transactionWorkDirectory).orElse(DEFAULT_TX_DIRECTORY);
		this.lockTimeoutSeconds = lockTimeoutSeconds;
		this.waitOnCloseSeconds = waitOnCloseSeconds;
		this.outputBufferSize = outputBufferSize;
		this.maxOpenedReadHandles = maxOpenedReadHandles;
		this.computeCRC32C = computeCRC32C;
		this.transactionMemoryBufferLimitSizeBytes = transactionMemoryBufferLimitSizeBytes;
		this.transactionMemoryRegionCount = transactionMemoryRegionCount;
		this.walFileSizeBytes = walFileSizeBytes;
		this.walFileCountKept = walFileCountKept;
	}

	/**
	 * Method returns null safe data directory.
	 */
	@Nonnull
	public Path storageDirectoryOrDefault() {
		return storageDirectory == null ?
			DEFAULT_DIRECTORY : storageDirectory;
	}

	/**
	 * Standard builder pattern implementation.
	 */
	@ToString
	public static class Builder {
		private Path storageDirectory = DEFAULT_DIRECTORY;
		private Path transactionWorkDirectory = DEFAULT_TX_DIRECTORY;
		private long lockTimeoutSeconds = DEFAULT_LOCK_TIMEOUT_SECONDS;
		private long waitOnCloseSeconds = DEFAULT_WAIT_ON_CLOSE_SECONDS;
		private int outputBufferSize = DEFAULT_OUTPUT_BUFFER_SIZE;
		private int maxOpenedReadHandles = DEFAULT_MAX_OPENED_READ_HANDLES;
		private boolean computeCRC32C = DEFAULT_COMPUTE_CRC;
		private long transactionMemoryBufferLimitSizeBytes = DEFAULT_TRANSACTION_MEMORY_BUFFER_LIMIT_SIZE;
		private int transactionMemoryRegionCount = DEFAULT_TRANSACTION_MEMORY_REGION_COUNT;
		private long walFileSizeBytes = DEFAULT_WAL_SIZE_BYTES;
		private int walFileCountKept = DEFAULT_WAL_FILE_COUNT_KEPT;

		Builder() {
		}

		Builder(@Nonnull StorageOptions storageOptions) {
			this.storageDirectory = storageOptions.storageDirectory;
			this.transactionWorkDirectory = storageOptions.transactionWorkDirectory;
			this.lockTimeoutSeconds = storageOptions.lockTimeoutSeconds;
			this.waitOnCloseSeconds = storageOptions.waitOnCloseSeconds;
			this.outputBufferSize = storageOptions.outputBufferSize;
			this.maxOpenedReadHandles = storageOptions.maxOpenedReadHandles;
			this.computeCRC32C = storageOptions.computeCRC32C;
			this.transactionMemoryBufferLimitSizeBytes = storageOptions.transactionMemoryBufferLimitSizeBytes;
			this.transactionMemoryRegionCount = storageOptions.transactionMemoryRegionCount;
			this.walFileSizeBytes = storageOptions.walFileSizeBytes;
			this.walFileCountKept = storageOptions.walFileCountKept;
		}

		@Nonnull
		public Builder storageDirectory(@Nonnull Path storageDirectory) {
			this.storageDirectory = storageDirectory;
			return this;
		}

		@Nonnull
		public Builder transactionWorkDirectory(@Nonnull Path transactionWorkDirectory) {
			this.transactionWorkDirectory = transactionWorkDirectory;
			return this;
		}

		@Nonnull
		public Builder lockTimeoutSeconds(long lockTimeoutSeconds) {
			this.lockTimeoutSeconds = lockTimeoutSeconds;
			return this;
		}

		@Nonnull
		public Builder waitOnCloseSeconds(long waitOnCloseSeconds) {
			this.waitOnCloseSeconds = waitOnCloseSeconds;
			return this;
		}

		@Nonnull
		public Builder outputBufferSize(int outputBufferSize) {
			this.outputBufferSize = outputBufferSize;
			return this;
		}

		@Nonnull
		public Builder maxOpenedReadHandles(int maxOpenedReadHandles) {
			this.maxOpenedReadHandles = maxOpenedReadHandles;
			return this;
		}

		@Nonnull
		public Builder computeCRC32(boolean computeCRC32) {
			this.computeCRC32C = computeCRC32;
			return this;
		}

		@Nonnull
		public Builder transactionMemoryBufferLimitSizeBytes(long transactionMemoryBufferLimitSizeBytes) {
			this.transactionMemoryBufferLimitSizeBytes = transactionMemoryBufferLimitSizeBytes;
			return this;
		}

		@Nonnull
		public Builder transactionMemoryRegionCount(int transactionMemoryRegionCount) {
			this.transactionMemoryRegionCount = transactionMemoryRegionCount;
			return this;
		}

		@Nonnull
		public Builder walFileSizeBytes(long walFileSizeBytes) {
			this.walFileSizeBytes = walFileSizeBytes;
			return this;
		}

		@Nonnull
		public Builder walFileCountKept(int walFileCountKept) {
			this.walFileCountKept = walFileCountKept;
			return this;
		}

		@Nonnull
		public StorageOptions build() {
			return new StorageOptions(
				storageDirectory,
				transactionWorkDirectory,
				lockTimeoutSeconds,
				waitOnCloseSeconds,
				outputBufferSize,
				maxOpenedReadHandles,
				computeCRC32C,
				transactionMemoryBufferLimitSizeBytes,
				transactionMemoryRegionCount,
				walFileSizeBytes,
				walFileCountKept
			);
		}

	}

}
