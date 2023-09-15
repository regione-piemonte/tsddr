import { Component, Input, OnInit } from '@angular/core';
import { UtilsService } from '../utils.service';
import { Colors, Sizes } from '@core/enums';

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html'
})
export class ButtonComponent implements OnInit {

  /* Tipo di button */
  @Input() type: string;
  /* Disabilitato */
  @Input() disabled: boolean;
  /* Outline */
  @Input() outline: boolean;
  /* Block */
  @Input() block: boolean;
  /* Active */
  @Input() active: boolean;
  /* Dimensione bootstrap */
  @Input() size: Sizes;
  /* Colore boostrap */
  @Input() color: Colors;
  /* Class Name */
  @Input() btnClassName: string;
  /* Icon. Booleano indica se il componente contiene una icon */
  @Input() icon: boolean;

  classes;

  constructor(private utils: UtilsService) {
  }

  ngOnInit(): void {
    this.classes = {
      btn: true,
      [`btn-${Sizes[this.size]}`]: this.utils.isDefined(this.size) && this.utils.isNotNull(this.size),
      [`btn${this.outline ? '-outline' : ''}-${this.color}`]: true,
      'btn-block': this.block,
      'btn-icon': this.icon,
      disabled: this.disabled,
      active: this.active,
      [this.btnClassName]:
      this.utils.isDefined(this.btnClassName) && this.utils.isNotNull(this.btnClassName)
    };
  }

}
