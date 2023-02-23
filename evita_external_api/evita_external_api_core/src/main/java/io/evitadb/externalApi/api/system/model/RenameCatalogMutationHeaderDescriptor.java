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

package io.evitadb.externalApi.api.system.model;

import io.evitadb.externalApi.api.model.PropertyDescriptor;

import static io.evitadb.externalApi.api.model.PrimitivePropertyDataTypeDescriptor.nonNull;

/**
 * Descriptor for header arguments of {@link SystemRootDescriptor#RENAME_CATALOG}
 * query.
 *
 * @author Lukáš Hornych, FG Forrest a.s. (c) 2023
 */
public interface RenameCatalogMutationHeaderDescriptor {

	PropertyDescriptor NAME = PropertyDescriptor.builder()
		.name("name")
		.description("""
			Name of existing catalog to rename.
			""")
		.type(nonNull(String.class))
		.build();
	PropertyDescriptor NEW_NAME = PropertyDescriptor.builder()
		.name("newName")
		.description("""
			New name of catalog the existing one will be replaced to.
			""")
		.type(nonNull(String.class))
		.build();
}
