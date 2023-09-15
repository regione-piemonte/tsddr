ALTER TABLE ONLY tsddr_t_indirizzi ALTER COLUMN id_nazione DROP NOT NULL;
ALTER TABLE ONLY tsddr_t_indirizzi ALTER COLUMN indirizzo DROP NOT NULL;

CREATE SEQUENCE IF NOT EXISTS tsddr_t_legali_rappresentanti_id_legale_rapp_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE tsddr_t_legali_rappresentanti_id_legale_rapp_seq OWNED BY tsddr_t_legali_rappresentanti.id_legale_rapp;