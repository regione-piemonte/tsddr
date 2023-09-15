import { ApiFilterRequest } from '@app/core';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TablePage } from './table-page';
import { AbstractDataSource } from './abstract-data-source';
import { TableSort } from './table-sort';
import { SortDirection, SortType } from '@swimlane/ngx-datatable';

export declare type ServerApiFunction<T> = () => Observable<T[]>;

interface DataSourceConstructor<T> {
  tablePage?: TablePage;
  tableSort?: TableSort[];
  observable: any;
  filters?: ApiFilterRequest[] | any;
}

export class LocalPagedDataSource<T> extends AbstractDataSource<T> {
  protected observable: ServerApiFunction<T>;
  protected localRows: T[] = [];
  protected localFilter: any;
  protected localSort: any;

  constructor(options: DataSourceConstructor<T>) {
    super(options);
  }

  getElements(): Observable<T[]> {
    const start = this.tablePage.size * (this.tablePage.pageNumber - 1);
    const end = this.tablePage.size * this.tablePage.pageNumber;

    if (
      this.localRows.length > 0 &&
      this.filter === this.localFilter &&
      this.tableSort === this.localSort
    ) {
      return of(this.localRows.slice(start, end) || []);
    }

    this.localFilter = this.filter;
    this.localSort = this.tableSort;

    return this.observable().pipe(
      map((res: T[]) => {
        this.tablePage.totalElements = res?.length || 0;
        this.localRows = res;
        this.tableSort.forEach((sort) => {
          this.localRows.sort((a, b) => sort.sortFn(a, b, sort.dir));
        });
        return this.localRows.slice(start, end) || [];
      })
    );
  }

  refresh(): void {
    this.localRows = [];
    this.localFilter = {};
    super.refresh();
  }
}
