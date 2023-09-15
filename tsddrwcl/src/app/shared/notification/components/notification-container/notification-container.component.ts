import { Component, OnInit } from '@angular/core';
import { NotificationService } from '@shared/notification/notification.service';

@Component({
  selector: 'app-notification-container',
  template: `
    <ngb-toast
      *ngFor="let notification of notificationService.notifications"
      [autohide]="true"
      [delay]="notification.time || 7000"
      (hidden)="notificationService.remove(notification)"
    >
      <ng-template [ngTemplateOutlet]="notification.template"></ng-template>
    </ngb-toast>
  `,
  host: {
    class: 'toast-container p-3',
    style: 'z-index: 1200; bottom: 0; right: 0; position: fixed;'
  }
})
export class NotificationContainerComponent implements OnInit {
  constructor(public notificationService: NotificationService) {}

  ngOnInit(): void {
     //This is intentional
  }
}
