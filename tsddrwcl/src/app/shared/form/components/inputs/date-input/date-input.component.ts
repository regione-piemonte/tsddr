import {
  ChangeDetectorRef,
  Component,
  ComponentFactoryResolver,
  Input,
  ViewChild
} from '@angular/core';
import { DatepickerHostDirective } from '../../../directives';
import { DateInput } from '../../../models/inputs/date-input';
import { BaseInputComponent } from '../base-input/base-input.component';

@Component({
  selector: 'app-date-input',
  template: `<input
    class="custom-input  shape-rectangle size-large nb-transition input-full-width"
    id="{{ 'input' + name }}"
    type="date"
    [class.hide-date]="control.readonly && !control.value"
    [appColorStatus]="control.colorStatus"
    [formControl]="control"
  />`
})
export class DateInputComponent extends BaseInputComponent {
  @ViewChild(DatepickerHostDirective) datepickerHost: DatepickerHostDirective;
  @Input() control: DateInput;
  @Input() name: string;

  constructor(
    private componentFactoryResolver: ComponentFactoryResolver,
    private cdr: ChangeDetectorRef
  ) {
    super();
  }
}
