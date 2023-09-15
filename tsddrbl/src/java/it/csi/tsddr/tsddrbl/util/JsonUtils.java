/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util;

import java.text.SimpleDateFormat;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * The type Json utils.
 */
public class JsonUtils {

    /**
     * To json string string.
     *
     * @param obj the obj
     * @return the string
     */
    public static String toJsonString(Object obj) {
        SimpleDateFormat df = new SimpleDateFormat(DateUtil.ddMMyyyy);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_EMPTY);
        mapper.setDateFormat(df);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    /**
     * To json obj json object.
     *
     * @param json the json
     * @return the json object
     * @throws JSONException the json exception
     */
    public static JSONObject toJsonObj(String json) throws JSONException {
        String sJson = toJsonString(json);
        JSONObject obj = new JSONObject(sJson);
        obj.remove("json_data");
        return obj;
    }

}