import { Component, OnInit } from '@angular/core';
import { AutocompleteInput, Divider, Form, TextInput } from '@shared/form';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { Router } from '@angular/router';
import { NotificationService } from '@shared/notification/notification.service';
import { ModalService } from '@shared/modal/modal.service';
import { I18nService } from '@eng-ds/translate';
import { LoadingService } from '@theme/layouts/loading.services';
import { UtentiService } from '@pages/utenti/services/utenti.service';
import { FunzionalitaProfiliACL } from '@pages/configurazione-profili/models/acl.model';
import { UtentiStoreService } from '@pages/utenti/services/utenti.store';
import { UtilityService } from '@core/services';

@UntilDestroy()
@Component({
  selector: 'app-ricerca',
  templateUrl: './ricerca.component.html'
})
export class RicercaComponent implements OnInit {
  acl: FunzionalitaProfiliACL;
  helperTitle: string = 'UTENTI.RICERCA.HELPER';

  // Form declaration
  searchForm: Form;

  constructor(
    private service: UtentiService,
    private router: Router,
    private notification: NotificationService,
    private modalService: ModalService,
    private i18n: I18nService,
    private utility: UtilityService,
    private loadingService: LoadingService,
    private store: UtentiStoreService
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
      'gestione-utenti-e-profili',
      'gestione-utenti',
      'lista'
    ]);
  }

  onBack() {
    this.router.navigate(['home']);
  }

  onCreate() {
    this.router.navigate([
      'gestione-utenti-e-profili',
      'gestione-utenti',
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
      asyncValidator: [
        this.service.searchValidator(),
        this.service.cfValidator()
      ],
      filter: true,
      controls: {
        codiceFiscale: new TextInput({
          forceFocus: true,
          label: 'UTENTI.RICERCA.FORM.CF.LABEL',
          placeholder: 'UTENTI.RICERCA.FORM.CF.PLACEHOLDER',
          type: 'text',
          size,
          clearable: true,
          validatorOrOpts: { updateOn: 'blur' }
        }),
        divider: new Divider(),
        cognome: new TextInput({
          label: 'UTENTI.RICERCA.FORM.COGNOME.LABEL',
          placeholder: 'UTENTI.RICERCA.FORM.COGNOME.PLACEHOLDER',
          type: 'text',
          size,
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        nome: new TextInput({
          label: 'UTENTI.RICERCA.FORM.NOME.LABEL',
          placeholder: 'UTENTI.RICERCA.FORM.NOME.PLACEHOLDER',
          type: 'text',
          size,
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        idGestore: new AutocompleteInput({
          label: 'UTENTI.RICERCA.FORM.GESTORE.LABEL',
          placeholder: 'UTENTI.RICERCA.FORM.GESTORE.PLACEHOLDER',
          options: this.service.getComboGestori() as any,
          size,
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        idProfilo: new AutocompleteInput({
          label: 'UTENTI.RICERCA.FORM.PROFILO.LABEL',
          placeholder: 'UTENTI.RICERCA.FORM.PROFILO.PLACEHOLDER',
          options: this.service.getComboProfili() as any,
          size,
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        })
      }
    });
  }
}
