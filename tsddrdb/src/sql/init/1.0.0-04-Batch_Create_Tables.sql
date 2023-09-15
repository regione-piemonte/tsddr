-- tsddr_d_regioni 
alter table tsddr_d_regioni 
alter column id_istat_regione type CHARACTER VARYING(2);
-- tsddr_d_province
alter table tsddr_d_province
alter column id_provincia_istat type CHARACTER VARYING(3);
-- tsddr_d_comuni
alter table tsddr_d_comuni 
alter column id_comune_istat type CHARACTER VARYING(6);
--
-- TSDDR_STATI_ESTERI
create table TSDDR_STATI_ESTERI
(Record CHARACTER VARYING(500) not null
);
-- TSDDR_LIMITI_AMMINISTRATIVI
create table TSDDR_LIMITI_AMMINISTRATIVI
(Record CHARACTER VARYING(1000) not null
);
-- TSDDR_PAR_FILE
create table TSDDR_PAR_FILE
(ID_PAR_FILE INTEGER not null,
FILE CHARACTER VARYING (50)	not null, 
NOME_CAMPO	CHARACTER VARYING(50) not null,		
POS_DA	INTEGER	not null,			
POS_A	INTEGER	not null,		
LUNGHEZZA	INTEGER	not null,
FORMATO	CHARACTER VARYING(50),
NULL_VALUE	CHARACTER VARYING(50),
CONSTRAINT PK_TSDDR_PAR_FILE PRIMARY KEY (ID_PAR_FILE)
);
-- COMMENT
COMMENT ON column TSDDR_PAR_FILE.ID_PAR_FILE is 'Primary Key';
COMMENT ON column TSDDR_PAR_FILE.FILE is 'Nome logico file';
COMMENT ON column TSDDR_PAR_FILE.NOME_CAMPO is 'Nome logico campo file';
COMMENT ON column TSDDR_PAR_FILE.POS_DA is 'Carattere di partenza del campo nel file';
COMMENT ON column TSDDR_PAR_FILE.POS_A is 'Carattere di arrivo del campo nel file';
COMMENT ON column TSDDR_PAR_FILE.LUNGHEZZA is 'Numero caratteri campo';
COMMENT ON column TSDDR_PAR_FILE.FORMATO is 'Formato di lettura del campo';
COMMENT ON column TSDDR_PAR_FILE.NULL_VALUE is 'Se valorizzato indica che il valore indicato è paragonabile a Null Es per data = Null potrebbe esserci il valore 00/00/0000';
