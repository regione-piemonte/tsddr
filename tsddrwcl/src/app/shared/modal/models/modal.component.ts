import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { ModalOptions } from './modal.options';
import { BaseComponent } from 'app/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-modal',
  template: ``
})
/**
 * Componente base per la creazione di modali.
 * Gestisce body e footer
 */
export class ModalComponent extends BaseComponent implements OnInit {
  @ViewChild('body') body;
  @ViewChild('footer') footer;

  @Input() options?: ModalOptions;
  modalContainer: NgbActiveModal;

  constructor() {
    super();
  }

  ngOnInit(): void {
     //This is intentional
  }
}
