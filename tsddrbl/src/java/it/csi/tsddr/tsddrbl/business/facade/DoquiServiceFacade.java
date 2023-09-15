/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.facade;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDichAnnuale;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTPrevCons;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTUtente;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.ResponseProtocollaDocumento;
import it.csi.tsddr.tsddrbl.integration.doqui.exception.ProtocollaDocumentoException;
import it.csi.tsddr.tsddrbl.integration.doqui.exception.RicercaDocumentoException;
import it.csi.tsddr.tsddrbl.vo.DocumentoProtocollatoVO;

public interface DoquiServiceFacade {

	ResponseProtocollaDocumento protocollaDocumentoFisico(TsddrTDichAnnuale dichAnnuale, byte[] document, String nomeFile, TsddrTUtente user) throws ProtocollaDocumentoException;

	DocumentoProtocollatoVO ricercaProtocolloSuACTA(String numProtocollo) throws RicercaDocumentoException;
	
	public ResponseProtocollaDocumento protocollaDocumentoFisicoDichiarazione(TsddrTPrevCons prevCons, byte[] document, String nomeFile, TsddrTUtente user) throws ProtocollaDocumentoException;
	
	public ResponseProtocollaDocumento protocollaDocumentoFisicoRichiesta(TsddrTPrevCons prevCons, byte[] document, String nomeFile, TsddrTUtente user) throws ProtocollaDocumentoException;
}
