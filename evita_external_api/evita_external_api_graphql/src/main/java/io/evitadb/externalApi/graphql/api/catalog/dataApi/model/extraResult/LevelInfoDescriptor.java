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

package io.evitadb.externalApi.graphql.api.catalog.dataApi.model.extraResult;

import io.evitadb.api.requestResponse.extraResult.Hierarchy;
import io.evitadb.externalApi.api.model.ObjectDescriptor;
import io.evitadb.externalApi.api.model.PropertyDescriptor;

import java.util.List;

import static io.evitadb.externalApi.api.model.PrimitivePropertyDataTypeDescriptor.nonNull;
import static io.evitadb.externalApi.api.model.PrimitivePropertyDataTypeDescriptor.nullable;

/**
 * Represents {@link Hierarchy.LevelInfo}.
 *
 * Note: this descriptor is meant be template for generated specific DTOs base on internal data. Fields in this
 * descriptor are supposed to be dynamically registered to target generated DTO.
 */
public interface LevelInfoDescriptor {

	PropertyDescriptor PARENT_PRIMARY_KEY = PropertyDescriptor.builder()
		.name("parentPrimaryKey")
		.description("""
			Primary key of parent hierarchical entity if this entity is not root entity.
			""")
		.type(nullable(Integer.class))
		.build();
	PropertyDescriptor LEVEL = PropertyDescriptor.builder()
		.name("level")
		.description("""
			Level on which this node is placed from root. Root is always on level 1.
						""")
		.type(nonNull(Integer.class))
		.build();
	PropertyDescriptor ENTITY = PropertyDescriptor.builder()
		.name("entity")
		.description("""
			Hierarchical entity at position in tree represented by this object.
			""")
		// type is expected to be a `Entity` object
		.build();
	PropertyDescriptor QUERIED_ENTITY_COUNT = PropertyDescriptor.builder()
		.name("queriedEntityCount")
		.description("""
			Contains the number of queried entities that refer directly to this {@link #entity} or to any of its children
			entities.
			""")
		.type(nonNull(Integer.class))
		.build();
	PropertyDescriptor CHILDREN_COUNT = PropertyDescriptor.builder()
		.name("childrenCount")
		.description("""
			Contains number of hierarchical entities that are referring to this `entity` as its parent.
			The count will respect behaviour settings and will not
			count empty children in case `REMOVE_EMPTY` is
			used for computation.
			""")
		.type(nonNull(Integer.class))
		.build();
	PropertyDescriptor HAS_CHILDREN = PropertyDescriptor.builder()
		.name("hasChildren")
		.description("""
			Whether this hierarchical entity has any child entities.
						""")
		.type(nonNull(Boolean.class))
		.build();

	ObjectDescriptor THIS = ObjectDescriptor.builder()
		.name("*LevelInfo")
		.description("""
			This DTO represents single hierarchical entity in the hierarchy tree. It contains identification of the entity,
			the cardinality of queried entities that refer to it and information about children level.
			""")
		.staticFields(List.of(PARENT_PRIMARY_KEY, LEVEL, QUERIED_ENTITY_COUNT, CHILDREN_COUNT, HAS_CHILDREN))
		.build();
}