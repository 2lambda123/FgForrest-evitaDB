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

package io.evitadb.core.buffer;

import io.evitadb.core.EntityCollection;
import io.evitadb.index.EntityIndex;
import io.evitadb.index.Index;
import io.evitadb.index.IndexKey;
import io.evitadb.index.transactionalMemory.TransactionalLayerCreator;
import io.evitadb.store.exception.CompressionKeyUnknownException;
import io.evitadb.store.model.StoragePart;
import io.evitadb.store.service.KeyCompressor;
import io.evitadb.store.spi.CatalogPersistenceService;
import io.evitadb.store.spi.StoragePartPersistenceService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import static io.evitadb.core.Transaction.getTransactionalMemoryLayer;
import static io.evitadb.core.Transaction.getTransactionalMemoryLayerIfExists;

/**
 * DataStoreMemoryBuffer represents volatile temporal memory between the {@link EntityCollection} and persistent
 * storage that takes {@link io.evitadb.core.Transaction} into an account. Even if transactional memory is not available
 * this buffer traps updates of certain objects in {@link DataStoreIndexMemoryBuffer} to avoid persistence of large
 * indexes with each update (which would drastically slow initial bulk database setup).
 *
 * All reads-writes are primarily targeting transactional memory if it's present for the current thread. If the value
 * is not found there it's located via {@link StoragePartPersistenceService#getStoragePart(long, Class)}.
 *
 * @author Jan Novotný (novotny@fg.cz), FG Forrest a.s. (c) 2021
 */
public class DataStoreMemoryBuffer<IK extends IndexKey, I extends Index<IK>, DSC extends DataStoreChanges<IK, I>> {
	/**
	 * The version number written for all stored {@link StoragePart} using this buffer.
	 */
	private final long nextCatalogVersion;
	/**
	 * Contains reference to the entity collection this buffer refers to.
	 */
	@Nonnull private final TransactionalLayerCreator<DSC> transactionalMemoryDataSource;
	/**
	 * DTO contains all trapped changes in this memory buffer.
	 */
	@Nonnull private final DataStoreIndexChanges<IK, I> dataStoreIndexChanges = new DataStoreIndexMemoryBuffer<>();
	/**
	 * Contains reference to the I/O service, that allows reading/writing records to the persistent storage.
	 */
	@Nonnull private StoragePartPersistenceService persistenceService;

	public DataStoreMemoryBuffer(
		long catalogVersion,
		@Nonnull TransactionalLayerCreator<DSC> transactionalMemoryDataSource,
		@Nonnull StoragePartPersistenceService persistenceService
	) {
		this.nextCatalogVersion = catalogVersion + 1L;
		this.transactionalMemoryDataSource = transactionalMemoryDataSource;
		this.persistenceService = persistenceService;
	}

	/**
	 * Method allows to refresh the I/O service.
	 */
	public void setPersistenceService(@Nonnull StoragePartPersistenceService persistenceService) {
		this.persistenceService = persistenceService;
	}

	/**
	 * Collects information about dirty indexes that need to be persisted. If transaction is opened, the changes
	 * are written only in the transactional layer and persisted at moment when transaction is committed. When
	 * transaction is not opened the changes are not immediately written to the persistent storage but trapped in shared
	 * memory and will be written when buffer is flushed. This is usually the case when entity index is just being
	 * created for the first time and the transaction were not yet enabled on it.
	 */
	public I getOrCreateIndexForModification(@Nonnull IK entityIndexKey, @Nonnull Function<IK, I> accessorWhenMissing) {
		final DataStoreChanges<IK, I> layer = getTransactionalMemoryLayer(transactionalMemoryDataSource);
		if (layer == null) {
			return dataStoreIndexChanges.getOrCreateIndexForModification(entityIndexKey, accessorWhenMissing);
		} else {
			return layer.getOrCreateIndexForModification(entityIndexKey, accessorWhenMissing);
		}
	}

	/**
	 * Returns {@link EntityIndex} by key if it already exists in change set. If the index is no present there
	 * `accessorWhenMissing` is executed to retrieve primary read-only index from the origin collection.
	 */
	public I getIndexIfExists(@Nonnull IK entityIndexKey, @Nonnull Function<IK, I> accessorWhenMissing) {
		final DataStoreChanges<IK, I> layer = getTransactionalMemoryLayerIfExists(transactionalMemoryDataSource);
		if (layer == null) {
			return dataStoreIndexChanges.getIndexIfExists(entityIndexKey, accessorWhenMissing);
		} else {
			return layer.getIndexIfExists(entityIndexKey, accessorWhenMissing);
		}
	}

	/**
	 * Removes {@link EntityIndex} from the change set. After removal (either successfully or unsuccessful)
	 * `removalPropagation` function is called to propagate deletion to the origin collection.
	 */
	public I removeIndex(@Nonnull IK entityIndexKey, @Nonnull Function<IK, I> removalPropagation) {
		final DataStoreChanges<IK, I> layer = getTransactionalMemoryLayerIfExists(transactionalMemoryDataSource);
		if (layer == null) {
			return dataStoreIndexChanges.removeIndex(entityIndexKey, removalPropagation);
		} else {
			return layer.removeIndex(entityIndexKey, removalPropagation);
		}
	}

	/**
	 * Reads container primarily from transactional memory and when the container is not present there (or transaction
	 * is not opened) reads it from the target {@link CatalogPersistenceService}.
	 */
	@Nullable
	public <T extends StoragePart> T fetch(long primaryKey, @Nonnull Class<T> containerType) {
		final DataStoreChanges<IK, I> layer = getTransactionalMemoryLayerIfExists(transactionalMemoryDataSource);
		if (layer == null) {
			return persistenceService.getStoragePart(primaryKey, containerType);
		} else {
			return layer.getStoragePart(primaryKey, containerType);
		}
	}

	/**
	 * Reads container primarily from transactional memory and when the container is not present there (or transaction
	 * is not opened) reads it from the target {@link CatalogPersistenceService}.
	 */
	@Nullable
	public <T extends StoragePart> byte[] fetchBinary(long primaryKey, @Nonnull Class<T> containerType) {
		final DataStoreChanges<IK, I> layer = getTransactionalMemoryLayerIfExists(transactionalMemoryDataSource);
		if (layer == null) {
			return persistenceService.getStoragePartAsBinary(primaryKey, containerType);
		} else {
			return layer.getStoragePartAsBinary(primaryKey, containerType);
		}
	}

	/**
	 * Reads container primarily from transactional memory and when the container is not present there (or transaction
	 * is not opened) reads it from the target {@link CatalogPersistenceService}.
	 */
	@Nullable
	public <T extends StoragePart, U extends Comparable<U>> T fetch(@Nonnull U originalKey, @Nonnull Class<T> containerType, @Nonnull BiFunction<KeyCompressor, U, Long> compressedKeyComputer) {
		final DataStoreChanges<IK, I> layer = getTransactionalMemoryLayerIfExists(transactionalMemoryDataSource);
		if (layer == null) {
			try {
				final long storagePartId = compressedKeyComputer.apply(this.persistenceService.getReadOnlyKeyCompressor(), originalKey);
				return persistenceService.getStoragePart(storagePartId, containerType);
			} catch (CompressionKeyUnknownException ex) {
				// key wasn't yet assigned
				return null;
			}
		} else {
			try {
				final long storagePartId = compressedKeyComputer.apply(layer.getReadOnlyKeyCompressor(), originalKey);
				return layer.getStoragePart(storagePartId, containerType);
			} catch (CompressionKeyUnknownException ex) {
				// key wasn't yet assigned
				return null;
			}
		}
	}

	/**
	 * Reads container primarily from transactional memory and when the container is not present there (or transaction
	 * is not opened) reads it from the target {@link CatalogPersistenceService}.
	 */
	@Nullable
	public <T extends StoragePart, U extends Comparable<U>> byte[] fetchBinary(@Nonnull U originalKey, @Nonnull Class<T> containerType, @Nonnull BiFunction<KeyCompressor, U, Long> compressedKeyComputer) {
		final DataStoreChanges<IK, I> layer = getTransactionalMemoryLayerIfExists(transactionalMemoryDataSource);
		if (layer == null) {
			try {
				final long nonFlushedCompressedId = compressedKeyComputer.apply(this.persistenceService.getReadOnlyKeyCompressor(), originalKey);
				return persistenceService.getStoragePartAsBinary(nonFlushedCompressedId, containerType);
			} catch (CompressionKeyUnknownException ex) {
				// key wasn't yet assigned
				return null;
			}
		} else {
			try {
				final long nonFlushedCompressedId = compressedKeyComputer.apply(layer.getReadOnlyKeyCompressor(), originalKey);
				return layer.getStoragePartAsBinary(nonFlushedCompressedId, containerType);
			} catch (CompressionKeyUnknownException ex) {
				// key wasn't yet assigned
				return null;
			}
		}
	}

	/**
	 * Removes container from the target storage. If transaction is open, it just marks the container as removed but
	 * doesn't really remove it.
	 */
	public <T extends StoragePart> boolean removeByPrimaryKey(long primaryKey, @Nonnull Class<T> entityClass) {
		final DataStoreChanges<IK, I> layer = getTransactionalMemoryLayer(transactionalMemoryDataSource);
		if (layer == null) {
			return this.persistenceService.removeStoragePart(primaryKey, entityClass);
		} else {
			return layer.removeStoragePart(primaryKey, entityClass);
		}
	}

	/**
	 * Inserts or updates container in the target storage. If transaction is opened, the changes are written only in
	 * the transactional layer and are not really written to the persistent storage. Changes are written at the moment
	 * when transaction is committed.
	 */
	public <T extends StoragePart> void update(@Nonnull T value) {
		final DataStoreChanges<IK, I> layer = getTransactionalMemoryLayer(transactionalMemoryDataSource);
		if (layer == null) {
			this.persistenceService.putStoragePart(nextCatalogVersion, value);
		} else {
			layer.putStoragePart(nextCatalogVersion, value);
		}
	}

	/**
	 * Method returns current buffer with trapped changes.
	 */
	public DataStoreIndexChanges<IK, I> getTrappedIndexChanges() {
		final DataStoreChanges<IK, I> layer = getTransactionalMemoryLayer(transactionalMemoryDataSource);
		// return current transactional layer that contains trapped updates
		// or fallback to shared memory buffer with trapped updates
		return Objects.requireNonNullElse(layer, this.dataStoreIndexChanges);
	}

}