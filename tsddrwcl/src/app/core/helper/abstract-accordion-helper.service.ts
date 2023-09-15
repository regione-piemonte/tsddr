import { Injectable } from '@angular/core';
import { AccordionCard, AccordionHelperGetter } from '@app/shared/models';

export interface AccordionItem {
  name: string;
  accordionCard: AccordionCard;
}

@Injectable({
  providedIn: 'root'
})
export abstract class AbstractAccordionHelperService {
  protected _items: Map<string, AccordionItem> = new Map<string, AccordionItem>();

  constructor() {
    //This is intentional
  }

  get(
    accordionHelperGetter: AccordionHelperGetter
  ): AccordionCard {
    const searched = this._items.get(accordionHelperGetter.name);

    if (!searched) {
      return null;
    }

    if (accordionHelperGetter.template) {
      searched.accordionCard.template = accordionHelperGetter.template;
    }
    if (accordionHelperGetter.templateData) {
      searched.accordionCard.templateData = accordionHelperGetter.templateData;
    }
    if (accordionHelperGetter.headerTemplate) {
      searched.accordionCard.header.template = accordionHelperGetter.headerTemplate;
    }

    return searched.accordionCard;
  }

}
