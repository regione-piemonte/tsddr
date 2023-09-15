import { BaseInput, BaseInputConstructor } from './base-input';

export type TextInputType =
  | 'text'
  | 'url'
  | 'email'
  | 'tel'
  | 'password'
  | 'number'
  | 'search'
  | 'date';

export interface TextInputConstructor extends BaseInputConstructor {
  type: TextInputType;
}

export class TextInput extends BaseInput {
  inputType = 'textbox';
  type: TextInputType;

  constructor(options: TextInputConstructor) {
    super(options);
    this.type = options.type;
  }
}
