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

package io.evitadb.externalApi.graphql.artificial.state;

import io.evitadb.externalApi.graphql.artificial.GraphQLArtificialEntitiesBenchmark;
import io.evitadb.externalApi.graphql.artificial.GraphQLArtificialFullDatabaseBenchmarkState;
import io.evitadb.test.Entities;
import io.evitadb.test.generator.DataGenerator;
import io.evitadb.utils.StringUtils;
import lombok.SneakyThrows;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.infra.Blackhole;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.evitadb.artificial.ArtificialFullDatabaseBenchmarkState.PRODUCT_COUNT;

/**
 * Base state class for {@link GraphQLArtificialEntitiesBenchmark#singleEntityRead(GraphQLArtificialFullDatabaseBenchmarkState, GraphQLArtificialSingleReadState, Blackhole)}.
 * See benchmark description on the method.
 *
 * @author Lukáš Hornych, FG Forrest a.s. (c) 2022
 */
public class GraphQLArtificialSingleReadState extends AbstractGraphQLArtificialState {

	/**
	 * Prepares artificial product for the next operation that is measured in the benchmark.
	 */
	@SneakyThrows
	@Setup(Level.Invocation)
	public void prepareCall(GraphQLArtificialFullDatabaseBenchmarkState benchmarkState) {
		final Set<String> outputFields = new HashSet<>();
		outputFields.add("primaryKey");

		/* 75% times fetch attributes */
		if (benchmarkState.getRandom().nextInt(4) != 0) {
			outputFields.add("attributes { code, quantity }");
		}
		/* 75% times fetch associated data */
		if (benchmarkState.getRandom().nextInt(4) != 0) {
			final String associatedData = benchmarkState.getProductSchema()
				.getAssociatedData()
				.keySet()
				.stream()
				.filter(it -> benchmarkState.getRandom().nextInt(4) != 0)
				.collect(Collectors.joining(", "));
			if (!associatedData.isEmpty()) {
				outputFields.add(
					"associatedData {" +
						associatedData +
						"}"
				);
			}
		}
		/* 50% times fetch prices */
		final String priceHeader;
		if (benchmarkState.getRandom().nextBoolean()) {
			final Currency randomExistingCurrency = Arrays.stream(DataGenerator.CURRENCIES)
				.skip(benchmarkState.getRandom().nextInt(DataGenerator.CURRENCIES.length))
				.findFirst()
				.orElseThrow(() -> new IllegalStateException("No currencies found!"));
			final String[] priceLists = Arrays.stream(DataGenerator.PRICE_LIST_NAMES)
				.filter(it -> benchmarkState.getRandom().nextBoolean())
				.toArray(String[]::new);

			priceHeader =
				"priceInCurrency: " + randomExistingCurrency.toString() + "," +
				"priceInPriceLists: [" + Arrays.stream(priceLists).map(p -> "\"" + p + "\"").collect(Collectors.joining(",")) + "]," +
				"priceValidIn: \"" + OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME) + "\"";

			/* 75% only filtered prices */
			if (benchmarkState.getRandom().nextInt(4) != 0) {
				outputFields.add("priceForSale { priceWithTax }");
			} else {
				outputFields.add("prices { priceWithTax }");
			}
		} else {
			priceHeader = "";
		}

		/* 25% times load references */
		if (benchmarkState.getRandom().nextInt(4) == 0) {
			Stream.of(Entities.BRAND, Entities.CATEGORY, Entities.PRICE_LIST, Entities.STORE)
				.filter(it -> benchmarkState.getProductSchema().getReference(it).isPresent())
				.forEach(ref -> {
					final String refFieldName = StringUtils.toCamelCase(ref);
					outputFields.add(refFieldName + " { " + refFieldName + " { primaryKey } }");
				});
		}

		final Locale randomExistingLocale = benchmarkState.getProductSchema()
			.getLocales()
			.stream()
			.skip(benchmarkState.getRandom().nextInt(benchmarkState.getProductSchema().getLocales().size()))
			.findFirst()
			.orElseThrow(() -> new IllegalStateException("No locales found!"));

		this.requestBody =
			String.format(
				"""
				query {
					get_%s(%s) {
						%s
					}
				}
				""",
				StringUtils.toCamelCase(Entities.PRODUCT),
				String.format(
					"primaryKey: %d, locale: %s %s",
					benchmarkState.getRandom().nextInt(PRODUCT_COUNT) + 1,
					randomExistingLocale,
					"," + priceHeader
				),
				String.join("\n", outputFields)
			);
	}
}
