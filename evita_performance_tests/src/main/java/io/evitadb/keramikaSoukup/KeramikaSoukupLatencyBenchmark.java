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

package io.evitadb.keramikaSoukup;

import io.evitadb.keramikaSoukup.state.*;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

/**
 * This class runs all tests in {@link KeramikaSoukupBenchmark} in latency mode measurement.
 *
 * @author Jan Novotný (novotny@fg.cz), FG Forrest a.s. (c) 2022
 */
@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class KeramikaSoukupLatencyBenchmark extends KeramikaSoukupBenchmark {

	@Override
	public void bulkInsertThroughput(KeramikaSoukupBulkWriteState state) {
		super.bulkInsertThroughput(state);
	}

	@Override
	public void transactionalUpsertThroughput(KeramikaSoukupTransactionalWriteState state) {
		super.transactionalUpsertThroughput(state);
	}

	@Override
	public void singleEntityRead(KeramikaSoukupSingleReadState state, Blackhole blackhole) {
		super.singleEntityRead(state, blackhole);
	}

	@Override
	public void paginatedEntityRead(KeramikaSoukupPageReadState state, Blackhole blackhole) {
		super.paginatedEntityRead(state, blackhole);
	}

	@Override
	public void attributeFiltering(KeramikaSoukupAttributeFilteringState state, Blackhole blackhole) {
		super.attributeFiltering(state, blackhole);
	}

	@Override
	public void attributeAndHierarchyFiltering(KeramikaSoukupAttributeAndHierarchyFilteringState state, Blackhole blackhole) {
		super.attributeAndHierarchyFiltering(state, blackhole);
	}

	@Override
	public void attributeHistogramComputation(KeramikaSoukupAttributeHistogramState state, Blackhole blackhole) {
		super.attributeHistogramComputation(state, blackhole);
	}

	@Override
	public void priceFiltering(KeramikaSoukupPriceFilteringState state, Blackhole blackhole) {
		super.priceFiltering(state, blackhole);
	}

	@Override
	public void priceAndHierarchyFiltering(KeramikaSoukupPriceAndHierarchyFilteringState state, Blackhole blackhole) {
		super.priceAndHierarchyFiltering(state, blackhole);
	}

	@Override
	public void priceHistogramComputation(KeramikaSoukupPriceHistogramState state, Blackhole blackhole) {
		super.priceHistogramComputation(state, blackhole);
	}

	@Override
	public void facetFiltering(KeramikaSoukupFacetFilteringState state, Blackhole blackhole) {
		super.facetFiltering(state, blackhole);
	}

	@Override
	public void facetAndHierarchyFiltering(KeramikaSoukupFacetAndHierarchyFilteringState state, Blackhole blackhole) {
		super.facetAndHierarchyFiltering(state, blackhole);
	}

	@Override
	public void facetFilteringAndSummarizingCount(KeramikaSoukupFacetFilteringAndSummarizingCountState state, Blackhole blackhole) {
		super.facetFilteringAndSummarizingCount(state, blackhole);
	}

	@Override
	public void facetAndHierarchyFilteringAndSummarizingCount(KeramikaSoukupFacetAndHierarchyFilteringAndSummarizingCountState state, Blackhole blackhole) {
		super.facetAndHierarchyFilteringAndSummarizingCount(state, blackhole);
	}

	@Override
	public void facetAndHierarchyFilteringAndSummarizingImpact(KeramikaSoukupFacetAndHierarchyFilteringAndSummarizingImpactState state, Blackhole blackhole) {
		super.facetAndHierarchyFilteringAndSummarizingImpact(state, blackhole);
	}

	@Override
	public void parentsComputation(KeramikaSoukupParentsComputationState state, Blackhole blackhole) {
		super.parentsComputation(state, blackhole);
	}

	@Override
	public void hierarchyStatisticsComputation(KeramikaSoukupHierarchyStatisticsComputationState state, Blackhole blackhole) {
		super.hierarchyStatisticsComputation(state, blackhole);
	}

	@Override
	public void syntheticTest(KeramikaSoukupSyntheticTestState state, Blackhole blackhole) {
		super.syntheticTest(state, blackhole);
	}

}
