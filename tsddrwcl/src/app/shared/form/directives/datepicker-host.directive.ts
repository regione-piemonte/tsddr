import { Directive, ViewContainerRef } from '@angular/core';

@Directive({
  selector: '[appDatepickerHost]'
})
export class DatepickerHostDirective {
  constructor(public viewContainerRef: ViewContainerRef) {}
}
