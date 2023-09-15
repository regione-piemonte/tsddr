import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem, MenuService } from '@app/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  @Input() menuItems: MenuItem[];
  public menuStatus: boolean = true;
  public currentItem: string;

  constructor(private router: Router,
              private menuService: MenuService) {
    this.menuService.getMenuStatus().subscribe(data => {
      this.menuStatus = data.menuStatus;
      this.currentItem = data.currentMenu;
      this._setOpenedLevel1();
    });
  }

  _setOpenedLevel1(): void {
    if(this.menuItems){
      this.menuItems.forEach(element => {
        if(element.children && element.children.length>0){
          let opened: boolean = false;
          element.children.forEach(child => {
            if(child.link===this.currentItem){
              opened=true;
            }
          });
          element.opened = opened;
        }
      });
    }
  }

  ngOnInit(): void {
    this.menuService.initRoute(this.menuItems);

  }

  navigateTo(link: string): void {
    this.menuService.changeCurrent(link);
    this.router.navigate([link]);
  }

  closeMenu(): void {
    this.menuService.close();
  }

  openMenu(): void {
    this.menuService.open();
  }

}
