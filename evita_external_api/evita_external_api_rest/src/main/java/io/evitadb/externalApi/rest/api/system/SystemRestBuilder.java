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

package io.evitadb.externalApi.rest.api.system;

import io.evitadb.core.Evita;
import io.evitadb.externalApi.api.system.model.CatalogDescriptor;
import io.evitadb.externalApi.rest.api.Rest;
import io.evitadb.externalApi.rest.api.builder.FinalRestBuilder;
import io.evitadb.externalApi.rest.api.model.ErrorDescriptor;
import io.evitadb.externalApi.rest.api.system.builder.SystemEndpointBuilder;
import io.evitadb.externalApi.rest.api.system.builder.SystemRestBuildingContext;
import io.evitadb.externalApi.rest.api.system.model.CreateCatalogRequestDescriptor;
import io.evitadb.externalApi.rest.api.system.model.LivenessDescriptor;
import io.evitadb.externalApi.rest.api.system.model.UpdateCatalogRequestDescriptor;
import io.evitadb.externalApi.rest.configuration.RestConfig;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;

/**
 * Creates OpenAPI specification for evitaDB management.
 *
 * @author Lukáš Hornych, FG Forrest a.s. (c) 2023
 */
@Slf4j
public class SystemRestBuilder extends FinalRestBuilder<SystemRestBuildingContext> {

	@Nonnull private final SystemEndpointBuilder endpointBuilder;

	/**
	 * Creates new builder.
	 */
	public SystemRestBuilder(@Nonnull RestConfig restConfig, @Nonnull Evita evita) {
		super(new SystemRestBuildingContext(restConfig, evita));
		this.endpointBuilder = new SystemEndpointBuilder(operationPathParameterBuilderTransformer);
	}

	/**
	 * Builds OpenAPI specification for evitaDB management.
	 *
	 * @return OpenAPI specification
	 */
	public Rest build() {
		buildCommonTypes();
		buildEndpoints();

		return buildingContext.buildRest();
	}

	private void buildCommonTypes() {
		buildingContext.registerType(ErrorDescriptor.THIS.to(objectBuilderTransformer).build());
		buildingContext.registerType(LivenessDescriptor.THIS.to(objectBuilderTransformer).build());
		buildingContext.registerType(CatalogDescriptor.THIS.to(objectBuilderTransformer).build());
		buildingContext.registerType(CreateCatalogRequestDescriptor.THIS.to(objectBuilderTransformer).build());
		buildingContext.registerType(UpdateCatalogRequestDescriptor.THIS.to(objectBuilderTransformer).build());
	}

	private void buildEndpoints() {
		buildingContext.registerEndpoint(endpointBuilder.buildOpenApiSpecificationEndpoint());
		buildingContext.registerEndpoint(endpointBuilder.buildLivenessEndpoint());
		buildingContext.registerEndpoint(endpointBuilder.buildGetCatalogEndpoint());
		buildingContext.registerEndpoint(endpointBuilder.buildListCatalogsEndpoint());
		buildingContext.registerEndpoint(endpointBuilder.buildCreateCatalogEndpoint());
		buildingContext.registerEndpoint(endpointBuilder.buildUpdateCatalogEndpoint());
		buildingContext.registerEndpoint(endpointBuilder.buildDeleteCatalogEndpoint());
	}
}
