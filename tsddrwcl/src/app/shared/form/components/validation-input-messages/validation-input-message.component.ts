import { Component, Input } from '@angular/core';
import { InternalStatusMessage } from '../../models';

@Component({
  selector: 'app-validation-input-message',
  template: `
    <small
      *ngIf='statusMessage'
      role='alert'
      aria-label='Validation error'
      [appColorStatus]='statusMessage.status'
      class='pl-1'
    ><strong
      [translate]='statusMessage.text'
      [translateParams]='statusMessage.params'
    ></strong
    ></small>
  `
})
export class ValidationInputMessagesComponent {
  @Input() statusMessage: InternalStatusMessage;
}
