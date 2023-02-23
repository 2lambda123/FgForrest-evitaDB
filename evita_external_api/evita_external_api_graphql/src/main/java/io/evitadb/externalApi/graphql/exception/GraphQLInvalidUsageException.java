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

package io.evitadb.externalApi.graphql.exception;

import io.evitadb.externalApi.exception.ExternalApiInvalidUsageException;

import javax.annotation.Nonnull;
import java.io.Serial;

/**
 * This exception represents an error that is caused by the client data or usage of Evita's GraphQL API. All exceptions of this
 * type can be solved by the client side by changing its behaviour.
 *
 * @author Lukáš Hornych, FG Forrest a.s. (c) 2022
 */
public class GraphQLInvalidUsageException extends ExternalApiInvalidUsageException {

	@Serial private static final long serialVersionUID = -6620965858364276913L;

	public GraphQLInvalidUsageException(@Nonnull String privateMessage, @Nonnull String publicMessage) {
		super(privateMessage, publicMessage);
	}

	public GraphQLInvalidUsageException(@Nonnull String publicMessage) {
		super(publicMessage);
	}

	public GraphQLInvalidUsageException(@Nonnull String privateMessage, @Nonnull String publicMessage, @Nonnull Throwable cause) {
		super(privateMessage, publicMessage, cause);
	}

	public GraphQLInvalidUsageException(@Nonnull String publicMessage, @Nonnull Throwable cause) {
		super(publicMessage, cause);
	}
}
