import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoadingService } from '@theme/layouts/loading.services';

@Component({
  styleUrls: ['./not-authorized.component.scss'],
  templateUrl: './not-authorized.component.html'
})
export class NotAuthorizedComponent {
  constructor(
    private router: Router,
    private loadingService: LoadingService
  ) {
    this.loadingService.hide();
  }

  goToHome() {
    this.router.navigate(['home']);
  }
}
