import { IPrevConsDettEntity } from './../../interfaces/prev-cons.interface';
import { ImpiantoMr } from './../../interfaces/api-mr.model';
import { IGetImpiantoLinee, IPrevCons, IStatoPrevCons, ITipoDoc, IPrevConsLineeEntity } from "../../interfaces/prev-cons.interface";
import { Indirizzo } from '@app/pages/impianti/models/impianto.model';
import { STATO_PREV_CONS } from '../constants';

export class PrevConsClass implements IPrevCons {

  idPrevCons: number;
  idPrevConsRMr?: number;
  prevConsRichiesta?: IPrevCons;
  annoTributo: number;
  dataDoc: string;
  dataInvioDoc?: string;
  modalita?: string;
  richiestaAmmissioneHasBeenUpdated?: boolean;
  numProtocollo?: string;
  prevConsLinee: (IPrevConsLineeEntity)[] | null;
  statoDichiarazione?: IStatoPrevCons;
  tipoDoc: ITipoDoc;
  impianto: ImpiantoMr;
  indirizzo?: Indirizzo;
  numProtDoc?: any;

  idCount = 0;


  constructor(
    idTipoDoc: number,
  ){
    // lo setto a null, se sono in modifica o visualizzazione verrà poi settato correttamente
    this.idPrevCons = null;
    this.tipoDoc = { idTipoDoc: idTipoDoc };
  }

  /**
   * @description in caso di modifica/visualizzazione imposta i valori della mia classe dalla prevCons ricevuta
   */
  setValueFromPrevCons(prevCons: IPrevCons): void{
    this.idPrevCons = prevCons.idPrevCons;
    this.idPrevConsRMr = prevCons.idPrevConsRMr ?? null;
    this.prevConsRichiesta = prevCons.prevConsRichiesta ?? null;
    this.annoTributo = prevCons.annoTributo;
    this.dataDoc = prevCons.dataDoc;
    this.dataInvioDoc = prevCons.dataInvioDoc ?? null;
    this.modalita = prevCons.modalita ?? null;
    this.numProtDoc = prevCons.numProtDoc ?? null;
    this.numProtocollo = prevCons.numProtocollo ?? null;
    this.statoDichiarazione = prevCons.statoDichiarazione ?? null;
    this.impianto = prevCons.impianto ?? null;
    this.indirizzo = prevCons.indirizzo ?? null;

    if(!prevCons.idPrevConsRMr){
      this.prevConsLinee = this.setPrevConsLineeEdit(prevCons.lineeImpianto, prevCons.prevConsLinee);
    } else if(prevCons.statoDichiarazione.idStatoDichiarazione == STATO_PREV_CONS.INVIATA) {
      // sono in visualizzazione, lascio solo le linee popolate nella dichiarazione, rimuovo le dett della richiesta slegate dalla dichiarazione (quindi quelle senza idPrevConsDett)
      this.prevConsLinee = this.setprevConsLineeView(prevCons.prevConsLinee);
    } else {
      this.prevConsLinee = this.setLinesFromRmr(prevCons.prevConsRichiesta.prevConsLinee, prevCons.prevConsLinee);
    }
  }

  /**
   * @description in caso di inserimento di una D-MR legato ad una R-MR imposta i valori della mia classe dalla prevCons ricevuta
   */
  setDichiarazioneFromRichiesta(rmr: IPrevCons): void{
    this.idPrevConsRMr = rmr.idPrevCons;
    this.prevConsRichiesta = rmr;
    this.prevConsLinee = [...rmr.prevConsLinee];
    this.impianto = rmr.impianto ?? null;
  }

  /**
   * @description nell'inserimento richiesta e dichiarazione non legata a RMR mappo tutti gli idImpiantoLinea e popolo il mio array
   */
  setPrevConsLineeInsert(list: IGetImpiantoLinee[]): IPrevConsLineeEntity[] {
    return list.map((item) => {
      let newLine: IPrevConsLineeEntity = {
        idImpiantoLinea: item.idImpiantoLinea,
        prevConsDett: [],
        idPrevConsLinee: --this.idCount
      }
      if(item.codLinea) {
        newLine.codLinea = item.codLinea;
      }
      if(item.codSottoLinea) {
        newLine.codSottoLinea = item.codSottoLinea;
      }
      return newLine;
    });
  }

  /**
   * @description  anche nella visualizzazione e modifica semplice mi arrivano linee vuote e devo creare linee finte per non spaccare le tables
                   aggiorno il mio array con le linee già popolata + le altre corrispondenze da poter lavorare
   */
  setPrevConsLineeEdit(list: IGetImpiantoLinee[], currentPrevConsLinee: IPrevConsLineeEntity[]): IPrevConsLineeEntity[] {
    return list.map((item) => {
      let currentLinea = currentPrevConsLinee?.find(linea => linea.idImpiantoLinea == item.idImpiantoLinea);
      if(currentLinea) {
        return currentLinea;
      } else {
        let newLine: IPrevConsLineeEntity = {
          idImpiantoLinea: item.idImpiantoLinea,
          prevConsDett: [],
          idPrevConsLinee: --this.idCount
        }
        if(item.codLinea) {
          newLine.codLinea = item.codLinea;
        }
        if(item.codSottoLinea) {
          newLine.codSottoLinea = item.codSottoLinea;
        }
        return newLine;
      }});
  }

  /**
   * @description azzero i dati della linea selezionata
   */
  private _setPrevConsLineeDelete(currentPrevConsLinea: IPrevConsLineeEntity): IPrevConsLineeEntity {
    return {
      ...currentPrevConsLinea,
      descSommaria: null,
      prevConsDett: [],
      idPrevConsLinee: --this.idCount,
      totMat: null,
      totRii: null,
      totRru: null,
      totRu: null,
      percRecupero: null,
      percScarto: null,
      dettRii: [],
      dettMat: [],
      dettRru: [],
      dettRu: [],
    }
  }

  /**
   * @description quando elimino una linea legata ad una richista:
   *              per i rifiuti legati alla richiesta elimino solo la proprietà quantita,
   *              per i rifiuti della dichiarazione elimino l'intero elemento,
   */
  private _setPrevConsLineeDeleteFromRmr(currentPrevConsLinea: IPrevConsLineeEntity): IPrevConsLineeEntity {

    function prevConsDettDeleteFromRmr(params: IPrevConsDettEntity[]): IPrevConsDettEntity[] {
      return params.map(dett=> ({...dett, quantita: !!dett.idPrevConsDettRmr ? null : dett.quantita}))
      .filter(dett=> !!dett.idPrevConsDettRmr) ?? [];
    }

    return {
      ...currentPrevConsLinea,
      descSommaria: null,
      prevConsDett: [],
      idPrevConsLinee: --this.idCount,
      totMat: null,
      totRii: null,
      totRru: null,
      totRu: null,
      percRecupero: null,
      percScarto: null,
      dettRii: prevConsDettDeleteFromRmr(currentPrevConsLinea.dettRii),
      dettMat: prevConsDettDeleteFromRmr(currentPrevConsLinea.dettMat),
      dettRru: prevConsDettDeleteFromRmr(currentPrevConsLinea.dettRru),
      dettRu: prevConsDettDeleteFromRmr(currentPrevConsLinea.dettRu),
    }
  }

  /**
   * @description svuota il contenuto di una prevConsLinea in memoria riportandola al formato iniziale,
   *              tramite il metodo _setPrevConsLineeDelete per le prevCons semplici,
   *              tramite il metodo _setPrevConsLineeDeleteFromRmr per le linee legate ad una richiesta,
   *              mi restituisce il nuovo array di linee aggiornato
   */
  removePrevConsLinee(current: IPrevConsLineeEntity, currentLinee: IPrevConsLineeEntity[]): IPrevConsLineeEntity[] {
    return currentLinee.map(linea => {
      if(linea.idImpiantoLinea == current.idImpiantoLinea) {
        if(linea.isLegameRmr){
          return this._setPrevConsLineeDeleteFromRmr(linea);
        } else {
          return this._setPrevConsLineeDelete(linea);
        }
      }
        return linea;
      });
  }

  /**
   * @description in caso di inserimento o modifica dichiarazione legata a R-MR devo prepopolare le linee e le prevConsDett con i dati della R-MR
   */
  setLinesFromRmr(lineeRmr: IPrevConsLineeEntity[], lineeDichiarazione: IPrevConsLineeEntity[]): IPrevConsLineeEntity[] {

    return lineeRmr.map((item) => {
      let currentLinea = lineeDichiarazione?.find(linea => linea.idImpiantoLinea == item.idImpiantoLinea);
      if(currentLinea) {
        //inserisco i dati mancanti alle dett legate a rmr ma non ancora compilate
        if(currentLinea.dettRii.length) {
          currentLinea.dettRii = this._preparePrevConsDett(currentLinea.dettRii);
        }
        if(currentLinea.dettMat.length) {
          currentLinea.dettMat = this._preparePrevConsDett(currentLinea.dettMat);
        }
        if(currentLinea.dettRru.length) {
          currentLinea.dettRru = this._preparePrevConsDett(currentLinea.dettRru);
        }
        if(currentLinea.dettRu.length) {
          currentLinea.dettRu = this._preparePrevConsDett(currentLinea.dettRu);
        }
        currentLinea.prevConsDett = [];
        currentLinea.isLegameRmr = true;
        return currentLinea;
      } else {
        //non dovrebbe mai entrare qui, ma nel dubbio lascio questo codice
        let newLine: IPrevConsLineeEntity = {
          idImpiantoLinea: item.idImpiantoLinea,
          prevConsDett: [],
          idPrevConsLinee: --this.idCount
        }
        if(item.codLinea) {
          newLine.codLinea = item.codLinea;
        }
        if(item.codSottoLinea) {
          newLine.codSottoLinea = item.codSottoLinea;
        }
        return newLine;
      }});
  }

  setprevConsLineeView(lineeDichiarazione: IPrevConsLineeEntity[]) : IPrevConsLineeEntity[] {
    //elimino le linee che non hanno nessuna quantità valorizzata, hanno solo il legame con la richiesta
    let linee = lineeDichiarazione.filter(linea => (linea.totRii || linea.totMat || linea.totRru || linea.totRu));
    // dalle linee rimaste, elimino le prevConsDett senza id, cioè quelle della richiesta slegate dalla dichiarazione
    return linee.map(linea => {
      linea.dettRii = linea.dettRii.filter(dett=> !!dett.idPrevConsDett) ?? [];
      linea.dettMat = linea.dettMat.filter(dett=> !!dett.idPrevConsDett) ?? [];
      linea.dettRru = linea.dettRru.filter(dett=> !!dett.idPrevConsDett) ?? [];
      linea.dettRu = linea.dettRu.filter(dett=> !!dett.idPrevConsDett) ?? [];
      return linea;
    });
  }

  private _preparePrevConsDett(dett: IPrevConsDettEntity[]): IPrevConsDettEntity[] {
    let count = 0;
    return dett.map(dett => {
      if(dett.idPrevConsDett) {
        return dett;
      } else {
        //se ho una dett slegata dalla dichiarazione, le assegno un id fittizio
        return {
          ...dett,
          idPrevConsDett: ++count,
          isRam: true,
          quantita: null
        }
      }
    })
  }

}
