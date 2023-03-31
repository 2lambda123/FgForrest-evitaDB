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

package io.evitadb.core.query.extraResult.translator.hierarchyStatistics.producer;

import io.evitadb.core.query.algebra.Formula;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**
 * TODO JNO - document me
 *
 * @author Jan Novotný (novotny@fg.cz), FG Forrest a.s. (c) 2023
 */
@RequiredArgsConstructor
public class HierarchyEntityPredicate {
	public static final HierarchyFilteringPredicate ACCEPT_ALL_NODES_PREDICATE = hierarchNodeId -> true;
	public static final HierarchyTraversalPredicate NEVER_STOP_PREDICATE = (hierarchyNodeId, level, distance) -> true;
	public static final HierarchyEntityPredicate MATCH_ALL = new HierarchyEntityPredicate(
		ACCEPT_ALL_NODES_PREDICATE,
		NEVER_STOP_PREDICATE
	);

	private final HierarchyFilteringPredicate hierarchyNodePredicate;
	private final HierarchyTraversalPredicate positionPredicate;

	/**
	 * TODO JNO - document me
	 * @return
	 */
	@Nullable
	public Formula getFilteringFormula() {
		return hierarchyNodePredicate.getFilteringFormula();
	}

	/**
	 * Method should return true or false in case the predicate matches the traversed node or its position in the tree.
	 *
	 * @param hierarchyNodeId id of visited hierarchy node
	 * @param level           the depth level of visited hierarchy node
	 * @param distance        the distance from the top node the visitor started to traversing
	 * @return true if the node is accepted
	 */
	public boolean test(int hierarchyNodeId, int level, int distance) {
		return positionPredicate.test(hierarchyNodeId, level, distance);
	}

	/**
	 * Method should return true or false in case the predicate matches the traversed node or its position in the tree.
	 *
	 * @param hierarchyNodeId id of visited hierarchy node
	 * @return true if the node is accepted
	 */
	public boolean test(int hierarchyNodeId) {
		return hierarchyNodePredicate.test(hierarchyNodeId);
	}

	/**
	 * Returns combination of this and passed predicate with AND relation.
	 */
	@Nonnull
	public HierarchyEntityPredicate and(@Nonnull HierarchyEntityPredicate other) {
		Objects.requireNonNull(other);
		return new HierarchyEntityPredicate(
			this.hierarchyNodePredicate.and(other.hierarchyNodePredicate),
			this.positionPredicate.and(other.positionPredicate)
		);
	}
}
