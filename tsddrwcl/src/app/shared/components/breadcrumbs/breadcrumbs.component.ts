import { Component, EventEmitter, Input, Output, TemplateRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { I18nService } from '@eng-ds/translate';

@Component({
  selector: 'app-breadcrumbs',
  templateUrl: './breadcrumbs.component.html',
  styleUrls: ['./breadcrumbs.component.scss']
})
export class BreadcrumbsComponent {
  @Input() optTemplate: TemplateRef<any>;

  routesLabels: {
    label: string;
    href: string;
  }[] = [];
  headerLabel: string;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private i18n: I18nService) {
    this._createBreadcrumbs();
  }

  private _createBreadcrumbs(): void {
    const routeSnapshot = this.route.snapshot;
    const routeData = routeSnapshot.data;

    this.routesLabels = routeData.breadcrumbs;
    this.headerLabel = routeData.headerLabel;
   

  }

}
