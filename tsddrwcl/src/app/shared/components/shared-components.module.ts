import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { BreadcrumbsComponent } from '@shared/components/breadcrumbs/breadcrumbs.component';
import { BadgesComponent } from '@shared/components/badges/badges.component';
import { SearchBarComponent } from '@shared/components/search-bar/search-bar.component';
import { TabComponent } from '@shared/components/tab/tab.component';
import { AccordionComponent } from '@shared/components/accordion/accordion.component';
import { UtilsService } from '@shared/components/utils.service';
import { IconComponent } from '@shared/components/icon/icon.component';
import { ButtonComponent } from '@shared/components/button/button.component';
import { DividerComponent } from '@shared/components/divider/divider.component';
import { DropDownComponent } from '@shared/components/dropdown/dropdown.component';
import { AccordionHeaderComponent } from '@shared/components/accordion/accordion-header/accordion-header.component';
import { CardComponent } from '@shared/components/card/card.component';
import { CardHeaderComponent } from '@shared/components/card/card-header/card-header.component';
import { CardBodyComponent } from '@shared/components/card/card-body/card-body.component';
import { CardFooterComponent } from '@shared/components/card/card-footer/card-footer.component';
import { StepperComponent } from '@shared/components/stepper/stepper.component';
import { StepperHeaderComponent } from '@shared/components/stepper/stepper-header/stepper-header.component';
import { StepperBodyComponent } from '@shared/components/stepper/stepper-body/stepper-body.component';
import { HelperComponent } from './helper/helper.component';
import { ToggleComponent } from '@shared/components/toggle/toggle.component';
import { AlertDialogComponent } from './alert-dialog/alert-dialog.component';

const COMPONENTS = [
  AccordionComponent,
  BadgesComponent,
  ButtonComponent,
  IconComponent,
  DividerComponent,
  DropDownComponent,
  TabComponent,
  BreadcrumbsComponent,
  SearchBarComponent,
  CardComponent,
  CardHeaderComponent,
  CardBodyComponent,
  CardFooterComponent,
  StepperComponent,
  StepperHeaderComponent,
  StepperBodyComponent,
  ToggleComponent
];

@NgModule({
  declarations: [...COMPONENTS, AccordionHeaderComponent, HelperComponent, AlertDialogComponent],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    TranslateModule.forChild()
  ],
  exports: [...COMPONENTS, HelperComponent],
  providers: [UtilsService]
})
export class SharedComponentsModule {}
