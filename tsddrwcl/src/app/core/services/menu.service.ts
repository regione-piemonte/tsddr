import { Injectable } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { MenuItem } from '@app/core';
import { filter } from 'rxjs/operators';



/*
  Servizio per il menu a cui sottoscriversi 
  per la navigazione utente tra le diverse 
  voci di menu
*/


@Injectable({
  providedIn: 'root'
})
export class MenuService {

  public subject = new Subject<any>();
  private status = true;
  private current;

  constructor(private route: ActivatedRoute,
              private router: Router) {
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe(
      (navigationEnd: NavigationEnd) => {
        this.changeCurrent(navigationEnd.url);
      }
    );
  }

  open(): void {
    this.status = true;
    this.subject.next({ menuStatus: this.status, currentMenu: this.current });
  }

  close(): void {
    this.status = false;
    this.subject.next({ menuStatus: this.status, currentMenu: this.current });
  }

  changeCurrent(menu) {
    this.current = menu;
    this.subject.next({ menuStatus: this.status, currentMenu: this.current });
  }

  initRoute(menuItem: Array<MenuItem>) {
    menuItem.forEach(item => {
      if (item.link === this.route.snapshot['_routerState'].url || item.link.indexOf(this.route.snapshot['_routerState'].url) >= 0) {
        this.changeCurrent(this.route.snapshot['_routerState'].url);
      }
    });
  }

  getMenuStatus(): Observable<any> {
    return this.subject.asObservable();
  }

}
