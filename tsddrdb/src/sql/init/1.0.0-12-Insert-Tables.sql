INSERT INTO tsddr_t_messaggi(id_messaggio, cod_msg, id_tipo_msg, titolo_msg, testo_msg) VALUES
(50, 'E012', 3, 'Linee duplicate', 'La linea d''impianto selezionata è già presente nell''impianto.'),
(51, 'A016', 2, 'Attenzione!', 'Conferma la cancellazione della Dichiarazione Annuale Selezionata.
Vuoi Procedere?'),
(52, 'I005', 4, 'Informazione', 'L''importo di CONGUAGLIO risulta differente dal SALDO calcolato anno precedente.'),
(53, 'E013', 3, 'Rifiuto duplicato', 'Il rifiuto selezionato è già presente nella dichiarazione'),
(54, 'A017', 2, 'Attenzione!', 'Non è stato inserito alcun dato nella sezione Elenco dei soggetti che hanno richiesto di pagare in misura ridotta. 
Si vuol procedere ugualmente con l''invio della dichiarazione?'),
(55, 'A018', 2, 'Attenzione!', 'Non è stato valorizzato l''attributo Obiettivi raggiunti per ogni soggetto che ha richiesto di versare in misura ridotta. Si vuol procedere ugualmente con l''invio della dichiarazione?');

INSERT INTO tsddr_par_file(id_par_file, file, nome_campo, pos_da, pos_a, lunghezza, formato, null_value) VALUES  
(1, 'Stati_esteri_istat.txt', 'ISTAT_STATO', 1, 3, 3, null, null),
(2, 'Stati_esteri_istat.txt', 'ID_STATO', 4, 23, 20, null, null),
(3, 'Stati_esteri_istat.txt', 'ID_STATO_NEXT', 24, 43, 20, null, null),
(4, 'Stati_esteri_istat.txt', 'ID_STATO_PREV', 44, 63, 20, null, null),
(5, 'Stati_esteri_istat.txt', 'D_START', 64, 73, 10, 'gg/mm/aaaa', null),
(6, 'Stati_esteri_istat.txt', 'D_STOP', 74, 83, 10, 'gg/mm/aaaa', '00/00/0000'),
(7, 'Stati_esteri_istat.txt', 'DESC_STATO', 84, 183, 100, null, null),
(8, 'Stati_esteri_istat.txt', 'COD_CONTINENTE', 184, 186, 3, null, null),
(9, 'Stati_esteri_istat.txt', 'DESC_CONTINENTE', 187, 211, 25, null, null),
(10, 'Stati_esteri_istat.txt', 'R_STATUS', 212, 212, 1, null, null),
(11, 'Stati_esteri_istat.txt', 'FLAG_UE', 213, 213, 1, null, null), 
(12, 'Stati_esteri_istat.txt', 'COD_AT', 214, 217, 4, null, null),
(13, 'Limiti_Amministrativi.txt', 'ID_COMUNE', 1, 20, 20, null, null), 
(14, 'Limiti_Amministrativi.txt', 'ID_COMUNE_NEXT', 21, 40, 20, null, null),
(15, 'Limiti_Amministrativi.txt', 'ID_COMUNE_PREV', 41, 60, 20, null, null),
(16, 'Limiti_Amministrativi.txt', 'D_START', 61, 70, 10, 'gg/mm/aaaa', null), 
(17, 'Limiti_Amministrativi.txt', 'D_STOP', 71, 80, 10, 'gg/mm/aaaa', '00/00/0000'),
(18, 'Limiti_Amministrativi.txt', 'R_STATUS', 81, 81, 1, null, null),
(19, 'Limiti_Amministrativi.txt', 'COD_CATASTO', 82, 85, 4, null, null),
(20, 'Limiti_Amministrativi.txt', 'ISTAT_COMUNE', 86, 91, 6, null, null), 
(21, 'Limiti_Amministrativi.txt', 'DESC_COMUNE', 92, 153, 62, null, null),
(22, 'Limiti_Amministrativi.txt', 'CAP', 154, 158, 5, null, null), 
(23, 'Limiti_Amministrativi.txt', 'ALTITUDINE', 159, 178, 20, null, null),
(24, 'Limiti_Amministrativi.txt', 'SUPERFICIE_HM2', 179, 198, 20, null, null), 
(25, 'Limiti_Amministrativi.txt', 'ISTAT_PROVINCIA', 199, 201, 3, null, null), 
(26, 'Limiti_Amministrativi.txt', 'DESC_PROVINCIA', 202, 301, 100, null, null), 
(27, 'Limiti_Amministrativi.txt', 'SIGLA_PROV', 302, 305, 4, null, null),
(28, 'Limiti_Amministrativi.txt', 'ISTAT_ZONA_ALTIMETRICA', 306, 306, 1, null, null),
(29, 'Limiti_Amministrativi.txt', 'DESC_ZONA_ALTIMETRICA', 307, 506, 200, null, null), 
(30, 'Limiti_Amministrativi.txt', 'ISTAT_REGIONE', 507, 508, 2, null, null),
(31, 'Limiti_Amministrativi.txt', 'DESC_REGIONE', 509, 608, 100, null, null), 
(32, 'Limiti_Amministrativi.txt', 'COD_STATO', 609, 611, 3, null, null), 
(33, 'Limiti_Amministrativi.txt', 'DESC_STATO', 612, 675, 64, null, null);

INSERT INTO tsddr_d_stati_dichiarazioni(id_stato_dichiarazione, descr_stato_dichiarazione, data_inizio_validita) VALUES
(1, 'Bozza', '2022-01-01'),
(2, 'Inviata (Protocollata)', '2022-01-01'),
(3, 'Eliminata', '2022-01-01');

INSERT INTO tsddr_d_unita_misura(id_unita_misura, desc_unita_misura, data_inizio_validita) VALUES
(1, '€/Kg', '2022-01-01'),
(2, 'Tonnellate', '2022-01-01'),
(3, 'Quintali', '2022-01-01'),
(4, 'KG', '2022-01-01');

INSERT INTO tsddr_d_periodi(id_periodo, desc_periodo, data_inizio_validita) VALUES
(1, 'I TRIMESTRE', '2022-01-01'),
(2, 'II TRIMESTRE', '2022-01-01'),
(3, 'III TRIMESTRE', '2022-01-01'),
(4, 'IV TRIMESTRE', '2022-01-01'),
(5, 'CONGUAGLIO', '2022-01-01');

INSERT INTO tsddr_t_rifiuti_tariffe(id_rifiuto_tariffa, id_tipo_rifiuto, id_tipologia_2, id_tipologia_3, importo, flag_riduzione, descrizione, data_inizio_validita) VALUES
(1, '1', '1n', null, 0.009, false, 'Rifiuti ammissibili al conferimento in discarica per rifiuti inerti (comma 1, lettera b, n. 1)', '2022-01-01'),
(2, '1', '1r', null, 0.018, true, 'Scarti e sovvalli ammissibili al conferimento in discarica per inerti (comma 3) – 20% di 1n RIDUZIONE SOGGETTA A CONDIZIONE', '2022-01-01'),
(3, '2', '2A', '2An', 0.02582, false, 'Rifiuti ammissibili al conferimento in discarica per rifiuti non pericolosi (comma 1, lettera b, n. 2)', '2022-01-01'),
(4, '2', '2A', '2Ar1', 0.005164, false, 'Rifiuti non pericolosi smaltiti in impianti di incenerimento senza recupero di energia (comma 2) – 20% di 2An', '2022-01-01'),
(5, '2', '2A', '2Ar2', 0.005164, true, 'Scarti e sovvalli ammissibili al conferimenti in discarica per rifiuti non pericolosi (comma 3) – 20% di 2An RIDUZIONE SOGGETTA A CONDIZIONE', '2022-01-01'),
(6, '2', '2A', '2Ar3', 0.005164, false, 'Fanghi ammissibili al conferimento in discarica per rifiuti non pericolosi (comma 2) – 20% di 2An', '2022-01-01'),
(7, '2', '2B', '2Bn', 0.01291, false, 'Rifiuti urbani e speciali derivanti dal trattamento dei rifiuti ammissibili al conferimento in discarica per rifiuti non pericolosi (comma 1, lettera b, n. 2)', '2022-01-01'),
(8, '2', '2B', '2Ba20', 0.015492, true, '» 2Bn, se la percentuale di raccolta differenziata (RD) è inferiore al 65% addizionale del 20%', '2022-01-01'),
(9, '2', '2B', '2Br30', 0.009037, true, '» 2Bn, se la percentuale di raccolta differenziata (RD) è superiore al 65% e inferiore al 75% riduzione del 30%', '2022-01-01'),
(10, '2', '2B', '2Br40', 0.007746, true, '» 2Bn, se la percentuale di raccolta differenziata (RD) è superiore al 75% e inferiore all’80% riduzione del 40%', '2022-01-01'),
(11, '2', '2B', '2Br50', 0.006455, true, '» 2Bn, se la percentuale di raccolta differenziata (RD) è superiore al 75% e inferiore all’80% riduzione del 50%', '2022-01-01'),
(12, '2', '2B', '2Br60', 0.00517, true, '» 2Bn, se la percentuale di raccolta differenziata (RD) è superiore all’85% e inferiore al 90% riduzione del 60%', '2022-01-01'),
(13, '2', '2B', '2Br70', 0.00517, true, '» 2Bn, se la percentuale di raccolta differenziata (RD) è superiore al 90% riduzione del 70% (*)', '2022-01-01'),
(14, '3', '3A', '3An', 0.019, false, 'Rifiuti ammissibili al conferimento in discarica per rifiuti pericolosi (comma 1, lettera b, n. 3)', '2022-01-01'),
(15, '3', '3A', '3A 3Ar1', 0.0038, true, 'Rifiuti pericolosi smaltiti in impianti di incenerimento senza recupero di energia (comma 2) – 20% di 3An', '2022-01-01'),
(16, '3', '3A', '3Ar2', 0.0038, true, 'Fanghi ammissibili al conferimento in discarica per rifiuti pericolosi (comma 3) – 20% di 3An', '2022-01-01'),
(17, '3', '3B', null, 0.01, false, 'Rifiuti ammissibili al conferimento in discarica per rifiuti pericolosi contenenti amianto (comma1, lettera b, n. 3)', '2022-01-01');

INSERT INTO tsddr_d_tipi_campi(id_tipo_campo, desc_tipo_campo, data_inizio_validita) VALUES
(1, 'TITOLO', '2022-01-01'),
(2, 'HEADER', '2022-01-01'),
(3, 'FOOTER', '2022-01-01'),
(4, 'SEZIONE', '2022-01-01'),
(5, 'NOTA_TITOLO', '2022-01-01'),
(6, 'NOTA_TESTO', '2022-01-01');

/* desc_report troppo piccolo (20), data fine validita deve essere nullabile
INSERT INTO tsddr_t_report(id_report, desc_report, xml_report, data_inizio_validita) VALUES
(1, 'DICHIARAZIONE ANNUALE', 'DichiarazioneAnnuale.jrxml', '2022-01-01'),
(2, 'RICHIESTA PAGAM MIS RID', 'Rischiesta_MR_R.jrxml', '2022-01-01'),
(3, 'DICHIARAZIONE PAGAM MIS RID', 'Dichiarazione_MR_D.jrxml', '2022-01-01');
*/

/* cod_campo è di tipo number ma vengono inserite stringhe, testo deve essere almeno 1500, testo, logo e firma devono essere nullabili
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
*/

INSERT INTO tsddr_c_parametri_acaris(id_parametro_acaris, nome_param_acaris, valore_param_acaris_s, valore_param_acaris_n, valore_param_acaris_b) VALUES
(1, 'Cartaceo', null, null, false),
(2, 'Collocazione cartacea', null, null, null),
(3, 'Copia cartacea', null, null, false),
(4, 'Tipo documento', 'SEMPLICE', null, null), 
(5, 'Origine interna', null, null, false),
(6, 'Presenza file', null, null, true),
(7, 'Tipologia dati contenuti - Dati Personali', null, null, true),
(8, 'Tipologia dati contenuti - Dati Sensibili', null, null, false),
(9, 'Tipologia dati contenuti - Dati Riservati', null, null, false),
(10, 'Annotazione', 'Text Annotazione', null, null), 
(11, 'Annotazione formale', null, null, true),
(12, 'Applica annotazione a intero documento', null, null, true),
(13, 'Applica annotazione a classificazione corrent', null, null, false),
(14, 'Stato registrato', null, null, true),
(15, 'Stato modificabile', null, null, false),
(16, 'Stato definitivo', null, null, true),
(17, 'Destinatario giuridico', 'REGIONE PIEMONTE', null, null), 
(18, 'Autenticato', null, null, false),
(19, 'Autenticato con firma autentica', null, null, false),
(20, 'Autenticato copia autentica', null, null, false),
(21, 'Forma documentaria', 'Dichiarazione', null, null), 
(22, 'AOO responsabile affare', null, null, null), 
(23, 'Struttura responsabile affare', null, null, null), 
(24, 'Applicativo alimentante', 'TSDDR', null, null), 
(25, 'Vital record code', 'MEDIO', null, null), 
(26, 'Tipo registrazione - arrivo', null, null, true),
(27, 'Ente', null, null, null), 
(28, 'AOO', null, null, null), 
(29, 'Mittente', null, null, null), 
(30, 'Destinatario', null, null, null), 
(31, 'Riservata', null, null, false),
(32, 'Stato di efficacia', 'PERFETTO ED EFFICACE', null, null), 
(33, 'Tipo', 'PERFETTO ED EFFICACE', null, null), 
(34, 'Composizione', 'DOCUMENTO SINGOLO', null, null), 
(35, 'Molteplicità della composizione', null, null, false),
(36, 'Archivio corrente', null, null, true),
(37, 'Utente di creazione', 'TSDDR', null, null); 

INSERT INTO tsddr_d_tipo_doc(id_tipo_doc, desc_tipo_doc, data_inizio_validita) VALUES
(1, 'Richiesta di ammissione al pagamento in misura ridotta', '2022-01-01'),
(2, 'Dichiarazione di raggiungimento degli obiettivi', '2022-01-01');

/* desc_sezione è troppo piccolo (100)
INSERT INTO tsddr_d_sezioni(id_sezione, desc_sezione, data_inizio_validita) VALUES
(1, '4.1 - Descrizione sommaria', '2022-01-01'),
(2, '4.2 - Elenco dei rifiuti in ingresso alla linea impiantistica effettivamente sottoposti ad attività di selezione automatica, riciclaggio e compostaggio – r.i.i', '2022-01-01'),
(3, '4.3 - Elenco dei materiali in uscita dalla linea impiantistica con indicazione dei quantitativi annui derivanti dai trattamenti sopra indicati – mat.', '2022-01-01'),
(4, '4.4 - Elenco delle tipologie di rifiuti recuperabili in uscita dalla linea impiantistica derivanti dai trattamenti sopra indicati – r.r.u.', '2022-01-01'),
(5, '4.5 - Elenco dei rifiuti in uscita inviati a smaltimento o recupero, a esclusione dei rifiuti avviati a impianti che effettuano il recupero di materia conclusivo o del percolato – r.u.', '2022-01-01'),
(6, '3 - Richiesta di ammissione al pagamento in misura ridotta', '2022-01-01');
*/
