import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { TranslateModule } from '@eng-ds/translate';
import { NgSelectModule } from '@ng-select/ng-select';
import {
  AutocompleteInputComponent,
  AutocompleteWithAddButtonInputComponent,
  AutocompleteWithInsertInputComponent,
  CheckboxInputComponent,
  DateInputComponent,
  DividerInputComponent,
  FormComponent,
  FormInputComponent,
  FormInputsComponent,
  RadiosInputComponent,
  SelectInputComponent,
  TextareaInputComponent,
  TextboxInputComponent,
  ValidationInputMessagesComponent
} from '@shared/form/components';
import {
  DatepickerHostDirective,
  FormColorStatusDirective
} from '@shared/form/directives';
import { InputLabelComponent } from '@shared/form/components/input-label/input-label.component';
import { NopInputComponent } from '@shared/form/components/inputs/nop-input/nop-input.component';
import { FileInputComponent } from '@shared/form/components/inputs/file-input/file-input.component';
import { ThemeModule } from '@theme/theme.module';
import { SharedComponentsModule } from '@shared/components/shared-components.module';

@NgModule({
  declarations: [
    FormInputsComponent,
    FormInputComponent,
    FormComponent,
    TextboxInputComponent,
    SelectInputComponent,
    NopInputComponent,
    TextareaInputComponent,
    DateInputComponent,
    FileInputComponent,
    CheckboxInputComponent,
    RadiosInputComponent,
    AutocompleteInputComponent,
    AutocompleteWithAddButtonInputComponent,
    AutocompleteWithInsertInputComponent,
    ValidationInputMessagesComponent,
    InputLabelComponent,
    DatepickerHostDirective,
    FormColorStatusDirective,
    DividerInputComponent
  ],
  exports: [
    ReactiveFormsModule,
    FormComponent,
    FormInputsComponent,
    InputLabelComponent,
    ValidationInputMessagesComponent,
    CheckboxInputComponent,
    AutocompleteInputComponent,
    AutocompleteWithAddButtonInputComponent,
    AutocompleteWithInsertInputComponent,
    DateInputComponent,
    DividerInputComponent,
    FormComponent,
    FormInputComponent,
    FormInputsComponent,
    RadiosInputComponent,
    SelectInputComponent,
    TextareaInputComponent,
    TextboxInputComponent
  ],
  imports: [
    ThemeModule,
    ReactiveFormsModule,
    TranslateModule.forChild(),
    SharedComponentsModule,
    NgSelectModule
  ],
  entryComponents: []
})
export class FormModule {}
