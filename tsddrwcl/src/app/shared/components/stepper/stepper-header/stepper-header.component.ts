import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-stepper-header',
  templateUrl: './stepper-header.component.html',
  styleUrls: ['./stepper-header.component.scss'],
})
export class StepperHeaderComponent implements OnInit {
  @Input() steps;

  constructor() {
    //This is intentional
  }

  ngOnInit(): void {
     //This is intentional
  }

}
