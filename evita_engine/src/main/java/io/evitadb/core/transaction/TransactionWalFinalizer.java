/*
 *
 *                         _ _        ____  ____
 *               _____   _(_) |_ __ _|  _ \| __ )
 *              / _ \ \ / / | __/ _` | | | |  _ \
 *             |  __/\ V /| | || (_| | |_| | |_) |
 *              \___| \_/ |_|\__\__,_|____/|____/
 *
 *   Copyright (c) 2024
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

package io.evitadb.core.transaction;

import io.evitadb.api.TransactionContract.CommitBehaviour;
import io.evitadb.api.requestResponse.mutation.Mutation;
import io.evitadb.core.Catalog;
import io.evitadb.index.transactionalMemory.TransactionalLayerMaintainer;
import io.evitadb.store.spi.IsolatedWalPersistenceService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import java.io.Closeable;
import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * TODO JNO - document me
 *
 * @author Jan Novotný (novotny@fg.cz), FG Forrest a.s. (c) 2024
 */
@Slf4j
@NotThreadSafe
public class TransactionWalFinalizer implements TransactionHandler {
	private final @Nonnull UUID transactionId;
	/**
	 * Contains commit behaviour for this transaction.
	 *
	 * @see CommitBehaviour
	 */
	@Getter private final CommitBehaviour commitBehaviour;
	private final @Nonnull Catalog catalog;
	private final @Nonnull LinkedList<Closeable> closeables = new LinkedList<>();
	/**
	 * TODO JNO - document me
	 */
	private final @Nonnull Function<UUID, IsolatedWalPersistenceService> walPersistenceServiceFactory;

	private final CompletableFuture<Long> transactionFinalizationFuture;
	private IsolatedWalPersistenceService walPersistenceService;

	public TransactionWalFinalizer(
		@Nonnull Catalog catalog,
		@Nonnull UUID transactionId,
		@Nonnull CommitBehaviour commitBehaviour,
		@Nonnull Function<UUID, IsolatedWalPersistenceService> walPersistenceServiceFactory,
		@Nonnull CompletableFuture<Long> transactionFinalizationFuture
	) {
		this.catalog = catalog;
		this.transactionId = transactionId;
		this.commitBehaviour = commitBehaviour;
		this.walPersistenceServiceFactory = walPersistenceServiceFactory;
		this.transactionFinalizationFuture = transactionFinalizationFuture;
	}

	public void registerCloseable(@Nonnull Closeable objectToClose) {
		closeables.add(objectToClose);
	}

	@Override
	public void commit(@Nonnull TransactionalLayerMaintainer transactionalLayer) {
		try {
			closeRegisteredCloseables();
			if (walPersistenceService != null) {
				catalog.commitWal(
					transactionId,
					commitBehaviour,
					walPersistenceService,
					transactionFinalizationFuture
				);
			}
		} finally {
			// throw away all memory changes (they will be reloaded and replayed in correct sequence order from the WAL)
			transactionalLayer.discard();
			// close the WAL persistence service
			if (walPersistenceService != null) {
				walPersistenceService.close();
				walPersistenceService = null;
			}
		}
	}

	@Override
	public void rollback(@Nonnull TransactionalLayerMaintainer transactionalLayer) {
		try {
			closeRegisteredCloseables();
		} finally {
			if (walPersistenceService != null) {
				walPersistenceService.close();
				walPersistenceService = null;
			}
		}
	}

	@Override
	public void registerMutation(@Nonnull Mutation mutation) {
		if (this.walPersistenceService == null) {
			this.walPersistenceService = walPersistenceServiceFactory.apply(transactionId);
		}
		this.walPersistenceService.write(catalog.getVersion(), mutation);
	}

	private void closeRegisteredCloseables() {
		for (Closeable closeable : closeables) {
			try {
				closeable.close();
			} catch (Exception ex) {
				log.error("Error closing object {}", closeable, ex);
			}
		}
	}
}
