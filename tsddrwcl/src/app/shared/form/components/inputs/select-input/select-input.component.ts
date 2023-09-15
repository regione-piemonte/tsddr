import { Component, Input } from '@angular/core';
import { SelectInput } from '../../../models/inputs/select-input';
import { BaseInputComponent } from '../base-input/base-input.component';

@Component({
  selector: 'app-select-input',
  template: `
    <select [formControl]="control" class="custom-input shape-rectangle size-large nb-transition input-full-width">
     <option
        *ngFor="let option of (control.options | async)?.content"
        [value]="option.id"
        >{{ option.value | translate }}</option
      >
    </select>
  `,
})
export class SelectInputComponent extends BaseInputComponent {
  @Input() control: SelectInput;
}

// <nb-select
//       fullWidth
//       size="large"
//       [status]="control.colorStatus"
//       [formControl]="control"
//       [placeholder]="control.placeholder | translate"
//     >
//       <nb-option
//         *ngFor="
//           let option of control.options
//             | async
//             | addClearOption: control.clearable
//         "
//         [value]="option.value"
//         >{{ option.label | translate }}</nb-option
//       >
//     </nb-select>
