--- tsddr_t_soggetti_mr

DROP TABLE if exists tsddr_t_soggetti_mr;

DROP SEQUENCE if exists tsddr_t_soggetti_mr_id_soggetti_mr_seq;

CREATE TABLE tsddr_t_soggetti_mr (
	id_soggetti_mr bigint NOT NULL,
	cod_fisc_partiva character varying(16) NOT NULL,
	id_dich_annuale bigint NOT NULL,
	rag_sociale character varying(16) NOT NULL,
	data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint
);

CREATE SEQUENCE tsddr_t_soggetti_mr_id_soggetti_mr_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE tsddr_t_soggetti_mr_id_soggetti_mr_seq OWNED BY tsddr_t_soggetti_mr.id_soggetti_mr;

ALTER TABLE ONLY tsddr_t_soggetti_mr ALTER COLUMN id_soggetti_mr SET DEFAULT nextval('tsddr_t_soggetti_mr_id_soggetti_mr_seq'::regclass);

ALTER TABLE ONLY tsddr_t_soggetti_mr ADD CONSTRAINT tsddr_t_soggetti_mr_pkey PRIMARY KEY (id_soggetti_mr);

-- tsddr_t_conferimenti

DROP TABLE if exists tsddr_t_conferimenti;

DROP SEQUENCE if exists tsddr_t_conferimenti_id_conferimento_seq;

CREATE TABLE tsddr_t_conferimenti (
	id_conferimento bigint NOT NULL,
	id_dich_annuale bigint NOT NULL,
	id_rifiuto_tariffa bigint NOT NULL,
	quantita numeric(10,6) DEFAULT 0 NOT NULL,
	id_unita_misura bigint NOT NULL,
	anno bigint NOT NULL,
	id_periodo bigint NOT NULL,
	importo numeric(10,2) DEFAULT 0 NOT NULL,
	data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint
);

CREATE SEQUENCE tsddr_t_conferimenti_id_conferimento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE tsddr_t_conferimenti_id_conferimento_seq OWNED BY tsddr_t_conferimenti.id_conferimento;

ALTER TABLE ONLY tsddr_t_conferimenti ALTER COLUMN id_conferimento SET DEFAULT nextval('tsddr_t_conferimenti_id_conferimento_seq'::regclass);

ALTER TABLE ONLY tsddr_t_conferimenti ADD CONSTRAINT tsddr_t_conferimenti_pkey PRIMARY KEY (id_conferimento);

--- tsddr_t_rifiuti_tariffe

DROP TABLE if exists tsddr_t_rifiuti_tariffe;

DROP SEQUENCE if exists tsddr_t_rifiuti_tariffe_id_rifiuto_tariffa_seq;

CREATE TABLE tsddr_t_rifiuti_tariffe (
	id_rifiuto_tariffa bigint NOT NULL,
	id_tipo_rifiuto character varying(10) NOT NULL,
	id_tipologia_2 character varying(10) NOT NULL,
	id_tipologia_3 character varying(10),
	importo numeric(10,2) DEFAULT 0 NOT NULL,
	flag_riduzione boolean DEFAULT FALSE NOT NULL,
	descrizione character varying(500) NOT NULL,
	data_inizio_validita date DEFAULT now() NOT NULL,
	data_fine_validita date,	
	data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint
);

CREATE SEQUENCE tsddr_t_rifiuti_tariffe_id_rifiuto_tariffa_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE tsddr_t_rifiuti_tariffe_id_rifiuto_tariffa_seq OWNED BY tsddr_t_rifiuti_tariffe.id_rifiuto_tariffa;

ALTER TABLE ONLY tsddr_t_rifiuti_tariffe ALTER COLUMN id_rifiuto_tariffa SET DEFAULT nextval('tsddr_t_rifiuti_tariffe_id_rifiuto_tariffa_seq'::regclass);

ALTER TABLE ONLY tsddr_t_rifiuti_tariffe ADD CONSTRAINT tsddr_t_rifiuti_tariffe_pkey PRIMARY KEY (id_rifiuto_tariffa);

--- tsddr_t_versamenti

DROP TABLE if exists tsddr_t_versamenti;

DROP SEQUENCE if exists tsddr_t_versamenti_id_versamento_seq;

CREATE TABLE tsddr_t_versamenti (
	id_versamento bigint NOT NULL,
	id_dich_annuale bigint NOT NULL,
	data_versamento date NOT NULL,
	id_periodo bigint NOT NULL,
	importo_versato numeric(10,2) DEFAULT 0,
	data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint
);

CREATE SEQUENCE tsddr_t_versamenti_id_versamento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE tsddr_t_versamenti_id_versamento_seq OWNED BY tsddr_t_versamenti.id_versamento;

ALTER TABLE ONLY tsddr_t_versamenti ALTER COLUMN id_versamento SET DEFAULT nextval('tsddr_t_versamenti_id_versamento_seq'::regclass);

ALTER TABLE ONLY tsddr_t_versamenti ADD CONSTRAINT tsddr_t_versamenti_pkey PRIMARY KEY (id_versamento);

--- tsddr_d_periodi

DROP TABLE if exists tsddr_d_periodi;

DROP SEQUENCE if exists tsddr_d_periodi_id_periodo_seq;

CREATE TABLE tsddr_d_periodi (
	id_periodo bigint NOT NULL,	
	desc_periodo character varying(100) NOT NULL,
	data_inizio_validita date DEFAULT now() NOT NULL,
	data_fine_validita date,
	data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint
);

CREATE SEQUENCE tsddr_d_periodi_id_periodo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE tsddr_d_periodi_id_periodo_seq OWNED BY tsddr_d_periodi.id_periodo;

ALTER TABLE ONLY tsddr_d_periodi ALTER COLUMN id_periodo SET DEFAULT nextval('tsddr_d_periodi_id_periodo_seq'::regclass);

ALTER TABLE ONLY tsddr_d_periodi ADD CONSTRAINT tsddr_d_periodi_pkey PRIMARY KEY (id_periodo);

--- tsddr_t_prev_cons_dett

DROP TABLE if exists tsddr_t_prev_cons_dett;

DROP SEQUENCE if exists tsddr_t_prev_cons_dett_id_prev_cons_dett_seq;

CREATE TABLE tsddr_t_prev_cons_dett (
	id_prev_cons_dett bigint NOT NULL,
	id_prev_cons bigint NOT NULL,
	id_sezione bigint NOT NULL,
	id_eer bigint NOT NULL,
	destinazione character varying(200),
	quantita numeric(10,6) DEFAULT 0 NOT NULL,
	id_unita_misura bigint NOT NULL,
	data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint
);

CREATE SEQUENCE tsddr_t_prev_cons_dett_id_prev_cons_dett_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE tsddr_t_prev_cons_dett_id_prev_cons_dett_seq OWNED BY tsddr_t_prev_cons_dett.id_prev_cons_dett;

ALTER TABLE ONLY tsddr_t_prev_cons_dett ALTER COLUMN id_prev_cons_dett SET DEFAULT nextval('tsddr_t_prev_cons_dett_id_prev_cons_dett_seq'::regclass);

ALTER TABLE ONLY tsddr_t_prev_cons_dett ADD CONSTRAINT tsddr_t_prev_cons_dett_pkey PRIMARY KEY (id_prev_cons_dett);

--- tsddr_t_prev_cons

DROP TABLE if exists tsddr_t_prev_cons;

DROP SEQUENCE if exists tsddr_t_prev_cons_id_prev_cons_seq;

CREATE TABLE tsddr_t_prev_cons (
	id_prev_cons bigint NOT NULL,
	id_impianto_linea bigint NOT NULL,
	anno_tributo bigint NOT NULL,
	desc_sommaria character varying(1000),
	id_tipo_documento bigint NOT NULL,
	perc_recupero numeric(10,6) DEFAULT 0,
	perc_scarto numeric(10,6) DEFAULT 0,	
	data_doc date,
	protocollo character varying(100),
	modalita character varying(1000),	
	data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint
);

CREATE SEQUENCE tsddr_t_prev_cons_id_prev_cons_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE tsddr_t_prev_cons_id_prev_cons_seq OWNED BY tsddr_t_prev_cons.id_prev_cons;

ALTER TABLE ONLY tsddr_t_prev_cons ALTER COLUMN id_prev_cons SET DEFAULT nextval('tsddr_t_prev_cons_id_prev_cons_seq'::regclass);

ALTER TABLE ONLY tsddr_t_prev_cons ADD CONSTRAINT tsddr_t_prev_cons_pkey PRIMARY KEY (id_prev_cons);


--- tsddr_d_eer

DROP TABLE if exists tsddr_d_eer;

DROP SEQUENCE if exists tsddr_d_eer_id_eer_seq;

CREATE TABLE tsddr_d_eer (
	id_eer bigint NOT NULL,	
	codice_eer character varying(20) NOT NULL,
	descrizione character varying(200) NOT NULL,	
	anno bigint NOT NULL,
	data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint
);

CREATE SEQUENCE tsddr_d_eer_id_eer_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE tsddr_d_eer_id_eer_seq OWNED BY tsddr_d_eer.id_eer;

ALTER TABLE ONLY tsddr_d_eer ALTER COLUMN id_eer SET DEFAULT nextval('tsddr_d_eer_id_eer_seq'::regclass);

ALTER TABLE ONLY tsddr_d_eer ADD CONSTRAINT tsddr_d_eer_pkey PRIMARY KEY (id_eer);

--- tsddr_d_sezioni

DROP TABLE if exists tsddr_d_sezioni;

DROP SEQUENCE if exists tsddr_d_sezioni_id_sezione_seq;

CREATE TABLE tsddr_d_sezioni (
	id_sezione bigint NOT NULL,	
	desc_sezione character varying(100) NOT NULL,
	data_inizio_validita date DEFAULT now() NOT NULL,
	data_fine_validita date,
	data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint
);

CREATE SEQUENCE tsddr_d_sezioni_id_sezione_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE tsddr_d_sezioni_id_sezione_seq OWNED BY tsddr_d_sezioni.id_sezione;

ALTER TABLE ONLY tsddr_d_sezioni ALTER COLUMN id_sezione SET DEFAULT nextval('tsddr_d_sezioni_id_sezione_seq'::regclass);

ALTER TABLE ONLY tsddr_d_sezioni ADD CONSTRAINT tsddr_d_sezioni_pkey PRIMARY KEY (id_sezione);

--- tsddr_d_unita_misura

DROP TABLE if exists tsddr_d_unita_misura;

DROP SEQUENCE if exists tsddr_d_unita_misura_id_unita_misura_seq;

CREATE TABLE tsddr_d_unita_misura (
	id_unita_misura bigint NOT NULL,	
	desc_unita_misura character varying(100) NOT NULL,
	data_inizio_validita date DEFAULT now() NOT NULL,
	data_fine_validita date,
	data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint
);

CREATE SEQUENCE tsddr_d_unita_misura_id_unita_misura_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE tsddr_d_unita_misura_id_unita_misura_seq OWNED BY tsddr_d_unita_misura.id_unita_misura;

ALTER TABLE ONLY tsddr_d_unita_misura ALTER COLUMN id_unita_misura SET DEFAULT nextval('tsddr_d_unita_misura_id_unita_misura_seq'::regclass);

ALTER TABLE ONLY tsddr_d_unita_misura ADD CONSTRAINT tsddr_d_unita_misura_pkey PRIMARY KEY (id_unita_misura);

--- tsddr_d_tipo_doc

DROP TABLE if exists tsddr_d_tipo_doc;

DROP SEQUENCE if exists tsddr_d_tipo_doc_id_tipo_doc_seq;

CREATE TABLE tsddr_d_tipo_doc (
	id_tipo_doc bigint NOT NULL,	
	desc_tipo_doc character varying(100) NOT NULL,
	data_inizio_validita date DEFAULT now() NOT NULL,
	data_fine_validita date,
	data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint
);

CREATE SEQUENCE tsddr_d_tipo_doc_id_tipo_doc_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE tsddr_d_tipo_doc_id_tipo_doc_seq OWNED BY tsddr_d_tipo_doc.id_tipo_doc;

ALTER TABLE ONLY tsddr_d_tipo_doc ALTER COLUMN id_tipo_doc SET DEFAULT nextval('tsddr_d_tipo_doc_id_tipo_doc_seq'::regclass);

ALTER TABLE ONLY tsddr_d_tipo_doc ADD CONSTRAINT tsddr_d_tipo_doc_pkey PRIMARY KEY (id_tipo_doc);

--- tsddr_t_dich_annuale

DROP TABLE if exists tsddr_t_dich_annuale;

DROP SEQUENCE if exists tsddr_t_dich_annuale_id_dich_annuale_seq;

CREATE TABLE tsddr_t_dich_annuale (
	id_dich_annuale bigint NOT NULL,
	id_impianto bigint NOT NULL,
	anno bigint NOT NULL,
	versione bigint NOT NULL,
	annotazioni character varying(500) NOT NULL,
	id_ind_dep_provv bigint,
	id_stato_dichiarazione bigint NOT NULL,
	credito_imposta bigint,
	saldo_imposta bigint,
	data_dichiarazione date,
	data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint
);

CREATE SEQUENCE tsddr_t_dich_annuale_id_dich_annuale_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE tsddr_t_dich_annuale_id_dich_annuale_seq OWNED BY tsddr_t_dich_annuale.id_dich_annuale;

ALTER TABLE ONLY tsddr_t_dich_annuale ALTER COLUMN id_dich_annuale SET DEFAULT nextval('tsddr_t_dich_annuale_id_dich_annuale_seq'::regclass);

ALTER TABLE ONLY tsddr_t_dich_annuale ADD CONSTRAINT tsddr_t_dich_annuale_pkey PRIMARY KEY (id_dich_annuale);

--- tsddr_d_stati_dichiarazioni

DROP TABLE if exists tsddr_d_stati_dichiarazioni;

DROP SEQUENCE if exists tsddr_d_stati_dichiarazioni_id_stato_dichiarazione_seq;

CREATE TABLE tsddr_d_stati_dichiarazioni (
	id_stato_dichiarazione bigint NOT NULL,
	descr_stato_dichiarazione character varying(500) NOT NULL,
	data_inizio_validita date DEFAULT now() NOT NULL,
	data_fine_validita date,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint
);

CREATE SEQUENCE tsddr_d_stati_dichiarazioni_id_stato_dichiarazione_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE tsddr_d_stati_dichiarazioni_id_stato_dichiarazione_seq OWNED BY tsddr_d_stati_dichiarazioni.id_stato_dichiarazione;

ALTER TABLE ONLY tsddr_d_stati_dichiarazioni ALTER COLUMN id_stato_dichiarazione SET DEFAULT nextval('tsddr_d_stati_dichiarazioni_id_stato_dichiarazione_seq'::regclass);

ALTER TABLE ONLY tsddr_d_stati_dichiarazioni ADD CONSTRAINT tsddr_d_stati_dichiarazioni_pkey PRIMARY KEY (id_stato_dichiarazione);

-- tsddr_c_parametri_acaris

DROP TABLE if exists tsddr_c_parametri_acaris;

DROP SEQUENCE if exists tsddr_c_parametri_acaris_id_parametro_acaris_seq;

CREATE TABLE tsddr_c_parametri_acaris (
    id_parametro_acaris bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    nome_param_acaris character varying(100) NOT NULL,
    valore_param_acaris_s character varying(200),
    valore_param_acaris_n bigint,
    valore_param_acaris_b boolean
);

CREATE SEQUENCE tsddr_c_parametri_acaris_id_parametro_acaris_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE tsddr_c_parametri_acaris_id_parametro_acaris_seq OWNED BY tsddr_c_parametri_acaris.id_parametro_acaris;	

ALTER TABLE ONLY tsddr_c_parametri_acaris ALTER COLUMN id_parametro_acaris SET DEFAULT nextval('tsddr_c_parametri_acaris_id_parametro_acaris_seq'::regclass);

ALTER TABLE ONLY tsddr_c_parametri_acaris
    ADD CONSTRAINT tsddr_c_parametri_acaris_pkey PRIMARY KEY (id_parametro_acaris);
	
-- tsddr_limiti_amministrativi
DROP TABLE if exists tsddr_limiti_amministrativi;

CREATE TABLE tsddr_limiti_amministrativi (
	record character varying(500) NOT NULL,
	data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint
    
);

-- tsddr_stati_esteri
DROP TABLE if exists tsddr_stati_esteri;

CREATE TABLE tsddr_stati_esteri (
	record character varying(500) NOT NULL,
	data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint    
);

-- tsddr_par_file
DROP TABLE if exists tsddr_par_file;

DROP SEQUENCE if exists tsddr_par_file_id_par_file_seq;

CREATE TABLE tsddr_par_file (
    id_par_file bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
	file character varying(50) NOT NULL,
	nome_campo character varying(50) NOT NULL,
	pos_da bigint NOT NULL,
	pos_a bigint NOT NULL,
	lunghezza bigint NOT NULL,
	formato character varying(50),
	null_value character varying(50)
);

CREATE SEQUENCE tsddr_par_file_id_par_file_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE tsddr_par_file_id_par_file_seq OWNED BY tsddr_par_file.id_par_file;	

ALTER TABLE ONLY tsddr_par_file ALTER COLUMN id_par_file SET DEFAULT nextval('tsddr_par_file_id_par_file_seq'::regclass);

ALTER TABLE ONLY tsddr_par_file
    ADD CONSTRAINT tsddr_par_file_pkey PRIMARY KEY (id_par_file);
	
-- tsddr_t_report_dett
DROP TABLE if exists tsddr_t_report_dett;

DROP SEQUENCE if exists tsddr_t_report_dett_id_dettaglio_seq;

CREATE TABLE tsddr_t_report_dett (
    id_dettaglio bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
	id_report bigint NOT NULL,
	cod_campo bigint NOT NULL,
	id_tipo_campo bigint NOT NULL,
	testo character varying(500) NOT NULL,
	logo bytea NOT NULL,
	firma bytea NOT NULL
);

CREATE SEQUENCE tsddr_t_report_dett_id_dettaglio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;    
    
-- tsddr_d_tipi_campi
DROP TABLE if exists tsddr_d_tipi_campi;

DROP SEQUENCE if exists tsddr_d_tipi_campi_id_tipo_campo_seq;

CREATE TABLE tsddr_d_tipi_campi (
    id_tipo_campo bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
	desc_tipo_campo character varying(50) NOT NULL,
	data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL
);

CREATE SEQUENCE tsddr_d_tipi_campi_id_tipo_campo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE tsddr_d_tipi_campi_id_tipo_campo_seq OWNED BY tsddr_d_tipi_campi.id_tipo_campo;	

ALTER TABLE ONLY tsddr_d_tipi_campi ALTER COLUMN id_tipo_campo SET DEFAULT nextval('tsddr_d_tipi_campi_id_tipo_campo_seq'::regclass);

ALTER TABLE ONLY tsddr_d_tipi_campi
    ADD CONSTRAINT tsddr_d_tipi_campi_pkey PRIMARY KEY (id_tipo_campo);
	
-- tsddr_t_report
DROP TABLE if exists tsddr_t_report;

DROP SEQUENCE if exists tsddr_t_report_id_report_seq;

CREATE TABLE tsddr_t_report (
    id_report bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
	desc_report character varying(20) NOT NULL,
	xml_report xml NOT NULL,
	data_fine_validita date NOT NULL,
    data_inizio_validita date DEFAULT now() NOT NULL
);

CREATE SEQUENCE tsddr_t_report_id_report_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE tsddr_t_report_id_report_seq OWNED BY tsddr_t_report.id_report;	

ALTER TABLE ONLY tsddr_t_report ALTER COLUMN id_report SET DEFAULT nextval('tsddr_t_report_id_report_seq'::regclass);

ALTER TABLE ONLY tsddr_t_report
    ADD CONSTRAINT tsddr_t_report_pkey PRIMARY KEY (id_report);
	
ALTER SEQUENCE tsddr_t_report_dett_id_dettaglio_seq OWNED BY tsddr_t_report_dett.id_dettaglio;

ALTER TABLE ONLY tsddr_t_report_dett ALTER COLUMN id_dettaglio SET DEFAULT nextval('tsddr_t_report_dett_id_dettaglio_seq'::regclass);

ALTER TABLE ONLY tsddr_t_report_dett
    ADD CONSTRAINT tsddr_t_report_dett_pkey PRIMARY KEY (id_dettaglio);	
	
--- FOREIGN KEYS

ALTER TABLE ONLY tsddr_t_dich_annuale ADD CONSTRAINT fk_impianto FOREIGN KEY (id_impianto) REFERENCES tsddr_t_impianti(id_impianto);

ALTER TABLE ONLY tsddr_t_dich_annuale ADD CONSTRAINT fk_indirizzo FOREIGN KEY (id_ind_dep_provv) REFERENCES tsddr_t_indirizzi(id_indirizzo);

ALTER TABLE ONLY tsddr_t_dich_annuale ADD CONSTRAINT fk_stato_dichiarazione FOREIGN KEY (id_stato_dichiarazione) REFERENCES tsddr_d_stati_dichiarazioni(id_stato_dichiarazione);

ALTER TABLE ONLY tsddr_t_soggetti_mr ADD CONSTRAINT fk_dich_annuale FOREIGN KEY (id_dich_annuale) REFERENCES tsddr_t_dich_annuale(id_dich_annuale);

ALTER TABLE ONLY tsddr_t_conferimenti ADD CONSTRAINT fk_dich_annuale FOREIGN KEY (id_dich_annuale) REFERENCES tsddr_t_dich_annuale(id_dich_annuale);

ALTER TABLE ONLY tsddr_t_conferimenti ADD CONSTRAINT fk_periodo FOREIGN KEY (id_periodo) REFERENCES tsddr_d_periodi(id_periodo);

ALTER TABLE ONLY tsddr_t_conferimenti ADD CONSTRAINT fk_rifiuto_tariffa FOREIGN KEY (id_rifiuto_tariffa) REFERENCES tsddr_t_rifiuti_tariffe(id_rifiuto_tariffa);

ALTER TABLE ONLY tsddr_t_conferimenti ADD CONSTRAINT fk_unita_misura FOREIGN KEY (id_unita_misura) REFERENCES tsddr_d_unita_misura(id_unita_misura);

ALTER TABLE ONLY tsddr_t_versamenti ADD CONSTRAINT fk_dich_annuale FOREIGN KEY (id_dich_annuale) REFERENCES tsddr_t_dich_annuale(id_dich_annuale);

ALTER TABLE ONLY tsddr_t_versamenti ADD CONSTRAINT fk_periodo FOREIGN KEY (id_periodo) REFERENCES tsddr_d_periodi(id_periodo);

ALTER TABLE ONLY tsddr_t_prev_cons ADD CONSTRAINT fk_impianto_linea FOREIGN KEY (id_impianto_linea) REFERENCES tsddr_r_impianti_linee(id_impianto_linea);

ALTER TABLE ONLY tsddr_t_prev_cons ADD CONSTRAINT fk_tipo_doc FOREIGN KEY (id_tipo_documento) REFERENCES tsddr_d_tipo_doc(id_tipo_doc);

ALTER TABLE ONLY tsddr_t_prev_cons_dett ADD CONSTRAINT fk_prev_cons FOREIGN KEY (id_prev_cons) REFERENCES tsddr_t_prev_cons(id_prev_cons);

ALTER TABLE ONLY tsddr_t_prev_cons_dett ADD CONSTRAINT fk_unita_misura FOREIGN KEY (id_unita_misura) REFERENCES tsddr_d_unita_misura(id_unita_misura);

ALTER TABLE ONLY tsddr_t_prev_cons_dett ADD CONSTRAINT fk_sezione FOREIGN KEY (id_sezione) REFERENCES tsddr_d_sezioni(id_sezione);

ALTER TABLE ONLY tsddr_t_prev_cons_dett ADD CONSTRAINT fk_eer FOREIGN KEY (id_eer) REFERENCES tsddr_d_eer(id_eer);

ALTER TABLE ONLY tsddr_t_report_dett ADD CONSTRAINT fk_tsddr_t_report FOREIGN KEY (id_report) REFERENCES tsddr_t_report(id_report);

ALTER TABLE ONLY tsddr_t_report_dett ADD CONSTRAINT fk_tsddr_d_tipi_campi FOREIGN KEY (id_tipo_campo) REFERENCES tsddr_d_tipi_campi(id_tipo_campo);
