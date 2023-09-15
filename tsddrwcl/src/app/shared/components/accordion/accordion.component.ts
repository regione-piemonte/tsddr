import { Component, Input, OnInit } from '@angular/core';
import { AccordionCard } from 'app/shared/models/';

@Component({
  selector: 'app-accordion',
  templateUrl: './accordion.component.html',
  styleUrls: ['./accordion.component.scss'],
})
export class AccordionComponent
  implements OnInit {

  @Input() card: AccordionCard;

  classes;

  constructor() {
     //This is intentional
  }

  ngOnInit(): void {
    this.classes = {
      accordion: true,
      'collapse-div': true,
      'collapse-left-icon': this.card?.header?.showLeft,
    };
  }

}
