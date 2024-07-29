import { ActivatedRoute, Router } from '@angular/router';

import { I18nService } from '@eng-ds/translate';
import { IProfiloACL } from '@core/models/acl.model';
import { TableColumn } from '@shared/table/models/column.model';
import { LocalPagedDataSource } from '@shared/table/models/local-paged-data-source';
import { MrService } from '../../services/mr.service';
import { ListaAbstract } from '../abstract-factory/lista-abstract';
import { UtilityService } from '@core/services';
import { TablePage } from '@app/shared/table/models/table-page';
import { IMessage } from '@app/core/models/shared.model';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { tap } from 'rxjs/operators';
import { AlertDialogComponent } from '@app/shared/components/alert-dialog/alert-dialog.component';
import { ModalService } from '@shared/modal/modal.service';
import { TemplateRef, ViewChild } from '@angular/core';
import { NotificationService } from '@shared/notification/notification.service';
import { DeleteMrComponent } from '../../modals/delete-mr/delete-mr.component';
import { AutocompleteInput, Form, TextInput } from '@app/shared/form';
import { IPrevCons } from '../../interfaces/prev-cons.interface';
import { ID_TIPO_DOC } from '../constants';

@UntilDestroy()
export class DichiarazioneLista extends ListaAbstract {
  @ViewChild('pregressoTemplate') pregressoTemplate: TemplateRef<any>;

  public dataSource: LocalPagedDataSource<IProfiloACL>;
  public columns: TableColumn[] = [];
  public filter: any;
  public filtro: string = '';
  public isProfileBo: boolean;
  public checkSuperAdmn: boolean;
  public idTipoDoc = ID_TIPO_DOC.DICHIARAZIONE;
  public helperTitle: string;
  // pulsanti e labels
  public btnNuovaMr: string = 'DICHIARAZIONI_MR.RICERCA.NUOVA_DICHIARAZIONE';
  public labelShow: string = 'DICHIARAZIONI_MR.LISTA.BUTTON.VISUALIZZA';
  public labelDownload: string = 'DICHIARAZIONI_MR.LISTA.BUTTON.SCARICA';
  public labelDelete: string = 'DICHIARAZIONI_MR.LISTA.BUTTON.ELIMINA';

  constructor(
    readonly mrService: MrService,
    readonly router: Router,
    private route: ActivatedRoute,
    readonly utilityService: UtilityService,
    readonly notification: NotificationService,
    readonly modalService: ModalService,
    readonly i18n: I18nService
  ) {
    super();
  }

  public onBack(): Promise<boolean> {
    return this.router.navigate(['dichiarazioni-mr']);
  }
  public onCreate(): Promise<boolean> {
    return this.router.navigate(['dichiarazioni-mr', 'pre-inserimento']);
  }
  public detail(row: IPrevCons): Promise<boolean> {
    return this.router.navigate([
      'dichiarazioni-mr',
      row.idPrevCons,
      'edit',
      this.idTipoDoc
    ]);
  }

  public initForm(): Form {
    // metodo non utilizzato in questo caso
    return null;
  }

  public setFilter(filter: any): void {
    this.filter = filter;
    this.updateDatasource(filter);
    this.initParametriFiltro(filter);
  }

  public delete(row: IPrevCons): void {
    this.utilityService.getMessage('A016').subscribe((result: any) => {
      const { content } = result;
      if (content) {
        const dialog = this.modalService.openDialog(DeleteMrComponent, {
          sizeModal: 'xs',
          header: content.titoloMsg,
          showCloseButton: true,
          context: {
            idPrevCons: row.idPrevCons,
            idTipoDoc: this.idTipoDoc,
            messageConfirm: content
          }
        });

        dialog.closed.pipe(untilDestroyed(this)).subscribe(() => {
          this.dataSource.refresh();
        });
      }
    });
  }
  onDowloadReport(){
    this.mrService.downloadReportPrevCons(this.idTipoDoc, this.filter)
  }
  public updateDatasource(filter: any): void {
    this.dataSource = new LocalPagedDataSource<any>({
      observable: this.mrService.getDichiarazioni.bind(
        this.mrService,
        filter,
        this.idTipoDoc
      ),
      tablePage: new TablePage()
    });
  }

  public initParametriFiltro(filter: any): void {


    this.mrService
      .getParametriFiltro(filter)
      .pipe(untilDestroyed(this))
      .subscribe((current) => {
        this.filtro = current;
      });
  }

  public initListenOnSize(messageA002: IMessage): void {
    this.dataSource?.getElements().pipe(
      tap((response) => {
        if (response.length === 0) {
          this.modalService.openDialog(AlertDialogComponent, {
            sizeModal: 'xs',
            header: messageA002.titoloMsg,
            showCloseButton: true,
            context: { messageConfirm: messageA002 }
          });
        }
      })
    );
  }

  public setHelperTitle(value: string): void {
    this.helperTitle = value;
  }

  public initTable(actionsTemplate: TemplateRef<any>,pregressoTemplate?: TemplateRef<any>): void {
    let columns = [
      {
        prop: 'annoTributo',
        name: this.i18n.translate('DICHIARAZIONI.LISTA.TABLE.COLUMNS.ANNO'),
        cellClass: 'align-middle min-w-140',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return a.annoTributo
              .toString()
              .localeCompare(b.annoTributo.toString(), 'en-US', {
                numeric: true
              });
          }
          return b.annoTributo
            .toString()
            .localeCompare(a.annoTributo.toString(), 'en-US', {
              numeric: true
            });
        }
      },
      {
        prop: 'ragSociale',
        name: this.i18n.translate('DICHIARAZIONI.LISTA.TABLE.COLUMNS.GESTORE'),
        cellClass: 'align-middle',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return a.ragSociale.localeCompare(b.ragSociale);
          }
          return b.ragSociale.localeCompare(a.ragSociale);
        }
      },
      {
        prop: 'denominazione',
        name: this.i18n.translate('DICHIARAZIONI.LISTA.TABLE.COLUMNS.IMPIANTO'),
        cellClass: 'align-middle',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return a.denominazione.localeCompare(b.denominazione);
          }
          return b.denominazione.localeCompare(a.denominazione);
        }
      },
      {
        prop: 'descrStatoDichiarazione',
        name: this.i18n.translate('DICHIARAZIONI_MR.LISTA.TABLE.COLUMNS.STATO'),
        cellClass: 'align-middle',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return a.descrStatoDichiarazione.localeCompare(
              b.descrStatoDichiarazione
            );
          }
          return b.descrStatoDichiarazione.localeCompare(
            a.descrStatoDichiarazione
          );
        }
      },
      {
        prop: 'numProtocollo',
        name: this.i18n.translate(
          'DICHIARAZIONI_MR.LISTA.TABLE.COLUMNS.PROTOCOLLO'
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
      },
      {
        name: this.i18n.translate('DICHIARAZIONI.LISTA.TABLE.COLUMNS.PREGRESSO'),
        cellTemplate: pregressoTemplate,
        cellClass: 'align-middle',
        sortable: false
      },
      {
        name: this.i18n.translate('DICHIARAZIONI.LISTA.TABLE.COLUMNS.AZIONI'),
        cellTemplate: actionsTemplate,
        cellClass: 'align-middle',
        sortable: false
      }
    ];
    if (this.isProfileBo) {
      // non mostro la colonna stato per il profiloBO
      columns = columns.filter(
        (item) => item.prop !== 'descrStatoDichiarazione'
      );
    }
    this.columns = columns;
  }
}
