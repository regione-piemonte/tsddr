import { Directive } from '@angular/core';
import { ColorStatusDirective } from '../../directives';

@Directive({
  selector: '[appColorStatus]'
})
export class FormColorStatusDirective extends ColorStatusDirective {}
