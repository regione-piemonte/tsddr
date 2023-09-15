import {
  AfterViewInit,
  Component,
  Inject,
  LOCALE_ID,
  OnInit,
  TemplateRef,
  ViewChild
} from '@angular/core';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { FunzionalitaProfiliACL } from '@pages/configurazione-profili/models/acl.model';
import { LocalPagedDataSource } from '@shared/table/models/local-paged-data-source';
import { FunzionalitaProfili } from '@pages/configurazione-profili/models/funzionalita-profilo.model';
import { TableColumn } from '@shared/table/models/column.model';
import { formatDate } from '@angular/common';
import { Router } from '@angular/router';
import { UtilityService } from '@core/services';
import { NotificationService } from '@shared/notification/notification.service';
import { ModalService } from '@shared/modal/modal.service';
import { I18nService } from '@eng-ds/translate';
import { LoadingService } from '@theme/layouts/loading.services';
import { TablePage } from '@shared/table/models/table-page';
import { take, tap } from 'rxjs/operators';
import { AlertDialogComponent } from '@shared/components/alert-dialog/alert-dialog.component';
import { GestoriService } from '@pages/gestori/services/gestori.service';
import { GestoriStoreService } from '@pages/gestori/services/gestori.store';
import { Gestore } from '@pages/gestori/models/gestore.model';
import { forkJoin } from 'rxjs';
import { ConfirmEditComponent } from '@pages/gestori/components/confirm-edit/confirm-edit.component';
import { csiCatchError } from '@core/operators/catch-error.operator';

@UntilDestroy()
@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styles: [
    'button[disabled]{  color: #fff !important; background-color: #5d7083 !important; border-color: #5d7083 !important }'
    ]
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
      value ? formatDate(value, 'dd/MM/yyyy', this.locale) : ''
  };

  pipeFormatComune = {
    transform: (value: any) =>
      value ? value.comune : ''
  };

  pipeFormatIndirizzo = {
    transform: (value: any) =>{
      let ret='';
      if(value){
        ret+=(value.sedime && value.sedime.descSedime)?value.sedime.descSedime+' ':'';
        ret+=(value.indirizzo)?value.indirizzo:'';
      }
      return ret;
    }
  };

  constructor(
    private service: GestoriService,
    private router: Router,
    private utility: UtilityService,
    private notification: NotificationService,
    private modalService: ModalService,
    private i18n: I18nService,
    private loadingService: LoadingService,
    private store: GestoriStoreService,
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
  onExportExcelGestoriLista(){
    this.service.downloadReportGestori(this.filter)
  }
  updateDatasource(filter: any) {
    this.dataSource = new LocalPagedDataSource<any>({
      observable: this.service.getGestori.bind(this.service, filter),
      tablePage: new TablePage()
    });
  }

  onBack() {
    this.router.navigate(['gestori']);
  }

  onCreate() {
    this.router.navigate(['gestori', 'inserimento']);
  }

  detail(row: Gestore) {
    this.router.navigate(['gestori', row.idGestore, 'edit']);
  }

  onDelete(row: Gestore) {
    const obs = [
      this.service.hasDomande(row),
      this.service.hasImpianti(row),
      this.utility.getMessage('A006'),
      this.utility.getMessage('A010'),
      this.utility.getMessage('I004'),
      this.utility.getMessage('P004'),
      this.utility.getMessage('A009'),
      this.utility.getMessage('P005')
    ];

    forkJoin(obs).subscribe(
      ([
        hasDomande,
        hasImpianti,
        a006,
        a010,
        i004,
        p004,
        a009,
        p005
      ]: any[]) => {
        this.startDeleteFlow(
          row,
          hasDomande,
          hasImpianti,
          a006,
          a010,
          i004,
          p004,
          a009,
          p005
        );
      }
    );
  }

  startDeleteFlow(
    row: Gestore,
    hasDomande: any,
    hasImpianti: any,
    a006: any,
    a010: any,
    i004: any,
    p004: any,
    a009: any,
    p005: any
  ) {
    if (!hasDomande.content) {
      const notHasDomandeDialog = this.modalService.openDialog(
        ConfirmEditComponent,
        {
          sizeModal: 'xs',
          header: a010.content.titoloMsg,
          showCloseButton: true,
          context: { messageConfirm: a010.content }
        }
      );

      notHasDomandeDialog.closed.pipe(untilDestroyed(this)).subscribe(() => {
        this.startCheckImpianti(
          row,
          hasDomande,
          hasImpianti,
          a006,
          a010,
          i004,
          p004,
          a009,
          p005
        );
      });
    } else {
      this.notification.warning({
        text: i004.content.testoMsg,
        title: i004.content.titoloMsg
      });
    }
  }

  startCheckImpianti(
    row: Gestore,
    hasDomande: any,
    hasImpianti: any,
    a006: any,
    a010: any,
    i004: any,
    p004: any,
    a009: any,
    p005: any
  ) {
    if (!hasImpianti.content) {
      this.delete(row, p004);
    } else {
      const hasImpiantiDialog = this.modalService.openDialog(
        ConfirmEditComponent,
        {
          sizeModal: 'xs',
          header: a009.content.titoloMsg,
          showCloseButton: true,
          context: { messageConfirm: a009.content }
        }
      );

      hasImpiantiDialog.closed.pipe(untilDestroyed(this)).subscribe(() => {
        this.delete(row, p005);
      });
    }
  }

  delete(row: Gestore, message: any) {
    this.service
      .deleteGestore(row)
      .pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response: any) => {
        this.loadingService.hide();
        this.notification.success({
          text: message.content.testoMsg,
          title: message.content.titoloMsg
        });
        this.dataSource.refresh();
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
        prop: 'codFiscPartiva',
        name: this.i18n.translate('GESTORI.TABLE.COLUMNS.CF'),
        cellClass: 'align-middle min-w-140',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.codFiscPartiva).localeCompare(b.codFiscPartiva);
          }
          return ('' + b.codFiscPartiva).localeCompare(a.codFiscPartiva);
        }
      },
      {
        prop: 'ragSociale',
        name: this.i18n.translate('GESTORI.TABLE.COLUMNS.RAGIONE_SOCIALE'),
        cellClass: 'align-middle',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.ragSociale).localeCompare(b.ragSociale);
          }
          return ('' + b.ragSociale).localeCompare(a.ragSociale);
        }
      },
      {
        prop: 'sedeLegale.comune',
        name: this.i18n.translate('GESTORI.TABLE.COLUMNS.COMUNE'),
        cellClass: 'align-middle',
        sortable: true,
        pipe: this.pipeFormatComune,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + this.pipeFormatComune.transform(a.sedeLegale.comune)).localeCompare(this.pipeFormatComune.transform(b.sedeLegale.comune));
          }
          return ('' + this.pipeFormatComune.transform(b.sedeLegale.comune)).localeCompare(this.pipeFormatComune.transform(a.sedeLegale.comune));
        }
      },
      {
        prop: 'sedeLegale',
        name: this.i18n.translate('GESTORI.TABLE.COLUMNS.INDIRIZZO'),
        cellClass: 'align-middle',
        sortable: true,
        pipe: this.pipeFormatIndirizzo,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + this.pipeFormatIndirizzo.transform(a.sedeLegale)).localeCompare(this.pipeFormatIndirizzo.transform(b.sedeLegale));
          }
          return ('' + this.pipeFormatIndirizzo.transform(b.sedeLegale)).localeCompare(this.pipeFormatIndirizzo.transform(a.sedeLegale));
        }
      },
      {
        prop: 'dataInizioValidita',
        name: this.i18n.translate('GESTORI.TABLE.COLUMNS.INIZIO'),
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
        name: this.i18n.translate('GESTORI.TABLE.COLUMNS.FINE'),
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
        name: this.i18n.translate('GESTORI.TABLE.COLUMNS.AZIONI'),
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
