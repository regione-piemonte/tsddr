/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.report;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.imageio.ImageIO;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDichAnnuale;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTReport;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsExtendedVO;

public interface TsddrReport {

    void setJasperParam(PrevConsExtendedVO prevConsExtendedVO, Map<String, Object> var2) throws IOException;
    
	void setJasperParam(TsddrTDichAnnuale var1, Map<String, Object> var2) throws IOException;

	String getJrxml() throws Exception;
	
	Long getTsddrReportId();
	
	TsddrTReport getTsddrTReport();
	
	default String getTemplateJrxml(String name) throws Exception {
        String fileJrxml = null;
        try {
            fileJrxml = getTsddrTReport().getXmlReport();
            if(fileJrxml == null)
                throw new Exception("Template not found!");
        } catch(Exception e) {
            throw e;
        }
        return fileJrxml;
    }

	default Double executeDivision(Integer numerator, Integer denominator) {
		if (denominator == null || denominator == 0)
			return 0d;
		return (numerator / (double)denominator);
	}

	default BufferedImage getImageFromUrl(String url) throws IOException {
		return ImageIO.read(Objects.requireNonNull(getClass().getResource(url)));
	}
	
	default BufferedImage getImageFromByteArray(byte[] byteArray) throws IOException {
	    return ImageIO.read(new ByteArrayInputStream(byteArray));
	}
	
	default BufferedImage getImageFromBase64ByteArray(byte[] byteArray) throws IOException {
        byte[] decoded = Base64.getDecoder().decode(new String(byteArray, StandardCharsets.UTF_8));
        return getImageFromByteArray(decoded);
    }
	
	default BufferedImage getEmptyBufferedImage() {
	    BufferedImage bi = new BufferedImage(1, 1,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D ig2 = bi.createGraphics();
        
        ig2.setBackground(Color.WHITE);
        ig2.clearRect(0, 0, 1, 1);
        return bi;
	}

	default Map<String, String> createRow(String param, String param2, Integer val, Integer val12Ore, Boolean addVar) {
		Map<String, String> result = new HashMap<>();
		result.put(param + param2, val + "");
		if (val12Ore != null) {
			result.put(param + "12" + param2, val12Ore + "");
			if (addVar)
				result.put(param + "Var" + param2, executeDivision(val, val12Ore) * 100 + "");
			else
				result.put(param + "Var" + param2, "0");
		} else {
			result.put(param + "12" + param2, "0");
			result.put(param + "Var" + param2, "0");
		}

		return result;
	}

	default Map<String, String> createRow(String param, Integer val, Integer val12Ore, Boolean addVar) {
		Map<String, String> result = new HashMap<>();
		result.put(param, val + "");
		result.put(param + "12", val12Ore + "");
		if (addVar)
			result.put(param + "Var", executeDivision(val, val12Ore) * 100 + "");
		else
			result.put(param + "Var", "");
		return result;
	}

	default Map<String, String> createRow(String param, Integer val, Integer val12Ore) {
		return createRow(param, val, val12Ore, false);
	}

	default Map<String, String> createRow(String param, Long val, Long val12Ore) {
		return createRow(param, val.intValue(), val12Ore.intValue());
	}

	default Map<String, String> createRow(String param, Long val, Long val12Ore, Boolean addVar) {
		return createRow(param, val.intValue(), val12Ore.intValue(), addVar);
	}
}
