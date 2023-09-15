import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-stepper',
  templateUrl: './stepper.component.html',
  styleUrls: ['./stepper.component.scss'],
})
export class StepperComponent implements OnInit {
  @Input() steps;

  constructor() {
    //This is intentional
  }

  ngOnInit(): void {
     //This is intentional
  }

}
