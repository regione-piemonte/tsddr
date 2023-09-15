import { Component, OnInit } from '@angular/core';
import { LoadingService } from '@theme/layouts/loading.services';
import { NotificationService } from '@shared/notification/notification.service';
import { I18nService } from '@eng-ds/translate';
import { ModalComponent } from '@shared/modal/models/modal.component';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { Form } from '@app/shared/form';
import { AreaPersonaleFormHelperService } from '../helper/area-personale-form-helper.service';
import { AreaPersonaleService } from '../sevices/area-personale.service';
import { SecurityService, UtilityService } from '@app/core';
import { forkJoin } from 'rxjs';
import { ModalService } from '@app/shared/modal/modal.service';
import { ConfirmModalComponent } from '@app/theme/confirm-modal/confirm-modal.component';

@UntilDestroy()
@Component({
  selector: 'area-personale-modal',
  templateUrl: './area-personale-modal.component.html'
})
export class AreaPersonaleModalComponent
  extends ModalComponent
  implements OnInit
{
  formTop: Form;
  formBottom: Form;
  isNotChangedFormBottom: boolean = true;
  utenteCorrente: any = null;
  messageConfirm: any = null;
  isEditEnabledPersonalArea: boolean;
  readOnly: boolean;

  constructor(
    private loadingService: LoadingService,
    private notificationService: NotificationService,
    private areaPersonaleFormHelperService: AreaPersonaleFormHelperService,
    private securityService: SecurityService,
    private areaPersonaleService: AreaPersonaleService,
    private modalService: ModalService,
    private utilityService: UtilityService,
    private i18n: I18nService
  ) {
    super();
  }

  ngOnInit(): void {    
    // ordine e'importante
    this.isEditEnabledPersonalArea =
      this.securityService.isEditEnabledPersonalArea();
    
      forkJoin([
        this.areaPersonaleService.getUtenteCorrente().pipe(untilDestroyed(this)),
        this.areaPersonaleService.getAreaPersonaleACL().pipe(untilDestroyed(this)),
        this.utilityService.getMessage('A001').pipe(untilDestroyed(this)),
      ]).subscribe((results) => {
        this.utenteCorrente = results[0].content;
        
        this._setData(results[0].content,!results[1].content.update);
        this.readOnly = !results[1].content.update;
        this._initForms();
        if((results[2] as any).content) {
          this.messageConfirm =(results[2] as any).content;
        }
      });
    
    
  }

  private _initForms(): void {
    this.formTop = this.areaPersonaleFormHelperService.get('areapersonaleTop');
    this.formBottom = this.areaPersonaleFormHelperService.get(
      'areapersonaleBottom'
    );
    this.formBottom.valueChanges.subscribe((value) => {
      this.isNotChangedFormBottom =
        this.areaPersonaleFormHelperService.isNotChangedFormBottom(value);
    });
  }

  onDismiss() {
    if (this.isNotChangedFormBottom) {
      this.modalContainer.close();
    } else {
      const modalRef = this.modalService.openDialog(ConfirmModalComponent, {
        header: this.messageConfirm.titoloMsg,
        showCloseButton: true,
        context: {
          confirmText: this.messageConfirm.testoMsg
        }
      });
      modalRef.result.then((yes) => {
        if (yes) {
          this.modalContainer.close();
        }
      });
    }
  }

  private _setData(areapersonaleBottomValues: any, readOnly:boolean = false): void {
    this.areaPersonaleFormHelperService.initForms(
      { profilo: this.securityService.getIdProfilo() },
      areapersonaleBottomValues,
      readOnly
    );
  }

  onSave() {
    if (this.isEditEnabledPersonalArea) {
      console.log(this.utenteCorrente);
      this.loadingService.show();
      this.utenteCorrente['email'] = this.formBottom.get('email').value;
      this.utenteCorrente['telefono'] = this.formBottom.get('telefono').value;
      this.areaPersonaleService
        .setUtenteCorrente(this.utenteCorrente)
        .pipe(untilDestroyed(this))
        .subscribe((response: any) => {
          this.loadingService.hide();
          console.log(response);
          this.notificationService.success({
            title: response.message.titoloMsg,
            text: response.message.testoMsg
          });
          this.modalContainer.close();
        });
    } else {
      console.log('Save not permitted with area personal disabled');
    }
  }
}
