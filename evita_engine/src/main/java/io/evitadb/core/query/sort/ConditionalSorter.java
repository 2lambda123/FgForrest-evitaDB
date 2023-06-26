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

package io.evitadb.core.query.sort;

import io.evitadb.core.query.QueryContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * TODO JNO - document me
 *
 * @author Jan Novotný (novotny@fg.cz), FG Forrest a.s. (c) 2023
 */
public interface ConditionalSorter extends Sorter {

	@Nullable
	static Sorter getFirstApplicableSorter(@Nullable Sorter sorter, @Nonnull QueryContext queryContext) {
		while (sorter instanceof ConditionalSorter conditionalSorter && !conditionalSorter.shouldApply(queryContext)) {
			sorter = conditionalSorter.getNextSorter();
		}
		return sorter;
	}

	/**
	 * TODO JNO - document me
	 * @param queryContext
	 * @return
	 */
	boolean shouldApply(@Nonnull QueryContext queryContext);

}
