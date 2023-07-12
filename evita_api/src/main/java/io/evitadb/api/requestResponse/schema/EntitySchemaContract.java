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

package io.evitadb.api.requestResponse.schema;

import io.evitadb.api.query.filter.EntityLocaleEquals;
import io.evitadb.api.query.filter.HierarchyWithin;
import io.evitadb.api.query.filter.PriceBetween;
import io.evitadb.api.query.filter.PriceInCurrency;
import io.evitadb.api.query.filter.PriceInPriceLists;
import io.evitadb.api.query.filter.PriceValidIn;
import io.evitadb.api.query.require.HierarchyOfSelf;
import io.evitadb.api.query.require.HierarchyParents;
import io.evitadb.api.query.require.PriceHistogram;
import io.evitadb.api.query.require.PriceType;
import io.evitadb.api.requestResponse.data.ContentComparator;
import io.evitadb.api.requestResponse.data.EntityContract;
import io.evitadb.api.requestResponse.data.PriceContract;
import io.evitadb.api.requestResponse.data.Versioned;
import io.evitadb.api.requestResponse.data.structure.Price;
import io.evitadb.api.requestResponse.data.structure.Prices;
import io.evitadb.utils.NamingConvention;

import javax.annotation.Nonnull;
import java.util.Currency;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * This is the definition object for {@link EntityContract}. Definition objects allow to describe the structure
 * of the entity type so that in any time everyone can consult complete structure of the entity type. Definition object
 * is similar to Java reflection process where you can also at any moment see which fields and methods are available
 * for the class.
 *
 * Based on our experience we've designed following data model for handling entities in evitaDB. Model is rather complex
 * but was designed to limit amount of data fetched from database and minimize an amount of data that are indexed and subject
 * to search.
 *
 * Minimal entity definition consists of:
 *
 * - entity type and
 * - primary key (even this is optional and may be autogenerated by the database).
 *
 * Other entity data is purely optional and may not be used at all.
 *
 * @author Jan Novotný (novotny@fg.cz), FG Forrest a.s. (c) 2021
 */
public interface EntitySchemaContract extends
	Versioned,
	NamedSchemaWithDeprecationContract,
	ContentComparator<EntitySchemaContract>,
	SortableAttributeCompoundSchemaProvider
{

	/**
	 * Returns version of this definition object and gets increased with any entity schema update. Allows to execute
	 * optimistic locking i.e. avoiding parallel modifications.
	 */
	@Override
	int getVersion();

	/**
	 * Method returns true if the schema contains only information about {@link #getName()} but nothing else. This signalizes
	 * the new schema without any setup.
	 */
	boolean isBlank();

	/**
	 * Returns TRUE when primary keys of entities of this type will not be provided by the external systems and Evita
	 * is responsible for generating unique primary keys for the entity on insertion.
	 *
	 * Generated key is guaranteed to be unique, but may not represent continuous ascending series. Generated key
	 * will be always greater than zero.
	 */
	boolean isWithGeneratedPrimaryKey();

	/**
	 * Returns TRUE when entities of this type are organized in a tree like structure (hierarchy) where certain entities
	 * are subordinate of other entities.
	 *
	 * Entities may be organized in hierarchical fashion. That means that entity may refer to single parent entity and may be
	 * referred by multiple child entities. Hierarchy is always composed of entities of same type.
	 * Each entity must be part of at most single hierarchy (tree).
	 *
	 * Hierarchy can limit returned entities by using filtering constraints {@link HierarchyWithin}. It's also used for
	 * computation of extra data - such as {@link HierarchyParents}. It can also invert type of returned entities in case requirement
	 * {@link HierarchyOfSelf} is used.
	 */
	boolean isWithHierarchy();

	/**
	 * Returns TRUE when entities of this type holds price information.
	 *
	 * Prices are specific to a very few entities, but because correct price computation is very complex in e-commerce
	 * systems and highly affects performance of the entities filtering and sorting, they deserve first class support
	 * in entity model. It is pretty common in B2B systems single product has assigned dozens of prices for the different
	 * customers.
	 *
	 * Specifying prices on entity allows usage of {@link PriceValidIn}, {@link PriceInCurrency}
	 * {@link PriceBetween}, and {@link PriceInPriceLists} filtering constraints and also {@link Price},
	 * ordering of the entities. Additional requirements
	 * {@link PriceHistogram}, {@link Prices}, {@link PriceType} can be used in query as well.
	 */
	boolean isWithPrice();

	/**
	 * Determines how many fractional places are important when entities are compared during filtering or sorting. It is
	 * important to know that all prices will be converted to {@link java.lang.Integer}, so any of the price values
	 * (either with or without tax) must not ever exceed maximum limits of {@link java.lang.Integer} type when scaling
	 * the number by the power of ten using `indexedPricePlaces` as exponent.
	 */
	int getIndexedPricePlaces();

	/**
	 * Returns set of all {@link Locale} that could be used for localized {@link AttributeSchemaContract} or {@link AssociatedDataSchemaContract}.
	 * Enables using {@link EntityLocaleEquals} filtering query in query.
	 */
	@Nonnull
	Set<Locale> getLocales();

	/**
	 * Returns true if schema supports locale in argument.
	 */
	default boolean supportsLocale(@Nonnull Locale locale) {
		return getLocales().contains(locale);
	}

	/**
	 * Returns set of all {@link Currency} that could be used for {@link PriceContract prices} in entities of this type.
	 */
	@Nonnull
	Set<Currency> getCurrencies();

	/**
	 * Returns true if schema supports currency in argument.
	 */
	default boolean supportsCurrency(@Nonnull Currency currency) {
		return getCurrencies().contains(currency);
	}

	/**
	 * Returns definition of the associated data of particular name or returns NULL.
	 */
	@Nonnull
	Optional<AssociatedDataSchemaContract> getAssociatedData(@Nonnull String dataName);

	/**
	 * Returns definition of the associated data of particular name or throws an exception.
	 */
	@Nonnull
	AssociatedDataSchemaContract getAssociatedDataOrThrowException(@Nonnull String dataName);

	/**
	 * Returns definition of the associated data of particular name in specific naming convention.
	 */
	@Nonnull
	Optional<AssociatedDataSchemaContract> getAssociatedDataByName(@Nonnull String dataName, @Nonnull NamingConvention namingConvention);

	/**
	 * Returns all definitions of the associated data indexed by their name.
	 */
	@Nonnull
	Map<String, AssociatedDataSchemaContract> getAssociatedData();

	/**
	 * Returns definition of the reference of passed name.
	 */
	@Nonnull
	default Optional<ReferenceSchemaContract> getReference(@Nonnull String referenceName) {
		return Optional.ofNullable(getReferences().get(referenceName));
	}

	/**
	 * Returns definition of the reference of particular name in specific naming convention.
	 */
	@Nonnull
	Optional<ReferenceSchemaContract> getReferenceByName(@Nonnull String referenceName, @Nonnull NamingConvention namingConvention);

	/**
	 * Returns all definitions of the references indexed by their name.
	 */
	@Nonnull
	Map<String, ReferenceSchemaContract> getReferences();

	/**
	 * Returns definition of the facet associated with passed entity type.
	 */
	@Nonnull
	ReferenceSchemaContract getReferenceOrThrowException(@Nonnull String referenceName);

	/**
	 * Returns set of allowed evolution modes. These allow to specify how strict is evitaDB when unknown information is
	 * presented to her for the first time. When no evolution mode is set, each violation of the {@link EntitySchemaContract} is
	 * reported by an exception. This behaviour can be changed by this evolution mode, however.
	 */
	@Nonnull
	Set<EvolutionMode> getEvolutionMode();

	/**
	 * Returns true if schema allows evolution in certain form.
	 */
	default boolean allows(@Nonnull EvolutionMode evolutionMode) {
		return getEvolutionMode().contains(evolutionMode);
	}

}
