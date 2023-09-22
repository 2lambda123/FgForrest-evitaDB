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

package io.evitadb.externalApi.api.catalog.schemaApi.resolver.mutation.catalog;

import io.evitadb.api.requestResponse.schema.mutation.LocalCatalogSchemaMutation;
import io.evitadb.api.requestResponse.schema.mutation.catalog.ModifyCatalogSchemaMutation;
import io.evitadb.externalApi.api.catalog.resolver.mutation.Input;
import io.evitadb.externalApi.api.catalog.resolver.mutation.MutationObjectParser;
import io.evitadb.externalApi.api.catalog.resolver.mutation.MutationResolvingExceptionFactory;
import io.evitadb.externalApi.api.catalog.resolver.mutation.Output;
import io.evitadb.externalApi.api.catalog.schemaApi.model.mutation.catalog.ModifyCatalogSchemaMutationDescriptor;
import io.evitadb.externalApi.api.catalog.schemaApi.resolver.mutation.LocalCatalogSchemaMutationAggregateConverter;
import io.evitadb.utils.Assert;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

/**
 * TODO lho docs
 *
 * @author Lukáš Hornych, FG Forrest a.s. (c) 2023
 */
public class ModifyCatalogSchemaMutationConverter extends TopLevelCatalogSchemaMutationConverter<ModifyCatalogSchemaMutation> {

	@Nonnull
	private final LocalCatalogSchemaMutationAggregateConverter localCatalogSchemaMutationAggregateConverter;

	public ModifyCatalogSchemaMutationConverter(@Nonnull MutationObjectParser objectParser,
	                                            @Nonnull MutationResolvingExceptionFactory exceptionFactory) {
		super(objectParser, exceptionFactory);
		this.localCatalogSchemaMutationAggregateConverter = new LocalCatalogSchemaMutationAggregateConverter(objectParser, exceptionFactory);
	}

	@Nonnull
	@Override
	protected Class<ModifyCatalogSchemaMutation> getMutationClass() {
		return ModifyCatalogSchemaMutation.class;
	}

	@Nonnull
	@Override
	protected ModifyCatalogSchemaMutation convertFromInput(@Nonnull Input input) {
		final List<Object> inputEntitySchemaMutations = Optional.of(input.getRequiredProperty(ModifyCatalogSchemaMutationDescriptor.SCHEMA_MUTATIONS.name()))
			.map(m -> {
				Assert.isTrue(
					m instanceof List<?>,
					() -> getExceptionFactory().createInvalidArgumentException("Field `" + ModifyCatalogSchemaMutationDescriptor.SCHEMA_MUTATIONS.name() + "` of mutation `" + getMutationName() + "` is expected to be a list.")
				);
				//noinspection unchecked
				return (List<Object>) m;
			})
			.get();
		final LocalCatalogSchemaMutation[] localCatalogSchemaMutations = inputEntitySchemaMutations.stream()
			.flatMap(m -> localCatalogSchemaMutationAggregateConverter.convertFromInput(m).stream())
			.toArray(LocalCatalogSchemaMutation[]::new);

		return new ModifyCatalogSchemaMutation(
			input.getProperty(ModifyCatalogSchemaMutationDescriptor.CATALOG_NAME),
			localCatalogSchemaMutations
		);
	}

	@Override
	protected void convertToOutput(@Nonnull ModifyCatalogSchemaMutation mutation, @Nonnull Output output) {
		output.setProperty(
			ModifyCatalogSchemaMutationDescriptor.SCHEMA_MUTATIONS,
			localCatalogSchemaMutationAggregateConverter.convertToOutput(mutation.getSchemaMutations())
		);
		super.convertToOutput(mutation, output);
	}
}
