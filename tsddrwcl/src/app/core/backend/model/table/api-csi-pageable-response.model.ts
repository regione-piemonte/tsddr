import { ApiResponseError } from './api-pageable-response.model';

export interface ApiCSIPageableResponse<T> extends Partial<ApiResponseError> {
  code: 'KO' | 'OK';
  error: string;
  result: {
    content: T[],
    first: boolean,
    last: boolean,
    totalElements: number,
    totalPages: number,
    size: number,
    sort: ApiCSISort[],
    numberOfElements: number
  };
}

export interface ApiCSISort {
  direction: 'ASC' | 'DESC';
  property: string;
  ignoreCase: boolean;
  nullHandling: 'NATIVE' | 'NULLS_FIRST' | 'NULLS_LAST';
  descending: boolean;
  ascending: boolean;
}
