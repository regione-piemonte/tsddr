/*
  Traduzione italiana delle stringhe pressente nel sito
*/

export const IT = {
  APP: {
    NAME: 'Tributo Speciale Deposito in Discarica dei Rifiuti',
    NAMESHORT: 'TSDDR',
    MENUOPEN: 'Apri menu',
    MENUCLOSE: 'Chiudi menu',
  },
  COMMONS: {
    SI: 'Si',
    NO: 'No',
    OK: 'OK',
    AZIONI: 'Azioni',
    IMPIANTO: 'Impianto',
    IMPIANTI: 'Impianti',
    GESTORE: 'Gestore',
    GESTORI: 'Gestori',
    DESCRIZIONE: 'Descrizione',
    DESTINAZIONE: 'Destinazione',
    NUM: 'N.',
    TONNELLATE: 'Tonnellate'
  },
  HEADER: {
    LOGIN: 'Accedi',
    DROPDOWN: {
      LOGOUT: 'Esci'
    }
  },
  DIALOG: {
    AREAPERSONALE: {
      TITLE: 'Area personale',
      FORMTOP: {
        FIELDS: {
          PROFILE: {
            LABEL: 'Seleziona il profilo',
            PLACEHOLDER: 'Seleziona il profilo'
          }
        }
      },
      DATA: {
        TITLE: 'Dati personali',
        FIELDS: {
          CODICEFISCALE: { LABEL: 'Codice Fiscale' },
          COGNOME: { LABEL: 'Cognome' },
          NOME: { LABEL: 'Nome' }
        }
      },
      FORMBOTTOM: {
        TITLE: 'Contatti',
        FIELDS: {
          NUMBER: {
            LABEL: 'Numero di Telefono',
            PLACEHOLDER: 'Numero di Telefono'
          },
          EMAIL: {
            LABEL: 'Email',
            PLACEHOLDER: 'Email'
          }
        }
      },
      FOOTER: {
        CONFIRM: 'Salva i dati',
        CANCEL: 'Indietro'
      }
    },
    CONFIRM: {
      TITLE: 'Tssddr',
      FOOTER: {
        CONFIRM: 'Si',
        CANCEL: 'No'
      }
    }
  },
  FOOTER: {
    REGIONE: {
      SERVICE: `Un servizio a cura della regione Piemonte<br>P. Iva 123456789 - CF 1234567489`
    },
    ALTREGIONE:'Regione Piemonte',
    ALTINIZIATIVA:'Iniziativa co-finanziata con FESR',
    ALTFONDOEUROPEO:'Piemonte - fondo europeo sviluppo regionale',
    LINK: {
      ACCESSIBILITY: 'Accessibilità',
      PRIVACY: 'Privacy',
      COOKIE: 'Cookie policy'
    }
  },
  MENU: {
    TITLE: 'Tributo Speciale Deposito in Discarica dei Rifiuti',
    HOME: 'Home',
    AMBIENTE: 'Ambiente',
    FORESTALE: 'Forestale',
    BOOKING: 'Prenotazioni',
    SEARCH_PRACTCE: 'Ricerca Archivio Progetti',
    OBSERVATIONS: 'Consulta Osservazioni'
  },
  NOT_AUTHORIZED: {
    NOTIFICATION: {
      TITLE: 'Utente non autorizzato',
      TEXT: "L'utente loggato non possiede i permessi per visualizzare questa pagina"
    },
    TITLE: '401 Accesso non consentito',
    SUB_TITLE: 'Non si hanno i permessi per accedere alla pagina',
    GO_TO_HOME: 'Portami alla Home'
  },
  NOT_FOUND: {
    TITLE: '404 Pagina non trovata',
    SUB_TITLE: 'La pagina non sembra esistere',
    GO_TO_HOME: 'Portami alla Home'
  },
  UTILS: {
    FILTER: 'Parametri di filtro applicati:',
    EXPORT:'Esporta elenco',
    ENABLED: 'On',
    DISABLED: 'Off',
    BACK: 'Indietro',
    NOTIFICATIONS: {
      CLOSE: 'Chiudi notifica: {{titleNotification}}'
    },
    TABLE: {
      TOTAL_ROWS: 'risultati',
      ORDER_BY:'ordina per ',
      PREV:'Vai alla pagina precedente',
      NEXT:'Vai alla pagina sucessiva',
      PAGE:'Vai alla pagina ',
      NUMBER_OF_ELEMENTS: 'elementi trovati',
      EMPTY: 'Nessuna corrispondenza trovata',
      INFO_PAGE: '{{page}} di {{total}} pagine',
      SIZE_PAGE_SELECT: 'righe per pagina',
      ACTIONS: 'Azioni'
    },
    AUTOCOMPLETE: {
      TYPE_TO_SEARCH: 'Search...',
      ITEMS_NOT_FOUND: 'Items not found',
      INSERT_MIN_CHARACTERS: 'Insert almost one character'
    },
    SELECT: {
      CLEAR_CHOICE: 'Clear'
    },
    FORM: {
      RESET: 'Pulisci',
      FILTER: 'Filtra',
      LABEL_NOT_MANDATORY: '(facoltativo)',
      MANDATORY: '*'
    },
    SEARCH: {
      NOT_EMPTY: {
        TITLE: 'Errore',
        TEXT: 'La ricerca non può essere vuota'
      }
    },
    DIALOG: {
      TEXT: 'Alcune modifiche non sono state salvate. Confermi di voler uscire?',
      CONFIRM: 'Conferma',
      YES: 'Si',
      NO: 'No',
      OK: 'OK'
    },
    CLEAR: 'Clear',
    SUBMIT: 'Save'
  },
  HOME: {
    TITLE: 'Home di',
    MORE_BUTTON: 'Visualizza Avvisi al Pubblico ',
    SEARCH_BUTTON: 'Ricerca Pratiche'
  },
  CONFIGURAZIONE_PROFILI: {
    TITLE: 'Configurazione dei profili',
    HELPER: {
      SELECT: 'Seleziona profilo su cui operare',
      SELECTED: 'Configurazione del profilo selezionato'
    },
    FORM: {
      PROFILO: { LABEL: 'Profilo', PLACEHOLDER: 'Seleziona un profilo' },
      FUNZIONALITA: {
        LABEL: 'Funzionalità',
        PLACEHOLDER: 'Seleziona una funzionalità'
      }
    },
    TABLE: {
      ON: 'On',
      OFF: 'Off',
      COLUMNS: {
        ID: 'Id',
        FUNZIONALITA: "Funzionalità",
        READ: 'Read',
        UPDATE: 'Update',
        INSERT: 'Insert',
        DELETE: 'Delete',
        AZIONI: 'Azioni'
      }
    },
    DELETE: {
      BODY: 'Sei sicuro di voler eliminare la funzionalità "{{desc}}" con id = {{id}}?',
      FOOTER: {
        CANCEL: 'No',
        CONFIRM: 'Si'
      }
    }
  },

  ACCREDITAMENTO_FO: {
    NUOVA_DOMANDA: {
      TITLE: 'Nuova domanda di accreditamento',
      FORM: {
        CF: {
          LABEL: 'Codice Fiscale',
          PLACEHOLDER: 'Inserisci codice fiscale'
        },
        COGNOME: {
          LABEL: 'Cognome',
          PLACEHOLDER: 'Inserisci cognome'
        },
        NOME: {
          LABEL: 'Nome',
          PLACEHOLDER: 'Inserisci nome'
        },
        GESTORE: {
          LABEL: "Gestore di cui richiedi l'accreditamento",
          PLACEHOLDER: 'Seleziona il gestore'
        },
        MAIL: {
          LABEL: 'Indirizzo di posta elettronica',
          PLACEHOLDER: 'Email'
        },
        TELEFONO: {
          LABEL: 'Numero di telefono',
          PLACEHOLDER: 'Telefono'
        },
        NOTE: {
          LABEL: 'Nota',
          PLACEHOLDER: 'Nota'
        },
        NOTE_LAVORAZIONE: {
          LABEL: 'Note lavorazione',
          PLACEHOLDER: 'Note lavorazione'
        },
        STATO: {
          LABEL: 'Stato',
          PLACEHOLDER: 'Stato'
        },
        DATA_INIZIO: {
          LABEL: 'Data inizio validità',
          PLACEHOLDER: 'Data inizio validità'
        },
        DATA_FINE: {
          LABEL: 'Data fine validità',
          PLACEHOLDER: 'Data fine validità'
        },
        DATA_RICHIESTA: {
          LABEL: 'Data richiesta',
          PLACEHOLDER: 'Data richiesta'
        },
        BUTTON: 'Inserisci domanda'
      }
    },
    GESTIONE: {
      TITLE: 'Gestione domande di accreditamento',
      TABLE: {
        ID: 'ID Domanda',
        GESTORE: 'Gestore',
        DATA_DOMANDA: 'Data Domanda',
        STATO: 'Stato domanda',
        AZIONI: 'Azioni'
      },
      EDIT: {
        BUTTON: 'Salva dati',
        TITLE: 'Visualizza dati domanda di accreditamento'
      }
    }
  },
  UTENTI: {
    RICERCA: {
      TITLE: 'Ricerca utenti',
      NUOVO_UTENTE: 'Inserisci nuovo utente',
      HELPER: 'Valorizza almeno un parametro di filtro e "Avvia ricerca"',
      FORM: {
        BUTTON: 'Avvia ricerca',
        CF: {
          LABEL: 'Codice Fiscale',
          PLACEHOLDER: 'Inserisci codice fiscale'
        },
        COGNOME: {
          LABEL: 'Cognome',
          PLACEHOLDER: 'Inserisci cognome'
        },
        NOME: {
          LABEL: 'Nome',
          PLACEHOLDER: 'Inserisci nome'
        },
        GESTORE: {
          LABEL: 'Gestore',
          PLACEHOLDER: 'Seleziona il gestore'
        },
        PROFILO: {
          LABEL: 'Profilo',
          PLACEHOLDER: 'Seleziona il profilo'
        }
      }
    },
    LISTA: {
      TITLE: 'Tabella utenti',
      NUOVO_UTENTE: 'Inserisci nuovo utente',
      COLUMNS: {
        ID: 'ID Utente',
        CF: 'Codice Fiscale',
        COGNOME: 'Cognome',
        NOME: 'Nome',
        INIZIO: 'Data inizio validità',
        FINE: 'Data fine validità',
        AZIONI: 'Azioni'
      },
      FORM: {
        BUTTON: 'Avvia ricerca',
        CF: {
          LABEL: 'Codice Fiscale',
          PLACEHOLDER: 'Inserisci codice fiscale'
        },
        COGNOME: {
          LABEL: 'Cognome',
          PLACEHOLDER: 'Inserisci cognome'
        },
        NOME: {
          LABEL: 'Nome',
          PLACEHOLDER: 'Inserisci nome'
        },
        GESTORE: {
          LABEL: 'Gestore',
          PLACEHOLDER: 'Seleziona il gestore'
        },
        PROFILO: {
          LABEL: 'Profilo',
          PLACEHOLDER: 'Seleziona il profilo'
        }
      }
    },
    CREATE: {
      TITLE: 'Nuovo utente',
      NUOVO_UTENTE: 'Inserisci nuovo utente',
      HELPER: 'Compila i dati richiesti e "Inserisci il nuovo utente"',
      FORM: {
        BUTTON: 'Avvia ricerca',
        CF: {
          LABEL: 'Codice Fiscale',
          PLACEHOLDER: 'Inserisci codice fiscale'
        },
        COGNOME: {
          LABEL: 'Cognome',
          PLACEHOLDER: 'Inserisci cognome'
        },
        NOME: {
          LABEL: 'Nome',
          PLACEHOLDER: 'Inserisci nome'
        },
        GESTORE: {
          LABEL: 'Gestore',
          PLACEHOLDER: 'Seleziona il gestore'
        },
        PROFILO: {
          LABEL: 'Profilo',
          PLACEHOLDER: 'Seleziona il profilo'
        },
        MAIL: {
          LABEL: 'Indirizzo di posta elettronica',
          PLACEHOLDER: 'Email'
        },
        TELEFONO: {
          LABEL: 'Numero di telefono',
          PLACEHOLDER: 'Telefono'
        }
      }
    },
    EDIT: {
      TITLE: 'Dati utente',
      BTN: 'Salva i dati',
      HELPER: "Dati dell'utente inserito",
      FORM: {
        BUTTON: 'Avvia ricerca',
        CF: {
          LABEL: 'Codice Fiscale',
          PLACEHOLDER: 'Inserisci codice fiscale'
        },
        COGNOME: {
          LABEL: 'Cognome',
          PLACEHOLDER: 'Inserisci cognome'
        },
        NOME: {
          LABEL: 'Nome',
          PLACEHOLDER: 'Inserisci nome'
        },
        GESTORE: {
          LABEL: 'Gestore',
          PLACEHOLDER: 'Seleziona il gestore'
        },
        PROFILO: {
          LABEL: 'Profilo',
          PLACEHOLDER: 'Seleziona il profilo'
        },
        MAIL: {
          LABEL: 'Indirizzo di posta elettronica',
          PLACEHOLDER: 'Email'
        },
        TELEFONO: {
          LABEL: 'Numero di telefono',
          PLACEHOLDER: 'Telefono'
        },

        FINE: {
          LABEL: 'Data fine validità',
          PLACEHOLDER: 'Data fine validità'
        },

        INIZIO: {
          LABEL: 'Data inizio validità',
          PLACEHOLDER: 'Data inizio validità'
        }
      },
      TABLE: {
        ON: 'On',
        OFF: 'Off',
        COLUMNS: {
          ID: 'Id Gestore',
          GESTORE: 'Gestore',
          INIZIO: 'Data inizio validità',
          FINE: 'Data fine validità',
          AZIONI: 'Azioni'
        }
      }
    },
    TABLE: {
      ON: 'On',
      OFF: 'Off',
      COLUMNS: {
        ID: 'Id',
        CF: 'Codice fiscale',
        NOME: 'Nome',
        COGNOME: 'Cognome',
        INIZIO: 'Data inizio validità',
        FINE: 'Data fine validità',
        AZIONI: 'Azioni'
      }
    },
    DELETE: {
      BODY: 'Sei sicuro di voler eliminare la funzionalità "{{desc}}" con id = {{id}}?',
      FOOTER: {
        CANCEL: 'No',
        CONFIRM: 'Si'
      }
    }
  },
  GESTORI: {
    RICERCA: {
      TITLE: 'Ricerca gestori',
      NUOVO_GESTORE: 'Inserisci nuovo gestore',
      HELPER: 'Valorizza almeno un parametro di filtro e "Avvia ricerca"',
      FORM: {
        BUTTON: 'Avvia ricerca',
        CF: {
          LABEL: 'Codice Fiscale / Partita IVA',
          PLACEHOLDER: 'Inserisci codice fiscale / partita IVA'
        },
        GESTORE: {
          LABEL: 'Gestore',
          PLACEHOLDER: 'Seleziona il gestore'
        },
        NATURA_GIURIDICA: {
          LABEL: 'Natura giuridica',
          PLACEHOLDER: 'Seleziona la Natura giuridica'
        },
        PROVINCIA: {
          LABEL: 'Provincia',
          PLACEHOLDER: 'Seleziona la provincia/CM'
        },
        COMUNE: {
          LABEL: 'Comune',
          PLACEHOLDER: 'Seleziona il comune'
        }
      }
    },
    LISTA: {
      TITLE: 'Tabella gestori',
      NUOVO_UTENTE: 'Inserisci nuovo utente',
      COLUMNS: {
        ID: 'ID Utente',
        CF: 'Codice Fiscale',
        COGNOME: 'Cognome',
        NOME: 'Nome',
        INIZIO: 'Data inizio validità',
        FINE: 'Data fine validità',
        AZIONI: 'Azioni'
      },
      FORM: {
        BUTTON: 'Avvia ricerca',
        CF: {
          LABEL: 'Codice Fiscale',
          PLACEHOLDER: 'Inserisci codice fiscale'
        },
        COGNOME: {
          LABEL: 'Cognome',
          PLACEHOLDER: 'Inserisci cognome'
        },
        NOME: {
          LABEL: 'Nome',
          PLACEHOLDER: 'Inserisci nome'
        },
        GESTORE: {
          LABEL: 'Gestore',
          PLACEHOLDER: 'Seleziona il gestore'
        },
        PROFILO: {
          LABEL: 'Profilo',
          PLACEHOLDER: 'Seleziona il profilo'
        }
      }
    },
    CREATE: {
      TITLE: 'Nuovo gestore',
      NUOVO_UTENTE: 'Inserisci nuovo gestore',
      HELPER: 'Compila i dati richiesti e "Inserisci il nuovo utente"',
      FORM: {
        BUTTON: 'Avvia ricerca',
        CF: {
          LABEL: 'Codice Fiscale / Partita IVA',
          PLACEHOLDER: 'Inserisci codice fiscale'
        },
        RAGIONE_SOCIALE: {
          LABEL: 'Denominazione o Ragione sociale',
          PLACEHOLDER: 'Inserisci Denominazione o Ragione sociale'
        },
        NATURA_GIURIDICA: {
          LABEL: 'Natura giuridica',
          PLACEHOLDER: 'Seleziona Natura giuridica'
        },
        SEDE_LEGALE: 'Sede legale',
        SEDIME: {
          LABEL: 'Sedime',
          PLACEHOLDER: 'Seleziona sedime'
        },
        INDIRIZZO: {
          LABEL: 'Indirizzo',
          PLACEHOLDER: 'Inserisci indirizzo'
        },
        PROVINCIA: {
          LABEL: 'Provincia',
          PLACEHOLDER: 'Seleziona la provincia/CM'
        },
        NAZIONE: {
          LABEL: 'Nazione straniera',
          PLACEHOLDER: 'Seleziona Nazione Straniera'
        },
        COMUNE: {
          LABEL: 'Comune di domicilio fiscale',
          PLACEHOLDER: 'Seleziona il comune'
        },
        CAP: {
          LABEL: 'CAP',
          PLACEHOLDER: 'Inserisci CAP'
        },
        CONTATTI: 'Contatti',
        MAIL: {
          LABEL: 'Indirizzo di posta elettronica',
          PLACEHOLDER: 'Email'
        },
        PEC: {
          LABEL: 'Posta elettronica certificata',
          PLACEHOLDER: 'PEC'
        },
        TELEFONO: {
          LABEL: 'Numero di telefono',
          PLACEHOLDER: 'Telefono'
        },
        LEGALE_RAPPRESENTANTE: 'Legale Rappresentante',
        COGNOME: {
          LABEL: 'Cognome',
          PLACEHOLDER: 'Inserisci cognome'
        },
        NOME: {
          LABEL: 'Nome',
          PLACEHOLDER: 'Inserisci nome'
        },
        QUALIFICA: {
          LABEL: 'Qualifica',
          PLACEHOLDER: 'Inserisci la qualifica'
        },
        FINE: {
          LABEL: 'Data fine validità',
          PLACEHOLDER: 'Data fine validità'
        },
        INIZIO: {
          LABEL: 'Data inizio validità',
          PLACEHOLDER: 'Data inizio validità'
        }
      }
    },
    EDIT: {
      TITLE: 'Dati gestore',
      BTN: 'Salva i dati',
      HELPER: 'Dati del gestore inserito',
      FORM: {
        BUTTON: 'Avvia ricerca',
        CF: {
          LABEL: 'Codice Fiscale',
          PLACEHOLDER: 'Inserisci codice fiscale'
        },
        COGNOME: {
          LABEL: 'Cognome',
          PLACEHOLDER: 'Inserisci cognome'
        },
        NOME: {
          LABEL: 'Nome',
          PLACEHOLDER: 'Inserisci nome'
        },
        GESTORE: {
          LABEL: 'Gestore',
          PLACEHOLDER: 'Seleziona il gestore'
        },
        PROFILO: {
          LABEL: 'Profilo',
          PLACEHOLDER: 'Seleziona il profilo'
        },
        MAIL: {
          LABEL: 'Indirizzo di posta elettronica',
          PLACEHOLDER: 'Email'
        },
        TELEFONO: {
          LABEL: 'Numero di telefono',
          PLACEHOLDER: 'Telefono'
        },

        FINE: {
          LABEL: 'Data fine validità',
          PLACEHOLDER: 'Data fine validità'
        },

        INIZIO: {
          LABEL: 'Data inizio validità',
          PLACEHOLDER: 'Data inizio validità'
        }
      },
      TABLE: {
        ON: 'On',
        OFF: 'Off',
        COLUMNS: {
          ID: 'Id Gestore',
          GESTORE: 'Gestore',
          INIZIO: 'Data inizio validità',
          FINE: 'Data fine validità',
          AZIONI: 'Azioni'
        }
      }
    },
    TABLE: {
      ON: 'On',
      OFF: 'Off',
      COLUMNS: {
        ID: 'Id',
        CF: 'Codice fiscale',
        NOME: 'Nome',
        COGNOME: 'Cognome',
        RAGIONE_SOCIALE: 'Ragione sociale',
        COMUNE: 'Comune sede legale',
        INDIRIZZO: 'Indirizzo',
        INIZIO: 'Data inizio validità',
        FINE: 'Data fine validità',
        AZIONI: 'Azioni'
      }
    },
    DELETE: {
      BODY: 'Sei sicuro di voler eliminare la funzionalità "{{desc}}" con id = {{id}}?',
      FOOTER: {
        CANCEL: 'No',
        CONFIRM: 'Si'
      }
    }
  },
  PROFILI: {
    TITLE: 'Gestione dei profili',
    HELPER: {
      SELECT: 'Seleziona profilo su cui operare',
      SELECTED: 'Gestione del profilo selezionato'
    },
    FORM: {
      FIELDS: {
        DESCRIZIONE: {
          LABEL: 'Descrizione del profilo',
          PLACEHOLDER: 'Descrizione del profilo'
        }
      }
    },
    TABLE: {
      ON: 'On',
      OFF: 'Off',
      COLUMNS: {
        ID: 'ID',
        DESC: 'Descrizione del profilo',
        TIPOLOGIAPROFILO: 'Tipologia profilo',
        DATAINIZIOVALIDITA: 'Data inizio validita',
        DATAFINEVALIDITA: 'Data fine validita',
        AZIONI: 'Azioni'
      }
    },
    DELETE: {
      BODY: 'Sei sicuro di voler eliminare la funzionalità "{{desc}}" con id = {{id}}?',
      FOOTER: {
        CANCEL: 'No',
        CONFIRM: 'Si'
      }
    }
  },
  BOOKING: {
    NEW: {
      TITLE: 'Aggiungi prenotazione',
      NOTIFICATION: {
        TITLE: 'Successo',
        TEXT: 'Prenotazione creata con successo'
      }
    },
    DETAIL: {
      NOTIFICATION: {
        ERROR: {
          TITLE: 'Prenotazione non trovate',
          TEXT: 'Non è stato possibile trovare la prenotazione cercata'
        }
      }
    },
    EDIT: {
      TITLE: 'Modifica prenotazione',
      HEADER: {
        START_DATE: 'Dal',
        END_DATE: 'Al',
        BOOKING_DATE: 'Data prenotazione',
        ID: 'Id'
      },
      NOTIFICATION: {
        TITLE: 'Successo',
        TEXT: 'Prenotazione modificata con successo'
      }
    },
    FORM: {
      CANCEL: 'Annulla',
      SAVE: 'Salva',
      FIELDS: {
        CITY: {
          LABEL: 'Città',
          PLACEHOLDER: `Inserisci città`
        },
        START_DATE: {
          LABEL: 'Data inizio',
          PLACEHOLDER: `Inserisci data inizio`
        },
        END_DATE: {
          LABEL: 'Data fine',
          PLACEHOLDER: `Inserisci data fine`
        },
        ADDRESS: {
          LABEL: 'Indirizzo',
          PLACEHOLDER: `Inserisci indirizzo`
        },
        AMOUNT: {
          LABEL: 'Totale',
          PLACEHOLDER: `Inserisci totale`
        },
        DISCOUNT_CODE: {
          LABEL: 'Codice sconto',
          PLACEHOLDER: `Inserisci codice sconto`
        },
        NOTES: {
          LABEL: 'Note',
          PLACEHOLDER: `Inserisci note`
        }
      }
    },
    LIST: {
      TAB_TITLE: 'Lista',
      TITLE: 'Lista prenotazioni',
      TOTAL_ELEMENTS: ' prenotazioni',
      EXPORT: 'Esporta',
      ADD_BUTTON: 'Aggiungi Prenotazione',
      FIELDS: {
        START_DATE: 'Dal',
        END_DATE: 'Al',
        BOOKING_DATE: 'Data prenotazione',
        ID: 'Id',
        TYPE: 'Tipo',
        ADDRESS: 'Indirizzo',
        CITY: 'Città',
        PRICE: 'Prezzo',
        USER_FULLNAME: 'Utente',
        COMPANY: 'Azienda',
        AMOUNT: 'Totale',
        DISCOUNT: 'Sconto',
        DISCOUNT_CODE: 'Codice sconto',
        NOTES: 'Note'
      },
      FILTERS: {
        DATE_INTERVALL: {
          LABEL: 'Periodo',
          PLACEHOLDER: 'Seleziona intervallo date'
        },
        USER: {
          LABEL: 'Utente',
          PLACEHOLDER: `Inserisci utente`
        },
        COMPANY: {
          LABEL: 'Azienda',
          PLACEHOLDER: `Inserisci azienda`
        },
        TYPE: {
          LABEL: 'Tipo',
          PLACEHOLDER: `Inserisci tipo`
        },
        CITY: {
          LABEL: 'Città',
          PLACEHOLDER: `Inserisci città`
        },
        PRICE: {
          LABEL: 'Prezzo',
          PLACEHOLDER: `Inserisci prezzo`
        }
      },
      MODAL_DETAIL_TITLE: 'Dettaglio prenotazione',
      DROPDOWN: {
        EDIT: 'Modifica',
        DELETE: 'Elimina'
      }
    },
    DELETE: {
      TITLE: 'Elimina prenotazione',
      BODY: 'Sei sicuro di voler eliminare la prenotazione con id = {{id}}',
      FOOTER: {
        CANCEL: 'No',
        CONFIRM: 'Si, cancella prenotazione'
      },
      NOTIFICATION: {
        TITLE: 'Prenotazione eliminata',
        TEXT: 'Prenotazione eliminata con successo. Grazie'
      }
    }
  },
  UTENTI_PROFILO: {
    TITLE: 'Associa Utenti a Profili',
    HELPER: {
      SELECT: 'Seleziona profilo su cui operare',
      SELECTED: 'Configurazione del profilo selezionato'
    },
    FORM: {
      PROFILO: { LABEL: 'Profilo', PLACEHOLDER: 'Seleziona un profilo' }
    },
    TABLE: {
      COLUMNS: {
        IDUTENTE: 'Id utente',
        CODICEFISCALE: "Codice fiscale",
        COGNOME: 'Cognome',
        NOME: 'Nome',
        AZIONI: 'Azioni'
      }
    },
    DELETE: {
      BODY: 'Sei sicuro di voler eliminare la funzionalità "{{desc}}" con id = {{id}}?',
      FOOTER: {
        CANCEL: 'No',
        CONFIRM: 'Si'
      }
    }
  },
  IMPIANTI: {
    RICERCA: {
      TITLE: 'Ricerca Impianti',
      NUOVO_IMPIANTO: 'Inserisci nuovo impianto',
      FORM: {
        BUTTON: 'Avvia ricerca',
        IDGESTORE: {
          LABEL: 'Gestore dell\'impianto',
          PLACEHOLDER: 'Seleziona il gestore'
        },
        DENOMINAZIONE: {
          LABEL: 'Denominazione impianto',
          PLACEHOLDER: 'Inserisci la denominazione dell\'impianto'
        },
        IDPROVINCIA: {
          LABEL: 'Provincia/CM',
          PLACEHOLDER: 'Seleziona la provincia/CM'
        },
        IDCOMUNE: {
          LABEL: 'Comune del sito',
          PLACEHOLDER: 'Seleziona il comune'
        },
        IDTIPOIMPIANTO: {
          LABEL: 'Tipo Impianto',
          PLACEHOLDER: 'Seleziona il tipo impianto'
        },
        IDSTATOIMPIANTO: {
          LABEL: 'Stato impianto',
          PLACEHOLDER: 'Seleziona lo stato dell\'impianto'
        }
      }
    },
    LISTA: {
      TITLE: 'Risultato Ricerca',
      NUOVO_IMPIANTO: 'Inserisci nuovo impianto',
      PARAMETRI: 'Parametri di filtro applicati',
      TABLE: {
        COLUMNS: {
          DENOMINAZIONE: 'Impianto',
          GESTORE: 'Gestore',
          COMUNE: 'Comune del sito',
          PROVINCIA: 'Provincia',
          DATAINIZIOVALIDITA: 'Data inizio validita',
          DATAFINEVALIDITA: 'Data fine validita',
          AZIONI: 'Azioni'
        }
      }
    },
    EDIT: {
      TITLE: 'Dati dell\'impianto',
      BTN: 'Salva i dati',
      HELPER: "Modifica i dati dell'impianto poi \"Salva i dati\"",
      TABS:{
        LINEA:'Struttura impianto',
        ATTO:'Provvedimenti autorizzativi'
      },
      FORM: {
        BUTTON: 'Salva i dati',
        LABELDATIIMPIANTO:'Dati Impianto',
        LABELSITOIMPIANTO:'Sito Impianto',
        IDGESTORE: {
          LABEL: 'Gestore dell\'impianto',
          PLACEHOLDER: 'Seleziona il gestore'
        },
        DENOMINAZIONE: {
          LABEL: 'Denominazione impianto',
          PLACEHOLDER: 'Inserisci la denominazione'
        },
        TIPOIMPIANTO: {
          LABEL: 'Tipo impianto',
          PLACEHOLDER: 'Seleziona il tipo impianto'
        },
        STATOIMPIANTO: {
          LABEL: 'Stato impianto',
          PLACEHOLDER: 'Seleziona lo stato impianto'
        },
        DATAINIZIOVALIDITA: {
          LABEL: 'Data inizio Validità',
          PLACEHOLDER: 'Seleziona la data inizio validità'
        },
        DATAFINEVALIDITA: {
          LABEL: 'Data fine Validità',
          PLACEHOLDER: 'Seleziona la data fine validità'
        },
        IDSEDIME: {
          LABEL: 'Sedime',
          PLACEHOLDER: 'Seleziona il sedime'
        },
        INDIRIZZO: {
          LABEL: 'Indirizzo',
          PLACEHOLDER: 'Inserisci indirizzo'
        },
        PROVINCIA: {
          LABEL: 'Provincia/CM',
          PLACEHOLDER: 'Seleziona la provincia'
        },
        COMUNE: {
          LABEL: 'Comune sito dell\'impianto',
          PLACEHOLDER: 'Seleziona il comune'
        },
        CAP: {
          LABEL: 'Cap',
          PLACEHOLDER: 'Inserisci il cap'
        }
      },
      EDITSUCCESSTITLE:'Impianto',
      EDITSUCCESSTEXT:'Dati salvati correttamente'
    },
    CREATE: {
      TITLE: 'Nuovo impianto',
      BTN: 'Salva i dati',
      HELPER: "Inserisci i dati dell'impianto poi \"Salva i dati\"",
      TABS:{
        LINEA:'Struttura impianto',
        ATTO:'Provvedimenti autorizzativi'
      },
      EDITSUCCESSTITLE:'Impianto',
      EDITSUCCESSTEXT:'Dati salvati correttamente'
    },
    FORMDATIIMPIANTO: {
      TITLE:'Dati Impianto',
      IDGESTORE: {
        LABEL_: 'Gestore dell\'impianto',
        PLACEHOLDER: 'Seleziona il gestore'
      },
      DENOMINAZIONE: {
        LABEL: 'Denominazione impianto',
        PLACEHOLDER: 'Inserisci la denominazione dell\'impianto'
      },
      IDTIPOIMPIANTO: {
        LABEL: 'Tipo Impianto',
        PLACEHOLDER: 'Seleziona il tipo impianto'
      },
      IDSTATOIMPIANTO: {
        LABEL: 'Stato impianto',
        PLACEHOLDER: 'Seleziona lo stato dell\'impianto'
      },
      DATAINIZIOVALIDITA: {
        LABEL: 'Data inizio Validità',
        PLACEHOLDER: 'Seleziona la data inizio validità'
      },
      DATAFINEVALIDITA: {
        LABEL: 'Data fine Validità',
        PLACEHOLDER: 'Seleziona la data fine validità'
      },
    },
    FORMDATISITO:{
      TITLE:'Sito Impianto',
      IDSEDIME: {
        LABEL: 'Sedime',
        PLACEHOLDER: 'Seleziona il sedime'
      },
      INDIRIZZO: {
        LABEL: 'Indirizzo',
        PLACEHOLDER: 'Inserisci indirizzo'
      },
      PROVINCIA: {
        LABEL: 'Provincia/CM',
        PLACEHOLDER: 'Seleziona la provincia'
      },
      COMUNE: {
        LABEL: 'Comune sito dell\'impianto',
        PLACEHOLDER: 'Seleziona il comune'
      },
      CAP: {
        LABEL: 'CAP',
        PLACEHOLDER: 'Inserisci il cap'
      }
    },
    TABLELINEE:{
      COLUMNS: {
        LINEA: 'Linea dell\'impianto',
        DATAINIZIOVALIDITA: 'Data inizio validità',
        DATAFINEVALIDITA: 'Data fine validità',
        AZIONI: 'Azioni'
      }
    },
    TABLEATTI:{
      COLUMNS: {
        NUMERO: 'Numero provvedimento',
        DATAPROVVEDIMENTO: 'Data provvedimento',
        TIPOPROVVEDIMENTO: 'Tipo provvedimento',
        DATADECORRENZA: 'Data decorrenza',
        DATASCADENZA: 'Data scadenza',
        AZIONI: 'Azioni'
      }
    },
    FORMLINEE: {
      FIELDS:{
        LINEA:{
          LABEL: 'Linea',
          PLACEHOLDER: 'Seleziona la LINEA'
        },
        DATAINIZIOVALIDITA: {
          LABEL: 'Data inizio Validità',
          PLACEHOLDER: 'Seleziona la data inizio validità'
        },
        DATAFINEVALIDITA: {
          LABEL: 'Data fine Validità',
          PLACEHOLDER: 'Seleziona la data fine validità'
        },
      }
    },
    FORMATTI: {
      FIELDS:{
        NUMERO:{
          LABEL: 'Numero Provvedimento',
          PLACEHOLDER: 'Inserisci il numero di provvedimento'
        },
        TIPOPROVVEDIMENTO:{
          LABEL: 'Tipo Provvedimento',
          PLACEHOLDER: 'Seleziona il tipo di provvedimento'
        },
        DATAINIZIOVALIDITA: {
          LABEL: 'Data inizio Validità',
          PLACEHOLDER: 'Seleziona la data inizio validità'
        },
        DATAFINEVALIDITA: {
          LABEL: 'Data fine Validità',
          PLACEHOLDER: 'Seleziona la data fine validità'
        },
      }
    }
  },
  ACCREDITAMENTO_BO: {
    RICERCA: {
      TITLE: 'Filtra domande di Accreditamento',
      FORM: {
        BUTTON: 'Avvia ricerca',
        IDOMANDA: {
          LABEL: 'Identificativo Domanda',
          PLACEHOLDER: 'Inserisci il parametro'
        },
        IDSTATODOMANDA: {
          LABEL: 'Stato della domanda',
          PLACEHOLDER: 'Seleziona lo stato della domanda'
        },
        IDGESTORE: {
          LABEL: 'Gestori',
          PLACEHOLDER: 'Seleziona il gestori'
        },
        IDRICHIEDENTE: {
          LABEL: 'Richiedenti',
          PLACEHOLDER: 'Seleziona i richiedenti'
        },
        DATADOMANDADAL: {
          LABEL: 'Data domanda dal',
          PLACEHOLDER: 'Seleziona la data'
        },
        DATADOMANDAAL: {
          LABEL: 'Data domanda al',
          PLACEHOLDER: 'Seleziona la data'
        }
      }
    },
    LISTA: {
      TITLE: 'Gestione domande di accreditamento',
      NUOVO_UTENTE: 'Inserisci nuovo utente',
      PARAMETRI: 'Parametri di filtro applicati',
      TABLE: {
        COLUMNS: {
          ID: 'ID Domanda',
          GESTORE: 'Gestore',
          DATARICHIESTA: 'Data domanda',
          STATODOMANDA: 'Stato domanda',
          RICHIEDENTE: 'Richiedente',
          AZIONI: 'Azioni'
        }
      },
      FORM: {
        BUTTON: 'Avvia ricerca',
        CF: {
          LABEL: 'Codice Fiscale',
          PLACEHOLDER: 'Inserisci codice fiscale'
        },
        COGNOME: {
          LABEL: 'Cognome',
          PLACEHOLDER: 'Inserisci cognome'
        },
        NOME: {
          LABEL: 'Nome',
          PLACEHOLDER: 'Inserisci nome'
        },
        GESTORE: {
          LABEL: 'Gestore',
          PLACEHOLDER: 'Seleziona il gestore'
        },
        PROFILO: {
          LABEL: 'Profilo',
          PLACEHOLDER: 'Seleziona il profilo'
        }
      }
    },
    EDIT: {
      TITLE: 'Dati della domanda di accreditamento',
      BTN: 'Salva i dati',
      HELPER: "Dati dell'utente inserito",
      FORM: {
        BUTTON: 'Avvia ricerca',
        STATODOMANDA: {
          LABEL: 'Stato delle domanda',
          PLACEHOLDER: 'Stato delle domanda'
        },
        CF: {
          LABEL: 'Codice Fiscale',
          PLACEHOLDER: 'Inserisci codice fiscale'
        },
        PROFILO: {
          LABEL: "Profilo dell'operatore di inserimento domanda",
          PLACEHOLDER: 'Seleziona il profilo'
        },
        COGNOME: {
          LABEL: 'Cognome',
          PLACEHOLDER: 'Inserisci cognome'
        },
        NOME: {
          LABEL: 'Nome',
          PLACEHOLDER: 'Inserisci nome'
        },
        GESTORE: {
          LABEL: 'Gestore di cui si chiede accreditamento',
          PLACEHOLDER: 'Seleziona il gestore'
        },
        TELEFONO: {
          LABEL: 'Numero di Telefono',
          PLACEHOLDER: 'Seleziona il numero di Telefono'
        },
        EMAIL: {
          LABEL: 'Indirizzo di posta elettronica',
          PLACEHOLDER: "Seleziona l'indirizzo di posta elettronica"
        },
        NOTAUTENTE: {
          LABEL: 'Nota della Domanda',
          PLACEHOLDER: 'Inserisci la nota della domanda'
        },
        NOTALAVORAZIONE: {
          LABEL: 'Nota di Lavorazione',
          PLACEHOLDER: 'Inserisci la nota di Lavorazione'
        }
      },
      TABLE: {
        ON: 'On',
        OFF: 'Off',
        COLUMNS: {
          ID: 'Id Gestore',
          GESTORE: 'Gestore',
          INIZIO: 'Data inizio validità',
          FINE: 'Data fine validità',
          AZIONI: 'Azioni'
        }
      },
      EDITSUCCESSTITLE: 'Domanda di accreditamento',
      EDITSUCCESSTEXT: 'Dati salvati correttamente'
    },
    TABLE: {
      ON: 'On',
      OFF: 'Off',
      COLUMNS: {
        ID: 'Id',
        CF: 'Codice fiscale',
        NOME: 'Nome',
        COGNOME: 'Cognome',
        INIZIO: 'Data inizio validità',
        FINE: 'Data fine validità',
        AZIONI: 'Azioni'
      }
    },
    DELETE: {
      BODY: 'Sei sicuro di voler eliminare la funzionalità "{{desc}}" con id = {{id}}?',
      FOOTER: {
        CANCEL: 'No',
        CONFIRM: 'Si'
      }
    }
  },
  DICHIARAZIONI: {
    RICERCA: {
      TITLE: 'Ricerca Dichiarazioni Annuali',
      NUOVA_DICHIARAZIONE: 'Inserisci nuova Dichiarazione Annuale',
      FORM: {
        BUTTON: 'Avvia ricerca',
        IDGESTORE: {
          LABEL: 'Gestori',
          PLACEHOLDER: 'Seleziona il gestore'
        },
        IMPIANTO: {
          LABEL: 'Impianti',
          PLACEHOLDER: "Seleziona l'impianto"
        },
        ANNODAL: {
          LABEL: 'Anno tributo Dal',
          PLACEHOLDER: 'Seleziona la data'
        },
        ANNOAL: {
          LABEL:  'Anno tributo Al',
          PLACEHOLDER: 'Seleziona la data'
        }
      }
    },
    LISTA: {
      TITLE: 'Risultato Ricerca DA',
      NUOVA_DICHIARAZIONE: 'Inserisci nuova Dichiarazione Annuale',
      PARAMETRI: 'Parametri di filtro applicati',
      TABLE: {
        COLUMNS: {
          ANNO: 'Anno',
          GESTORE: 'Gestore',
          IMPIANTO: 'Impianto',
          STATODA: 'Stato da',
          VERSIONE: 'Versione',
          PROTOCOLLO: 'Protocollo',
          AZIONI: 'Azioni',
          PREGRESSO:'Pregresso'
        }
      }
    },
    EDIT: {
      TITLE: 'Visualizzazione DA',
      BTN: 'Salva i dati',
      HELPER: "Modifica i dati dell'impianto poi \"Salva i dati\"",
      TABS:{
        LINEA:'Struttura impianto',
        ATTO:'Provvedimenti autorizzativi'
      },
      FORM: {
        BUTTON: 'Salva i dati',
        LABELDATIIMPIANTO:'Dati Impianto',
        LABELSITOIMPIANTO:'Sito Impianto',
        IDGESTORE: {
          LABEL: 'Gestore dell\'impianto',
          PLACEHOLDER: 'Seleziona il gestore'
        },
        DENOMINAZIONE: {
          LABEL: 'Denominazione impianto',
          PLACEHOLDER: 'Inserisci la denominazione'
        },
        TIPOIMPIANTO: {
          LABEL: 'Tipo impianto',
          PLACEHOLDER: 'Seleziona il tipo impianto'
        },
        STATOIMPIANTO: {
          LABEL: 'Stato impianto',
          PLACEHOLDER: 'Seleziona lo stato impianto'
        },
        DATAINIZIOVALIDITA: {
          LABEL: 'Data inizio Validità',
          PLACEHOLDER: 'Seleziona la data inizio validità'
        },
        DATAFINEVALIDITA: {
          LABEL: 'Data fine Validità',
          PLACEHOLDER: 'Seleziona la data fine validità'
        },
        IDSEDIME: {
          LABEL: 'Sedime',
          PLACEHOLDER: 'Seleziona il sedime'
        },
        INDIRIZZO: {
          LABEL: 'Indirizzo',
          PLACEHOLDER: 'Inserisci indirizzo'
        },
        PROVINCIA: {
          LABEL: 'Provincia/CM',
          PLACEHOLDER: 'Seleziona la provincia'
        },
        COMUNE: {
          LABEL: 'Comune sito dell\'impianto',
          PLACEHOLDER: 'Seleziona il comune'
        },
        CAP: {
          LABEL: 'CAP',
          PLACEHOLDER: 'Inserisci il cap'
        }
      },
      EDITSUCCESSTITLE:'Impianto',
      EDITSUCCESSTEXT:'Dati salvati correttamente'
    },
    TABS:{
      GESTORE:'Gestore',
      IMPIANTO:'Impianto',
      RIFIUTI:'Rifiuti Conferiti',
      VERSAMENTI:'Versamenti',
      SOGGETTI:'Soggetti',
      SEDEDOCUMENTI:'Sede Documenti',
      ANNOTAZIONI: 'Annotazioni'
    },
    CREATE: {
      TITLE: 'Nuova Dichiarazione Annuale',
      BTN: 'Salva i dati',
      HELPER: "Inserisci i dati della dichiarazione annuale e poi \"Salva i dati\"",
      EDITSUCCESSTITLE:'Dichiarazione Annuale',
      EDITSUCCESSTEXT:'Dati salvati correttamente',
      FORM: {
        BUTTON: 'Invia Dichiarazione',
        BUTTONDRAFT: 'Salva in Bozza',
        BUTTONNUOVAVERSIONE : 'Nuova Versione',
        GESTORE: {
          LABEL: 'Gestore',
          PLACEHOLDER: 'Seleziona il gestore'
        },
        IMPIANTO: {
          LABEL: 'Impianto',
          PLACEHOLDER: "Seleziona l'impianto"
        },
        ANNO: {
          LABEL: 'Anno',
          PLACEHOLDER: "Inserisci l'anno"
        },
        DATA: {
          LABEL: 'Data Dichiarazione',
          PLACEHOLDER: "Seleziona la Data Dichiarazione"
        },
        VERSIONE: {
          LABEL:  'Versione',
          PLACEHOLDER: 'Inserisci la versione'
        },
        STATO: {
          LABEL:  'Stato DA',
          PLACEHOLDER: 'Inserisci lo Stato DA'
        },
        PROTOCOLLO: {
          LABEL:  'Protocollo',
          PLACEHOLDER: 'Protocollo'
        },
        DATA_PROTOCOLLO:{
          LABEL: 'Data protocollo',
          PLACEHOLDER: "Seleziona la Data Dichiarazione"

        },
        PREGRESSO: 'Pregresso'
      },
    },
    FORMDATIIMPIANTO: {
      TITLE:'Dati Impianto',
      IDGESTORE: {
        LABEL: 'Gestore dell\'impianto',
        PLACEHOLDER: 'Seleziona il gestore'
      },
      DENOMINAZIONE: {
        LABEL: 'Denominazione impianto',
        PLACEHOLDER: 'Inserisci la denominazione dell\'impianto'
      },
      IDTIPOIMPIANTO: {
        LABEL: 'Tipo Impianto',
        PLACEHOLDER: 'Seleziona il tipo impianto'
      },
      IDSTATOIMPIANTO: {
        LABEL: 'Stato impianto',
        PLACEHOLDER: 'Seleziona lo stato dell\'impianto'
      },
      DATAINIZIOVALIDITA: {
        LABEL: 'Data inizio Validità',
        PLACEHOLDER: 'Seleziona la data inizio validità'
      },
      DATAFINEVALIDITA: {
        LABEL: 'Data fine Validità',
        PLACEHOLDER: 'Seleziona la data fine validità'
      },
    },
    TABLELINEE:{
      COLUMNS: {
        LINEA: 'Linea dell\'impianto',
        DATAINIZIOVALIDITA: 'Data inizio validità',
        DATAFINEVALIDITA: 'Data fine validità',
        AZIONI: 'Azioni'
      }
    },
    TABLEATTI:{
      COLUMNS: {
        NUMERO: 'Numero provvedimento',
        DATAPROVVEDIMENTO: 'Data provvedimento',
        TIPOPROVVEDIMENTO: 'Tipo provvedimento',
        DATADECORRENZA: 'Data decorrenza',
        DATASCADENZA: 'Data scadenza',
        AZIONI: 'Azioni'
      }
    },
    FORMLINEE: {
      FIELDS:{
        LINEA:{
          LABEL: 'Linea',
          PLACEHOLDER: 'Seleziona la LINEA'
        },
        DATAINIZIOVALIDITA: {
          LABEL: 'Data inizio Validità',
          PLACEHOLDER: 'Seleziona la data inizio validità'
        },
        DATAFINEVALIDITA: {
          LABEL: 'Data fine Validità',
          PLACEHOLDER: 'Seleziona la data fine validità'
        },
      }
    },
    FORMATTI: {
      FIELDS:{
        NUMERO:{
          LABEL: 'Numero Provvedimento',
          PLACEHOLDER: 'Inserisci il numero di provvedimento'
        },
        TIPOPROVVEDIMENTO:{
          LABEL: 'Tipo Provvedimento',
          PLACEHOLDER: 'Seleziona il tipo di provvedimento'
        },
        DATAINIZIOVALIDITA: {
          LABEL: 'Data inizio Validità',
          PLACEHOLDER: 'Seleziona la data inizio validità'
        },
        DATAFINEVALIDITA: {
          LABEL: 'Data fine Validità',
          PLACEHOLDER: 'Seleziona la data fine validità'
        },
      }
    },
    TABLESOGGETTI:{
      COLUMNS: {
        NUMERO: 'Num',
        CODFISCPARTIVA: 'Codice fiscale/partiva iva',
        RAGSOCIALE: 'Denominazione o ragione sociale',
        OBIETTIVI: 'Obiettivi raggiunti',
        AZIONI: 'Azioni'
      }
    },
    CREATESOGGETTI: {
      FORM: {
        CODFISCPARTIVA: {
          LABEL: 'Codice fiscale/parita iva',
          PLACEHOLDER: 'Inserisci il dato'
        },
        RAGSOCIALE: {
          LABEL: 'Denominazione o ragione sociale',
          PLACEHOLDER: 'Inserisci il valore'
        },
        OBIETTIVI: {
          LABEL: 'Obiettivi raggiunti',
          PLACEHOLDER: "",
          TRUE:'Si',
          FALSE:'No'
        }
      },
    },
    FORMDATISITO:{


    },
    SEDEDOCUMENTI: {
      FORM: {
        TITLE:'Luogo conservazione documento',
        IDSEDIME: {
          LABEL: 'Sedime',
          PLACEHOLDER: 'Seleziona il sedime'
        },
        INDIRIZZO: {
          LABEL: 'Indirizzo',
          PLACEHOLDER: 'Inserisci indirizzo'
        },
        PROVINCIA: {
          LABEL: 'Provincia/CM',
          PLACEHOLDER: 'Seleziona la provincia'
        },
        COMUNE: {
          LABEL: 'Comune sito dell\'impianto',
          PLACEHOLDER: 'Seleziona il comune'
        },
        CAP: {
          LABEL: 'CAP',
          PLACEHOLDER: 'Inserisci il cap'
        }
      },
    },
    ANNOTAZIONI: {
      FORM: {
        ANNOTAZIONI: {
          LABEL: 'Annotazioni',
          PLACEHOLDER: "Inserisci l'annotazione",
          MSG:'Limite massimo del testo 500 caratteri',
        }
      },
    },
    TABSCONTENT:{
      QUANTITA:'Quantità',
      DATA:'Data',
      IMPORTO:'Importo',
      TOTALE:'Totale',
      PERIODI:{
        PRIMO: {
          LABEL:'PRIMO TRIMESTRE'
        },
        SECONDO: {
          LABEL:'SECONDO TRIMESTRE'
        },
        TERZO: {
          LABEL:'TERZO TRIMESTRE'
        },
        QUARTO: {
          LABEL:'QUARTO TRIMESTRE'
        },
        TOTALE: {
          LABEL:'Totale annuo'
        }
      }
    },
    TABVERSAMENTI:{
      TOTALS:{
        IMPOSTA: "Imposta totale dovuta per l'anno di riferimento",
        IMPORTOTOTALE:'Importo totale versato',
        CREDITO:'Credito risultante dalla precedente dichiarazione',
        IMPORTODEBITO:'Importo a debito (da versare a saldo)',
        IMPORTOCREDITO:'Importo a credito (da portare in diminuzione nella dichiarazione successiva)',
      }
    },
    CREATECONFERIMENTO: {
      FORM:{
        UNITAMISURA:{
          LABEL: 'Tributo(€/Kg)',
          PLACEHOLDER: 'Unità di misura'
        },
        IMPORTO:{
          LABEL: 'IMPORTO',
          PLACEHOLDER: 'Seleziona il tipo di provvedimento'
        },
        RIFIUTOTARIFFA: {
          LABEL: 'Rifiuto Tariffa ',
          PLACEHOLDER: 'Seleziona un rifiuto tariffa'
        },
        RIDUZIONE: {
          LABEL: 'Riduzione',
          PLACEHOLDER: ' '
        },
      }
    },
    EXIST: {
      FOOTER: {
        CONFIRM: 'OK'
      }
    }


  },
  DICHIARAZIONI_MR: {
    NUOVA_DICHIARAZIONE: 'Inserisci nuova Dichiarazione obiettivi SENZA Richiesta presente a sistema',
    NUOVA_DICHIARAZIONE_RMR: 'Inserisci nuova Dichiarazione MR da Richiesta MR presente a sistema',
    RICERCA: {
      TITLE: 'Ricerca Dichiarazioni Conseguimento Obiettivi',
      NUOVA_DICHIARAZIONE: 'Inserisci nuova Dichiarazione di ammissione al pagamento in misura ridotta',
    },
    LISTA: {
      TITLE: 'Risultato Ricerca MR_D',
      TABLE: {
        COLUMNS: {
          STATO: 'Stato MR_D',
          PROTOCOLLO: 'Protocollo (Num/Anno)',
          INVIO_RICHIESTA: 'Data Invio Richiesta',
          LINEE_RICHIESTA: 'Linee Richiesta'
        }
      },
      BUTTON: {
        VISUALIZZA:'Visualizza dati della dichiarazione MR',
        SCARICA: 'Visualizza dati della dichiarazione MR',
        ELIMINA: 'Elimina dichiarazione MR'
      }
    },
    CREATE: {
      TITLE: 'Nuova Dichiarazione in misura ridotta',
      HELPER: "Inserisci i dati della dichiarazione in misura ridotta e poi \"Salva i dati\"",
      EDITSUCCESSTITLE:'Dichiarazione in misura ridotta',
      FORM: {
        DATA_REGISTRAZIONE: {
          LABEL: 'Data di registrazione',
        },
        PROTOCOLLO: {
          LABEL: 'Protocollo',
        },
        MODALITA:{
          LABEL: 'Data',
          MODALITA: 'Modalità(PEC. posta elettronica ordinaria, posta raccomandata, posta ordinaria)-indicare sempre l\'indirizzo'
        },

        STATO: {
          LABEL: 'Stato Dichiarazione'
        },
        BUTTON: {
          LABEL: 'Invia nuova dichiarazione MR'
        }
      }
    },
    EDIT: {
      TITLE: 'Visualizza MR_D'
    },
    TABS:{
      RICHIESTA: {
        TITLE: 'Richiesta Ammissione (MR-R)',
        FORM: {
          DATA: 'Mod. TS-MR-R (fare riferimento ai dati di registazione comunicati a suo tempo all\'amministrazione)',
          MODALITA: 'Non risulta pervenuta alcuna comunicazione. - La richiesta è stata inviata nella data e con le modalità sotto indicate:',
        }
      },
      PROCESSO:{
        TITLE: 'Processo impiantistico',
        DESCRIZIONE: {
          TAB: 'Descrizione Sommaria',
          TITLE: 'Descrizione sommaria della Linea',
          NOTA: 'Limite massimo del testo 1000 caratteri'
        },
        RIFIUTI_INGRESSO: 'Rifiuti In Ingresso a linea (r.i.i)',
        MATERIALE: 'Materiale in uscita',
        RIFIUTI_RECUPERABILI: 'Rifiuti recuperabili (r.r.u.)',
        RIFIUTI_USCITA: 'Rifiuti in uscita (r.u.)',
        //ELIMINA: 'Eliminare i dati della linea',
        FORM: {
          LINEE: 'Linee',
          LINEE_COMPILATE: 'Linee compilate nella dichiarazione',
        },
        TABLE: {
          RII: 'Rifiuti in ingresso \n (r.i.i.) E.E.R.',
          MAT_USCITA: 'Materiali ottenuti in uscita dalla linea impiantistica \n(descrizione)',
          RECUPERABILI: 'Rifiuti recuperabili \n (r.r.u.) E.E.R.',
          RIF_USCITA: 'Rifiuti in uscita\n(r.u.) E.E.R.',
          TONNELLATE_RICHIESTA: 'Tonnellate \nRichiesta',
          TONNELLATE_DICHIARAZIONE: 'Tonnellate Dichiarazione'
        },
        PERCENTUALI: {
          TITLE: 'Percentuale costituente obiettivo raggiunto per anno dichiarazione',
          RECUPERO: 'Percentuale di RECUPERO [(mat. + r.r.u.) / r.r.i.]',
          SCARTO: 'Percentuale di SCARTO (r.u. / r.i.i)X100',
        },
        RIEPILOGO: {
          TITLE: 'Riepilogo',
          TOTrii: 'TOT r.i.i',
          TOTmat: 'TOT mat.',
          TOTrru: 'TOT r.r.u.',
          TOTru: 'TOT r.u.',
        },
      }
    },
    BUTTON: {
      NUOVA_LABEL:'Inserisci nuova dichiarazione MR'
    }
  },
  RICHIESTA_MR: {
    NUOVA_RICHIESTA: 'Inserisci nuova Richiesta di ammissione al pagamento in misura ridotta',
    RICERCA: {
      TITLE: 'Ricerca Richieste Pagamento Misura Ridotta',
      NUOVA_RICHIESTA: 'Inserisci nuova Richiesta di ammissione al pagamento in misura ridotta',
    },
    LISTA: {
      TITLE: 'Risultato ricerca R-MR',
      TABLE: {
        COLUMNS: {
          STATO: 'Stato R-MR',
        }
      },
      BUTTON: {
        VISUALIZZA:'Visualizza dati della richiesta MR',
        SCARICA: 'Visualizza dati della richiesta MR',
        ELIMINA: 'Elimina richiesta MR'
      }
    },
    EDIT: {
      TITLE: 'Visualizza R-MR'
    },
    TABS: {
      PROCESSO: {
        PERCENTUALI: {
          TITLE: 'Percentuale costituente obiettivo da raggiungere per l\'anno in corso'
        }
      }
    },
    CREATE: {
      TITLE: 'Nuova Richiesta Pagamento misura ridotta',
      FORM: {
        BUTTON: 'Invia Richiesta',
        LABEL: 'Invia nuova richiesta MR',
        DATA: {
          LABEL: 'Data Richiesta',
          PLACEHOLDER: "Seleziona la Data Richiesta"
        },
        STATO: {
          LABEL: 'Stato Richiesta'
        },
      }
    },
    BUTTON: {
      NUOVA_LABEL:'Inserisci nuova richiesta MR'
    }
  }
};
