/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

public abstract class RepositoryUtil {

	public static final String TDP_PROFILO_VALIDITY_CHECK = "tdp.dataInizioValidita <= :currentDate AND "
			+ "(tdp.dataFineValidita IS NULL OR tdp.dataFineValidita >= :currentDate) AND "
			+ "tdp.dataDelete IS NULL AND "
			+ "tdp.idUserDelete IS NULL ";
	
	public static final String TDP_PROFILO_DATA_DELETE_ID_USER_VALIDITY_CHECK = "tdp.dataDelete IS NULL AND "
			+ "tdp.idUserDelete IS NULL ";
	
	public static final String TDP_PROFILO_ID_CHECK = "tdp.idProfilo != 4 ";
	
	public static final String TTDS_DATISOGG_DELETE_VALIDITY_CHECK = "ttds.dataDelete IS NULL AND "
			+ "ttds.idUserDelete IS NULL ";

	public static final String TTU_UTENTE_DELETE_VALIDITY_CHECK = "ttu.dataDelete IS NULL AND "
			+ "ttu.idUserDelete IS NULL ";
	
	public static final String TTU_UTENTE_VALIDITY_CHECK = "ttu.dataInizioValidita <= :currentDate AND "
			+ "(ttu.dataFineValidita IS NULL OR ttu.dataFineValidita >= :currentDate) AND "
			+ "ttu.dataDelete IS NULL AND "
			+ "ttu.idUserDelete IS NULL ";
	
	public static final String TDF_FUNZIONE_VALIDITY_CHECK = "tdf.dataInizioValidita <= :currentDate AND "
			+ "(tdf.dataFineValidita IS NULL OR tdf.dataFineValidita >= :currentDate) AND "
			+ "tdf.dataDelete IS NULL AND "
			+ "tdf.idUserDelete IS NULL ";
	
	public static final String TTM_MENU_VALIDITY_CHECK = "ttm.dataDelete IS NULL AND "
			+ "ttm.idUserDelete IS NULL ";

	public static final String TRUP_RUTENTIPROF_VALIDITY_CHECK = "trup.dataInizioValidita <= :currentDate AND "
			+ "(trup.dataFineValidita IS NULL OR trup.dataFineValidita >= :currentDate) AND "
			+ "trup.dataDelete IS NULL AND "
			+ "trup.idUserDelete IS NULL ";

	public static final String TRFP_RFUNZPROF_VALIDITY_CHECK = "trfp.dataInizioValidita <= :currentDate AND "
			+ "(trfp.dataFineValidita IS NULL OR trfp.dataFineValidita >= :currentDate) AND "
			+ "trfp.dataDelete IS NULL AND "
			+ "trfp.idUserDelete IS NULL ";
	
	public static final String TDTP_TIPO_PROFILO_VALIDITY_CHECK = "tdtp.dataInizioValidita <= :currentDate AND "
			+ "(tdtp.dataFineValidita IS NULL OR tdtp.dataFineValidita >= :currentDate) AND "
			+ "tdtp.dataDelete IS NULL AND "
			+ "tdtp.idUserDelete IS NULL ";
	
	public static final String TTG_GESTORE_VALIDITY_CHECK = "ttg.dataInizioValidita <= :currentDate AND "
			+ "(ttg.dataFineValidita IS NULL OR ttg.dataFineValidita >= :currentDate) AND "
			+ "ttg.dataDelete IS NULL AND "
			+ "ttg.idUserDelete IS NULL ";
	
	public static final String TTG_GESTORE_DELETE_VALIDITY_CHECK = "ttg.dataDelete IS NULL AND "
			+ "ttg.idUserDelete IS NULL ";
	
	public static final String TRUGP_RUTENTEGESTOREPROFILO_VALIDITY_CHECK = "trugp.dataInizioValidita <= :currentDate AND "
			+ "(trugp.dataFineValidita IS NULL OR trugp.dataFineValidita >= :currentDate) AND "
			+ "trugp.dataDelete IS NULL AND "
			+ "trugp.idUserDelete IS NULL ";
	
	public static final String TCP_PARAMETRO_DELETE_VALIDITY_CHECK = "tcp.dataDelete IS NULL AND "
			+ "tcp.idUserDelete IS NULL ";
	
	public static final String TTD_DOMANDA_DELETE_VALIDITY_CHECK = "ttd.dataDelete IS NULL AND "
			+ "ttd.idUserDelete IS NULL ";
	
	public static final String TDSD_STATO_DOMANDA_DELETE_VALIDITY_CHECK = "tdsd.dataDelete IS NULL AND "
			+ "tdsd.idUserDelete IS NULL ";
	
	public static final String TDSD_STATO_DOMANDA_VALIDITY_CHECK = "tdsd.dataInizioValidita <= :currentDate AND "
			+ "(tdsd.dataFineValidita IS NULL OR tdsd.dataFineValidita >= :currentDate) AND "
			+ "tdsd.dataDelete IS NULL AND "
			+ "tdsd.idUserDelete IS NULL ";
	
	public static final String TEDT_CASELLA_DELETE_VALIDITY_CHECK = "tedt.dataDelete IS NULL AND "
			+ "tedt.idUserDelete IS NULL ";
	
	public static final String TDS_SEDIME_VALIDITY_CHECK = "tds.dataInizioValidita <= :currentDate AND "
			+ "(tds.dataFineValidita IS NULL OR tds.dataFineValidita >= :currentDate) AND "
			+ "tds.dataDelete IS NULL AND "
			+ "tds.idUserDelete IS NULL ";
			
	public static final String TDC_COMUNE_VALIDITY_CHECK = "tdc.dataInizioValidita <= :currentDate AND "
			+ "(tdc.dataFineValidita IS NULL OR tdc.dataFineValidita >= :currentDate) AND "
			+ "tdc.dataDelete IS NULL AND "
			+ "tdc.idUserDelete IS NULL ";
	
	public static final String TTLR_LEGALE_RAPP_VALIDITY_CHECK = "ttlr.dataInizioValidita <= :currentDate AND "
			+ "(ttlr.dataFineValidita IS NULL OR ttlr.dataFineValidita >= :currentDate) AND "
			+ "ttlr.dataDelete IS NULL AND "
			+ "ttlr.idUserDelete IS NULL ";
	
	public static final String TDNG_NATURA_GIURIDICA_VALIDITY_CHECK = "tdng.dataInizioValidita <= :currentDate AND "
			+ "(tdng.dataFineValidita IS NULL OR tdng.dataFineValidita >= :currentDate) AND "
			+ "tdng.dataDelete IS NULL AND "
			+ "tdng.idUserDelete IS NULL ";
			
	public static final String TDPRV_PROVINCIA_VALIDITY_CHECK = "tdprv.dataInizioValidita <= :currentDate AND "
			+ "(tdprv.dataFineValidita IS NULL OR tdprv.dataFineValidita >= :currentDate) AND "
			+ "tdprv.dataDelete IS NULL AND "
			+ "tdprv.idUserDelete IS NULL ";
	
	public static final String TDSI_STATO_IMPIANTO_VALIDITY_CHECK = "tdsi.dataInizioValidita <= :currentDate AND "
			+ "(tdsi.dataFineValidita IS NULL OR tdsi.dataFineValidita >= :currentDate) AND "
			+ "tdsi.dataDelete IS NULL AND "
			+ "tdsi.idUserDelete IS NULL ";
	
	public static final String TDN_NAZIONE_VALIDITY_CHECK = "tdn.dataInizioValidita <= :currentDate AND "
			+ "(tdn.dataFineValidita IS NULL OR tdn.dataFineValidita >= :currentDate) AND "
			+ "tdn.dataDelete IS NULL AND "
			+ "tdn.idUserDelete IS NULL ";
	
	public static final String TDTI_TIPO_IMPIANTO_VALIDITY_CHECK = "tdti.dataInizioValidita <= :currentDate AND "
			+ "(tdti.dataFineValidita IS NULL OR tdti.dataFineValidita >= :currentDate) AND "
			+ "tdti.dataDelete IS NULL AND "
			+ "tdti.idUserDelete IS NULL ";
	
	public static final String TTINDR_INDIRIZZO_DELETE_VALIDITY_CHECK = "ttindr.dataDelete IS NULL AND "
			+ "ttindr.idUserDelete IS NULL ";
		
	public static final String TRIL_RIMPIANTOLINEA_VALIDITY_CHECK = "tril.dataInizioValidita <= :currentDate AND "
			+ "(tril.dataFineValidita IS NULL OR tril.dataFineValidita >= :currentDate) AND "
			+ "tril.dataDelete IS NULL AND "
			+ "tril.idUserDelete IS NULL ";
	
	public static final String TRIL_RIMPIANTOLINEA_DELETE_VALIDITY_CHECK = "tril.dataDelete IS NULL AND "
			+ "tril.idUserDelete IS NULL ";
	
	public static final String TDAPREGRESSO_VALIDITY_CHECK = "tdaPregresso.dataInizioValidita <= :currentDate AND "
			+ "(tdaPregresso.dataFineValidita IS NULL OR tdaPregresso.dataFineValidita >= :currentDate) AND "
			+ "tdaPregresso.dataDelete IS NULL AND "
			+ "tdaPregresso.idUserDelete IS NULL ";
				
	public static final String TDTPROVV_TIPO_PROVVEDIMENTO_VALIDITY_CHECK = "tdtprovv.dataInizioValidita <= :currentDate AND "
	+ "(tdtprovv.dataFineValidita IS NULL OR tdtprovv.dataFineValidita >= :currentDate) AND "
	+ "tdtprovv.dataDelete IS NULL AND "
	+ "tdtprovv.idUserDelete IS NULL ";

	public static final String TTA_ATTO_DELETE_VALIDITY_CHECK = "tta.dataDelete IS NULL AND "
			+ "tta.idUserDelete IS NULL ";
	
	public static final String TTIMP_IMPIANTO_VALIDITY_CHECK = "ttimp.dataInizioValidita <= :currentDate AND "
			+ "(ttimp.dataFineValidita IS NULL OR ttimp.dataFineValidita >= :currentDate) AND "
			+ "ttimp.dataDelete IS NULL AND "
			+ "ttimp.idUserDelete IS NULL ";	
	
	public static final String TTIMP_IMPIANTO_DELETE_VALIDITY_CHECK = "ttimp.dataDelete IS NULL AND ttimp.idUserDelete IS NULL ";
	
	public static final String TTL_LINEA_VALIDITY_CHECK = "ttl.dataInizioValidita <= :currentDate AND "
			+ "(ttl.dataFineValidita IS NULL OR ttl.dataFineValidita >= :currentDate) AND "
			+ "ttl.dataDelete IS NULL AND "
			+ "ttl.idUserDelete IS NULL ";	
	
	public static final String TTSL_SOTTO_LINEA_VALIDITY_CHECK = "ttsl.dataInizioValidita <= :currentDate AND "
			+ "(ttsl.dataFineValidita IS NULL OR ttsl.dataFineValidita >= :currentDate) AND "
			+ "ttsl.dataDelete IS NULL AND "
			+ "ttsl.idUserDelete IS NULL ";
	
	public static final String TDTIND_TIPO_INDIRIZZO_VALIDITY_CHECK = "tdtind.dataInizioValidita <= :currentDate AND "
			+ "(tdtind.dataFineValidita IS NULL OR tdtind.dataFineValidita >= :currentDate) AND "
			+ "tdtind.dataDelete IS NULL AND "
			+ "tdtind.idUserDelete IS NULL ";
	
	public static final String TTDA_DICH_ANNUALE_DELETE_VALIDITY_CHECK = "ttda.dataDelete IS NULL AND "
			+ "ttda.idUserDelete IS NULL ";
	
	public static final String TTC_CONFERIMENTO_VALIDITY_CHECK = "ttc.dataInizioValidita <= :currentDate AND "
			+ "(ttc.dataFineValidita IS NULL OR ttc.dataFineValidita >= :currentDate) AND "
			+ "ttc.dataDelete IS NULL AND "
			+ "ttc.idUserDelete IS NULL ";
	
	public static final String TTRT_RIFIUTO_TARIFFA_DELETE_VALIDITY_CHECK =  "ttrt.dataDelete IS NULL AND ttrt.idUserDelete IS NULL ";
	
	public static final String TTRT_RIFIUTO_TARIFFA_VALIDITY_YEAR_CHECK = "TO_CHAR (ttrt.dataInizioValidita, 'YYYY') <= :currentYear AND "
			+ "(ttrt.dataFineValidita IS NULL OR TO_CHAR (ttrt.dataFineValidita, 'YYYY') >= :currentYear) AND "
			+ "ttrt.dataDelete IS NULL AND "
			+ "ttrt.idUserDelete IS NULL ";
	

	public static final String TTRT_RIFIUTO_TARIFFA_VALIDITY_CHECK = "ttrt.dataInizioValidita <= :currentDate AND "
			+ "(ttrt.dataFineValidita IS NULL OR ttrt.dataFineValidita >= :currentDate) AND "
			+ "ttrt.dataDelete IS NULL AND "
			+ "ttrt.idUserDelete IS NULL ";
	
	
	public static final String TTSM_SOGGETTO_MR_DELETE_VALIDITY_CHECK = "ttsm.dataDelete IS NULL AND ttsm.idUserDelete IS NULL ";
	
	public static final String TDPER_PERIODO_DELETE_VALIDITY_CHECK = "tdper.dataDelete IS NULL AND tdper.idUserDelete IS NULL ";
	
	public static final String TDSDICH_STATO_DICHIARAZIONE_DELETE_VALIDITY_CHECK = "tdsdich.dataDelete IS NULL AND tdsdich.idUserDelete IS NULL ";
	
	public static final String TDUM_UNITA_MISURA_DELETE_VALIDITY_CHECK = "tdum.dataDelete IS NULL AND tdum.idUserDelete IS NULL ";
	
	public static final String TCPA_PARAMETRO_ACARIS_DELETE_VALIDITY_CHECK = "tcpa.dataDelete IS NULL AND tcpa.idUserDelete IS NULL ";
	
	public static final String TDE_EER_VALIDITY_CHECK = "tde.dataInizioValidita <= :currentDate AND "
            + "(tde.dataFineValidita IS NULL OR tde.dataFineValidita >= :currentDate) AND "
            + "tde.dataDelete IS NULL AND "
            + "tde.idUserDelete IS NULL ";
	
	public static final String TDE_EER_VALIDITY_YEAR_CHECK = "TO_CHAR (tde.dataInizioValidita, 'YYYY')  <= :currentYear AND "
            + "(tde.dataFineValidita IS NULL OR TO_CHAR (tde.dataFineValidita, 'YYYY') >= :currentYear) AND "
            + "tde.dataDelete IS NULL AND "
            + "tde.idUserDelete IS NULL ";
	
	
	public static final String TDTD_TIPO_DOC_VALIDITY_CHECK = "tdtd.dataInizioValidita <= :currentDate AND "
	        + "(tdtd.dataFineValidita IS NULL OR tdtd.dataFineValidita >= :currentDate) AND "
	        + "tdtd.dataDelete IS NULL AND "
	        + "tdtd.idUserDelete IS NULL ";
	
	public static final String TDSEZ_SEZIONE_VALIDITY_CHECK = "tdsez.dataInizioValidita <= :currentDate AND "
            + "(tdsez.dataFineValidita IS NULL OR tdsez.dataFineValidita >= :currentDate) AND "
            + "tdsez.dataDelete IS NULL AND "
            + "tdsez.idUserDelete IS NULL ";
	
}
