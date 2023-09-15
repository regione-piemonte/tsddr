import { PipeTransform } from '@angular/core';

export interface TableColumn {
  /**
   * Nome della colonna
   */
  name?: string;

  /**
   * Proprietà dell'oggetto dal quale prendere il valore
   * E' possibile ussare la notazione con il punto per
   * selezionare proprietà annidate.
   * Es. prop1.prop2.prop3
   */
  prop?: string;

  /**
   * Permette di specificare un template diverso di render per la colonna
   */
  cellTemplate?: any;

  /**
   * Indica se la colonna è ordinabile
   */
  sortable?: boolean;

  /**
   * Permette di trasformare il value di una colonna
   */
  pipe?: PipeTransform;

  /**
   * Classi per la colonna
   */
  cellClass?: string | ((data: any) => string | any);

  /**
   * Larghezza della colonna in percentuale
   */
  width?: number;

  /**
   * Indica se la colonna deve contenere dei checkbox per la selezione delle righe.
   * Se true viene abilitata tutta la logica di gestione dei selezionati.
   * Da usare con l'evento sulla tabella (select)
   */
  checkboxable?: boolean;

  /**
   * Indica se la colonna deve contenere anche l'icona per espandere la riga
   */
  rowDetailIcon?: boolean;

  /**
   * funzione di sorting sulla colonna
   * @param x
   * @param y
   */
  sortFn?: (x, y, dir) => number;
}
