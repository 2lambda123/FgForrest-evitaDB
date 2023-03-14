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

package io.evitadb.externalApi.rest.api.system.builder;

import io.evitadb.externalApi.api.system.model.CatalogDescriptor;
import io.evitadb.externalApi.rest.api.dataType.DataTypesConverter;
import io.evitadb.externalApi.rest.api.model.PropertyDescriptorToOpenApiOperationPathParameterTransformer;
import io.evitadb.externalApi.rest.api.model.RestRootDescriptor;
import io.evitadb.externalApi.rest.api.openApi.OpenApiSystemEndpoint;
import io.evitadb.externalApi.rest.api.resolver.endpoint.OpenApiSpecificationHandler;
import io.evitadb.externalApi.rest.api.system.model.CatalogsHeaderDescriptor;
import io.evitadb.externalApi.rest.api.system.model.CreateCatalogRequestDescriptor;
import io.evitadb.externalApi.rest.api.system.model.LivenessDescriptor;
import io.evitadb.externalApi.rest.api.system.model.SystemRootDescriptor;
import io.evitadb.externalApi.rest.api.system.model.UpdateCatalogRequestDescriptor;
import io.evitadb.externalApi.rest.api.system.resolver.endpoint.CreateCatalogHandler;
import io.evitadb.externalApi.rest.api.system.resolver.endpoint.DeleteCatalogHandler;
import io.evitadb.externalApi.rest.api.system.resolver.endpoint.GetCatalogHandler;
import io.evitadb.externalApi.rest.api.system.resolver.endpoint.ListCatalogsHandler;
import io.evitadb.externalApi.rest.api.system.resolver.endpoint.LivenessHandler;
import io.evitadb.externalApi.rest.api.system.resolver.endpoint.UpdateCatalogHandler;
import io.swagger.v3.oas.models.PathItem.HttpMethod;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nonnull;

import static io.evitadb.externalApi.api.ExternalApiNamingConventions.URL_NAME_NAMING_CONVENTION;
import static io.evitadb.externalApi.rest.api.openApi.OpenApiArray.arrayOf;
import static io.evitadb.externalApi.rest.api.openApi.OpenApiNonNull.nonNull;
import static io.evitadb.externalApi.rest.api.openApi.OpenApiSystemEndpoint.newSystemEndpoint;
import static io.evitadb.externalApi.rest.api.openApi.OpenApiTypeReference.typeRefTo;

/**
 * Creates OpenAPI {@link io.evitadb.externalApi.rest.api.openApi.OpenApiEndpoint} for each schema API endpoint.
 * for requests processing.
 *
 * @author Lukáš Hornych, FG Forrest a.s. (c) 2023
 */
@RequiredArgsConstructor
public class SystemEndpointBuilder {

	@Nonnull private final PropertyDescriptorToOpenApiOperationPathParameterTransformer operationPathParameterBuilderTransformer;

	@Nonnull
	public OpenApiSystemEndpoint buildOpenApiSpecificationEndpoint() {
		return newSystemEndpoint()
			.path(p -> p) // directly at the system root
			.method(HttpMethod.GET)
			.description(RestRootDescriptor.OPEN_API_SPECIFICATION.description())
			.successResponse(nonNull(DataTypesConverter.getOpenApiScalar(String.class)))
			.handler(OpenApiSpecificationHandler::new)
			.build();
	}

	@Nonnull
	public OpenApiSystemEndpoint buildLivenessEndpoint() {
		return newSystemEndpoint()
			.path(p -> p
				.staticItem(SystemRootDescriptor.LIVENESS.operation(URL_NAME_NAMING_CONVENTION)))
			.method(HttpMethod.GET)
			.description(SystemRootDescriptor.LIVENESS.description())
			.successResponse(nonNull(typeRefTo(LivenessDescriptor.THIS.name())))
			.handler(LivenessHandler::new)
			.build();
	}

	@Nonnull
	public OpenApiSystemEndpoint buildGetCatalogEndpoint() {
		return newSystemEndpoint()
			.path(p -> p
				.staticItem(SystemRootDescriptor.GET_CATALOG.operation(URL_NAME_NAMING_CONVENTION))
				.paramItem(CatalogsHeaderDescriptor.NAME.to(operationPathParameterBuilderTransformer)))
			.method(HttpMethod.GET)
			.description(SystemRootDescriptor.GET_CATALOG.description())
			.successResponse(typeRefTo(CatalogDescriptor.THIS.name()))
			.handler(GetCatalogHandler::new)
			.build();
	}

	@Nonnull
	public OpenApiSystemEndpoint buildListCatalogsEndpoint() {
		return newSystemEndpoint()
			.path(p -> p
				.staticItem(SystemRootDescriptor.LIST_CATALOGS.operation(URL_NAME_NAMING_CONVENTION)))
			.method(HttpMethod.GET)
			.description(SystemRootDescriptor.LIST_CATALOGS.description())
			.successResponse(nonNull(arrayOf(typeRefTo(CatalogDescriptor.THIS.name()))))
			.handler(ListCatalogsHandler::new)
			.build();
	}

	@Nonnull
	public OpenApiSystemEndpoint buildCreateCatalogEndpoint() {
		return newSystemEndpoint()
			.path(p -> p
				.staticItem(SystemRootDescriptor.CREATE_CATALOG.operation(URL_NAME_NAMING_CONVENTION)))
			.method(HttpMethod.POST)
			.description(SystemRootDescriptor.CREATE_CATALOG.description())
			.requestBody(typeRefTo(CreateCatalogRequestDescriptor.THIS.name()))
			.successResponse(nonNull(typeRefTo(CatalogDescriptor.THIS.name())))
			.handler(CreateCatalogHandler::new)
			.build();
	}

	@Nonnull
	public OpenApiSystemEndpoint buildUpdateCatalogEndpoint() {
		return newSystemEndpoint()
			.path(p -> p
				.staticItem(SystemRootDescriptor.UPDATE_CATALOG.operation(URL_NAME_NAMING_CONVENTION))
				.paramItem(CatalogsHeaderDescriptor.NAME.to(operationPathParameterBuilderTransformer)))
			.method(HttpMethod.PATCH)
			.description(SystemRootDescriptor.UPDATE_CATALOG.description())
			.requestBody(typeRefTo(UpdateCatalogRequestDescriptor.THIS.name()))
			.successResponse(nonNull(typeRefTo(CatalogDescriptor.THIS.name())))
			.handler(UpdateCatalogHandler::new)
			.build();
	}

	@Nonnull
	public OpenApiSystemEndpoint buildDeleteCatalogEndpoint() {
		return newSystemEndpoint()
			.path(p -> p
				.staticItem(SystemRootDescriptor.DELETE_CATALOG.operation(URL_NAME_NAMING_CONVENTION))
				.paramItem(CatalogsHeaderDescriptor.NAME.to(operationPathParameterBuilderTransformer)))
			.method(HttpMethod.DELETE)
			.description(SystemRootDescriptor.DELETE_CATALOG.description())
			.handler(DeleteCatalogHandler::new)
			.build();
	}

//
//	@Nonnull
//	public OpenApiCollectionEndpoint buildUpdateEntitySchemaEndpoint(@Nonnull CatalogSchemaContract catalogSchema,
//	                                                                         @Nonnull EntitySchemaContract entitySchema) {
//
//		return newCollectionEndpoint(catalogSchema, entitySchema)
//			.path(p -> p
//				.staticItem(CatalogSchemaApiRootDescriptor.UPDATE_ENTITY_SCHEMA.operation(URL_NAME_NAMING_CONVENTION)))
//			.method(HttpMethod.PUT)
//			.description(CatalogSchemaApiRootDescriptor.UPDATE_ENTITY_SCHEMA.description(entitySchema.getName()))
//			.deprecationNotice(entitySchema.getDeprecationNotice())
//			.requestBody(typeRefTo(UpdateEntitySchemaRequestDescriptor.THIS.name()))
//			.successResponse(nonNull(typeRefTo(EntitySchemaDescriptor.THIS_SPECIFIC.name(entitySchema))))
//			.handler(UpdateEntitySchemaHandler::new)
//			.build();
//	}
//
//	@Nonnull
//	public OpenApiCollectionEndpoint buildDeleteEntitySchemaEndpoint(@Nonnull CatalogSchemaContract catalogSchema,
//	                                                                 @Nonnull EntitySchemaContract entitySchema) {
//
//		return newCollectionEndpoint(catalogSchema, entitySchema)
//			.path(p -> p
//				.staticItem(CatalogSchemaApiRootDescriptor.DELETE_ENTITY_SCHEMA.operation(URL_NAME_NAMING_CONVENTION)))
//			.method(HttpMethod.DELETE)
//			.description(CatalogSchemaApiRootDescriptor.DELETE_ENTITY_SCHEMA.description(entitySchema.getName()))
//			.deprecationNotice(entitySchema.getDeprecationNotice())
//			.handler(DeleteEntitySchemaHandler::new)
//			.build();
//	}
//
//	@Nonnull
//	public OpenApiCatalogEndpoint buildGetCatalogSchemaEndpoint(@Nonnull CatalogSchemaContract catalogSchema) {
//		return newCatalogEndpoint(catalogSchema)
//			.path(p -> p
//				.staticItem(CatalogSchemaApiRootDescriptor.GET_CATALOG_SCHEMA.operation(URL_NAME_NAMING_CONVENTION)))
//			.method(HttpMethod.GET)
//			.description(CatalogSchemaApiRootDescriptor.GET_CATALOG_SCHEMA.description())
//			.successResponse(nonNull(typeRefTo(CatalogSchemaDescriptor.THIS.name())))
//			.handler(GetCatalogSchemaHandler::new)
//			.build();
//	}
//
//	@Nonnull
//	public OpenApiCatalogEndpoint buildUpdateCatalogSchemaEndpoint(@Nonnull CatalogSchemaContract catalogSchema) {
//		return newCatalogEndpoint(catalogSchema)
//			.path(p -> p
//				.staticItem(CatalogSchemaApiRootDescriptor.UPDATE_CATALOG_SCHEMA.operation(URL_NAME_NAMING_CONVENTION)))
//			.method(HttpMethod.PUT)
//			.description(CatalogSchemaApiRootDescriptor.UPDATE_CATALOG_SCHEMA.description())
//			.requestBody(typeRefTo(UpdateCatalogSchemaRequestDescriptor.THIS.name()))
//			.successResponse(nonNull(typeRefTo(CatalogSchemaDescriptor.THIS.name())))
//			.handler(UpdateCatalogSchemaHandler::new)
//			.build();
//	}
}
