export declare type NbComponentStatus =
  | 'basic'
  | 'primary'
  | 'success'
  | 'warning'
  | 'danger'
  | 'info'
  | 'control';

export type StatusMessage = Omit<InternalStatusMessage, 'status'>;

export class InternalStatusMessage {
  /**
   * The text message for the error (it's possible use i18n)
   */
  text: string;

  /**
   * Placeholder for i18n messages
   */
  params?: any;

  /**
   * The color of label
   */
  status: NbComponentStatus;

  constructor(status: NbComponentStatus, text: string, params?: any) {
    this.status = status;
    this.text = text;
    this.params = params;
  }
}
