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

package io.evitadb.api.exception;

import io.evitadb.exception.EvitaInternalError;

import javax.annotation.Nonnull;
import java.io.Serial;

/**
 * Exception is thrown when there is no schema stored in an appropriate data store file. The files must always
 * contain the schema object.
 *
 * @author Jan Novotný (novotny@fg.cz), FG Forrest a.s. (c) 2022
 */
public class SchemaNotFoundException extends EvitaInternalError {
	@Serial private static final long serialVersionUID = 244210093979306431L;

	public SchemaNotFoundException(@Nonnull String catalogName) {
		super(
			"No schema found for catalog with name `" + catalogName +
			"` in persistent storage. The data are probably corrupted!"
		);
	}

	public SchemaNotFoundException(@Nonnull String catalogName, @Nonnull String entityType) {
		super(
			"No schema found " +
				"for entity collection `" + entityType + "` " +
				"in catalog with name `" + catalogName + "` in persistent storage. The data are probably corrupted!"
		);
	}
}
