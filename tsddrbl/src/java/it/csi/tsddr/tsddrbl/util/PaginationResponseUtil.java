/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The type Pagination response util.
 */
public class PaginationResponseUtil {


    /**
     * Build response response.
     *
     * @param content       the content
     * @param page          the page
     * @param size          the size
     * @param totalPages    the total pages
     * @param totalElements the total elements
     * @return the response
     */
    public static Response buildResponse(List content, int page, int size, int totalPages,
			long totalElements) {
//		X-Page; // For pagination purposes. The current page.
//		X-Page-Size; //For pagination purposes. The page size.
//		X-Total-Elements; // For pagination purposes. The total number of elements satisfying the search query.
//		X-Total-Pages;  // For pagination purposes. The total number of pages for representing the total number of elements satisfying the query.
		

        ObjectMapper mapper = new ObjectMapper();
		String str = "";
		int len = 0;
		try {
			str = mapper.writeValueAsString(content);
			len = str.getBytes("UTF-8").length;
		} catch (JsonProcessingException | UnsupportedEncodingException e) {}
        return Response.ok(str)
                .header(HttpHeaders.CONTENT_ENCODING, Constants.IDENTITY_CONTENT_ENCODING)
                .header(HttpHeaders.CONTENT_LENGTH, len)
                .header("X-Page", page)
        		.header("X-Page-Size", size)
        		.header("X-Total-Elements", totalElements)
        		.header("X-Total-Pages", totalPages)
                .build();
		
//		return Response.ok(content).header("X-Page", page)
//		.header("X-Page-Size", size)
//		.header("X-Total-Elements", totalElements)
//		.header("X-Total-Pages", totalPages).build();
	
	}

}