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

package io.evitadb.index.hierarchy.predicate;

import io.evitadb.core.query.algebra.Formula;
import io.evitadb.index.EntityIndex;
import net.openhft.hashing.LongHashFunction;

import javax.annotation.Nonnull;
import java.util.Locale;

/**
 * This class allows to test, whether the hierarchical entity has requested language variant.
 */
public class LocaleHierarchyEntityPredicate implements HierarchyFilteringPredicate {
	/**
	 * Formula computes id of all hierarchical entities of the requested language.
	 */
	@Nonnull private final Formula filteringFormula;

	public LocaleHierarchyEntityPredicate(@Nonnull EntityIndex targetIndex, @Nonnull Locale language) {
		this.filteringFormula = targetIndex.getRecordsWithLanguageFormula(language);
	}

	@Override
	public long computeHash(@Nonnull LongHashFunction hashFunction) {
		return filteringFormula.computeHash(hashFunction);
	}

	@Override
	public boolean test(int hierarchyNodeId) {
		return filteringFormula.compute().contains(hierarchyNodeId);
	}

}
