import {
  AfterViewInit,
  Component,
  EventEmitter,
  Inject,
  Input,
  LOCALE_ID,
  OnInit,
  Output,
  TemplateRef,
  ViewChild
} from '@angular/core';
import {
  AutocompleteInput,
  DateInput,
  Form,
  ValidationStatus
} from '@shared/form';
import { UtentiService } from '@pages/utenti/services/utenti.service';
import { Router } from '@angular/router';
import { NotificationService } from '@shared/notification/notification.service';
import { ModalService } from '@shared/modal/modal.service';
import { I18nService } from '@eng-ds/translate';
import { LoadingService } from '@theme/layouts/loading.services';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { LocalPagedDataSource } from '@shared/table/models/local-paged-data-source';
import { FunzionalitaProfili } from '@pages/configurazione-profili/models/funzionalita-profilo.model';
import { TablePage } from '@shared/table/models/table-page';
import { FunzionalitaProfiliACL } from '@pages/configurazione-profili/models/acl.model';
import { TableColumn } from '@shared/table/models/column.model';
import { Utente } from '@pages/utenti/models/utente.model';
import { DatePipe, formatDate } from '@angular/common';
import { csiCatchError } from '@core/operators/catch-error.operator';

import {
  Gestore,
  GestoreLinkUpdateResponse
} from '@pages/utenti/models/gestore.model';
import { UtilityService } from '@core/services';
import { ConfirmDeleteLinkComponent } from '@pages/utenti/components/confirm-delete-link/confirm-delete-link.component';
import { ConfirmExitComponent } from '@pages/configurazione-profili/components/confirm-exit/confirm-exit.component';
import { Observable } from 'rxjs';

@UntilDestroy()
@Component({
  selector: 'app-associazione-profilo-utente-gestore',
  templateUrl: './associazione-profilo-utente-gestore.component.html'
})
export class AssociazioneProfiloUtenteGestoreComponent
  implements OnInit, AfterViewInit
{
  @Input() utente: Utente;
  @Input() profiliUtente$: Observable<any>;
  @Output() isEditing: EventEmitter<boolean> = new EventEmitter<boolean>();
  @ViewChild('idGestoreTemplate') idGestoreTemplate: TemplateRef<any>;
  @ViewChild('gestoreTemplate') gestoreTemplate: TemplateRef<any>;
  @ViewChild('inizoTemplate') inizoTemplate: TemplateRef<any>;
  @ViewChild('fineTemplate') fineTemplate: TemplateRef<any>;
  @ViewChild('actionsTemplate') actionsTemplate: TemplateRef<any>;

  acl: FunzionalitaProfiliACL;
  createEnabled: boolean = false;
  // Form declaration
  createForm: Form;
  searchForm: Form;
  profilo: number;

  // Table declaration
  updateForms: Form[];
  rows: Gestore[];

  dataSource: LocalPagedDataSource<any>;
  columns: TableColumn[] = [];
  pipeFormatDate = {
    transform: (value: string) =>
      value ? formatDate(value, 'dd/MM/yyyy', this.locale) : ''
  };

  constructor(
    private service: UtentiService,
    private router: Router,
    private notification: NotificationService,
    private modalService: ModalService,
    private i18n: I18nService,
    private datePipe: DatePipe,
    private loadingService: LoadingService,
    private utilityService: UtilityService,
    @Inject(LOCALE_ID) public locale: string
  ) {}

  ngOnInit(): void {
    this._initACL();
    this._initSearchForm();
    this._initForm();
    this.loadingService.hide();
  }

  ngAfterViewInit() {
    this._initTable();
  }

  create() {
    (this.createForm.get('idGestore') as AutocompleteInput).setOptions(
      this.service.getComboGestoriUtente(
        +this.profilo,
        this.utente.idUtente
      ) as any
    );

    if (this.createEnabled) {
      this.loadingService.show();
      const toSave = { ...this.createForm.value };
      toSave.idUtente = +this.utente.idUtente;
      toSave.idProfilo = +this.profilo;

      this.service
        .createLinkUtenteProfiloGestore(toSave)
        .pipe(csiCatchError(), untilDestroyed(this))
        .subscribe(() => {
          this._cleanForm();
          this.loadingService.hide();
          this.dataSource.refresh();
          this.isEditing.emit(false);
        });
      return;
    }
    this.isEditing.emit(true);
    this.createEnabled = !this.createEnabled;
  }

  updateDatasource(profilo: number, utente?: number) {
    if (!this.dataSource) {
      this.dataSource = new LocalPagedDataSource<FunzionalitaProfili>({
        observable: this.service.getGestoriUtente.bind(
          this.service,
          profilo,
          utente
        ),
        tablePage: new TablePage()
      });

      this.dataSource.rows$.subscribe((items: any[]) => {
        this.datasourceElementsForm(items);
      });
      return;
    }

    this.dataSource.setObservable(
      this.service.getGestoriUtente.bind(this.service, profilo, utente)
    );

    this.dataSource.refresh();
  }

  update(row: Gestore) {
    this.loadingService.show();
    this.service
      .updateLinkUtenteProfiloGestore(this._getUpdatesRow(row))
      .pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response: GestoreLinkUpdateResponse) => {
        this.notification.info({
          title: response.message.titoloMsg,
          text: response.message.testoMsg
        });
        this.isEditing.emit(false);
        this.loadingService.hide();
        this.dataSource.refresh();
      });
  }

  delete(row: Gestore) {
    this.utilityService.getMessage('A008').subscribe((result: any) => {
      const { content } = result;
      if (content) {
        const dialog = this.modalService.openDialog(
          ConfirmDeleteLinkComponent,
          {
            sizeModal: 'xs',
            header: content.titoloMsg,
            showCloseButton: true,
            context: {
              gestore: {
                ...row,
                idProfilo: this.profilo,
                idUtente: this.utente.idUtente
              },
              messageConfirm: content
            }
          }
        );

        dialog.closed.pipe(untilDestroyed(this)).subscribe(() => {
          this.isEditing.emit(false);
          this.dataSource.refresh();
        });
      }
    });
  }

  datasourceElementsForm(rows: Gestore[]) {
    const size = '12|6|6|6|6';
    this.updateForms = [];
    this.rows = rows;
    rows.forEach((row) => {
      this.updateForms[row.idGestore] = new Form({
        header: { show: false },
        filter: true,
        controls: {
          dataInizioValidita: new DateInput({
            label: 'UTENTI.EDIT.FORM.INIZIO.LABEL',
            placeholder: 'UTENTI.EDIT.FORM.INIZIO.PLACEHOLDER',
            size,
            value: row.dataInizioValidita
              ? this.datePipe.transform(
                  new Date(row.dataInizioValidita),
                  'yyyy-MM-dd'
                )
              : new Date(),
            validatorOrOpts: { updateOn: 'blur' },
            clearable: true
          }),
          dataFineValidita: new DateInput({
            label: 'UTENTI.EDIT.FORM.FINE.LABEL',
            placeholder: 'UTENTI.EDIT.FORM.FINE.PLACEHOLDER',
            size,
            value: row.dataFineValidita
              ? this.datePipe.transform(
                  new Date(row.dataFineValidita),
                  'yyyy-MM-dd'
                )
              : null,
            validatorOrOpts: { updateOn: 'blur' },
            clearable: true
          })
        }
      });
      this.updateForms[row.idGestore].valueChanges.subscribe((x) => {
        this.isEditing.emit(true);
      });
    });
  }

  private _getUpdatesRow(row: Gestore): Partial<Gestore> {
    const rowForm: Partial<Gestore> = {
      idGestore: row.idGestore,
      idUtente: this.utente.idUtente,
      idProfilo: this.profilo,
      dataInizioValidita: this.updateForms[row.idGestore].get(
        'dataInizioValidita'
      ).value
        ? this.updateForms[row.idGestore].get('dataInizioValidita').value +
          'T06:00:00.000+00:00'
        : null,
      dataFineValidita: this.updateForms[row.idGestore].get('dataFineValidita')
        .value
        ? this.updateForms[row.idGestore].get('dataFineValidita').value +
          'T06:00:00.000+00:00'
        : null
    };
    return rowForm;
  }

  private _initACL(): void {
    this.service
      .getStoredAcl()
      .pipe(untilDestroyed(this))
      .subscribe((acl) => (this.acl = acl));
  }

  private _cleanForm() {
    this.createForm.reset();
  }

  private _initTable(): void {
    this.columns = [
      {
        prop: 'idGestore',
        cellTemplate: this.idGestoreTemplate,
        name: this.i18n.translate('UTENTI.EDIT.TABLE.COLUMNS.ID'),
        cellClass: 'align-middle',
        sortable: false
      },
      {
        cellTemplate: this.gestoreTemplate,
        name: this.i18n.translate('UTENTI.EDIT.TABLE.COLUMNS.GESTORE'),
        cellClass: 'align-middle min-w-250',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.ragSociale).localeCompare(b.ragSociale);
          }
          return ('' + b.ragSociale).localeCompare(a.ragSociale);
        }
      },
      {
        prop: 'dataInizioValidita',
        cellTemplate: this.inizoTemplate,
        name: this.i18n.translate('UTENTI.EDIT.TABLE.COLUMNS.INIZIO'),
        cellClass: 'align-middle min-w-140',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.dataInizioValidita).localeCompare(
              b.dataInizioValidita
            );
          }
          return ('' + b.dataInizioValidita).localeCompare(
            a.dataInizioValidita
          );
        }
      },
      {
        prop: 'dataFineValidita',
        cellTemplate: this.fineTemplate,
        name: this.i18n.translate('UTENTI.EDIT.TABLE.COLUMNS.FINE'),
        cellClass: 'align-middle min-w-140',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.dataFineValidita).localeCompare(b.dataFineValidita);
          }
          return ('' + b.dataFineValidita).localeCompare(a.dataFineValidita);
        }
      },
      {
        name: this.i18n.translate('UTENTI.TABLE.COLUMNS.AZIONI'),
        cellTemplate: this.actionsTemplate,
        cellClass: 'align-middle',
        sortable: false
      }
    ];
  }

  private _initForm() {
    const size = '12|6|6|6|6';
    this.createForm = new Form({
      header: { show: false },
      filter: false,
      controls: {
        idGestore: new AutocompleteInput({
          label: 'UTENTI.EDIT.FORM.GESTORE.LABEL',
          placeholder: 'UTENTI.EDIT.FORM.GESTORE.PLACEHOLDER',
          options: this.service.getComboGestoriUtente(
            this.profilo,
            this.utente.idUtente
          ) as any,
          size,
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        dataInizioValidita: new DateInput({
          label: 'UTENTI.EDIT.FORM.INIZIO.LABEL',
          placeholder: 'UTENTI.EDIT.FORM.INIZIO.PLACEHOLDER',
          required: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({ text: '' })
          ],
          size,
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        dataFineValidita: new DateInput({
          label: 'UTENTI.EDIT.FORM.FINE.LABEL',
          placeholder: 'UTENTI.EDIT.FORM.FINE.PLACEHOLDER',
          size,
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        })
      }
    });
  }

  private _initSearchForm() {
    const size = '12|12|12|12|12';
    this.searchForm = new Form({
      header: { show: false },
      filter: true,
      controls: {
        idProfilo: new AutocompleteInput({
          forceFocus: true,
          label: 'UTENTI.EDIT.FORM.PROFILO.LABEL',
          placeholder: 'UTENTI.EDIT.FORM.PROFILO.PLACEHOLDER',
          readonly: !this.acl.content.update,
          options: this.profiliUtente$ as any,
          valueChange: (profilo: number) => {
            if (profilo) {
              this.updateDatasource(profilo, this.utente.idUtente);
              this.profilo = profilo;
            }
          },
          size,
          clearable: true
        })
      }
    });
  }
}
