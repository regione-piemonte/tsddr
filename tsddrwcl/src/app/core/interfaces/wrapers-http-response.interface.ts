interface WrapperResponse<T> {
  timestamp: number;
  status: number;
  exceptionCode: number;
  exceptionMessage: string;
  path: string;
  result: T & WrapperResult;
}

interface WrapperResult {
  className: string;
}

export { WrapperResponse };
