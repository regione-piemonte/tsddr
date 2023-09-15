/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui.helper;

import it.csi.tsddr.tsddrbl.integration.doqui.bean.RequestProtocollaDocumentoFisico;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.ResponseProtocollaDocumento;
import it.csi.tsddr.tsddrbl.integration.doqui.exception.ProtocollaDocumentoException;
import it.csi.tsddr.tsddrbl.vo.parametroacaris.ParametroAcarisDTO;

public interface ManageProtocollaDocumentoHelper {
	
	public ResponseProtocollaDocumento protocollaDocumentoFisico(RequestProtocollaDocumentoFisico request, ParametroAcarisDTO parametri/*,String rootActa, String soggettoActa, boolean isProtocollazioneInUscitaSenzaDocumento*/) throws ProtocollaDocumentoException;
	
	
}
