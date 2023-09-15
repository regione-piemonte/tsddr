INSERT INTO tsddr_c_parametri (id_parametro, nome_parametro, valore_parametro_s) VALUES 
(1, 'codiceProdotto', 'TSDDR'),
(2, 'codiceLineaCliente', 'RP-01'),
(3, 'codiceAmbiente', 'DEV'),
(4, 'codiceUnitaInstallazione', 'tsddrbl'),
(5, 'LinkPrivacy', 'https://servizi.regione.piemonte.it/media/415/download'),
(7, 'Valori email non validi', 'pec,legalmail');

INSERT INTO tsddr_c_parametri (id_parametro, nome_parametro, valore_parametro_n) VALUES 
(6, 'ImportoMinimo', 0.00517);

INSERT INTO tsddr_d_funzioni (id_funzione, data_inizio_validita, cod_funzione, desc_funzione) VALUES 
(0, '2022-01-01', 'ALL_00', 'Home'),
(1, '2022-01-01', 'FO_001', 'Accreditamento'),
(2, '2022-01-01', 'FO_002', 'Nuova Domanda'),
(3, '2022-01-01', 'FO_003', 'Gestione Domande'),
(4, '2022-01-01', 'BO_001', 'Domande Accreditamento'),
(5, '2022-01-01', 'AM_001', 'Gestione Utenti e Profili'),
(6, '2022-01-01', 'AM_002', 'Gestione Utenti'),
(7, '2022-01-01', 'AM_003', 'Gestione Profili'),
(8, '2022-01-01', 'AM_004', 'Configurazione Profili'),
(9, '2022-01-01', 'AM_005', 'Associa Utenti a Profili'),
(10, '2022-01-01', 'SYS_001', 'Profilazione Utente'),
(11, '2022-01-01', 'ALL_01', 'Area Dati Personali'),
(12, '2022-01-01', 'AM_006', 'Gestori'),
(13, '2022-01-01', 'AM_007', 'Impianti'),
(14, '2022-01-01', 'ALL_02', 'Dichiarazione Annuale');

INSERT INTO tsddr_t_menu(id_menu, desc_voce_menu, livello, id_padre, id_funzione, descrizione_card) VALUES 
(1, 'Home', 1, 0, 0, null),
(2, 'Accreditamento', 1, 0, 1, null),
(3, 'Nuova Domanda', 2, 2, 2, 'Inserimento nuova domanda di Accreditamento'),
(4, 'Gestione Domande', 2, 2, 3, 'Gestione delle domande di Accreditamento'),
(5, 'Domanda Accreditamento', 1, 0, 4, 'Lavorazione delle domande di Accreditamento'),
(6, 'Gestione Utenti e Profili', 1, 0, 5, null), 
(7, 'Gestione Utenti', 2, 6, 6, 'Gestione degli Utenti del sistema'),
(8, 'Gestione Profili', 2, 6, 7, 'Gestione dei profili del sistema'),
(9, 'Configurazione Profili', 2, 6, 8, 'Gestione Funzionalità per Profilo'),
(10, 'Associa Utenti a Profili', 2, 6, 9, 'Gestione associazione Utenti a Profili'),
(11, 'Gestori', 1, 0, 12, 'Gestione dei Gestori degli Impianti del sistema'),
(12, 'Impianti', 1, 0, 13, 'Gestione degli Impianti e loro struttura e Atti'),
(13, 'Dichiarazioni Annuali', 1, 0, 14, 'Gestione Dichiarazioni Annuali Tributo Speciale per il conferimento in discarica dei rifiuti'),
(14, 'Richieste MR', 1, 0, 0, null),
(15, 'Dichiarazioni MR', 1, 0, 0, null);

INSERT INTO tsddr_t_note_info(id_nota_info, cod_nota_info, testo_info, etichetta_campo) VALUES
(1, 'PARAMETRO', 'Valorizza almeno un parametro di filtro e "Avvia Ricerca"', 'PARAMETRO'),
(2, 'PROFILO', 'Seleziona un Profilo su cui operare', 'PROFILO'),
(3, 'UTENTE', 'Compila i dati richiesti e seleziona "Inserisci Utente"', 'UTENTE'),
(4, 'INVIO DOMANDA', 'Compila i dati richiesti e seleziona "Inserisci Domanda"', 'INVIO DOMANDA'),
(5, 'ELENCO DOMANDE', 'Elenco Domande', 'ELENCO DOMANDE'),
(6, 'MODIFICA', 'Modifica i dati e Salva.', 'MODIFICA'),
(7, 'INSERISCI', 'Inserisci i dati richiesti.', 'INSERISCI'),
(8, 'VERSAMENTI', 'Riepilogo dei Vresamenti della Dichiarazione Annuale', 'VERSAMENTI'),
(9, 'SOGGETTI', 'Elenco dei Soggetti che hanno versato in misura ridotta (tipologie 1r e 2Ar2)', 'SOGGETTI'),
(10, 'ANNOTA', 'Annotazioni e comunicazioni del dichiarante', 'ANNOTA');

INSERT INTO tsddr_d_nature_giuridiche(id_natura_giuridica, desc_natura_giuridica, data_inizio_validita) VALUES
(1,	'Persona Giuridica', '2022-01-01'),
(2,	'Persona non Giuridica', '2022-01-01'),
(3,	'Altro', '2022-01-01');

INSERT INTO tsddr_d_stati_domande(id_stato_domanda, desc_stato_domanda, step, data_inizio_validita) VALUES
(1, 'IN_LAVORAZIONE', 1, '2022-01-01'),
(2, 'ACCETTATA', 1, '2022-01-01'),
(3, 'RIFIUTATA', 0, '2022-01-01'),
(4, 'ANNULLATA', 0, '2022-01-01');

INSERT INTO tsddr_d_tipi_profili(id_tipo_profilo, desc_tipo_profilo, data_inizio_validita) VALUES
(1, 'Back Office', '2022-01-01'),
(2, 'Front Office', '2022-01-01');

INSERT INTO tsddr_d_profili( desc_profilo, id_tipo_profilo, data_inizio_validita) VALUES
('Amministratore', 1, '2022-01-01'),
('Utente Back Office', 1, '2022-01-01'),
('Utente Front Office', 2, '2022-01-01'),
('Utente non Accreditato', 2, '2022-01-01');

INSERT INTO tsddr_r_funz_prof(id_profilo, id_funzione, read, update, insert, delete, data_inizio_validita) VALUES
(1, 10, true, true, true, true, '2022-01-01'),
(2, 10, true, true, true, true, '2022-01-01'),
(3, 10, true, true, true, true, '2022-01-01'),
(1, 11, true, true, true, true, '2022-01-01'),
(2, 11, true, true, true, true, '2022-01-01'),
(3, 11, true, true, true, true, '2022-01-01'),
(3, 1, true, true, true, true, '2022-01-01'),
(4, 1, true, true, true, true, '2022-01-01'),
(3, 2, true, true, true, true, '2022-01-01'),
(4, 2, true, true, true, true, '2022-01-01'),
(3, 3, true, true, true, true, '2022-01-01'),
(4, 3, true, true, true, true, '2022-01-01'),
(1, 4, true, true, true, true, '2022-01-01'),
(2, 4, true, true, true, true, '2022-01-01'),
(1, 5, true, true, true, true, '2022-01-01'),
(1, 6, true, true, true, true, '2022-01-01'),
(1, 7, true, true, true, true, '2022-01-01'),
(1, 8, true, true, true, true, '2022-01-01'),
(1, 9, true, true, true, true, '2022-01-01'),
(1, 12, true, true, true, true, '2022-01-01'),
(2, 12, true, false, false, false, '2022-01-01'),
(1, 13, true, true, true, true, '2022-01-01'),
(2, 13, true, false, false, false, '2022-01-01'),
(1, 14, true, false, false, false, '2022-01-01'),
(2, 14, true, false, false, false, '2022-01-01'),
(3, 14, true, true, true, true, '2022-01-01');

INSERT INTO tsddr_d_tipi_indirizzi(id_tipo_indirizzo, desc_tipo_indirizzo, data_inizio_validita)	VALUES 
(1, 'Sede Legale Gestore', '2022-01-01'),
(2, 'Sito dell''impianto', '2022-01-01'),
(3, 'Sede conservazione documenti', '2022-01-01');

INSERT INTO tsddr_d_sedime(id_sedime, desc_sedime, data_inizio_validita) VALUES 
(1, 'BORGATA', '2022-01-01'),
(2, 'BORGO', '2022-01-01'),
(3, 'CASALE', '2022-01-01'),
(4, 'CONTRADA', '2022-01-01'),
(5, 'CORSO', '2022-01-01'),
(6, 'FRAZIONE', '2022-01-01'),
(7, 'LARGO', '2022-01-01'),
(8, 'LOCALITA''', '2022-01-01'),
(9, 'PARCO', '2022-01-01'),
(10, 'PIAZZA', '2022-01-01'),
(11, 'PIAZZALE', '2022-01-01'),
(12, 'PIAZZETTA', '2022-01-01'),
(13, 'REGIONE', '2022-01-01'),
(14, 'STRADA', '2022-01-01'),
(15, 'TETTI', '2022-01-01'),
(16, 'VIA', '2022-01-01'),
(17, 'VALLONE', '2022-01-01'),
(18, 'VIALE', '2022-01-01'),
(19, 'VILLAGGIO', '2022-01-01');

INSERT INTO tsddr_t_linee(id_linea, desc_linea, flag_sotto_linea, data_inizio_validita) VALUES
(1, 'Linee di selezione automatica e/o riciclaggio', true, '2022-01-01'),
(2, 'recupero delle frazioni di rifiuto', true, '2022-01-01'),
(3, 'Linee dedicate alla produzione di combustibile solido secondario', true, '2022-01-01'),
(4, 'Linee di trattamento aerobico dedicati alla produzione di ammendante compostato (allegato 2 del decreto legislativo 75/2010)', false, '2022-01-01'),
(5, 'Linee di trattamento anaerobico, con successivo trattamento aerobico dei prodotti risultanti dal precedente trattamento, dedicati alla produzione di ammendante compostato (allegato 2 del decreto legislativo 75/2010)', false, '2022-01-01'),
(6, 'Linee di trattamento anaerobico, con successivo trattamento aerobico dei prodotti risultanti dal precedente trattamento, dedicati alla produzione di ammendante compostato (allegato 2 del decreto legislativo 75/2010)', false, '2022-01-01');

INSERT INTO tsddr_t_sotto_linee(id_sotto_linea, id_linea, desc_sotto_linea, data_fine_validita) VALUES
(1, 1, 'Linee di selezione automatica e/o riciclaggio - a) carta e cartone', '2022-01-01'),
(2, 1, 'Linee di selezione automatica e/o riciclaggio - b) legno e sughero', '2022-01-01'),
(3, 1, 'Linee di selezione automatica e/o riciclaggio - c) Vetro', '2022-01-01'),
(4, 1, 'Linee di selezione automatica e/o riciclaggio - d) cuoio e tessili', '2022-01-01'),
(5, 1, 'Linee di selezione automatica e/o riciclaggio - e) metalli e loro leghe', '2022-01-01'),
(6, 1, 'Linee di selezione automatica e/o riciclaggio -f) materie plastiche', '2022-01-01'),
(7, 1, 'Linee di selezione automatica e/o riciclaggio - g) pneumatici fuori uso non ricostruibili', '2022-01-01'),
(8, 1, 'Linee di selezione automatica e/o riciclaggio - h) gomma e caucciù', '2022-01-01'),
(9, 1, 'Linee di selezione automatica e/o riciclaggio - i) terre da spazzamento', '2022-01-01'),
(10, 1, 'Linee di selezione automatica e/o riciclaggio - j) rifiuti da costruzione e demolizione', '2022-01-01'),
(11, 1, 'Linee di selezione automatica e/o riciclaggio - k) raee', '2022-01-01'),
(12, 2, 'recupero delle frazioni di rifiuto - a) frazioni di rifiuti urbani, raccolte e conferite con il sistema multimateriale', '2022-01-01'),
(13, 2, 'recupero delle frazioni di rifiuto - b) frazioni omogenee di rifiuti speciali non pericolosi gestite sulla stessa linea di selezione, purché in momenti diversi', '2022-01-01'),
(14, 3, 'Linee dedicate alla produzione di combustibile solido secondario - a) rifiuti urbani indifferenziati, anche ingombranti, al netto delle frazioni omogenee oggetto delle raccolte differenziate', '2022-01-01'),
(15, 3, 'Linee dedicate alla produzione di combustibile solido secondario - b) frazione secca di rifiuto, derivante dalla selezione dei rifiuti urbani indifferenziati al netto delle frazioni omogenee oggetto delle raccolte differenziate', '2022-01-01');

INSERT INTO tsddr_t_linea_sotto_linea_perc(id_linea_sotto_linea_perc, id_linea, id_sotto_linea, per_min_recupero, per_max_scarto, data_inizio_validita) VALUES
(1, 1, 1, 85, 0, '2022-01-01'),
(2, 1, 2, 85, 0, '2022-01-01'),
(3, 1, 3, 80, 0, '2022-01-01'),
(4, 1, 4, 80, 0, '2022-01-01'),
(5, 1, 5, 70, 0, '2022-01-01'),
(6, 1, 6, 70, 0, '2022-01-01'),
(7, 1, 7, 60, 0, '2022-01-01'),
(8, 1, 8, 80, 0, '2022-01-01'),
(9, 1, 9, 60, 0, '2022-01-01'),
(10, 1, 10, 70, 0, '2022-01-01'),
(11, 1, 11, 85, 0, '2022-01-01'),
(12, 2, 12, 65, 0, '2022-01-01'),
(13, 2, 13, 40, 50, '2022-01-01'),
(14, 3, 14, 40, 50, '2022-01-01'),
(15, 3, 15, 60, 0, '2022-01-01'),
(16, 4, null, 0, 25, '2022-01-01'),
(17, 5, null, 0, 15, '2022-01-01'),
(18, 6, null, 50, 0, '2022-01-01');

INSERT INTO tsddr_d_tipi_provvedimenti(id_tipo_provvedimento, desc_tipo_provvedimento, data_inizio_validita) VALUES 
(1, 'Decreto', '2022-01-01'),
(2, 'Deliberazione', '2022-01-01'),
(3, 'Determinazione', '2022-01-01');

INSERT INTO tsddr_d_tipi_impianti(id_tipo_impianto, desc_tipo_impianto, data_inizio_validita) VALUES
(1, 'DISCARICA', '2022-01-01'),
(2, 'TRATTAMENTO', '2022-01-01'),
(3, 'STOCCAGGIO', '2022-01-01');

INSERT INTO tsddr_d_stati_impianti(id_stato_impianto, desc_stato_impianto, data_inizio_validita) VALUES
(1, 'OPERATIVO', '2022-01-01'),
(2, 'POST-OPERATIVO', '2022-01-01'),
(3, 'DISMESSO', '2022-01-01');

INSERT INTO tsddr_email_d_t(id_casella, nome_server, porta, sicurezza_conn, casella_di_posta) VALUES
(1, 'mailfarm-app.csi.it', 25, 'STARTTLS', 'tsddrTest_noreply@csi.it');

INSERT INTO tsddr_email_testi(id_email, desc_email, mittente, destinatari, oggetto, corpo) VALUES
(1, 'Registrazione domanda ACC.', 'tsddrTest_noreply@csi.it', null, 'Registrazione domanda di accreditamento al sistema TSDDR', 'Gentile <<Nome>> <<Cognome>>
la sua richiesta è stata correttamente acquisita con codice <<ID_DOMANDA>>. 

Al termine del processo di verifica riceverà una comunicazione. 

Cordiali saluti

L''informativa sul trattamento dei dati personali è consultabile al seguente link <<link>>
Questo indirizzo e-mail è dedicato esclusivamente all''invio della comunicazione sopra riportata.
Si prega di non rispondere alla presente.'),
(2, 'Valutazione Domanda ACC', 'tsddrTest_noreply@csi.it', 'cristiana.sabiacolucci@csi.it', 'Richiesta valutazione nuova domanda di accreditamento al sistema TSDDR', 'Il soggetto
<<NOME>>
<<COGNOME>>
con codice fiscale <<CF_UTENTE_FO>>
ha inviato la richiesta <ID_DOMANDA > di accreditamento al sistema TSDDR FO per 
<<RAG_SOCIALE>> 

L''informativa sul trattamento dei dati personali è consultabile al seguente link <<link>>
Questo indirizzo e-mail è dedicato esclusivamente all''invio della comunicazione sopra
riportata. 
Si prega di non rispondere alla presente.'),
(3, 'Domanda Accettata', 'tsddrTest_noreply@csi.it', null, 'Domanda di accreditamento al sistema TSDDR – ACCETTATA', 'Gentile <<Nome>> <<Cognome>> la sua richiesta con codice <<ID_DOMANDA>> è stata accolta.

<<Note>>
Cordiali saluti
L''informativa sul trattamento dei dati personali è consultabile al seguente link <<link>>
Questo indirizzo e-mail è dedicato esclusivamente all''invio della comunicazione sopra riportata. Si prega di non rispondere alla presente.'),
(4, 'Domada Rifiutata', 'tsddrTest_noreply@csi.it', null, 'Domanda di accreditamento al sistema TSDDR - RIFIUTATA', 'Gentile <<Nome>> <<Cognome>>

la sua richiesta con codice <<ID_DOMANDA>> non è stata accolta.
<<Note>>
Cordiali saluti
L''informativa sul trattamento dei dati personali è consultabile al seguente link <<link>>
Questo indirizzo e-mail è dedicato esclusivamente all''invio della comunicazione sopra riportata. 
Si prega di non rispondere alla presente');


INSERT INTO tsddr_d_tipi_msg(id_tipo_msg, desc_tipo_msg, data_inizio_validita) VALUES
(1, 'Messaggio Positivo', '2022-01-01'),
(2, 'Messaggio di Attenzione', '2022-01-01'),
(3, 'Messaggio di Errore', '2022-01-01'),
(4, 'Messaggio Informativo', '2022-01-01');

INSERT INTO tsddr_t_messaggi(id_messaggio, cod_msg, id_tipo_msg, titolo_msg, testo_msg) VALUES
(1, 'P001', 1, 'Operazione terminata correttamente', 'Dati salvati correttamente'),
(2, 'A001', 2, 'Attenzione Dati modificati!', 'Sono state apportate delle modifiche ai dati, 
Procedendo non saranno salvate.
Vuoi Procedere ?
'),
(3, 'E001', 3, 'Errore nel formato dei parametri di filtro', 'Il campo "Identificativo domanda" può contenere solo caratteri numerici'),
(4, 'E002', 3, 'Errore nel formato dei parametri di filtro', 'Il campo "Data al" è minore della data di inizio dell''intervallo di tempo da considerare'),
(5, 'A002', 2, 'Attenzione ai parametri di filtro inseriti.', 'In base ai parametri di filtro impostati non si sono risultati da visualizzare'),
(6, 'E003', 3, 'Errore nel formato del campo E-mail', 'Il campo E-mail deve rispettare le seguenti regole:
•	presenza del carattere @
•	di un solo punto dopo la @
•	non sono ammessi caratteri speciali nel campo oltre la @ (ad esempio #, £, etc.) 
•	non è ammessa PEC'),
(7, 'A003', 2, 'Attenzione!', 'La domanda selezionata verrà annullata.
Vuoi Procedere ?'),
(8, 'P002', 1, 'Operazione terminata correttamente', 'Inserimento dati avvenuto con successo'),
(9, 'P003', 1, 'Operazione terminata correttamente', 'Il dato selezionato è stato eliminato dall''archivio.'),
(10, 'A004', 3, 'Non è possibile eseguire questa operazione', 'Non è possibile eliminare questo profilo in quanto ci sono ancora utenti associati a questo profilo.'),
(11, 'A005', 2, 'Attenzione!', 'Il profilo selezionato verrà annullato.
Vuoi Procedere ?'),
(12, 'E004', 3, 'Errore GRAVE nelle funzionalità', 'La funzionalità richiesta non è più valida - verificare la configurazione.'),
(13, 'I001', 4, 'Informazione', 'In archivio non sono presenti domande di accreditamento da te inviate'),
(14, 'E005', 3, 'Errore nel campo Codice fiscale/Partita IVA', 'Il codice fiscale o la partita IVA inserita non è corretta.'),
(15, 'A006', 2, 'Attenzione!', 'L''utente selezionato verrà eliminato dall''archivio, con tutti i Profili associati.
Vuoi Procedere ?'),
(16, 'A007', 2, 'Attenzione!', 'L''utente selezionato non ha ancora un profilo associato.'),
(17, 'A008', 2, 'Attenzione!', 'Il gestore selezionato non sarà più associato all''utente.
Vuoi Procedere ?'),
(18, 'A009', 2, 'Attenzione!', 'Il gestore selezionato è associato ad almeno un impianto.
Vuoi Procedere?'),
(19, 'P004', 1, 'Operazione terminata correttamente', 'Il gestore selezionato è stato eliminato dall''archivio.'),
(20, 'P005', 1, 'Operazione terminata correttamente', 'Il gestore selezionato e gli impianti ad esso associati sono stati eliminati dall''archivio.'),
(21, 'A010', 2, 'Attenzione!', 'Il gestore selezionato verrà eliminato dall''archivio.
Vuoi Procedere?'),
(22, 'A011', 2, 'Attenzione!', 'L''impianto selezionato verrà eliminato dall''archivio.
Vuoi Procedere?'),
(23, 'P006', 1, 'Operazione terminata correttamente', 'L''impianto selezionato è stato eliminato dall''archivio.'),
(24, 'A012', 2, 'Attenzione!', 'Conferma la modifica del Gestore dell''impianto.
Vuoi Procedere?'),
(25, 'A013', 2, 'Attenzione!', 'La linea selezionata verrà eliminata dall''impianto.
Vuoi Procedere?'),
(26, 'P007', 1, 'Operazione terminata correttamente', 'La linea selezionata è stata eliminata dall''impianto.'),
(27, 'P008', 1, 'Operazione terminata correttamente', 'La linea selezionata è stata associata all''impianto.'),
(28, 'A014', 2, 'Attenzione!', 'Il provvedimento autorizzativo selezionato verrà eliminata dall''impianto.
Vuoi Procedere?'),
(29, 'P017', 1, 'Operazione terminata correttamente', 'Il Provvedimento autorizzativo selezionato è stata eliminato dall''impianto.'),
(30, 'P009', 1, 'Operazione terminata correttamente', 'Dichiarazione annuale salvata in stato di BOZZA.'),
(31, 'P010', 1, 'Operazione terminata correttamente', 'Dichiarazione annuale INVIATA correttamente.'),
(32, 'P011', 1, 'Operazione terminata correttamente', 'La dichiarazione annuale è stata scaricata in un file formato PDF.'),
(33, 'A015', 2, 'Attenzione!', 'La dichiarazione annuale selezionata verrà eliminata dall''archivio.
Vuoi Procedere?'),
(34, 'P012', 1, 'Operazione terminata correttamente', 'La dichiarazione annuale selezionata è stata eliminata dall''archivio.'),
(35, 'P013', 1, 'Operazione terminata correttamente', 'La BOZZA della dichiarazione annuale è stata salvata in archivio.'),
(36, 'P014', 1, 'Operazione terminata correttamente', 'La dichiarazione annuale è stata inviata e salvata in archivio.'),
(37, 'P015', 1, 'Operazione terminata correttamente', 'La BOZZA della nuova dichiarazione annuale è stata salvata in archivio.'),
(38, 'P016', 1, 'Operazione terminata correttamente', 'La nuova dichiarazione annuale è stata inviata e salvata in archivio.'),
(39, 'E006', 3, 'Errore GRAVE nelle funzionalità', 'Il Profilo per la funzionalità richiesta non è più valido - verificare la configurazione.'),
(40, 'E007', 3, 'Errore GRAVE nelle funzionalità', 'Il codice fiscale o la partita IVA inserita è già presente in Banca Dati.'),
(41, 'I002', 4, 'Informazione', 'Domanda ANNULLATA, dati non modificabili.'),
(42, 'E008', 3, 'Errore GRAVE nelle funzionalità', 'Errore grave di sistema. Si prega di contattare servizio di assistenza.'),
(43, 'E009', 3, 'Errore nel formato del campo PEC', 'Il campo E-mail deve rispettare le seguenti regole:
•	presenza del carattere @
•	di un solo punto dopo la @
•	non sono ammessi caratteri speciali nel campo oltre la @ (ad esempio #, £, etc.) 
•	è ammessa solo PEC'),
(44, 'E010', 3, 'Dati Obbligatori Mancanti', 'Il dato selezionato deve essere valorizzato'),
(45, 'A14', 2, 'Attenzione!', 'Conferma la modifica dei dati dell''impianto.
Vuoi Procedere?'),
(46, 'E11', 3, 'Date validità già utilizzate', 'Esisono altri dati attivi per le date validità selezionate. '),
(47, 'A15', 2, 'Attenzione!', 'Conferma la modifica dei dati del Gestore.
Vuoi Procedere?'),
(48, 'I004', 2, 'Informazione', 'Il Gestore è collegato a delle Domande. Cancellazione non possibile.'),
(49, 'I003', 4, 'Titolo informativa Home', 'Testo informativa Home');
