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

package io.evitadb.externalApi.grpc.requestResponse.data.mutation.entity;

import com.google.protobuf.Int32Value;
import io.evitadb.api.requestResponse.data.mutation.entity.SetHierarchicalPlacementMutation;
import io.evitadb.externalApi.grpc.generated.GrpcSetHierarchicalPlacementMutation;
import io.evitadb.externalApi.grpc.requestResponse.data.mutation.LocalMutationConverter;

import javax.annotation.Nonnull;

/**
 * Converts between {@link SetHierarchicalPlacementMutation} and {@link GrpcSetHierarchicalPlacementMutation} in both directions.
 *
 * @author Tomáš Pozler, 2022
 * @author Lukáš Hornych, FG Forrest a.s. (c) 2023
 */
public class SetHierarchicalPlacementMutationConverter implements LocalMutationConverter<SetHierarchicalPlacementMutation, GrpcSetHierarchicalPlacementMutation> {

	@Override
	@Nonnull
	public SetHierarchicalPlacementMutation convert(@Nonnull GrpcSetHierarchicalPlacementMutation mutation) {
		if (!mutation.hasPrimaryKey())
			return new SetHierarchicalPlacementMutation(mutation.getOrderAmongSiblings());
		return new SetHierarchicalPlacementMutation(
			mutation.getPrimaryKey().getValue(),
			mutation.getOrderAmongSiblings()
		);
	}

	@Nonnull
	@Override
	public GrpcSetHierarchicalPlacementMutation convert(@Nonnull SetHierarchicalPlacementMutation mutation) {
		final GrpcSetHierarchicalPlacementMutation.Builder builder = GrpcSetHierarchicalPlacementMutation.newBuilder()
			.setOrderAmongSiblings(mutation.getOrderAmongSiblings());

		if (mutation.getParentPrimaryKey() != null) {
			builder.setPrimaryKey(Int32Value.of(mutation.getParentPrimaryKey()));
		}

		return builder.build();
	}
}
