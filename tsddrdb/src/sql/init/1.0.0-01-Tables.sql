-- DROP TABLES

DROP TABLE if exists  csi_log_audit;

DROP TABLE if exists  tsddr_c_parametri;

DROP TABLE if exists  tsddr_t_indirizzi;

DROP TABLE if exists  tsddr_d_comuni;

DROP TABLE if exists  tsddr_r_funz_prof;

DROP TABLE if exists  tsddr_t_menu;

DROP TABLE if exists  tsddr_d_funzioni;

DROP TABLE if exists  tsddr_r_impianti_linee;

DROP TABLE if exists  tsddr_t_impianti;

DROP TABLE if exists  tsddr_t_legali_rappresentanti;

DROP TABLE if exists  tsddr_t_domande;

DROP TABLE if exists  tsddr_r_utenti_gestori_profili;

DROP TABLE if exists  tsddr_t_gestori;

DROP TABLE if exists  tsddr_d_nature_giuridiche;

DROP TABLE if exists  tsddr_d_province;

DROP TABLE if exists  tsddr_d_regioni;

DROP TABLE if exists  tsddr_d_nazioni;

DROP TABLE if exists  tsddr_r_utenti_prof;

DROP TABLE if exists  tsddr_d_profili;

DROP TABLE if exists  tsddr_d_sedime;

DROP TABLE if exists  tsddr_d_stati_domande;

DROP TABLE if exists  tsddr_d_stati_impianti;

DROP TABLE if exists  tsddr_d_tipi_impianti;

DROP TABLE if exists  tsddr_d_tipi_indirizzi;

DROP TABLE if exists  tsddr_t_messaggi;

DROP TABLE if exists  tsddr_d_tipi_msg;

DROP TABLE if exists  tsddr_d_tipi_profili;

DROP TABLE if exists  tsddr_t_atti;

DROP TABLE if exists  tsddr_d_tipi_provvedimenti;

DROP TABLE if exists  tsddr_email_d_t;

DROP TABLE if exists  tsddr_email_testi;

DROP TABLE if exists  tsddr_t_utenti;

DROP TABLE if exists  tsddr_t_dati_sogg;

DROP TABLE if exists  tsddr_t_linea_sotto_linea_perc;

DROP TABLE if exists  tsddr_t_sotto_linee;

DROP TABLE if exists tsddr_t_linee;

DROP TABLE if exists tsddr_t_note_info;


-- DROP SEQUENCES

DROP SEQUENCE if exists tsddr_c_parametri_id_parametro_seq;

DROP SEQUENCE if exists tsddr_d_comuni_id_comune_seq;

DROP SEQUENCE if exists tsddr_d_funzioni_id_funzione_seq;

DROP SEQUENCE if exists tsddr_d_nature_giuridiche_id_natura_giuridica_seq;

DROP SEQUENCE if exists tsddr_d_nazioni_id_nazione_seq;

DROP SEQUENCE if exists tsddr_d_profili_id_profilo_seq;

DROP SEQUENCE if exists tsddr_d_province_id_provincia_seq;

DROP SEQUENCE if exists tsddr_d_regioni_id_regione_seq;

DROP SEQUENCE if exists tsddr_d_sedime_id_sedime_seq;

DROP SEQUENCE if exists tsddr_d_stati_domande_id_stato_domanda_seq;

DROP SEQUENCE if exists tsddr_d_stati_impianti_id_stato_impianto_seq;

DROP SEQUENCE if exists tsddr_d_tipi_impianti_id_tipo_impianto_seq;

DROP SEQUENCE if exists tsddr_d_tipi_indirizzi_id_tipo_indirizzo_seq;

DROP SEQUENCE if exists tsddr_d_tipi_msg_id_tipo_msg_seq;

DROP SEQUENCE if exists tsddr_d_tipi_profili_id_tipo_profilo_seq;

DROP SEQUENCE if exists tsddr_d_tipi_provvedimenti_id_tipo_provvedimento_seq;

DROP SEQUENCE if exists tsddr_email_d_t_id_casella_seq;

DROP SEQUENCE if exists tsddr_email_testi_id_email_seq;

DROP SEQUENCE if exists tsddr_r_impianti_linee_seq;

DROP SEQUENCE if exists tsddr_r_impianti_linee_id_impianto_linea_seq;

DROP SEQUENCE if exists tsddr_t_atti_id_atto_seq;

DROP SEQUENCE if exists tsddr_t_dati_sogg_id_dati_sogg_seq;

DROP SEQUENCE if exists tsddr_t_domande_id_domanda_seq;

DROP SEQUENCE if exists tsddr_t_gestori_id_gestore_seq;

DROP SEQUENCE if exists tsddr_t_impianti_id_impianto_seq;

DROP SEQUENCE if exists tsddr_t_indirizzi_id_indirizzo_seq;

DROP SEQUENCE if exists tsddr_t_legali_rappresentanti_id_legale_rapp_seq;

DROP SEQUENCE if exists tsddr_t_linea_sotto_linea_perc_id_linea_sotto_linea_perc_seq;

DROP SEQUENCE if exists tsddr_t_linee_id_linea_seq;

DROP SEQUENCE if exists tsddr_t_menu_id_menu_seq;

DROP SEQUENCE if exists tsddr_t_messaggi_id_messaggio_seq;

DROP SEQUENCE if exists tsddr_t_note_info_id_nota_info_seq;

DROP SEQUENCE if exists tsddr_t_sotto_linee_id_sotto_linea_seq;

DROP SEQUENCE if exists tsddr_t_utenti_id_utente_seq;

DROP SEQUENCE if exists csi_log_audit_id_csi_log_audit_seq;



--
-- Name: csi_log_audit; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE csi_log_audit (
    data_ora timestamp with time zone DEFAULT now() NOT NULL,
    id_app character varying(100) NOT NULL,
    key_oper character varying(500),
    ogg_oper character varying(500) NOT NULL,
    operazione character varying(100) NOT NULL,
    utente character varying(40) NOT NULL
);


--
-- Name: tsddr_c_parametri; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_c_parametri (
    id_parametro bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    nome_parametro character varying(100) NOT NULL,
    valore_parametro_b boolean,
    valore_parametro_n bigint,
    valore_parametro_s character varying(100)
);


--
-- Name: tsddr_c_parametri_id_parametro_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_c_parametri_id_parametro_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_c_parametri_id_parametro_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_c_parametri_id_parametro_seq OWNED BY tsddr_c_parametri.id_parametro;


--
-- Name: tsddr_d_comuni; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_d_comuni (
    id_comune bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    cap character varying(5) NOT NULL,
    cod_catasto character varying(255) NOT NULL,
    comune character varying(100) NOT NULL,
    id_comune_istat bigint NOT NULL,
    id_provincia bigint
);


--
-- Name: tsddr_d_comuni_id_comune_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_d_comuni_id_comune_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_d_comuni_id_comune_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_d_comuni_id_comune_seq OWNED BY tsddr_d_comuni.id_comune;


--
-- Name: tsddr_d_funzioni_id_funzione_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_d_funzioni_id_funzione_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_d_funzioni; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_d_funzioni (
    id_funzione bigint DEFAULT nextval('tsddr_d_funzioni_id_funzione_seq'::regclass) NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    cod_funzione character varying(10) NOT NULL,
    desc_funzione character varying(100) NOT NULL
);


--
-- Name: tsddr_d_nature_giuridiche; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_d_nature_giuridiche (
    id_natura_giuridica bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    desc_natura_giuridica character varying(100) NOT NULL
);


--
-- Name: tsddr_d_nature_giuridiche_id_natura_giuridica_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_d_nature_giuridiche_id_natura_giuridica_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_d_nature_giuridiche_id_natura_giuridica_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_d_nature_giuridiche_id_natura_giuridica_seq OWNED BY tsddr_d_nature_giuridiche.id_natura_giuridica;


--
-- Name: tsddr_d_nazioni; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_d_nazioni (
    id_nazione bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    desc_nazione character varying(100) NOT NULL,
    id_istat_nazione character varying(3) NOT NULL
);


--
-- Name: tsddr_d_nazioni_id_nazione_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_d_nazioni_id_nazione_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_d_nazioni_id_nazione_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_d_nazioni_id_nazione_seq OWNED BY tsddr_d_nazioni.id_nazione;


--
-- Name: tsddr_d_profili; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_d_profili (
    id_profilo bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    desc_profilo character varying(255),
    id_tipo_profilo bigint
);


--
-- Name: tsddr_d_profili_id_profilo_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_d_profili_id_profilo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_d_profili_id_profilo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_d_profili_id_profilo_seq OWNED BY tsddr_d_profili.id_profilo;


--
-- Name: tsddr_d_province; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_d_province (
    id_provincia bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    desc_provincia character varying(100) NOT NULL,
    id_provincia_istat bigint NOT NULL,
    sigla_prov character varying(5) NOT NULL,
    id_regione bigint
);


--
-- Name: tsddr_d_province_id_provincia_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_d_province_id_provincia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_d_province_id_provincia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_d_province_id_provincia_seq OWNED BY tsddr_d_province.id_provincia;


--
-- Name: tsddr_d_regioni; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_d_regioni (
    id_regione bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    desc_regione character varying(100) NOT NULL,
    id_istat_regione bigint NOT NULL,
    id_nazione bigint
);


--
-- Name: tsddr_d_regioni_id_regione_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_d_regioni_id_regione_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_d_regioni_id_regione_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_d_regioni_id_regione_seq OWNED BY tsddr_d_regioni.id_regione;


--
-- Name: tsddr_d_sedime; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_d_sedime (
    id_sedime bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    desc_sedime character varying(100) NOT NULL
);


--
-- Name: tsddr_d_sedime_id_sedime_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_d_sedime_id_sedime_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_d_sedime_id_sedime_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_d_sedime_id_sedime_seq OWNED BY tsddr_d_sedime.id_sedime;


--
-- Name: tsddr_d_stati_domande; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_d_stati_domande (
    id_stato_domanda bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    desc_stato_domanda character varying(255) NOT NULL,
    step bigint NOT NULL
);


--
-- Name: tsddr_d_stati_domande_id_stato_domanda_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_d_stati_domande_id_stato_domanda_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_d_stati_domande_id_stato_domanda_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_d_stati_domande_id_stato_domanda_seq OWNED BY tsddr_d_stati_domande.id_stato_domanda;


--
-- Name: tsddr_d_stati_impianti; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_d_stati_impianti (
    id_stato_impianto bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    desc_stato_impianto character varying(100) NOT NULL
);


--
-- Name: tsddr_d_stati_impianti_id_stato_impianto_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_d_stati_impianti_id_stato_impianto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_d_stati_impianti_id_stato_impianto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_d_stati_impianti_id_stato_impianto_seq OWNED BY tsddr_d_stati_impianti.id_stato_impianto;


--
-- Name: tsddr_d_tipi_impianti; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_d_tipi_impianti (
    id_tipo_impianto bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    desc_tipo_impianto character varying(100) NOT NULL
);


--
-- Name: tsddr_d_tipi_impianti_id_tipo_impianto_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_d_tipi_impianti_id_tipo_impianto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_d_tipi_impianti_id_tipo_impianto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_d_tipi_impianti_id_tipo_impianto_seq OWNED BY tsddr_d_tipi_impianti.id_tipo_impianto;


--
-- Name: tsddr_d_tipi_indirizzi; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_d_tipi_indirizzi (
    id_tipo_indirizzo bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    desc_tipo_indirizzo character varying(100) NOT NULL
);


--
-- Name: tsddr_d_tipi_indirizzi_id_tipo_indirizzo_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_d_tipi_indirizzi_id_tipo_indirizzo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_d_tipi_indirizzi_id_tipo_indirizzo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_d_tipi_indirizzi_id_tipo_indirizzo_seq OWNED BY tsddr_d_tipi_indirizzi.id_tipo_indirizzo;


--
-- Name: tsddr_d_tipi_msg; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_d_tipi_msg (
    id_tipo_msg bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    desc_tipo_msg character varying(100) NOT NULL
);


--
-- Name: tsddr_d_tipi_msg_id_tipo_msg_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_d_tipi_msg_id_tipo_msg_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_d_tipi_msg_id_tipo_msg_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_d_tipi_msg_id_tipo_msg_seq OWNED BY tsddr_d_tipi_msg.id_tipo_msg;


--
-- Name: tsddr_d_tipi_profili; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_d_tipi_profili (
    id_tipo_profilo bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    desc_tipo_profilo character varying(100) NOT NULL
);


--
-- Name: tsddr_d_tipi_profili_id_tipo_profilo_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_d_tipi_profili_id_tipo_profilo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_d_tipi_profili_id_tipo_profilo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_d_tipi_profili_id_tipo_profilo_seq OWNED BY tsddr_d_tipi_profili.id_tipo_profilo;


--
-- Name: tsddr_d_tipi_provvedimenti; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_d_tipi_provvedimenti (
    id_tipo_provvedimento bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    desc_tipo_provvedimento character varying(100) NOT NULL
);


--
-- Name: tsddr_d_tipi_provvedimenti_id_tipo_provvedimento_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_d_tipi_provvedimenti_id_tipo_provvedimento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_d_tipi_provvedimenti_id_tipo_provvedimento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_d_tipi_provvedimenti_id_tipo_provvedimento_seq OWNED BY tsddr_d_tipi_provvedimenti.id_tipo_provvedimento;


--
-- Name: tsddr_email_d_t; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_email_d_t (
    id_casella bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    autenticazione character varying(200) DEFAULT 'Nessuna' NOT NULL,
    casella_di_posta character varying(200) NOT NULL,
    nome_server character varying(200) NOT NULL,
    porta character varying(200) NOT NULL,
    sicurezza_conn character varying(200) NOT NULL
);


--
-- Name: tsddr_email_d_t_id_casella_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_email_d_t_id_casella_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_email_d_t_id_casella_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_email_d_t_id_casella_seq OWNED BY tsddr_email_d_t.id_casella;


--
-- Name: tsddr_email_testi; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_email_testi (
    id_email bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    corpo character varying(1000) NOT NULL,
    desc_email character varying(100) NOT NULL,
    destinatari character varying(1000),
    fondo_email bytea,
    mittente character varying(100) NOT NULL,
    oggetto character varying(200) NOT NULL
);


--
-- Name: tsddr_email_testi_id_email_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_email_testi_id_email_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_email_testi_id_email_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_email_testi_id_email_seq OWNED BY tsddr_email_testi.id_email;


--
-- Name: tsddr_r_funz_prof; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_r_funz_prof (
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    delete boolean NOT NULL,
    insert boolean NOT NULL,
    read boolean NOT NULL,
    update boolean NOT NULL,
    id_profilo bigint NOT NULL,
    id_funzione bigint NOT NULL
);


--
-- Name: tsddr_r_impianti_linee; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_r_impianti_linee (
    id_impianto_linea bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    id_sotto_linea bigint,
    id_linea bigint,
    id_impianto bigint NOT NULL
);


--
-- Name: tsddr_r_impianti_linee_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_r_impianti_linee_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_r_impianti_linee_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_r_impianti_linee_seq OWNED BY tsddr_r_impianti_linee.id_impianto_linea;


--
-- Name: tsddr_r_utenti_gestori_profili; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_r_utenti_gestori_profili (
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    id_utente bigint NOT NULL,
    id_gestore bigint NOT NULL,
    id_profilo bigint NOT NULL
);


--
-- Name: tsddr_r_utenti_prof; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_r_utenti_prof (
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    id_utente bigint NOT NULL,
    id_profilo bigint NOT NULL
);


--
-- Name: tsddr_t_atti; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_t_atti (
    id_atto bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_autorizzazione date,
    data_inizio_autorizzazione date NOT NULL,
    data_provvedimento date,
    num_provvedimento character varying(50) NOT NULL,
    id_tipo_provvedimento bigint,
    id_impianto bigint
);


--
-- Name: tsddr_t_atti_id_atto_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_t_atti_id_atto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_t_atti_id_atto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_t_atti_id_atto_seq OWNED BY tsddr_t_atti.id_atto;


--
-- Name: tsddr_t_dati_sogg; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_t_dati_sogg (
    id_dati_sogg bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    cod_fiscale character varying(255) NOT NULL,
    cognome character varying(255) NOT NULL,
    email character varying(255),
    nome character varying(255) NOT NULL,
    pec character varying(255),
    telefono character varying(255),
    telefono_2 character varying(255)
);


--
-- Name: tsddr_t_dati_sogg_id_dati_sogg_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_t_dati_sogg_id_dati_sogg_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_t_dati_sogg_id_dati_sogg_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_t_dati_sogg_id_dati_sogg_seq OWNED BY tsddr_t_dati_sogg.id_dati_sogg;


--
-- Name: tsddr_t_domande; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_t_domande (
    id_domanda bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_richiesta date NOT NULL,
    data_risposta date,
    nota_lavorazione character varying(255),
    nota_utente character varying(255),
    id_dati_sogg bigint,
    id_gestore bigint,
    id_stato_domanda bigint
);


--
-- Name: tsddr_t_domande_id_domanda_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_t_domande_id_domanda_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_t_domande_id_domanda_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_t_domande_id_domanda_seq OWNED BY tsddr_t_domande.id_domanda;


--
-- Name: tsddr_t_gestori; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_t_gestori (
    id_gestore bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    cod_fisc_partiva character varying(16) NOT NULL,
    email character varying(100),
    id_iscrizione_albo character varying(50),
    id_sede_legale bigint NOT NULL,
    pec character varying(100),
    rag_sociale character varying(200) NOT NULL,
    telefono character varying(50),
    id_natura_giuridica bigint
);


--
-- Name: tsddr_t_gestori_id_gestore_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_t_gestori_id_gestore_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_t_gestori_id_gestore_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_t_gestori_id_gestore_seq OWNED BY tsddr_t_gestori.id_gestore;


--
-- Name: tsddr_t_impianti; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_t_impianti (
    id_impianto bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    denominazione character varying(200) NOT NULL,
    id_gestore bigint,
    id_stato bigint,
    id_tipo_impianto bigint,
    id_indirizzo bigint NOT NULL
);


--
-- Name: tsddr_t_impianti_id_impianto_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_t_impianti_id_impianto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_t_impianti_id_impianto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_t_impianti_id_impianto_seq OWNED BY tsddr_t_impianti.id_impianto;


--
-- Name: tsddr_t_indirizzi; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_t_indirizzi (
    id_indirizzo bigint NOT NULL,
    versione bigint DEFAULT 1 NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    indirizzo character varying(100) NOT NULL,
    id_comune bigint,
    id_sedime bigint,
    id_tipo_indirizzo bigint,
    id_nazione bigint NOT NULL
);


--
-- Name: tsddr_t_indirizzi_id_indirizzo_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_t_indirizzi_id_indirizzo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_t_indirizzi_id_indirizzo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_t_indirizzi_id_indirizzo_seq OWNED BY tsddr_t_indirizzi.id_indirizzo;


--
-- Name: tsddr_t_legali_rappresentanti; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_t_legali_rappresentanti (
    id_gestore bigint NOT NULL,
    id_legale_rapp bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    qualifica character varying(50) NOT NULL,
    id_dati_sogg bigint
);


--
-- Name: tsddr_t_legali_rappresentanti_id_legale_rapp_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_t_legali_rappresentanti_id_legale_rapp_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_t_legali_rappresentanti_id_legale_rapp_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_t_legali_rappresentanti_id_legale_rapp_seq OWNED BY tsddr_t_legali_rappresentanti.id_legale_rapp;


--
-- Name: tsddr_t_linea_sotto_linea_perc; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_t_linea_sotto_linea_perc (
    id_linea_sotto_linea_perc bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    per_max_scarto integer NOT NULL,
    per_min_recupero integer NOT NULL,
    id_linea bigint,
    id_sotto_linea bigint
);


--
-- Name: tsddr_t_linea_sotto_linea_perc_id_linea_sotto_linea_perc_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_t_linea_sotto_linea_perc_id_linea_sotto_linea_perc_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_t_linea_sotto_linea_perc_id_linea_sotto_linea_perc_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_t_linea_sotto_linea_perc_id_linea_sotto_linea_perc_seq OWNED BY tsddr_t_linea_sotto_linea_perc.id_linea_sotto_linea_perc;


--
-- Name: tsddr_t_linee; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_t_linee (
    id_linea bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    desc_linea character varying(300) NOT NULL,
    flag_sotto_linea boolean NOT NULL
);


--
-- Name: tsddr_t_linee_id_linea_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_t_linee_id_linea_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_t_linee_id_linea_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_t_linee_id_linea_seq OWNED BY tsddr_t_linee.id_linea;


--
-- Name: tsddr_t_menu; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_t_menu (
    id_menu bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    desc_voce_menu character varying(255) NOT NULL,
    descrizione_card character varying(255),
    livello bigint NOT NULL,
    id_funzione bigint NOT NULL,
    id_padre bigint DEFAULT 0 NOT NULL
);


--
-- Name: tsddr_t_menu_id_menu_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_t_menu_id_menu_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_t_menu_id_menu_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_t_menu_id_menu_seq OWNED BY tsddr_t_menu.id_menu;


--
-- Name: tsddr_t_messaggi; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_t_messaggi (
    id_messaggio bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    cod_msg character varying(20) NOT NULL,
    testo_msg character varying(500) NOT NULL,
    titolo_msg character varying(200) NOT NULL,
    id_tipo_msg bigint
);


--
-- Name: tsddr_t_messaggi_id_messaggio_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_t_messaggi_id_messaggio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_t_messaggi_id_messaggio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_t_messaggi_id_messaggio_seq OWNED BY tsddr_t_messaggi.id_messaggio;


--
-- Name: tsddr_t_note_info; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_t_note_info (
    id_nota_info bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    cod_nota_info character varying(20) NOT NULL,
    etichetta_campo character varying(100) NOT NULL,
    media bytea,
    nome_form character varying(100),
    testo_info character varying(500) NOT NULL,
    titolo_info character varying(200)
);


--
-- Name: tsddr_t_note_info_id_nota_info_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_t_note_info_id_nota_info_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_t_note_info_id_nota_info_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_t_note_info_id_nota_info_seq OWNED BY tsddr_t_note_info.id_nota_info;


--
-- Name: tsddr_t_sotto_linee; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_t_sotto_linee (
    id_sotto_linea bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    desc_sotto_linea character varying(300) NOT NULL,
    id_linea bigint NOT NULL
);


--
-- Name: tsddr_t_sotto_linee_id_sotto_linea_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_t_sotto_linee_id_sotto_linea_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_t_sotto_linee_id_sotto_linea_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_t_sotto_linee_id_sotto_linea_seq OWNED BY tsddr_t_sotto_linee.id_sotto_linea;


--
-- Name: tsddr_t_utenti; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tsddr_t_utenti (
    id_utente bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    id_dati_sogg bigint NOT NULL
);


--
-- Name: tsddr_t_utenti_id_utente_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_t_utenti_id_utente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_t_utenti_id_utente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_t_utenti_id_utente_seq OWNED BY tsddr_t_utenti.id_utente;


--
-- Name: tsddr_c_parametri id_parametro; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_c_parametri ALTER COLUMN id_parametro SET DEFAULT nextval('tsddr_c_parametri_id_parametro_seq'::regclass);


--
-- Name: tsddr_d_comuni id_comune; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_comuni ALTER COLUMN id_comune SET DEFAULT nextval('tsddr_d_comuni_id_comune_seq'::regclass);


--
-- Name: tsddr_d_nature_giuridiche id_natura_giuridica; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_nature_giuridiche ALTER COLUMN id_natura_giuridica SET DEFAULT nextval('tsddr_d_nature_giuridiche_id_natura_giuridica_seq'::regclass);


--
-- Name: tsddr_d_nazioni id_nazione; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_nazioni ALTER COLUMN id_nazione SET DEFAULT nextval('tsddr_d_nazioni_id_nazione_seq'::regclass);


--
-- Name: tsddr_d_profili id_profilo; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_profili ALTER COLUMN id_profilo SET DEFAULT nextval('tsddr_d_profili_id_profilo_seq'::regclass);


--
-- Name: tsddr_d_province id_provincia; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_province ALTER COLUMN id_provincia SET DEFAULT nextval('tsddr_d_province_id_provincia_seq'::regclass);


--
-- Name: tsddr_d_regioni id_regione; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_regioni ALTER COLUMN id_regione SET DEFAULT nextval('tsddr_d_regioni_id_regione_seq'::regclass);


--
-- Name: tsddr_d_sedime id_sedime; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_sedime ALTER COLUMN id_sedime SET DEFAULT nextval('tsddr_d_sedime_id_sedime_seq'::regclass);


--
-- Name: tsddr_d_stati_domande id_stato_domanda; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_stati_domande ALTER COLUMN id_stato_domanda SET DEFAULT nextval('tsddr_d_stati_domande_id_stato_domanda_seq'::regclass);


--
-- Name: tsddr_d_stati_impianti id_stato_impianto; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_stati_impianti ALTER COLUMN id_stato_impianto SET DEFAULT nextval('tsddr_d_stati_impianti_id_stato_impianto_seq'::regclass);


--
-- Name: tsddr_d_tipi_impianti id_tipo_impianto; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_tipi_impianti ALTER COLUMN id_tipo_impianto SET DEFAULT nextval('tsddr_d_tipi_impianti_id_tipo_impianto_seq'::regclass);


--
-- Name: tsddr_d_tipi_indirizzi id_tipo_indirizzo; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_tipi_indirizzi ALTER COLUMN id_tipo_indirizzo SET DEFAULT nextval('tsddr_d_tipi_indirizzi_id_tipo_indirizzo_seq'::regclass);


--
-- Name: tsddr_d_tipi_msg id_tipo_msg; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_tipi_msg ALTER COLUMN id_tipo_msg SET DEFAULT nextval('tsddr_d_tipi_msg_id_tipo_msg_seq'::regclass);


--
-- Name: tsddr_d_tipi_profili id_tipo_profilo; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_tipi_profili ALTER COLUMN id_tipo_profilo SET DEFAULT nextval('tsddr_d_tipi_profili_id_tipo_profilo_seq'::regclass);


--
-- Name: tsddr_d_tipi_provvedimenti id_tipo_provvedimento; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_tipi_provvedimenti ALTER COLUMN id_tipo_provvedimento SET DEFAULT nextval('tsddr_d_tipi_provvedimenti_id_tipo_provvedimento_seq'::regclass);


--
-- Name: tsddr_email_d_t id_casella; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_email_d_t ALTER COLUMN id_casella SET DEFAULT nextval('tsddr_email_d_t_id_casella_seq'::regclass);


--
-- Name: tsddr_email_testi id_email; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_email_testi ALTER COLUMN id_email SET DEFAULT nextval('tsddr_email_testi_id_email_seq'::regclass);


--
-- Name: tsddr_r_impianti_linee id_impianto_linea; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_r_impianti_linee ALTER COLUMN id_impianto_linea SET DEFAULT nextval('tsddr_r_impianti_linee_seq'::regclass);


--
-- Name: tsddr_t_atti id_atto; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_atti ALTER COLUMN id_atto SET DEFAULT nextval('tsddr_t_atti_id_atto_seq'::regclass);


--
-- Name: tsddr_t_dati_sogg id_dati_sogg; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_dati_sogg ALTER COLUMN id_dati_sogg SET DEFAULT nextval('tsddr_t_dati_sogg_id_dati_sogg_seq'::regclass);


--
-- Name: tsddr_t_domande id_domanda; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_domande ALTER COLUMN id_domanda SET DEFAULT nextval('tsddr_t_domande_id_domanda_seq'::regclass);


--
-- Name: tsddr_t_gestori id_gestore; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_gestori ALTER COLUMN id_gestore SET DEFAULT nextval('tsddr_t_gestori_id_gestore_seq'::regclass);


--
-- Name: tsddr_t_impianti id_impianto; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_impianti ALTER COLUMN id_impianto SET DEFAULT nextval('tsddr_t_impianti_id_impianto_seq'::regclass);


--
-- Name: tsddr_t_indirizzi id_indirizzo; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_indirizzi ALTER COLUMN id_indirizzo SET DEFAULT nextval('tsddr_t_indirizzi_id_indirizzo_seq'::regclass);


--
-- Name: tsddr_t_legali_rappresentanti id_legale_rapp; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_legali_rappresentanti ALTER COLUMN id_legale_rapp SET DEFAULT nextval('tsddr_t_legali_rappresentanti_id_legale_rapp_seq'::regclass);


--
-- Name: tsddr_t_linea_sotto_linea_perc id_linea_sotto_linea_perc; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_linea_sotto_linea_perc ALTER COLUMN id_linea_sotto_linea_perc SET DEFAULT nextval('tsddr_t_linea_sotto_linea_perc_id_linea_sotto_linea_perc_seq'::regclass);


--
-- Name: tsddr_t_linee id_linea; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_linee ALTER COLUMN id_linea SET DEFAULT nextval('tsddr_t_linee_id_linea_seq'::regclass);


--
-- Name: tsddr_t_menu id_menu; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_menu ALTER COLUMN id_menu SET DEFAULT nextval('tsddr_t_menu_id_menu_seq'::regclass);


--
-- Name: tsddr_t_messaggi id_messaggio; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_messaggi ALTER COLUMN id_messaggio SET DEFAULT nextval('tsddr_t_messaggi_id_messaggio_seq'::regclass);


--
-- Name: tsddr_t_note_info id_nota_info; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_note_info ALTER COLUMN id_nota_info SET DEFAULT nextval('tsddr_t_note_info_id_nota_info_seq'::regclass);


--
-- Name: tsddr_t_sotto_linee id_sotto_linea; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_sotto_linee ALTER COLUMN id_sotto_linea SET DEFAULT nextval('tsddr_t_sotto_linee_id_sotto_linea_seq'::regclass);


--
-- Name: tsddr_t_utenti id_utente; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_utenti ALTER COLUMN id_utente SET DEFAULT nextval('tsddr_t_utenti_id_utente_seq'::regclass);


--
-- Name: csi_log_audit csi_log_audit_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY csi_log_audit
    ADD CONSTRAINT csi_log_audit_pkey PRIMARY KEY (data_ora);


--
-- Name: tsddr_c_parametri tsddr_c_parametri_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_c_parametri
    ADD CONSTRAINT tsddr_c_parametri_pkey PRIMARY KEY (id_parametro);


--
-- Name: tsddr_d_comuni tsddr_d_comuni_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_comuni
    ADD CONSTRAINT tsddr_d_comuni_pkey PRIMARY KEY (id_comune);


--
-- Name: tsddr_d_funzioni tsddr_d_funzioni_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_funzioni
    ADD CONSTRAINT tsddr_d_funzioni_pkey PRIMARY KEY (id_funzione);


--
-- Name: tsddr_d_nature_giuridiche tsddr_d_nature_giuridiche_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_nature_giuridiche
    ADD CONSTRAINT tsddr_d_nature_giuridiche_pkey PRIMARY KEY (id_natura_giuridica);


--
-- Name: tsddr_d_nazioni tsddr_d_nazioni_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_nazioni
    ADD CONSTRAINT tsddr_d_nazioni_pkey PRIMARY KEY (id_nazione);


--
-- Name: tsddr_d_profili tsddr_d_profili_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_profili
    ADD CONSTRAINT tsddr_d_profili_pkey PRIMARY KEY (id_profilo);


--
-- Name: tsddr_d_province tsddr_d_province_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_province
    ADD CONSTRAINT tsddr_d_province_pkey PRIMARY KEY (id_provincia);


--
-- Name: tsddr_d_regioni tsddr_d_regioni_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_regioni
    ADD CONSTRAINT tsddr_d_regioni_pkey PRIMARY KEY (id_regione);


--
-- Name: tsddr_d_sedime tsddr_d_sedime_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_sedime
    ADD CONSTRAINT tsddr_d_sedime_pkey PRIMARY KEY (id_sedime);


--
-- Name: tsddr_d_stati_domande tsddr_d_stati_domande_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_stati_domande
    ADD CONSTRAINT tsddr_d_stati_domande_pkey PRIMARY KEY (id_stato_domanda);


--
-- Name: tsddr_d_stati_impianti tsddr_d_stati_impianti_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_stati_impianti
    ADD CONSTRAINT tsddr_d_stati_impianti_pkey PRIMARY KEY (id_stato_impianto);


--
-- Name: tsddr_d_tipi_impianti tsddr_d_tipi_impianti_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_tipi_impianti
    ADD CONSTRAINT tsddr_d_tipi_impianti_pkey PRIMARY KEY (id_tipo_impianto);


--
-- Name: tsddr_d_tipi_indirizzi tsddr_d_tipi_indirizzi_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_tipi_indirizzi
    ADD CONSTRAINT tsddr_d_tipi_indirizzi_pkey PRIMARY KEY (id_tipo_indirizzo);


--
-- Name: tsddr_d_tipi_msg tsddr_d_tipi_msg_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_tipi_msg
    ADD CONSTRAINT tsddr_d_tipi_msg_pkey PRIMARY KEY (id_tipo_msg);


--
-- Name: tsddr_d_tipi_profili tsddr_d_tipi_profili_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_tipi_profili
    ADD CONSTRAINT tsddr_d_tipi_profili_pkey PRIMARY KEY (id_tipo_profilo);


--
-- Name: tsddr_d_tipi_provvedimenti tsddr_d_tipi_provvedimenti_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_tipi_provvedimenti
    ADD CONSTRAINT tsddr_d_tipi_provvedimenti_pkey PRIMARY KEY (id_tipo_provvedimento);


--
-- Name: tsddr_email_d_t tsddr_email_d_t_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_email_d_t
    ADD CONSTRAINT tsddr_email_d_t_pkey PRIMARY KEY (id_casella);


--
-- Name: tsddr_email_testi tsddr_email_testi_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_email_testi
    ADD CONSTRAINT tsddr_email_testi_pkey PRIMARY KEY (id_email);


--
-- Name: tsddr_r_funz_prof tsddr_r_funz_prof_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_r_funz_prof
    ADD CONSTRAINT tsddr_r_funz_prof_pkey PRIMARY KEY (id_funzione, id_profilo);


--
-- Name: tsddr_r_impianti_linee tsddr_r_impianti_linee_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_r_impianti_linee
    ADD CONSTRAINT tsddr_r_impianti_linee_pkey PRIMARY KEY (id_impianto_linea);


--
-- Name: tsddr_r_utenti_gestori_profili tsddr_r_utenti_gestori_profili_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_r_utenti_gestori_profili
    ADD CONSTRAINT tsddr_r_utenti_gestori_profili_pkey PRIMARY KEY (id_utente, id_profilo, id_gestore);


--
-- Name: tsddr_r_utenti_prof tsddr_r_utenti_prof_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_r_utenti_prof
    ADD CONSTRAINT tsddr_r_utenti_prof_pkey PRIMARY KEY (id_profilo, id_utente);


--
-- Name: tsddr_t_atti tsddr_t_atti_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_atti
    ADD CONSTRAINT tsddr_t_atti_pkey PRIMARY KEY (id_atto);


--
-- Name: tsddr_t_dati_sogg tsddr_t_dati_sogg_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_dati_sogg
    ADD CONSTRAINT tsddr_t_dati_sogg_pkey PRIMARY KEY (id_dati_sogg);


--
-- Name: tsddr_t_domande tsddr_t_domande_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_domande
    ADD CONSTRAINT tsddr_t_domande_pkey PRIMARY KEY (id_domanda);


--
-- Name: tsddr_t_gestori tsddr_t_gestori_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_gestori
    ADD CONSTRAINT tsddr_t_gestori_pkey PRIMARY KEY (id_gestore);


--
-- Name: tsddr_t_impianti tsddr_t_impianti_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_impianti
    ADD CONSTRAINT tsddr_t_impianti_pkey PRIMARY KEY (id_impianto);


--
-- Name: tsddr_t_indirizzi tsddr_t_indirizzi_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_indirizzi
    ADD CONSTRAINT tsddr_t_indirizzi_pkey PRIMARY KEY (id_indirizzo, versione);


--
-- Name: tsddr_t_legali_rappresentanti tsddr_t_legali_rappresentanti_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_legali_rappresentanti
    ADD CONSTRAINT tsddr_t_legali_rappresentanti_pkey PRIMARY KEY (id_gestore, id_legale_rapp);


--
-- Name: tsddr_t_linea_sotto_linea_perc tsddr_t_linea_sotto_linea_perc_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_linea_sotto_linea_perc
    ADD CONSTRAINT tsddr_t_linea_sotto_linea_perc_pkey PRIMARY KEY (id_linea_sotto_linea_perc);


--
-- Name: tsddr_t_linee tsddr_t_linee_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_linee
    ADD CONSTRAINT tsddr_t_linee_pkey PRIMARY KEY (id_linea);


--
-- Name: tsddr_t_menu tsddr_t_menu_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_menu
    ADD CONSTRAINT tsddr_t_menu_pkey PRIMARY KEY (id_menu);


--
-- Name: tsddr_t_messaggi tsddr_t_messaggi_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_messaggi
    ADD CONSTRAINT tsddr_t_messaggi_pkey PRIMARY KEY (id_messaggio);


--
-- Name: tsddr_t_note_info tsddr_t_note_info_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_note_info
    ADD CONSTRAINT tsddr_t_note_info_pkey PRIMARY KEY (id_nota_info);


--
-- Name: tsddr_t_sotto_linee tsddr_t_sotto_linee_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_sotto_linee
    ADD CONSTRAINT tsddr_t_sotto_linee_pkey PRIMARY KEY (id_sotto_linea);


--
-- Name: tsddr_t_utenti tsddr_t_utenti_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_utenti
    ADD CONSTRAINT tsddr_t_utenti_pkey PRIMARY KEY (id_utente);


--
-- Name: tsddr_t_utenti uk_7qhshbj00j4ms56o5i9c792u5f8; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_utenti
    ADD CONSTRAINT uk_7qhshbj00j4ms56o5i9c792u5f8 UNIQUE (id_dati_sogg);


--
-- Name: tsddr_t_gestori uk_awbdjq3743jrljl030h9hy9vl; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_gestori
    ADD CONSTRAINT uk_awbdjq3743jrljl030h9hy9vl UNIQUE (id_sede_legale);


--
-- Name: tsddr_t_impianti uk_id_indirizzo; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_impianti
    ADD CONSTRAINT uk_id_indirizzo UNIQUE (id_indirizzo);


--
-- Name: fki_F; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX "fki_F" ON tsddr_d_profili USING btree (id_profilo);


--
-- Name: fki_fk_7qhd5tj00j48co5i9c792u5f8; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_fk_7qhd5tj00j48co5i9c792u5f8 ON tsddr_t_dati_sogg USING btree (id_dati_sogg);


--
-- Name: fki_fk_7qhshbj00j48co5i9c792u5f8; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_fk_7qhshbj00j48co5i9c792u5f8 ON tsddr_t_indirizzi USING btree (id_indirizzo);


--
-- Name: fki_fk_TSDDR_R_FUNZ_PROF; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX "fki_fk_TSDDR_R_FUNZ_PROF" ON tsddr_d_funzioni USING btree (id_funzione);


--
-- Name: fki_fk_TSDDR_R_UTENTI_PROF; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX "fki_fk_TSDDR_R_UTENTI_PROF" ON tsddr_r_utenti_gestori_profili USING btree (id_profilo, id_utente);


--
-- Name: fki_fk_TSDDR_T_GESTORI; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX "fki_fk_TSDDR_T_GESTORI" ON tsddr_t_domande USING btree (id_gestore);


--
-- Name: fki_fk_TSDDR_T_IMPIANTI; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX "fki_fk_TSDDR_T_IMPIANTI" ON tsddr_t_indirizzi USING btree (id_indirizzo);


--
-- Name: fki_fk_cskdd2xo32sdf54agr1hux3iu4h; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_fk_cskdd2xo32sdf54agr1hux3iu4h ON tsddr_t_impianti USING btree (id_tipo_impianto);


--
-- Name: fki_fk_qdmc4ep272xm4qosn0yxjbk7a; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_fk_qdmc4ep272xm4qosn0yxjbk7a ON tsddr_t_indirizzi USING btree (id_nazione);


--
-- Name: fki_uk_7qhshbj00j48co5i9c792u5f8; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_uk_7qhshbj00j48co5i9c792u5f8 ON tsddr_t_indirizzi USING btree (id_indirizzo);


--
-- Name: tsddr_r_impianti_linee fk_1fyt325xgwdmkuhlv345gjashko; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_r_impianti_linee
    ADD CONSTRAINT fk_1fyt325xgwdmkuhlv345gjashko FOREIGN KEY (id_sotto_linea) REFERENCES tsddr_t_sotto_linee(id_sotto_linea);


--
-- Name: tsddr_r_impianti_linee fk_1fytvixgw4645mkuhlv345gjashko; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_r_impianti_linee
    ADD CONSTRAINT fk_1fytvixgw4645mkuhlv345gjashko FOREIGN KEY (id_linea) REFERENCES tsddr_t_linee(id_linea);


--
-- Name: tsddr_d_regioni fk_1fytvixgwdmkuhlv1ygjashko; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_regioni
    ADD CONSTRAINT fk_1fytvixgwdmkuhlv1ygjashko FOREIGN KEY (id_nazione) REFERENCES tsddr_d_nazioni(id_nazione);


--
-- Name: tsddr_r_impianti_linee fk_1fytvixgwdmkuhlv345gjashko; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_r_impianti_linee
    ADD CONSTRAINT fk_1fytvixgwdmkuhlv345gjashko FOREIGN KEY (id_impianto) REFERENCES tsddr_t_impianti(id_impianto);


--
-- Name: tsddr_t_legali_rappresentanti fk_1l9wlccndslbgwwfnlw0si5ct; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_legali_rappresentanti
    ADD CONSTRAINT fk_1l9wlccndslbgwwfnlw0si5ct FOREIGN KEY (id_dati_sogg) REFERENCES tsddr_t_dati_sogg(id_dati_sogg);


--
-- Name: tsddr_r_utenti_prof fk_1tsb65rgff7ewq08gestee0gw; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_r_utenti_prof
    ADD CONSTRAINT fk_1tsb65rgff7ewq08gestee0gw FOREIGN KEY (id_utente) REFERENCES tsddr_t_utenti(id_utente);


--
-- Name: tsddr_t_linea_sotto_linea_perc fk_3388hj6vupuq7kllkiga519if; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_linea_sotto_linea_perc
    ADD CONSTRAINT fk_3388hj6vupuq7kllkiga519if FOREIGN KEY (id_linea) REFERENCES tsddr_t_linee(id_linea);


--
-- Name: tsddr_d_profili fk_3inljghw9eyg6gigt0iojm8r3; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_profili
    ADD CONSTRAINT fk_3inljghw9eyg6gigt0iojm8r3 FOREIGN KEY (id_tipo_profilo) REFERENCES tsddr_d_tipi_profili(id_tipo_profilo);


--
-- Name: tsddr_t_domande fk_4o9hx8w5o4ku5445dtxrm1c32; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_domande
    ADD CONSTRAINT fk_4o9hx8w5o4ku5445dtxrm1c32 FOREIGN KEY (id_dati_sogg) REFERENCES tsddr_t_dati_sogg(id_dati_sogg);


--
-- Name: tsddr_t_utenti fk_5e6reqmttff34qo89dduy3uia; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_utenti
    ADD CONSTRAINT fk_5e6reqmttff34qo89dduy3uia FOREIGN KEY (id_dati_sogg) REFERENCES tsddr_t_dati_sogg(id_dati_sogg);


--
-- Name: tsddr_r_funz_prof fk_5ys8p20kjs98i3xr3m58fmouo; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_r_funz_prof
    ADD CONSTRAINT fk_5ys8p20kjs98i3xr3m58fmouo FOREIGN KEY (id_funzione) REFERENCES tsddr_d_funzioni(id_funzione);


--
-- Name: tsddr_r_utenti_gestori_profili fk_7874srii0m91cijksdnovs0wb; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_r_utenti_gestori_profili
    ADD CONSTRAINT fk_7874srii0m91cijksdnovs0wb FOREIGN KEY (id_profilo) REFERENCES tsddr_d_profili(id_profilo);


--
-- Name: tsddr_t_indirizzi fk_7qhshbj00j48co5i9c792u5f8; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_indirizzi
    ADD CONSTRAINT fk_7qhshbj00j48co5i9c792u5f8 FOREIGN KEY (id_indirizzo) REFERENCES tsddr_t_gestori(id_sede_legale);


--
-- Name: tsddr_t_impianti fk_9feuoij76mi66jmn5wlbjx65a; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_impianti
    ADD CONSTRAINT fk_9feuoij76mi66jmn5wlbjx65a FOREIGN KEY (id_gestore) REFERENCES tsddr_t_gestori(id_gestore);


--
-- Name: tsddr_r_utenti_gestori_profili fk_TSDDR_R_UTENTI_PROF; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_r_utenti_gestori_profili
    ADD CONSTRAINT "fk_TSDDR_R_UTENTI_PROF" FOREIGN KEY (id_profilo, id_utente) REFERENCES tsddr_r_utenti_prof(id_profilo, id_utente);


--
-- Name: tsddr_t_indirizzi fk_TSDDR_T_IMPIANTI; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_indirizzi
    ADD CONSTRAINT "fk_TSDDR_T_IMPIANTI" FOREIGN KEY (id_indirizzo) REFERENCES tsddr_t_impianti(id_indirizzo);


--
-- Name: tsddr_t_legali_rappresentanti fk_awpp02frnf2o0w26fq4oost3i; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_legali_rappresentanti
    ADD CONSTRAINT fk_awpp02frnf2o0w26fq4oost3i FOREIGN KEY (id_gestore) REFERENCES tsddr_t_gestori(id_gestore);


--
-- Name: tsddr_d_province fk_bgg5v4hlpavy7ncghvv2wyrjx; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_province
    ADD CONSTRAINT fk_bgg5v4hlpavy7ncghvv2wyrjx FOREIGN KEY (id_regione) REFERENCES tsddr_d_regioni(id_regione);


--
-- Name: tsddr_t_impianti fk_cskdd2xo32sdf54agr1hux3iu4h; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_impianti
    ADD CONSTRAINT fk_cskdd2xo32sdf54agr1hux3iu4h FOREIGN KEY (id_tipo_impianto) REFERENCES tsddr_d_tipi_impianti(id_tipo_impianto);


--
-- Name: tsddr_t_domande fk_cuwol88aaym6rxomd66vkiyqe; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_domande
    ADD CONSTRAINT fk_cuwol88aaym6rxomd66vkiyqe FOREIGN KEY (id_gestore) REFERENCES tsddr_t_gestori(id_gestore);


--
-- Name: tsddr_t_atti fk_cvetnj5sjd420ykkshaj1jo29; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_atti
    ADD CONSTRAINT fk_cvetnj5sjd420ykkshaj1jo29 FOREIGN KEY (id_tipo_provvedimento) REFERENCES tsddr_d_tipi_provvedimenti(id_tipo_provvedimento);
    
--
-- Name: tsddr_t_atti fk_aftgtnj5sjd420ykkshaj1jlo89; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_atti
    ADD CONSTRAINT fk_aftgtnj5sjd420ykkshaj1jlo89 FOREIGN KEY (id_impianto) REFERENCES tsddr_t_impianti(id_impianto);


--
-- Name: tsddr_t_indirizzi fk_ems80uhxyg5f8qgci72vhg15v; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_indirizzi
    ADD CONSTRAINT fk_ems80uhxyg5f8qgci72vhg15v FOREIGN KEY (id_comune) REFERENCES tsddr_d_comuni(id_comune);


--
-- Name: tsddr_r_utenti_prof fk_fxy96hydgupq0i9qxn6026fx2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_r_utenti_prof
    ADD CONSTRAINT fk_fxy96hydgupq0i9qxn6026fx2 FOREIGN KEY (id_profilo) REFERENCES tsddr_d_profili(id_profilo);


--
-- Name: tsddr_t_indirizzi fk_g1y7npwx9naolirtmuhbdybxd; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_indirizzi
    ADD CONSTRAINT fk_g1y7npwx9naolirtmuhbdybxd FOREIGN KEY (id_tipo_indirizzo) REFERENCES tsddr_d_tipi_indirizzi(id_tipo_indirizzo);


--
-- Name: tsddr_r_utenti_gestori_profili fk_g8svop1w1p45jaj75wa4c9xfm; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_r_utenti_gestori_profili
    ADD CONSTRAINT fk_g8svop1w1p45jaj75wa4c9xfm FOREIGN KEY (id_utente) REFERENCES tsddr_t_utenti(id_utente);


--
-- Name: tsddr_t_impianti fk_gdqulkillen6cxmq318kb0vn2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_impianti
    ADD CONSTRAINT fk_gdqulkillen6cxmq318kb0vn2 FOREIGN KEY (id_stato) REFERENCES tsddr_d_stati_impianti(id_stato_impianto);


--
-- Name: tsddr_r_utenti_gestori_profili fk_jxilahrx7ca0ng195ij8q00bd; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_r_utenti_gestori_profili
    ADD CONSTRAINT fk_jxilahrx7ca0ng195ij8q00bd FOREIGN KEY (id_gestore) REFERENCES tsddr_t_gestori(id_gestore);


--
-- Name: tsddr_d_comuni fk_l1d7695u1k27dsqhxdc9vnuwh; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_comuni
    ADD CONSTRAINT fk_l1d7695u1k27dsqhxdc9vnuwh FOREIGN KEY (id_provincia) REFERENCES tsddr_d_province(id_provincia);


--
-- Name: tsddr_t_domande fk_m4xp0fvy76op0swlmfadlbo5p; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_domande
    ADD CONSTRAINT fk_m4xp0fvy76op0swlmfadlbo5p FOREIGN KEY (id_stato_domanda) REFERENCES tsddr_d_stati_domande(id_stato_domanda);


--
-- Name: tsddr_t_messaggi fk_mcqdsdahs4y3e6ob33n579jlx; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_messaggi
    ADD CONSTRAINT fk_mcqdsdahs4y3e6ob33n579jlx FOREIGN KEY (id_tipo_msg) REFERENCES tsddr_d_tipi_msg(id_tipo_msg);


--
-- Name: tsddr_t_gestori fk_mwi1o9j38e8x7d0jj3e9d1hmu; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_gestori
    ADD CONSTRAINT fk_mwi1o9j38e8x7d0jj3e9d1hmu FOREIGN KEY (id_natura_giuridica) REFERENCES tsddr_d_nature_giuridiche(id_natura_giuridica);


--
-- Name: tsddr_r_funz_prof fk_pdfohsrctxm9q596d5aq0s04a; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_r_funz_prof
    ADD CONSTRAINT fk_pdfohsrctxm9q596d5aq0s04a FOREIGN KEY (id_profilo) REFERENCES tsddr_d_profili(id_profilo);


--
-- Name: tsddr_t_sotto_linee fk_pgjtnxjbdi7yk6mm7peea7ayu; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_sotto_linee
    ADD CONSTRAINT fk_pgjtnxjbdi7yk6mm7peea7ayu FOREIGN KEY (id_linea) REFERENCES tsddr_t_linee(id_linea);


--
-- Name: tsddr_t_menu fk_pwk4oh2yg2jg8ep7821lb2dol; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_menu
    ADD CONSTRAINT fk_pwk4oh2yg2jg8ep7821lb2dol FOREIGN KEY (id_funzione) REFERENCES tsddr_d_funzioni(id_funzione);


--
-- Name: tsddr_t_linea_sotto_linea_perc fk_q3xo0d6jlsexv5hq3mnecms5w; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_linea_sotto_linea_perc
    ADD CONSTRAINT fk_q3xo0d6jlsexv5hq3mnecms5w FOREIGN KEY (id_sotto_linea) REFERENCES tsddr_t_sotto_linee(id_sotto_linea);


--
-- Name: tsddr_t_indirizzi fk_qdmc4ep272xm4qosn0yxjbk7a; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_indirizzi
    ADD CONSTRAINT fk_qdmc4ep272xm4qosn0yxjbk7a FOREIGN KEY (id_nazione) REFERENCES tsddr_d_nazioni(id_nazione);


--
-- Name: tsddr_t_indirizzi fk_rr4y3blomgfwn1motda0ieapx; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_t_indirizzi
    ADD CONSTRAINT fk_rr4y3blomgfwn1motda0ieapx FOREIGN KEY (id_sedime) REFERENCES tsddr_d_sedime(id_sedime);

