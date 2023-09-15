import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'ngxDateConstructor' })
export class DateConstructPipe implements PipeTransform {
  transform(input: string): Date {
    return new Date(input);
  }
}
