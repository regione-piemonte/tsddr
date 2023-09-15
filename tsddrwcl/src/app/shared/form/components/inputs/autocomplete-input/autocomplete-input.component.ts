import { AfterViewInit, Component, Input, ViewChild } from '@angular/core';
import { AutocompleteInput } from '../../../models/inputs/autocomplete-input';
import { BaseInputComponent } from '../base-input/base-input.component';
import { NgSelectComponent } from '@ng-select/ng-select';

@Component({
  selector: 'app-autocomplete-input',
  template: `
    <ng-select
      style="overflow: visible;"
      #ngSelect
      labelForId="{{ 'input' + name }}"
      bindLabel="value"
      bindValue="id"
      [minTermLength]="control.minTermLength"
      [dropdownPosition]="'bottom'"
      [typeToSearchText]="control.typeToSearchText | translate"
      [notFoundText]="control.notFoundText | translate"
      [typeahead]="control.typeahead"
      [clearable]="control.clearable"
      [multiple]="control.multiple"
      [searchable]="control.searchable"
      [items]="(control.options | async)?.content"
      [loading]="control.loading"
      [formControl]="control"
      [placeholder]="control.placeholder | translate"
      [appColorStatus]="control.colorStatus"
      [compareWith]="control.compareWith"
      [appendTo]="'body'"
    >
    </ng-select>
  `
})
export class AutocompleteInputComponent
  extends BaseInputComponent
  implements AfterViewInit
{
  @Input() control: AutocompleteInput;
  @Input() name: string;

  @ViewChild('ngSelect') ngSelect: NgSelectComponent;

  ngAfterViewInit() {
    if (this.control.forceFocus) {
      setTimeout(() => {
        this.ngSelect.focus();
      }, 500);
    }
  }
}

/* <ng-select
      bindLabel="label"
      [minTermLength]="control.minTermLength"
      [typeToSearchText]="control.typeToSearchText | translate"
      [notFoundText]="control.notFoundText | translate"
      [typeahead]="control.typeahead"
      [clearable]="control.clearable"
      [loading]="control.loading"
      [items]="control.options | async"
      [formControl]="control"
      [placeholder]="control.placeholder | translate"
      [appColorStatus]="control.colorStatus"
    >
    </ng-select> */
