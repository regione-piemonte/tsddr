CREATE OR REPLACE FUNCTION GetPosL(
    pnomefile character varying,
    pnomecampo character varying)
  RETURNS integer AS
$BODY$
DECLARE
  nLun integer;
  
BEGIN
  SELECT  lunghezza
  INTO    nLun 
  FROM    TSDDR_PAR_FILE 
  WHERE   FILE= pnomefile
  AND     NOME_CAMPO =pnomecampo;
  ------------
  RETURN nLun; 
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

ALTER FUNCTION GetPosL(character varying,character varying)
  OWNER TO tsddr;

GRANT EXECUTE ON FUNCTION GetPosL(character varying,character varying) TO tsddr_rw;
