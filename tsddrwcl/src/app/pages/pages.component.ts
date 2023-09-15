import { Component } from '@angular/core';
import { AutoUnsubscribe, MenuService } from '../core';
import { filter } from 'rxjs/operators';
import { ActivatedRouteSnapshot, ActivationStart, ChildActivationStart, Router } from '@angular/router';
import { LoadingService } from '@theme/layouts/loading.services';
import { PagesMenuService } from '@pages/pages-menu.service';
import { BroadcastEventService } from '@app/core/services/broadcast-event.service';
import { Subscription } from 'rxjs';
import { ModalHelperService } from '@app/shared/modal/modal-helper.service';

@Component({
  template: `
    <app-default-layout>
      <app-menu [menuItems]='pagesMenu.items'></app-menu>
      <router-outlet></router-outlet>
    </app-default-layout>
  `
})
export class PagesComponent extends AutoUnsubscribe {
  sidebarExpanded = false;
  menuStatus: boolean = true;
  private broadcastEventSubscription: Subscription = new Subscription;


  constructor(public loadingService: LoadingService,
              private router: Router,
              public pagesMenu: PagesMenuService,
              private modalHelperService: ModalHelperService,
              private menuService: MenuService) {
    super();
    this._routerOnChangeListen();
    this.menuService.getMenuStatus().subscribe(data => {
    
      this.menuStatus = data.menuStatus;
    });

    modalHelperService.init();

  }

  private _routerOnChangeListen(): void {
    this.router.events
      .pipe(
        filter(
          (event) =>
            (event instanceof ActivationStart ||
              event instanceof ChildActivationStart) &&
            (this._routeHasResolve(event.snapshot) ||
              this._routeHasLoadingStart(event.snapshot))
        )
      )
      .subscribe((e) => {
        this.loadingService.show();
      });
  }

  private _routeHasLoadingStart(snapshot: ActivatedRouteSnapshot): boolean {
    return !!snapshot.routeConfig?.data?.loading?.start;
  }

  private _routeHasResolve(snapshot: ActivatedRouteSnapshot): boolean {
    return !!(
      snapshot.routeConfig?.resolve &&
      Object.keys(snapshot.routeConfig?.resolve).length
    );
  }

}
