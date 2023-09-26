# Prodotto
TSDDR componenti TSDDRWCL e TSDDRBL

# Descrizione della componente
Questa cartella rappresenta una "unità di installazione" che racchiude in sè due distinte componenti "logiche", ovvero TSDDRWCL e TSDDRBL.

TSDDRBL
 è una web application che segue il paradigma "Single Page Application (SPA)", espone servizi REST alla componente TSDDRWCL (Angular 11) e si connette al DB (TSDDRDB) per le operazioni CRUD.
Si collega al servizio di autenticazione (SHIBBOLETH nel contesto di Regione Piemonte) e ad altri servizi del Sistema Informativo Regionale Ficalità (DOQUI-ACTA).
I servizi esterni sono fruiti tramite protocollo HTTP (protetto tramite uno schema di "basic authentication") o HTTPS.
Questa componente si connette al DB (DBMS PostgreSQL, vedi componente tsddrdb) utilizzando il DataSource definito a livello del container (WildFly).


# Configurazioni iniziali
Da un punto di vista generale, nella fase iniziale occorre adattare i file di properties nella directory buildfiles alla propria configurazione.
Una delle cose principali da configurare è il datasource con i riferimenti del DB che si intende utilizzare (JNDI name).\

# Getting Started
Una volta prelevata e portata in locale dal repository la componente ("git clone"), procedere con la modifica dei file di configurazione in base al proprio ambiente di deploy e quindi procedere al build.

# Prerequisiti di sistema
Occorre per prima cosa predisporre il DB Schema utilizzato da questa componente, e popolarlo con i dati iniziali: si deve quindi prima aver completato installazione e configurazione della componente comonldb.

Occorre inoltre prevedere le opportune sostituzioni dei servizi esterni richiamati.

Per il "build" si è previsto di utilizzare Apache Ant. Prima di procedere al build della componente TSDDRBL, occorre però eseguire i passi necessari definiti nel file README.md della componente TSDDRWCL.
Questo perchè le due componenti logiche saranno racchiuse in un'unica unità di installazione. Il processo di build di TSDDRBL prevede quindi di prelevare il generato della compilazione di TSDDRWCL e di includerlo nel path di progetto della componente TSDDRBL (src/web/tsddr/rest/).
Svolta l'operazione di copia si potrà procedere al build (esempio: ant -Dtarget=dev-rp-01 -lib apache-ivy-2.0.0.jar).
L'unità di installazione risultante dalla build di TSDDRBL sarà un file tsddrbl-MAJOR.MINOR.PATCH.tar, contenente un file tsddrbl.ear che a sua volta sarà così strutturato:

-lib
-META-INF
-appl-tsddrbl-rest.war

# Installazione - Deployment

Installare il file "ear" generato con il build sul proprio ambiente WildFly.

# Esecuzione dei test

Questa componente è stata sottoposta a vulnerability assessment.

# Versioning

Per il versionamento del software si usa la tecnica Semantic Versioning (http://semver.org).

# Copyrights

© Copyright Regione Piemonte – 2022\
© Copyright CSI-Piemonte – 2022\

Questo stesso elenco dei titolari del software è anche riportato in Copyrights.txt .

# License
Il prodotto software è sottoposto alla licenza EUPL-1.2 o versioni successive.
SPDX-License-Identifier: EUPL-1.2-or-later

