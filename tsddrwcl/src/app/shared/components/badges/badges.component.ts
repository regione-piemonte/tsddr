import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-badges',
  templateUrl: './badges.component.html',
  styleUrls: ['./badges.component.scss']
})
export class BadgesComponent
  implements OnInit {

  @Input() filters: any;
  @Output() filtersChange: EventEmitter<any> = new EventEmitter();

  constructor() {
 //This is intentional
  }

  ngOnInit(): void {
     //This is intentional
  }

  closeFilter(keyFilter: any): void {
    this.filtersChange.emit(keyFilter);
  }

}
