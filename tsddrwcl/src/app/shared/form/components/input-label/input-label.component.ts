import { Component, Input } from '@angular/core';
import { BaseInput } from '../../models';

@Component({
  selector: 'app-input-label',
  template: `
      <label
            for="{{ 'input' + name }}"
            class="label custom-label font-size-16 mb-0"
            [class.font-weight-bold]="field.required"
            [class.font-weight-normal]="!field.required"
            style="text-align: left; position: relative"
            *ngIf="
        field.label &&
        field.inputType !== 'checkbox' &&
        field.inputType !== 'fieldset'
      "
      >{{ field.label | translate }}
          <span *ngIf="field.required && !filter" class="font-weight-light">
        {{ 'UTILS.FORM.MANDATORY' | translate }}
      </span></label
      >
  `,
})
export class InputLabelComponent {
  @Input() name: string;
  @Input() field: BaseInput;
  @Input() filter: boolean;
}
