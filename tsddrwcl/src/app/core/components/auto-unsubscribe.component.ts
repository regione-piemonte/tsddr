import { Component, OnDestroy } from '@angular/core';
import { Subject } from 'rxjs';

@Component(
  {
    template: ''
  }
)
// tslint:disable-next-line:component-class-suffix
export abstract class AutoUnsubscribe implements OnDestroy {
  destroy$: Subject<boolean> = new Subject<boolean>();

  constructor() {
    //This is intentional
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }
}
