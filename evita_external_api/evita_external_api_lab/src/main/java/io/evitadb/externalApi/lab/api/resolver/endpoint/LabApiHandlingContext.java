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

package io.evitadb.externalApi.lab.api.resolver.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.evitadb.api.CatalogContract;
import io.evitadb.api.requestResponse.schema.EntitySchemaContract;
import io.evitadb.core.Evita;
import io.evitadb.externalApi.rest.exception.RestInternalError;
import io.evitadb.externalApi.rest.io.RestHandlingContext;
import io.evitadb.utils.NamingConvention;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;

/**
 * This class contains information required to process REST API requests. Not all attributes has to be set
 * it depends on needs of particular handler.
 *
 * @author Martin Veska (veska@fg.cz), FG Forrest a.s. (c) 2022
 */
public class LabApiHandlingContext extends RestHandlingContext {


	public LabApiHandlingContext(@Nonnull ObjectMapper objectMapper,
	                             @Nonnull Evita evita,
	                             @Nonnull OpenAPI openApi,
	                             @Nonnull Map<String, Class<? extends Enum<?>>> enumMapping,
	                             @Nonnull Operation endpointOperation) {
		super(objectMapper, evita, openApi, enumMapping, endpointOperation, false);
	}

	/**
	 * Gets entity schema for any entity by entity name
	 */
	@Nonnull
	public EntitySchemaContract getEntitySchema(@Nonnull String catalogName, @Nonnull String entityName) {
		return evita.getCatalogInstanceOrThrowException(catalogName)
			.getEntitySchema(entityName)
			.orElseThrow(() -> new RestInternalError("No schema found for entity: " + entityName + " in catalog: " + catalogName));
	}
}
