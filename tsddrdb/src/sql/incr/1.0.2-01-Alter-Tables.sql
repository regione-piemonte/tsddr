-- TSDDR_D_EER
ALTER TABLE tsddr_d_eer
DROP COLUMN anno;

ALTER TABLE tsddr_d_eer
ADD COLUMN data_inizio_validita date DEFAULT now() NOT NULL;

ALTER TABLE tsddr_d_eer
ADD COLUMN data_fine_validita date;

ALTER TABLE tsddr_d_eer
ALTER COLUMN descrizione TYPE character varying(500);

-- TSDDR_R_PREV_CONS_LINEE
CREATE TABLE tsddr_r_prev_cons_linee (
    id_prev_cons_linee bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
	id_prev_cons bigint NOT NULL,
	id_impianto_linee bigint NOT NULL,
	desc_sommaria character varying(1000),
	perc_recupero numeric(10,6) DEFAULT 0,
	perc_scarto numeric(10,6) DEFAULT 0
);

CREATE SEQUENCE tsddr_r_prev_cons_linee_id_prev_cons_linee_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE tsddr_r_prev_cons_linee_id_prev_cons_linee_seq OWNED BY tsddr_r_prev_cons_linee.id_prev_cons_linee;

ALTER TABLE ONLY tsddr_r_prev_cons_linee ALTER COLUMN id_prev_cons_linee SET DEFAULT nextval('tsddr_r_prev_cons_linee_id_prev_cons_linee_seq'::regclass);

ALTER TABLE ONLY tsddr_r_prev_cons_linee ADD CONSTRAINT tsddr_r_prev_cons_linee_pkey PRIMARY KEY (id_prev_cons_linee);

ALTER TABLE ONLY tsddr_r_prev_cons_linee ADD CONSTRAINT fk_tsddr_t_prev_cons FOREIGN KEY (id_prev_cons) REFERENCES tsddr_t_prev_cons(id_prev_cons);

ALTER TABLE ONLY tsddr_r_prev_cons_linee ADD CONSTRAINT fk_tsddr_r_impianti_linee FOREIGN KEY (id_impianto_linee) REFERENCES tsddr_r_impianti_linee(id_impianto_linea);

-- TSDDR_T_PREV_CONS
ALTER TABLE tsddr_t_prev_cons
DROP COLUMN id_impianto_linea;

ALTER TABLE tsddr_t_prev_cons
DROP COLUMN desc_sommaria;

ALTER TABLE tsddr_t_prev_cons
DROP COLUMN protocollo;

ALTER TABLE tsddr_t_prev_cons ALTER COLUMN data_doc SET NOT NULL;

ALTER TABLE tsddr_t_prev_cons
ADD COLUMN data_invio_doc date;

ALTER TABLE tsddr_t_prev_cons
ADD COLUMN id_prev_cons_r_mr bigint NOT NULL;

ALTER TABLE tsddr_t_prev_cons
ADD COLUMN num_protocollo character varying(50) NOT NULL;

ALTER TABLE tsddr_t_prev_cons
ADD COLUMN data_protocllo date NOT NULL;

ALTER TABLE tsddr_t_prev_cons
ADD COLUMN id_stato_dichiarazione bigint NOT NULL;

ALTER TABLE ONLY tsddr_t_prev_cons ADD CONSTRAINT fk_tsddr_d_stati_dichiarazioni FOREIGN KEY (id_stato_dichiarazione) REFERENCES tsddr_d_stati_dichiarazioni(id_stato_dichiarazione);

-- TSDDR_T_PREV_CONS_DETT
ALTER TABLE tsddr_t_prev_cons_dett
ADD COLUMN id_prev_cons_linee bigint NOT NULL;

ALTER TABLE tsddr_t_prev_cons_dett
ADD COLUMN desc_mat_uscita character varying(50);

ALTER TABLE tsddr_t_prev_cons_dett 
ALTER COLUMN quantita TYPE NUMERIC(14,4);

ALTER TABLE ONLY tsddr_t_prev_cons_dett ADD CONSTRAINT fk_tsddr_r_prev_cons_linee FOREIGN KEY (id_prev_cons_linee) REFERENCES tsddr_r_prev_cons_linee(id_prev_cons_linee);

ALTER TABLE tsddr_t_prev_cons_dett DROP COLUMN id_prev_cons;

-- TSDDR_T_PREV_CONS
ALTER TABLE tsddr_t_prev_cons
ADD COLUMN id_impianto bigint NOT NULL;

ALTER TABLE ONLY tsddr_t_prev_cons ADD CONSTRAINT fk_tsddr_t_impianti FOREIGN KEY (id_impianto) REFERENCES tsddr_t_impianti(id_impianto);

