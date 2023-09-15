import { ApiConfig } from './api-config.model';

export class BackendConfig {
  baseUrl: string;
  api: ApiConfig[];

  timeout?: number;

  constructor(backend: Partial<BackendConfig>) {
    this.baseUrl = backend.baseUrl;
    this.api = (backend.api as ApiConfig[]).map(
      (a: ApiConfig) => new ApiConfig(a)
    );
    this.timeout = backend.timeout || 3000;
  }

  getApiConfig(apiName: string): ApiConfig | null {
    let api;
    try {
      // tslint:disable-next-line:no-shadowed-variable
      api = Object.assign(
        {},
        this.api.find((config: ApiConfig) => config.name === apiName)
      );
      api.url = this.prepareUrl(api.url);
    } catch (err) {
      api = null;
    }
    return api;
  }

  /**
   * Add baseUrl as prefix if the api url is relative
   * @param url Relative api url
   */
  prepareUrl(url: string): string {
    let localUrl = `${url}`;
    if (localUrl.trim().indexOf('http') !== 0) {
      localUrl = (this.baseUrl + localUrl).trim();
    }
    return localUrl;
  }
}
