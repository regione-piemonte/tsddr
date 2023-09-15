import {
  AfterViewInit,
  Component,
  Inject,
  LOCALE_ID,
  OnInit,
  TemplateRef,
  ViewChild
} from '@angular/core';
import { ImpiantiACL } from '@pages/impianti/models/acl.model';
import { ImpiantiService } from '@pages/impianti/services/impianti.service';
import { Router } from '@angular/router';
import { NotificationService } from '@shared/notification/notification.service';
import { ModalService } from '@shared/modal/modal.service';
import { I18nService } from '@eng-ds/translate';
import { LoadingService } from '@theme/layouts/loading.services';
import { ImpiantiStoreService } from '@pages/impianti/services/impianti.store';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { LocalPagedDataSource } from '@shared/table/models/local-paged-data-source';
import { TableColumn } from '@shared/table/models/column.model';
import { TablePage } from '@shared/table/models/table-page';
import { take, tap } from 'rxjs/operators';
import { AlertDialogComponent } from '@shared/components/alert-dialog/alert-dialog.component';
import { UtilityService } from '@core/services';
import { formatDate } from '@angular/common';
import { FunzionalitaProfili } from '@pages/configurazione-profili/models/funzionalita-profilo.model';
import { ConfirmDeleteComponent } from '@pages/impianti/components/confirm-delete/confirm-delete.component';
import { Impianto } from '../../models/impianto.model';
import { ConfirmEditComponent } from '../../components/confirm-edit/confirm-edit.component';
import { InfoImpiantoComponent } from '../../components/info-impianto/info-impianto.component';

@UntilDestroy()
@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.scss']
})
export class ListaComponent implements OnInit, AfterViewInit {
  @ViewChild('actionsTemplate') actionsTemplate: TemplateRef<any>;
  acl: ImpiantiACL;

  // Table declaration
  dataSource: LocalPagedDataSource<FunzionalitaProfili>;
  columns: TableColumn[] = [];
  filter: any;

  filtro: string = '';

  pipeFormatDate = {
    transform: (value: string) =>
      value ? formatDate(value, 'dd/MM/yyyy', this.locale) : ''
  };

  constructor(
    private service: ImpiantiService,
    private router: Router,
    private utility: UtilityService,
    private notification: NotificationService,
    private modalService: ModalService,
    private i18n: I18nService,
    private loadingService: LoadingService,
    private store: ImpiantiStoreService,
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
      observable: this.service.getImpianti.bind(this.service, filter),
      tablePage: new TablePage()
    });
  }
  onExportExcelImpiantiLista() {
    this.service.downloadReportImpianti(this.filter);
  }
  onBack() {
    this.router.navigate(['impianti']);
  }

  onCreate() {
    this.router.navigate(['impianti', 'inserimento']);
  }

  detail(row: Impianto) {
    this.router.navigate(['impianti', row.idImpianto, 'edit']);
  }

  delete(row: Impianto) {
    this.service
      .checkDeleteImpianti(row.idImpianto)
      .subscribe((response: any) => {
        if (response.content.result === true) {
          this.utility.getMessage('A011').subscribe((result: any) => {
            const { content } = result;
            if (content) {
              const dialog = this.modalService.openDialog(
                ConfirmDeleteComponent,
                {
                  sizeModal: 'xs',
                  header: content.titoloMsg,
                  showCloseButton: true,
                  context: { impianto: row, messageConfirm: content }
                }
              );

              dialog.closed.pipe(untilDestroyed(this)).subscribe(() => {
                this.dataSource.refresh();
              });
            }
          });
        } else if (response.content.result === false) {
          this.utility.getMessage('I006').subscribe((result: any) => {
            const { content } = result;
            if (content) {
              const dialog = this.modalService.openDialog(
                InfoImpiantoComponent,
                {
                  sizeModal: 'xs',
                  header: content.titoloMsg,
                  showCloseButton: true,
                  context: { impianto: row, messageConfirm: content }
                }
              );
            }
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
        prop: 'denominazione',
        name: this.i18n.translate('IMPIANTI.LISTA.TABLE.COLUMNS.DENOMINAZIONE'),
        cellClass: 'align-middle min-w-140',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.denominazione).localeCompare(b.denominazione);
          }
          return ('' + b.denominazione).localeCompare(a.denominazione);
        }
      },
      {
        prop: 'gestore.ragSociale',
        name: this.i18n.translate('IMPIANTI.LISTA.TABLE.COLUMNS.GESTORE'),
        cellClass: 'align-middle',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.gestore.ragSociale).localeCompare(
              b.gestore.ragSociale
            );
          }
          return ('' + b.gestore.ragSociale).localeCompare(
            a.gestore.ragSociale
          );
        }
      },
      {
        prop: 'indirizzo.comune.comune',
        name: this.i18n.translate('IMPIANTI.LISTA.TABLE.COLUMNS.COMUNE'),
        cellClass: 'align-middle',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.indirizzo.comune.comune).localeCompare(
              b.indirizzo.comune.comune
            );
          }
          return ('' + b.indirizzo.comune.comune).localeCompare(
            a.indirizzo.comune.comune
          );
        }
      },
      {
        prop: 'indirizzo.comune.provincia.descProvincia',
        name: this.i18n.translate('IMPIANTI.LISTA.TABLE.COLUMNS.PROVINCIA'),
        cellClass: 'align-middle',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return (
              '' + a.indirizzo.comune.provincia.descProvincia
            ).localeCompare(b.indirizzo.comune.provincia.descProvincia);
          }
          return (
            '' + b.indirizzo.comune.provincia.descProvincia
          ).localeCompare(a.indirizzo.comune.provincia.descProvincia);
        }
      },
      {
        prop: 'dataInizioValidita',
        name: this.i18n.translate(
          'IMPIANTI.LISTA.TABLE.COLUMNS.DATAINIZIOVALIDITA'
        ),
        cellClass: 'align-middle',
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
        name: this.i18n.translate(
          'IMPIANTI.LISTA.TABLE.COLUMNS.DATAFINEVALIDITA'
        ),
        cellClass: 'align-middle',
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
        name: this.i18n.translate('IMPIANTI.LISTA.TABLE.COLUMNS.AZIONI'),
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
