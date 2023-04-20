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

package io.evitadb.externalApi.graphql.api.catalog.dataApi.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLInputType;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLOutputType;
import graphql.schema.PropertyDataFetcher;
import io.evitadb.api.query.descriptor.ConstraintDescriptorProvider;
import io.evitadb.api.query.require.HierarchyStatistics;
import io.evitadb.api.query.require.HierarchyStopAt;
import io.evitadb.api.requestResponse.extraResult.FacetSummary.RequestImpact;
import io.evitadb.api.requestResponse.schema.AttributeSchemaContract;
import io.evitadb.api.requestResponse.schema.EntitySchemaContract;
import io.evitadb.api.requestResponse.schema.ReferenceSchemaContract;
import io.evitadb.externalApi.api.catalog.dataApi.constraint.HierarchyDataLocator;
import io.evitadb.externalApi.api.catalog.dataApi.model.DataChunkDescriptor;
import io.evitadb.externalApi.api.catalog.dataApi.model.EntityDescriptor;
import io.evitadb.externalApi.api.catalog.dataApi.model.RecordPageDescriptor;
import io.evitadb.externalApi.api.catalog.dataApi.model.RecordStripDescriptor;
import io.evitadb.externalApi.api.catalog.dataApi.model.ResponseDescriptor;
import io.evitadb.externalApi.api.catalog.dataApi.model.extraResult.AttributeHistogramDescriptor;
import io.evitadb.externalApi.api.catalog.dataApi.model.extraResult.ExtraResultsDescriptor;
import io.evitadb.externalApi.api.catalog.dataApi.model.extraResult.FacetSummaryDescriptor;
import io.evitadb.externalApi.api.catalog.dataApi.model.extraResult.FacetSummaryDescriptor.FacetGroupStatisticsDescriptor;
import io.evitadb.externalApi.api.catalog.dataApi.model.extraResult.FacetSummaryDescriptor.FacetRequestImpactDescriptor;
import io.evitadb.externalApi.api.catalog.dataApi.model.extraResult.FacetSummaryDescriptor.FacetStatisticsDescriptor;
import io.evitadb.externalApi.api.catalog.dataApi.model.extraResult.HierarchyDescriptor.HierarchyOfReferenceDescriptor;
import io.evitadb.externalApi.api.catalog.dataApi.model.extraResult.HierarchyDescriptor.HierarchyOfSelfDescriptor;
import io.evitadb.externalApi.api.catalog.dataApi.model.extraResult.HierarchyDescriptor.ParentInfoDescriptor;
import io.evitadb.externalApi.api.catalog.dataApi.model.extraResult.HierarchyParentsDescriptor;
import io.evitadb.externalApi.api.catalog.dataApi.model.extraResult.HierarchyParentsDescriptor.ParentsOfEntityDescriptor;
import io.evitadb.externalApi.api.catalog.dataApi.model.extraResult.HierarchyParentsDescriptor.ParentsOfEntityDescriptor.ParentsOfReferenceDescriptor;
import io.evitadb.externalApi.api.catalog.dataApi.model.extraResult.HierarchyDescriptor;
import io.evitadb.externalApi.api.catalog.dataApi.model.extraResult.HierarchyDescriptor.LevelInfoDescriptor;
import io.evitadb.externalApi.api.catalog.dataApi.model.extraResult.HistogramDescriptor;
import io.evitadb.externalApi.api.catalog.dataApi.model.extraResult.HistogramDescriptor.BucketDescriptor;
import io.evitadb.externalApi.graphql.api.builder.BuiltFieldDescriptor;
import io.evitadb.externalApi.graphql.api.catalog.builder.CatalogGraphQLSchemaBuildingContext;
import io.evitadb.externalApi.graphql.api.catalog.dataApi.builder.constraint.FilterConstraintSchemaBuilder;
import io.evitadb.externalApi.graphql.api.catalog.dataApi.builder.constraint.GraphQLConstraintSchemaBuildingContext;
import io.evitadb.externalApi.graphql.api.catalog.dataApi.builder.constraint.RequireConstraintSchemaBuilder;
import io.evitadb.externalApi.graphql.api.catalog.dataApi.model.ResponseHeaderDescriptor.BucketsFieldHeaderDescriptor;
import io.evitadb.externalApi.graphql.api.catalog.dataApi.model.ResponseHeaderDescriptor.QueryTelemetryFieldHeaderDescriptor;
import io.evitadb.externalApi.graphql.api.catalog.dataApi.model.ResponseHeaderDescriptor.RecordPageFieldHeaderDescriptor;
import io.evitadb.externalApi.graphql.api.catalog.dataApi.model.ResponseHeaderDescriptor.RecordStripFieldHeaderDescriptor;
import io.evitadb.externalApi.graphql.api.catalog.dataApi.resolver.dataFetcher.RecordPageDataFetcher;
import io.evitadb.externalApi.graphql.api.catalog.dataApi.resolver.dataFetcher.RecordStripDataFetcher;
import io.evitadb.externalApi.graphql.api.catalog.dataApi.resolver.dataFetcher.SingleParentsOfReferenceDataFetcher;
import io.evitadb.externalApi.graphql.api.catalog.dataApi.resolver.dataFetcher.extraResult.AttributeHistogramDataFetcher;
import io.evitadb.externalApi.graphql.api.catalog.dataApi.resolver.dataFetcher.extraResult.AttributeHistogramsDataFetcher;
import io.evitadb.externalApi.graphql.api.catalog.dataApi.resolver.dataFetcher.extraResult.ExtraResultsDataFetcher;
import io.evitadb.externalApi.graphql.api.catalog.dataApi.resolver.dataFetcher.extraResult.FacetGroupStatisticsDataFetcher;
import io.evitadb.externalApi.graphql.api.catalog.dataApi.resolver.dataFetcher.extraResult.FacetSummaryDataFetcher;
import io.evitadb.externalApi.graphql.api.catalog.dataApi.resolver.dataFetcher.extraResult.HierarchyDataFetcher;
import io.evitadb.externalApi.graphql.api.catalog.dataApi.resolver.dataFetcher.extraResult.HierarchyParentsDataFetcher;
import io.evitadb.externalApi.graphql.api.catalog.dataApi.resolver.dataFetcher.extraResult.PriceHistogramDataFetcher;
import io.evitadb.externalApi.graphql.api.catalog.dataApi.resolver.dataFetcher.extraResult.QueryTelemetryDataFetcher;
import io.evitadb.externalApi.graphql.api.model.ObjectDescriptorToGraphQLObjectTransformer;
import io.evitadb.externalApi.graphql.api.model.PropertyDescriptorToGraphQLArgumentTransformer;
import io.evitadb.externalApi.graphql.api.model.PropertyDescriptorToGraphQLFieldTransformer;
import io.evitadb.externalApi.graphql.exception.GraphQLSchemaBuildingError;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLList.list;
import static graphql.schema.GraphQLNonNull.nonNull;
import static graphql.schema.GraphQLTypeReference.typeRef;
import static io.evitadb.externalApi.api.ExternalApiNamingConventions.PROPERTY_NAME_NAMING_CONVENTION;
import static io.evitadb.externalApi.api.ExternalApiNamingConventions.TYPE_NAME_NAMING_CONVENTION;
import static io.evitadb.externalApi.graphql.api.dataType.GraphQLScalars.OBJECT;
import static io.evitadb.externalApi.graphql.api.dataType.GraphQLScalars.STRING;

/**
 * Builds schema object representing {@link io.evitadb.api.requestResponse.EvitaResponse} with entities and extra results.
 *
 * @author Lukáš Hornych, FG Forrest a.s. (c) 2023
 */
@RequiredArgsConstructor
public class FullResponseObjectBuilder {

	private static final ObjectMapper QUERY_TELEMETRY_OBJECT_MAPPER = new ObjectMapper();

	@Nonnull private final CatalogGraphQLSchemaBuildingContext buildingContext;
	@Nonnull private final PropertyDescriptorToGraphQLArgumentTransformer argumentBuilderTransformer;
	@Nonnull private final ObjectDescriptorToGraphQLObjectTransformer objectBuilderTransformer;
	@Nonnull private final PropertyDescriptorToGraphQLFieldTransformer fieldBuilderTransformer;
	@Nonnull private final GraphQLConstraintSchemaBuildingContext constraintContext;
	@Nonnull private final FilterConstraintSchemaBuilder filterConstraintSchemaBuilder;

	public void buildCommonTypes() {
		buildingContext.registerType(BucketDescriptor.THIS.to(objectBuilderTransformer).build());
		buildingContext.registerType(buildHistogramObject());
		// todo lho: remove after https://gitlab.fg.cz/hv/evita/-/issues/120 is implemented
		buildingContext.registerType(buildAttributeNamedHistogramObject());
		buildingContext.registerType(buildFacetRequestImpactObject());
	}

	@Nonnull
	public GraphQLObjectType build(@Nonnull EntitySchemaContract entitySchema) {
		final String objectName = ResponseDescriptor.THIS.name(entitySchema);

		final GraphQLObjectType.Builder responseObjectBuilder = ResponseDescriptor.THIS
			.to(objectBuilderTransformer)
			.name(objectName);

		final List<BuiltFieldDescriptor> responseFields = new LinkedList<>();

		responseFields.add(buildRecordPageField(entitySchema));
		responseFields.add(buildRecordStripField(entitySchema));
		buildExtraResultsField(entitySchema).ifPresent(responseFields::add);

		responseFields.forEach(responseField ->
			buildingContext.registerFieldToObject(
				objectName,
				responseObjectBuilder,
				responseField
			)
		);

		return responseObjectBuilder.build();
	}

	@Nonnull
	private BuiltFieldDescriptor buildRecordPageField(@Nonnull EntitySchemaContract entitySchema) {
		final GraphQLObjectType recordPageObject = buildRecordPageObject(entitySchema);

		final GraphQLFieldDefinition recordPageField = ResponseDescriptor.RECORD_PAGE
			.to(fieldBuilderTransformer)
			.type(recordPageObject)
			.argument(RecordPageFieldHeaderDescriptor.NUMBER.to(argumentBuilderTransformer))
			.argument(RecordPageFieldHeaderDescriptor.SIZE.to(argumentBuilderTransformer))
			.build();

		return new BuiltFieldDescriptor(
			recordPageField,
			new RecordPageDataFetcher()
		);
	}

	@Nonnull
	private GraphQLObjectType buildRecordPageObject(@Nonnull EntitySchemaContract entitySchema) {
		final String objectName = RecordPageDescriptor.THIS.name(entitySchema);

		return RecordPageDescriptor.THIS
			.to(objectBuilderTransformer)
			.name(objectName)
			.field(DataChunkDescriptor.DATA
				.to(fieldBuilderTransformer)
				.type(nonNull(list(nonNull(typeRef(EntityDescriptor.THIS.name(entitySchema)))))))
			.build();
	}

	@Nonnull
	private BuiltFieldDescriptor buildRecordStripField(@Nonnull EntitySchemaContract entitySchema) {
		final GraphQLObjectType recordStripObject = buildRecordStripObject(entitySchema);

		final GraphQLFieldDefinition recordStripField = ResponseDescriptor.RECORD_STRIP
			.to(fieldBuilderTransformer)
			.type(recordStripObject)
			.argument(RecordStripFieldHeaderDescriptor.OFFSET.to(argumentBuilderTransformer))
			.argument(RecordStripFieldHeaderDescriptor.LIMIT.to(argumentBuilderTransformer))
			.build();

		return new BuiltFieldDescriptor(
			recordStripField,
			new RecordStripDataFetcher()
		);
	}

	@Nonnull
	private GraphQLObjectType buildRecordStripObject(@Nonnull EntitySchemaContract entitySchema) {
		final String objectName = RecordStripDescriptor.THIS.name(entitySchema);

		return RecordStripDescriptor.THIS
			.to(objectBuilderTransformer)
			.name(objectName)
			.field(DataChunkDescriptor.DATA
				.to(fieldBuilderTransformer)
				.type(nonNull(list(nonNull(typeRef(EntityDescriptor.THIS.name(entitySchema)))))))
			.build();
	}

	@Nonnull
	private Optional<BuiltFieldDescriptor> buildExtraResultsField(@Nonnull EntitySchemaContract entitySchema) {
		final Optional<GraphQLObjectType> extraResultsObject = buildExtraResultsObject(entitySchema);
		if (extraResultsObject.isEmpty()) {
			return Optional.empty();
		}

		final GraphQLFieldDefinition extraResultsField = ResponseDescriptor.EXTRA_RESULTS
			.to(fieldBuilderTransformer)
			.type(nonNull(extraResultsObject.get()))
			.build();

		return Optional.of(new BuiltFieldDescriptor(
			extraResultsField,
			new ExtraResultsDataFetcher()
		));
	}

	@Nonnull
	private Optional<GraphQLObjectType> buildExtraResultsObject(@Nonnull EntitySchemaContract entitySchema) {
		final String objectName = ExtraResultsDescriptor.THIS.name(entitySchema);

		final GraphQLObjectType.Builder extraResultsObjectBuilder = ExtraResultsDescriptor.THIS
			.to(objectBuilderTransformer)
			.name(objectName);

		final List<BuiltFieldDescriptor> extraResultFields = new ArrayList<>(10);

		buildAttributeHistogramField(entitySchema).ifPresent(extraResultFields::add);
		// todo lho: remove after https://gitlab.fg.cz/hv/evita/-/issues/120 is implemented
		buildAttributeHistogramsField(entitySchema).ifPresent(extraResultFields::add);
		buildPriceHistogramField(entitySchema).ifPresent(extraResultFields::add);
		buildFacetSummaryField(entitySchema).ifPresent(extraResultFields::add);
		extraResultFields.addAll(buildHierarchyFields(entitySchema));
		extraResultFields.add(buildQueryTelemetryField());

		if (extraResultFields.isEmpty()) {
			return Optional.empty();
		}

		extraResultFields.forEach(extraResultField ->
			buildingContext.registerFieldToObject(
				objectName,
				extraResultsObjectBuilder,
				extraResultField
			)
		);
		return Optional.of(extraResultsObjectBuilder.build());
	}

	@Nonnull
	private Optional<BuiltFieldDescriptor> buildAttributeHistogramField(@Nonnull EntitySchemaContract entitySchema) {
		final Optional<GraphQLObjectType> attributeHistogramObject = buildAttributeHistogramObject(entitySchema);
		if (attributeHistogramObject.isEmpty()) {
			return Optional.empty();
		}

		final GraphQLFieldDefinition attributeHistogramField = ExtraResultsDescriptor.ATTRIBUTE_HISTOGRAM
			.to(fieldBuilderTransformer)
			.type(attributeHistogramObject.get())
			.build();

		return Optional.of(new BuiltFieldDescriptor(
			attributeHistogramField,
			new AttributeHistogramDataFetcher()
		));
	}

	@Nonnull
	private Optional<GraphQLObjectType> buildAttributeHistogramObject(@Nonnull EntitySchemaContract entitySchema) {
		final List<AttributeSchemaContract> attributeSchemas = entitySchema
			.getAttributes()
			.values()
			.stream()
			.filter(attributeSchema -> attributeSchema.isFilterable() &&
				Number.class.isAssignableFrom(attributeSchema.getPlainType()))
			.toList();

		if (attributeSchemas.isEmpty()) {
			return Optional.empty();
		}

		final GraphQLObjectType.Builder attributeHistogramsObjectBuilder = AttributeHistogramDescriptor.THIS
			.to(objectBuilderTransformer)
			.name(AttributeHistogramDescriptor.THIS.name(entitySchema));
		attributeSchemas.forEach(attributeSchema ->
			attributeHistogramsObjectBuilder.field(f -> f
				.name(attributeSchema.getNameVariant(PROPERTY_NAME_NAMING_CONVENTION))
				.type(typeRef(HistogramDescriptor.THIS.name())))
		);

		return Optional.of(attributeHistogramsObjectBuilder.build());
	}

	// todo lho: remove after https://gitlab.fg.cz/hv/evita/-/issues/120 is implemented
	@Nonnull
	private static Optional<BuiltFieldDescriptor> buildAttributeHistogramsField(@Nonnull EntitySchemaContract entitySchema) {
		final GraphQLFieldDefinition attributeHistogramField = newFieldDefinition()
			.name("attributeHistograms")
			.type(list(nonNull(typeRef("AttributeNamedHistogram"))))
			.argument(a -> a
				.name("attributes")
				.type(nonNull(list(nonNull(STRING)))))
			.build();

		return Optional.of(new BuiltFieldDescriptor(
			attributeHistogramField,
			new AttributeHistogramsDataFetcher(entitySchema)
		));
	}

	@Nonnull
	private Optional<BuiltFieldDescriptor> buildPriceHistogramField(@Nonnull EntitySchemaContract entitySchema) {
		if (entitySchema.getCurrencies().isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(new BuiltFieldDescriptor(
			ExtraResultsDescriptor.PRICE_HISTOGRAM.to(fieldBuilderTransformer).build(),
			new PriceHistogramDataFetcher()
		));
	}

	@Nonnull
	private Optional<BuiltFieldDescriptor> buildFacetSummaryField(@Nonnull EntitySchemaContract entitySchema) {
		final Optional<GraphQLObjectType> facetSummaryObject = buildFacetSummaryObject(entitySchema);
		if (facetSummaryObject.isEmpty()) {
			return Optional.empty();
		}

		final GraphQLFieldDefinition facetSummaryField = ExtraResultsDescriptor.FACET_SUMMARY
			.to(fieldBuilderTransformer)
			.type(facetSummaryObject.get())
			.build();

		return Optional.of(new BuiltFieldDescriptor(
			facetSummaryField,
			new FacetSummaryDataFetcher()
		));
	}

	@Nonnull
	private Optional<GraphQLObjectType> buildFacetSummaryObject(@Nonnull EntitySchemaContract entitySchema) {
		final List<ReferenceSchemaContract> referenceSchemas = entitySchema
			.getReferences()
			.values()
			.stream()
			.filter(ReferenceSchemaContract::isFaceted)
			.toList();

		if (referenceSchemas.isEmpty()) {
			return Optional.empty();
		}

		final String objectName = FacetSummaryDescriptor.THIS.name(entitySchema);

		final GraphQLObjectType.Builder facetSummaryObjectBuilder = FacetSummaryDescriptor.THIS
			.to(objectBuilderTransformer)
			.name(objectName);


		referenceSchemas.forEach(referenceSchema -> {
			final BuiltFieldDescriptor facetGroupStatisticsField = buildFacetGroupStatisticsField(
				entitySchema,
				referenceSchema
			);

			buildingContext.registerFieldToObject(
				objectName,
				facetSummaryObjectBuilder,
				facetGroupStatisticsField
			);
		});

		return Optional.of(facetSummaryObjectBuilder.build());
	}

	@Nonnull
	private BuiltFieldDescriptor buildFacetGroupStatisticsField(@Nonnull EntitySchemaContract entitySchema,
	                                                            @Nonnull ReferenceSchemaContract referenceSchema) {
		final GraphQLObjectType facetGroupStatisticsObject = buildFacetGroupStatisticsObject(
			entitySchema,
			referenceSchema
		);

		final GraphQLFieldDefinition facetGroupStatisticsField =  newFieldDefinition()
			.name(referenceSchema.getNameVariant(PROPERTY_NAME_NAMING_CONVENTION))
			.type(list(nonNull(facetGroupStatisticsObject)))
			.build();

		return new BuiltFieldDescriptor(
			facetGroupStatisticsField,
			new FacetGroupStatisticsDataFetcher(referenceSchema)
		);
	}

	@Nonnull
	private GraphQLObjectType buildFacetGroupStatisticsObject(@Nonnull EntitySchemaContract entitySchema,
	                                                          @Nonnull ReferenceSchemaContract referenceSchema) {
		final EntitySchemaContract groupEntitySchema = referenceSchema.isReferencedGroupTypeManaged() ?
			Optional.ofNullable(referenceSchema.getReferencedGroupType())
				.map(groupType -> buildingContext
					.getSchema()
					.getEntitySchemaOrThrowException(groupType))
				.orElse(null) :
			null;

		final GraphQLOutputType groupEntityObject = buildReferencedEntityObject(groupEntitySchema);
		final GraphQLObjectType facetStatisticsObject = buildFacetStatisticsObject(entitySchema, referenceSchema);

		return FacetGroupStatisticsDescriptor.THIS
			.to(objectBuilderTransformer)
			.name(FacetGroupStatisticsDescriptor.THIS.name(entitySchema, referenceSchema))
			.field(FacetGroupStatisticsDescriptor.GROUP_ENTITY
				.to(fieldBuilderTransformer)
				.type(groupEntityObject))
			.field(FacetGroupStatisticsDescriptor.FACET_STATISTICS
				.to(fieldBuilderTransformer)
				.type(nonNull(list(nonNull(facetStatisticsObject)))))
			.build();
	}

	@Nonnull
	private GraphQLObjectType buildFacetStatisticsObject(@Nonnull EntitySchemaContract entitySchema,
	                                                     @Nonnull ReferenceSchemaContract referenceSchema) {
		final EntitySchemaContract facetEntitySchema = referenceSchema.isReferencedEntityTypeManaged() ?
			buildingContext
				.getSchema()
				.getEntitySchemaOrThrowException(referenceSchema.getReferencedEntityType()) :
			null;
		final GraphQLOutputType facetEntityObject = buildReferencedEntityObject(facetEntitySchema);

		return FacetStatisticsDescriptor.THIS
			.to(objectBuilderTransformer)
			.name(FacetStatisticsDescriptor.THIS.name(entitySchema, referenceSchema))
			.field(FacetStatisticsDescriptor.FACET_ENTITY
				.to(fieldBuilderTransformer)
				.type(facetEntityObject))
			.build();
	}

	@Nonnull
	private List<BuiltFieldDescriptor> buildHierarchyFields(@Nonnull EntitySchemaContract entitySchema) {
		final List<ReferenceSchemaContract> referenceSchemas = entitySchema
			.getReferences()
			.values()
			.stream()
			.filter(referenceSchema -> referenceSchema.isReferencedEntityTypeManaged() &&
				buildingContext.getSchema().getEntitySchema(referenceSchema.getReferencedEntityType())
					.map(EntitySchemaContract::isWithHierarchy)
					.orElseThrow(() -> new GraphQLSchemaBuildingError("Reference `" + referenceSchema.getName() + "` should have existing entity schema but no schema found.")))
			.toList();

		if (referenceSchemas.isEmpty() && !entitySchema.isWithHierarchy()) {
			return List.of();
		}

		final List<BuiltFieldDescriptor> hierarchyExtraResultFields = new ArrayList<>(2);

		final GraphQLObjectType parentsObject = buildParentsObject(entitySchema, referenceSchemas);
		final GraphQLFieldDefinition parentsField = ExtraResultsDescriptor.HIERARCHY_PARENTS
			.to(fieldBuilderTransformer)
			.type(parentsObject)
			.build();
		hierarchyExtraResultFields.add(new BuiltFieldDescriptor(
			parentsField,
			new HierarchyParentsDataFetcher(entitySchema.getReferences().values())
		));

		final GraphQLObjectType hierarchyStatisticsObject = buildHierarchyObject(entitySchema, referenceSchemas);
		final GraphQLFieldDefinition hierarchyField = ExtraResultsDescriptor.HIERARCHY
			.to(fieldBuilderTransformer)
			.type(hierarchyStatisticsObject)
			.build();
		hierarchyExtraResultFields.add(new BuiltFieldDescriptor(
			hierarchyField,
			new HierarchyDataFetcher(entitySchema.getReferences().values())
		));

		return hierarchyExtraResultFields;
	}

	@Nonnull
	private GraphQLObjectType buildParentsObject(@Nonnull EntitySchemaContract entitySchema,
	                                             @Nonnull List<ReferenceSchemaContract> referenceSchemas) {
		final String objectName = HierarchyParentsDescriptor.THIS.name(entitySchema);

		final GraphQLObjectType.Builder parentsObjectBuilder = HierarchyParentsDescriptor.THIS
			.to(objectBuilderTransformer)
			.name(objectName);

		if (entitySchema.isWithHierarchy()) {
			buildingContext.registerFieldToObject(
				objectName,
				parentsObjectBuilder,
				buildSelfParentsOfEntityField(entitySchema)
			);
		}
		referenceSchemas.forEach(referenceSchema ->
			buildingContext.registerFieldToObject(
				objectName,
				parentsObjectBuilder,
				buildParentsOfEntityField(entitySchema, referenceSchema)
			)
		);

		return parentsObjectBuilder.build();
	}

	@Nonnull
	private BuiltFieldDescriptor buildSelfParentsOfEntityField(@Nonnull EntitySchemaContract entitySchema) {
		final GraphQLObjectType parentsOfEntityObject = buildSelfParentsOfEntityObject(entitySchema);

		final GraphQLFieldDefinition parentsField = HierarchyParentsDescriptor.SELF
			.to(fieldBuilderTransformer)
			.type(list(nonNull(parentsOfEntityObject)))
			.build();

		return new BuiltFieldDescriptor(parentsField, null);
	}

	@Nonnull
	private GraphQLObjectType buildSelfParentsOfEntityObject(@Nonnull EntitySchemaContract entitySchema) {
		final String objectName = ParentsOfEntityDescriptor.THIS.name(entitySchema, entitySchema);

		final GraphQLObjectType.Builder parentsOfEntityObjectBuilder = ParentsOfEntityDescriptor.THIS
			.to(objectBuilderTransformer)
			.name(objectName);

		buildingContext.registerFieldToObject(
			objectName,
			parentsOfEntityObjectBuilder,
			buildSelfParentsOfEntityParentEntitiesField(entitySchema)
		);

		return parentsOfEntityObjectBuilder.build();
	}

	@Nonnull
	private BuiltFieldDescriptor buildSelfParentsOfEntityParentEntitiesField(@Nonnull EntitySchemaContract entitySchema) {
		final GraphQLFieldDefinition parentEntitiesField = ParentsOfEntityDescriptor.PARENT_ENTITIES
			.to(fieldBuilderTransformer)
			.type(nonNull(list(nonNull(typeRef(EntityDescriptor.THIS.name(entitySchema))))))
			.build();

		return new BuiltFieldDescriptor(
			parentEntitiesField,
			new SingleParentsOfReferenceDataFetcher()
		);
	}

	@Nonnull
	private BuiltFieldDescriptor buildParentsOfEntityField(@Nonnull EntitySchemaContract entitySchema,
	                                                       @Nonnull ReferenceSchemaContract referenceSchema) {
		final GraphQLObjectType parentsOfEntityObject = buildParentsOfEntityObject(entitySchema, referenceSchema);

		final GraphQLFieldDefinition singleParentsField = newFieldDefinition()
			.name(referenceSchema.getNameVariant(PROPERTY_NAME_NAMING_CONVENTION))
			.type(list(nonNull(parentsOfEntityObject)))
			.build();

		return new BuiltFieldDescriptor(singleParentsField, null);
	}

	@Nonnull
	private GraphQLObjectType buildParentsOfEntityObject(@Nonnull EntitySchemaContract entitySchema,
	                                                     @Nonnull ReferenceSchemaContract referenceSchema) {
		final String objectName = ParentsOfEntityDescriptor.THIS.name(entitySchema, referenceSchema);

		final GraphQLObjectType.Builder parentsOfEntityObjectBuilder = ParentsOfEntityDescriptor.THIS
			.to(objectBuilderTransformer)
			.name(objectName);

		buildingContext.registerFieldToObject(
			objectName,
			parentsOfEntityObjectBuilder,
			buildParentsOfEntityParentEntitiesField(referenceSchema)
		);

		buildingContext.registerFieldToObject(
			objectName,
			parentsOfEntityObjectBuilder,
			buildParentsOfEntityReferencesField(entitySchema, referenceSchema)
		);

		return parentsOfEntityObjectBuilder.build();
	}

	@Nonnull
	private BuiltFieldDescriptor buildParentsOfEntityReferencesField(@Nonnull EntitySchemaContract entitySchema,
	                                                                 @Nonnull ReferenceSchemaContract referenceSchema) {
		final GraphQLObjectType object = buildParentsOfEntityReferencesObject(entitySchema, referenceSchema);

		final GraphQLFieldDefinition referencesField = ParentsOfEntityDescriptor.REFERENCES
			.to(fieldBuilderTransformer)
			.type(nonNull(list(nonNull(object))))
			.build();

		return new BuiltFieldDescriptor(referencesField, null);
	}

	@Nonnull
	private GraphQLObjectType buildParentsOfEntityReferencesObject(@Nonnull EntitySchemaContract entitySchema,
	                                                               @Nonnull ReferenceSchemaContract referenceSchema) {
		final EntitySchemaContract referencedEntitySchema = buildingContext
			.getSchema()
			.getEntitySchemaOrThrowException(referenceSchema.getReferencedEntityType());
		final String referencedEntityObjectName = referencedEntitySchema.getNameVariant(TYPE_NAME_NAMING_CONVENTION);

		return ParentsOfReferenceDescriptor.THIS
			.to(objectBuilderTransformer)
			.name(ParentsOfReferenceDescriptor.THIS.name(entitySchema, referenceSchema))
			.field(ParentsOfReferenceDescriptor.PARENT_ENTITIES
				.to(fieldBuilderTransformer)
				.type(nonNull(list(nonNull(typeRef(referencedEntityObjectName))))))
			.build();
	}

	@Nonnull
	private BuiltFieldDescriptor buildParentsOfEntityParentEntitiesField(@Nonnull ReferenceSchemaContract referenceSchema) {
		final EntitySchemaContract referencedEntitySchema = buildingContext
			.getSchema()
			.getEntitySchemaOrThrowException(referenceSchema.getReferencedEntityType());
		final String referencedEntityObjectName = referencedEntitySchema.getNameVariant(TYPE_NAME_NAMING_CONVENTION);

		final GraphQLFieldDefinition parentEntitiesField = ParentsOfEntityDescriptor.PARENT_ENTITIES
			.to(fieldBuilderTransformer)
			.type(nonNull(list(nonNull(typeRef(referencedEntityObjectName)))))
			.build();

		return new BuiltFieldDescriptor(
			parentEntitiesField,
			new SingleParentsOfReferenceDataFetcher()
		);
	}

	@Nonnull
	private GraphQLObjectType buildHierarchyObject(@Nonnull EntitySchemaContract entitySchema,
	                                               @Nonnull List<ReferenceSchemaContract> referenceSchemas) {
		final String objectName = HierarchyDescriptor.THIS.name(entitySchema);
		final GraphQLObjectType.Builder hierarchyStatisticsObjectBuilder = HierarchyDescriptor.THIS
			.to(objectBuilderTransformer)
			.name(objectName);

		if (entitySchema.isWithHierarchy()) {
			buildingContext.registerFieldToObject(
				objectName,
				hierarchyStatisticsObjectBuilder,
				buildHierarchyOfSelfField(entitySchema)
			);
		}
		referenceSchemas.forEach(referenceSchema ->
			buildingContext.registerFieldToObject(
				objectName,
				hierarchyStatisticsObjectBuilder,
				buildHierarchyOfReferenceField(entitySchema, referenceSchema)
			)
		);

		return hierarchyStatisticsObjectBuilder.build();
	}

	@Nonnull
	private BuiltFieldDescriptor buildHierarchyOfSelfField(@Nonnull EntitySchemaContract entitySchema) {
		final GraphQLObjectType hierarchyOfSelfObject = buildHierarchyOfSelfObject(entitySchema);
		final GraphQLFieldDefinition hierarchyOfSelfField = HierarchyDescriptor.SELF
			.to(fieldBuilderTransformer)
			.type(nonNull(hierarchyOfSelfObject))
			.build();
		return new BuiltFieldDescriptor(hierarchyOfSelfField, null);
	}

	@Nonnull
	private GraphQLObjectType buildHierarchyOfSelfObject(@Nonnull EntitySchemaContract entitySchema) {
		final String objectName = HierarchyOfSelfDescriptor.THIS.name(entitySchema, entitySchema);

		final GraphQLObjectType selfLevelInfoObject = buildSelfLevelInfoObject(entitySchema);
		final GraphQLObjectType selfParentInfoObject = buildSelfParentInfoObject(entitySchema);

		// todo lho just a prototype
		final RequireConstraintSchemaBuilder requireConstraintSchemaBuilder = new RequireConstraintSchemaBuilder(constraintContext, new AtomicReference<>(filterConstraintSchemaBuilder));
		final GraphQLInputType stopAt = requireConstraintSchemaBuilder.build(
			new HierarchyDataLocator(entitySchema.getName(), null),
			ConstraintDescriptorProvider.getConstraint(HierarchyStopAt.class)
		);

		return HierarchyOfSelfDescriptor.THIS
			.to(objectBuilderTransformer)
			.name(objectName)
			// todo lho dont know about resolvers, maybe we will need `registerFieldToObject` to properly transform tree to flat list
			.field(HierarchyOfSelfDescriptor.FROM_ROOT
				.to(fieldBuilderTransformer)
				.type(nonNull(list(nonNull(selfLevelInfoObject))))
				.argument(a -> a.name("stopAt").type(stopAt)))
			.field(HierarchyOfSelfDescriptor.FROM_NODE
				.to(fieldBuilderTransformer)
				.type(nonNull(list(nonNull(selfLevelInfoObject)))))
			.field(HierarchyOfSelfDescriptor.CHILDREN
				.to(fieldBuilderTransformer)
				.type(nonNull(list(nonNull(selfLevelInfoObject)))))
			.field(HierarchyOfSelfDescriptor.PARENTS
				.to(fieldBuilderTransformer)
				.type(nonNull(list(nonNull(selfParentInfoObject)))))
			.field(HierarchyOfSelfDescriptor.SIBLINGS
				.to(fieldBuilderTransformer)
				.type(nonNull(list(nonNull(selfLevelInfoObject)))))
			.build();
	}

	@Nonnull
	private GraphQLObjectType buildSelfLevelInfoObject(@Nonnull EntitySchemaContract entitySchema) {
		final String objectName = LevelInfoDescriptor.THIS.name(entitySchema, entitySchema);

		final GraphQLObjectType.Builder selfLevelInfoObjectBuilder = LevelInfoDescriptor.THIS
			.to(objectBuilderTransformer)
			.name(objectName);

		buildingContext.registerFieldToObject(
			objectName,
			selfLevelInfoObjectBuilder,
			buildSelfLevelInfoEntityField(entitySchema)
		);

		return selfLevelInfoObjectBuilder.build();
	}

	@Nonnull
	private BuiltFieldDescriptor buildSelfLevelInfoEntityField(@Nonnull EntitySchemaContract entitySchema) {
		final String referencedEntityObjectName = entitySchema.getNameVariant(TYPE_NAME_NAMING_CONVENTION);

		final GraphQLFieldDefinition entityField = LevelInfoDescriptor.ENTITY
			.to(fieldBuilderTransformer)
			.type(nonNull(typeRef(referencedEntityObjectName)))
			.build();

		return new BuiltFieldDescriptor(
			entityField,
			null
		);
	}

	@Nonnull
	private GraphQLObjectType buildSelfParentInfoObject(@Nonnull EntitySchemaContract entitySchema) {
		final String objectName = ParentInfoDescriptor.THIS.name(entitySchema, entitySchema);

		final GraphQLObjectType.Builder selfParentInfoObjectBuilder = ParentInfoDescriptor.THIS
			.to(objectBuilderTransformer)
			.name(objectName)
			.field(ParentInfoDescriptor.SIBLINGS
				.to(fieldBuilderTransformer)
				.type(nonNull(list(nonNull(typeRef(LevelInfoDescriptor.THIS.name(entitySchema, entitySchema)))))));

		buildingContext.registerFieldToObject(
			objectName,
			selfParentInfoObjectBuilder,
			buildSelfLevelInfoEntityField(entitySchema)
		);

		return selfParentInfoObjectBuilder.build();
	}

	@Nonnull
	private BuiltFieldDescriptor buildHierarchyOfReferenceField(@Nonnull EntitySchemaContract entitySchema,
	                                                            @Nonnull ReferenceSchemaContract referenceSchema) {
		final GraphQLObjectType hierarchyOfReferenceObject = buildHierarchyOfReferenceObject(entitySchema, referenceSchema);
		final GraphQLFieldDefinition hierarchyOfReferenceField = HierarchyDescriptor.REFERENCE
			.to(fieldBuilderTransformer)
			.name(referenceSchema.getNameVariant(PROPERTY_NAME_NAMING_CONVENTION))
			.description(HierarchyDescriptor.REFERENCE.description(referenceSchema.getReferencedEntityType()))
			.type(nonNull(hierarchyOfReferenceObject))
			.build();
		return new BuiltFieldDescriptor(hierarchyOfReferenceField, null);
	}

	@Nonnull
	private GraphQLObjectType buildHierarchyOfReferenceObject(@Nonnull EntitySchemaContract entitySchema,
	                                                          @Nonnull ReferenceSchemaContract referenceSchema) {
		final String objectName = HierarchyOfReferenceDescriptor.THIS.name(entitySchema, referenceSchema);

		final GraphQLObjectType levelInfoObject = buildLevelInfoObject(entitySchema, referenceSchema);
		final GraphQLObjectType parentInfoObject = buildParentInfoObject(entitySchema, referenceSchema);

		// todo lho just a prototype
		final GraphQLInputType stopAt = new RequireConstraintSchemaBuilder(constraintContext, new AtomicReference<>(filterConstraintSchemaBuilder)).build(
			new HierarchyDataLocator(entitySchema.getName(), referenceSchema.getName()),
			ConstraintDescriptorProvider.getConstraint(HierarchyStopAt.class)
		);

		return HierarchyOfSelfDescriptor.THIS
			.to(objectBuilderTransformer)
			.name(objectName)
			// todo lho dont know about resolvers, maybe we will need `registerFieldToObject` to properly transform tree to flat list
			.field(HierarchyOfReferenceDescriptor.FROM_ROOT
				.to(fieldBuilderTransformer)
				.type(nonNull(list(nonNull(levelInfoObject))))
				.argument(a -> a.name("stopAt").type(stopAt)))
			.field(HierarchyOfReferenceDescriptor.FROM_NODE
				.to(fieldBuilderTransformer)
				.type(nonNull(list(nonNull(levelInfoObject)))))
			.field(HierarchyOfReferenceDescriptor.CHILDREN
				.to(fieldBuilderTransformer)
				.type(nonNull(list(nonNull(levelInfoObject)))))
			.field(HierarchyOfReferenceDescriptor.PARENTS
				.to(fieldBuilderTransformer)
				.type(nonNull(list(nonNull(parentInfoObject)))))
			.field(HierarchyOfReferenceDescriptor.SIBLINGS
				.to(fieldBuilderTransformer)
				.type(nonNull(list(nonNull(levelInfoObject)))))
			.build();
	}

	@Nonnull
	private GraphQLObjectType buildLevelInfoObject(@Nonnull EntitySchemaContract entitySchema,
	                                               @Nonnull ReferenceSchemaContract referenceSchema) {
		final String objectName = LevelInfoDescriptor.THIS.name(entitySchema, referenceSchema);

		final GraphQLObjectType.Builder levelInfoObjectBuilder = LevelInfoDescriptor.THIS
			.to(objectBuilderTransformer)
			.name(objectName);

		buildingContext.registerFieldToObject(
			objectName,
			levelInfoObjectBuilder,
			buildLevelInfoEntityField(referenceSchema)
		);

		return levelInfoObjectBuilder.build();
	}

	@Nonnull
	private BuiltFieldDescriptor buildLevelInfoEntityField(@Nonnull ReferenceSchemaContract referenceSchema) {
		final EntitySchemaContract referencedEntitySchema = buildingContext
			.getSchema()
			.getEntitySchemaOrThrowException(referenceSchema.getReferencedEntityType());
		final String referencedEntityObjectName = referencedEntitySchema.getNameVariant(TYPE_NAME_NAMING_CONVENTION);

		final GraphQLFieldDefinition entityField = LevelInfoDescriptor.ENTITY
			.to(fieldBuilderTransformer)
			.type(nonNull(typeRef(referencedEntityObjectName)))
			.build();

		return new BuiltFieldDescriptor(
			entityField,
			null
		);
	}

	@Nonnull
	private GraphQLObjectType buildParentInfoObject(@Nonnull EntitySchemaContract entitySchema,
	                                                @Nonnull ReferenceSchemaContract referenceSchema) {
		final String objectName = ParentInfoDescriptor.THIS.name(entitySchema, referenceSchema);

		final GraphQLObjectType.Builder parentInfoObjectBuilder = ParentInfoDescriptor.THIS
			.to(objectBuilderTransformer)
			.name(objectName)
			.field(ParentInfoDescriptor.SIBLINGS
				.to(fieldBuilderTransformer)
				// todo lho dont i need custom data fetcher for flattening?
				.type(nonNull(list(nonNull(typeRef(LevelInfoDescriptor.THIS.name(entitySchema, referenceSchema)))))));

		buildingContext.registerFieldToObject(
			objectName,
			parentInfoObjectBuilder,
			buildLevelInfoEntityField(referenceSchema)
		);

		return parentInfoObjectBuilder.build();
	}

	@Nonnull
	private BuiltFieldDescriptor buildQueryTelemetryField() {
		return new BuiltFieldDescriptor(
			ExtraResultsDescriptor.QUERY_TELEMETRY
				.to(fieldBuilderTransformer)
				.type(nonNull(OBJECT)) // workaround because GQL doesn't support infinite recursive structures
				.argument(QueryTelemetryFieldHeaderDescriptor.FORMATTED.to(argumentBuilderTransformer))
				.build(),
			new QueryTelemetryDataFetcher(QUERY_TELEMETRY_OBJECT_MAPPER)
		);
	}

	@Nonnull
	private static GraphQLOutputType buildReferencedEntityObject(@Nullable EntitySchemaContract referencedEntitySchema) {
		if (referencedEntitySchema != null) {
			return typeRef(EntityDescriptor.THIS.name(referencedEntitySchema));
		} else {
			return typeRef(EntityDescriptor.THIS_ENTITY_REFERENCE.name());
		}
	}


	@Nonnull
	private GraphQLObjectType buildHistogramObject() {
		return HistogramDescriptor.THIS
			.to(objectBuilderTransformer)
			.field(HistogramDescriptor.BUCKETS
				.to(fieldBuilderTransformer)
				.argument(BucketsFieldHeaderDescriptor.REQUESTED_COUNT.to(argumentBuilderTransformer)))
			.build();
	}

	// todo lho: remove after https://gitlab.fg.cz/hv/evita/-/issues/120 is implemented
	@Nonnull
	private GraphQLObjectType buildAttributeNamedHistogramObject() {
		return HistogramDescriptor.THIS
			.to(objectBuilderTransformer)
			.name("AttributeNamedHistogram")
			.field(f -> f.name("attributeName").type(nonNull(STRING)))
			.field(HistogramDescriptor.BUCKETS
				.to(fieldBuilderTransformer)
				.argument(BucketsFieldHeaderDescriptor.REQUESTED_COUNT.to(argumentBuilderTransformer)))
			.build();
	}

	@Nonnull
	private GraphQLObjectType buildFacetRequestImpactObject() {
		// register custom data fetcher because of the request impact being java record
		buildingContext.registerDataFetcher(
			FacetRequestImpactDescriptor.THIS,
			FacetRequestImpactDescriptor.DIFFERENCE,
			PropertyDataFetcher.fetching(RequestImpact::difference)
		);
		buildingContext.registerDataFetcher(
			FacetRequestImpactDescriptor.THIS,
			FacetRequestImpactDescriptor.MATCH_COUNT,
			PropertyDataFetcher.fetching(RequestImpact::matchCount)
		);
		buildingContext.registerDataFetcher(
			FacetRequestImpactDescriptor.THIS,
			FacetRequestImpactDescriptor.HAS_SENSE,
			PropertyDataFetcher.fetching(RequestImpact::hasSense)
		);

		return FacetRequestImpactDescriptor.THIS
			.to(objectBuilderTransformer)
			.build();
	}
}
