ALTER TABLE ONLY tsddr_t_indirizzi DROP CONSTRAINT tsddr_t_indirizzi_pkey;

ALTER TABLE ONLY tsddr_t_indirizzi DROP CONSTRAINT fk_7qhshbj00j48co5i9c792u5f8;

ALTER TABLE ONLY tsddr_t_indirizzi DROP CONSTRAINT "fk_TSDDR_T_IMPIANTI";

ALTER TABLE ONLY tsddr_t_indirizzi ADD COLUMN IF NOT EXISTS original_id BIGINT;

ALTER TABLE ONLY tsddr_t_indirizzi ADD CONSTRAINT tsddr_t_indirizzi_pkey PRIMARY KEY (id_indirizzo);

ALTER TABLE ONLY tsddr_t_indirizzi ADD CONSTRAINT uc_id_versione UNIQUE (original_id, versione);

ALTER TABLE ONLY tsddr_t_gestori DROP CONSTRAINT uk_awbdjq3743jrljl030h9hy9vl;

ALTER TABLE ONLY tsddr_t_gestori ADD CONSTRAINT fk_indirizzi FOREIGN KEY (id_sede_legale) REFERENCES tsddr_t_indirizzi(id_indirizzo);

ALTER TABLE ONLY tsddr_t_impianti DROP CONSTRAINT uk_id_indirizzo;

ALTER TABLE ONLY tsddr_t_impianti ADD CONSTRAINT fk_indirizzi FOREIGN KEY (id_indirizzo) REFERENCES tsddr_t_indirizzi(id_indirizzo);

ALTER TABLE tsddr_t_legali_rappresentanti ALTER COLUMN qualifica DROP NOT NULL;

--- csi_log_audit
ALTER TABLE ONLY csi_log_audit ADD COLUMN IF NOT EXISTS id_csi_log_audit BIGINT;

ALTER TABLE ONLY csi_log_audit ADD COLUMN IF NOT EXISTS ip_address character varying(40);

ALTER TABLE ONLY csi_log_audit DROP CONSTRAINT IF EXISTS csi_log_audit_pkey;

CREATE SEQUENCE IF NOT EXISTS  csi_log_audit_id_csi_log_audit_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE csi_log_audit_id_csi_log_audit_seq OWNED BY csi_log_audit.id_csi_log_audit;

ALTER TABLE ONLY csi_log_audit ALTER COLUMN id_csi_log_audit SET DEFAULT nextval('csi_log_audit_id_csi_log_audit_seq');

UPDATE csi_log_audit SET id_csi_log_audit = nextval('csi_log_audit_id_csi_log_audit_seq');

ALTER TABLE ONLY csi_log_audit ADD CONSTRAINT csi_log_audit_pkey PRIMARY KEY (id_csi_log_audit);

--- tsddr_r_impianti_linee
ALTER SEQUENCE IF EXISTS tsddr_r_impianti_linee_seq RENAME TO tsddr_r_impianti_linee_id_impianto_linea_seq;

--- tsddr_t_atti
ALTER TABLE ONLY tsddr_t_atti ADD COLUMN IF NOT EXISTS id_impianto BIGINT;

