import { Component, OnInit } from '@angular/core';
import { LoadingService } from '../../theme/layouts/loading.services';

@Component({
  styleUrls: ['./not-found.component.scss'],
  templateUrl: './not-found.component.html',
})
export class NotFoundComponent implements OnInit {

  constructor(private loadingService: LoadingService) {
    this.loadingService.hide();
  }

  ngOnInit(): void {
     //This is intentional
  }

}
