import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SecurityService } from '@app/core';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';

@UntilDestroy()
@Component({
  selector: 'app-auth',
  template: ''
})
export class AuthComponent implements OnInit {
  constructor(private userService: SecurityService, private router: Router) {}

  ngOnInit(): void {
    this.userService
      .getUser()
      .pipe(untilDestroyed(this))
      .subscribe((userInfo) => {
        if (userInfo) {
          const redirectUrl = localStorage.getItem('redirectUrl') || '/home';
          this.router.navigateByUrl(redirectUrl);
          localStorage.removeItem('redirectUrl');
        }
      });
  }
}
