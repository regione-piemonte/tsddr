import { IPrevConsDettEntity, IPrevConsLineeEntity } from "../../interfaces/prev-cons.interface";

export class PrevConsLineeClass implements IPrevConsLineeEntity {

  idImpiantoLinea?: number;
  codLinea?: string;
  codSottoLinea?: string;
  descLinea?: string;
  descSottoLinea?: string;
  descSommaria?: string;
  hasBeenUpdated?: boolean;
  idPrevCons?: number;
  idPrevConsLinee?: number;
  percRecupero?: number;
  percScarto?: number;
  prevConsDett?: (IPrevConsDettEntity)[] | null;
  totRii?: number;
  totMat?: number;
  totRru?: number;
  totRu?: number;

  constructor(prevConsLinee: IPrevConsLineeEntity[] = []){
     //This is intentional
   }

  /**
   * @description aggiorno il valore della classe in base all'idImpiantoLinea di una prevConsLinea selezionata
   */
  setSelectedPrevConsLinea(linee: IPrevConsLineeEntity[], idImpiantoLinea?: number): IPrevConsLineeEntity {
    if (idImpiantoLinea){
      //aggiorno il valore della classe con l'idImpiantoLinea corrispondente
      return linee.find(linea => linea.idImpiantoLinea == idImpiantoLinea);
    } else if (linee){
      //se non ho valori, uso il primo che mi arriva, in caso di prima visualizzazione
      return linee[0];
    }
  }

}
