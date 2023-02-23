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

package io.evitadb.dataType.exception;

import io.evitadb.dataType.EvitaDataTypes;
import io.evitadb.exception.EvitaInvalidUsageException;

import java.io.Serial;

/**
 * Exception is thrown when {@link EvitaDataTypes} tries to parse string to any data type to
 * EvitaDB.
 *
 * @author Jan Novotný (novotny@fg.cz), FG Forrest a.s. (c) 2021
 */
public class DataTypeParseException extends EvitaInvalidUsageException {
	@Serial private static final long serialVersionUID = 1156296941391203458L;

	public DataTypeParseException(String message) {
		super(message);
	}

}
