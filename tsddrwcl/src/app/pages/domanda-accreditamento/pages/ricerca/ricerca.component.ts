import { Component, OnInit } from '@angular/core';
import { AutocompleteInput, DateInput, Form, TextInput, ValidationStatus } from '@shared/form';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { Router } from '@angular/router';
import { NotificationService } from '@shared/notification/notification.service';
import { ModalService } from '@shared/modal/modal.service';
import { I18nService } from '@eng-ds/translate';
import { LoadingService } from '@theme/layouts/loading.services';
import { DomandaAccreditamentoService } from '@app/pages/domanda-accreditamento/services/domanda-accreditamento.service';
import { FunzionalitaProfiliACL } from '@pages/configurazione-profili/models/acl.model';
import { DomandaAccreditamentoStoreService } from '@app/pages/domanda-accreditamento/services/domanda-accreditamento.store';
import { UtilityService } from '@app/core';
import { forkJoin } from 'rxjs';

@UntilDestroy()
@Component({
  selector: 'app-ricerca',
  templateUrl: './ricerca.component.html'
})
export class RicercaComponent implements OnInit {
  acl: FunzionalitaProfiliACL;
  helperTitle: string;

  // Form declaration
  searchForm: Form;

  // errori del form che recuperiamo dal BE
  messageIdDomandaTesto: string;
  messageIdDataTesto: string;


  constructor(
    private service: DomandaAccreditamentoService,
    private router: Router,
    private notification: NotificationService,
    private modalService: ModalService,
    private i18n: I18nService,
    private loadingService: LoadingService,
    private store: DomandaAccreditamentoStoreService,
    private utilityService: UtilityService
  ) {}

  ngOnInit(): void {
    this._initACL();
    forkJoin([ this.utilityService
      .getMessage('E001')
      .pipe(untilDestroyed(this)), this.utilityService
      .getMessage('E002')
      .pipe(untilDestroyed(this))]).subscribe(results => {
        this.messageIdDomandaTesto = ((results[0] as any).content.testoMsg) as string;
        this.messageIdDataTesto = ((results[1] as any).content.testoMsg) as string;
        this._initSearchForm();
    });
    this._initHelper();
    this.loadingService.hide();
  }

  onSearch() {
    this.store.setSearchInput(this.searchForm.value);
    this.router.navigate([
      'domanda-accreditamento',
      'lista'
    ]);
  }

  onBack() {
    this.router.navigate(['home']);
  }

  onExportExcelAccrRicerca(){
this.service.downloadReportDomandeAccreditamento()
  }

  private _initACL(): void {
    this.service
      .getStoredAcl()
      .pipe(untilDestroyed(this))
      .subscribe((acl) => (this.acl = acl));
  }

  private _initHelper(): void {
    this.utilityService.getNotaInfo('PARAMETRO').subscribe((result: any) => {
      if (result.content) {
        this.helperTitle = result.content.testoInfo;
      }
    });
  }

  private _initSearchForm() {
    const size = '12|6|6|6|6';
    this.searchForm = new Form({
      header: { show: false },
      validatorOrOpts: [
        this.service.searchValidator(),
        this.service.idDomandaNumericValidator(),
        this.service.dateValidator(),
      ],
      filter: true,
      controls: {
        idDomanda: new TextInput({
          forceFocus: true,
          label: 'ACCREDITAMENTO_BO.RICERCA.FORM.IDOMANDA.LABEL',
          placeholder: 'ACCREDITAMENTO_BO.RICERCA.FORM.IDOMANDA.PLACEHOLDER',
          type: 'number',
          size,
          clearable: true,
          validationStatus: [
            ValidationStatus.ERROR.CUSTOM(
              (control) => control.touched && control.parent?.hasError('iddomanda'),
              {
                text: this.messageIdDomandaTesto
              }
            )
          ],
        }),
        idStatoDomanda: new AutocompleteInput({
          label: 'ACCREDITAMENTO_BO.RICERCA.FORM.IDSTATODOMANDA.LABEL',
          placeholder: 'ACCREDITAMENTO_BO.RICERCA.FORM.IDSTATODOMANDA.PLACEHOLDER',
          options: this.service.getComboAllStati() as any,
          size,
          clearable: true,
          multiple: true
        }),
        idGestore: new AutocompleteInput({
          label: 'ACCREDITAMENTO_BO.RICERCA.FORM.IDGESTORE.LABEL',
          placeholder: 'ACCREDITAMENTO_BO.RICERCA.FORM.IDGESTORE.PLACEHOLDER',
          options: this.service.getComboGestoriAccreditamento() as any,
          size,
          clearable: true,
          multiple: true
        }),
        idRichiedente: new AutocompleteInput({
          label: 'ACCREDITAMENTO_BO.RICERCA.FORM.IDRICHIEDENTE.LABEL',
          placeholder: 'ACCREDITAMENTO_BO.RICERCA.FORM.IDRICHIEDENTE.PLACEHOLDER',
          options: this.service.getComboRichiedenti() as any,
          size,
          clearable: true,
          multiple: true
        }),
        dataDomandaDal: new DateInput({
          label: 'ACCREDITAMENTO_BO.RICERCA.FORM.DATADOMANDADAL.LABEL',
          placeholder: 'ACCREDITAMENTO_BO.RICERCA.FORM.DATADOMANDADAL.PLACEHOLDER',
          size: size,
          clearable: true
        }),
        dataDomandaAl: new DateInput({
          label: 'ACCREDITAMENTO_BO.RICERCA.FORM.DATADOMANDAAL.LABEL',
          placeholder: 'ACCREDITAMENTO_BO.RICERCA.FORM.PLACEHOLDER',
          size: size,
          clearable: true,
          validationStatus: [
            ValidationStatus.ERROR.CUSTOM(
              (control) => control.touched && control.parent?.hasError('dataDomandaAl'),
              {
                text: this.messageIdDataTesto
              }
            )
          ],
        })
      }
    });
  }
}
