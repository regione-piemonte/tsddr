import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'deepObject',
  pure: true,
})
export class DeepObjectPipe implements PipeTransform {
  /*
  transform(value: any, args?: any): any {
    const arr = args.split('.');
    // let tmp = value;
    const tmp = value;
    // while (arr.length && (tmp = tmp[arr.shift()])) { }
    while (arr.length && (tmp === tmp[arr.shift()])) { }
    return tmp;
  }
  */

  transform(value: any, args?: any): any {
    let tmp = value;
    let arr = [];
    if (args) {
      arr = args.split('.');
    }
    while ( arr.length ) {
      tmp = tmp[arr.shift()];
    }
    return tmp;
  }
}
