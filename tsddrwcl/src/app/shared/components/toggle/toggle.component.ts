import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-toggle',
  templateUrl: './toggle.component.html',
  styleUrls: ['./toggle.component.scss']
})
export class ToggleComponent implements OnInit {
  @Input() id!: string;
  @Input() checked = false;
  @Input() isReadonly = false;

  @Output() checkedChange = new EventEmitter<false>();

  constructor() {
    //This is intentional
  }

  ngOnInit(): void {
     //This is intentional
  }

  onClick(value: any) {
    this.checkedChange.emit(value.target.checked);
  }
}
