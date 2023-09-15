import { Component, Input } from '@angular/core';
import { AutoUnsubscribe } from '../../../../../core';
import { BaseInput } from '../../../models';

@Component(
  {
    template: ``
  }
)
export class BaseInputComponent extends AutoUnsubscribe {
  @Input() control?: BaseInput<any>;
}
