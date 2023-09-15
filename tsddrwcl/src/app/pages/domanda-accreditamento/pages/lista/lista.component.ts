import {
  AfterViewInit,
  Component,
  Inject,
  LOCALE_ID,
  OnInit,
  TemplateRef,
  ViewChild
} from '@angular/core';
import { DomandaAccreditamentoACL } from '@pages/domanda-accreditamento/models/acl.model';
import { DomandaAccreditamentoService } from '@app/pages/domanda-accreditamento/services/domanda-accreditamento.service';
import { Router } from '@angular/router';
import { NotificationService } from '@shared/notification/notification.service';
import { ModalService } from '@shared/modal/modal.service';
import { I18nService } from '@eng-ds/translate';
import { LoadingService } from '@theme/layouts/loading.services';
import { DomandaAccreditamentoStoreService } from '@app/pages/domanda-accreditamento/services/domanda-accreditamento.store';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { LocalPagedDataSource } from '@shared/table/models/local-paged-data-source';
import { TableColumn } from '@shared/table/models/column.model';
import { TablePage } from '@shared/table/models/table-page';
import { take, tap } from 'rxjs/operators';
import { AlertDialogComponent } from '@shared/components/alert-dialog/alert-dialog.component';
import { UtilityService } from '@core/services';
import { formatDate } from '@angular/common';
import { Domanda } from '@pages/domanda-accreditamento/models/domanda.model';
import { FunzionalitaProfili } from '@pages/configurazione-profili/models/funzionalita-profilo.model';
import { DatiSogg } from '../../models/dati-sogg.model';

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
  acl: DomandaAccreditamentoACL;

  // Table declaration
  dataSource: LocalPagedDataSource<FunzionalitaProfili>;
  columns: TableColumn[] = [];
  filter: any;
  parametriApplicati:string;
  helperTitle:string;

  pipeFormatDate = {
    transform: (value: string) =>
      value ? formatDate(value, 'dd/MM/yyyy', this.locale) : ''
  };

  pipeFormatRichiedente = {
    transform: (value: DatiSogg) =>
      value ? value.cognome+' '+value.nome: ''
  };

  constructor(
    private service: DomandaAccreditamentoService,
    private router: Router,
    private utility: UtilityService,
    private notification: NotificationService,
    private modalService: ModalService,
    private i18n: I18nService,
    private loadingService: LoadingService,
    private store: DomandaAccreditamentoStoreService,
    private utilityService: UtilityService,
    @Inject(LOCALE_ID) public locale: string
  ) {}

  ngOnInit(): void {
    this._initHelper();
    this._initACL();
    this._initStore();
    this._initListenOnSize();
    this.loadingService.hide();
  }

  ngAfterViewInit() {
    this._initTable();
  }
  private _initHelper(): void {
    this.utilityService.getNotaInfo('ELENCO DOMANDE').subscribe((result: any) => {
      if (result.content) {
        this.helperTitle = result.content.testoInfo;
      }
    });
  }


  updateDatasource(filter: any) {
    this.dataSource = new LocalPagedDataSource<any>({
      observable: this.service.getDomande.bind(this.service, filter),
      tablePage: new TablePage()
    });
  }

  updateParametriFiltroApplicati(filter: any) {
    this.service.getParametriFiltroApplicati(filter).pipe(untilDestroyed(this)).subscribe(
      res=>this.parametriApplicati=res
    )
  }
onExportExcel(){
  console.log(this.filter)
  this.service.downloadReportDomandeAccreditamento(this.filter)
}
  onBack() {
    this.router.navigate(['domanda-accreditamento']);
  }

  detail(row: Domanda) {
    this.router.navigate([
      'domanda-accreditamento',
      row.idDomanda,
      'edit'
    ]);
  }

  private _initListenOnSize() {
    this.dataSource.getElements().pipe(
      tap((response) => {
        console.log(response);
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
        this.updateParametriFiltroApplicati(filter);
      });
  }

  private _initTable(): void {
    this.columns = [
      {
        prop: 'idDomanda',
        name: this.i18n.translate('ACCREDITAMENTO_BO.LISTA.TABLE.COLUMNS.ID'),
        cellClass: 'align-middle',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.idDomanda).localeCompare(
              b.idDomanda
            );
          }
          return ('' + b.idDomanda).localeCompare(
            a.idDomanda
          );
        }
      },
      {
        prop: 'gestore.ragSociale',
        name: this.i18n.translate('ACCREDITAMENTO_BO.LISTA.TABLE.COLUMNS.GESTORE'),
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
        prop: 'dataRichiesta',
        name: this.i18n.translate('ACCREDITAMENTO_BO.LISTA.TABLE.COLUMNS.DATARICHIESTA'),
        cellClass: 'align-middle',
        sortable: true,
        pipe: this.pipeFormatDate,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.dataRichiesta).localeCompare(
              b.dataRichiesta
            );
          }
          return ('' + b.dataRichiesta).localeCompare(
            a.dataRichiesta
          );
        }
      },
      {
        prop: 'stato.desc',
        name: this.i18n.translate('ACCREDITAMENTO_BO.LISTA.TABLE.COLUMNS.STATODOMANDA'),
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.stato.desc).localeCompare(b.stato.desc);
          }
          return ('' + b.stato.desc).localeCompare(a.stato.desc);
        }
      },
      {
        prop: 'richiedente',
        name: this.i18n.translate('ACCREDITAMENTO_BO.LISTA.TABLE.COLUMNS.RICHIEDENTE'),
        pipe: this.pipeFormatRichiedente,
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + this.pipeFormatRichiedente.transform(a.richiedente)).localeCompare(this.pipeFormatRichiedente.transform(b.richiedente));
          }
          return ('' + this.pipeFormatRichiedente.transform(b.richiedente)).localeCompare(this.pipeFormatRichiedente.transform(a.richiedente));
        }
      },
      {
        name: this.i18n.translate('ACCREDITAMENTO_BO.LISTA.TABLE.COLUMNS.AZIONI'),
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
