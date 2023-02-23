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

package io.evitadb.externalApi.grpc.artificial.state;

import io.evitadb.api.requestResponse.data.structure.EntityReference;
import io.evitadb.api.requestResponse.schema.SealedEntitySchema;
import io.evitadb.externalApi.grpc.artificial.GrpcArtificialFullDatabaseBenchmarkState;
import io.evitadb.generators.RandomQueryGenerator;
import io.evitadb.setup.EvitaCatalogReusableSetup;
import io.evitadb.test.Entities;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * Base state class for hierarchy-related benchmarks.
 * See benchmark description on the method.
 *
 * @author Lukáš Hornych, FG Forrest a.s. (c) 2022
 */
public class GrpcArtificialHierarchyBenchmarkState extends GrpcArtificialFullDatabaseBenchmarkState
	implements RandomQueryGenerator, EvitaCatalogReusableSetup {

	/**
	 * Set contains all `entityTypes` that are hierarchical.
	 */
	@Getter private final Set<String> hierarchicalEntities = new HashSet<>();
	/**
	 * Contains maximal product id.
	 */
	@Getter private int maxProductId = -1;


	@Override
	protected SealedEntitySchema processSchema(SealedEntitySchema schema) {
		if (schema.getName().equals(Entities.PRODUCT)) {
			this.productSchema = schema;
		} else {
			if (schema.isWithHierarchy()) {
				hierarchicalEntities.add(schema.getName());
			}
		}
		return schema;
	}

	@Override
	protected void processCreatedEntityReference(EntityReference entity) {
		super.processCreatedEntityReference(entity);
		if (entity.getType().equals(Entities.PRODUCT) && entity.getPrimaryKey() > maxProductId) {
			maxProductId = entity.getPrimaryKey();
		}
	}
}