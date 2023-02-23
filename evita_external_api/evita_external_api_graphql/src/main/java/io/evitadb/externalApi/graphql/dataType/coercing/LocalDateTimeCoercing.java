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

package io.evitadb.externalApi.graphql.dataType.coercing;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

import javax.annotation.Nonnull;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * {@link Coercing} for converting between Java's side {@link LocalDateTime} and client string.
 *
 * @author Lukáš Hornych, FG Forrest a.s. (c) 2022
 */
public class LocalDateTimeCoercing implements Coercing<LocalDateTime, String> {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public String serialize(@Nonnull Object dataFetcherResult) throws CoercingSerializeException {
        if (!(dataFetcherResult instanceof LocalDateTime)) {
            throw new CoercingSerializeException("Local date time data fetcher result is not a local date time.");
        }
        try {
            return ((LocalDateTime) dataFetcherResult).format(FORMATTER);
        } catch (DateTimeException ex) {
            throw new CoercingSerializeException(ex.getMessage(), ex);
        }
    }

    @Nonnull
    @Override
    public LocalDateTime parseValue(@Nonnull Object input) throws CoercingParseValueException {
        if (!(input instanceof String)) {
            throw new CoercingParseValueException("Local date time input is not a string.");
        }
        try {
            return LocalDateTime.parse((String) input, FORMATTER);
        } catch (DateTimeParseException ex) {
            throw new CoercingParseValueException(ex.getMessage(), ex);
        }
    }

    @Nonnull
    @Override
    public LocalDateTime parseLiteral(@Nonnull Object input) throws CoercingParseLiteralException {
        if (!(input instanceof StringValue)) {
            throw new CoercingParseValueException("Local date time input is not a StringValue.");
        }
        try {
            return LocalDateTime.parse(((StringValue) input).getValue(), FORMATTER);
        } catch (DateTimeParseException ex) {
            throw new CoercingParseLiteralException(ex.getMessage(), ex);
        }
    }
}
