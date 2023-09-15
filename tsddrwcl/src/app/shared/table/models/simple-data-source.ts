import { Observable } from 'rxjs';
import { AbstractDataSource } from './abstract-data-source';

export declare type GetElementsFunction<T> = () => Observable<T[]>;

interface SimpleDataSourceConstructor<T> {
  observable: GetElementsFunction<T>;
}

export class SimpleDataSource<T> extends AbstractDataSource<T> {
  protected observable: GetElementsFunction<T>;
  protected tmpRows: T[];

  constructor(options: SimpleDataSourceConstructor<T>) {
    super(options);
  }

  setObservable(observable: GetElementsFunction<T>): void {
    this.observable = observable;
  }

  getElements(): Observable<T[]> {
    return this.observable();
  }
}
