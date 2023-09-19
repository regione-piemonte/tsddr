# Prodotto

TRISPE - TRIbuto SPEciale Deposito in Discarica dei Rifiuti

# Descrizione del prodotto
Il sistema TRISPE è composto da due applicativi.
     
TRISPE-FO per gli operatori delle società che gestiscono impianti di trattamento e conferimento dei rifiuti e sono tenute ad assolvere agli adempimenti previsti dalla normativa vigente. Comprende le seguenti funzionalità:
     
- Accreditamento consente di presentare la richiesta di accesso al sistema.
     
- Dichiarazione annuale permette di presentare la dichiarazione annuale sui quantitativi conferiti nell’anno e sui versamenti effettuati. L'applicativo permette di imputare i dati, inviare la dichiarazione con contestuale generazione del PDF e protocollazione. E’ a disposizione, anche, la funzionalità di ricerca delle dichiarazioni in lavorazione e inviate.

- Dichiarazioni con obiettivi MR permette di presentare la dichiarazione annuale di effettivo raggiungimento delle percentuali di recupero o di non superamento delle percentuali di scarto. L'applicativo permette di imputare i dati, inviare la dichiarazione 
  con contestuale generazione del PDF e protocollazione. Per la compilazione è possibile utilizzare i dati di una richiesta di ammissione al pagamento in misura ridotta presentata con TRISPE.  E’ a disposizione, anche, la funzionalità di ricerca delle dichiarazioni in lavorazione e inviate.

- Richieste ammissione MR permette di presentare la domanda per il pagamento in misura ridotta del tributo. L'applicativo permette di imputare i dati, inviare la richiesta con contestuale generazione del PDF e protocollazione. E’ a disposizione, anche, la funzionalità di ricerca delle domande in lavorazione e inviate.
     
TRISPE-BO per gli utenti della P.A. addetti al controllo. Comprende le seguenti funzionalità:
     
- Gestione accreditamento permette la consultazione e lavorazione delle domande di accreditamento presentate dagli operatori delle aziende per poter usufruire delle funzionalità rivolte alle imprese.
- Gestione dichiarazione annuale consente la consultazione delle dichiarazioni presentate.
- Gestione dichiarazione MR consente la consultazione delle dichiarazioni raggiungimento degli obiettivi presentate.
- Gestione richieste di ammissione a pagamento in misura ridotta consente la consultazione delle domande presentate per il pagamento ridotto del tributo.


Il prodotto è strutturato nelle seguenti componenti specifiche:
- [tsddrdb]( https://github.com/regione-piemonte/tsddr/tree/main/tsddrdb ) : script per la creazione del DB (istanza PostgreSQL);
- [tsddrwcl]( https://github.com/regione-piemonte/tsddr/tree/main/tsddrwcl ) : Client Web (Angular11), front-end applicativo;
- [tsddrbl]( https://github.com/regione-piemonte/tsddr/tree/main/tsddrbl ) : Componente di esposizione servizi (REST API) verso il proprio front-end tsddrwcl.			;

La componente tsddrwcl è "logicamente" distinta da tsddrbl, ma inclusa nella stessa unità di installazione (file "ear").

A ciascuna componente del prodotto elencata sopra corrisponde una sotto-directory denominata tsddr-<nome_componente>.\
In ciascuna di queste cartelle di componente si trovano ulteriori informazioni specifiche, incluso il BOM della componente di prodotto.


# Prerequisiti di sistema

Una istanza PostgreSQL (consigliata la verione 12) con utenza avente privilegi per la creazione tabelle ed altri oggetti DB (tramite le istruzioni DDL messe a disposizione nella componente comonldb), ed una ulteriore utenza separata non proprietaria dello schama, per l'esecuzione di istruzioni DML di Create, Readd, Update e Delete sui dati.

Una istanza di application server J2EE, consigliato WildFly 23 ( https://www.wildfly.org/downloads/ ).\
Una istanza di web server, consigliato apache web server ( https://httpd.apache.org/ ).\
Per il build è previsto l'uso di Apache Ant Ivy ( https://ant.apache.org/ivy/ ).\

Il prodotto TSDDR è integrato nei servizi del sistema informativo di Regione Piemonte "Fiscalità": alcune sue funzionalità sono quindi strettamente legate alla possibilità di accedere a servizi esposti da altre componenti dell'ecosistema "Fiscalità" di Regione Piemonte.

Infine, anche per quanto concerne l'autenticazione e la profilazione degli utenti del sistema, TSDDR è integrato con servizi trasversali del sistema informativo regionale ("Shibboleth"), di conseguenza per un utilizzo in un altro contesto occorre avere a disposizione servizi analoghi o integrare moduli opportuni che svolgano analoghe funzionalità.
 

# Installazione

Creare lo schema del DB, tramite gli script della componente tsddrdb.
 
Configurare il datasource nell'application server, utilizzato in tsddrbl.

Configurare i web server e definire gli opportuni Virtual Host e "location" - per utilizzare il protocollo https occorre munirsi di adeguati certificati SSL.

Nel caso si vogliano sfruttare le funzionalità di invio mail, occorre anche configurare un mail-server.


# Deployment

Dopo aver seguito le indicazioni del paragrafo relativo all'installazione, si può procedere al build dei pacchetti ed al deploy su application server (WildFly).


# Versioning
Per la gestione del codice sorgente viene utilizzato Git, ma non vi sono vincoli per l'utilizzo di altri strumenti analoghi.\
Per il versionamento del software si usa la tecnica Semantic Versioning (http://semver.org).


# Copyrights
© Copyright Regione Piemonte – 2022\
© Copyright CSI-Piemonte – 2022


# License

SPDX-License-Identifier: EUPL-1.2-or-later .\
Questo software è distribuito con licenza EUPL-1.2 .\
Consultare il file LICENSE.txt per i dettagli sulla licenza.
