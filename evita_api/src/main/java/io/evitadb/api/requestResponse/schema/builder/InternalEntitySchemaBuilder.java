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

package io.evitadb.api.requestResponse.schema.builder;

import io.evitadb.api.exception.AssociatedDataAlreadyPresentInEntitySchemaException;
import io.evitadb.api.exception.AttributeAlreadyPresentInCatalogSchemaException;
import io.evitadb.api.exception.InvalidSchemaMutationException;
import io.evitadb.api.exception.ReferenceAlreadyPresentInEntitySchemaException;
import io.evitadb.api.requestResponse.schema.*;
import io.evitadb.api.requestResponse.schema.EntitySchemaEditor.EntitySchemaBuilder;
import io.evitadb.api.requestResponse.schema.mutation.EntitySchemaMutation;
import io.evitadb.api.requestResponse.schema.mutation.ReferenceSchemaMutation;
import io.evitadb.api.requestResponse.schema.mutation.associatedData.RemoveAssociatedDataSchemaMutation;
import io.evitadb.api.requestResponse.schema.mutation.attribute.RemoveAttributeSchemaMutation;
import io.evitadb.api.requestResponse.schema.mutation.attribute.UseGlobalAttributeSchemaMutation;
import io.evitadb.api.requestResponse.schema.mutation.catalog.ModifyEntitySchemaMutation;
import io.evitadb.api.requestResponse.schema.mutation.entity.*;
import io.evitadb.api.requestResponse.schema.mutation.reference.RemoveReferenceSchemaMutation;
import io.evitadb.dataType.ClassifierType;
import io.evitadb.exception.EvitaInternalError;
import io.evitadb.utils.Assert;
import io.evitadb.utils.ClassifierUtils;
import io.evitadb.utils.NamingConvention;
import lombok.experimental.Delegate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

/**
 * Internal implementation of the {@link EntitySchemaBuilder} builder returned by {@link SealedEntitySchema} when
 * {@link SealedEntitySchema#openForWrite()} is called. This implementation does the heavy lifting of this particular
 * interface. The class should never be instantiated by the client code.
 *
 * @author Jan Novotný (novotny@fg.cz), FG Forrest a.s. (c) 2022
 */
public final class InternalEntitySchemaBuilder implements EntitySchemaBuilder, InternalSchemaBuilderHelper {
	@Serial private static final long serialVersionUID = -2643204562100111998L;
	private static final EntitySchemaMutation[] EMPTY_ARRAY = new EntitySchemaMutation[0];

	private final EntitySchemaContract baseSchema;
	private final List<EntitySchemaMutation> mutations = new LinkedList<>();
	private Supplier<CatalogSchemaContract> catalogSchemaAccessor;
	private boolean updatedSchemaDirty;
	private EntitySchemaContract updatedSchema;

	public InternalEntitySchemaBuilder(
		@Nonnull CatalogSchemaContract catalogSchema,
		@Nonnull EntitySchemaContract baseSchema,
		@Nonnull Collection<EntitySchemaMutation> schemaMutations
	) {
		this.catalogSchemaAccessor = () -> catalogSchema;
		this.baseSchema = baseSchema;
		this.updatedSchemaDirty = addMutations(
			this.catalogSchemaAccessor.get(), this.baseSchema, this.mutations,
			schemaMutations.toArray(EMPTY_ARRAY)
		);
	}

	public InternalEntitySchemaBuilder(
		@Nonnull CatalogSchemaContract catalogSchema,
		@Nonnull EntitySchemaContract baseSchema
	) {
		this(catalogSchema, baseSchema, Collections.emptyList());
	}

	@Nonnull
	@Override
	public EntitySchemaBuilder cooperatingWith(@Nonnull Supplier<CatalogSchemaContract> catalogSupplier) {
		catalogSchemaAccessor = catalogSupplier;
		return this;
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder verifySchemaStrictly() {
		this.updatedSchemaDirty = addMutations(
			this.catalogSchemaAccessor.get(), this.baseSchema, this.mutations,
			new DisallowEvolutionModeInEntitySchemaMutation(EvolutionMode.values())
		);
		return this;
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder verifySchemaButAllow(@Nonnull EvolutionMode... evolutionMode) {
		this.updatedSchemaDirty = addMutations(
			this.catalogSchemaAccessor.get(), this.baseSchema, this.mutations,
			new DisallowEvolutionModeInEntitySchemaMutation(EvolutionMode.values()),
			new AllowEvolutionModeInEntitySchemaMutation(evolutionMode)
		);
		return this;
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder verifySchemaButCreateOnTheFly() {
		this.updatedSchemaDirty = addMutations(
			this.catalogSchemaAccessor.get(), this.baseSchema, this.mutations,
			new AllowEvolutionModeInEntitySchemaMutation(EvolutionMode.values())
		);
		return this;
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder withDescription(@Nullable String description) {
		this.updatedSchemaDirty = addMutations(
			this.catalogSchemaAccessor.get(), this.baseSchema, this.mutations,
			new ModifyEntitySchemaDescriptionMutation(description)
		);
		return this;
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder deprecated(@Nonnull String deprecationNotice) {
		this.updatedSchemaDirty = addMutations(
			this.catalogSchemaAccessor.get(), this.baseSchema, this.mutations,
			new ModifyEntitySchemaDeprecationNoticeMutation(deprecationNotice)
		);
		return this;
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder notDeprecatedAnymore() {
		this.updatedSchemaDirty = addMutations(
			this.catalogSchemaAccessor.get(), this.baseSchema, this.mutations,
			new ModifyEntitySchemaDeprecationNoticeMutation(null)
		);
		return this;
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder withGeneratedPrimaryKey() {
		this.updatedSchemaDirty = addMutations(
			this.catalogSchemaAccessor.get(), this.baseSchema, this.mutations,
			new SetEntitySchemaWithGeneratedPrimaryKeyMutation(true)
		);
		return this;
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder withoutGeneratedPrimaryKey() {
		this.updatedSchemaDirty = addMutations(
			this.catalogSchemaAccessor.get(), this.baseSchema, this.mutations,
			new SetEntitySchemaWithGeneratedPrimaryKeyMutation(false)
		);
		return this;
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder withHierarchy() {
		this.updatedSchemaDirty = addMutations(
			this.catalogSchemaAccessor.get(), this.baseSchema, this.mutations,
			new SetEntitySchemaWithHierarchyMutation(true)
		);
		return this;
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder withoutHierarchy() {
		this.updatedSchemaDirty = addMutations(
			this.catalogSchemaAccessor.get(), this.baseSchema, this.mutations,
			new SetEntitySchemaWithHierarchyMutation(false)
		);
		return this;
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder withPrice() {
		this.updatedSchemaDirty = addMutations(
			this.catalogSchemaAccessor.get(), this.baseSchema, this.mutations,
			new SetEntitySchemaWithPriceMutation(true, 2)
		);
		return this;
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder withPrice(int indexedDecimalPlaces) {
		this.updatedSchemaDirty = addMutations(
			this.catalogSchemaAccessor.get(), this.baseSchema, this.mutations,
			new SetEntitySchemaWithPriceMutation(true, indexedDecimalPlaces)
		);
		return this;
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder withPriceInCurrency(@Nonnull Currency... currency) {
		return withPriceInCurrency(2, currency);
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder withPriceInCurrency(int indexedPricePlaces, @Nonnull Currency... currency) {
		this.updatedSchemaDirty = addMutations(
			this.catalogSchemaAccessor.get(), this.baseSchema, this.mutations,
			new SetEntitySchemaWithPriceMutation(true, indexedPricePlaces),
			new AllowCurrencyInEntitySchemaMutation(currency)
		);
		return this;
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder withoutPrice() {
		this.updatedSchemaDirty = addMutations(
			this.catalogSchemaAccessor.get(), this.baseSchema, this.mutations,
			new SetEntitySchemaWithPriceMutation(false, 0)
		);
		return this;
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder withoutPriceInCurrency(@Nonnull Currency currency) {
		this.updatedSchemaDirty = addMutations(
			this.catalogSchemaAccessor.get(), this.baseSchema, this.mutations,
			new DisallowCurrencyInEntitySchemaMutation(currency)
		);
		return this;
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder withLocale(@Nonnull Locale... locale) {
		this.updatedSchemaDirty = addMutations(
			this.catalogSchemaAccessor.get(), this.baseSchema, this.mutations,
			new AllowLocaleInEntitySchemaMutation(locale)
		);
		return this;
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder withoutLocale(@Nonnull Locale locale) {
		this.updatedSchemaDirty = addMutations(
			this.catalogSchemaAccessor.get(), this.baseSchema, this.mutations,
			new DisallowLocaleInEntitySchemaMutation(locale)
		);
		return this;
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder withGlobalAttribute(@Nonnull String attributeName) {
		this.updatedSchemaDirty = addMutations(
			this.catalogSchemaAccessor.get(), this.baseSchema, this.mutations,
			new UseGlobalAttributeSchemaMutation(attributeName)
		);
		return this;
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder withAttribute(@Nonnull String attributeName, @Nonnull Class<? extends Serializable> ofType) {
		return withAttribute(attributeName, ofType, null);
	}

	@Nonnull
	@Override
	public EntitySchemaBuilder withAttribute(
		@Nonnull String attributeName,
		@Nonnull Class<? extends Serializable> ofType,
		@Nullable Consumer<AttributeSchemaEditor.AttributeSchemaBuilder> whichIs
	) {
		final CatalogSchemaContract catalogSchema = catalogSchemaAccessor.get();
		catalogSchema.getAttribute(attributeName)
			.ifPresent(it -> {
				throw new AttributeAlreadyPresentInCatalogSchemaException(
					catalogSchema.getName(), Objects.requireNonNull(it)
				);
			});
		final Optional<AttributeSchemaContract> existingAttribute = getAttribute(attributeName);
		final AttributeSchemaBuilder attributeSchemaBuilder =
			existingAttribute
				.map(it -> {
					Assert.isTrue(
						ofType.equals(it.getType()),
						() -> new InvalidSchemaMutationException(
							"Attribute " + attributeName + " has already assigned type " + it.getType() +
								", cannot change this type to: " + ofType + "!"
						)
					);
					return new AttributeSchemaBuilder(baseSchema, it);
				})
				.orElseGet(() -> new AttributeSchemaBuilder(baseSchema, attributeName, ofType));

		ofNullable(whichIs).ifPresent(it -> it.accept(attributeSchemaBuilder));
		final AttributeSchemaContract attributeSchema = attributeSchemaBuilder.toInstance();
		checkSortableTraits(attributeName, attributeSchema);

		// check the names in all naming conventions are unique in the catalog schema
		checkNamesAreUniqueInAllNamingConventions(this.getAttributes().values(), attributeSchema);

		if (existingAttribute.map(it -> !it.equals(attributeSchema)).orElse(true)) {
			this.updatedSchemaDirty = addMutations(
				catalogSchema, this.baseSchema, this.mutations,
				attributeSchemaBuilder.toMutation().toArray(EMPTY_ARRAY)
			);
		}
		return this;
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder withoutAttribute(@Nonnull String attributeName) {
		this.updatedSchemaDirty = addMutations(
			this.catalogSchemaAccessor.get(), this.baseSchema, this.mutations,
			new RemoveAttributeSchemaMutation(attributeName)
		);
		return this;
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder withAssociatedData(@Nonnull String dataName, @Nonnull Class<? extends Serializable> ofType) {
		return withAssociatedData(dataName, ofType, null);
	}

	@Nonnull
	@Override
	public EntitySchemaBuilder withAssociatedData(
		@Nonnull String dataName,
		@Nonnull Class<? extends Serializable> ofType,
		@Nullable Consumer<AssociatedDataSchemaEditor> whichIs
	) {
		final Optional<AssociatedDataSchemaContract> existingAssociatedData = getAssociatedData(dataName);
		final CatalogSchemaContract catalogSchema = catalogSchemaAccessor.get();
		final AssociatedDataSchemaBuilder associatedDataSchemaBuilder = existingAssociatedData
			.map(it -> {
				Assert.isTrue(
					ofType.equals(it.getType()),
					() -> new InvalidSchemaMutationException(
						"Associated data " + dataName + " has already assigned type " + it.getType() +
							", cannot change this type to: " + ofType + "!"
					)
				);
				return new AssociatedDataSchemaBuilder(catalogSchema, baseSchema, it);
			})
			.orElseGet(() -> new AssociatedDataSchemaBuilder(catalogSchema, baseSchema, dataName, ofType));

		ofNullable(whichIs).ifPresent(it -> it.accept(associatedDataSchemaBuilder));
		final AssociatedDataSchemaContract associatedDataSchema = associatedDataSchemaBuilder.toInstance();

		if (existingAssociatedData.map(it -> !it.equals(associatedDataSchema)).orElse(true)) {
			ClassifierUtils.validateClassifierFormat(ClassifierType.ASSOCIATED_DATA, dataName);
			// check the names in all naming conventions are unique in the entity schema
			getAssociatedData()
				.values()
				.stream()
				.filter(it -> !Objects.equals(it.getName(), associatedDataSchema.getName()))
				.flatMap(it -> it.getNameVariants()
					.entrySet()
					.stream()
					.filter(nameVariant -> nameVariant.getValue().equals(associatedDataSchema.getNameVariant(nameVariant.getKey())))
					.map(nameVariant -> new AssociatedDataNamingConventionConflict(it, nameVariant.getKey(), nameVariant.getValue()))
				)
				.forEach(conflict -> {
					throw new AssociatedDataAlreadyPresentInEntitySchemaException(
						conflict.conflictingSchema(), associatedDataSchema,
						conflict.convention(), conflict.conflictingName()
					);
				});
			this.updatedSchemaDirty = addMutations(
				catalogSchema, this.baseSchema, this.mutations,
				associatedDataSchemaBuilder.toMutation().toArray(EMPTY_ARRAY)
			);
		}
		return this;
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder withoutAssociatedData(@Nonnull String dataName) {
		this.updatedSchemaDirty = addMutations(
			this.catalogSchemaAccessor.get(), this.baseSchema, this.mutations,
			new RemoveAssociatedDataSchemaMutation(dataName)
		);
		return this;
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder withReferenceTo(@Nonnull String name, @Nonnull String externalEntityType, @Nonnull Cardinality cardinality) {
		return withReferenceTo(name, externalEntityType, cardinality, null);
	}

	@Nonnull
	@Override
	public EntitySchemaBuilder withReferenceTo(
		@Nonnull String name,
		@Nonnull String externalEntityType,
		@Nonnull Cardinality cardinality,
		@Nullable Consumer<ReferenceSchemaEditor.ReferenceSchemaBuilder> whichIs
	) {
		final EntitySchemaContract currentSchema = toInstance();
		final ReferenceSchemaContract existingReference = currentSchema.getReference(name).orElse(null);
		final ReferenceSchemaBuilder referenceBuilder = new ReferenceSchemaBuilder(
			catalogSchemaAccessor.get(),
			baseSchema,
			existingReference,
			name,
			externalEntityType,
			false,
			cardinality,
			this.mutations,
			this.baseSchema.getReference(name).isEmpty()
		);
		ofNullable(whichIs).ifPresent(it -> it.accept(referenceBuilder));
		redefineReferenceType(
			referenceBuilder,
			existingReference
		);
		return this;
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder withReferenceToEntity(@Nonnull String name, @Nonnull String entityType, @Nonnull Cardinality cardinality) {
		return withReferenceToEntity(name, entityType, cardinality, null);
	}

	@Nonnull
	@Override
	public EntitySchemaBuilder withReferenceToEntity(
		@Nonnull String name,
		@Nonnull String entityType,
		@Nonnull Cardinality cardinality,
		@Nullable Consumer<ReferenceSchemaEditor.ReferenceSchemaBuilder> whichIs
	) {
		final EntitySchemaContract currentSchema = toInstance();
		final ReferenceSchemaContract existingReference = currentSchema.getReference(name).orElse(null);
		final ReferenceSchemaBuilder referenceSchemaBuilder = new ReferenceSchemaBuilder(
			catalogSchemaAccessor.get(),
			baseSchema,
			existingReference,
			name,
			entityType,
			true,
			cardinality,
			this.mutations,
			this.baseSchema.getReference(name).isEmpty()
		);
		ofNullable(whichIs).ifPresent(it -> it.accept(referenceSchemaBuilder));
		redefineReferenceType(
			referenceSchemaBuilder,
			existingReference
		);
		return this;
	}

	@Override
	@Nonnull
	public EntitySchemaBuilder withoutReferenceTo(@Nonnull String name) {
		this.updatedSchemaDirty = addMutations(
			this.catalogSchemaAccessor.get(), this.baseSchema, this.mutations,
			new RemoveReferenceSchemaMutation(name)
		);
		return this;
	}

	@Nonnull
	@Override
	public Optional<ModifyEntitySchemaMutation> toMutation() {
		return this.mutations.isEmpty() ?
			empty() :
			of(new ModifyEntitySchemaMutation(getName(), this.mutations.toArray(EMPTY_ARRAY)));
	}

	@Nonnull
	@Delegate(types = EntitySchemaContract.class)
	@Override
	public EntitySchemaContract toInstance() {
		if (this.updatedSchema == null || this.updatedSchemaDirty) {
			EntitySchemaContract currentSchema = this.baseSchema;
			for (EntitySchemaMutation mutation : this.mutations) {
				currentSchema = mutation.mutate(catalogSchemaAccessor.get(), currentSchema);
				if (currentSchema == null) {
					throw new EvitaInternalError("Catalog schema unexpectedly removed from inside!");
				}
			}
			this.updatedSchema = currentSchema;
			this.updatedSchemaDirty = false;
		}
		return this.updatedSchema;
	}

	void redefineReferenceType(
		@Nonnull ReferenceSchemaBuilder referenceSchemaBuilder,
		@Nullable ReferenceSchemaContract existingReference
	) {
		final ReferenceSchemaContract newReference = referenceSchemaBuilder.toInstance();
		if (!Objects.equals(existingReference, newReference)) {
			// remove all existing mutations for the reference schema (it needs to be replaced)
			this.mutations
				.removeIf(it -> it instanceof ReferenceSchemaMutation referenceSchemaMutation && referenceSchemaMutation.getName().equals(newReference.getName()));
			// check the names in all naming conventions are unique in the entity schema
			toInstance()
				.getReferences()
				.values()
				.stream()
				.filter(it -> !Objects.equals(it.getName(), referenceSchemaBuilder.getName()))
				.flatMap(it -> it.getNameVariants()
					.entrySet()
					.stream()
					.filter(nameVariant -> nameVariant.getValue().equals(referenceSchemaBuilder.getNameVariant(nameVariant.getKey())))
					.map(nameVariant -> new ReferenceNamingConventionConflict(it, nameVariant.getKey(), nameVariant.getValue()))
				)
				.forEach(conflict -> {
					throw new ReferenceAlreadyPresentInEntitySchemaException(
						conflict.conflictingSchema(), referenceSchemaBuilder,
						conflict.convention(), conflict.conflictingName()
					);
				});
			this.updatedSchemaDirty = addMutations(
				this.catalogSchemaAccessor.get(), this.baseSchema, this.mutations,
				referenceSchemaBuilder.toMutation().toArray(EMPTY_ARRAY)
			);
		}
	}

	/**
	 * DTO for passing the identified conflict in attribute names for certain naming convention.
	 */
	record AttributeNamingConventionConflict(
		@Nonnull AttributeSchemaContract conflictingSchema,
		@Nonnull NamingConvention convention,
		@Nonnull String conflictingName
	) {
	}

	/**
	 * DTO for passing the identified conflict in associated data names for certain naming convention.
	 */
	record AssociatedDataNamingConventionConflict(
		@Nonnull AssociatedDataSchemaContract conflictingSchema,
		@Nonnull NamingConvention convention,
		@Nonnull String conflictingName
	) {
	}

	/**
	 * DTO for passing the identified conflict in reference names for certain naming convention.
	 */
	record ReferenceNamingConventionConflict(
		@Nonnull ReferenceSchemaContract conflictingSchema,
		@Nonnull NamingConvention convention,
		@Nonnull String conflictingName
	) {
	}

}
