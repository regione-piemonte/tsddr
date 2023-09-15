/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.serializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * The type Custom date serializer.
 */
public class CustomDateSerializer extends JsonSerializer<LocalDate> {

    /**
     * The constant FORMATTER.
     */
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    /**
     * The constant FORMATTER_SORIS.
     */
    public static final DateTimeFormatter FORMATTER_SORIS = DateTimeFormatter.ofPattern("ddMMyy");
    /**
     * The constant FORMATTER_DATE.
     */
    public static final DateTimeFormatter FORMATTER_DATE = DateTimeFormatter.ofPattern("ddMMyyyy");
    /**
     * The constant LOCALE_HUNGARIAN.
     */
    public static final Locale LOCALE_HUNGARIAN = new Locale("it", "IT");
    /**
     * The constant LOCAL_TIME_ZONE.
     */
    public static final TimeZone LOCAL_TIME_ZONE = TimeZone.getTimeZone("Europe/Paris");

	@Override
	public void serialize(LocalDate date, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
		if (date == null) {
			jsonGenerator.writeNull();
		} else {
			jsonGenerator.writeString(date.format(FORMATTER));
		}

	}

}