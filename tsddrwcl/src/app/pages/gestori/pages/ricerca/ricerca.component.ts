import { Component, OnInit } from '@angular/core';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { FunzionalitaProfiliACL } from '@pages/configurazione-profili/models/acl.model';
import { AutocompleteInput, Divider, Form, TextInput } from '@shared/form';
import { Router } from '@angular/router';
import { NotificationService } from '@shared/notification/notification.service';
import { ModalService } from '@shared/modal/modal.service';
import { I18nService } from '@eng-ds/translate';
import { UtilityService } from '@core/services';
import { LoadingService } from '@theme/layouts/loading.services';
import { GestoriService } from '@pages/gestori/services/gestori.service';
import { GestoriStoreService } from '@pages/gestori/services/gestori.store';
import { of } from 'rxjs';

@UntilDestroy()
@Component({
  selector: 'app-ricerca',
  templateUrl: './ricerca.component.html'
})
export class RicercaComponent implements OnInit {
  acl: FunzionalitaProfiliACL;
  helperTitle: string = 'GESTORI.RICERCA.HELPER';

  // Form declaration
  searchForm: Form;

  constructor(
    private service: GestoriService,
    private router: Router,
    private notification: NotificationService,
    private modalService: ModalService,
    private i18n: I18nService,
    private utility: UtilityService,
    private loadingService: LoadingService,
    private store: GestoriStoreService
  ) {}

  ngOnInit(): void {
    this._initACL();
    this._initSearchForm();
    this._initHelper();
    this.loadingService.hide();
  }

  onSearch() {
    this.store.setSearchInput(this.searchForm.value);
    this.router.navigate(['gestori', 'lista']);
  }

  onBack() {
    this.router.navigate(['home']);
  }
  onExportExcelGestoriRicerca(){
    this.service.downloadReportGestori()
  }
  onCreate() {
    this.router.navigate(['gestori', 'inserimento']);
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
      asyncValidator: [
        this.service.searchValidator(),
        this.service.cfValidator()
      ],
      filter: true,
      controls: {
        codFiscPartiva: new TextInput({
          forceFocus: true,
          label: 'GESTORI.RICERCA.FORM.CF.LABEL',
          placeholder: 'GESTORI.RICERCA.FORM.CF.PLACEHOLDER',
          type: 'text',
          size,
          clearable: true,
          validatorOrOpts: { updateOn: 'blur' }
        }),
        divider: new Divider(),
        idGestore: new AutocompleteInput({
          label: 'GESTORI.RICERCA.FORM.GESTORE.LABEL',
          placeholder: 'GESTORI.RICERCA.FORM.GESTORE.PLACEHOLDER',
          options: this.service.getComboGestori() as any,
          size,
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        idNaturaGiuridica: new AutocompleteInput({
          label: 'GESTORI.RICERCA.FORM.NATURA_GIURIDICA.LABEL',
          placeholder: 'GESTORI.RICERCA.FORM.NATURA_GIURIDICA.PLACEHOLDER',
          options: this.service.getComboNaturaGiuridica() as any,
          size,
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        idProvincia: new AutocompleteInput({
          label: 'GESTORI.RICERCA.FORM.PROVINCIA.LABEL',
          placeholder: 'GESTORI.RICERCA.FORM.PROVINCIA.PLACEHOLDER',
          options: this.service.getComboProvincia() as any,
          valueChange: (value) => {
            if (value) {
              // TODO controllare mapping combo
              const comune = this.searchForm.get(
                'idComune'
              ) as AutocompleteInput;
              comune.reset();
              comune.enable();
              comune.setOptions(this.service.getComboComune(value));
            }
          },
          size,
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        idComune: new AutocompleteInput({
          label: 'GESTORI.RICERCA.FORM.COMUNE.LABEL',
          placeholder: 'GESTORI.RICERCA.FORM.COMUNE.PLACEHOLDER',
          options: of([]),
          size,
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        })
      }
    });
    const idComune = this.searchForm.get('idComune') as AutocompleteInput;
    idComune.disable();
  }
}
