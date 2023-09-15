import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BaseComponent, SecurityService, UserInfo, UtilityService } from '../../core';
import { LoadingService } from '../../theme/layouts/loading.services';
import { Subscription } from 'rxjs';
import { PagesMenuService } from '../pages-menu.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent extends BaseComponent implements OnInit, OnDestroy {
  cards: any[];
  cardsHp: any[];
  userCompleto:string;
  cardsSubscription: Subscription;
  helperTitle:string = null;
  helperText:string = null;
fixedItems: any;
  constructor(
    private loadingService: LoadingService,
    private router: Router,
    private securityService: SecurityService,
    private utility: UtilityService,
    private pagesMenuService: PagesMenuService,
  ) {
    super();
  }

  ngOnInit(): void {
    this._initHelper();
    this.fixedItems= this.pagesMenuService.getItemsRoute()
    this.securityService
      .getUser()
      .subscribe((user: UserInfo) => {
        if (!!user) {
          this.userCompleto = user.cognome+' '+user.nome;
        }
      });
    this.cardsSubscription = this.securityService
      .getUserCards()
      .subscribe((cards: any) => {
        this.loadingService.hide();
        if (!!cards) {
          this.cards = cards;
          this.cardsHp = cards.filter((obj) => {
            return obj.descCard;
          });
        }
      });
  }

  private _initHelper(): void {
    this.utility.getMessage('I003').subscribe((result: any) => {
      if (result.content) {
        this.helperTitle = result.content.titoloMsg;
        this.helperText = result.content.testoMsg;
      }
    });
  }

  ngOnDestroy() {
    this.cardsSubscription.unsubscribe();
  }

  onNavigateList(card: any): void {
    this.router.navigate(this._getRoute(card));
  }

  _getRoute(card): string[] {
    let r = ['/'];
    if (card.livello == 2 && card.idPadre != 0) {
      const cardParent = this.cards.find((i) => i.idMenu == card.idPadre);
      if (cardParent) {
        let cP = this.fixedItems.find(
          (element) => element.idMenu === cardParent.idMenu
        ).link as any;
        r.push(
          cP.toLowerCase().replaceAll(' ', '-')
        );
      }
      let itemLink = this.fixedItems.find(
        (element) => element.idMenu === card.idMenu
      ).link as any;
      r.push(itemLink.toLowerCase().replaceAll(' ', '-'));
    }
    if (card.livello == 1) {
      let itemLink = this.fixedItems.find(
        (element) => element.idMenu === card.idMenu
      ).link as any;
      r.push(itemLink.toLowerCase().replaceAll(' ', '-'));
    }
    return r;
  }
}
