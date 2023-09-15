import { Injectable } from '@angular/core';
import { ICombo } from '../interfaces/api-mr.model';
import { Observable, of } from 'rxjs';
import { MrService } from './mr.service';
import { tap } from 'rxjs/operators';
import { I18nService } from '@app/core/translate/public-api';
import { IPrevConsDettEntity } from '../interfaces/prev-cons.interface';

@Injectable({
  providedIn: 'root'
})
export class TabsRifiutiService {
  private comboEerArray: ICombo = null;

  constructor(private mrService: MrService,
              private i18n: I18nService,) {}

  getComboEerObservable(): Observable<ICombo> {
    // se non ho il comboarray lo popolo, altrimenti mi torno quello che già ho
    if (this.comboEerArray === null) {
      return this.mrService
        .getComboEer()
        .pipe(tap((res) => (this.comboEerArray = res)));
    }
    return of(this.comboEerArray);
  }

  get comboEer(): ICombo {
    return this.comboEerArray;
  }

  /**
   * @description per aggiornare gli id locali dei prevConsDett
   */
  createIdCountPrevConsDett(prevConsDett: IPrevConsDettEntity[]): number{
    return !prevConsDett.length ? 1 : prevConsDett.map(prevConsDett => prevConsDett.idPrevConsDett).sort((a,b) => a - b )[prevConsDett.length - 1] + 1;
  }

  /**
   * @description metodo per aggiornare i totali del tab
   */
  setTotali(selectedPrevConsDett: IPrevConsDettEntity[]): number{
    const total = selectedPrevConsDett.map(items => items.quantita).reduce((previousValue, currentValue) => previousValue + currentValue, 0);
    return total;
  }

  /**
   * @description rimuove la prevConsDett[] selezionata dalla memoria locale e restituisce l'array aggiornato
  */
  removePrevConsDett(current: IPrevConsDettEntity[], toDelete: IPrevConsDettEntity): IPrevConsDettEntity[]{
    /*ho l'array di prevConsDett completo su cui sto lavorando + la dett che devo eliminare
      creo un nuovo array con tutti gli elementi precedenti meno quello che voleto togliere
    */
   return current.filter(items => items.idPrevConsDett != toDelete.idPrevConsDett);
  }

  /**
   * @description aggiorna una prevConsDett modificata nella memoria locale e restituisce l'array aggiornato
  */
  updatePrevConsDett(current: IPrevConsDettEntity[], toUpdate: IPrevConsDettEntity) : IPrevConsDettEntity[]{
    return current.map((item, index, array) => {
      if(item.idPrevConsDett === toUpdate.idPrevConsDett){
        return array[index] = {...array[index], ...toUpdate};
      }
      return item;
    });
  }

}
