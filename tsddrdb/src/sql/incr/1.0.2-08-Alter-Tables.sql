-- ALTER TSDDR_T_PREV_CONS
ALTER TABLE TSDDR_T_PREV_CONS ADD COLUMN id_ind_dep_provv_pc BIGINT;

ALTER TABLE ONLY TSDDR_T_PREV_CONS ADD CONSTRAINT fk_tsddr_t_indirizzi FOREIGN KEY (id_ind_dep_provv_pc) REFERENCES tsddr_t_indirizzi(id_indirizzo);

-- ALTER TSDDR_T_PREV_CONS_DETT
ALTER TABLE TSDDR_T_PREV_CONS_DETT ADD COLUMN id_prev_cons_dett_r_mr BIGINT;