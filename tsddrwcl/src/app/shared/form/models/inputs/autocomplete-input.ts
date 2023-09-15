import { concat, Observable, of, Subject } from 'rxjs';
import {
  catchError,
  debounceTime,
  distinctUntilChanged,
  switchMap,
  tap
} from 'rxjs/operators';
import { BaseInput, BaseInputConstructor } from './base-input';
import { SelectOption } from './select-input';

export interface Typeahead<T, E> {
  options$: Observable<SelectOption<T, E>[]>;
  typeahead$: Subject<string>;
}

export type TypeaheadMethod<T, E> = (
  search: string
) => Observable<SelectOption<T, E>[]>;

export interface AutocompleteInputConstructor<T, E>
  extends BaseInputConstructor<T> {
  typeaheadMethod?: TypeaheadMethod<T, E>;
  typeToSearchText?: string;
  notFoundText?: string;
  loading?: boolean;
  searchable?: boolean;
  multiple?: boolean;
  minTermLength?: number;
  options?: Observable<SelectOption<T, E>[]>;
  absolute?: boolean;
  compareWith?: (item: any, selected: any) => boolean;
}

export class AutocompleteInput<T = string, E = string> extends BaseInput<T> {
  inputType = 'autocomplete';

  readonly absolute: boolean = false;
  readonly options: Observable<SelectOption<T, E>[]>;
  readonly typeahead: Subject<string>;
  readonly typeaheadMethod?: TypeaheadMethod<T, E>;
  readonly typeToSearchText?: string = 'UTILS.AUTOCOMPLETE.TYPE_TO_SEARCH';
  readonly notFoundText?: string = 'UTILS.AUTOCOMPLETE.ITEMS_NOT_FOUND';
  readonly loading?: boolean;
  readonly searchable?: boolean = true;
  readonly multiple?: boolean;
  readonly minTermLength?: number = 0;
  readonly compareWith?: (item: any, selected: any) => boolean;

  constructor(options: AutocompleteInputConstructor<T, E>) {
    super(options);

    if (options.options) {
      this.options = options.options;
    } else if (options.typeaheadMethod) {
      this.typeaheadMethod = options.typeaheadMethod;

      ({ typeahead$: this.typeahead, options$: this.options } = this._typehead(
        this.typeaheadMethod
      ));
    }
    this.multiple = options.multiple;
    if (options.compareWith) {
      this.compareWith = options.compareWith;
    }

    if (options.absolute) {
      this.absolute = options.absolute;
    }
  }

  setOptions(options: Observable<SelectOption<T, E>[]>): void {
    (this as { options: any }).options = options;
  }

  private _typehead(fn: TypeaheadMethod<T, E>): Typeahead<T, E> {
    const typeahead$: Subject<string> = new Subject<string>();

    const options$: Observable<SelectOption<T, E>[]> = concat(
      of([]),
      typeahead$.pipe(
        debounceTime(500),
        distinctUntilChanged(),
        tap(() => ((this as { loading: boolean }).loading = true)),
        switchMap((search: string) => {
          if (search && search.length > 0) {
            (this as { notFoundText: string }).notFoundText =
              'UTILS.AUTOCOMPLETE.ITEMS_NOT_FOUND';

            return fn(search).pipe(
              catchError(() => of([])),
              tap(() => ((this as { loading: boolean }).loading = false))
            );
          }
          (this as { notFoundText: string }).notFoundText =
            'UTILS.AUTOCOMPLETE.LOADING';
          (this as { loading: boolean }).loading = false;
          return of([]);
        })
      )
    );

    return { options$, typeahead$ };
  }
}
