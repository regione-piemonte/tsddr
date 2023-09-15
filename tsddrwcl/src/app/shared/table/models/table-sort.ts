export class TableSort {
  dir: 'desc' | 'asc';
  prop: string;
  sortFn?: any;

  constructor(options: Partial<TableSort>) {
    this.dir = options.dir;
    this.prop = options.prop;
    this.sortFn = options.sortFn;
  }
}
