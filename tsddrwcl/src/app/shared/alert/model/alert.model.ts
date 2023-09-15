import { Status } from '../../../core/enums';

export interface AlertModel {
  /*
  * Titolo della notifica
  * */
  title: string;
  /*
  * Testo della notifica
  * */
  text: string;
  /*
  * Dismissable
  * */
  dismissable?: boolean;
  /*
  * Status (Info, Warning, Error, Success
  * */
  status?: Status;
  /*
  * Nome Icon da visualizzare
  * */
  icon?: string;
}
