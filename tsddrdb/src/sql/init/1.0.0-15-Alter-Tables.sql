ALTER TABLE ONLY tsddr_d_sezioni ALTER COLUMN desc_sezione TYPE character varying(500);

ALTER TABLE ONLY tsddr_t_report_dett ALTER COLUMN cod_campo TYPE character varying(50);

ALTER TABLE ONLY tsddr_t_report_dett ALTER COLUMN testo TYPE character varying(1500);

ALTER TABLE ONLY tsddr_t_report_dett ALTER COLUMN testo DROP NOT NULL;

ALTER TABLE ONLY tsddr_t_report_dett ALTER COLUMN logo DROP NOT NULL;

ALTER TABLE ONLY tsddr_t_report_dett ALTER COLUMN firma DROP NOT NULL;

ALTER TABLE ONLY tsddr_t_report ALTER COLUMN desc_report TYPE character varying(50);

ALTER TABLE ONLY tsddr_t_report ALTER COLUMN data_fine_validita DROP NOT NULL;

ALTER TABLE ONLY tsddr_t_dich_annuale ADD COLUMN num_protocollo character varying(50);

ALTER TABLE ONLY tsddr_t_dich_annuale ADD COLUMN data_protocollo date;

ALTER TABLE ONLY tsddr_t_soggetti_mr ADD COLUMN obb_ragg character varying(2);

ALTER TABLE ONLY tsddr_t_dich_annuale ALTER COLUMN credito_imposta TYPE numeric(10,2);

ALTER TABLE ONLY tsddr_t_dich_annuale ALTER COLUMN saldo_imposta TYPE numeric(10,2);

ALTER TABLE ONLY tsddr_t_versamenti ALTER COLUMN data_versamento DROP NOT NULL; 