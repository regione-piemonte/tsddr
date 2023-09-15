Delete from tsddr_c_parametri_acaris;

INSERT INTO tsddr_c_parametri_acaris(id_parametro_acaris, nome_param_acaris, valore_param_acaris_s, valore_param_acaris_n, valore_param_acaris_b) VALUES
(1,	'Annotazione',	'Dichiarazione presentata ai sensi dell''art. 65, comma 1, lettera b) del CAD',	null,	null),
(2,	'Annotazione formale',	null,	null,	true),
(3,	'AOO Protocollante',	'A11000',	null,	null),
(4,	'AOO Responsabile della materia',	'A11000',	null,	null),
(5,	'Applica annotazione a classificazione corrente',	null,	null,	false),
(6,	'Applica annotazione a intero documento',	null,	null,	true),
(7,	'Applicativo alimentante',	'TSDDR',	null,	null),
(8,	'Archivio corrente',	null,	null,	true),
(9,	'Autenticato',	null,	null,	false),
(10,	'Autenticato con firma autentica',	null,	null,	false),
(11,	'Autenticato copia autentica',	null,	null,	false),
(12,	'Cartaceo',	null,	null,	false),
(13,	'Composizione',	'DOCUMENTO SINGOLO',	null,	null),
(14,	'Consenti creazione fascicoli',	null,	null,	true),
(15,	'Consenti inserimento documenti',	null,	null,	true),
(16,	'Consenti riclassificazione documenti nel dossier',	null,	null,	true),
(17,	'Consenti riclassificazione fascicoli nel dossier',	null,	null,	true),
(18,	'Conservazione corrente',	'5',	null,	null),
(19,	'Conservazione generale',	'5',	null,	null),
(20,	'Copia cartacea',	null,	null,	false),
(21,	'Destinatari interni',	'A1103A-R',	null,	null),
(22,	'Destinatario giuridico',	'REGIONE PIEMONTE',	null,	null),
(23,	'Forma documentaria (Tipologia documentale)',	'ISTANZA',	null,	null),
(24,	'Grado di vitalità',	'MEDIO',	null,	null),
(25,	'Molteplicità della composizione',	null,	null,	false),
(26,	'Nodo Protocollante',	'A1103A-R',	null,	null),
(27,	'Nodo Responsabile della materia',	'A1103A-R',	null,	null),
(28,	'Origine interna',	null,	null,	false),
(29,	'Presenza file',	null,	null,	true),
(30,	'Protocollista',	'TSDDR',	null,	null),
(31,	'Registrazione riservata',	null,	null,	false),
(32,	'Rimanda operazione sbustamento',	'NO',	null,	null),
(33,	'Stato definitivo',	null,	null,	true),
(34,	'Stato di efficacia',	'PERFETTO ED EFFICACE',	null,	null),
(35,	'Stato modificabile',	null,	null,	false),
(36,	'Stato registrato',	null,	null,	true),
(37,	'Struttura Protocollante',	'A1103A',	null,	null),
(38,	'Struttura Responsabile della materia',	'A1103A',	null,	null),
(39,	'Tipo',	'PERFETTO ED EFFICACE',	null,	null),
(40,	'Tipo documento',	'SEMPLICE',	null,	null),
(41,	'Tipologia',	'annuale',	null,	true),
(42,	'Tipologia dati contenuti - Dati Personali',	null,	null,	true),
(43,	'Tipologia dati contenuti - Dati Riservati',	null,	null,	false),
(44,	'Tipologia dati contenuti - Dati Sensibili',	null,	null,	false),
(45,	'Utente di creazione',	'TSDDR',	null,	null),
(46,	'Vital record code',	'MEDIO',	null,	null);

Delete from tsddr_c_parametri where id_parametro in (8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23);

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

INSERT INTO tsddr_c_parametri (id_parametro, nome_parametro, valore_parametro_s) VALUES 
(16, 'ACTA_ID_AOO', '276'),
(17, 'ACTA_CF', 'TDRTDR74A02L219H'),
(18, 'ACTA_ID_STRUTTURA', '1108'),
(19, 'ACTA_ID_NODO', '1194'),
(20, 'ACTA_CODE_FRUITORE', 'TSDDR'),
(21, 'ACTA_APP_KEY_FRUITORE', '-88/-90/-13/53/-19/-60/122/-102/-115/69/74/3/-28/-30/-50/-68'),
(22, 'ACTA_REPO_NAME_FRUITORE', 'RP201209 Regione Piemonte - Agg. 09/2012'),
(23, 'ACTA_CODE_ENTE_FRUITORE', 'RP201209');