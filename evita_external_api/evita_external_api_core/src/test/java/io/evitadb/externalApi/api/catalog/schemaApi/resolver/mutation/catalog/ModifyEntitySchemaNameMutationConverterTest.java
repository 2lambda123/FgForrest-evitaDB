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

import io.evitadb.api.requestResponse.schema.mutation.catalog.ModifyEntitySchemaNameMutation;
import io.evitadb.exception.EvitaInvalidUsageException;
import io.evitadb.externalApi.api.catalog.mutation.TestMutationResolvingExceptionFactory;
import io.evitadb.externalApi.api.catalog.resolver.mutation.PassThroughMutationObjectParser;
import io.evitadb.externalApi.api.catalog.schemaApi.model.mutation.catalog.ModifyEntitySchemaNameMutationDescriptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.evitadb.test.builder.MapBuilder.map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for {@link ModifyEntitySchemaNameMutationConverter}
 *
 * @author Lukáš Hornych, FG Forrest a.s. (c) 2023
 */
class ModifyEntitySchemaNameMutationConverterTest {

	private ModifyEntitySchemaNameMutationConverter converter;

	@BeforeEach
	void init() {
		converter = new ModifyEntitySchemaNameMutationConverter(new PassThroughMutationObjectParser(), new TestMutationResolvingExceptionFactory());
	}

	@Test
	void shouldResolveInputToLocalMutation() {
		final ModifyEntitySchemaNameMutation expectedMutation = new ModifyEntitySchemaNameMutation(
			"product",
			"category",
			true
		);

		final ModifyEntitySchemaNameMutation convertedMutation1 = converter.convert(
			map()
				.e(ModifyEntitySchemaNameMutationDescriptor.NAME.name(), "product")
				.e(ModifyEntitySchemaNameMutationDescriptor.NEW_NAME.name(), "category")
				.e(ModifyEntitySchemaNameMutationDescriptor.OVERWRITE_TARGET.name(), true)
				.build()
		);
		assertEquals(expectedMutation, convertedMutation1);

		final ModifyEntitySchemaNameMutation convertedMutation2 = converter.convert(
			map()
				.e(ModifyEntitySchemaNameMutationDescriptor.NAME.name(), "product")
				.e(ModifyEntitySchemaNameMutationDescriptor.NEW_NAME.name(), "category")
				.e(ModifyEntitySchemaNameMutationDescriptor.OVERWRITE_TARGET.name(), "true")
				.build()
		);
		assertEquals(expectedMutation, convertedMutation2);
	}

	@Test
	void shouldNotResolveInputWhenMissingRequiredData() {
		assertThrows(
			EvitaInvalidUsageException.class,
			() -> converter.convert(
				map()
					.e(ModifyEntitySchemaNameMutationDescriptor.NEW_NAME.name(), "category")
					.e(ModifyEntitySchemaNameMutationDescriptor.OVERWRITE_TARGET.name(), true)
					.build()
			)
		);
		assertThrows(
			EvitaInvalidUsageException.class,
			() -> converter.convert(
				map()
					.e(ModifyEntitySchemaNameMutationDescriptor.NAME.name(), "product")
					.e(ModifyEntitySchemaNameMutationDescriptor.OVERWRITE_TARGET.name(), true)
					.build()
			)
		);
		assertThrows(
			EvitaInvalidUsageException.class,
			() -> converter.convert(
				map()
					.e(ModifyEntitySchemaNameMutationDescriptor.NAME.name(), "product")
					.e(ModifyEntitySchemaNameMutationDescriptor.NEW_NAME.name(), "category")
					.build()
			)
		);
		assertThrows(EvitaInvalidUsageException.class, () -> converter.convert(Map.of()));
		assertThrows(EvitaInvalidUsageException.class, () -> converter.convert((Object) null));
	}
}