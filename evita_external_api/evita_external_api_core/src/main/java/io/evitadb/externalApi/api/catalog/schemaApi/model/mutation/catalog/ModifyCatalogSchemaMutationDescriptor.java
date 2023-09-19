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

package io.evitadb.externalApi.api.catalog.schemaApi.model.mutation.catalog;

import io.evitadb.externalApi.api.catalog.schemaApi.model.mutation.LocalCatalogSchemaMutationAggregateDescriptor;
import io.evitadb.externalApi.api.model.ObjectDescriptor;
import io.evitadb.externalApi.api.model.PropertyDescriptor;

import static io.evitadb.externalApi.api.model.ObjectPropertyDataTypeDescriptor.nonNullListRef;

/**
 * TODO lho docs
 *
 * @author Lukáš Hornych, 2023
 */
public interface ModifyCatalogSchemaMutationDescriptor extends TopLevelCatalogSchemaMutationDescriptor {

	PropertyDescriptor SCHEMA_MUTATIONS = PropertyDescriptor.builder()
		.name("schemaMutations")
		.description("""
			Contains all possible schema mutations for catalog.
			""")
		.type(nonNullListRef(LocalCatalogSchemaMutationAggregateDescriptor.THIS))
		.build();

	ObjectDescriptor THIS = ObjectDescriptor.builder()
		.name("ModifyCatalogSchemaMutation")
		.description("""
			 Mutation is a holder for a set of catalog schema mutations that affect a internal contents of the catalog
			 schema itself.
			""")
		.staticField(CATALOG_NAME)
		.staticField(SCHEMA_MUTATIONS)
		.build();
}
