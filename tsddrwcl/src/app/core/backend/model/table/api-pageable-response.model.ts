export interface ApiResponse<T> extends Partial<ApiResponseError> {
  content: T;
}

export interface ApiListResponse<T> extends ApiResponse<T[]> {
}

export interface ApiPageableResponse<T> extends Partial<ApiResponseError> {
  content: T[];
  totalElements: number;
  totalPages: number;
}

export interface ApiResponseError {
  keyErrorMessage: string;
  response: {
    errorDescription: string;
  };
}
