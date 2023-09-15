import { Component, OnInit } from '@angular/core';
import { AutocompleteInput, Divider, Form, TextInput } from '@shared/form';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { Router } from '@angular/router';
import { NotificationService } from '@shared/notification/notification.service';
import { ModalService } from '@shared/modal/modal.service';
import { I18nService } from '@eng-ds/translate';
import { LoadingService } from '@theme/layouts/loading.services';
import { ImpiantiService } from '@pages/impianti/services/impianti.service';
import { ImpiantiACL } from '@pages/impianti/models/acl.model';
import { ImpiantiStoreService } from '@pages/impianti/services/impianti.store';
import { UtilityService } from '@core/services';
import { of } from 'rxjs';

@UntilDestroy()
@Component({
  selector: 'app-ricerca',
  templateUrl: './ricerca.component.html'
})
export class RicercaComponent implements OnInit {
  acl: ImpiantiACL;
  helperTitle: string;

  // Form declaration
  searchForm: Form;

  constructor(
    private service: ImpiantiService,
    private router: Router,
    private notification: NotificationService,
    private modalService: ModalService,
    private i18n: I18nService,
    private utility: UtilityService,
    private loadingService: LoadingService,
    private store: ImpiantiStoreService
  ) {}

  ngOnInit(): void {
    this._initACL();
    this._initSearchForm();
    this._initHelper();
    this.loadingService.hide();
  }

  onSearch() {
    this.store.setSearchInput(this.searchForm.value);
    this.router.navigate([
      'impianti',
      'lista'
    ]);
  }
  onExportExcelImpiantiRicerca(){
    this.service.downloadReportImpianti()
  }
  onBack() {
    this.router.navigate(['home']);
  }

  onCreate() {
    this.router.navigate([
      'impianti',
      'inserimento'
    ]);
  }

  private _initHelper(): void {
    this.utility.getNotaInfo('PARAMETRO').subscribe((result: any) => {
      if (result.content) {
        this.helperTitle = result.content.testoInfo;
      }
    });
  }

  private _initACL(): void {
    this.service
      .getStoredAcl()
      .pipe(untilDestroyed(this))
      .subscribe((acl) => (this.acl = acl));
  }

  private _initSearchForm() {
    const size = '12|6|6|6|6';
    this.searchForm = new Form({
      header: { show: false },
      validatorOrOpts: [
        this.service.searchValidator(),
      ],
      filter: true,
      controls: {
        idGestore: new AutocompleteInput({
          label: 'IMPIANTI.RICERCA.FORM.IDGESTORE.LABEL',
          placeholder: 'IMPIANTI.RICERCA.FORM.IDGESTORE.PLACEHOLDER',
          options: this.service.getComboGestori() as any,
          size,
          clearable: true
        }),
        denominazione: new TextInput({
          label: 'IMPIANTI.RICERCA.FORM.DENOMINAZIONE.LABEL',
          placeholder: 'IMPIANTI.RICERCA.FORM.DENOMINAZIONE.PLACEHOLDER',
          type: 'text',
          size,
          clearable: true
        }),
        idProvincia: new AutocompleteInput({
          label: 'IMPIANTI.RICERCA.FORM.IDPROVINCIA.LABEL',
          placeholder: 'IMPIANTI.RICERCA.FORM.IDPROVINCIA.PLACEHOLDER',
          options: this.service.getComboProvince() as any,
          valueChange: (idProvincia: number) => {
            if (idProvincia){
              (
                this.searchForm.get('idComune') as AutocompleteInput
              ).setOptions(
                this.service.getComboComuni(+idProvincia) as any
              );
            }else{
              (
                this.searchForm.get('idComune') as AutocompleteInput
              ).setOptions(
                (of()) as any
              );
            }
          },
          size,
          clearable: true
        }),
        idComune: new AutocompleteInput({
          label: 'IMPIANTI.RICERCA.FORM.IDCOMUNE.LABEL',
          placeholder: 'IMPIANTI.RICERCA.FORM.IDCOMUNE.PLACEHOLDER',
          options: (of()) as any,
          size,
          clearable: true
        }),
        idTipoImpianto: new AutocompleteInput({
          label: 'IMPIANTI.RICERCA.FORM.IDTIPOIMPIANTO.LABEL',
          placeholder: 'IMPIANTI.RICERCA.FORM.IDTIPOIMPIANTO.PLACEHOLDER',
          options: this.service.getComboImpiantiTipi() as any,
          size,
          clearable: true
        }),
        idStatoImpianto: new AutocompleteInput({
          label: 'IMPIANTI.RICERCA.FORM.IDSTATOIMPIANTO.LABEL',
          placeholder: 'IMPIANTI.RICERCA.FORM.IDSTATOIMPIANTO.PLACEHOLDER',
          options: this.service.getComboImpiantiStati() as any,
          size,
          clearable: true
        })
      }
    });
  }
}
