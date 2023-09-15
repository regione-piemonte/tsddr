export class ModalOptions {
  /*
   * Titolo header
   */
  header?: string;

  /*
   * Abilita o disabilita il close button sull'header
   */
  showCloseButton?: boolean;

  /*
   * Size della modale
   */
  sizeModal?: string;

  /*
   * Scrollable
   */
  scrollable ? = true;

  /*
   * Posizione centrale
   */
  centered ? = true;

  /*
   * Oggetto per passaggio input sul body della modale
   */
  context?: any;
}
