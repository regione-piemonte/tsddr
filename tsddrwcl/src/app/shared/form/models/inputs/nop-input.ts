import { BaseInput, BaseInputConstructor } from './base-input';

export interface NopConstructor<T, E>
  extends Omit<BaseInputConstructor<T>, 'label'> {}

export class NopInput<T = string, E = string> extends BaseInput<T> {
  inputType = 'nop';

  constructor(options: NopConstructor<T, E>) {
    super(options);
  }
}
