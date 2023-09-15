/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.report;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTReport;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTReportRepository;

public abstract class GenericReport implements TsddrReport {
	
	@Autowired
	TsddrTReportRepository tsddrTReportRepository;
	
	TsddrTReport tsddrTReport = null;

	public TsddrTReport getTsddrTReport(){
		if(tsddrTReport == null ) {
			tsddrTReport = tsddrTReportRepository.findOne(getTsddrReportId());
		}
		return tsddrTReport;
	}
	
	public String getJrxml() throws Exception {
		return this.getTemplateJrxml(getTsddrTReport().getXmlReport());
	}
	
	
}
