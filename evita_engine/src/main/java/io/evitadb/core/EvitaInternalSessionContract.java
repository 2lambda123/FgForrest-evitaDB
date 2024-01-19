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

package io.evitadb.core;

import io.evitadb.api.CatalogContract;
import io.evitadb.api.EvitaSessionContract;
import io.evitadb.api.exception.InstanceTerminatedException;
import io.evitadb.api.exception.UnexpectedResultCountException;
import io.evitadb.api.exception.UnexpectedResultException;
import io.evitadb.api.query.Query;
import io.evitadb.api.requestResponse.EvitaRequest;
import io.evitadb.api.requestResponse.EvitaResponse;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * This interface extends the public interface and adds a few methods that are targeted for internal use of EvitaDB
 * API only.
 *
 * @author Jan Novotný (novotny@fg.cz), FG Forrest a.s. (c) 2022
 */
public interface EvitaInternalSessionContract extends EvitaSessionContract {

	/**
	 * Opens a new transaction. This method returns a UUID
	 * that represents the identification of the opened transaction.
	 *
	 * @return The UUID of the opened transaction.
	 */
	@Nonnull
	UUID openTransaction();

	/**
	 * Method executes query on {@link CatalogContract} data and returns zero or exactly one entity result. Method
	 * behaves exactly the same as {@link #query(Query, Class)} but verifies the count of returned results and
	 * translates it to simplified return type.
	 *
	 * Because result is generic and may contain different data as its contents (based on input query), additional
	 * parameter `expectedType` is passed. This parameter allows to check whether passed response contains the expected
	 * type of data before returning it back to the client. This should prevent late ClassCastExceptions on the client
	 * side.
	 *
	 * @param evitaRequest externally created evita request
	 * @throws UnexpectedResultException      when result object is not assignable to `expectedType`
	 * @throws UnexpectedResultCountException when query matched more than single record
	 * @throws InstanceTerminatedException    when session has been already terminated
	 */
	@Nonnull
	<S extends Serializable> Optional<S> queryOne(@Nonnull EvitaRequest evitaRequest)
		throws UnexpectedResultException, UnexpectedResultCountException, InstanceTerminatedException;

	/**
	 * Method executes query on {@link CatalogContract} data and returns simplified list of results. Method behaves
	 * exactly the same as {@link #query(Query, Class)} but verifies the count of returned results and translates
	 * it to simplified return type. This method will throw out all possible extra results from, because there is
	 * no way how to propagate them in return value. If you require extra results or paginated list use
	 * the {@link #query(Query, Class)} method.
	 *
	 * Because result is generic and may contain different data as its contents (based on input query), additional
	 * parameter `expectedType` is passed. This parameter allows to check whether passed response contains the expected
	 * type of data before returning it back to the client. This should prevent late ClassCastExceptions on the client
	 * side.
	 *
	 * @param evitaRequest externally created evita request
	 * @throws UnexpectedResultException   when result object is not assignable to `expectedType`
	 * @throws InstanceTerminatedException when session has been already terminated
	 */
	@Nonnull
	<S extends Serializable> List<S> queryList(@Nonnull EvitaRequest evitaRequest)
		throws UnexpectedResultException, InstanceTerminatedException;

	/**
	 * Method executes query on {@link CatalogContract} data and returns result. Because result is generic and may contain
	 * different data as its contents (based on input query), additional parameter `expectedType` is passed. This parameter
	 * allows to check whether passed response contains the expected type of data before returning it back to the client.
	 * This should prevent late ClassCastExceptions on the client side.
	 *
	 * @param evitaRequest externally created evita request
	 * @throws UnexpectedResultException   when {@link EvitaResponse#getRecordPage()} contains data that are not assignable to `expectedType`
	 * @throws InstanceTerminatedException when session has been already terminated
	 */
	@Nonnull
	<S extends Serializable, T extends EvitaResponse<S>> T query(@Nonnull EvitaRequest evitaRequest)
		throws UnexpectedResultException, InstanceTerminatedException;

	/**
	 * TODO JNO - document me
	 * @return
	 */
	@Nonnull
	Optional<CompletableFuture<Long>> getTransactionFinalizationFuture();

}
