-- TSDDR_T_LINEE
ALTER TABLE tsddr_t_linee
ADD COLUMN cod_linea character varying(5);

UPDATE tsddr_t_linee SET cod_linea = '1', desc_linea = '1 - Linee di selezione automatica e/o riciclaggio' WHERE id_linea = 1;
UPDATE tsddr_t_linee SET cod_linea = '2', desc_linea = '2 - recupero delle frazioni di rifiuto' WHERE id_linea = 2;
UPDATE tsddr_t_linee SET cod_linea = '3', desc_linea = '3 - Linee dedicate alla produzione di combustibile solido secondario' WHERE id_linea = 3;
UPDATE tsddr_t_linee SET cod_linea = '4', desc_linea = '4 - Linee di trattamento aerobico dedicati alla produzione di ammendante compostato (allegato 2 del decreto legislativo 75/2010)' WHERE id_linea = 4;
UPDATE tsddr_t_linee SET cod_linea = '5', desc_linea = '5 - Linee di trattamento anaerobico, con successivo trattamento aerobico dei prodotti risultanti dal precedente trattamento, dedicati alla produzione di ammendante compostato (allegato 2 del decreto legislativo 75/2010)' WHERE id_linea = 5;
UPDATE tsddr_t_linee SET cod_linea = '6', desc_linea = '6 -Impianti di trattamento di rifiuti urbani indifferenziati in cui sono presenti operazioni di selezione automatica: il trattamento deve essere finalizzato, come stabilito dall’art. 1 del decreto legislativo 36/2003.' WHERE id_linea = 6;

-- TSDDR_T_SOTTO_LINEE
ALTER TABLE tsddr_t_sotto_linee
ADD COLUMN cod_sotto_linea character varying(5);

UPDATE tsddr_t_sotto_linee SET cod_sotto_linea = '1.a', desc_sotto_linea = '1.a - Linee di selezione automatica e/o riciclaggio - a) carta e cartone' WHERE id_sotto_linea = 1;
UPDATE tsddr_t_sotto_linee SET cod_sotto_linea = '1.b', desc_sotto_linea = '1.b - Linee di selezione automatica e/o riciclaggio - b) legno e sughero' WHERE id_sotto_linea = 2;
UPDATE tsddr_t_sotto_linee SET cod_sotto_linea = '1.c', desc_sotto_linea = '1.c - Linee di selezione automatica e/o riciclaggio - c) Vetro' WHERE id_sotto_linea = 3;
UPDATE tsddr_t_sotto_linee SET cod_sotto_linea = '1.d', desc_sotto_linea = '1.d - Linee di selezione automatica e/o riciclaggio - d) cuoio e tessili' WHERE id_sotto_linea = 4;
UPDATE tsddr_t_sotto_linee SET cod_sotto_linea = '1.e', desc_sotto_linea = '1.e - Linee di selezione automatica e/o riciclaggio - e) metalli e loro leghe' WHERE id_sotto_linea = 5;
UPDATE tsddr_t_sotto_linee SET cod_sotto_linea = '1.f', desc_sotto_linea = '1.f - Linee di selezione automatica e/o riciclaggio -f) materie plastiche' WHERE id_sotto_linea = 6;
UPDATE tsddr_t_sotto_linee SET cod_sotto_linea = '1.g', desc_sotto_linea = '1.g - Linee di selezione automatica e/o riciclaggio - g) pneumatici fuori uso non ricostruibili' WHERE id_sotto_linea = 7;
UPDATE tsddr_t_sotto_linee SET cod_sotto_linea = '1.h', desc_sotto_linea = '1.h - Linee di selezione automatica e/o riciclaggio - h) gomma e caucciù' WHERE id_sotto_linea = 8;
UPDATE tsddr_t_sotto_linee SET cod_sotto_linea = '1.i', desc_sotto_linea = '1.i - Linee di selezione automatica e/o riciclaggio - i) terre da spazzamento' WHERE id_sotto_linea = 9;
UPDATE tsddr_t_sotto_linee SET cod_sotto_linea = '1.j', desc_sotto_linea = '1.j - Linee di selezione automatica e/o riciclaggio - j) rifiuti da costruzione e demolizione' WHERE id_sotto_linea = 10;
UPDATE tsddr_t_sotto_linee SET cod_sotto_linea = '1.k', desc_sotto_linea = '1.k - Linee di selezione automatica e/o riciclaggio - k) raee' WHERE id_sotto_linea = 11;
UPDATE tsddr_t_sotto_linee SET cod_sotto_linea = '2.a', desc_sotto_linea = '2.a - Recupero delle frazioni di rifiuto - a) frazioni di rifiuti urbani, raccolte e conferite con il sistema multimateriale' WHERE id_sotto_linea = 12;
UPDATE tsddr_t_sotto_linee SET cod_sotto_linea = '2.b', desc_sotto_linea = '2.b - Recupero delle frazioni di rifiuto - b) frazioni omogenee di rifiuti speciali non pericolosi gestite sulla stessa linea di selezione, purché in momenti diversi' WHERE id_sotto_linea = 13;
UPDATE tsddr_t_sotto_linee SET cod_sotto_linea = '3.a', desc_sotto_linea = '3.a - Linee dedicate alla produzione di combustibile solido secondario - a) rifiuti urbani indifferenziati, anche ingombranti, al netto delle frazioni omogenee oggetto delle raccolte differenziate' WHERE id_sotto_linea = 14;
UPDATE tsddr_t_sotto_linee SET cod_sotto_linea = '3.b', desc_sotto_linea = '3.b - Linee dedicate alla produzione di combustibile solido secondario - b) frazione secca di rifiuto, derivante dalla selezione dei rifiuti urbani indifferenziati al netto delle frazioni omogenee oggetto delle raccolte differenziate' WHERE id_sotto_linea = 15;