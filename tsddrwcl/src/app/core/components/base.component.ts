import { AutoUnsubscribe } from './auto-unsubscribe.component';
import { Component } from '@angular/core';

@Component(
  {
    template: '',
  },
)
// tslint:disable-next-line:component-class-suffix
export abstract class BaseComponent extends AutoUnsubscribe {
  constructor() {
    super();
  }
}
