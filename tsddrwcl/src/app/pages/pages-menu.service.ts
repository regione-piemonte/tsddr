import { Injectable, OnDestroy } from '@angular/core';
import { I18nService } from '@eng-ds/translate';
import { NbAccessChecker } from '@nebular/security';
import { Subscription } from 'rxjs';
import { MenuItem, SecurityService } from '../core';

/*
 Gestisce le voci di menu del sito
 Prevede una navigazione a due livelli
 La navigazione viene fornita da una chiamata api al BE
*/

@Injectable()
export class PagesMenuService implements OnDestroy {
  cardsSubscription: Subscription;
  // tslint:disable-next-line:variable-name
  private _items: MenuItem[] = [
    {
      title: this.i18n.translate('MENU.HOME'),
      icon: 'eng-home',
      link: '/home',
      home: true,
      data: {
        permission: 'view',
        resource: 'home'
      },
      children: []
    }
  ];
  //Richiesta  rotte  che non dipendano da quanto tornato dal BE.
  private fixedItems = [
    {
      idMenu: 2,
      link: 'accreditamento'
    },
    {
      idMenu: 3,
      link: 'nuova-domanda'
    },
    {
      idMenu: 4,
      link: 'gestione-domande'
    },
    {
      idMenu: 5,
      link: 'domanda-accreditamento'
    },
    {
      idMenu: 6,
      link: 'gestione-utenti-e-profili'
    },
    {
      idMenu: 7,
      link: 'gestione-utenti'
    },
    {
      idMenu: 8,
      link: 'gestione-profili'
    },
    {
      idMenu: 9,
      link: 'configurazione-profili'
    },
    {
      idMenu:10,
      link: 'associa-utenti-a-profili'
    },
    {
      idMenu: 11,
      link: 'gestori'
    },
    {
      idMenu:12,
      link: 'impianti'
    },
    {
      idMenu: 13,
      link: 'dichiarazioni-annuali'
    },
    {
      idMenu: 14,
      link: 'richieste-mr'
    },
    {
      idMenu: 15,
      link: 'dichiarazioni-mr'
    }
  ];
  constructor(
    private i18n: I18nService,
    private accessChecker: NbAccessChecker,
    private securityService: SecurityService
  ) {
    this.cardsSubscription = this.securityService
      .getUserCards()
      .subscribe((cards: any) => {
        if (!!cards) {
          this._items = [this._items[0]];
          // TODO non add se richiama n volte devo accodare solo alla home
          cards.forEach((card) => {
            let desc = this.fixedItems.find(
              (element) => element.idMenu === card.idMenu
            ).link as any;

            const icon = desc.toLowerCase().replaceAll(' ', '-');

            const children = this._getChildren(cards, card);
            if (card.livello == 1) {
              this._items.push({
                title: card.descrizioneVoceMenu,
                icon: 'eng-' + icon,
                link: '/' + icon,
                home: false,
                children: children,
                data: {
                  permission: 'view',
                  resource: 'menu-item'
                }
              });
            }

          });
        }
      });
  }
  getItemsRoute(){
  return this.fixedItems
  }
  private _getChildren(cards, card): MenuItem[] {
    let children: MenuItem[] = [];
    let desc = this.fixedItems.find((element) => element.idMenu === card.idMenu)
      .link as any;

    const routingPathParent =
      '/' + desc.toLowerCase().replaceAll(' ', '-');
    cards
      .filter((i) => i.idPadre == card.idMenu)
      .forEach((cardChild) => {
        let child = this.fixedItems.find((element) => element.idMenu === cardChild.idMenu).link as any

        const icon = child
          .toLowerCase()
          .replaceAll(' ', '-');
        const routingPath = routingPathParent + '/' + icon;
        children.push({
          title: cardChild.descrizioneVoceMenu,
          icon: 'eng-' + icon,
          link: routingPath,
          home: false,
          data: {
            permission: 'view',
            resource: 'menu-item'
          }
        });
      });

    return children;
  }

  ngOnDestroy() {
    this.cardsSubscription.unsubscribe();
  }

  get items(): MenuItem[] {
    return this._items;

    // No need to hide not granted menu item
    // return this._checkAuthMenuItems(this._items);
  }

  private _checkAuthMenuItems(menuItems: MenuItem[]): MenuItem[] {
    return menuItems.map((item) => this._checkAuthMenuItem(item));
  }

  private _checkAuthMenuItem(menuItem: MenuItem): MenuItem {
    if (menuItem.children) {
      menuItem.children = this._checkAuthMenuItems(menuItem.children);
    }

    if (menuItem.data && menuItem.data.permission && menuItem.data.resource) {
      this.accessChecker
        .isGranted(menuItem.data.permission, menuItem.data.resource)
        .subscribe((granted) => {
          // console.log(menuItem.data.permission)
          // console.log(menuItem.data.resource)
          // console.log(granted)
          menuItem.hidden = !granted;
        });
    } else {
      menuItem.hidden = true;
    }
    return menuItem;
  }
}
