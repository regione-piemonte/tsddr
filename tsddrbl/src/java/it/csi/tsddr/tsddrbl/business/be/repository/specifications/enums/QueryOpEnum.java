/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository.specifications.enums;

import java.util.Arrays;

/**
 * The enum Query op enum.
 */
public enum QueryOpEnum {
    /**
     * Equals query op enum.
     */
    EQUALS("eq"),
    /**
     * Not equals query op enum.
     */
    NOT_EQUALS("ne"),
    /**
     * Less than query op enum.
     */
    LESS_THAN("lt"),
    /**
     * Less than or equals query op enum.
     */
    LESS_THAN_OR_EQUALS("lte"),
    /**
     * Greater than query op enum.
     */
    GREATER_THAN("gt"),
    /**
     * Greater than or equals query op enum.
     */
    GREATER_THAN_OR_EQUALS("gte"),
    /**
     * In query op enum.
     */
    IN("in"),
    /**
     * Not in query op enum.
     */
    NOT_IN("nin"),
    /**
     * Like query op enum.
     */
    LIKE("c"),
    /**
     * Like ignore case query op enum.
     */
    LIKE_IGNORE_CASE("ci"),
    /**
     * Start with query op enum.
     */
    START_WITH("s"),
    /**
     * Start with ignore case query op enum.
     */
    START_WITH_IGNORE_CASE("si"),
    /**
     * End with query op enum.
     */
    END_WITH("e"),
    /**
     * End with ignore case query op enum.
     */
    END_WITH_IGNORE_CASE("ei");

    private final String value;

    QueryOpEnum(String value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Find by value query op enum.
     *
     * @param value the value
     * @return the query op enum
     */
    public static QueryOpEnum findByValue(final String value) {
        return Arrays.stream(values()).filter(elem -> elem.getValue().equals(value.trim())).findFirst().orElse(null);
    }
}