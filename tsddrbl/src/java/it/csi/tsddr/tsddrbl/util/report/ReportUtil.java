/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.report;

import java.util.Map;
import net.sf.jasperreports.engine.JasperPrint;

public interface ReportUtil {

	JasperPrint printJasper(Map<String, Object> var1, TsddrReport var2) throws Exception;
}