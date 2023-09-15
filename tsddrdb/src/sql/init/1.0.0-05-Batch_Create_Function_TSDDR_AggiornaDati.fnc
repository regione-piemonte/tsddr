CREATE OR REPLACE FUNCTION AggiornaDatiTrasversali()
  RETURNS integer AS
$BODY$
DECLARE
--
rec record;
nRet integer:= 0;
vFlag varchar(3):='000';
vId_Nazione TSDDR_D_NAZIONI.ID_NAZIONE%type;
vID_REGIONE  TSDDR_D_REGIONI.ID_REGIONE%type;
vID_PROVINCIA TSDDR_D_PROVINCE.ID_PROVINCIA%type; 
-- Exception
v_state   TEXT;
v_msg     TEXT;
v_detail  TEXT;
--
-- Gestione File
f_Stat utl_file.file_type;
f_Log  utl_file.file_type;
r_Stat boolean;
r_Log  boolean;
v_file_dir varchar(200);
-- Gestione Statistiche
REC_STA integer;
REC_LIM_AMM integer;
STA_AGG integer;
STA_INS integer;
REG_AGG integer;
REG_INS integer;
PRO_AGG integer;
PRO_INS integer;
COM_AGG integer;
COM_INS integer;
--
nCount integer;
-- 

begin
	
	
	-- Scrivo file statistiche
-- 
  -- Ricavo la dir dove scrivere il file
  --EXECUTE 'SELECT DIR FROM UTL_FILE.UTL_FILE_DIR' INTO v_file_dir;
	SELECT DIRNAME
	INTO v_file_dir
	FROM UTL_FILE.UTL_FILE_DIR
	where dir like '%in%';

  -- Apro file Statistiche e file di Log
  f_Stat := utl_file.fopen(v_file_dir, 'Statistiche.txt', 'w');
  f_Log  := utl_file.fopen(v_file_dir, 'Log.txt', 'w');
  
  r_Stat:= utl_file.put_line(f_Stat,'<<Inizio processo '||current_timestamp||'>>');
  
  
  SELECT Count(*)
  INTO   REC_STA
  FROM   TSDDR_STATI_ESTERI;
 
 r_Stat:= utl_file.put_line(f_Stat,'<<N record Stati Esteri Input: '||REC_STA||'>>');
    
-- Eseguo algoritmo: Aggiorna Nazione
STA_AGG := 0;
STA_INS := 0;
--

 FOR Rec IN
   SELECT SUBSTR(RECORD,GetPosDA('Stati_esteri_istat.txt','ISTAT_STATO'),GetPosL('Stati_esteri_istat.txt','ISTAT_STATO')) ISTAT_STATO,
          SUBSTR(RECORD,GetPosDA('Stati_esteri_istat.txt','D_STOP'),GetPosL('Stati_esteri_istat.txt','D_STOP')) D_STOP,
          SUBSTR(RECORD,GetPosDA('Stati_esteri_istat.txt','DESC_STATO'),GetPosL('Stati_esteri_istat.txt','DESC_STATO')) DESC_STATO,
          SUBSTR(RECORD,GetPosDA('Stati_esteri_istat.txt','D_START'),GetPosL('Stati_esteri_istat.txt','D_START')) D_START
   FROM   TSDDR_STATI_ESTERI
   WHERE  SUBSTR(RECORD,GetPosDA('Stati_esteri_istat.txt','D_STOP'),GetPosL('Stati_esteri_istat.txt','D_STOP')) = 
           (SELECT NULL_VALUE FROM TSDDR_PAR_FILE 
            WHERE   FILE= 'Stati_esteri_istat.txt'                                                                                                                    
              AND   NOME_CAMPO ='D_STOP') 
     AND  SUBSTR(RECORD,GetPosDA('Stati_esteri_istat.txt','R_STATUS'),GetPosL('Stati_esteri_istat.txt','R_STATUS')) = '1'
  LOOP
     Begin
	     
	  SELECT Count(*)
      INTO   nCount	  
	  FROM   TSDDR_D_NAZIONI 
	  WHERE  ID_ISTAT_NAZIONE = trim(rec.ISTAT_STATO);
	  
	  If nCount > 0 Then
		  
		  Update TSDDR_D_NAZIONI  
		  SET    DESC_NAZIONE = TRIM(rec.DESC_STATO),
				 DATA_INIZIO_VALIDITA = to_date(rec.D_START,'dd/mm/yyyy'),
				 DATA_FINE_VALIDITA = null,
				 data_update = current_timestamp 
		  WHERE  ID_ISTAT_NAZIONE = trim(rec.ISTAT_STATO);
		  
		  STA_AGG := STA_AGG + 1;
	  
	  Else
	      
		  Insert INTO TSDDR_D_NAZIONI
		  ( DESC_NAZIONE,		
			ID_ISTAT_NAZIONE,		
			DATA_INIZIO_VALIDITA,	
			DATA_FINE_VALIDITA,
			DATA_INSERT
		  ) VALUES
		  ( trim(Rec.DESC_STATO),
			trim(Rec.ISTAT_STATO),
			to_date(Rec.D_START,'dd/mm/yyyy'),
			null,
			current_timestamp
		  );
		  
		  STA_INS := STA_INS+1;
	  
		 End If;
		
	 Exception When Others THEN
	  GET STACKED DIAGNOSTICS
                v_state   := RETURNED_SQLSTATE,
                v_msg     := MESSAGE_TEXT,
                v_detail  := PG_EXCEPTION_DETAIL;
	-- scrivi file log
	  r_Log:= utl_file.put_line(f_Log,'<<Mancato aggiornamento/inserimento Nazioni: ISTAT_STATO '||rec.istat_stato||'DESC_STATO '||rec.DESC_STATO||
	  ' Codice errore '||v_state||' messaggio errore '||v_msg|| ' DETTAGLIO '||v_detail||'>>');
	  RAISE NOTICE 'exception: % %  ', sqlstate ,  sqlerrm ;
      nRet := 1;
	 End; 
	
END LOOP; 
-- Statistiche STATI
   r_Stat:= utl_file.put_line(f_Stat,'N record Nazioni aggiornati: '||STA_AGG);
   r_Stat:= utl_file.put_line(f_Stat,'N record Nazioni inseriti: '||STA_INS);
--

-- REGIONI
REG_AGG := 0;
REG_INS := 0;
-- Recupero i valori sulla tabella dei parametri TSDDR_PAR_FILE

   Select count(*)
   Into   REC_LIM_AMM
   From   TSDDR_LIMITI_AMMINISTRATIVI;
-- Statistica limiti amministrativi
   r_Stat:= utl_file.put_line(f_Stat,'N record Limiti  Ammin. Input: '||REC_LIM_AMM);
-- da impostare la selezione del min(rec.D_START)
FOR Rec IN  
   SELECT SUBSTR(RECORD,GetPosDA('Limiti_Amministrativi.txt','ISTAT_REGIONE'),GetPosL('Limiti_Amministrativi.txt','ISTAT_REGIONE')) ISTAT_REGIONE,
          SUBSTR(RECORD,GetPosDA('Limiti_Amministrativi.txt','D_STOP'),GetPosL('Limiti_Amministrativi.txt','D_STOP')) D_STOP,
          SUBSTR(RECORD,GetPosDA('Limiti_Amministrativi.txt','DESC_REGIONE'),GetPosL('Limiti_Amministrativi.txt','DESC_REGIONE')) DESC_REGIONE,
          SUBSTR(RECORD,GetPosDA('Limiti_Amministrativi.txt','D_START'),GetPosL('Limiti_Amministrativi.txt','D_START')) D_START,
          SUBSTR(RECORD,GetPosDA('Limiti_Amministrativi.txt','DESC_STATO'),GetPosL('Limiti_Amministrativi.txt','DESC_STATO')) DESC_STATO 
   FROM   TSDDR_LIMITI_AMMINISTRATIVI
   WHERE  SUBSTR(RECORD,GetPosDA('Limiti_Amministrativi.txt','D_STOP'),GetPosL('Limiti_Amministrativi.txt','D_STOP')) = 
           (SELECT NULL_VALUE FROM TSDDR_PAR_FILE 
            WHERE   FILE= 'Limiti_Amministrativi.txt'                                                                                                                    
              AND   NOME_CAMPO ='D_STOP') 
     AND  SUBSTR(RECORD,GetPosDA('Limiti_Amministrativi.txt','R_STATUS'),GetPosL('Limiti_Amministrativi.txt','R_STATUS')) = '1'            
     ORDER  BY ISTAT_REGIONE,
               D_START
  LOOP
     
	 Begin

-- vflag gestisce la rottura di chiave per inserire/aggiornare 
-- un solo record con d_start più vecchia
If vflag <> rec.ISTAT_REGIONE then		 
	  
     SELECT Count(*)
      INTO   nCount	  
	  FROM   TSDDR_D_REGIONI  
	  WHERE  ID_ISTAT_REGIONE = TRIM(rec.ISTAT_REGIONE);
	  
	  SELECT dn.ID_NAZIONE
	  INTO   vID_NAZIONE
	  FROM   TSDDR_D_NAZIONI dn
      WHERE  dn.desc_nazione = TRIM(rec.DESC_STATO);
	  
	  If nCount > 0 Then
	  
		  Update TSDDR_D_REGIONI  
		  SET    DESC_REGIONE = trim(rec.DESC_REGIONE),
				 DATA_INIZIO_VALIDITA = to_date(rec.D_START,'dd/mm/yyyy'),
				 DATA_FINE_VALIDITA = Null,
				 ID_NAZIONE = vID_NAZIONE,
				 DATA_UPDATE = current_timestamp 
		  WHERE  ID_ISTAT_REGIONE = TRIM(rec.ISTAT_REGIONE);
		  
		  REG_AGG  := REG_AGG  + 1;
	  
	  Else
	      
		  Insert into TSDDR_D_REGIONI
		  ( DESC_REGIONE,             
			ID_ISTAT_REGIONE,      
			DATA_INIZIO_VALIDITA, 
			DATA_FINE_VALIDITA,  
			ID_NAZIONE,
			DATA_INSERT
		  ) VALUES
		  (	trim(rec.DESC_REGIONE), 
			Trim(rec.ISTAT_REGIONE),
			to_date(rec.D_START,'dd/mm/yyyy'), 
			null,
			vID_NAZIONE,
			CURRENT_TIMESTAMP
		  );
		  
		  REG_INS  := REG_INS+1;
	  
	  End If;
	 
 end if;
	 Exception When Others THEN
	  GET STACKED DIAGNOSTICS
                v_state   := RETURNED_SQLSTATE,
                v_msg     := MESSAGE_TEXT,
                v_detail  := PG_EXCEPTION_DETAIL;
	-- scrivi file log
	  r_Log:= utl_file.put_line(f_Log,'<<Mancato aggiornamento/inserimento Regioni: ISTAT_REGIONE '||rec.istat_regione||'DESC_REGIONE '||rec.DESC_REGIONE||
	 ' Codice errore '||v_state||' messaggio errore '||v_msg|| ' DETTAGLIO '||v_detail||'>>');
	  RAISE NOTICE 'exception: % %  ', sqlstate ,  sqlerrm ;
      nRet := 1;
	 End;

vflag:= rec.ISTAT_REGIONE;
	 
END LOOP; 
-- Statistiche REGIONI
	r_Stat:= utl_file.put_line(f_Stat,'N record Regioni aggiornati: '||REG_AGG );
	r_Stat:= utl_file.put_line(f_Stat,'N record Regioni inseriti: '||REG_INS );


-- PROVINCE
PRO_AGG  := 0;
PRO_INS  := 0;
-- 
vflag :='000';
--

-- da impostare la selezione del min(rec.D_START)
FOR Rec IN  
   SELECT SUBSTR(RECORD,GetPosDA('Limiti_Amministrativi.txt','ISTAT_PROVINCIA'),GetPosL('Limiti_Amministrativi.txt','ISTAT_PROVINCIA')) ISTAT_PROVINCIA,
          SUBSTR(RECORD,GetPosDA('Limiti_Amministrativi.txt','D_STOP'),GetPosL('Limiti_Amministrativi.txt','D_STOP')) D_STOP,
          SUBSTR(RECORD,GetPosDA('Limiti_Amministrativi.txt','DESC_PROVINCIA'),GetPosL('Limiti_Amministrativi.txt','DESC_PROVINCIA')) DESC_PROVINCIA,
          SUBSTR(RECORD,GetPosDA('Limiti_Amministrativi.txt','SIGLA_PROV'),GetPosL('Limiti_Amministrativi.txt','SIGLA_PROV')) SIGLA_PROV,          
		  SUBSTR(RECORD,GetPosDA('Limiti_Amministrativi.txt','D_START'),GetPosL('Limiti_Amministrativi.txt','D_START')) D_START,
          SUBSTR(RECORD,GetPosDA('Limiti_Amministrativi.txt','ISTAT_REGIONE'),GetPosL('Limiti_Amministrativi.txt','ISTAT_REGIONE')) ISTAT_REGIONE
    FROM  TSDDR_LIMITI_AMMINISTRATIVI
   WHERE  SUBSTR(RECORD,GetPosDA('Limiti_Amministrativi.txt','D_STOP'),GetPosL('Limiti_Amministrativi.txt','D_STOP')) = 
           (SELECT NULL_VALUE FROM TSDDR_PAR_FILE 
            WHERE   FILE= 'Limiti_Amministrativi.txt'                                                                                                                    
              AND   NOME_CAMPO ='D_STOP') 
     AND  SUBSTR(RECORD,GetPosDA('Limiti_Amministrativi.txt','R_STATUS'),GetPosL('Limiti_Amministrativi.txt','R_STATUS')) = '1'           
     ORDER  BY ISTAT_PROVINCIA,
               D_START
  LOOP
     
	 begin
    
-- vflag gestisce la rottura di chiave per inserire/aggiornare 
-- un solo record con d_start più vecchia
If vflag <> rec.ISTAT_PROVINCIA then	
	  
	  SELECT Count(*)
      INTO   nCount	  
	  FROM   TSDDR_D_PROVINCE   
	  WHERE  ID_PROVINCIA_ISTAT = TRIM(rec.ISTAT_PROVINCIA);
	  
	 
	  SELECT ID_REGIONE
		 INTO   vID_REGIONE
		 FROM   TSDDR_D_REGIONI dr
		 where  dr.ID_ISTAT_REGIONE  = TRIM(rec.ISTAT_REGIONE);
	  
	  If nCount > 0 Then
	  
		  Update TSDDR_D_PROVINCE  
		  SET    DESC_PROVINCIA	= TRIM(REC.DESC_PROVINCIA), 
				 SIGLA_PROV = TRIM(REC.SIGLA_PROV),
				 DATA_INIZIO_VALIDITA = to_date (REC.D_START,'dd/mm/yyyy'), 
				 DATA_FINE_VALIDITA	= null,
				 ID_REGIONE = vID_REGIONE,
				 data_update = current_timestamp 
		  WHERE  id_provincia_istat = TRIM(rec.ISTAT_PROVINCIA);
		  
		  PRO_AGG  := PRO_AGG  + 1;
	  
	  Else
	      
		  Insert into TSDDR_D_PROVINCE
		  ( DESC_PROVINCIA, 
			SIGLA_PROV,
			ID_PROVINCIA_ISTAT,
			DATA_INIZIO_VALIDITA, 
			DATA_FINE_VALIDITA,
			ID_REGIONE,
			DATA_INSERT
		  )VALUES
		  ( TRIM(REC.DESC_PROVINCIA), 
			TRIM(REC.SIGLA_PROV),
			TRIM(REC.ISTAT_PROVINCIA),
			to_date (REC.D_START,'dd/mm/yyyy'), 
			null,
			vID_REGIONE,
			CURRENT_TIMESTAMP
		  );
		 
		  PRO_INS  := REG_INS+1;
	  
	  End If;
	 
 End if;
	 
	 Exception When Others THEN
	 GET STACKED DIAGNOSTICS
                v_state   := RETURNED_SQLSTATE,
                v_msg     := MESSAGE_TEXT,
                v_detail  := PG_EXCEPTION_DETAIL;
	-- scrivi file log
	  r_Log:= utl_file.put_line(f_Log,'<<Mancato aggiornamento/inserimento Provincia: ISTAT_PROVINCIA '||rec.istat_provincia||'DESC_PROVINCIA '||rec.DESC_PROVINCIA||
	 ' Codice errore '||v_state||' messaggio errore '||v_msg|| ' DETTAGLIO '||v_detail||'>>');
	  RAISE NOTICE 'exception: % %  ', sqlstate ,  sqlerrm ;
      nRet := 1;
	 End;
	
vflag:= rec.ISTAT_PROVINCIA;

END LOOP; 

-- Statistiche PROVINCE
   r_Stat:= utl_file.put_line(f_Stat,'N record Province aggiornati: '||PRO_AGG);
   r_Stat:= utl_file.put_line(f_Stat,'N record Province inseriti: '||PRO_INS);


---- COMUNI
COM_AGG  := 0;
COM_INS  := 0;
-- 
-- da impostare la selezione del min(rec.D_START)
FOR Rec IN
   SELECT SUBSTR(RECORD,GetPosDa('Limiti_Amministrativi.txt','ISTAT_COMUNE'),GetPosL('Limiti_Amministrativi.txt','ISTAT_COMUNE')) ISTAT_COMUNE,
          SUBSTR(RECORD,GetPosDa('Limiti_Amministrativi.txt','D_STOP'),GetPosL('Limiti_Amministrativi.txt','D_STOP')) D_STOP,
          SUBSTR(RECORD,GetPosDa('Limiti_Amministrativi.txt','DESC_COMUNE'),GetPosL('Limiti_Amministrativi.txt','DESC_COMUNE')) DESC_COMUNE,
          SUBSTR(RECORD,GetPosDa('Limiti_Amministrativi.txt','CAP'),GetPosL('Limiti_Amministrativi.txt','CAP')) CAP,        
		  SUBSTR(RECORD,GetPosDa('Limiti_Amministrativi.txt','COD_CATASTO'),GetPosL('Limiti_Amministrativi.txt','COD_CATASTO')) CODICE_CATASTO,
		  SUBSTR(RECORD,GetPosDa('Limiti_Amministrativi.txt','D_START'),GetPosL('Limiti_Amministrativi.txt','D_START')) D_START,
          SUBSTR(RECORD,GetPosDa('Limiti_Amministrativi.txt','ISTAT_PROVINCIA'),GetPosL('Limiti_Amministrativi.txt','ISTAT_PROVINCIA')) ISTAT_PROVINCIA   
   FROM   TSDDR_LIMITI_AMMINISTRATIVI
   WHERE  SUBSTR(RECORD,GetPosDA('Limiti_Amministrativi.txt','D_STOP'),GetPosL('Limiti_Amministrativi.txt','D_STOP')) = 
           (SELECT NULL_VALUE FROM TSDDR_PAR_FILE 
            WHERE   FILE= 'Limiti_Amministrativi.txt'                                                                                                                    
              AND   NOME_CAMPO ='D_STOP') 
     AND  SUBSTR(RECORD,GetPosDA('Limiti_Amministrativi.txt','R_STATUS'),GetPosL('Limiti_Amministrativi.txt','R_STATUS')) = '1'           
     ORDER  BY ISTAT_COMUNE,
               D_START
  
  LOOP
     
	 Begin
	  
	  SELECT Count(*)
      INTO   nCount	  
	  FROM   TSDDR_D_COMUNI    
	  WHERE  ID_COMUNE_ISTAT= TRIM(rec.ISTAT_COMUNE); 
	  
	  SELECT    ID_PROVINCIA
		 INTO   vID_PROVINCIA
		 FROM   tsddr_d_province dp
		 where  dp.id_provincia_istat = TRIM(rec.ISTAT_PROVINCIA);
	  
	  If nCount > 0 Then
	  
		  Update TSDDR_D_COMUNI  
		  SET    COMUNE = TRIM(rec.DESC_COMUNE), 
					CAP	= TRIM(rec.CAP),
					COD_CATASTO	= TRIM(rec.CODICE_CATASTO),					
					DATA_INIZIO_VALIDITA = to_date(rec.D_START,'dd/mm/yyyy'),
					DATA_FINE_VALIDITA		= null,	
					ID_PROVINCIA = vID_PROVINCIA,
					DATA_UPDATE = current_timestamp 
		  WHERE  ID_COMUNE_ISTAT = TRIM(rec.ISTAT_comune);
		  
		  COM_AGG  := COM_AGG  + 1;
	  
	  Else
	      
		  Insert into TSDDR_D_COMUNI
		  (	COMUNE	, 
			CAP	,
			COD_CATASTO	,
			ID_COMUNE_ISTAT	,
			DATA_INIZIO_VALIDITA,
			DATA_FINE_VALIDITA,
			ID_PROVINCIA,
			DATA_INSERT
		  )VALUES
		  (	trim(REC.DESC_COMUNE), 
			trim(REC.CAP),
			trim(REC.CODICE_CATASTO),
			trim(REC.ISTAT_COMUNE),
			TO_DATE(REC.D_START,'dd/mm/yyyy') ,
			null,
			vID_PROVINCIA,
			current_timestamp
		  );
		  
		  COM_INS  := COM_INS+1;
	  
	  End If;	 
	 Exception When Others THEN
	 GET STACKED DIAGNOSTICS
                v_state   := RETURNED_SQLSTATE,
                v_msg     := MESSAGE_TEXT,
                v_detail  := PG_EXCEPTION_DETAIL;
	-- scrivi file log
	  r_Log:= utl_file.put_line(f_Log,'<<Mancato aggiornamento/inserimento Comuni: ISTAT_COMUNE '||rec.istat_comune||'DESC_COMUNE '||rec.DESC_COMUNE||
	 ' Codice errore '||v_state||' messaggio errore '||v_msg|| ' DETTAGLIO '||v_detail||'>>');
	  RAISE NOTICE 'exception: % %  ', sqlstate ,  sqlerrm ;
      nRet := 1; 
	 End;
	 
END LOOP;


-- Statistiche Comuni
    r_Stat:= utl_file.put_line(f_Stat,'N record Comuni aggiornati: '|| COM_AGG);
	r_Stat:= utl_file.put_line(f_Stat,'N record Comuni inseriti: '|| COM_INS);

-- Fine Elaborazione
-- Chiusura statistiche 
   r_Stat:= utl_file.put_line(f_Stat,'<<Fine processo Acquisizione Limiti Amministrativi'||current_timestamp||'>>');

-- chiusura dei file
r_Stat:= utl_file.fclose(f_Stat);
r_Log:=  utl_file.fclose(f_Log); 
------------

  RETURN nRet;

Exception When Others Then
 GET STACKED DIAGNOSTICS
                v_state   := RETURNED_SQLSTATE,
                v_msg     := MESSAGE_TEXT,
                v_detail  := PG_EXCEPTION_DETAIL;
 r_Log:= utl_file.put_line(f_Log,'Codice errore '||v_state||' messaggio errore '||v_msg|| 
' DETTAGLIO '||v_detail);
 RAISE NOTICE 'exception: % %  ', sqlstate ,  sqlerrm ;
 nRet := 99;
 Return nRet;
END;

$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

ALTER FUNCTION AggiornaDatiTrasversali()
OWNER TO tsddr;

GRANT EXECUTE ON FUNCTION AggiornaDatiTrasversali() TO tsddr_rw;