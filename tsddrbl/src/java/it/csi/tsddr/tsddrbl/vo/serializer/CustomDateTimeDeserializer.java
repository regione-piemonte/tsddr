/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.serializer;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * The type Custom date time deserializer.
 */
public class CustomDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

	@Override
	public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
		String dateAsString = jsonParser.getText();
		if (StringUtils.isEmpty(dateAsString))
			return null;
		return LocalDateTime.parse(dateAsString, CustomDateTimeSerializer.FORMATTER);
	}
}