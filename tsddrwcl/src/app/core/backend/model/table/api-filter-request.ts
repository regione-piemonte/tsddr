import { Condition } from './condition';

export abstract class ApiFilterRequest implements Condition {
  field: string;
  operator: string;
  value: any;
  condition?: 'AND' | 'OR' = 'OR';

  constructor(options: Omit<ApiFilterRequest, 'operator'>) {
    this.field = options.field;
    this.value = options.value;

    if (options.condition) {
      this.condition = options.condition;
    }
  }
}
