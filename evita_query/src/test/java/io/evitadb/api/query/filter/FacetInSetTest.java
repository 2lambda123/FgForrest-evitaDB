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

package io.evitadb.api.query.filter;

import io.evitadb.api.query.QueryConstraints;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This tests verifies basic properties of {@link FacetInSet} query.
 *
 * @author Jan Novotný (novotny@fg.cz), FG Forrest a.s. (c) 2021
 */
class FacetInSetTest {

	@Test
	void shouldCreateViaFactoryClassWorkAsExpected() {
		final FacetInSet facetInSet = QueryConstraints.facetInSet("brand", 1, 5, 7);
		assertEquals("brand", facetInSet.getReferenceName());
		assertArrayEquals(new int[] {1, 5, 7}, facetInSet.getFacetIds());
	}

	@Test
	void shouldRecognizeApplicability() {
		assertFalse(new FacetInSet("brand").isApplicable());
		assertTrue(QueryConstraints.facetInSet("brand", 1).isApplicable());
		assertTrue(QueryConstraints.facetInSet("brand", 1, 5, 7).isApplicable());
	}

	@Test
	void shouldToStringReturnExpectedFormat() {
		final FacetInSet facetInSet = QueryConstraints.facetInSet("brand", 1, 5, 7);
		assertEquals("facetInSet('brand',1,5,7)", facetInSet.toString());
	}

	@Test
	void shouldConformToEqualsAndHashContract() {
		assertNotSame(QueryConstraints.facetInSet("brand", 1, 1, 5), QueryConstraints.facetInSet("brand", 1, 1, 5));
		assertEquals(QueryConstraints.facetInSet("brand", 1, 1, 5), QueryConstraints.facetInSet("brand", 1, 1, 5));
		assertNotEquals(QueryConstraints.facetInSet("brand", 1, 1, 5), QueryConstraints.facetInSet("brand", 1, 1, 6));
		assertNotEquals(QueryConstraints.facetInSet("brand", 1, 1, 5), QueryConstraints.facetInSet("brand", 1, 1));
		assertNotEquals(QueryConstraints.facetInSet("brand", 1, 1, 5), QueryConstraints.facetInSet("brand", 2, 1, 5));
		assertNotEquals(QueryConstraints.facetInSet("brand", 1, 1, 5), QueryConstraints.facetInSet("category", 1, 1, 6));
		assertNotEquals(QueryConstraints.facetInSet("brand", 1, 1, 5), QueryConstraints.facetInSet("brand", 1, 1));
		assertEquals(QueryConstraints.facetInSet("brand", 1, 1, 5).hashCode(), QueryConstraints.facetInSet("brand", 1, 1, 5).hashCode());
		assertNotEquals(QueryConstraints.facetInSet("brand", 1, 1, 5).hashCode(), QueryConstraints.facetInSet("brand", 1, 1, 6).hashCode());
		assertNotEquals(QueryConstraints.facetInSet("brand", 1, 1, 5).hashCode(), QueryConstraints.facetInSet("brand", 1, 1).hashCode());
	}

}