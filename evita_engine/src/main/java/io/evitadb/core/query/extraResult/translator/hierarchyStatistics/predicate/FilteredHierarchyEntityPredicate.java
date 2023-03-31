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

package io.evitadb.core.query.extraResult.translator.hierarchyStatistics.predicate;

import io.evitadb.api.query.filter.FilterBy;
import io.evitadb.api.requestResponse.data.AttributesContract;
import io.evitadb.api.requestResponse.schema.AttributeSchemaContract;
import io.evitadb.core.exception.AttributeNotFilterableException;
import io.evitadb.core.exception.AttributeNotFoundException;
import io.evitadb.core.query.algebra.Formula;
import io.evitadb.core.query.extraResult.translator.hierarchyStatistics.producer.HierarchyPositionalPredicate;
import io.evitadb.core.query.extraResult.translator.hierarchyStatistics.producer.HierarchyProducerContext;
import io.evitadb.core.query.filter.FilterByVisitor;
import io.evitadb.core.query.indexSelection.TargetIndexes;
import io.evitadb.index.bitmap.Bitmap;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.function.IntPredicate;

import static io.evitadb.utils.Assert.isTrue;
import static io.evitadb.utils.Assert.notNull;

/**
 * TODO JNO - document me
 *
 * @author Jan Novotný (novotny@fg.cz), FG Forrest a.s. (c) 2023
 */
@RequiredArgsConstructor
public class FilteredHierarchyEntityPredicate implements IntPredicate, HierarchyPositionalPredicate {
	private final Formula filteringFormula;
	private Bitmap filteredIds;

	public FilteredHierarchyEntityPredicate(HierarchyProducerContext context, FilterBy filterBy) {
		final FilterByVisitor theFilterByVisitor = new FilterByVisitor(
			context.queryContext(),
			Collections.emptyList(),
			TargetIndexes.EMPTY,
			false
		);
		this.filteringFormula = theFilterByVisitor.executeInContext(
			Collections.singletonList(context.entityIndex()),
			null,
			null,
			null,
			null,
			(entitySchema, attributeName) -> {
				final AttributeSchemaContract attributeSchema = context.entitySchema().getAttribute(attributeName).orElse(null);
				notNull(
					attributeSchema,
					() -> new AttributeNotFoundException(attributeName, entitySchema)
				);
				isTrue(
					attributeSchema.isFilterable() || attributeSchema.isUnique(),
					() -> new AttributeNotFilterableException(attributeName, entitySchema)
				);
				return attributeSchema;
			},
			AttributesContract::getAttribute,
			() -> {
				filterBy.accept(theFilterByVisitor);
				// get the result and clear the visitor internal structures
				return theFilterByVisitor.getFormulaAndClear();
			}
		);
	}

	@Override
	public boolean test(int hierarchyNodeId, int level, int distance) {
		return getFilteredIds().contains(hierarchyNodeId);
	}

	@Override
	public boolean test(int hierarchyNodeId) {
		return getFilteredIds().contains(hierarchyNodeId);
	}

	private Bitmap getFilteredIds() {
		if (filteredIds == null) {
			/* TODO JNO - add footprint to query telemetry */
			filteredIds = filteringFormula.compute();
		}
		return filteredIds;
	}
}
