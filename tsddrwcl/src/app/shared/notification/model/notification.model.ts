import { NotificationPosition, Status } from '../../../core/enums';

export interface NotificationModel {
  /*
   * Notification Id
   * */
  id?: string;
  /*
   * Posizione della notifica
   * */
  position?: NotificationPosition;
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
  /*
   * Time
   * */
  time?: number;
  /*
   * Template
   * */
  template?: any;
}
