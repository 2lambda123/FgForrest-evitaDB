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

package io.evitadb.externalApi.rest.api.resolver.endpoint;

import io.evitadb.externalApi.http.MimeTypes;
import io.evitadb.externalApi.rest.api.openApi.OpenApiWriter;
import io.evitadb.externalApi.rest.io.RestHandler;
import io.evitadb.externalApi.rest.io.RestHandlingContext;
import io.undertow.server.HttpServerExchange;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 * Returns OpenAPI schema for whole collection.
 *
 * @author Martin Veska (veska@fg.cz), FG Forrest a.s. (c) 2022
 */
public class OpenApiSpecificationHandler<C extends RestHandlingContext> extends RestHandler<C> {

	public OpenApiSpecificationHandler(@Nonnull C restHandlingContext) {
		super(restHandlingContext);
	}

	@Nonnull
	@Override
	public Optional<Object> doHandleRequest(@Nonnull HttpServerExchange exchange) {
		return Optional.of(
			OpenApiWriter.toYaml(restApiHandlingContext.getOpenApi())
		);
	}

	@Nonnull
	@Override
	protected String getContentType() {
		return MimeTypes.APPLICATION_YAML;
	}
}