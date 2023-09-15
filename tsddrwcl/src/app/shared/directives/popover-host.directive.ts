// tslint:disable: directive-selector
import { Directive, ViewContainerRef } from '@angular/core';

@Directive({
  selector: '[popverHost]',
})
export class PopoverHostDirective {
  constructor(public viewContainerRef: ViewContainerRef) {}
}
