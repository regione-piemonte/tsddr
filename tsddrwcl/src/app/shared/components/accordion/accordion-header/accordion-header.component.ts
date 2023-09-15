import { Component, Input, OnInit } from '@angular/core';
import { AccordionCard } from 'app/shared/models/';

@Component({
  selector: 'app-accordion-header',
  templateUrl: './accordion-header.component.html',
  styleUrls: ['./accordion-header.component.scss'],
})
export class AccordionHeaderComponent implements OnInit {
  @Input() card: AccordionCard;

  classes;

  constructor() {
     //This is intentional
  }

  ngOnInit(): void {
    this.classes = {
      'card-header': true,
      'collapse-header': true,
      'custom-header': true,
    };
  }

}
