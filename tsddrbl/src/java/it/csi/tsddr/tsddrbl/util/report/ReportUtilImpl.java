/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import javax.print.PrintException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;

@Service
public class ReportUtilImpl implements ReportUtil {

	public JasperPrint printJasper(Map<String, Object> jasperParam, TsddrReport report) throws Exception {
		JasperReport jasperReport = getJasperFromFile(report);
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList(""));
		return JasperFillManager.fillReport(jasperReport, jasperParam, dataSource);
	}

	private static JasperReport getJasperFromFile(TsddrReport report) throws Exception {
		String jrxml = report.getJrxml();
		InputStream bais = compileJRXML(jrxml.getBytes());
		return (JasperReport) JRLoader.loadObject(bais);
	}

	private static ByteArrayInputStream compileJRXML(byte[] template) throws PrintException {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ByteArrayInputStream bais = new ByteArrayInputStream(template);
			JasperCompileManager.compileReportToStream(bais, os);
			ByteArrayInputStream result = new ByteArrayInputStream(os.toByteArray());
			return result;

		} catch (JRException e) {
			throw new PrintException("Errore nella creazione del file jasper dal template jrxml", e);
		}
	}
}