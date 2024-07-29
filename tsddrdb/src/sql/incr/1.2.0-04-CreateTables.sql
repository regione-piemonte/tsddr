
DROP TABLE if exists  tsddr_d_anni_pregresso;

CREATE TABLE tsddr_d_anni_pregresso (
    id_anno_pregresso bigint NOT NULL,
    data_delete timestamp without time zone,
    data_insert timestamp without time zone,
    data_update timestamp without time zone,
    id_user_delete bigint,
    id_user_insert bigint,
    id_user_update bigint,
    data_fine_validita date,
    data_inizio_validita date DEFAULT now() NOT NULL,
    desc_anno_pregresso character varying(100) NOT NULL
);


--
-- Name: tsddr_d_anni_pregresso_id_anno_pregresso_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tsddr_d_anni_pregresso_id_anno_pregresso_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tsddr_d_anni_pregresso_id_anno_pregresso_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tsddr_d_anni_pregresso_id_anno_pregresso_seq OWNED BY tsddr_d_anni_pregresso.id_anno_pregresso;


--
-- Name: tsddr_d_anni_pregresso id_anno_pregresso; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_anni_pregresso ALTER COLUMN id_anno_pregresso SET DEFAULT nextval('tsddr_d_anni_pregresso_id_anno_pregresso_seq'::regclass);


--
-- Name: tsddr_d_anni_pregresso tsddr_d_anni_pregresso_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tsddr_d_anni_pregresso
    ADD CONSTRAINT tsddr_d_anni_pregresso_pkey PRIMARY KEY (id_anno_pregresso);




