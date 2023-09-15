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
import { LocalPagedDataSource } from '@shared/table/models/local-paged-data-source';
import { TableColumn } from '@shared/table/models/column.model';
import { formatDate } from '@angular/common';
import { Router } from '@angular/router';
import { UtilityService } from '@core/services';
import { NotificationService } from '@shared/notification/notification.service';
import { ModalService } from '@shared/modal/modal.service';
import { I18nService } from '@eng-ds/translate';
import { LoadingService } from '@theme/layouts/loading.services';
import { TablePage } from '@shared/table/models/table-page';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { take, tap } from 'rxjs/operators';
import { AlertDialogComponent } from '@shared/components/alert-dialog/alert-dialog.component';
import { AccreditamentoFoService } from '@pages/accreditamento-fo/services/accreditamento-fo.service';
import {
  DomandaAccreditamento,
  ListaDomandeAccreditamento
} from '@pages/accreditamento-fo/models/domanda-accreditamento.model';
import { ConfirmDeleteComponent } from '@pages/accreditamento-fo/components/confirm-delete/confirm-delete.component';

@UntilDestroy()
@Component({
  selector: 'app-gestione',
  templateUrl: './gestione.component.html'
})
export class GestioneComponent implements OnInit, AfterViewInit {
  @ViewChild('actionsTemplate') actionsTemplate: TemplateRef<any>;
  acl: FunzionalitaProfiliACL;

  helperTitle: string;
  // Table declaration
  dataSource: LocalPagedDataSource<ListaDomandeAccreditamento>;
  columns: TableColumn[] = [];
  filter: any;

  pipeFormatDate = {
    transform: (value: string) =>
      value ? formatDate(value, 'dd/MM/yyyy', this.locale) : ''
  };

  constructor(
    private service: AccreditamentoFoService,
    private router: Router,
    private utility: UtilityService,
    private notification: NotificationService,
    private modalService: ModalService,
    private i18n: I18nService,
    private loadingService: LoadingService,
    @Inject(LOCALE_ID) public locale: string
  ) {}

  ngOnInit(): void {
    // this._initACL();
    this._initHelper();
    this._initDatasource();
    this._initListenOnSize();
    this.loadingService.hide();
  }

  ngAfterViewInit() {
    this._initTable();
  }

  _initDatasource() {
    this.dataSource = new LocalPagedDataSource<ListaDomandeAccreditamento>({
      observable: this.service.getDomandeAccreditamento.bind(this.service),
      tablePage: new TablePage()
    });
  }
  onExportExcel() {
    this.service.dowloadReportAccreditamento()
  }
  onBack() {
    this.router.navigate(['home']);
  }

  detail(row: DomandaAccreditamento) {
    this.router.navigate(['accreditamento', 'gestione-domande', row.idDomanda]);
  }

  delete(row: DomandaAccreditamento) {
    if (row.stato.desc !== 'IN_LAVORAZIONE') {
      return;
    }
    this.utility.getMessage('A003').subscribe((result: any) => {
      const { content } = result;
      if (content) {
        const dialog = this.modalService.openDialog(ConfirmDeleteComponent, {
          sizeModal: 'xs',
          header: content.titoloMsg,
          showCloseButton: true,
          context: { domanda: row, messageConfirm: content }
        });

        dialog.closed.pipe(untilDestroyed(this)).subscribe(() => {
          this.dataSource.refresh();
        });
      }
    });
  }

  private _initHelper(): void {
    this.utility.getNotaInfo('ELENCO DOMANDE').subscribe((result: any) => {
      if (result.content) {
        this.helperTitle = result.content.testoInfo;
      }
    });
  }

  private _initListenOnSize() {
    this.dataSource.getElements().pipe(
      tap((response) => {
        if (response.length === 0) {
          this.utility
            .getMessage('I001')
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

  private _initTable(): void {
    this.columns = [
      {
        prop: 'idDomanda',
        name: this.i18n.translate('ACCREDITAMENTO_FO.GESTIONE.TABLE.ID'),
        cellClass: 'align-middle',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.idDomanda).localeCompare(b.idDomanda);
          }
          return ('' + b.idDomanda).localeCompare(a.idDomanda);
        }
      },
      {
        prop: 'gestore.ragSociale',
        name: this.i18n.translate('ACCREDITAMENTO_FO.GESTIONE.TABLE.GESTORE'),
        cellClass: 'align-middle min-w-140',
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
        prop: 'dataRichiesta',
        name: this.i18n.translate(
          'ACCREDITAMENTO_FO.GESTIONE.TABLE.DATA_DOMANDA'
        ),
        cellClass: 'align-middle min-w-140',
        pipe: this.pipeFormatDate,
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.dataRichiesta).localeCompare(b.dataRichiesta);
          }
          return ('' + b.dataRichiesta).localeCompare(a.dataRichiesta);
        }
      },
      {
        prop: 'stato.desc',
        name: this.i18n.translate('ACCREDITAMENTO_FO.GESTIONE.TABLE.STATO'),
        cellClass: 'align-middle min-w-140',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.stato.desc).localeCompare(b.stato.desc);
          }
          return ('' + b.stato.desc).localeCompare(a.stato.desc);
        }
      },
      {
        name: this.i18n.translate('ACCREDITAMENTO_FO.GESTIONE.TABLE.AZIONI'),
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
