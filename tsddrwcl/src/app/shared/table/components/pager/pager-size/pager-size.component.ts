import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-pager-size',
  templateUrl: './pager-size.component.html',
  styleUrls: ['./pager-size.component.scss']
})
export class AgidPagerSizeComponent {

  /**
   * Numero di elementi per pagina
   * @private
   */
  @Input() size = 10;

  /**
   * Evento invocato al cambio del size
   * @private
   */
  @Output() sizeChange: EventEmitter<number> = new EventEmitter();

  sizes = [5, 10, 20, 50, 100];

  handleItemClick(size: number) {
    this.size = size;
    this.sizeChange.emit(size);
  }
}
