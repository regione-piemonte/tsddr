import { ApiConfig } from '@eng-ds/api-client';

/*
  Qui sono configurate tutte le url del BE
  la chiave name deve essere unica per funzionare correttamente
*/
export const backendApi: ApiConfig[] = [
  {
    name: 'idProfilo',
    method: 'GET',
    url: '/login/id-profilo'
  },
  {
    name: 'isPercRecuperoVisible',
    method: 'GET',
    url: '/prev-cons/isPercRecuperoVisible'
  },
  {
    name: 'isPercScartoVisible',
    method: 'GET',
    url: '/prev-cons/isPercScartoVisible'
  },
  {
    name: 'strutturaMenueCard',
    method: 'GET',
    url: '/login/struttura-menue-card'
  },
  {
    name: 'comboProfilo',
    method: 'GET',
    url: '/login/combo-profilo'
  },
  {
    name: 'logCambioProfilo',
    method: 'POST',
    url: '/login/log-cambio-profilo'
  },
  {
    name: 'getAreaPersonaleACL',
    method: 'GET',
    url: '/area-personale/acl'
  },
  {
    name: 'utenteCorrente',
    method: 'GET',
    url: '/area-personale/utente-corrente'
  },
  {
    name: 'verificaDatiObbligatori',
    method: 'POST',
    url: '/area-personale/verifica-dati-obbligatori'
  },
  {
    name: 'salvaUtenteCorrente',
    method: 'PUT',
    url: '/area-personale/utente-corrente'
  },
  {
    name: 'getMessage',
    method: 'GET',
    url: '/messaggi/{codMsg}'
  },
  {
    name: 'getNotaInfo',
    method: 'GET',
    url: '/note-info/{codNotaInfo}'
  },
  {
    name: 'logLogout',
    method: 'POST',
    url: '/area-personale/logout'
  },
  {
    name: 'getFunzionalitaProfili',
    method: 'GET',
    url: '/funzionalita-profilo'
  },
  {
    name: 'getFunzionalitaProfiliACL',
    method: 'GET',
    url: '/funzionalita-profilo/acl'
  },
  {
    name: 'getComboProfili',
    method: 'GET',
    url: '/profili/combo'
  },
  {
    name: 'getComboAllProfili',
    method: 'GET',
    url: '/profili/combo-all'
  },
  {
    name: 'getComboFunzionalita',
    method: 'GET',
    url: '/funzionalita-profilo/combo'
  },
  {
    name: 'getComboNuovaFunzionalita',
    method: 'GET',
    url: '/funzionalita-profilo/combo-nuova-funzionalita'
  },
  {
    name: 'updateFunzionalita',
    method: 'PUT',
    url: '/funzionalita-profilo'
  },
  {
    name: 'createFunzionalita',
    method: 'POST',
    url: '/funzionalita-profilo'
  },
  {
    name: 'deleteFunzionalita',
    method: 'DELETE',
    url: '/funzionalita-profilo'
  },
  {
    name: 'getUtentiACL',
    method: 'GET',
    url: '/utenti/acl'
  },
  {
    name: 'getComboGestori',
    method: 'GET',
    url: '/gestori/combo'
  },
  {
    name: 'validateSearchUtenti',
    method: 'POST',
    url: '/utenti/verifica-parametri-ricerca'
  },
  {
    name: 'validateCF',
    method: 'GET',
    url: '/validazione/verifica-formato-cf'
  },
  {
    name: 'getUtenti',
    method: 'POST',
    url: '/utenti/griglia-utenti'
  },
  {
    name: 'getParametriFiltro',
    method: 'POST',
    url: '/utenti/parametri-filtro-applicati'
  },
  {
    name: 'validateInsert',
    method: 'POST',
    url: '/utenti/verifica-dati-obbligatori'
  },
  {
    name: 'emailValidator',
    method: 'GET',
    url: '/validazione/verifica-formato-email'
  },
  {
    name: 'validateUser',
    method: 'GET',
    url: '/utenti/verifica-utente'
  },
  {
    name: 'createUtente',
    method: 'POST',
    url: '/utenti'
  },
  {
    name: 'editUtente',
    method: 'PUT',
    url: '/utenti'
  },
  {
    name: 'deleteUtente',
    method: 'DELETE',
    url: '/utenti'
  },
  {
    name: 'getComboProfiliUtente',
    method: 'GET',
    url: '/utenti/combo-profili'
  },
  {
    name: 'getComboGestoriUtente',
    method: 'GET',
    url: '/utenti/combo-nuovo-gestore'
  },
  {
    name: 'getGestoriUtente',
    method: 'GET',
    url: '/utenti/dati-utente/gestori'
  },
  {
    name: 'getUtente',
    method: 'GET',
    url: '/utenti/dati-utente'
  },
  {
    name: 'getProfiliACL',
    method: 'GET',
    url: '/profili/acl'
  },
  {
    name: 'getProfili',
    method: 'GET',
    url: '/profili'
  },
  {
    name: 'verifyProfilo',
    method: 'POST',
    url: '/profili/verifica'
  },
  {
    name: 'updateProfilo',
    method: 'PUT',
    url: '/profili'
  },
  {
    name: 'createProfilo',
    method: 'POST',
    url: '/profili'
  },
  {
    name: 'deleteProfilo',
    method: 'DELETE',
    url: '/profili'
  },
  {
    name: 'getComboTipiProfilo',
    method: 'GET',
    url: '/tipi-profilo/combo'
  },
  {
    name: 'updateLinkUtenteProfiloGestore',
    method: 'PUT',
    url: '/utenti/utente-gestore-profilo'
  },
  {
    name: 'deleteLinkUtenteProfiloGestore',
    method: 'DELETE',
    url: '/utenti/utente-gestore-profilo'
  },
  {
    name: 'createLinkUtenteProfiloGestore',
    method: 'POST',
    url: '/utenti/utente-gestore-profilo'
  },
  {
    name: 'getUtentiProfilo',
    method: 'GET',
    url: '/utenti-profilo'
  },
  {
    name: 'getUtentiProfiloACL',
    method: 'GET',
    url: '/funzionalita-profilo/acl'
  },
  {
    name: 'getComboUtentiProfiloNuovoUtente',
    method: 'GET',
    url: '/utenti-profilo/combo-nuovo-utente'
  },
  {
    name: 'associaUtenteProfilo',
    method: 'POST',
    url: '/utenti-profilo'
  },
  {
    name: 'rimuoviUtenteProfilo',
    method: 'DELETE',
    url: '/utenti-profilo'
  },
  {
    name: 'getDomandeAccreditamento',
    method: 'GET',
    url: '/accreditamento'
  },
  {
    name: 'getAccreditamentoACL',
    method: 'GET',
    url: '/accreditamento/acl'
  },
  {
    name: 'getDomandeAccreditamentoParametri',
    method: 'GET',
    url: '/accreditamento/parametri-filtro-applicati'
  },
  {
    name: 'getDomandaAccreditamento',
    method: 'GET',
    url: '/accreditamento/{idDomanda}'
  },
  {
    name: 'createDomandaAccreditamento',
    method: 'POST',
    url: '/accreditamento'
  },
  {
    name: 'editDomandaAccreditamento',
    method: 'PUT',
    url: '/accreditamento/{id}'
  },
  {
    name: 'deleteDomandaAccreditamento',
    method: 'DELETE',
    url: '/accreditamento/{idDomanda}'
  },
  {
    name: 'getComboGestoriAccreditamento',
    method: 'GET',
    url: '/accreditamento/gestori/combo'
  },
  {
    name: 'getComboProfiliAccreditamento',
    method: 'GET',
    url: '/accreditamento/{idDomanda}/profili/combo'
  },
  {
    name: 'getComboRichiedentiAccreditamento',
    method: 'GET',
    url: '/accreditamento/richiedenti/combo'
  },
  {
    name: 'getComboStatiAccreditamento',
    method: 'GET',
    url: '/accreditamento/stati/combo'
  },
  {
    name: 'getComboAllStatiAccreditamento',
    method: 'GET',
    url: '/accreditamento/stati/combo-all'
  },
  {
    name: 'getGestoriACL',
    method: 'GET',
    url: '/gestori/acl'
  },
  {
    name: 'getComboNaturaGiuridica',
    method: 'GET',
    url: '/nature-giuridiche/combo'
  },
  {
    name: 'getComboProvincia',
    method: 'GET',
    url: '/province/combo'
  },
  {
    name: 'getComboComune',
    method: 'GET',
    url: '/comuni/combo'
  },
  {
    name: 'getComboSedime',
    method: 'GET',
    url: '/sedimi/combo'
  },
  {
    name: 'getComboNazioni',
    method: 'GET',
    url: '/nazioni/combo'
  },
  {
    name: 'getGestori',
    method: 'GET',
    url: '/gestori'
  },
  {
    name: 'getGestore',
    method: 'GET',
    url: '/gestori/{id}'
  },
  {
    name: 'createGestore',
    method: 'POST',
    url: '/gestori'
  },
  {
    name: 'existGestore',
    method: 'GET',
    url: '/gestori/exists/{cf}'
  },
  {
    name: 'getRappresentanteLegale',
    method: 'GET',
    url: '/gestori/rappresentante-legale/{cf}'
  },
  {
    name: 'getParametriFiltroGestori',
    method: 'GET',
    url: '/gestori/parametri-filtro-applicati'
  },
  {
    name: 'hasDomande',
    method: 'GET',
    url: '/gestori/{id}/accreditamento/exists'
  },
  {
    name: 'hasImpianti',
    method: 'GET',
    url: '/gestori/{id}/impianti/exists'
  },
  {
    name: 'editGestore',
    method: 'PUT',
    url: '/gestori/{id}'
  },
  {
    name: 'deleteGestore',
    method: 'DELETE',
    url: '/gestori/{id}'
  },
  {
    name: 'validateSearchGestori',
    method: 'POST',
    url: '/utenti/verifica-parametri-ricerca'
  },
  {
    name: 'getImpianti',
    method: 'GET',
    url: '/impianti'
  },
  {
    name: 'getImpiantiACL',
    method: 'GET',
    url: '/impianti/acl'
  },
  {
    name: 'getImpiantiParametri',
    method: 'GET',
    url: '/impianti/parametri-filtro-applicati'
  },
  {
    name: 'getImpianto',
    method: 'GET',
    url: '/impianti/{idImpianto}'
  },
  {
    name: 'createImpianto',
    method: 'POST',
    url: '/impianti'
  },
  {
    name: 'editImpianto',
    method: 'PUT',
    url: '/impianti/{idImpianto}'
  },
  {
    name: 'deleteImpianto',
    method: 'DELETE',
    url: '/impianti/{idImpianto}'
  },
  {
    name: 'getComboImpiantiAtti',
    method: 'GET',
    url: '/impianti/atti/combo'
  },
  {
    name: 'getComboImpiantiLinee',
    method: 'GET',
    url: '/impianti/{idImpianto}/linee/combo'
  },
  {
    name: 'getComboLinee',
    method: 'GET',
    url: '/impianti/linee/combo'
  },
  {
    name: 'getComboImpiantiStati',
    method: 'GET',
    url: '/impianti/stati/combo'
  },
  {
    name: 'getComboImpiantiTipi',
    method: 'GET',
    url: '/impianti/tipi/combo'
  },
  {
    name: 'getImpiantoAtti',
    method: 'GET',
    url: '/impianti/{idImpianto}/atti'
  },
  {
    name: 'createImpiantoAtto',
    method: 'POST',
    url: '/impianti/{idImpianto}/atti'
  },
  {
    name: 'editImpiantoAtto',
    method: 'PUT',
    url: '/impianti/{idImpianto}/atti/{idAtto}'
  },
  {
    name: 'deleteImpiantoAtto',
    method: 'DELETE',
    url: '/impianti/{idImpianto}/atti/{idAtto}'
  },
  {
    name: 'getImpiantoLinee',
    method: 'GET',
    url: '/impianti/{idImpianto}/linee'
  },
  {
    name: 'createImpiantoLinea',
    method: 'POST',
    url: '/impianti/{idImpianto}/linee/{idLinea}'
  },
  {
    name: 'editImpiantoLinea',
    method: 'PUT',
    url: '/impianti/{idImpianto}/linee/{idLinea}'
  },
  {
    name: 'deleteImpiantoLinea',
    method: 'DELETE',
    url: '/impianti/{idImpianto}/linee/{idLinea}'
  },
  {
    name: 'createImpiantoSottolinea',
    method: 'POST',
    url: '/impianti/{idImpianto}/sottolinee/{idLinea}'
  },
  {
    name: 'editImpiantoSottolinea',
    method: 'PUT',
    url: '/impianti/{idImpianto}/sottolinee/{idLinea}'
  },
  {
    name: 'deleteImpiantoSottolinea',
    method: 'DELETE',
    url: '/impianti/{idImpianto}/sottolinee/{idLinea}'
  },
  {
    name: 'getImpiantiLinee',
    method: 'GET',
    url:'/impianti-linee'
  },
  {
    name: 'getComboProvince',
    method: 'GET',
    url: '/province/combo'
  },
  {
    name: 'getComboComuni',
    method: 'GET',
    url: '/comuni/combo'
  },
  {
    name: 'getComboSedimi',
    method: 'GET',
    url: '/sedimi/combo'
  },
  {
    name: 'getDichiarazioniACL',
    method: 'GET',
    url: '/dichiarazioni/acl'
  },
  {
    name: 'getComboDichiarazioniAnni',
    method: 'GET',
    url: '/dichiarazioni/anni/combo'
  },
  {
    name: 'existDichiarazione',
    method: 'GET',
    url: '/dichiarazioni/exists'
  },
  {
    name: 'allowDuplicaDichiarazione',
    method: 'GET',
    url: '/dichiarazioni/allow-duplica'
  },
  {
    name: 'getDichiarazioni',
    method: 'GET',
    url: '/dichiarazioni'
  },
  {
    name: 'getDichiarazioniSingleResult',
    method: 'GET',
    url: '/dichiarazioni/single-result'
  },
  {
    name: 'getDichiarazioniParametri',
    method: 'GET',
    url: '/dichiarazioni/parametri-filtro-applicati'
  },
  {
    name: 'deleteDichiarazioneSoggetto',
    method: 'DELETE',
    url: '/dichiarazioni/{idDichAnnuale}/soggetti-mr/{idSoggettiMr}'
  },
  {
    name: 'deleteRifiutoConferito',
    method: 'DELETE',
    url: '/dichiarazioni/{idDichAnnuale}/conferimenti/{idRifiutoTariffa}'
  },
  {
    name: 'deleteDichiarazione',
    method: 'DELETE',
    url: '/dichiarazioni/{idDichAnnuale}'
  },
  {
    name: 'getDichiarazione',
    method: 'GET',
    url: '/dichiarazioni/{idDichAnnuale}'
  },
  {
    name: 'getDichiarazioneDownload',
    method: 'GET',
    url: '/dichiarazioni/{idDichAnnuale}/download'
  },
  {
    name: 'duplicaDichiarazione',
    method: 'GET',
    url: '/dichiarazioni/{idDichAnnuale}/nuova-versione'
  },
  {
    name: 'insertDichiarazione',
    method: 'POST',
    url: '/dichiarazioni'
  },
  {
    name: 'insertDichiarazioneBozza',
    method: 'POST',
    url: '/dichiarazioni/bozza'
  },
  {
    name: 'getComboRifiutiTariffa',
    method: 'GET',
    url: '/rifiuti-tariffe/combo'
  },
  {
    name: 'getRifiutiTariffa',
    method: 'GET',
    url: '/rifiuti-tariffe'
  },
  {
    name: 'getPeriodi',
    method: 'GET',
    url: '/periodi'
  },
  {
    name: 'getComboDichiarazioneImpianti',
    method: 'GET',
    url: '/dichiarazioni/impianti/combo'
  },
  {
    name: 'getComboDichiarazioneGestori',
    method: 'GET',
    url: '/dichiarazioni/gestore/combo'
  },
  {
    name: 'getProfileBo',
    method: 'GET',
    url: '/profili/is-profilo-BO'
  },
  {
    name: 'getDichiarazioniMr',
    method: 'GET',
    url: '/prev-cons'
  },
  {
    name: 'insertDichiarazioneMr',
    method: 'POST',
    url: '/prev-cons'
  },
  {
    name: 'getPrevConsACL',
    method: 'GET',
    url: '/prev-cons/acl'
  },
  {
    name: 'getComboDichiarazioniMrAnni',
    method: 'GET',
    url: '/prev-cons/anni/combo'
  },
  {
    name: 'insertDichiarazioneMrBozza',
    method: 'POST',
    url: '/prev-cons/bozza'
  },
  {
    name: 'getComboDichiarazioneMrGestori',
    method: 'GET',
    url: '/prev-cons/gestore/combo'
  },
  {
    name: 'getComboDichiarazioneMrImpianti',
    method: 'GET',
    url: '/prev-cons/impianti/combo'
  },
  {
    name: 'getDichiarazioniMrParametri',
    method: 'GET',
    url: '/prev-cons/parametri-filtro-applicati'
  },
  {
    name: 'deleteDichiarazioneMr',
    method: 'DELETE',
    url: '/prev-cons/{idPrevCons}'
  },
  {
    name: 'deleteDichiarazioneMrLineePrevCons',
    method: 'DELETE',
    url: '/prev-cons/linee'
  },
  {
    name: 'deleteDichiarazioneMrPrevConsDett',
    method: 'DELETE',
    url: '/prev-cons-dett/{idPrevConsDett}'
  },
  {
    name: 'getDichiarazioneMrById',
    method: 'GET',
    url: '/prev-cons/{idPrevCons}'
  },
  {
    name: 'getDichiarazioneMrDownload',
    method: 'GET',
    url: '/prev-cons/{idPrevCons}/download'
  },
  {
    name: 'existsPrevCons',
    method: 'GET',
    url: '/prev-cons/exists'
  },
  {
    name: 'getComboEer',
    method: 'GET',
    url: '/eer/combo'
  },
  {
    name: 'getLineeRichiesta',
    method: 'GET',
    url: '/prev-cons/{idPrevCons}/linee-richiesta'
  },
  /////LOTTO 6/////
  {
    name: 'downloadReportAccreditamento',
    method: 'POST',
    url: '/accreditamento/downloadReport'
  },
  {
    name: 'downloadReportDichiarazioni',
    method: 'POST',
    url: '/dichiarazioni/downloadReport'
  },
  {
    name: 'downloadReportGestori',
    method: 'POST',
    url: '/gestori/downloadReport'
  },
  {
    name: 'downloadReportImpianti',
    method: 'POST',
    url: '/impianti/downloadReport'
  },
  {
    name: 'downloadReportPrevCons',
    method: 'POST',
    url: '/prev-cons/downloadReport'
  },
  {
    name: 'checkDeleteImpianto',
    method: 'GET',
    url: '/impianti/checkDelete/{idImpianto}'
  },
];
