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

package io.evitadb.externalApi.graphql.api.catalog.schemaApi.resolver.dataFetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import io.evitadb.api.requestResponse.schema.AttributeSchemaProvider;
import io.evitadb.api.requestResponse.schema.SortableAttributeCompoundSchemaContract;
import io.evitadb.api.requestResponse.schema.SortableAttributeCompoundSchemaProvider;
import io.evitadb.api.requestResponse.schema.dto.AttributeSchema;
import io.evitadb.api.requestResponse.schema.dto.SortableAttributeCompoundSchema;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * Returns collection of {@link SortableAttributeCompoundSchemaContract}s from {@link SortableAttributeCompoundSchemaProvider}.
 *
 * @author Lukáš Hornych, FG Forrest a.s. (c) 2022
 */
public class AllSortableAttributeCompoundSchemasDataFetcher implements DataFetcher<Collection<SortableAttributeCompoundSchemaContract>> {

	@Nonnull
	@Override
	public Collection<SortableAttributeCompoundSchemaContract> get(@Nonnull DataFetchingEnvironment environment) throws Exception {
		final SortableAttributeCompoundSchemaProvider provider = environment.getSource();
		return provider.getSortableAttributeCompounds().values();
	}
}