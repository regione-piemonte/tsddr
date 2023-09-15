CREATE OR REPLACE FUNCTION Pulisci_Log()
  RETURNS integer AS
$BODY$
DECLARE
  
nRet  integer:=0;
  
BEGIN
  ------------
  delete from csi_log_audit cla;
  commit;
  ------------
  RETURN nRet; 
END; 
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION Pulisci_Log()
  OWNER TO tsddr;
GRANT EXECUTE ON FUNCTION Pulisci_Log() TO tsddr_rw;