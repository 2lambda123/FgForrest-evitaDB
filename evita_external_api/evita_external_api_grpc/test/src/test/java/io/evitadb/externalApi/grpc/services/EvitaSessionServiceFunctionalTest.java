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

package io.evitadb.externalApi.grpc.services;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import com.google.protobuf.StringValue;
import io.evitadb.api.EvitaSessionContract;
import io.evitadb.api.SessionTraits;
import io.evitadb.api.SessionTraits.SessionFlags;
import io.evitadb.api.query.Query;
import io.evitadb.api.query.QueryConstraints;
import io.evitadb.api.query.filter.AttributeSpecialValue;
import io.evitadb.api.query.order.OrderDirection;
import io.evitadb.api.query.require.FacetStatisticsDepth;
import io.evitadb.api.query.require.QueryPriceMode;
import io.evitadb.api.query.visitor.PrettyPrintingVisitor;
import io.evitadb.api.query.visitor.PrettyPrintingVisitor.StringWithParameters;
import io.evitadb.api.requestResponse.EvitaResponse;
import io.evitadb.api.requestResponse.data.AssociatedDataContract.AssociatedDataValue;
import io.evitadb.api.requestResponse.data.EntityContract;
import io.evitadb.api.requestResponse.data.PriceContract;
import io.evitadb.api.requestResponse.data.SealedEntity;
import io.evitadb.api.requestResponse.data.structure.BinaryEntity;
import io.evitadb.api.requestResponse.data.structure.EntityReference;
import io.evitadb.api.requestResponse.extraResult.AttributeHistogram;
import io.evitadb.api.requestResponse.extraResult.FacetSummary;
import io.evitadb.api.requestResponse.extraResult.HierarchyParents;
import io.evitadb.api.requestResponse.extraResult.HierarchyStatistics;
import io.evitadb.api.requestResponse.extraResult.PriceHistogram;
import io.evitadb.core.Evita;
import io.evitadb.dataType.BigDecimalNumberRange;
import io.evitadb.dataType.ComplexDataObject;
import io.evitadb.externalApi.EvitaSystemDataProvider;
import io.evitadb.externalApi.configuration.ApiOptions;
import io.evitadb.externalApi.configuration.CertificateSettings;
import io.evitadb.externalApi.grpc.configuration.GrpcConfig;
import io.evitadb.externalApi.grpc.dataType.ComplexDataObjectConverter;
import io.evitadb.externalApi.grpc.dataType.EvitaDataTypesConverter;
import io.evitadb.externalApi.grpc.generated.*;
import io.evitadb.externalApi.grpc.interceptor.ClientSessionInterceptor;
import io.evitadb.externalApi.grpc.interceptor.ClientSessionInterceptor.SessionIdHolder;
import io.evitadb.externalApi.grpc.query.QueryConverter;
import io.evitadb.externalApi.grpc.testUtils.GrpcAssertions;
import io.evitadb.externalApi.grpc.testUtils.SessionInitializer;
import io.evitadb.externalApi.grpc.testUtils.TestChannelCreator;
import io.evitadb.externalApi.grpc.testUtils.TestDataProvider;
import io.evitadb.externalApi.grpc.utils.GrpcServer;
import io.evitadb.externalApi.grpc.utils.QueryUtil;
import io.evitadb.test.Entities;
import io.evitadb.test.annotation.DataSet;
import io.evitadb.test.annotation.UseDataSet;
import io.evitadb.test.extension.DbInstanceParameterResolver;
import io.evitadb.utils.CollectionUtils;
import io.grpc.ManagedChannel;
import io.grpc.Server;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.opentest4j.AssertionFailedError;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static io.evitadb.api.query.QueryConstraints.collection;
import static io.evitadb.api.query.QueryConstraints.entityFetch;
import static io.evitadb.api.query.QueryConstraints.require;
import static io.evitadb.externalApi.grpc.query.QueryConverter.convertQueryParam;
import static io.evitadb.externalApi.grpc.testUtils.GrpcAssertions.*;
import static io.evitadb.externalApi.grpc.testUtils.TestDataProvider.*;
import static io.evitadb.test.TestConstants.FUNCTIONAL_TEST;
import static io.evitadb.test.TestConstants.TEST_CATALOG;
import static io.evitadb.test.generator.DataGenerator.*;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings({"ResultOfMethodCallIgnored", "UnusedParameters"})
@DisplayName("EvitaSessionService gRPC functional test")
@Tag(FUNCTIONAL_TEST)
@ExtendWith(DbInstanceParameterResolver.class)
@Slf4j
class EvitaSessionServiceFunctionalTest {
	private static final String THOUSAND_PRODUCTS = "ThousandProducts";
	private static Server server;
	private static ManagedChannel channel;

	@AfterAll
	static void tearDown() {
		if (!server.isTerminated()) {
			server.shutdown();
		}
	}

	@AfterEach
	void afterEach() {
		SessionIdHolder.reset();
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should return default instance of entity when trying to get a non-existent entity")
	void shouldThrowWhenAskingForNonExistingEntity(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		final int primaryKey = -1;
		final String entityType = Entities.PRODUCT;

		final AtomicReference<GrpcEntityResponse> response = new AtomicReference<>();

		assertDoesNotThrow(() -> response.set(evitaSessionBlockingStub.getEntity(GrpcEntityRequest.newBuilder()
			.setPrimaryKey(primaryKey)
			.setEntityType(entityType)
			.build())));

		assertEquals(GrpcSealedEntity.getDefaultInstance(), response.get().getEntity());
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should return an existing entity specified by its primary key and entity type")
	void shouldReturnExistingEntitySpecifiedByPrimaryKeyAndEntityType(Evita evita, List<SealedEntity> entities) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		//noinspection ConstantConditions
		final int primaryKey = entities.stream()
			.filter(entity -> entity.getType().equals(Entities.PRODUCT))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Suitable entity not found!"))
			.getPrimaryKey();
		final String entityType = Entities.PRODUCT;

		final AtomicReference<GrpcEntityResponse> response = new AtomicReference<>();

		final Executable executable = () ->
			response.set(evitaSessionBlockingStub.getEntity(GrpcEntityRequest.newBuilder()
				.setPrimaryKey(primaryKey)
				.setEntityType(entityType)
				.build()
			));

		assertDoesNotThrow(executable);

		assertEquals(primaryKey, response.get().getEntity().getPrimaryKey());
		assertEquals(entityType, response.get().getEntity().getEntityType());
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should return enriched existing entity specified by its primary key and entity type and require query")
	void shouldReturnExistingEnrichedEntitySpecifiedByPrimaryKeyAndEntityTypeAndEntityContentRequires(Evita evita, List<SealedEntity> entities) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		//noinspection ConstantConditions
		final int primaryKey = entities.stream()
			.filter(entity -> entity.getType().equals(Entities.PRODUCT) && !entity.getPrices().isEmpty())
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Suitable entity not found!"))
			.getPrimaryKey();
		final String entityType = Entities.PRODUCT;

		final String stringEntityContentRequires = "priceContent()";

		final AtomicReference<GrpcEntityResponse> response = new AtomicReference<>();

		final Executable executable = () ->
			response.set(evitaSessionBlockingStub.getEntity(GrpcEntityRequest.newBuilder()
				.setPrimaryKey(primaryKey)
				.setEntityType(entityType)
				.setRequire(stringEntityContentRequires)
				.build()
			));

		assertDoesNotThrow(executable);

		assertEquals(primaryKey, response.get().getEntity().getPrimaryKey());
		assertEquals(entityType, response.get().getEntity().getEntityType());
		assertNotNull(response.get().getEntity().getPriceForSale());
		assertNotEquals(0, response.get().getEntity().getPricesCount());
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should return data chunk of entity references")
	void shouldReturnDataChunkOfEntityReferences(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		final List<QueryParam> params = new ArrayList<>(8);
		params.add(convertQueryParam(Entities.PRODUCT));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(2));
		params.add(convertQueryParam(3));
		params.add(convertQueryParam(4));
		params.add(convertQueryParam(5));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(Integer.MAX_VALUE));

		final String stringQuery = """
					query(
						collection(?),
						filterBy(
							entityPrimaryKeyInSet(?, ?, ?, ?, ?)
						),
						require(
							page(?, ?)
						)
					)
			""";

		final AtomicReference<GrpcQueryResponse> response = new AtomicReference<>();

		final Executable executable = () ->
			response.set(evitaSessionBlockingStub.query(GrpcQueryRequest.newBuilder()
				.setQuery(stringQuery)
				.addAllPositionalQueryParams(params)
				.build()
			));

		assertDoesNotThrow(executable);

		final Query query = QueryUtil.parseQuery(stringQuery, params, null);

		assertNotNull(query);

		final EvitaResponse<EntityReference> entityResponse = evita.createReadOnlySession(TEST_CATALOG).query(query, EntityReference.class);

		assertEquals(entityResponse.getRecordData().size(), response.get().getRecordPage().getEntityReferencesCount());

		for (int i = 0; i < entityResponse.getRecordData().size(); i++) {
			final GrpcEntityReference grpcEntityReference = response.get().getRecordPage().getEntityReferencesList().get(i);
			final EntityReference entityReference = entityResponse.getRecordData().get(i);
			assertEquals(entityReference.getType(), grpcEntityReference.getEntityType());
			assertEquals(entityReference.getPrimaryKey(), grpcEntityReference.getPrimaryKey());
		}
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should return list of entity references")
	void shouldReturnListOfEntityReferences(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		final List<QueryParam> params = new ArrayList<>(8);
		params.add(convertQueryParam(Entities.PRODUCT));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(2));
		params.add(convertQueryParam(3));
		params.add(convertQueryParam(4));
		params.add(convertQueryParam(5));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(Integer.MAX_VALUE));

		final String stringQuery = """
			query(
				collection(?),
				filterBy(
					entityPrimaryKeyInSet(?, ?, ?, ?, ?)
				),
				require(
					page(?, ?)
				)
			)
			""";

		final AtomicReference<GrpcQueryListResponse> response = new AtomicReference<>();

		final Executable executable = () ->
			response.set(evitaSessionBlockingStub.queryList(GrpcQueryRequest.newBuilder()
				.setQuery(stringQuery)
				.addAllPositionalQueryParams(params)
				.build()
			));

		assertDoesNotThrow(executable);

		final Query query = QueryUtil.parseQuery(stringQuery, params, null);

		assertNotNull(query);

		final List<EntityReference> entityResponse = evita.createReadOnlySession(TEST_CATALOG).queryListOfEntityReferences(query);

		for (int i = 0; i < entityResponse.size(); i++) {
			final GrpcEntityReference grpcEntityReference = response.get().getEntityReferencesList().get(i);
			final EntityReference entityReference = entityResponse.get(i);
			assertEquals(entityReference.getType(), grpcEntityReference.getEntityType());
			assertEquals(entityReference.getPrimaryKey(), grpcEntityReference.getPrimaryKey());
		}
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should throw when queryOne returns more than one entity with queryOne")
	void shouldThrowWhenQueryOneReturnsMoreThanOneEntity(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		final List<QueryParam> params = new ArrayList<>(8);
		params.add(convertQueryParam(Entities.PRODUCT));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(2));
		params.add(convertQueryParam(3));
		params.add(convertQueryParam(4));
		params.add(convertQueryParam(5));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(Integer.MAX_VALUE));

		final String stringQuery = """
			query(
				collection(?),
				filterBy(
					entityPrimaryKeyInSet(?, ?, ?, ?, ?)
				),
				require(
					page(?, ?)
				)
			)
			""";

		final Executable executable = () ->
			evitaSessionBlockingStub.queryOne(GrpcQueryRequest.newBuilder()
				.setQuery(stringQuery)
				.addAllPositionalQueryParams(params)
				.build()
			);

		assertThrows(StatusRuntimeException.class, executable);
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should return one entity when using queryOne and only one matches")
	void shouldReturnOneEntityWhenUsingQueryOneAndProperlySpecified(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		final List<QueryParam> params = new ArrayList<>(4);
		params.add(convertQueryParam(Entities.PRODUCT));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(20));

		final String stringQuery = """
			query(
				collection(?),
				filterBy(
					entityPrimaryKeyInSet(?)
				),
				require(
					page(?, ?)
				)
			)
			""";

		final AtomicReference<GrpcQueryOneResponse> response = new AtomicReference<>();

		final Executable executable = () ->
			response.set(evitaSessionBlockingStub.queryOne(GrpcQueryRequest.newBuilder()
				.setQuery(stringQuery)
				.addAllPositionalQueryParams(params)
				.build())
			);

		assertDoesNotThrow(executable);

		final Query query = QueryUtil.parseQuery(stringQuery, params, null);

		assertNotNull(query);

		final EntityReference entity = evita.createReadOnlySession(TEST_CATALOG)
			.queryOneEntityReference(query)
			.orElseThrow();

		final GrpcEntityReference grpcEntityReference = response.get().getEntityReference();
		assertEquals(entity.getType(), grpcEntityReference.getEntityType());
		assertEquals(entity.getPrimaryKey(), grpcEntityReference.getPrimaryKey());
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should throw when trying to get hierarchy statistics of non-hierarchical entity collection")
	void shouldFailWhenTryingToGetHierarchyStatisticsOnNotHierarchicalCollection(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		final List<QueryParam> params = new ArrayList<>(8);
		params.add(convertQueryParam(Entities.PRODUCT));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(2));
		params.add(convertQueryParam(3));
		params.add(convertQueryParam(4));
		params.add(convertQueryParam(5));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(Integer.MAX_VALUE));

		final String stringQuery = """
			query(
				collection(?),
				filterBy(
					entityPrimaryKeyInSet(?, ?, ?, ?, ?)
				),
				require(
					page(?, ?),
					hierarchyStatisticsOfSelf()
				)
			)
			""";

		assertThrows(StatusRuntimeException.class, () -> evitaSessionBlockingStub.query(GrpcQueryRequest.newBuilder()
			.setQuery(stringQuery)
			.addAllPositionalQueryParams(params)
			.build()
		));
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should throw when trying to get parents of non-hierarchical entity collection")
	void shouldFailWhenTryingToGetParentsOfNotHierarchicalCollection(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		final List<QueryParam> params = new ArrayList<>(8);
		params.add(convertQueryParam(Entities.PRODUCT));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(2));
		params.add(convertQueryParam(3));
		params.add(convertQueryParam(4));
		params.add(convertQueryParam(5));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(Integer.MAX_VALUE));

		final String stringQuery = """
			query(
				collection(?),
				filterBy(
					entityPrimaryKeyInSet(?, ?, ?, ?, ?)
				),
				require(
					page(?, ?),
					hierarchyParentsOfSelf()
				)
			)
			""";

		assertThrows(StatusRuntimeException.class,
			() -> evitaSessionBlockingStub.query(GrpcQueryRequest.newBuilder()
				.setQuery(stringQuery)
				.addAllPositionalQueryParams(params)
				.build()
			));
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should return data chunk of enriched entities with one language specified")
	void shouldReturnDataChunkOfEnrichedEntitiesWithOneLanguageSpecified(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		final List<QueryParam> params = new ArrayList<>(17);
		params.add(convertQueryParam(Entities.PRODUCT));
		params.add(convertQueryParam(ATTRIBUTE_NAME));
		params.add(convertQueryParam("a"));
		params.add(convertQueryParam(CZECH_LOCALE));
		params.add(convertQueryParam(CURRENCY_CZK));
		params.add(convertQueryParam(PRICE_LIST_VIP));
		params.add(convertQueryParam(PRICE_LIST_BASIC));
		params.add(convertQueryParam(Entities.STORE));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(2));
		params.add(convertQueryParam(Entities.BRAND));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(2));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(20));
		params.add(convertQueryParam(Entities.CATEGORY));
		params.add(convertQueryParam(Entities.STORE));

		final String stringQuery = """
			query(
				collection(?),
				filterBy(
					and(
						attributeContains(?, ?),
						entityLocaleEquals(?),
						priceInCurrency(?),
						priceInPriceLists(?, ?),
						userFilter(
							facetInSet(?, ?, ?),
							facetInSet(?, ?, ?)
						)
					)
				),
				require(
					page(?, ?),
					entityFetch(
						attributeContent(),
						priceContent(),
						referenceContent(?, ?),
						associatedDataContent()
					)
				)
			)
			""";

		final AtomicReference<GrpcQueryResponse> response = new AtomicReference<>();

		final Executable executable = () ->
			response.set(evitaSessionBlockingStub.query(GrpcQueryRequest.newBuilder()
				.setQuery(stringQuery)
				.addAllPositionalQueryParams(params)
				.build()
			));

		assertDoesNotThrow(executable);

		final Query query = QueryUtil.parseQuery(stringQuery, params, null);

		assertNotNull(query);

		assertNotEquals(0, response.get().getRecordPage().getSealedEntitiesCount());
		assertEquals(0, response.get().getRecordPage().getEntityReferencesCount());

		final EvitaResponse<SealedEntity> entityResponse = evita.createReadOnlySession(TEST_CATALOG).query(query, SealedEntity.class);

		for (int i = 0; i < entityResponse.getRecordData().size(); i++) {
			final SealedEntity entity = entityResponse.getRecordData().get(i);
			final GrpcSealedEntity grpcSealedEntity = response.get().getRecordPage().getSealedEntitiesList().get(i);
			assertEntity(entity, grpcSealedEntity);
		}
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should return data chunk of enriched entities with rich references")
	void shouldReturnDataChunkOfEnrichedEntitiesWithRichReferences(Evita evita, List<SealedEntity> entities) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		final Integer primaryKey = entities.stream()
			.filter(it -> !it.getReferences(Entities.PARAMETER).isEmpty())
			.map(EntityContract::getPrimaryKey)
			.findAny()
			.orElseThrow();

		final List<QueryParam> params = new ArrayList<>(17);
		params.add(convertQueryParam(Entities.PRODUCT));
		params.add(convertQueryParam(primaryKey));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(20));
		params.add(convertQueryParam(Entities.CATEGORY));
		params.add(convertQueryParam(ATTRIBUTE_CODE));

		final String stringQuery = """
			query(
				collection(?),
				filterBy(
					entityPrimaryKeyInSet(?)
				),
				require(
					page(?, ?),
					entityFetch(
						priceContent(),
						referenceContent(?, entityFetch(attributeContent(?)), entityGroupFetch())
					)
				)
			)
			""";

		final AtomicReference<GrpcQueryResponse> response = new AtomicReference<>();

		final Executable executable = () ->
			response.set(evitaSessionBlockingStub.query(GrpcQueryRequest.newBuilder()
				.setQuery(stringQuery)
				.addAllPositionalQueryParams(params)
				.build()
			));

		assertDoesNotThrow(executable);

		final Query query = QueryUtil.parseQuery(stringQuery, params, null);

		assertNotNull(query);

		assertNotEquals(0, response.get().getRecordPage().getSealedEntitiesCount());
		assertEquals(0, response.get().getRecordPage().getEntityReferencesCount());

		final EvitaResponse<SealedEntity> entityResponse = evita.createReadOnlySession(TEST_CATALOG).query(query, SealedEntity.class);

		for (int i = 0; i < entityResponse.getRecordData().size(); i++) {
			final SealedEntity entity = entityResponse.getRecordData().get(i);
			final GrpcSealedEntity grpcSealedEntity = response.get().getRecordPage().getSealedEntitiesList().get(i);
			assertEntity(entity, grpcSealedEntity);
		}
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should return data chunk of enriched entities when using multiple filter conditions, using query enums and ordering by attributes with passed mix of named and positional parameters")
	void shouldReturnDataChunkOfEnrichedEntitiesWhenFilteringByMultipleConditionsAndOrderingByAttributesMixedParams(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		final List<QueryParam> positionalParams = new ArrayList<>(19);
		positionalParams.add(convertQueryParam(Entities.PRODUCT));
		positionalParams.add(convertQueryParam(ATTRIBUTE_SIZE));
		positionalParams.add(convertQueryParam(ATTRIBUTE_NAME));
		positionalParams.add(convertQueryParam(CZECH_LOCALE));
		positionalParams.add(convertQueryParam(CURRENCY_CZK));
		positionalParams.add(convertQueryParam(PRICE_LIST_VIP));
		positionalParams.add(convertQueryParam(PRICE_LIST_BASIC));
		positionalParams.add(convertQueryParam(Entities.STORE));
		positionalParams.add(convertQueryParam(1));
		positionalParams.add(convertQueryParam(2));
		positionalParams.add(convertQueryParam(Entities.BRAND));
		positionalParams.add(convertQueryParam(1));
		positionalParams.add(convertQueryParam(2));
		positionalParams.add(convertQueryParam(ATTRIBUTE_NAME));
		positionalParams.add(convertQueryParam(OrderDirection.DESC));
		positionalParams.add(convertQueryParam(Entities.CATEGORY));
		positionalParams.add(convertQueryParam(Entities.STORE));
		positionalParams.add(convertQueryParam(FacetStatisticsDepth.COUNTS));
		positionalParams.add(convertQueryParam(QueryPriceMode.WITH_TAX));

		final Map<String, QueryParam> namedParams = CollectionUtils.createHashMap(4);
		namedParams.put("sizeIs", convertQueryParam(AttributeSpecialValue.NOT_NULL));
		namedParams.put("nameContains", convertQueryParam("a"));
		namedParams.put("page", convertQueryParam(1));
		namedParams.put("pageSize", convertQueryParam(20));

		final String stringQuery = """
			query(
				collection(?),
				filterBy(
					and(
						attributeIs(?, @sizeIs),
						attributeContains(?, @nameContains),
						entityLocaleEquals(?),
						priceInCurrency(?),
						priceInPriceLists(?, ?),
						userFilter(
							facetInSet(?, ?, ?),
							facetInSet(?, ?, ?)
						)
					)
				),
				orderBy(
					attributeNatural(?, ?),
					priceNatural()
				),
				require(
					page(@page, @pageSize),
					entityFetch(
						attributeContent(),
						priceContent(),
						referenceContent(?, ?),
						associatedDataContent()
					),
					facetSummary(?),
					priceType(?),
					queryTelemetry()
				)
			)
			""";

		final AtomicReference<GrpcQueryResponse> response = new AtomicReference<>();

		final Executable executable = () ->
			response.set(evitaSessionBlockingStub.query(GrpcQueryRequest.newBuilder()
				.setQuery(stringQuery)
				.addAllPositionalQueryParams(positionalParams)
				.putAllNamedQueryParams(namedParams)
				.build()
			));

		assertDoesNotThrow(executable);

		final Query query = QueryUtil.parseQuery(stringQuery, positionalParams, namedParams, null);

		assertNotNull(query);

		assertNotEquals(0, response.get().getRecordPage().getSealedEntitiesCount());
		assertEquals(0, response.get().getRecordPage().getEntityReferencesCount());

		final EvitaResponse<SealedEntity> entityResponse = evita.createReadOnlySession(TEST_CATALOG).query(query, SealedEntity.class);

		for (int i = 0; i < entityResponse.getRecordData().size(); i++) {
			final SealedEntity entity = entityResponse.getRecordData().get(i);
			final GrpcSealedEntity grpcSealedEntity = response.get().getRecordPage().getSealedEntitiesList().get(i);
			assertEntity(entity, grpcSealedEntity);
		}

		assertFacetSummary(Objects.requireNonNull(
				entityResponse.getExtraResult(FacetSummary.class)),
			response.get().getExtraResults().getFacetGroupStatisticsList()
		);

		final GrpcQueryTelemetry queryTelemetry = response.get().getExtraResults().getQueryTelemetry();
		assertNotEquals(GrpcQueryTelemetry.getDefaultInstance(), queryTelemetry);
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should return data chunk of enriched entities when using multiple filter conditions, using query enums and ordering by attributes with passed positional parameters")
	void shouldReturnDataChunkOfEnrichedEntitiesWhenFilteringByMultipleConditionsAndOrderingByAttributesPositional(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		final List<QueryParam> positionalParams = new ArrayList<>(19);
		positionalParams.add(convertQueryParam(Entities.PRODUCT));
		positionalParams.add(convertQueryParam(ATTRIBUTE_SIZE));
		positionalParams.add(convertQueryParam(AttributeSpecialValue.NOT_NULL));
		positionalParams.add(convertQueryParam(ATTRIBUTE_NAME));
		positionalParams.add(convertQueryParam("a"));
		positionalParams.add(convertQueryParam(CZECH_LOCALE));
		positionalParams.add(convertQueryParam(CURRENCY_CZK));
		positionalParams.add(convertQueryParam(PRICE_LIST_VIP));
		positionalParams.add(convertQueryParam(PRICE_LIST_BASIC));
		positionalParams.add(convertQueryParam(Entities.STORE));
		positionalParams.add(convertQueryParam(1));
		positionalParams.add(convertQueryParam(2));
		positionalParams.add(convertQueryParam(Entities.BRAND));
		positionalParams.add(convertQueryParam(1));
		positionalParams.add(convertQueryParam(2));
		positionalParams.add(convertQueryParam(ATTRIBUTE_NAME));
		positionalParams.add(convertQueryParam(OrderDirection.DESC));
		positionalParams.add(convertQueryParam(1));
		positionalParams.add(convertQueryParam(20));
		positionalParams.add(convertQueryParam(Entities.CATEGORY));
		positionalParams.add(convertQueryParam(Entities.STORE));
		positionalParams.add(convertQueryParam(FacetStatisticsDepth.COUNTS));
		positionalParams.add(convertQueryParam(QueryPriceMode.WITH_TAX));

		final String stringQuery = """
			query(
				collection(?),
				filterBy(
					and(
						attributeIs(?, ?),
						attributeContains(?, ?),
						entityLocaleEquals(?),
						priceInCurrency(?),
						priceInPriceLists(?, ?),
						userFilter(
							facetInSet(?, ?, ?),
							facetInSet(?, ?, ?)
						)
					)
				),
				orderBy(
					attributeNatural(?, ?),
					priceNatural()
				),
				require(
					page(?, ?),
					entityFetch(
						attributeContent(),
						priceContent(),
						referenceContent(?, ?)
					),
					associatedDataContent(),
					facetSummary(?),
					priceType(?)
				)
			)
			""";

		final AtomicReference<GrpcQueryResponse> response = new AtomicReference<>();

		final Executable executable = () ->
			response.set(evitaSessionBlockingStub.query(GrpcQueryRequest.newBuilder()
				.setQuery(stringQuery)
				.addAllPositionalQueryParams(positionalParams)
				.build()
			));

		assertDoesNotThrow(executable);

		final Query query = QueryUtil.parseQuery(stringQuery, positionalParams, null);

		assertNotNull(query);

		assertNotEquals(0, response.get().getRecordPage().getSealedEntitiesCount());
		assertEquals(0, response.get().getRecordPage().getEntityReferencesCount());

		final EvitaResponse<SealedEntity> entityResponse = evita.createReadOnlySession(TEST_CATALOG).query(query, SealedEntity.class);

		for (int i = 0; i < entityResponse.getRecordData().size(); i++) {
			final SealedEntity entity = entityResponse.getRecordData().get(i);
			final GrpcSealedEntity grpcSealedEntity = response.get().getRecordPage().getSealedEntitiesList().get(i);
			assertEntity(entity, grpcSealedEntity);
		}

		assertFacetSummary(Objects.requireNonNull(
				entityResponse.getExtraResult(FacetSummary.class)),
			response.get().getExtraResults().getFacetGroupStatisticsList()
		);
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should return data chunk of enriched entities when using multiple filter conditions, using query enums and ordering by attributes with passed named parameters")
	void shouldReturnDataChunkOfEnrichedEntitiesWhenFilteringByMultipleConditionsAndOrderingByAttributesNamed(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		final Map<String, QueryParam> namedParams = CollectionUtils.createHashMap(19);
		namedParams.put("entitiesProduct", convertQueryParam(Entities.PRODUCT));
		namedParams.put("attributeSizeName", convertQueryParam(ATTRIBUTE_SIZE));
		namedParams.put("attributeSizeValue", convertQueryParam(AttributeSpecialValue.NOT_NULL));
		namedParams.put("attributeNameName", convertQueryParam(ATTRIBUTE_NAME));
		namedParams.put("attributeNameValue", convertQueryParam("a"));
		namedParams.put("locale", convertQueryParam(CZECH_LOCALE));
		namedParams.put("currency", convertQueryParam(CURRENCY_CZK));
		namedParams.put("priceListVip", convertQueryParam(PRICE_LIST_VIP));
		namedParams.put("priceListBasic", convertQueryParam(PRICE_LIST_BASIC));
		namedParams.put("entitiesStore", convertQueryParam(Entities.STORE));
		namedParams.put("facetId1", convertQueryParam(1));
		namedParams.put("facetId2", convertQueryParam(2));
		namedParams.put("entitiesBrand", convertQueryParam(Entities.BRAND));
		namedParams.put("attributeOrderDirection", convertQueryParam(OrderDirection.DESC));
		namedParams.put("page", convertQueryParam(1));
		namedParams.put("pageSize", convertQueryParam(20));
		namedParams.put("entitiesCategory", convertQueryParam(Entities.CATEGORY));
		namedParams.put("facetStatisticsDepth", convertQueryParam(FacetStatisticsDepth.COUNTS));
		namedParams.put("queryPriceMode", convertQueryParam(QueryPriceMode.WITH_TAX));

		final String stringQuery = """
			query(
				collection(@entitiesProduct),
				filterBy(
					and(
						attributeIs(@attributeSizeName, @attributeSizeValue),
						attributeContains(@attributeNameName, @attributeNameValue),
						entityLocaleEquals(@locale),
						priceInCurrency(@currency),
						priceInPriceLists(@priceListVip, @priceListBasic),
						userFilter(
							facetInSet(@entitiesStore, @facetId1, @facetId2),
							facetInSet(@entitiesBrand, @facetId1, @facetId2)
						)
					)
				),
				orderBy(
					attributeNatural(@attributeNameName, @attributeOrderDirection),
					priceNatural()
				),
				require(
					page(@page, @pageSize),
					entityFetch(
						attributeContent(),
						priceContent(),
						referenceContent(@entitiesCategory, @entitiesStore),
						associatedDataContent()
					),
					facetSummary(@facetStatisticsDepth),
					priceType(@queryPriceMode)
				)
			)
			""";

		final AtomicReference<GrpcQueryResponse> response = new AtomicReference<>();

		final Executable executable = () ->
			response.set(evitaSessionBlockingStub.query(GrpcQueryRequest.newBuilder()
				.setQuery(stringQuery)
				.putAllNamedQueryParams(namedParams)
				.build()
			));

		assertDoesNotThrow(executable);

		final Query query = QueryUtil.parseQuery(stringQuery, namedParams, null);

		assertNotNull(query);

		assertNotEquals(0, response.get().getRecordPage().getSealedEntitiesCount());
		assertEquals(0, response.get().getRecordPage().getEntityReferencesCount());

		final EvitaResponse<SealedEntity> entityResponse = evita.createReadOnlySession(TEST_CATALOG).query(query, SealedEntity.class);

		for (int i = 0; i < entityResponse.getRecordData().size(); i++) {
			final SealedEntity entity = entityResponse.getRecordData().get(i);
			final GrpcSealedEntity grpcSealedEntity = response.get().getRecordPage().getSealedEntitiesList().get(i);
			assertEntity(entity, grpcSealedEntity);
		}

		assertFacetSummary(Objects.requireNonNull(
				entityResponse.getExtraResult(FacetSummary.class)),
			response.get().getExtraResults().getFacetGroupStatisticsList()
		);
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should return data chunk of enriched entities with more than one language specified")
	void shouldReturnDataChunkOfEnrichedEntitiesWithMoreThanOneLanguageSpecified(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		final List<QueryParam> params = new ArrayList<>(17);
		params.add(convertQueryParam(Entities.PRODUCT));
		params.add(convertQueryParam(ATTRIBUTE_NAME));
		params.add(convertQueryParam("a"));
		params.add(convertQueryParam(CZECH_LOCALE));
		params.add(convertQueryParam(CURRENCY_CZK));
		params.add(convertQueryParam(PRICE_LIST_VIP));
		params.add(convertQueryParam(PRICE_LIST_BASIC));
		params.add(convertQueryParam(Entities.STORE));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(2));
		params.add(convertQueryParam(Entities.BRAND));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(2));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(20));
		params.add(convertQueryParam(Locale.ENGLISH));
		params.add(convertQueryParam(CZECH_LOCALE));

		final String stringQuery = """
			query(
				collection(?),
				filterBy(
					and(
						attributeContains(?, ?),
						entityLocaleEquals(?),
						priceInCurrency(?),
						priceInPriceLists(?, ?),
						userFilter(
							facetInSet(?, ?, ?),
							facetInSet(?, ?, ?)
						)
					)
				),
				require(
					page(?, ?),
					entityFetch(
						attributeContent(),
						priceContent(),
						referenceContent(),
						associatedDataContent(),
						dataInLocales(?, ?)
					)
				)
			)
			""";

		final AtomicReference<GrpcQueryResponse> response = new AtomicReference<>();

		final Executable executable = () ->
			response.set(evitaSessionBlockingStub.query(GrpcQueryRequest.newBuilder()
				.setQuery(stringQuery)
				.addAllPositionalQueryParams(params)
				.build()
			));

		assertDoesNotThrow(executable);

		final Query query = QueryUtil.parseQuery(stringQuery, params, null);

		assertNotNull(query);

		assertNotEquals(0, response.get().getRecordPage().getSealedEntitiesCount());
		assertEquals(0, response.get().getRecordPage().getEntityReferencesCount());

		final EvitaResponse<SealedEntity> entityResponse = evita.createReadOnlySession(TEST_CATALOG).query(query, SealedEntity.class);

		for (int i = 0; i < entityResponse.getRecordData().size(); i++) {
			final SealedEntity entity = entityResponse.getRecordData().get(i);
			final GrpcSealedEntity enrichedEntity = response.get().getRecordPage().getSealedEntitiesList().get(i);
			assertEntity(entity, enrichedEntity);
		}
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should return extra result of parents consisting of products referencing to its categories")
	void shouldReturnParentsOfProductsReferencingToItsCategories(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		final List<QueryParam> params = new ArrayList<>(23);
		params.add(convertQueryParam(Entities.PRODUCT));
		params.add(convertQueryParam(ATTRIBUTE_NAME));
		params.add(convertQueryParam("a"));
		params.add(convertQueryParam(CZECH_LOCALE));
		params.add(convertQueryParam(CURRENCY_CZK));
		params.add(convertQueryParam(PRICE_LIST_VIP));
		params.add(convertQueryParam(PRICE_LIST_BASIC));
		params.add(convertQueryParam(Entities.STORE));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(2));
		params.add(convertQueryParam(Entities.BRAND));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(2));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(20));
		params.add(convertQueryParam(Locale.ENGLISH));
		params.add(convertQueryParam(CZECH_LOCALE));
		params.add(convertQueryParam(20));
		params.add(convertQueryParam(ATTRIBUTE_QUANTITY));
		params.add(convertQueryParam(ATTRIBUTE_PRIORITY));
		params.add(convertQueryParam(20));
		params.add(convertQueryParam(FacetStatisticsDepth.IMPACT));
		params.add(convertQueryParam(Entities.CATEGORY));
		params.add(convertQueryParam(Entities.CATEGORY));

		final String stringQuery = """
			query(
				collection(?),
				filterBy(
					and(
						attributeContains(?, ?),
						entityLocaleEquals(?),
						priceInCurrency(?),
						priceInPriceLists(?, ?),
						userFilter(
							facetInSet(?, ?, ?),
							facetInSet(?, ?, ?)
						)
					)
				),
				require(
					page(?, ?),
					entityFetch(
						attributeContent(),
						priceContent(),
						referenceContent(),
						associatedDataContent(),
						dataInLocales(?, ?)
					),
					attributeHistogram(?, ?, ?),
					priceHistogram(?),
					facetSummary(?),
					hierarchyParentsOfReference(?, entityFetch(referenceContent())),
					hierarchyStatisticsOfReference(?, entityFetch(attributeContent()))
				)
			)
			""";

		final AtomicReference<GrpcQueryResponse> response = new AtomicReference<>();

		final Executable executable = () ->
			response.set(evitaSessionBlockingStub.query(GrpcQueryRequest.newBuilder()
				.setQuery(stringQuery)
				.addAllPositionalQueryParams(params)
				.build()
			));

		assertDoesNotThrow(executable);

		final Query query = QueryUtil.parseQuery(stringQuery, params, null);

		assertNotNull(query);

		assertNotEquals(0, response.get().getRecordPage().getSealedEntitiesCount());
		assertEquals(0, response.get().getRecordPage().getEntityReferencesCount());

		final EvitaResponse<SealedEntity> entityResponse = evita.createReadOnlySession(TEST_CATALOG).query(query, SealedEntity.class);

		for (int i = 0; i < entityResponse.getRecordData().size(); i++) {
			final SealedEntity entity = entityResponse.getRecordData().get(i);
			final GrpcSealedEntity enrichedEntity = response.get().getRecordPage().getSealedEntitiesList().get(i);
			assertEntity(entity, enrichedEntity);
		}

		final HierarchyParents hierarchyParentsOfSelf = entityResponse.getExtraResult(HierarchyParents.class);

		if (hierarchyParentsOfSelf != null) {
			assertParents(
				hierarchyParentsOfSelf.ofType(Entities.CATEGORY),
				response.get().getExtraResults().getHierarchyParentsOrThrow(Entities.CATEGORY)
			);
		}
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should return all requested extra results")
	void shouldReturnAllRequestedExtraResults(Evita evita, List<SealedEntity> entities) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		final List<QueryParam> params = new ArrayList<>(21);
		params.add(convertQueryParam(Entities.PRODUCT));
		params.add(convertQueryParam(CURRENCY_USD));
		params.add(convertQueryParam(PRICE_LIST_VIP));
		params.add(convertQueryParam(PRICE_LIST_BASIC));
		params.add(convertQueryParam(PRICE_LIST_B2B));
		params.add(convertQueryParam(PRICE_LIST_REFERENCE));
		params.add(convertQueryParam(PRICE_LIST_SELLOUT));
		params.add(convertQueryParam(Entities.PARAMETER));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(20));
		params.add(convertQueryParam(Locale.ENGLISH));
		params.add(convertQueryParam(CZECH_LOCALE));
		params.add(convertQueryParam(20));
		params.add(convertQueryParam(ATTRIBUTE_QUANTITY));
		params.add(convertQueryParam(ATTRIBUTE_PRIORITY));
		params.add(convertQueryParam(20));
		params.add(convertQueryParam(FacetStatisticsDepth.IMPACT));

		final String stringQuery = """
			query(
				collection(?),
				filterBy(
					and(
						priceInCurrency(?),
						priceInPriceLists(?, ?, ?, ?, ?),
						userFilter(
							facetInSet(?, ?)
						)
					)
				),
				require(
					page(?, ?),
					entityFetch(
						dataInLocales(?, ?)
					),
					attributeHistogram(?, ?, ?),
					priceHistogram(?),
					facetSummary(?)
				)
			)
			""";

		final AtomicReference<GrpcQueryResponse> response = new AtomicReference<>();

		final Executable executable = () ->
			response.set(evitaSessionBlockingStub.query(GrpcQueryRequest.newBuilder()
				.setQuery(stringQuery)
				.addAllPositionalQueryParams(params)
				.build()
			));

		assertDoesNotThrow(executable);

		final Query query = QueryUtil.parseQuery(stringQuery, params, null);

		assertNotNull(query);

		assertNotEquals(0, response.get().getRecordPage().getSealedEntitiesCount());
		assertEquals(0, response.get().getRecordPage().getEntityReferencesCount());

		final EvitaResponse<SealedEntity> entityResponse = evita.createReadOnlySession(TEST_CATALOG).query(query, SealedEntity.class);

		assertAttributeHistograms(Objects.requireNonNull(entityResponse.getExtraResult(AttributeHistogram.class)), response.get().getExtraResults().getAttributeHistogramMap());
		assertPriceHistogram(Objects.requireNonNull(entityResponse.getExtraResult(PriceHistogram.class)), response.get().getExtraResults().getPriceHistogram());
		assertFacetSummary(Objects.requireNonNull(entityResponse.getExtraResult(FacetSummary.class)), response.get().getExtraResults().getFacetGroupStatisticsList());
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should return rich facet summary")
	void shouldReturnRichFacetSummary(Evita evita, List<SealedEntity> entities) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		final List<QueryParam> params = new ArrayList<>(21);
		params.add(convertQueryParam(Entities.PRODUCT));
		params.add(convertQueryParam(CURRENCY_USD));
		params.add(convertQueryParam(PRICE_LIST_VIP));
		params.add(convertQueryParam(PRICE_LIST_BASIC));
		params.add(convertQueryParam(PRICE_LIST_B2B));
		params.add(convertQueryParam(PRICE_LIST_REFERENCE));
		params.add(convertQueryParam(PRICE_LIST_SELLOUT));
		params.add(convertQueryParam(Entities.PARAMETER));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(20));
		params.add(convertQueryParam(FacetStatisticsDepth.IMPACT));
		params.add(convertQueryParam("code"));

		final String stringQuery = """
			query(
				collection(?),
				filterBy(
					and(
						priceInCurrency(?),
						priceInPriceLists(?, ?, ?, ?, ?),
						userFilter(
							facetInSet(?, ?)
						)
					)
				),
				require(
					page(?, ?),
					facetSummary(?,entityFetch(priceContent(), attributeContent(?)),entityGroupFetch())
				)
			)
			""";

		final AtomicReference<GrpcQueryResponse> response = new AtomicReference<>();

		final Executable executable = () ->
			response.set(evitaSessionBlockingStub.query(GrpcQueryRequest.newBuilder()
				.setQuery(stringQuery)
				.addAllPositionalQueryParams(params)
				.build()
			));

		assertDoesNotThrow(executable);

		final Query query = QueryUtil.parseQuery(stringQuery, params, null);

		assertNotNull(query);

		assertNotEquals(0, response.get().getRecordPage().getEntityReferencesCount());

		final EvitaResponse<EntityReference> entityResponse = evita.createReadOnlySession(TEST_CATALOG).query(query, EntityReference.class);

		assertFacetSummary(
			Objects.requireNonNull(entityResponse.getExtraResult(FacetSummary.class)),
			response.get().getExtraResults().getFacetGroupStatisticsList()
		);
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should return data chunk of entity references with applied within hierarchy filter with computed hierarchy statistics and parents trees consisting of entity primary keys")
	void shouldReturnDataChunkOfEnrichedEntitiesWithHierarchyStatisticsAndParentsOfIntegers(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		final List<QueryParam> params = new ArrayList<>(5);
		params.add(convertQueryParam(Entities.CATEGORY));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(20));

		final String stringQuery = """
			query(
				collection(?),
				filterBy(
					hierarchyWithinSelf(?)
				),
				require(
					page(?, ?),
					hierarchyStatisticsOfSelf(),
					hierarchyParentsOfSelf()
				)
			)
			""";

		final AtomicReference<GrpcQueryResponse> response = new AtomicReference<>();

		final Executable executable = () ->
			response.set(evitaSessionBlockingStub.query(GrpcQueryRequest.newBuilder()
				.setQuery(stringQuery)
				.addAllPositionalQueryParams(params)
				.build()
			));

		assertDoesNotThrow(executable);

		final Query query = QueryUtil.parseQuery(stringQuery, params, null);

		assertNotNull(query);

		final EvitaResponse<EntityReference> referenceResponse = evita.createReadOnlySession(TEST_CATALOG).query(query, EntityReference.class);

		final HierarchyStatistics hierarchyStatisticsOfSelf = referenceResponse.getExtraResult(HierarchyStatistics.class);
		final HierarchyParents hierarchyParentsOfSelf = referenceResponse.getExtraResult(HierarchyParents.class);

		if (hierarchyStatisticsOfSelf != null) {
			GrpcAssertions.assertStatistics(
				hierarchyStatisticsOfSelf.getSelfStatistics(),
				response.get().getExtraResults().getSelfHierarchyStatistics()
			);
		}

		if (hierarchyParentsOfSelf != null) {
			assertParents(
				hierarchyParentsOfSelf.ofSelf(),
				response.get().getExtraResults().getSelfHierarchyParents()
			);
		}

		for (int i = 0; i < referenceResponse.getRecordData().size(); i++) {
			final EntityReference reference = referenceResponse.getRecordData().get(i);
			final GrpcEntityReference entityReference = response.get().getRecordPage().getEntityReferencesList().get(i);
			assertEquals(reference.getType(), entityReference.getEntityType());
			assertEquals(reference.getPrimaryKey(), entityReference.getPrimaryKey());
		}
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should return data chunk of enriched entities with computed hierarchy statistics and parents trees consisting of enriched entities")
	void shouldReturnDataChunkOfEnrichedEntitiesWithHierarchyStatisticsAndParentsOfEntities(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		final List<QueryParam> params = new ArrayList<>(3);
		params.add(convertQueryParam(Entities.CATEGORY));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(Integer.MAX_VALUE));

		final String stringQuery = """
			query(
				collection(?),
				require(
					page(?, ?),
					entityFetch(),
					hierarchyStatisticsOfSelf(entityFetch(attributeContent())),
					hierarchyParentsOfSelf(entityFetch(attributeContent()))
				)
			)
			""";

		final AtomicReference<GrpcQueryResponse> response = new AtomicReference<>();

		final Executable executable = () ->
			response.set(evitaSessionBlockingStub.query(GrpcQueryRequest.newBuilder()
				.setQuery(stringQuery)
				.addAllPositionalQueryParams(params)
				.build()
			));

		assertDoesNotThrow(executable);

		final Query query = QueryUtil.parseQuery(stringQuery, params, null);

		assertNotNull(query);

		final EvitaResponse<SealedEntity> entityResponse = evita.createReadOnlySession(TEST_CATALOG).query(query, SealedEntity.class);

		assertStatistics(
			Objects.requireNonNull(entityResponse.getExtraResult(HierarchyStatistics.class)).getSelfStatistics(),
			response.get().getExtraResults().getSelfHierarchyStatistics()
		);

		assertParents(
			entityResponse.getExtraResult(HierarchyParents.class).ofSelf(),
			response.get().getExtraResults().getSelfHierarchyParents()
		);

		for (int i = 0; i < entityResponse.getRecordData().size(); i++) {
			final SealedEntity entity = entityResponse.getRecordData().get(i);
			final GrpcSealedEntity grpcSealedEntity = response.get().getRecordPage().getSealedEntitiesList().get(i);
			assertEquals(entity.getType(), grpcSealedEntity.getEntityType());
			assertEquals(entity.getPrimaryKey(), grpcSealedEntity.getPrimaryKey());
		}
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should throw when trying to get size of non-existing collection")
	void shouldThrowWhenTryingToGetSizeOfNonExistingCollection(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		assertThrows(StatusRuntimeException.class, () -> evitaSessionBlockingStub.getEntityCollectionSize(
			GrpcEntityCollectionSizeRequest.newBuilder()
				.setEntityType("non-existing-collection")
				.build()
		));
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should return entity count in collection when passing name of an existing one")
	void shouldReturnWhenTryingToGetSizeOfExistingCollection(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		final AtomicReference<GrpcEntityCollectionSizeResponse> response = new AtomicReference<>();

		final Executable executable = () ->
			response.set(evitaSessionBlockingStub.getEntityCollectionSize(
				GrpcEntityCollectionSizeRequest.newBuilder()
					.setEntityType(Entities.PRODUCT)
					.build()
			));

		assertDoesNotThrow(executable);

		final int size = response.get().getSize();

		assertTrue(size > 0);
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should throw when trying to delete a collection without read/write session")
	void shouldNotDeleteCollectionWithoutReadWriteSession(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		assertThrows(StatusRuntimeException.class, () -> evitaSessionBlockingStub.deleteCollection(
			GrpcDeleteCollectionRequest.newBuilder()
				.setEntityType("non-existing-collection")
				.build()
		));
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should not throw when trying to delete non-existing collection")
	void shouldNotDeleteNonExistingCollection(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_WRITE);

		final AtomicReference<GrpcDeleteCollectionResponse> response = new AtomicReference<>();

		final Executable executable = () ->
			response.set(evitaSessionBlockingStub.deleteCollection(
				GrpcDeleteCollectionRequest.newBuilder()
					.setEntityType("non-existing-collection")
					.build()
			));

		assertDoesNotThrow(executable);
		assertFalse(response.get().getDeleted());
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should delete collection when passing name of an existing collection")
	void shouldDeleteExistingCollection(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_WRITE);

		final AtomicReference<GrpcDeleteCollectionResponse> response = new AtomicReference<>();

		assertDoesNotThrow(() -> evita.createReadWriteSession(TEST_CATALOG).getEntityCollectionSize(Entities.BRAND));

		final Executable executable = () ->
			response.set(evitaSessionBlockingStub.deleteCollection(
				GrpcDeleteCollectionRequest.newBuilder()
					.setEntityType(Entities.BRAND)
					.build()
			));

		assertDoesNotThrow(executable);
		assertTrue(response.get().getDeleted());
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should return catalog schema")
	void shouldReturnCatalogSchema(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		final AtomicReference<GrpcCatalogSchemaResponse> response = new AtomicReference<>();

		final Executable executable = () ->
			response.set(evitaSessionBlockingStub.getCatalogSchema(Empty.newBuilder().build()));

		assertDoesNotThrow(executable);

		final GrpcCatalogSchema catalogSchema = response.get().getCatalogSchema();
		assertNotNull(catalogSchema);
		assertEquals(TEST_CATALOG, catalogSchema.getName());
		assertEquals(3, catalogSchema.getAttributesCount());

		final GrpcGlobalAttributeSchema enabledAttribute = catalogSchema.getAttributesMap().get(ATTRIBUTE_ENABLED);
		assertEquals(GrpcEvitaDataType.BOOLEAN, enabledAttribute.getType());
		assertTrue(enabledAttribute.getDefaultValue().isInitialized());
		assertTrue(enabledAttribute.getDefaultValue().getBooleanValue());
		assertEquals("Sets visibility of the entity.", enabledAttribute.getDescription().getValue());

		final GrpcGlobalAttributeSchema inheritanceAttribute = catalogSchema.getAttributesMap().get(ATTRIBUTE_INHERITANCE);
		assertEquals(GrpcEvitaDataType.BOOLEAN_ARRAY, inheritanceAttribute.getType());
		assertTrue(inheritanceAttribute.getDefaultValue().isInitialized());
		final GrpcBooleanArray booleanArrayValue = inheritanceAttribute.getDefaultValue().getBooleanArrayValue();
		final List<Boolean> booleanList = booleanArrayValue.getValueList();
		assertEquals(3, booleanList.size());
		assertTrue(booleanList.get(0));
		assertFalse(booleanList.get(1));
		assertTrue(booleanList.get(2));

		final GrpcGlobalAttributeSchema opticsValue = catalogSchema.getAttributesMap().get(ATTRIBUTE_OPTICS);
		assertEquals(GrpcEvitaDataType.BYTE_ARRAY, opticsValue.getType());
		assertTrue(opticsValue.getDefaultValue().isInitialized());
		final GrpcIntegerArray byteArrayValue = opticsValue.getDefaultValue().getIntegerArrayValue();
		final List<Integer> byteList = byteArrayValue.getValueList();
		assertEquals(3, byteList.size());
		assertEquals(1, byteList.get(0));
		assertEquals(5, byteList.get(1));
		assertEquals(12, byteList.get(2));
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should return catalog schema")
	void shouldReturnEntitySchema(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		final AtomicReference<GrpcEntitySchemaResponse> response = new AtomicReference<>();

		final Executable executable = () ->
			response.set(
				evitaSessionBlockingStub.getEntitySchema(
					GrpcEntitySchemaRequest.newBuilder()
						.setEntityType(Entities.PRODUCT)
						.build()
				)
			);

		assertDoesNotThrow(executable);

		final GrpcEntitySchema entitySchema = response.get().getEntitySchema();
		assertNotNull(entitySchema);
		assertEquals(Entities.PRODUCT, entitySchema.getName());
		assertEquals(12, entitySchema.getAttributesCount());
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should return set of stored entity types")
	void shouldReturnSetOfEntityTypes(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		final AtomicReference<GrpcEntityTypesResponse> response = new AtomicReference<>();

		final EvitaSessionContract session = evita.createReadOnlySession(TEST_CATALOG);

		final Executable executable = () ->
			response.set(evitaSessionBlockingStub.getAllEntityTypes(Empty.newBuilder().build()));

		assertDoesNotThrow(executable);

		final Set<String> expectedEntityTypes = session.getAllEntityTypes();
		final List<String> actualEntityTypes = response.get().getEntityTypesList();

		assertEquals(expectedEntityTypes.size(), actualEntityTypes.size());
		for (String entityType : session.getAllEntityTypes()) {
			assertTrue(actualEntityTypes.contains(entityType));
		}
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should not be able switch catalog state to ALIVE and close current session since testing instance of evita's catalog is by default in ALIVE state ")
	void shouldNotBeAbleToSwitchCatalogStateToAliveAndClose(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		final Executable executable = () -> evitaSessionBlockingStub.goLiveAndClose(Empty.newBuilder().build());

		assertThrows(StatusRuntimeException.class, executable);
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should be able to close currently active session upon which this test operates")
	void shouldBeAbleToCloseCurrentlyActiveSessionUponWhichThisTestOperates(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		final Executable executable = () -> evitaSessionBlockingStub.close(Empty.newBuilder().build());

		assertDoesNotThrow(executable);

		final Executable executableFail = () -> evitaSessionBlockingStub.getEntity(GrpcEntityRequest.newBuilder().setEntityType(Entities.PRODUCT).setPrimaryKey(1).build());

		assertThrows(StatusRuntimeException.class, executableFail);
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should not be able to mutate entity with read-only session")
	void shouldNotBeAbleMutateEntityWithReadOnlySession(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_ONLY);

		final Executable executable = () -> evitaSessionBlockingStub.upsertEntity(
			GrpcUpsertEntityRequest.newBuilder()
				.setEntityMutation(
					GrpcEntityMutation.newBuilder()
						.setEntityUpsertMutation(
							GrpcEntityUpsertMutation.newBuilder()
								.setEntityType(Entities.PRODUCT)
								.setEntityPrimaryKey(Int32Value.of(1))
								.addMutations(
									GrpcLocalMutation.newBuilder()
										.setUpsertAttributeMutation(
											GrpcUpsertAttributeMutation.newBuilder()
												.setAttributeName(ATTRIBUTE_PRIORITY)
												.setAttributeLocale(EvitaDataTypesConverter.toGrpcLocale(CZECH_LOCALE))
												.setAttributeValue(EvitaDataTypesConverter.toGrpcEvitaValue(Long.MAX_VALUE))
												.build()
										)
										.build()
								)
								.build()
						)
						.build()
				)
				.build()
		);

		assertThrows(StatusRuntimeException.class, executable);
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should be able to mutate entity attributes with read-write session with one mutation specified")
	void shouldBeAbleMutateEntityAttributesWithReadWriteSession(Evita evita, List<SealedEntity> entities) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_WRITE);

		final SealedEntity selectedEntity = entities.stream()
			.filter(it -> it.getAttribute(ATTRIBUTE_QUANTITY) != null && it.getAttribute(ATTRIBUTE_PRIORITY) != null)
			.findFirst()
			.orElseThrow();

		final GrpcEntityRequest entityRequest = GrpcEntityRequest.newBuilder()
			.setEntityType(Entities.PRODUCT)
			.setPrimaryKey(selectedEntity.getPrimaryKey())
			.setRequire("attributeContent()")
			.build();

		final GrpcEntityResponse originalEntity = evitaSessionBlockingStub.getEntity(entityRequest);

		final long priorityValue = 10;
		final BigDecimal quantityValueDelta = BigDecimal.valueOf(-20.34);
		final String name = "new-attribute-name";

		final AtomicReference<GrpcUpsertEntityResponse> upsertEntityResponse = new AtomicReference<>();

		final Executable executable = () -> {
			final StringWithParameters stringWithParameters = PrettyPrintingVisitor.toStringWithParameterExtraction(QueryConstraints.entityFetchAll().getRequirements());
			upsertEntityResponse.set(evitaSessionBlockingStub.upsertEntity(
					GrpcUpsertEntityRequest.newBuilder()
						.setRequire(stringWithParameters.query())
						.addAllPositionalQueryParams(
							stringWithParameters.parameters()
								.stream()
								.map(QueryConverter::convertQueryParam)
								.toList()
						)
						.setEntityMutation(
							GrpcEntityMutation.newBuilder()
								.setEntityUpsertMutation(
									GrpcEntityUpsertMutation.newBuilder()
										.setEntityType(originalEntity.getEntity().getEntityType())
										.setEntityPrimaryKey(Int32Value.of(originalEntity.getEntity().getPrimaryKey()))
										.addMutations(
											GrpcLocalMutation.newBuilder()
												.setApplyDeltaAttributeMutation(
													GrpcApplyDeltaAttributeMutation.newBuilder()
														.setAttributeName(ATTRIBUTE_QUANTITY)
														.setBigDecimalRequiredRangeAfterApplication(
															EvitaDataTypesConverter.toGrpcBigDecimalNumberRange(
																BigDecimalNumberRange.between(
																	BigDecimal.valueOf(0),
																	BigDecimal.valueOf(1000)
																)
															)
														)
														.setBigDecimalDelta(EvitaDataTypesConverter.toGrpcBigDecimal(quantityValueDelta))
														.build()
												)
												.build()
										)
										.addMutations(
											GrpcLocalMutation.newBuilder()
												.setUpsertAttributeMutation(
													GrpcUpsertAttributeMutation.newBuilder()
														.setAttributeName(ATTRIBUTE_PRIORITY)
														.setAttributeValue(EvitaDataTypesConverter.toGrpcEvitaValue(priorityValue))
												)
												.build()
										)
										.addMutations(
											GrpcLocalMutation.newBuilder()
												.setUpsertAttributeMutation(
													GrpcUpsertAttributeMutation.newBuilder()
														.setAttributeName(ATTRIBUTE_NAME)
														.setAttributeLocale(EvitaDataTypesConverter.toGrpcLocale(CZECH_LOCALE))
														.setAttributeValue(EvitaDataTypesConverter.toGrpcEvitaValue(name))
														.build()
												)
												.build()
										)
										.addMutations(
											GrpcLocalMutation.newBuilder()
												.setRemoveAttributeMutation(
													GrpcRemoveAttributeMutation.newBuilder()
														.setAttributeName(ATTRIBUTE_SIZE)
														.build()
												)
												.build()
										)
										.build()
								)
								.build()
						)
						.build()
				)
			);
		};

		assertDoesNotThrow(executable);

		assertNull(originalEntity.getEntity().getLocalizedAttributesMap().get(ATTRIBUTE_NAME));
		final GrpcEvitaValue attributeName = upsertEntityResponse.get().getEntity().getLocalizedAttributesMap().get(CZECH_LOCALE.toLanguageTag()).getAttributesMap().get(ATTRIBUTE_NAME);
		assertNotNull(attributeName);
		assertEquals(name, attributeName.getStringValue());

		assertNotNull(originalEntity.getEntity().getGlobalAttributesMap().get(ATTRIBUTE_SIZE));
		assertNull(upsertEntityResponse.get().getEntity().getGlobalAttributesMap().get(ATTRIBUTE_SIZE));

		assertNotEquals(
			originalEntity.getEntity().getGlobalAttributesMap().get(ATTRIBUTE_PRIORITY),
			upsertEntityResponse.get().getEntity().getGlobalAttributesMap().get(ATTRIBUTE_PRIORITY)
		);
		assertNotEquals(
			originalEntity.getEntity().getGlobalAttributesMap().get(ATTRIBUTE_QUANTITY),
			upsertEntityResponse.get().getEntity().getGlobalAttributesMap().get(ATTRIBUTE_QUANTITY)
		);
		assertEquals(upsertEntityResponse.get().getEntity().getGlobalAttributesMap().get(ATTRIBUTE_PRIORITY).getLongValue(), priorityValue);
		final BigDecimal originalQuantityValue = EvitaDataTypesConverter.toBigDecimal(originalEntity.getEntity().getGlobalAttributesMap().get(ATTRIBUTE_QUANTITY).getBigDecimalValue());
		final BigDecimal updatedQuantityValue = EvitaDataTypesConverter.toBigDecimal(upsertEntityResponse.get().getEntity().getGlobalAttributesMap().get(ATTRIBUTE_QUANTITY).getBigDecimalValue());
		assertEquals(originalQuantityValue.add(quantityValueDelta), updatedQuantityValue);
		//updated attributes should have increased version
		assertEquals(
			Int32Value.of(originalEntity.getEntity().getGlobalAttributesMap().get(ATTRIBUTE_QUANTITY).getVersion().getValue() + 1),
			upsertEntityResponse.get().getEntity().getGlobalAttributesMap().get(ATTRIBUTE_QUANTITY).getVersion()
		);
		assertEquals(
			Int32Value.of(originalEntity.getEntity().getGlobalAttributesMap().get(ATTRIBUTE_PRIORITY).getVersion().getValue() + 1),
			upsertEntityResponse.get().getEntity().getGlobalAttributesMap().get(ATTRIBUTE_PRIORITY).getVersion()
		);
		//new attribute should have version 1
		assertEquals(
			Int32Value.of(1),
			upsertEntityResponse.get().getEntity().getGlobalAttributesMap().get(ATTRIBUTE_MANUFACTURED).getVersion()
		);
		//not changed attributes should have same version
		assertEquals(originalEntity.getEntity().getGlobalAttributesMap().get(ATTRIBUTE_EAN).getVersion(), upsertEntityResponse.get().getEntity().getGlobalAttributesMap().get(ATTRIBUTE_EAN).getVersion());
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should be able to mutate entity associated data with read-write session")
	void shouldBeAbleMutateEntityAssociatedDataWithReadWriteSession(Evita evita, List<SealedEntity> entities) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_WRITE);

		final SealedEntity existingEntity = entities.stream()
			.filter(e -> e.getType().equals(Entities.PRODUCT) &&
				e.getAssociatedDataValues().stream().anyMatch(a -> a.getKey().getAssociatedDataName().equals(ASSOCIATED_DATA_REFERENCED_FILES)) &&
				e.getAssociatedDataValues().stream().noneMatch(a -> a.getKey().getAssociatedDataName().equals(ASSOCIATED_DATA_LABELS))
			)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Suitable entity not found!"));

		final GrpcEntityRequest entityRequest = GrpcEntityRequest.newBuilder()
			.setEntityType(Entities.PRODUCT)
			.setPrimaryKey(Objects.requireNonNull(existingEntity.getPrimaryKey()))
			.setRequire("associatedDataContent(), dataInLocales()")
			.build();

		final GrpcEntityResponse originalEntity = evitaSessionBlockingStub.getEntity(entityRequest);

		final String jsonValue = "{\"root\":{\"name\":\"test\", \"value\":\"100.453\"}}";
		final String defaultJsonValue = ComplexDataObjectConverter.convertComplexDataObjectToJson(
			(ComplexDataObject) existingEntity.getAssociatedDataValue(ASSOCIATED_DATA_REFERENCED_FILES)
				.map(AssociatedDataValue::getValue)
				.orElseThrow()
		).toString();
		final String czLocaleString = CZECH_LOCALE.toLanguageTag();

		final AtomicReference<GrpcUpsertEntityResponse> upsertEntityResponse = new AtomicReference<>();

		final Executable executable = () -> {
			final StringWithParameters stringWithParameters = PrettyPrintingVisitor.toStringWithParameterExtraction(QueryConstraints.entityFetchAll().getRequirements());
			upsertEntityResponse.set(
				evitaSessionBlockingStub.upsertEntity(
					GrpcUpsertEntityRequest.newBuilder()
						.setRequire(stringWithParameters.query())
						.addAllPositionalQueryParams(
							stringWithParameters.parameters()
								.stream()
								.map(QueryConverter::convertQueryParam)
								.toList()
						)
						.setEntityMutation(
							GrpcEntityMutation.newBuilder()
								.setEntityUpsertMutation(
									GrpcEntityUpsertMutation.newBuilder()
										.setEntityType(originalEntity.getEntity().getEntityType())
										.setEntityPrimaryKey(Int32Value.of(originalEntity.getEntity().getPrimaryKey()))
										.addMutations(
											GrpcLocalMutation.newBuilder()
												.setUpsertAssociatedDataMutation(
													GrpcUpsertAssociatedDataMutation.newBuilder()
														.setAssociatedDataName(ASSOCIATED_DATA_LABELS)
														.setAssociatedDataLocale(EvitaDataTypesConverter.toGrpcLocale(CZECH_LOCALE))
														.setAssociatedDataValue(
															GrpcEvitaAssociatedDataValue.newBuilder()
																.setJsonValue(jsonValue)
																.build())
														.build()
												)
												.build()
										)
										.addMutations(
											GrpcLocalMutation.newBuilder()
												.setRemoveAssociatedDataMutation(
													GrpcRemoveAssociatedDataMutation.newBuilder()
														.setAssociatedDataName(ASSOCIATED_DATA_REFERENCED_FILES)
														.build()
												)
												.build()
										)
										.build()
								)
								.build()
						)
						.build()
				)
			);
		};

		assertDoesNotThrow(executable);

		assertNull(originalEntity.getEntity().getLocalizedAssociatedDataMap().get(czLocaleString));
		assertNotNull(upsertEntityResponse.get().getEntity().getLocalizedAssociatedDataMap().get(czLocaleString));
		assertNotNull(upsertEntityResponse.get().getEntity().getLocalizedAssociatedDataMap().get(czLocaleString));
		assertNotNull(originalEntity.getEntity().getGlobalAssociatedDataMap().get(ASSOCIATED_DATA_REFERENCED_FILES));
		assertNull(upsertEntityResponse.get().getEntity().getGlobalAssociatedDataMap().get(ASSOCIATED_DATA_REFERENCED_FILES));
		assertEquals(defaultJsonValue, originalEntity.getEntity().getGlobalAssociatedDataMap().get(ASSOCIATED_DATA_REFERENCED_FILES).getJsonValue());
		assertNotEquals(jsonValue, upsertEntityResponse.get().getEntity().getLocalizedAssociatedDataMap().get(czLocaleString).getAssociatedDataMap().get(ASSOCIATED_DATA_LABELS).getJsonValue());
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should be able to mutate entity prices with read-write session")
	void shouldBeAbleMutateEntityPricesWithReadWriteSession(Evita evita, List<SealedEntity> entities) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_WRITE);

		final SealedEntity existingEntity = entities.stream()
			.filter(e -> e.getType().equals(Entities.PRODUCT) &&
				e.getPrices().size() > 2 && e.getPrices().size() < 6
			)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Suitable entity not found!"));

		//noinspection ConstantConditions
		final GrpcEntityRequest entityRequest = GrpcEntityRequest.newBuilder()
			.setEntityType(Entities.PRODUCT)
			.setPrimaryKey(existingEntity.getPrimaryKey())
			.setRequire("priceContent()")
			.build();

		final List<String> existingPriceLists = existingEntity.getPrices().stream().map(PriceContract::getPriceList).toList();

		final String insertNewPriceIntoNonExistingPriceList = Arrays.stream(PRICE_LIST_NAMES)
			.filter(p -> !existingPriceLists.contains(p)).findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Suitable price list not found!"));
		final String insertNewPriceIntoExistingPriceList = existingPriceLists.stream()
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Suitable price list not found!"));
		final GrpcCurrency insertCurrency = EvitaDataTypesConverter.toGrpcCurrency(CURRENCY_CZK);
		final int insertPriceId = 1000000;

		final PriceContract toRemove = existingEntity.getPrices().stream().findFirst().orElseThrow(() -> new IllegalArgumentException("Suitable price not found!"));
		final String removedPriceList = toRemove.getPriceList();
		final GrpcCurrency removedCurrency = EvitaDataTypesConverter.toGrpcCurrency(toRemove.getCurrency());
		final int removedPriceId = toRemove.getPriceId();

		final PriceContract toUpdate = existingEntity.getPrices().stream().skip(existingEntity.getPrices().size() - 1).findFirst().orElseThrow(() -> new IllegalArgumentException("Suitable price not found!"));
		final String updatedPriceList = toUpdate.getPriceList();
		final GrpcCurrency updatedCurrency = EvitaDataTypesConverter.toGrpcCurrency(toUpdate.getCurrency());
		final int updatedPriceId = toUpdate.getPriceId();

		final GrpcEntityResponse originalEntity = evitaSessionBlockingStub.getEntity(entityRequest);

		final AtomicReference<GrpcUpsertEntityResponse> upsertEntityResponse = new AtomicReference<>();

		final Executable executable = () -> {
			final StringWithParameters stringWithParameters = PrettyPrintingVisitor.toStringWithParameterExtraction(QueryConstraints.entityFetchAll().getRequirements());
			upsertEntityResponse.set(evitaSessionBlockingStub.upsertEntity(
					GrpcUpsertEntityRequest.newBuilder()
						.setRequire(stringWithParameters.query())
						.addAllPositionalQueryParams(
							stringWithParameters.parameters()
								.stream()
								.map(QueryConverter::convertQueryParam)
								.toList()
						)
						.setEntityMutation(
							GrpcEntityMutation.newBuilder()
								.setEntityUpsertMutation(
									GrpcEntityUpsertMutation.newBuilder()
										.setEntityType(originalEntity.getEntity().getEntityType())
										.setEntityPrimaryKey(Int32Value.of(originalEntity.getEntity().getPrimaryKey()))
										.addMutations(
											GrpcLocalMutation.newBuilder()
												.setUpsertPriceMutation(
													GrpcUpsertPriceMutation.newBuilder()
														.setPriceId(updatedPriceId)
														.setPriceList(updatedPriceList)
														.setCurrency(updatedCurrency)
														.setPriceWithoutTax(EvitaDataTypesConverter.toGrpcBigDecimal(BigDecimal.valueOf(100)))
														.setTaxRate(EvitaDataTypesConverter.toGrpcBigDecimal(BigDecimal.valueOf(10.55)))
														.setPriceWithTax(EvitaDataTypesConverter.toGrpcBigDecimal(BigDecimal.valueOf(110.55)))
														.setSellable(true)
														.build()
												)
												.build()
										)
										.addMutations(
											GrpcLocalMutation.newBuilder()
												.setUpsertPriceMutation(
													GrpcUpsertPriceMutation.newBuilder()
														.setPriceId(insertPriceId)
														.setPriceList(insertNewPriceIntoNonExistingPriceList)
														.setCurrency(insertCurrency)
														.setPriceWithoutTax(EvitaDataTypesConverter.toGrpcBigDecimal(BigDecimal.valueOf(10)))
														.setTaxRate(EvitaDataTypesConverter.toGrpcBigDecimal(BigDecimal.valueOf(50)))
														.setPriceWithTax(EvitaDataTypesConverter.toGrpcBigDecimal(BigDecimal.valueOf(15)))
														.setSellable(false)
														.build()
												)
												.build()
										)
										.addMutations(
											GrpcLocalMutation.newBuilder()
												.setUpsertPriceMutation(
													GrpcUpsertPriceMutation.newBuilder()
														.setPriceId(insertPriceId)
														.setPriceList(insertNewPriceIntoExistingPriceList)
														.setCurrency(insertCurrency)
														.setPriceWithoutTax(EvitaDataTypesConverter.toGrpcBigDecimal(BigDecimal.valueOf(10)))
														.setTaxRate(EvitaDataTypesConverter.toGrpcBigDecimal(BigDecimal.valueOf(50)))
														.setPriceWithTax(EvitaDataTypesConverter.toGrpcBigDecimal(BigDecimal.valueOf(15)))
														.setSellable(false)
														.build()
												)
												.build()
										)
										.addMutations(
											GrpcLocalMutation.newBuilder()
												.setRemovePriceMutation(
													GrpcRemovePriceMutation.newBuilder()
														.setPriceId(removedPriceId)
														.setPriceList(removedPriceList)
														.setCurrency(removedCurrency)
														.build()
												)
												.build()
										)
										.addMutations(
											GrpcLocalMutation.newBuilder()
												.setSetPriceInnerRecordHandlingMutation(
													GrpcSetPriceInnerRecordHandlingMutation.newBuilder()
														.setPriceInnerRecordHandling(GrpcPriceInnerRecordHandling.SUM)
														.build()
												)
												.build()
										)
										.build()
								)
								.build()
						)
						.build()
				)
			);
		};

		assertDoesNotThrow(executable);


		assertNotEquals(originalEntity.getEntity().getPriceInnerRecordHandling(), upsertEntityResponse.get().getEntity().getPriceInnerRecordHandling());

		final Map<String, List<GrpcPrice>> originalEntityPricesByPriceList = originalEntity.getEntity().getPricesList().stream().collect(Collectors.groupingBy(GrpcPrice::getPriceList));
		final Map<String, List<GrpcPrice>> upsertedEntityPricesByPriceList = upsertEntityResponse.get().getEntity().getPricesList().stream().collect(Collectors.groupingBy(GrpcPrice::getPriceList));

		assertTrue(originalEntityPricesByPriceList.get(removedPriceList).stream().anyMatch(p -> p.getPriceId() == removedPriceId));
		assertFalse(upsertedEntityPricesByPriceList.get(removedPriceList).stream().anyMatch(p -> p.getPriceId() == removedPriceId));

		assertNotNull(originalEntityPricesByPriceList.get(insertNewPriceIntoExistingPriceList));
		assertTrue(upsertedEntityPricesByPriceList.get(insertNewPriceIntoExistingPriceList).stream().anyMatch(p -> p.getPriceId() == insertPriceId));

		assertFalse(originalEntityPricesByPriceList.containsKey(insertNewPriceIntoNonExistingPriceList));
		assertTrue(upsertedEntityPricesByPriceList.containsKey(insertNewPriceIntoNonExistingPriceList));

		final Optional<GrpcPrice> preUpdatePrice = originalEntityPricesByPriceList.get(updatedPriceList).stream().filter(p -> p.getPriceId() == updatedPriceId).findFirst();
		final Optional<GrpcPrice> postUpdatePrice = upsertedEntityPricesByPriceList.get(updatedPriceList).stream().filter(p -> p.getPriceId() == updatedPriceId).findFirst();

		assertTrue(preUpdatePrice.isPresent());
		assertTrue(postUpdatePrice.isPresent());

		assertNotEquals(EvitaDataTypesConverter.toBigDecimal(preUpdatePrice.get().getPriceWithoutTax()), EvitaDataTypesConverter.toBigDecimal(postUpdatePrice.get().getPriceWithoutTax()));
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should be able to mutate entity hierarchy placement with read-write session")
	void shouldBeAbleMutateEntityHierarchyPlacementWithReadWriteSession(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_WRITE);

		List<SealedEntity> originalCategoryEntities = evita.createReadOnlySession(TEST_CATALOG)
			.queryListOfSealedEntities(Query.query(
				collection(Entities.CATEGORY), require(entityFetch())
			));

		final String entityType = Entities.CATEGORY;

		final SealedEntity categoryWithoutHierarchyPlacement = originalCategoryEntities.stream().filter(e -> e.getType().equals(entityType) &&
				e.getHierarchicalPlacement().isEmpty() || e.getHierarchicalPlacement().orElseThrow().getParentPrimaryKey() == null)
			.findFirst().orElseThrow(() -> new IllegalArgumentException("Suitable category not found!"));

		final SealedEntity categoryWithHierarchyPlacement = originalCategoryEntities.stream().filter(e -> e.getType().equals(entityType) &&
				e.getHierarchicalPlacement().isPresent() && e.getHierarchicalPlacement().orElseThrow().getParentPrimaryKey() != null)
			.findFirst().orElseThrow(() -> new IllegalArgumentException("Suitable category not found!"));

		//noinspection ConstantConditions
		final GrpcEntityRequest toAddHierarchyEntityRequest = GrpcEntityRequest.newBuilder()
			.setEntityType(entityType)
			.setPrimaryKey(categoryWithoutHierarchyPlacement.getPrimaryKey())
			.build();

		//noinspection ConstantConditions
		final GrpcEntityRequest toRemoveHierarchyEntityRequest = GrpcEntityRequest.newBuilder()
			.setEntityType(entityType)
			.setPrimaryKey(categoryWithHierarchyPlacement.getPrimaryKey())
			.build();

		//noinspection ConstantConditions
		final int parentPrimaryKey = originalCategoryEntities.stream().filter(e -> !Objects.equals(e.getPrimaryKey(), categoryWithoutHierarchyPlacement.getPrimaryKey()))
			.findFirst().orElseThrow(() -> new IllegalArgumentException("Suitable category not found!")).getPrimaryKey();
		final int orderAmongSibling = 5;

		//adding hierarchy to non-hierarchical entity
		final GrpcEntityResponse originalToAddHierarchyEntity = evitaSessionBlockingStub.getEntity(toAddHierarchyEntityRequest);
		final AtomicReference<GrpcUpsertEntityResponse> upsertToAddHierarchyEntityResponse = new AtomicReference<>();

		final Executable addHierarchyExecutable = () -> {
			final StringWithParameters stringWithParameters = PrettyPrintingVisitor.toStringWithParameterExtraction(QueryConstraints.entityFetchAll().getRequirements());
			upsertToAddHierarchyEntityResponse.set(
				evitaSessionBlockingStub.upsertEntity(
					GrpcUpsertEntityRequest.newBuilder()
						.setRequire(stringWithParameters.query())
						.addAllPositionalQueryParams(
							stringWithParameters.parameters()
								.stream()
								.map(QueryConverter::convertQueryParam)
								.toList()
						)
						.setEntityMutation(
							GrpcEntityMutation.newBuilder()
								.setEntityUpsertMutation(
									GrpcEntityUpsertMutation.newBuilder()
										.setEntityType(originalToAddHierarchyEntity.getEntity().getEntityType())
										.setEntityPrimaryKey(Int32Value.of(originalToAddHierarchyEntity.getEntity().getPrimaryKey()))
										.addMutations(
											GrpcLocalMutation.newBuilder()
												.setSetHierarchicalPlacementMutation(
													GrpcSetHierarchicalPlacementMutation.newBuilder()
														.setPrimaryKey(Int32Value.of(parentPrimaryKey))
														.setOrderAmongSiblings(orderAmongSibling)
												)
												.build()
										)
										.build()
								)
								.build()
						)
						.build()
				)
			);
		};

		assertDoesNotThrow(addHierarchyExecutable);

		final GrpcHierarchicalPlacement hierarchicalPlacement = originalToAddHierarchyEntity.getEntity().getHierarchicalPlacement();
		final GrpcHierarchicalPlacement returnedHierarchicalPlacement = upsertToAddHierarchyEntityResponse.get().getEntity().getHierarchicalPlacement();
		assertTrue(
			hierarchicalPlacement.getOrderAmongSiblings() != returnedHierarchicalPlacement.getOrderAmongSiblings() ||
				hierarchicalPlacement.hasParentPrimaryKey() != returnedHierarchicalPlacement.hasParentPrimaryKey()
		);
		assertFalse(hierarchicalPlacement.hasParentPrimaryKey());
		assertTrue(returnedHierarchicalPlacement.hasParentPrimaryKey());

		//removing hierarchy from hierarchical entity
		final GrpcEntityResponse originalToRemoveHierarchyEntity = evitaSessionBlockingStub.getEntity(toRemoveHierarchyEntityRequest);
		final AtomicReference<GrpcUpsertEntityResponse> upsertToRemoveHierarchyEntityResponse = new AtomicReference<>();

		final Executable removeHierarchyExecutable = () -> upsertToRemoveHierarchyEntityResponse.set(evitaSessionBlockingStub.upsertEntity(
				GrpcUpsertEntityRequest.newBuilder()
					.setEntityMutation(
						GrpcEntityMutation.newBuilder()
							.setEntityUpsertMutation(
								GrpcEntityUpsertMutation.newBuilder()
									.setEntityType(originalToRemoveHierarchyEntity.getEntity().getEntityType())
									.setEntityPrimaryKey(Int32Value.of(originalToRemoveHierarchyEntity.getEntity().getPrimaryKey()))
									.addMutations(
										GrpcLocalMutation.newBuilder()
											.setRemoveHierarchicalPlacementMutation(
												GrpcRemoveHierarchicalPlacementMutation.newBuilder().build()
											)
											.build()
									)
									.build()
							)
							.build()
					)
					.build()
			)
		);

		assertDoesNotThrow(removeHierarchyExecutable);

		final GrpcHierarchicalPlacement hierarchicalPlacementAgain = originalToRemoveHierarchyEntity.getEntity().getHierarchicalPlacement();
		final GrpcHierarchicalPlacement returnedHierarchicalPlacementAgain = upsertToRemoveHierarchyEntityResponse.get().getEntity().getHierarchicalPlacement();
		assertTrue(
			hierarchicalPlacementAgain.getOrderAmongSiblings() != returnedHierarchicalPlacementAgain.getOrderAmongSiblings() ||
				hierarchicalPlacementAgain.hasParentPrimaryKey() != returnedHierarchicalPlacementAgain.hasParentPrimaryKey()
		);
		assertTrue(hierarchicalPlacementAgain.hasParentPrimaryKey());
		assertFalse(returnedHierarchicalPlacementAgain.hasParentPrimaryKey());
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should be able to create entity with specified references with read-write session")
	void shouldBeAbleMutateEntityReferencesWithReadWriteSession(Evita evita, List<SealedEntity> entities) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_WRITE);

		final GrpcEntityRequest newEntityRequest = GrpcEntityRequest.newBuilder()
			.setEntityType(Entities.PRODUCT)
			.setRequire("referenceContent(entityFetch(attributeContent()))")
			.build();

		final long attributeValue = 102L;
		final String attributeName = "categoryPriority";
		final GrpcCardinality cardinality = GrpcCardinality.ZERO_OR_MORE;
		final String referenceEntityType = Entities.CATEGORY;
		final int referencePrimaryKey = 7;
		final String referenceParameterEntityType = Entities.PARAMETER;
		final String referenceGroupEntityType = Entities.PARAMETER_GROUP;
		final int referenceGroupPrimaryKey = 1;

		final AtomicReference<GrpcUpsertEntityResponse> upsertEntityResponse = new AtomicReference<>();

		final Executable executableInsert = () -> {
			final StringWithParameters stringWithParameters = PrettyPrintingVisitor.toStringWithParameterExtraction(QueryConstraints.entityFetchAll().getRequirements());
			upsertEntityResponse.set(
				evitaSessionBlockingStub.upsertEntity(
					GrpcUpsertEntityRequest.newBuilder()
						.setRequire(stringWithParameters.query())
						.addAllPositionalQueryParams(
							stringWithParameters.parameters()
								.stream()
								.map(QueryConverter::convertQueryParam)
								.toList()
						)
						.setEntityMutation(
							GrpcEntityMutation.newBuilder()
								.setEntityUpsertMutation(
									GrpcEntityUpsertMutation.newBuilder()
										.setEntityType(newEntityRequest.getEntityType())
										.addMutations(
											GrpcLocalMutation.newBuilder()
												.setInsertReferenceMutation(
													GrpcInsertReferenceMutation.newBuilder()
														.setReferenceName(referenceEntityType)
														.setReferencePrimaryKey(referencePrimaryKey)
												)
												.build()
										)
										.addMutations(
											GrpcLocalMutation.newBuilder()
												.setInsertReferenceMutation(
													GrpcInsertReferenceMutation.newBuilder()
														.setReferenceName(referenceParameterEntityType)
														.setReferencePrimaryKey(referenceGroupPrimaryKey)
												)
												.build()
										)
										.addMutations(
											GrpcLocalMutation.newBuilder()
												.setSetReferenceGroupMutation(
													GrpcSetReferenceGroupMutation.newBuilder()
														.setReferenceName(referenceParameterEntityType)
														.setReferencePrimaryKey(referenceGroupPrimaryKey)
														.setGroupType(StringValue.of(referenceGroupEntityType))
														.setGroupPrimaryKey(referenceGroupPrimaryKey)
												)
												.build()
										)
										.addMutations(
											GrpcLocalMutation.newBuilder()
												.setReferenceAttributeMutation(
													GrpcReferenceAttributeMutation.newBuilder()
														.setReferenceName(referenceEntityType)
														.setReferencePrimaryKey(referencePrimaryKey)
														.setAttributeMutation(GrpcAttributeMutation.newBuilder()
															.setUpsertAttributeMutation(GrpcUpsertAttributeMutation.newBuilder()
																.setAttributeName(attributeName)
																.setAttributeValue(EvitaDataTypesConverter.toGrpcEvitaValue(attributeValue))
																.build())
															.build()
														)
												)
												.build()
										)
										.build()
								)
								.build()
						)
						.build()
				)
			);
		};

		assertDoesNotThrow(executableInsert);
		assertEquals(2, upsertEntityResponse.get().getEntity().getReferencesCount());
		final GrpcReference referenceCategoryAfterInsert = upsertEntityResponse.get().getEntity().getReferencesList()
			.stream()
			.filter(it -> referenceEntityType.equals(it.getReferencedEntityReference().getEntityType()))
			.findFirst()
			.orElseThrow(() -> new AssertionFailedError("Failed to find reference with entity type " + referenceEntityType + " in result!"));
		assertEquals(referenceEntityType, referenceCategoryAfterInsert.getReferencedEntityReference().getEntityType());
		assertEquals(referencePrimaryKey, referenceCategoryAfterInsert.getReferencedEntityReference().getPrimaryKey());
		assertEquals(1, referenceCategoryAfterInsert.getGlobalAttributesCount());
		assertEquals(cardinality, referenceCategoryAfterInsert.getReferenceCardinality());
		assertEquals(attributeValue, referenceCategoryAfterInsert.getGlobalAttributesMap().get(attributeName).getLongValue());

		final GrpcReference referenceParameterAfterInsert = upsertEntityResponse.get().getEntity().getReferencesList()
			.stream()
			.filter(it -> referenceParameterEntityType.equals(it.getReferencedEntityReference().getEntityType()))
			.findFirst()
			.orElseThrow(() -> new AssertionFailedError("Failed to find reference with entity type " + referenceParameterEntityType + " in result!"));
		assertEquals(referenceParameterEntityType, referenceParameterAfterInsert.getReferencedEntityReference().getEntityType());
		assertEquals(referenceGroupPrimaryKey, referenceParameterAfterInsert.getGroupReferencedEntityReference().getPrimaryKey());
		assertEquals(referenceGroupEntityType, referenceParameterAfterInsert.getGroupReferencedEntityReference().getEntityType());

		final int removeReferenceId = 1;
		final SealedEntity existingEntity = entities.stream().filter(entity ->
				entity.getReferences().stream().anyMatch(reference ->
					reference.getReferenceName().equals(referenceEntityType) &&
						(reference.getReferencedPrimaryKey() != referencePrimaryKey ||
							reference.getReferencedPrimaryKey() == removeReferenceId)
				)
			).findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Suitable reference not found!"));

		//noinspection ConstantConditions
		final GrpcEntityRequest existingEntityRequest = GrpcEntityRequest.newBuilder()
			.setEntityType(Entities.PRODUCT)
			.setPrimaryKey(existingEntity.getPrimaryKey())
			.setRequire("referenceContent(entityFetch(attributeContent()))")
			.build();

		final GrpcEntityResponse originalEntity = evitaSessionBlockingStub.getEntity(existingEntityRequest);

		final Executable executableUpdate = () -> {
			final StringWithParameters stringWithParameters = PrettyPrintingVisitor.toStringWithParameterExtraction(QueryConstraints.entityFetchAll().getRequirements());
			upsertEntityResponse.set(
				evitaSessionBlockingStub.upsertEntity(
					GrpcUpsertEntityRequest.newBuilder()
						.setRequire(stringWithParameters.query())
						.addAllPositionalQueryParams(
							stringWithParameters.parameters()
								.stream()
								.map(QueryConverter::convertQueryParam)
								.toList()
						)
						.setEntityMutation(
							GrpcEntityMutation.newBuilder()
								.setEntityUpsertMutation(
									GrpcEntityUpsertMutation.newBuilder()
										.setEntityType(existingEntityRequest.getEntityType())
										.setEntityPrimaryKey(Int32Value.of(existingEntityRequest.getPrimaryKey()))
										.addMutations(
											GrpcLocalMutation.newBuilder()
												.setInsertReferenceMutation(
													GrpcInsertReferenceMutation.newBuilder()
														.setReferenceName(referenceEntityType)
														.setReferencePrimaryKey(referencePrimaryKey)
														.setReferencedEntityType(StringValue.of(referenceEntityType))
												)
												.build()
										)
										.addMutations(
											GrpcLocalMutation.newBuilder()
												.setRemoveReferenceMutation(
													GrpcRemoveReferenceMutation.newBuilder()
														.setReferenceName(referenceEntityType)
														.setReferencePrimaryKey(removeReferenceId)
												)
												.build()
										)
										.addMutations(
											GrpcLocalMutation.newBuilder()
												.setReferenceAttributeMutation(
													GrpcReferenceAttributeMutation.newBuilder()
														.setReferenceName(referenceEntityType)
														.setReferencePrimaryKey(referencePrimaryKey)
														.setAttributeMutation(GrpcAttributeMutation.newBuilder()
															.setUpsertAttributeMutation(GrpcUpsertAttributeMutation.newBuilder()
																.setAttributeName(attributeName)
																.setAttributeValue(EvitaDataTypesConverter.toGrpcEvitaValue(attributeValue))
																.build())
															.build()
														)
												)
												.build()
										)
										.build()
								)
								.build()
						)
						.build()
				)
			);
		};

		assertDoesNotThrow(executableUpdate);

		final GrpcReference newReferenceAfterUpdate = upsertEntityResponse.get().getEntity().getReferencesList().stream()
			.filter(r ->
				r.getReferencedEntityReference().getEntityType().equals(referenceEntityType) &&
					r.getReferencedEntityReference().getPrimaryKey() == referencePrimaryKey)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Suitable reference not found!"));

		//one added, one removed
		assertEquals(originalEntity.getEntity().getReferencesCount(), upsertEntityResponse.get().getEntity().getReferencesCount());
		assertEquals(referenceEntityType, newReferenceAfterUpdate.getReferencedEntityReference().getEntityType());
		assertEquals(referencePrimaryKey, newReferenceAfterUpdate.getReferencedEntityReference().getPrimaryKey());
		assertFalse(newReferenceAfterUpdate.hasGroupReferencedEntityReference());

		final Optional<GrpcReference> existingReferenceAfterUpdate = upsertEntityResponse.get().getEntity().getReferencesList().stream()
			.filter(r ->
				r.getReferencedEntityReference().getEntityType().equals(referenceEntityType) &&
					r.getReferencedEntityReference().getPrimaryKey() != referencePrimaryKey
			)
			.findFirst();

		if (existingReferenceAfterUpdate.isPresent() && existingReferenceAfterUpdate.get().getGlobalAttributesMap().size() > 0) {
			assertNotEquals(attributeValue, existingReferenceAfterUpdate.get().getGlobalAttributesMap().get(attributeName).getLongValue());
		}
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should throw exception when trying to open transaction when one is already open")
	void shouldThrowWhenOpeningTransactionWhenAnotherAlreadyOpen(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_WRITE);

		final Executable openTransactionExecutable = () -> evitaSessionBlockingStub.openTransaction(Empty.getDefaultInstance());

		assertDoesNotThrow(openTransactionExecutable);
		assertThrows(StatusRuntimeException.class, openTransactionExecutable);
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should throw exception when trying to close transaction when none is open")
	void shouldThrowWhenClosingTransactionWhenNoneOpen(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_WRITE);

		evitaSessionBlockingStub.openTransaction(Empty.getDefaultInstance());

		final Executable closeTransactionExecutable = () -> evitaSessionBlockingStub.closeTransaction(
			GrpcCloseTransactionRequest.newBuilder()
				.setRollback(false)
				.build()
		);

		assertDoesNotThrow(closeTransactionExecutable);
		assertThrows(StatusRuntimeException.class, closeTransactionExecutable);
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should write changes to database only after transaction is closed")
	void shouldWriteChangesToDatabaseAfterCloseTransaction(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.READ_WRITE, false);

		final int primaryKey = 99999;
		final String entityType = Entities.CATEGORY;

		final EvitaSessionContract evitaSession = evita.createReadOnlySession(TEST_CATALOG);
		assertNull(evitaSession.getEntity(entityType, primaryKey).orElse(null));

		evitaSessionBlockingStub.openTransaction(Empty.getDefaultInstance());

		evitaSessionBlockingStub.upsertEntity(
			GrpcUpsertEntityRequest.newBuilder()
				.setEntityMutation(
					GrpcEntityMutation.newBuilder()
						.setEntityUpsertMutation(
							GrpcEntityUpsertMutation.newBuilder()
								.setEntityPrimaryKey(Int32Value.of(primaryKey))
								.setEntityType(entityType)
								.addMutations(GrpcLocalMutation.newBuilder()
									.setUpsertAttributeMutation(GrpcUpsertAttributeMutation.newBuilder()
										.setAttributeName(ATTRIBUTE_PRIORITY)
										.setAttributeValue(EvitaDataTypesConverter.toGrpcEvitaValue(99999L))
										.build())
									.build())
								.build()
						)
						.build()
				)
				.build()
		);

		assertNull(evitaSession.getEntity(entityType, primaryKey).orElse(null));

		evitaSessionBlockingStub.closeTransaction(GrpcCloseTransactionRequest.newBuilder().build());

		assertNotNull(evitaSession.getEntity(entityType, primaryKey));

		evitaSessionBlockingStub.deleteEntity(GrpcDeleteEntityRequest.newBuilder()
			.setEntityType(entityType)
			.setPrimaryKey(Int32Value.of(primaryKey))
			.build());
	}

	@Test
	@UseDataSet(THOUSAND_PRODUCTS)
	@DisplayName("Should return query record page in binary data format")
	void shouldReturnQueryRecordPageInBinaryDataFormat(Evita evita) {
		final EvitaSessionServiceGrpc.EvitaSessionServiceBlockingStub evitaSessionBlockingStub = EvitaSessionServiceGrpc.newBlockingStub(channel);
		SessionInitializer.setSession(channel, GrpcSessionType.BINARY_READ_ONLY);

		final List<QueryParam> params = new ArrayList<>(8);
		params.add(convertQueryParam(Entities.PRODUCT));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(2));
		params.add(convertQueryParam(3));
		params.add(convertQueryParam(4));
		params.add(convertQueryParam(5));
		params.add(convertQueryParam(1));
		params.add(convertQueryParam(Integer.MAX_VALUE));

		final String stringQuery = """
			query(
				collection(?),
				filterBy(
					entityPrimaryKeyInSet(?, ?, ?, ?, ?)
				),
				require(
					page(?, ?),
					entityFetch(
						attributeContent(),
						priceContent(),
						referenceContent(),
						associatedDataContent()
					)
				)
			)
			""";

		final AtomicReference<GrpcQueryResponse> response = new AtomicReference<>();

		final Executable executable = () ->
			response.set(evitaSessionBlockingStub.query(GrpcQueryRequest.newBuilder()
				.setQuery(stringQuery)
				.addAllPositionalQueryParams(params)
				.build()
			));

		assertDoesNotThrow(executable);

		final Query query = QueryUtil.parseQuery(stringQuery, params, null);

		assertNotNull(query);

		final List<BinaryEntity> entityResponse = evita.createSession(new SessionTraits(TEST_CATALOG, SessionFlags.BINARY)).queryList(query, BinaryEntity.class);

		for (int i = 0; i < entityResponse.size(); i++) {
			final GrpcBinaryEntity grpcBinaryEntity = response.get().getRecordPage().getBinaryEntitiesList().get(i);
			final BinaryEntity binaryEntity = entityResponse.get(i);
			assertBinaryEntity(binaryEntity, grpcBinaryEntity);
		}
	}

	@DataSet(THOUSAND_PRODUCTS)
	List<SealedEntity> setUp(Evita evita) {
		initGrpcServerAndChannel(new EvitaSystemDataProvider(evita), new GrpcConfig());

		return new TestDataProvider().generateEntities(evita);
	}

	private void initGrpcServerAndChannel(@Nonnull EvitaSystemDataProvider evitaSystemDataProvider, @Nonnull GrpcConfig apiConfig) {
		if (server != null && !server.isTerminated()) {
			server.shutdown();
		}
		final GrpcServer grpcServer = new GrpcServer(evitaSystemDataProvider, new ApiOptions(null, new CertificateSettings.Builder().build(), Collections.emptyMap()), apiConfig);
		try {
			server = grpcServer.getServer().start();
		} catch (Exception e) {
			log.error("Failed to start server", e);
		}

		channel = TestChannelCreator.getChannel(new ClientSessionInterceptor(), server.getPort());
	}
}
