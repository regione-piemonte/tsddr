ALTER TABLE ONLY tsddr_t_dich_annuale ALTER COLUMN annotazioni DROP NOT NULL;

UPDATE tsddr_t_report_dett SET cod_campo='SEZ_VERS_MR' WHERE id_dettaglio=19;