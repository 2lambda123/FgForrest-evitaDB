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

package io.evitadb.core.transaction.stage;

import io.evitadb.api.exception.TransactionTimedOutException;
import io.evitadb.core.Catalog;
import io.evitadb.utils.Assert;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.BiPredicate;

/**
 * Abstract class representing a transaction stage in a catalog processing pipeline.
 * It is a {@link Flow} processor that receives a specific type of transaction task, processes it, and produces
 * a different type of transaction task.
 *
 * @param <T> The type of the input transaction task.
 * @param <F> The type of the output transaction task.
 * @author Jan Novotný (novotny@fg.cz), FG Forrest a.s. (c) 2024
 */
@Slf4j
public sealed abstract class AbstractTransactionStage<T extends TransactionTask, F extends TransactionTask>
	extends SubmissionPublisher<F>
	implements Flow.Processor<T, F>
	permits ConflictResolutionTransactionStage, WalAppendingTransactionStage, TrunkIncorporationTransactionStage {

	/**
	 * The name of the catalog the processor is bound to. Each catalog has its own transaction processor.
	 */
	private final String catalogName;
	/**
	 * The subscription variable represents a subscription to a reactive stream.
	 * It is used to manage the flow of data from the publisher to the subscriber.
	 *
	 * @see Flow.Subscription
	 */
	private Flow.Subscription subscription;
	/**
	 * Represents the "lag" of the next stage observed during {@link SubmissionPublisher#offer(Object, BiPredicate)}.
	 *
	 * @see SubmissionPublisher#offer(Object, BiPredicate)
	 */
	@Getter private volatile int stageHandoff = 0;
	/**
	 * Contains TRUE if the processor has been completed and does not accept any more data.
	 */
	@Getter private boolean completed;

	protected AbstractTransactionStage(@Nonnull Executor executor, int maxBufferCapacity, @Nonnull String catalogName) {
		super(executor, maxBufferCapacity);
		this.catalogName = catalogName;
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		subscription.request(1);
	}

	@Override
	public final void onNext(T task) {
		try {
			Assert.isPremiseValid(
				Objects.equals(catalogName, task.catalogName()),
				"Catalog name mismatch!"
			);
			// delegate handling logic to the concrete implementation
			handleNext(task);
		} catch (Throwable ex) {
			log.error("Error while processing " + getName() + " task for catalog `" + catalogName + "`!", ex);
			final CompletableFuture<Long> future = task.future();
			if (future != null) {
				future.completeExceptionally(ex);
			}
		}
		subscription.request(1);
	}

	@Override
	public final void onError(Throwable throwable) {
		log.error(
			"Fatal error! Error propagated outside catalog `" + catalogName + "` conflict resolution transaction stage! " +
				"This is unexpected and effectively stops transaction processing!",
			throwable
		);
	}

	@Override
	public final void onComplete() {
		log.debug("Conflict resolution transaction stage completed for catalog `" + catalogName + "`!");
		this.completed = true;
	}

	/**
	 * Retrieves the name of the transaction stage. The name is used in logs and exceptions.
	 *
	 * @return The name of the transaction stage as a String.
	 */
	protected abstract String getName();

	/**
	 * Handles the next transaction task. It converts the source task to the target task and pushes it to the next
	 * transaction stage. During the transformation all necessary actions are performed.
	 *
	 * @param task The task to be handled.
	 */
	protected abstract void handleNext(@Nonnull T task);

	/**
	 * Method is called when new catalog version is propagated to the "live view" in evitaDB engine.
	 * @param catalog
	 */
	public abstract void updateCatalogReference(@Nonnull Catalog catalog);

	/**
	 * Pushes a target task to the next transaction stage.
	 * If the target task's future is null, it completes the future with a new catalog version.
	 *
	 * @param sourceTask The source task to be pushed.
	 * @param targetTask The target task to be created.
	 */
	protected void push(@Nonnull T sourceTask, @Nonnull F targetTask) {
		this.stageHandoff = offer(
			targetTask,
			(subscriber, theTask) -> {
				theTask.future().completeExceptionally(
					new TransactionTimedOutException(
						"Transaction timed out - capacity of WAL appender reached (`" + getMaxBufferCapacity() + "`)!"
					)
				);
				return false;
			}
		);

		if (sourceTask.future() != null && targetTask.future() == null) {
			sourceTask.future().complete(targetTask.catalogVersion());
		}
	}

}