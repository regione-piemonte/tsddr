import {ApiFilterRequest} from './api-filter-request';

export class CSIApiFiltersRequest {
  filters: any;
  page?: number;
  size?: number;

  constructor(options: Omit<CSIApiFiltersRequest, 'toServer' | 'removeFilter'>) {
    this.filters = options.filters;
    this.page = options.page;
    this.size = options.size;
  }

  removeFilter(fieldFilterName: string): void {
    const index = this.filters.findIndex((f) => f.field === fieldFilterName);
    this.filters.splice(index, 1);
  }

  toServer(): {
    filters: ApiFilterRequest[];
    page?: number;
    size?: number;
  } {
    if (this.page && this.size) {
      return {filters: this.filters, page: this.page, size: this.size};
    }
    if (this.page) {
      return {filters: this.filters, page: this.page};
    }
    return {filters: this.filters};
  }
}
