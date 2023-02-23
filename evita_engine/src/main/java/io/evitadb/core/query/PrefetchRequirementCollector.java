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

package io.evitadb.core.query;

import io.evitadb.api.query.require.CombinableEntityContentRequire;

import javax.annotation.Nonnull;

/**
 * This interface allows to unify the process for registering new requirements for entity prefetching from multiple
 * places (filtering, ordering and requires).
 *
 * @author Jan Novotný (novotny@fg.cz), FG Forrest a.s. (c) 2022
 */
@FunctionalInterface
public interface PrefetchRequirementCollector {

	/**
	 * Registers new requirement that should be taken into an account when/if the prefetch of the entities occur.
	 * The method call might be completely ignored if the visitor is not present.
	 */
	void addRequirementToPrefetch(@Nonnull CombinableEntityContentRequire require);

}
