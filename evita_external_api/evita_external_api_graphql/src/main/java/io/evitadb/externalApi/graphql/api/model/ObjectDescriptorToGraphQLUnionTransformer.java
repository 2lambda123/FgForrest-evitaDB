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

package io.evitadb.externalApi.graphql.api.model;

import graphql.schema.GraphQLUnionType;
import io.evitadb.externalApi.api.model.ObjectDescriptor;
import io.evitadb.externalApi.api.model.ObjectDescriptorTransformer;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nonnull;

/**
 * Transforms API-independent {@link ObjectDescriptor} to GraphQL union definition.
 *
 * @author Lukáš Hornych, FG Forrest a.s. (c) 2022
 */
@RequiredArgsConstructor
public class ObjectDescriptorToGraphQLUnionTransformer implements ObjectDescriptorTransformer<GraphQLUnionType.Builder> {

	@Override
	public GraphQLUnionType.Builder apply(@Nonnull ObjectDescriptor objectDescriptor) {
		final GraphQLUnionType.Builder unionBuilder = GraphQLUnionType.newUnionType();

		if (objectDescriptor.isNameStatic()) {
			unionBuilder.name(objectDescriptor.name());
		}
		if (objectDescriptor.description() != null) {
			unionBuilder.description(objectDescriptor.description());
		}

		return unionBuilder;
	}
}