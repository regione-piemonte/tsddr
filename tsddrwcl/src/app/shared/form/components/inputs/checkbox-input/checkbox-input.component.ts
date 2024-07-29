import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CheckboxInput } from '../../../models/inputs/checkbox-input';
import { BaseInputComponent } from '../base-input/base-input.component';

@Component({
  selector: 'app-checkbox-input',
  styles: ['input[type="checkbox"] {left: 0 !important; position: relative !important}' ],
  template: `
    <div class="form-check">

 <label  class="mr-1" for="{{ 'input' + name }}" style=" vertical-align: middle !important; line-height:1em !important">
      <span [class]="showLabel? 'custom-label font-size-16 mr-1' : 'visually-hidden' ">{{
        control?.label || label | translate
      }}
      </span>
      </label>
    <input style='height: 1em !important; width:1em !important'
        *ngIf="control; else noControl"
        id="{{ 'input' + name }}"
        type="checkbox"
        [formControl]="control"
      />

      <ng-template #noControl>
        <input style='height: 1em !important; width:1em !important'
          id="{{ 'input' + name }}"
          type="checkbox"
          [checked]="checked"
          [indeterminate]="indeterminate"

          (change)="change.emit($event)"
          [disabled]=disable
        />
      </ng-template>


    </div>
  `,
})
export class CheckboxInputComponent extends BaseInputComponent {
  @Input() name: string;
  @Input() control?: CheckboxInput;
  @Input() checked: boolean;
  @Input() indeterminate: boolean;
  @Input() disable?: boolean;
  @Input() label = '';
  @Input() showLabel : boolean= true;

  // tslint:disable-next-line: no-output-native
  @Output() change: EventEmitter<any> = new EventEmitter();
}
