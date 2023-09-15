import {
  AfterViewInit,
  Component,
  Inject,
  LOCALE_ID,
  OnInit,
  TemplateRef,
  ViewChild
} from '@angular/core';
import { FunzionalitaProfiliACL } from '@pages/configurazione-profili/models/acl.model';
import { UtentiService } from '@pages/utenti/services/utenti.service';
import { Router } from '@angular/router';
import { NotificationService } from '@shared/notification/notification.service';
import { ModalService } from '@shared/modal/modal.service';
import { I18nService } from '@eng-ds/translate';
import { LoadingService } from '@theme/layouts/loading.services';
import { UtentiStoreService } from '@pages/utenti/services/utenti.store';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { LocalPagedDataSource } from '@shared/table/models/local-paged-data-source';
import { TableColumn } from '@shared/table/models/column.model';
import { TablePage } from '@shared/table/models/table-page';
import { take, tap } from 'rxjs/operators';
import { AlertDialogComponent } from '@shared/components/alert-dialog/alert-dialog.component';
import { UtilityService } from '@core/services';
import { formatDate } from '@angular/common';
import { Utente } from '@pages/utenti/models/utente.model';
import { FunzionalitaProfili } from '@pages/configurazione-profili/models/funzionalita-profilo.model';
import { ConfirmDeleteComponent } from '@pages/utenti/components/confirm-delete/confirm-delete.component';

@UntilDestroy()
@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.scss']
})
export class ListaComponent implements OnInit, AfterViewInit {
  @ViewChild('actionsTemplate') actionsTemplate: TemplateRef<any>;
  acl: FunzionalitaProfiliACL;

  // Table declaration
  dataSource: LocalPagedDataSource<FunzionalitaProfili>;
  columns: TableColumn[] = [];
  filter: any;

  filtro: string = '';

  pipeFormatDate = {
    transform: (value: string) =>
      value ? formatDate(value, 'dd/MM/yyyy - HH:mm', this.locale) : ''
  };

  constructor(
    private service: UtentiService,
    private router: Router,
    private utility: UtilityService,
    private notification: NotificationService,
    private modalService: ModalService,
    private i18n: I18nService,
    private loadingService: LoadingService,
    private store: UtentiStoreService,
    @Inject(LOCALE_ID) public locale: string
  ) {}

  ngOnInit(): void {
    this._initACL();
    this._initStore();
    this._initListenOnSize();
    this.loadingService.hide();
  }

  ngAfterViewInit() {
    this._initTable();
  }

  updateDatasource(filter: any) {
    this.dataSource = new LocalPagedDataSource<any>({
      observable: this.service.getUtenti.bind(this.service, filter),
      tablePage: new TablePage()
    });
  }

  onBack() {
    this.router.navigate(['gestione-utenti-e-profili', 'gestione-utenti']);
  }

  onCreate() {
    this.router.navigate([
      'gestione-utenti-e-profili',
      'gestione-utenti',
      'inserimento'
    ]);
  }

  detail(row: Utente) {
    this.router.navigate([
      'gestione-utenti-e-profili',
      'gestione-utenti',
      row.idUtente,
      'edit'
    ]);
  }

  delete(row: Utente) {
    this.utility.getMessage('A006').subscribe((result: any) => {
      const { content } = result;
      if (content) {
        const dialog = this.modalService.openDialog(ConfirmDeleteComponent, {
          sizeModal: 'xs',
          header: content.titoloMsg,
          showCloseButton: true,
          context: { utente: row, messageConfirm: content }
        });

        dialog.closed.pipe(untilDestroyed(this)).subscribe(() => {
          this.dataSource.refresh();
        });
      }
    });
  }

  private _initListenOnSize() {
    this.dataSource.getElements().pipe(
      tap((response) => {
        if (response.length === 0) {
          this.utility
            .getMessage('A002')
            .pipe(take(1))
            .subscribe((msg: any) => {
              this.modalService.openDialog(AlertDialogComponent, {
                sizeModal: 'xs',
                header: msg.titoloMsg,
                showCloseButton: true,
                context: { messageConfirm: msg }
              });
            });
        }
      })
    );
  }

  private _initParametriFiltro(filter: any) {
    this.service
      .getParametriFiltro(filter)
      .pipe(untilDestroyed(this))
      .subscribe((current) => {
        this.filtro = current;
      });
  }

  private _initStore() {
    this.store
      .getSearchInput()
      .pipe(untilDestroyed(this))
      .subscribe((filter: any) => {
        if (!filter) {
          this.onBack();
          return;
        }
        delete filter.divider;
        this.filter = filter;
        this.updateDatasource(filter);
        this._initParametriFiltro(filter);
      });
  }

  private _initTable(): void {
    this.columns = [
      {
        prop: 'idUtente',
        name: this.i18n.translate('UTENTI.TABLE.COLUMNS.ID'),
        cellClass: 'align-middle',
        sortable: false
      },
      {
        prop: 'codiceFiscale',
        name: this.i18n.translate('UTENTI.TABLE.COLUMNS.CF'),
        cellClass: 'align-middle min-w-250',
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
        name: this.i18n.translate('UTENTI.TABLE.COLUMNS.COGNOME'),
        cellClass: 'align-middle min-w-140',
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
        name: this.i18n.translate('UTENTI.TABLE.COLUMNS.NOME'),
        cellClass: 'align-middle min-w-140',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.nome).localeCompare(b.nome);
          }
          return ('' + b.nome).localeCompare(a.nome);
        }
      },
      {
        prop: 'dataInizioValidita',
        name: this.i18n.translate('UTENTI.TABLE.COLUMNS.INIZIO'),
        cellClass: 'align-middle min-w-140',
        pipe: this.pipeFormatDate,
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
        name: this.i18n.translate('UTENTI.TABLE.COLUMNS.FINE'),
        cellClass: 'align-middle min-w-140',
        pipe: this.pipeFormatDate,
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

  private _initACL(): void {
    this.service
      .getStoredAcl()
      .pipe(untilDestroyed(this))
      .subscribe((acl) => (this.acl = acl));
  }
}
