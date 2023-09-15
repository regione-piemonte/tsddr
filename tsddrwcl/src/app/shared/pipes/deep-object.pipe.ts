import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'deepObject' })
export class DeepObjectPipe implements PipeTransform {

  transform(input: any): string {
    return input.denominazione;
  }
}
