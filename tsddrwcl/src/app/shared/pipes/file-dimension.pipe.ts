import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'fileDimension' })
export class FileDimensionPipe implements PipeTransform {
  transform(input: number): string {
    // from byte to Mb with 3 decimals
    return input
      ? (Math.round(input / 1000) / 1000).toString() + ' ' + 'Mb'
      : input.toString();
  }
}
