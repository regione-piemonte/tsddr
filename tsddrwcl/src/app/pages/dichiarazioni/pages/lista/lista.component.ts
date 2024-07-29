import {
  AfterViewInit,
  Component,
  Inject,
  LOCALE_ID,
  OnInit,
  TemplateRef,
  ViewChild
} from '@angular/core';
import { DichiarazioniACL } from '@pages/dichiarazioni/models/acl.model';
import { DichiarazioneService } from '@app/pages/dichiarazioni/services/dichiarazioni.service';
import { Router } from '@angular/router';
import { NotificationService } from '@shared/notification/notification.service';
import { ModalService } from '@shared/modal/modal.service';
import { I18nService } from '@eng-ds/translate';
import { LoadingService } from '@theme/layouts/loading.services';
import { DichiarazioniStoreService } from '@app/pages/dichiarazioni/services/dichiarazioni.store';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { LocalPagedDataSource } from '@shared/table/models/local-paged-data-source';
import { TableColumn } from '@shared/table/models/column.model';
import { TablePage } from '@shared/table/models/table-page';
import { take, tap } from 'rxjs/operators';
import { AlertDialogComponent } from '@shared/components/alert-dialog/alert-dialog.component';
import { SecurityService, UtilityService } from '@core/services';
import { formatDate } from '@angular/common';
import { FunzionalitaProfili } from '@pages/configurazione-profili/models/funzionalita-profilo.model';
import { ConfirmDeleteComponent } from '@pages/dichiarazioni/components/confirm-delete/confirm-delete.component';
import { Dichiarazione } from '../../models/dichiarazione.model';

@UntilDestroy()
@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.scss']
})
export class ListaComponent implements OnInit, AfterViewInit {
  @ViewChild('actionsTemplate') actionsTemplate: TemplateRef<any>;

  @ViewChild('pregressoTemplate') pregressoTemplate: TemplateRef<any>;

  acl: DichiarazioniACL;

  // Table declaration
  dataSource: LocalPagedDataSource<FunzionalitaProfili>;
  columns: TableColumn[] = [];
  filter: any;

  filtro: string = '';

  isProfileBo: boolean;

  pipeFormatDate = {
    transform: (value: string) =>
      value ? formatDate(value, 'dd/MM/yyyy', this.locale) : ''
  };
  checkSuperAdmn = false;
  constructor(
    private securityService: SecurityService,
    private service: DichiarazioneService,
    private router: Router,
    private utility: UtilityService,
    private notification: NotificationService,
    private modalService: ModalService,
    private i18n: I18nService,
    private loadingService: LoadingService,
    private store: DichiarazioniStoreService,
    @Inject(LOCALE_ID) public locale: string
  ) {}

  ngOnInit(): void {
    this.isProfileBo = this.securityService.isProfileBo();
    this.service
      .getProfilo(this.securityService.getIdProfilo())
      .subscribe((response: any) => {
        this.checkSuperAdmn = response.content;
      });
    this._initACL();
    this._initStore();
    this._initListenOnSize();
    this.loadingService.hide();
  }

  ngAfterViewInit() {
    this._initTable();
  }
  onExportExcelDichLista() {
    this.service.downloadReportDichiarazioni(this.filter);
  }
  updateDatasource(filter: any) {
    this.dataSource = new LocalPagedDataSource<any>({
      observable: this.service.getDichiarazioni.bind(this.service, filter),
      tablePage: new TablePage()
    });
  }

  onBack() {
    this.router.navigate(['dichiarazioni-annuali']);
  }

  onCreate() {
    this.router.navigate(['dichiarazioni-annuali', 'inserimento']);
  }

  detail(row: Dichiarazione) {
    this.router.navigate(['dichiarazioni-annuali', row.idDichAnnuale, 'edit']);
  }

  download(blob, filename) {
    blob.then((res) => {
      const href = window.URL.createObjectURL(res);
      const dlink: HTMLAnchorElement = document.createElement('a');
      dlink.download = filename; // the file name
      dlink.href = href;
      dlink.click(); // this will trigger the dialog window
      dlink.remove();
    });
  }

  b64toBlob(b64Data, contentType = '', sliceSize = 512) {
    const byteCharacters = atob(b64Data);
    const byteArrays = [];

    for (let offset = 0; offset < byteCharacters.length; offset += sliceSize) {
      const slice = byteCharacters.slice(offset, offset + sliceSize);

      const byteNumbers = new Array(slice.length);
      for (let i = 0; i < slice.length; i++) {
        byteNumbers[i] = slice.charCodeAt(i);
      }

      const byteArray = new Uint8Array(byteNumbers);
      byteArrays.push(byteArray);
    }

    const blob = new Blob(byteArrays, { type: contentType });
    return blob;
  }

  pdf(row: Dichiarazione) {
    this.service
      .getDichiarazioneDownload(row.idDichAnnuale)
      .pipe(untilDestroyed(this))
      .subscribe((response) => {
        if (response.content.file && response.content.filename) {
          const blob = this.b64toBlob(response.content.file, 'application/pdf');
          const blobUrl = URL.createObjectURL(blob);
          const dlink: HTMLAnchorElement = document.createElement('a');
          dlink.download = response.content.filename; // the file name
          dlink.href = blobUrl;
          dlink.click(); // this will trigger the dialog window
          dlink.remove();
        }
        if (response.message) {
          this.notification.info({
            title: response.message.titoloMsg,
            text: response.message.testoMsg
          });
        }
      });
  }

  delete(row: Dichiarazione) {
    this.utility.getMessage('A016').subscribe((result: any) => {
      const { content } = result;
      if (content) {
        const dialog = this.modalService.openDialog(ConfirmDeleteComponent, {
          sizeModal: 'xs',
          header: content.titoloMsg,
          showCloseButton: true,
          context: { dichiarazione: row, messageConfirm: content }
        });

        dialog.closed.pipe(untilDestroyed(this)).subscribe(() => {
          this.dataSource.refresh();
        });
      }
    });
  }

  private _initListenOnSize() {
    this.dataSource?.getElements().pipe(
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
    let columns = [
      {
        prop: 'anno',
        name: this.i18n.translate('DICHIARAZIONI.LISTA.TABLE.COLUMNS.ANNO'),
        cellClass: 'align-middle min-w-140',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.anno).localeCompare(b.anno);
          }
          return ('' + b.anno).localeCompare(a.anno);
        }
      },
      {
        prop: 'impianto.gestore.ragSociale',
        name: this.i18n.translate('DICHIARAZIONI.LISTA.TABLE.COLUMNS.GESTORE'),
        cellClass: 'align-middle',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.impianto.gestore.ragSociale).localeCompare(
              b.impianto.gestore.ragSociale
            );
          }
          return ('' + b.impianto.gestore.ragSociale).localeCompare(
            a.impianto.gestore.ragSociale
          );
        }
      },
      {
        prop: 'impianto.denominazione',
        name: this.i18n.translate('DICHIARAZIONI.LISTA.TABLE.COLUMNS.IMPIANTO'),
        cellClass: 'align-middle',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.impianto.denominazione).localeCompare(
              b.impianto.denominazione
            );
          }
          return ('' + b.impianto.denominazione).localeCompare(
            a.impianto.denominazione
          );
        }
      },
      {
        prop: 'statoDichiarazione.descrStatoDichiarazione',
        name: this.i18n.translate('DICHIARAZIONI.LISTA.TABLE.COLUMNS.STATODA'),
        cellClass: 'align-middle',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return (
              '' + a.statoDichiarazione.descrStatoDichiarazione
            ).localeCompare(b.statoDichiarazione.descrStatoDichiarazione);
          }
          return (
            '' + b.statoDichiarazione.descrStatoDichiarazione
          ).localeCompare(a.statoDichiarazione.descrStatoDichiarazione);
        }
      },
      {
        prop: 'versione',
        name: this.i18n.translate('DICHIARAZIONI.LISTA.TABLE.COLUMNS.VERSIONE'),
        cellClass: 'align-middle',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.versione).localeCompare(b.versione);
          }
          return ('' + b.versione).localeCompare(a.versione);
        }
      },
      {
        prop: 'numProtocollo',
        name: this.i18n.translate(
          'DICHIARAZIONI.LISTA.TABLE.COLUMNS.PROTOCOLLO'
        ),
        cellClass: 'align-middle',
        sortable: true,
        sortFn: (a, b, dir) => {
          /**
           * The localeCompare method returns a number indicating whether 'a' comes before, after or is the same as 'b' in sort order.
           * Returns -1 if 'a' is sorted before 'b'
           * Returns 0 if the two strings are equal
           * Returns 1 if 'a' is sorted after 'b'
           */

          let yearCheck;
          let numCheck;
          let numProtocolloA: string[] = a.numProtocollo?.toString().split('/');
          let numProtocolloB: string[] = b.numProtocollo?.toString().split('/');

          if (dir === 'asc') {
            if (a.numProtocollo == undefined) {
              return -1;
            }
            if (b.numProtocollo == undefined) {
              return 1;
            }
            yearCheck = numProtocolloA[1]?.localeCompare(
              numProtocolloB[1],
              'en-US',
              { numeric: true }
            );
            numCheck = numProtocolloA[0]?.localeCompare(
              numProtocolloB[0],
              'en-US',
              { numeric: true }
            );
          } else {
            if (a.numProtocollo == undefined) {
              return 1;
            }
            if (b.numProtocollo == undefined) {
              return -1;
            }
            yearCheck = numProtocolloB[1]?.localeCompare(
              numProtocolloA[1],
              'en-US',
              { numeric: true }
            );
            numCheck = numProtocolloB[0]?.localeCompare(
              numProtocolloA[0],
              'en-US',
              { numeric: true }
            );
          }

          if (yearCheck == 0) {
            return numCheck;
          }
          return yearCheck;
        }
      },  {
        name: this.i18n.translate('DICHIARAZIONI.LISTA.TABLE.COLUMNS.PREGRESSO'),
        cellTemplate: this.pregressoTemplate,
        cellClass: 'align-middle',
        sortable: false
      },
      {
        name: this.i18n.translate('DICHIARAZIONI.LISTA.TABLE.COLUMNS.AZIONI'),
        cellTemplate: this.actionsTemplate,
        cellClass: 'align-middle',
        sortable: false
      },

    ];
    if (this.isProfileBo) {
      // non mostro la colonna stato per il profiloBO
      columns.forEach((item, index, object) => {
        if (item.prop === 'statoDichiarazione.descrStatoDichiarazione') {
          object.splice(index, 1);
        }
      });
    }
    this.columns = columns;
  }

  private _initACL(): void {
    this.service
      .getStoredAcl()
      .pipe(untilDestroyed(this))
      .subscribe((acl) => (this.acl = acl));
  }
}
