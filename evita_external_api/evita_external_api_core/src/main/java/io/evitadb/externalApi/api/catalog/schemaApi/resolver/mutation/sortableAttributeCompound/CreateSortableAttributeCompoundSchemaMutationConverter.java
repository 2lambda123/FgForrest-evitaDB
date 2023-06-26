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

package io.evitadb.externalApi.api.catalog.schemaApi.resolver.mutation.sortableAttributeCompound;

import io.evitadb.api.requestResponse.schema.SortableAttributeCompoundSchemaContract.AttributeElement;
import io.evitadb.api.requestResponse.schema.mutation.sortableAttributeCompound.CreateSortableAttributeCompoundSchemaMutation;
import io.evitadb.externalApi.api.catalog.resolver.mutation.InputMutation;
import io.evitadb.externalApi.api.catalog.resolver.mutation.MutationObjectParser;
import io.evitadb.externalApi.api.catalog.resolver.mutation.MutationResolvingExceptionFactory;
import io.evitadb.externalApi.api.catalog.schemaApi.model.AttributeElementDescriptor;
import io.evitadb.externalApi.api.catalog.schemaApi.model.mutation.sortableAttributeCompound.CreateSortableAttributeCompoundSchemaMutationDescriptor;
import io.evitadb.externalApi.api.catalog.schemaApi.model.mutation.sortableAttributeCompound.SortableAttributeCompoundSchemaMutationDescriptor;
import io.evitadb.externalApi.api.catalog.schemaApi.resolver.mutation.SchemaMutationConverter;
import io.evitadb.utils.Assert;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link SchemaMutationConverter} for resolving {@link CreateSortableAttributeCompoundSchemaMutation}.
 *
 * @author Lukáš Hornych, FG Forrest a.s. (c) 2023
 */
public class CreateSortableAttributeCompoundSchemaMutationConverter extends SortableAttributeCompoundSchemaMutationConverter<CreateSortableAttributeCompoundSchemaMutation> {

	public CreateSortableAttributeCompoundSchemaMutationConverter(@Nonnull MutationObjectParser objectParser,
	                                                              @Nonnull MutationResolvingExceptionFactory exceptionFactory) {
		super(objectParser, exceptionFactory);
	}

	@Nonnull
	@Override
	protected String getMutationName() {
		return CreateSortableAttributeCompoundSchemaMutationDescriptor.THIS.name();
	}

	@Nonnull
	@Override
	protected CreateSortableAttributeCompoundSchemaMutation convert(@Nonnull InputMutation inputMutation) {
		/* TODO LHO - add attributeElements here */
		final AttributeElement[] attributeElements = inputMutation.getRequiredField(
			CreateSortableAttributeCompoundSchemaMutationDescriptor.ATTRIBUTE_ELEMENTS.name(),
			rawField -> {
				Assert.isTrue(
					rawField instanceof List<?>,
					() -> getExceptionFactory().createInvalidArgumentException("Field `" + CreateSortableAttributeCompoundSchemaMutationDescriptor.ATTRIBUTE_ELEMENTS.name() + "` of mutation `" + getMutationName() + "` is expected to be an array.")
				);
				//noinspection unchecked
				final List<Object> rawElements = (List<Object>) rawField;
				return rawElements.stream()
					.map(rawElement -> {
						Assert.isTrue(
							rawElement instanceof Map<?,?>,
							() -> getExceptionFactory().createInvalidArgumentException("Item in field `" + CreateSortableAttributeCompoundSchemaMutationDescriptor.ATTRIBUTE_ELEMENTS.name() + "` of mutation `" + getMutationName() + "` is expected to be an object.")
						);
						//noinspection unchecked
						final Map<String, Object> element = (Map<String, Object>) rawElement;

						final InputMutation elementInput = new InputMutation(getMutationName(), element, getExceptionFactory());
						return new AttributeElement(
							elementInput.getRequiredField(AttributeElementDescriptor.ATTRIBUTE_NAME),
							elementInput.getRequiredField(AttributeElementDescriptor.DIRECTION),
							elementInput.getRequiredField(AttributeElementDescriptor.BEHAVIOUR)
						);
					})
					.toArray(AttributeElement[]::new);
			}
		);

		return new CreateSortableAttributeCompoundSchemaMutation(
			inputMutation.getRequiredField(SortableAttributeCompoundSchemaMutationDescriptor.NAME),
			inputMutation.getOptionalField(CreateSortableAttributeCompoundSchemaMutationDescriptor.DESCRIPTION),
			inputMutation.getOptionalField(CreateSortableAttributeCompoundSchemaMutationDescriptor.DEPRECATION_NOTICE),
			attributeElements
		);
	}
}
