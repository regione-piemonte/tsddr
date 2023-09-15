import { Component, Input } from '@angular/core';
import { AutocompleteWithAddButtonInput } from 'app/shared/form/models/inputs/autocompleteWithAddButton-input';
import { BaseInputComponent } from '../base-input/base-input.component';

@Component({
    selector: 'app-autocomplete-add-btn-input',
    template: `
        <div class="row">
            <div class="col-10">
                <ng-select
                    bindLabel="denominazione"
                    bindValue="id"
                    labelForId="{{ 'input' + name }}"
                    [minTermLength]="control.minTermLength"
                    [typeToSearchText]="control.typeToSearchText | translate"
                    [notFoundText]="control.notFoundText | translate"
                    [typeahead]="control.typeahead"
                    [dropdownPosition]="'bottom'"
                    [clearable]="control.clearable"
                    [multiple]="control.multiple"
                    [searchable]="control.searchable"
                    [items]="control.options | async"
                    [loading]="control.loading"
                    [formControl]="control"
                    [placeholder]="control.placeholder | translate"
                    [appColorStatus]="control.colorStatus"
                >
                </ng-select>
            </div>
            <div class="col-2 p-0">
                <button class="btn btn-sm" (click)="addClickHandler()">
                    <app-icon
                        [name]="'eng-edit'"
                        [type]="'eng'"
                        [cssClass]="'cursor-pointer'"
                        [size]="'small'"
                    ></app-icon>
                </button>
            </div>
        </div>
    `,
})
export class AutocompleteWithAddButtonInputComponent extends BaseInputComponent {
    @Input() control: AutocompleteWithAddButtonInput;
    @Input() name: string;

    addClickHandler(): void {
        const dialog = this.control.addFn();
        if (typeof dialog === 'object') {
            dialog.closed.subscribe((response) => {
                if (response.success) {
                    this.control.setOptions(response.observable);
                }
            });
        }

    }
}
