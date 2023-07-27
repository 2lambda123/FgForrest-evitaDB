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

package io.evitadb.api.requestResponse.data.mutation;

import io.evitadb.api.exception.InvalidMutationException;
import io.evitadb.api.requestResponse.cdc.Operation;
import io.evitadb.api.requestResponse.data.Droppable;
import io.evitadb.api.requestResponse.data.structure.Entity;
import io.evitadb.api.requestResponse.schema.EntitySchemaContract;
import io.evitadb.api.requestResponse.schema.EvolutionMode;
import io.evitadb.api.requestResponse.schema.SealedCatalogSchema;
import io.evitadb.api.requestResponse.schema.SealedEntitySchema;
import io.evitadb.api.requestResponse.schema.mutation.EntitySchemaMutation;
import io.evitadb.api.requestResponse.schema.mutation.entity.SetEntitySchemaWithGeneratedPrimaryKeyMutation;
import io.evitadb.utils.Assert;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serial;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Optional.empty;
import static java.util.Optional.of;

/**
 * EntityUpsertMutation represents a terminal mutation when existing entity is removed in the evitaDB. The entity is
 * and all its internal data are marked as TRUE for {@link Droppable#dropped()}, stored to the storage file and
 * removed from the mem-table.
 *
 * @author Jan Novotný (novotny@fg.cz), FG Forrest a.s. (c) 2022
 */
@EqualsAndHashCode
public class EntityUpsertMutation implements EntityMutation {
	@Serial private static final long serialVersionUID = -2012245398443357781L;

	/**
	 * The existing entity {@link Entity#getPrimaryKey()} allowing identification of the entity to modify.
	 */
	@Nullable
	private Integer entityPrimaryKey;
	/**
	 * The {@link EntitySchemaContract#getName()} of the entity type.
	 */
	@Nonnull @Getter
	private final String entityType;
	/**
	 * Controls behaviour of the upsert operation.
	 *
	 * - MUST_NOT_EXIST: use when you know you'll be inserting a new value
	 * - MUST_EXIST: use when you know you'll be updating an existing value
	 * - MAY_EXIST: use when you're not sure
	 */
	@Nonnull
	private final EntityExistence entityExistence;
	/**
	 * List of all local mutations that modify internal data of the entity.
	 */
	private final Collection<? extends LocalMutation<?, ?>> localMutations;

	public EntityUpsertMutation(
		@Nonnull String entityType,
		@Nullable Integer entityPrimaryKey,
		@Nonnull EntityExistence entityExistence,
		@Nonnull Collection<? extends LocalMutation<?, ?>> localMutations
	) {
		this.entityPrimaryKey = entityPrimaryKey;
		this.entityType = entityType;
		this.entityExistence = entityExistence;
		this.localMutations = localMutations;
	}

	public EntityUpsertMutation(
		@Nonnull String entityType,
		@Nullable Integer entityPrimaryKey,
		@Nonnull EntityExistence entityExistence,
		@Nonnull LocalMutation<?, ?>... localMutations
	) {
		this.entityPrimaryKey = entityPrimaryKey;
		this.entityType = entityType;
		this.entityExistence = entityExistence;
		this.localMutations = Arrays.asList(localMutations);
	}

	@Nonnull
	@Override
	public Operation getOperation() {
		return Operation.UPDATE;
	}

	@Nonnull
	public Collection<? extends LocalMutation<?, ?>> getLocalMutations() {
		return localMutations;
	}

	@Nullable
	@Override
	public Integer getEntityPrimaryKey() {
		return entityPrimaryKey;
	}

	@Override
	public void setEntityPrimaryKey(@Nonnull Integer primaryKey) {
		this.entityPrimaryKey = primaryKey;
	}

	@Nonnull
	@Override
	public EntityExistence expects() {
		return entityExistence;
	}

	@Nonnull
	@Override
	public Optional<EntitySchemaMutation[]> verifyOrEvolveSchema(
		@Nonnull SealedCatalogSchema catalogSchema,
		@Nonnull SealedEntitySchema entitySchema,
		boolean entityCollectionEmpty
	) {
		final Optional<EntitySchemaMutation> pkMutation;
		// when the collection is empty - we may redefine primary key behaviour in schema (this is the only moment to do so)
		if (entityCollectionEmpty) {
			if (entityPrimaryKey == null && !entitySchema.isWithGeneratedPrimaryKey()) {
				// if primary key in first entity is not present switch schema to automatically assign new ids
				Assert.isTrue(
					entitySchema.allows(EvolutionMode.ADAPT_PRIMARY_KEY_GENERATION),
					() ->  new InvalidMutationException(
						"Entity of type `" + entitySchema.getName() + "` schema " +
							"is set to expect primary keys assigned externally, but no primary key is provided in entity mutation!"
					)
				);
				pkMutation = of(new SetEntitySchemaWithGeneratedPrimaryKeyMutation(true));
			} else if (entityPrimaryKey != null && entitySchema.isWithGeneratedPrimaryKey()) {
				// if primary key in first entity is present switch schema to expect ids generated externally
				Assert.isTrue(
					entitySchema.allows(EvolutionMode.ADAPT_PRIMARY_KEY_GENERATION),
					() ->  new InvalidMutationException(
						"Entity of type `" + entitySchema.getName() + "` schema " +
							"is set to expect to generate primary keys automatically, but primary key is provided " +
							"in entity mutation and no appropriate entity exists in the collection!"
					)
				);
				pkMutation = of(new SetEntitySchemaWithGeneratedPrimaryKeyMutation(false));
			} else {
				// the mutation match the expectations
				pkMutation = empty();
			}
		} else {
			// collection is not empty - cannot adapt schema primary key generation settings
			pkMutation = empty();
		}
		// collect schema mutations from the local entity mutations
		final Optional<EntitySchemaMutation[]> additionalMutations = EntityMutation.verifyOrEvolveSchema(catalogSchema, entitySchema, localMutations);
		// combine mutation local mutations with the entity primary key mutation is provided
		return additionalMutations
			.map(
				mutations -> pkMutation
					.map(it -> Stream.concat(Stream.of(it), Arrays.stream(mutations)))
					.orElseGet(() -> Arrays.stream(mutations))
					.toArray(EntitySchemaMutation[]::new)
			)
			.or(() -> pkMutation.map(x -> new EntitySchemaMutation[]{x}));
	}

	@Nonnull
	@Override
	public Entity mutate(@Nonnull EntitySchemaContract entitySchema, @Nullable Entity entity) {
		return Entity.mutateEntity(
			entitySchema,
			Objects.requireNonNullElseGet(entity, () -> new Entity(entityType, entityPrimaryKey)),
			localMutations
		);
	}
}
