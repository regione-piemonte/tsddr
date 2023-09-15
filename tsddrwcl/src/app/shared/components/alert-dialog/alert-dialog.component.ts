import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-alert-dialog',
  templateUrl: './alert-dialog.component.html'
})
export class AlertDialogComponent implements OnInit {
  @Input() messageConfirm: Record<string, any>;
  @Input() route: string[];
  modalContainer: NgbActiveModal;

  constructor(private router: Router) {}

  ngOnInit(): void {
     //This is intentional
  }

  onConfirm() {
    this.router.navigate(this.route);
    this.modalContainer.close(true);
  }
}
