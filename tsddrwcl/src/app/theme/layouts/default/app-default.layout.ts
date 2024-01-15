import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AutoUnsubscribe, MenuService } from '@app/core';
import { LoadingService } from '@theme/layouts/loading.services';
import { Location, LocationStrategy } from '@angular/common';
@Component({
  selector: 'app-default-layout',
  styleUrls: ['./app-default.layout.scss'],
  templateUrl: './app-default.layout.html'
})
export class AppDefaultLayoutComponent extends AutoUnsubscribe {
  menuStatus: boolean = true;

  constructor(
    public loadingService: LoadingService,
    private menu: MenuService,

    private location: Location,
    public locationStrategy: LocationStrategy
  ) {
    super();

    this.menu.getMenuStatus().subscribe((data) => {
      this.menuStatus = data.menuStatus;
    });
  }
}
