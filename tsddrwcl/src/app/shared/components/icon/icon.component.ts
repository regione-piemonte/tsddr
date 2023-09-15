import { Component, Input, OnInit } from '@angular/core';
import { UtilsService } from '../utils.service';
import { Sizes } from '@core/enums/sizes';
import { environment } from '@env/environment';

export enum IconType {
  IT = 'it',
  ENG = 'eng'
}

export enum IconBaseHrefType {
  BOOTSTRAP_ITALIA = '/bootstrap-italia/dist/svg/sprite.svg#',
  ENG = '/assets/images/sprite.svg#'
}

@Component({
  selector: 'app-icon',
  templateUrl: './icon.component.html',
  styleUrls: ['./icon.component.scss']
})
export class IconComponent implements OnInit {
  @Input() name: string;
  @Input() color: string;
  @Input() fill: string;
  @Input() size: string;
  @Input() padding: boolean;
  @Input() cssClass: string;
  @Input() type: string = IconType.IT;
  @Input() baseHref: string = IconBaseHrefType.BOOTSTRAP_ITALIA;
  classes;

  constructor(private utils: UtilsService) {}

  ngOnInit(): void {
    this.type === IconType.IT
      ? (this.baseHref = IconBaseHrefType.BOOTSTRAP_ITALIA)
      : (this.baseHref = IconBaseHrefType.ENG);

    this.baseHref = environment.iconBaseUrl + this.baseHref;
    if (!this.baseHref.endsWith('#')) {
      this.baseHref += '#';
    }
    this.baseHref = this.baseHref + this.name;

    this.classes = {
      icon: true,
      [`icon-${Sizes[this.size]}`]:
        this.utils.isDefined(this.size) && this.utils.isNotNull(this.size),
      [`icon-${this.color}`]:
        this.utils.isDefined(this.color) && this.utils.isNotNull(this.color),
      'icon-padded': this.padding,
      [this.cssClass]:
        this.utils.isDefined(this.cssClass) &&
        this.utils.isNotNull(this.cssClass)
    };
  }
}
