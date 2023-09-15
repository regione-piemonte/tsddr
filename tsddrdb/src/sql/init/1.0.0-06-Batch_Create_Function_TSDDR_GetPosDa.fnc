CREATE OR REPLACE FUNCTION GetPosDa(
    pnomefile character varying,
    pnomecampo character varying)
  RETURNS integer AS
$BODY$
DECLARE
  nPOS_DA  integer;
  
BEGIN
  SELECT  POS_DA
  INTO    nPOS_DA 
  FROM    TSDDR_PAR_FILE 
  WHERE   FILE= pnomefile
  AND     NOME_CAMPO =pnomecampo;
  ------------
  RETURN nPOS_DA; 
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

ALTER FUNCTION GetPosDa(character varying,character varying)
  OWNER TO tsddr;

GRANT EXECUTE ON FUNCTION GetPosDA(character varying,character varying) TO tsddr_rw;
