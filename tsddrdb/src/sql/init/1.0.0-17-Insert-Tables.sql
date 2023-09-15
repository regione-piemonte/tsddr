INSERT INTO tsddr_t_report(id_report, desc_report, xml_report, data_inizio_validita) VALUES
(1, 'DICHIARAZIONE ANNUALE', 'DichiarazioneAnnuale.jrxml', '2022-01-01'),
(2, 'RICHIESTA PAGAM MIS RID', 'Rischiesta_MR_R.jrxml', '2022-01-01'),
(3, 'DICHIARAZIONE PAGAM MIS RID', 'Dichiarazione_MR_D.jrxml', '2022-01-01');

INSERT INTO tsddr_t_report_dett(id_dettaglio, id_report, cod_campo, id_tipo_campo, testo, logo, firma) VALUES
(1, 1, 'H_S_1', 1, 'DIREZIONE RISORSE FINANZIARIE E PATRIMONIO', null, null),
(2, 1, 'H_S_2', 1, 'SETTORE POLITICHE FISCALI', null, null),
(3, 1, 'H_S_3', 1, 'E CONTENZIOSO AMMINISTRATIVO', null, null),
(4, 1, 'H_S_4', 1, 'MOD A1103A TS DA 2019', null, null),
(5, 1, 'TITOLO', 1, 'TRIBUTO SPECIALE', null, null), 
(6, 1, 'H_C_1', 1, 'PER IL CONFERIMENTO IN DISCARICA DEI RIFIUTI', null, null),
(7, 1, 'H_C_2', 1, 'QUANTITA'' DEI RIFIUTI CONFERITI E VERSAMENTI ESEGUITI', null, null),
(8, 1, 'H_D_1', 1, 'DICHIARAZIONE PER L''ANNO', null, null),
(9, 1, 'H_C_3', 1, null, null, null),
(10, 1, 'H_D_2', 1, null, null, null),
(11, 1, 'LOGO RP', 2, null, 'LogoRP.jpg', null), 
(12, 1, 'SEZ_DICH', 1, 'Dichiarante', null, null),
(13, 1, 'SEZ_L_R', 1, 'Legale Rappresentante', null, null),
(14, 1, 'SEZ_IMP', 1, 'Impianto', null, null),
(15, 1, 'SEZ_P_A', 1, 'Provvedimento Autorizzativo', null, null),
(16, 1, 'SEZ_L_DOC', 1, 'LUOGO DI CONSERVAZIONE DELLE SCRITTURE CONTABILI RELATIVE ALLA PRESENTE DICHIARAZIONE', null, null),
(17, 1, 'SEZ_CONF', 1, 'Quantità di rifiuti conferiti, liquidazione del tributo e versamenti eseguiti', null, null),
(18, 1, 'SEZ_VERS', 1, 'Versamenti eseguiti', null, null),
(19, 1, 'SEZ_VERS', 1, 'Elenco dei soggetti che hanno versato in misura ridotta (tipologie 1r e 2Ar2)', null, null),
(20, 1, 'SEZ_ANN', 1, 'Annotazioni e comunicazioni del dichiarante:', null, null),
(21, 1, 'SEZ_PIE', 1, 'I dati riportati nella presente dichiarazione corrispondono alle registrazioni dei conferimenti.', null, null),
(22, 1, 'FIRMA', 2, null, null, 'Firma.jpg'),
(23, 1, 'SEZ_PIE_1', 1, 'Il documento è presentato mediante sottoscrizione identificativa con il sistema SPID/CIE/CNS ai sensi dell''articolo 65, comma 1, lettera b) del D.Lgs 82/2005  - Codice dell''Amministrazione Digitale.', null, null),
(24, 1, 'INFO_T', 1, 'NOTA INFORMATIVA SUL TRATTAMENTO DEI DATI PERSONALI', null, null),
(25, 1, 'INFO_D', 1, 'I dati di natura personale acquisiti mediante il presente modello sono rilevati al solo fine di consentire l''istruttoria della domanda cui il modello medesimo si riferisce, e verranno trattati con modalità prevalentemente informatiche e con logiche rispondenti alle finalità da perseguire, anche mediante la verifica dei dati esposti con altri dati in possesso della Regione, del Ministero dell’economia e delle finanze e di altri enti quali, ad esempio, gli enti locali ed altre pubbliche istituzioni.
 ● Titolare del trattamento dei dati è la Regione Piemonte, direzione risorse finanziarie e patrimonio, settore politiche fiscali e contenzioso amministrativo, e presso detta struttura regionale è possibile ottenere informazioni sui responsabili; sempre presso tale struttura l’interessato potrà accedere ai propri dati per verificarne l’utilizzo o, eventualmente, correggerli, aggiornarli nei limiti previsti dalla legge ed anche per cancellarli od opporsi al loro trattamento se trattati in violazione della legge.
● La Regione, in quanto soggetto pubblico, non deve acquisire il consenso degli interessati per trattare i loro dati personali.', null, null),
(26, 2, 'H_S_1', 1, 'DIREZIONE RISORSE FINANZIARIE E PATRIMONIO', null, null),
(27, 2, 'H_S_2', 1, 'SETTORE POLITICHE FISCALI', null, null),
(28, 2, 'H_S_3', 1, 'E CONTENZIOSO AMMINISTRATIVO', null, null),
(29, 2, 'H_S_4', 1, 'MOD. TS-MR-R-2019', null, null),
(30, 2, 'TITOLO', 1, 'TRIBUTO SPECIALE', null, null),
(31, 2, 'H_C_1', 1, 'PER IL CONFERIMENTO IN DISCARICA DEI RIFIUTI', null, null),
(32, 2, 'H_C_2', 1, 'RICHIESTA DI AMMISSIONE AL PAGAMENTO IN MISURA RIDOTTA', null, null),
(33, 2, 'H_D_1', 1, 'ANNO', null, null),
(34, 2, 'H_C_3', 1, null, null, null), 
(35, 2, 'H_D_2', 1, null, null, null);

INSERT INTO tsddr_d_sezioni(id_sezione, desc_sezione, data_inizio_validita) VALUES
(1, '4.1 - Descrizione sommaria', '2022-01-01'),
(2, '4.2 - Elenco dei rifiuti in ingresso alla linea impiantistica effettivamente sottoposti ad attività di selezione automatica, riciclaggio e compostaggio – r.i.i', '2022-01-01'),
(3, '4.3 - Elenco dei materiali in uscita dalla linea impiantistica con indicazione dei quantitativi annui derivanti dai trattamenti sopra indicati – mat.', '2022-01-01'),
(4, '4.4 - Elenco delle tipologie di rifiuti recuperabili in uscita dalla linea impiantistica derivanti dai trattamenti sopra indicati – r.r.u.', '2022-01-01'),
(5, '4.5 - Elenco dei rifiuti in uscita inviati a smaltimento o recupero, a esclusione dei rifiuti avviati a impianti che effettuano il recupero di materia conclusivo o del percolato – r.u.', '2022-01-01'),
(6, '3 - Richiesta di ammissione al pagamento in misura ridotta', '2022-01-01');

UPDATE tsddr_c_parametri_acaris SET nome_param_acaris = 'Applica annotazione a classificazione corrente' WHERE id_parametro_acaris = 13;

INSERT INTO tsddr_c_parametri (id_parametro, nome_parametro, valore_parametro_s) VALUES 
(8, 'ACTA_SERVER', 'tst-applogic.reteunitaria.piemonte.it'),
(9, 'ACTA_CONTEXT', '/actasrv/'),
(10, 'APIMANAGER_OAUTHURL', 'https://tst-api-piemonte.ecosis.csi.it/token'),
(11, 'APIMANAGER_CONSUMERKEY', 'hCE3PaxXi1n8jrKM__yf7IBnrB4a'),
(12, 'APIMANAGER_CONSUMERSECRET', 'MzFG7GUx7sr1EFcyahAcP8n6c6Ya'),
(13, 'APIMANAGER_URL', 'http://tst-api-piemonte.ecosis.csi.it/documentale/acaris-test-'),
(14, 'APIMANAGER_URL_END', '/v1');

INSERT INTO tsddr_c_parametri (id_parametro, nome_parametro, valore_parametro_n) VALUES 
(15, 'ACTA_PORT', 80);

-- Da modificare - impostate con quelle conam
INSERT INTO tsddr_c_parametri (id_parametro, nome_parametro, valore_parametro_s) VALUES 
(16, 'ACTA_ID_AOO', 'A11000'),
(17, 'ACTA_CF', 'ZNLFRZ59L25H620O'),
(18, 'ACTA_ID_STRUTTURA', 'A1103A'),
(19, 'ACTA_ID_NODO', 'A11103A-O5'),
(20, 'ACTA_CODE_FRUITORE', 'TSDDR'),
(21, 'ACTA_APP_KEY_FRUITORE', '-26/77/91/68/-124/16/21/62/-123/-105/-119/-18/26/72/53/108'),
(22, 'ACTA_REPO_NAME_FRUITORE', 'RP201209 Regione Piemonte - Agg. 09/2012'),
(23, 'ACTA_CODE_ENTE_FRUITORE', 'RP201209');

