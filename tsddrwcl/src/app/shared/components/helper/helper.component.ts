import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-helper',
  templateUrl: './helper.component.html',
  styleUrls: ['./helper.component.scss']
})
export class HelperComponent implements OnInit {
  @Input() title;
  @Input() text;

  constructor() {
    //This is intentional
  }

  ngOnInit(): void {
     //This is intentional
  }
}
