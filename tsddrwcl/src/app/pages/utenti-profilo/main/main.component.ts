import {
  AfterViewInit,
  Component,
  OnInit,
  TemplateRef,
  ViewChild
} from '@angular/core';
import { LoadingService } from '@theme/layouts/loading.services';
import { AutocompleteInput, Form } from '@shared/form';
import { UtentiProfiloService } from '@pages/utenti-profilo/services/utenti-profilo.service';
import { LocalPagedDataSource } from '@shared/table/models/local-paged-data-source';
import { TablePage } from '@shared/table/models/table-page';
import { UtentiProfilo } from '@pages/utenti-profilo/models/utenti-profilo.model';
import { TableColumn } from '@shared/table/models/column.model';
import { I18nService } from '@eng-ds/translate';
import { UtentiProfiloACL } from '@pages/utenti-profilo/models/acl.model';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { NotificationService } from '@shared/notification/notification.service';
import { of } from 'rxjs';
import { ModalService } from '@shared/modal/modal.service';
import { ConfirmDeleteComponent } from '@pages/utenti-profilo/components/confirm-delete/confirm-delete.component';
import { csiCatchError } from '@core/operators/catch-error.operator';
import { Router } from '@angular/router';
import { ConfirmExitComponent } from '@pages/utenti-profilo/components/confirm-exit/confirm-exit.component';
import { UtilityService } from '@core/services';
import { Utente } from '@app/pages/utenti/models/utente.model';

@UntilDestroy()
@Component({
  selector: 'app-main',
  templateUrl: './main.component.html'
})
export class MainComponent implements OnInit, AfterViewInit {
  @ViewChild('actionsTemplate') actionsTemplate: TemplateRef<any>;
  @ViewChild('codiceFiscaleTemplate') codiceFiscaleTemplate: TemplateRef<any>;

  acl: UtentiProfiloACL;
  helperTitle: string = 'UTENTI_PROFILO.HELPER.SELECT';

  // Unsaved change
  hasUnsavedChange = false;

  // Form declaration
  searchForm: Form;
  createForm: Form;
  profilo: number;
  funzionalita: number;
  createEnabled: boolean = false;

  // Create section
  toCreate: UtentiProfilo = {
    idProfilo: null,
    idUtente: null
  };

  // Table declaration
  dataSource: LocalPagedDataSource<Utente>;
  columns: TableColumn[] = [];

  constructor(
    private service: UtentiProfiloService,
    private router: Router,
    private notification: NotificationService,
    private modalService: ModalService,
    private i18n: I18nService,
    private loadingService: LoadingService,
    private utilityService: UtilityService
  ) {}

  ngOnInit(): void {
    this._initACL();
    this._initSearchForm();
    this._initCreateForm();
    this._initHelper();
    this.loadingService.hide();
  }

  ngAfterViewInit() {
    this._initTable();
  }

  create() {
    (this.createForm.get('nuovoUtente') as AutocompleteInput).setOptions(
      this.service.getComboUtentiProfiloNuovoUtente(+this.profilo) as any
    );

    if (this.createEnabled) {
      this.loadingService.show();
      const toSave = { ...this.toCreate };
      toSave.idUtente = +this.createForm.get('nuovoUtente').value;
      toSave.idProfilo = +this.profilo;

      this.service
        .associaUtenteProfilo(toSave)
        .pipe(csiCatchError(), untilDestroyed(this))
        .subscribe((response) => {
          this.notification.info({
            title: response.message.titoloMsg,
            text: response.message.testoMsg
          });
          this._cleanForm();
          this.loadingService.hide();
          this.dataSource.refresh();
          (this.createForm.get('nuovoUtente') as AutocompleteInput).setOptions(
            this.service.getComboUtentiProfiloNuovoUtente(+this.profilo) as any
          );
        });
      return;
    }
    this.createEnabled = !this.createEnabled;
  }

  delete(row: Utente) {
    this.utilityService.getMessage('A005').subscribe((result: any) => {
      const { content } = result;
      if (content) {
        const dialog = this.modalService.openDialog(ConfirmDeleteComponent, {
          sizeModal: 'xs',
          header: content.titoloMsg,
          showCloseButton: true,
          context: {
            utentiProfilo: { idProfilo: this.profilo, idUtente: row.idUtente },
            messageConfirm: content
          }
        });

        dialog.closed
          .pipe(untilDestroyed(this))
          .subscribe(() => this.dataSource.refresh());
      }
    });
  }

  updateDatasource(profilo: number, funzionalita?: number) {
    if (!this.dataSource) {
      this.dataSource = new LocalPagedDataSource<Utente>({
        observable: this.service.getUtentiProfilo.bind(this.service, profilo),
        tablePage: new TablePage()
      });
      return;
    }

    this.dataSource.setObservable(
      this.service.getUtentiProfilo.bind(this.service, profilo)
    );

    this.dataSource.refresh();
  }

  onBack() {
    if (
      this.hasUnsavedChange ||
      (this.createEnabled && this.createForm.valid)
    ) {
      this.utilityService.getMessage('A001').subscribe((result: any) => {
        const { content } = result;
        if (content) {
          const dialog = this._preventDataLose(content);

          dialog.closed
            .pipe(untilDestroyed(this))
            .subscribe(() => this.router.navigate(['']));
        }
      });
      return;
    }
    this.router.navigate(['home']);
  }

  private _initHelper(): void {
    this.utilityService.getNotaInfo('PROFILO').subscribe((result: any) => {
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

  private _initTable(): void {
    this.columns = [
      {
        prop: 'idUtente',
        name: this.i18n.translate('UTENTI_PROFILO.TABLE.COLUMNS.IDUTENTE'),
        cellClass: 'align-middle',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.idUtente).localeCompare(b.idUtente);
          }
          return ('' + b.idUtente).localeCompare(a.idUtente);
        }
      },
      {
        prop: 'codiceFiscale',
        name: this.i18n.translate('UTENTI_PROFILO.TABLE.COLUMNS.CODICEFISCALE'),
        cellClass: 'align-middle',
        cellTemplate: this.codiceFiscaleTemplate,
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.codiceFiscale).localeCompare(b.codiceFiscale);
          }
          return ('' + b.codiceFiscale).localeCompare(a.codiceFiscale);
        }
      },
      {
        prop: 'cognome',
        name: this.i18n.translate('UTENTI_PROFILO.TABLE.COLUMNS.COGNOME'),
        cellClass: 'align-middle',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.cognome).localeCompare(b.cognome);
          }
          return ('' + b.cognome).localeCompare(a.cognome);
        }
      },
      {
        prop: 'nome',
        name: this.i18n.translate('UTENTI_PROFILO.TABLE.COLUMNS.NOME'),
        cellClass: 'align-middle',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.nome).localeCompare(b.nome);
          }
          return ('' + b.nome).localeCompare(a.nome);
        }
      },
      {
        name: this.i18n.translate('UTENTI_PROFILO.TABLE.COLUMNS.AZIONI'),
        cellClass: 'align-middle',
        cellTemplate: this.actionsTemplate,
        sortable: false
      }
    ];
  }

  private _initSearchForm() {
    const size = '12|6|6|6|6';
    this.searchForm = new Form({
      header: { show: false },
      filter: true,
      controls: {
        idProfilo: new AutocompleteInput({
          forceFocus: true,
          label: 'CONFIGURAZIONE_PROFILI.FORM.PROFILO.LABEL',
          placeholder: 'CONFIGURAZIONE_PROFILI.FORM.PROFILO.PLACEHOLDER',
          options: this.service.getComboProfili() as any,
          valueChange: (profilo: number) => {
            if (profilo) {
              this.updateDatasource(profilo);
              this.profilo = profilo;
              this.helperTitle = 'CONFIGURAZIONE_PROFILI.HELPER.SELECTED';
            }
          },
          size,
          clearable: true
        })
      }
    });
  }

  private _initCreateForm() {
    const size = '12|6|6|6|6';
    this.createForm = new Form({
      header: { show: false },
      filter: true,
      controls: {
        nuovoUtente: new AutocompleteInput({
          absolute: true,
          label: 'CONFIGURAZIONE_PROFILI.FORM.FUNZIONALITA.LABEL',
          placeholder: 'CONFIGURAZIONE_PROFILI.FORM.FUNZIONALITA.PLACEHOLDER',
          options: of([]) as any,
          size,
          clearable: true,
          required: true
        })
      }
    });
  }

  private _cleanForm() {
    this.toCreate = {
      idProfilo: null,
      idUtente: null
    };
    this.createEnabled = false;
    this.createForm.reset();
  }

  private _preventDataLose(content: any) {
    const dialog = this.modalService.openDialog(ConfirmExitComponent, {
      sizeModal: 'xs',
      header: content.titoloMsg,
      showCloseButton: true,
      context: { messageConfirm: content }
    });

    return dialog;
  }
}
