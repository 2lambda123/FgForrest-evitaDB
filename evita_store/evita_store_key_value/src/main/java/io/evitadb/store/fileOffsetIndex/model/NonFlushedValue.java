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

package io.evitadb.store.fileOffsetIndex.model;

import io.evitadb.store.fileOffsetIndex.FileOffsetIndex;
import io.evitadb.store.model.FileLocation;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * This DTO allows to keep the object that was written to the {@link FileOffsetIndex} but its location
 * was not yet flushed to the disk.
 *
 * @param primaryKey   Contains primary key of the stored container.
 * @param recordType   Contains type of the record stored on specified position.
 * @param fileLocation Contains coordinates to the space in the file that is occupied by this record.
 * @author Jan Novotný (novotny@fg.cz), FG Forrest a.s. (c) 2021
 */
public record NonFlushedValue(long primaryKey, byte recordType, FileLocation fileLocation) implements Serializable {
	@Serial private static final long serialVersionUID = -4467999274212489366L;

	/**
	 * Returns true if this non flushed value represents removal of the record.
	 */
	public boolean isRemoval() {
		return recordType < 0;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		NonFlushedValue that = (NonFlushedValue) o;
		return primaryKey == that.primaryKey && Math.abs(recordType) == Math.abs(that.recordType) && fileLocation.equals(that.fileLocation);
	}

	@Override
	public int hashCode() {
		return Objects.hash(primaryKey, Math.abs(recordType), fileLocation);
	}
}
