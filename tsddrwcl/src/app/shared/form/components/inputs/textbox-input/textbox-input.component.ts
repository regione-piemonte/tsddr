import { AfterViewInit, Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { BaseInputComponent } from '../base-input/base-input.component';
import { TextInput } from '@shared/form/models/inputs/text-input';

@Component({
  selector: 'app-textbox-input',
  template: `
    <input
      #input
      id="{{ 'input' + name }}"
      class="custom-input shape-rectangle size-large nb-transition input-full-width"
      [appColorStatus]="control.colorStatus"
      [type]="control.clearable ? 'search' : control.type"
      [placeholder]="control.placeholder | translate"
      (blur)="blurEvent.emit(control)"
      [formControl]="control"
    />
  `
})
export class TextboxInputComponent
  extends BaseInputComponent
  implements AfterViewInit
{
  @Input() control: TextInput;

  @Input() name: string;

  @Output() blurEvent = new EventEmitter<any>();

  @ViewChild('input') input: any;

  ngAfterViewInit() {
    if (this.control.forceFocus) {
      setTimeout(() => {
        this.input.nativeElement.focus();
      }, 500);
    }
  }
}
