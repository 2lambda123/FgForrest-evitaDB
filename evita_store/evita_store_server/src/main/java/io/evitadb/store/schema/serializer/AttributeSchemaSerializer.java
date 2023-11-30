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

package io.evitadb.store.schema.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import io.evitadb.api.requestResponse.schema.dto.AttributeSchema;
import io.evitadb.utils.CollectionUtils;
import io.evitadb.utils.NamingConvention;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This {@link Serializer} implementation reads/writes {@link AttributeSchema} from/to binary format.
 *
 * @author Jan Novotný (novotny@fg.cz), FG Forrest a.s. (c) 2021
 */
@RequiredArgsConstructor
public class AttributeSchemaSerializer extends Serializer<AttributeSchema> {

	@Override
	public void write(Kryo kryo, Output output, AttributeSchema attributeSchema) {
		output.writeString(attributeSchema.getName());
		output.writeVarInt(attributeSchema.getNameVariants().size(), true);
		for (Entry<NamingConvention, String> entry : attributeSchema.getNameVariants().entrySet()) {
			output.writeVarInt(entry.getKey().ordinal(), true);
			output.writeString(entry.getValue());
		}
		kryo.writeClass(output, attributeSchema.getType());
		if (attributeSchema.getDefaultValue() == null) {
			output.writeBoolean(false);
		} else {
			output.writeBoolean(true);
			kryo.writeClassAndObject(output, attributeSchema.getDefaultValue());
		}
		output.writeBoolean(attributeSchema.isUnique());
		output.writeBoolean(attributeSchema.isLocalized());
		output.writeBoolean(attributeSchema.isFilterable());
		output.writeBoolean(attributeSchema.isSortable());
		output.writeBoolean(attributeSchema.isNullable());
		output.writeInt(attributeSchema.getIndexedDecimalPlaces());
		if (attributeSchema.getDescription() != null) {
			output.writeBoolean(true);
			output.writeString(attributeSchema.getDescription());
		} else {
			output.writeBoolean(false);
		}
		if (attributeSchema.getDeprecationNotice() != null) {
			output.writeBoolean(true);
			output.writeString(attributeSchema.getDeprecationNotice());
		} else {
			output.writeBoolean(false);
		}
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public AttributeSchema read(Kryo kryo, Input input, Class<? extends AttributeSchema> aClass) {
		final String name = input.readString();
		final int nameVariantCount = input.readVarInt(true);
		final Map<NamingConvention, String> nameVariants = CollectionUtils.createLinkedHashMap(nameVariantCount);
		for(int i = 0; i < nameVariantCount; i++) {
			nameVariants.put(
				NamingConvention.values()[input.readVarInt(true)],
				input.readString()
			);
		}
		final Class type = kryo.readClass(input).getType();
		final Object defaultValue = input.readBoolean() ? kryo.readClassAndObject(input) : null;
		final boolean unique = input.readBoolean();
		final boolean localized = input.readBoolean();
		final boolean filterable = input.readBoolean();
		final boolean sortable = input.readBoolean();
		final boolean nullable = input.readBoolean();
		final int indexedDecimalPlaces = input.readInt();
		final String description = input.readBoolean() ? input.readString() : null;
		final String deprecationNotice = input.readBoolean() ? input.readString() : null;
		return AttributeSchema._internalBuild(
			name, nameVariants, description, deprecationNotice,
			unique, filterable, sortable, localized, nullable,
			type, (Serializable) defaultValue, indexedDecimalPlaces
		);
	}

}
