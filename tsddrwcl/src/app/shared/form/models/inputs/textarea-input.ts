import { BaseInput, BaseInputConstructor } from './base-input';

export interface TextareaConstructor extends BaseInputConstructor {
  rows?: number;
}

export class TextareaInput extends BaseInput {
  inputType = 'textarea';
  rows?: number;

  constructor(options: TextareaConstructor) {
    super(options);
    this.rows = options.rows || 5;
  }
}
