/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui.helper;

import it.csi.tsddr.tsddrbl.integration.doqui.exception.RicercaDocumentoException;
import it.csi.tsddr.tsddrbl.vo.DocumentoProtocollatoVO;

public interface ManageRicercaDocumentoHelper{
	
	
	public DocumentoProtocollatoVO ricercaDocumentoProtocollato(String numProtocollo) throws RicercaDocumentoException;


}
