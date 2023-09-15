import { formatDate } from '@angular/common';
import { TemplateRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UtilityService } from '@app/core';
import { IProfiloACL } from '@app/core/models/acl.model';
import { IMessage } from '@app/core/models/shared.model';
import { I18nService } from '@app/core/translate/public-api';
import { AlertDialogComponent } from '@app/shared/components/alert-dialog/alert-dialog.component';
import { AutocompleteInput, Form, SelectOption, TextInput } from '@app/shared/form';
import { ModalService } from '@app/shared/modal/modal.service';
import { NotificationService } from '@app/shared/notification/notification.service';
import { TableColumn } from '@app/shared/table/models/column.model';
import { LocalPagedDataSource } from '@app/shared/table/models/local-paged-data-source';
import { TablePage } from '@app/shared/table/models/table-page';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { IPrevCons } from '../../interfaces/prev-cons.interface';
import { MrService } from '../../services/mr.service';
import { ListaAbstract } from '../abstract-factory/lista-abstract';
import { ID_TIPO_DOC, STATO_PREV_CONS } from '../constants';

@UntilDestroy()
export class InserimentoLista extends ListaAbstract {
  public dataSource: LocalPagedDataSource<any>;
  public columns: TableColumn[] = [];
  public filter: any;
  public filtro: string = '';
  public isProfileBo: boolean;
  public checkSuperAdmn: boolean;
  public idTipoDoc = ID_TIPO_DOC.RICHIESTA;
  public helperTitle: string;
  // pulsanti e labels
  public btnNuovaMr: string = 'DICHIARAZIONI_MR.NUOVA_DICHIARAZIONE';
  public labelShow: string = 'DICHIARAZIONI_MR.NUOVA_DICHIARAZIONE_RMR';
  public labelDownload: string = 'DICHIARAZIONI_MR.LISTA.BUTTON.SCARICA';
  public labelDelete: string = 'DICHIARAZIONI_MR.LISTA.BUTTON.ELIMINA';

  private form: Form;

  pipeFormatDate = {
    transform: (value: string) =>
      value ? formatDate(value, 'dd/MM/yyyy', this.locale) : ''
  };

  constructor(
    readonly mrService: MrService,
    readonly router: Router,
    private route: ActivatedRoute,
    readonly utilityService: UtilityService,
    readonly notification: NotificationService,
    readonly modalService: ModalService,
    readonly i18n: I18nService,
    readonly locale: string
  ) {
    super();
  }

  public onBack(): Promise<boolean> {
    return this.router.navigate(['dichiarazioni-mr']);
  }
  public onCreate(): Promise<boolean> {
    //inserimento NON legato a richiesta RMR
    return this.router.navigate(['dichiarazioni-mr', 'inserimento']);
  }

  public detail(row: IPrevCons): Promise<boolean> {
    //inserimento legato a richiesta RMR
    return this.router.navigate(['dichiarazioni-mr', 'inserimento', row.idPrevCons, this.idTipoDoc]);
  }
public onDowloadReport(): void {
    
}
  public setFilter(filter: any): void {
    // devo aggiungere ai filtri il parametro idStatoDichiarazione: 2 per prendere solo le RMR in stato inviata
    filter.idStatoDichiarazione = STATO_PREV_CONS.INVIATA;
    this.filter = filter;
    this.updateDatasource(filter);
    this.initParametriFiltro(filter);
  }

  public searchRmr(filter: any) {
    // devo aggiungere ai filtri il parametro idStatoDichiarazione: 2 per prendere solo le RMR in stato inviata
    filter.idStatoDichiarazione = STATO_PREV_CONS.INVIATA;
    this.filter = filter;
    // per visualizzare i nuovi risultati nella tabella devo prima settare così il nuovo observable
    this.dataSource.setObservable(this.mrService.getDichiarazioni.bind(
      this.mrService,
      this.filter,
      this.idTipoDoc
    ));
    // poi devo chiamare un dataSource.refresh() che va a richiamare la tabella con il nuovo observable
    this.dataSource.refresh();
    this.initParametriFiltro(filter);
  }

  public updateDatasource(filter: any): void {
    this.dataSource = new LocalPagedDataSource<any>({
        observable: this.mrService.getDichiarazioni.bind(
          this.mrService,
          this.filter,
          this.idTipoDoc
        ),
        tablePage: new TablePage()
      });
  }

  public initParametriFiltro(filter: any): void {

//In case of error E014 filters saved in the store service and restored
    if (this.route.snapshot.queryParamMap.get('previousUrl') === 'da-duplicate') {
      let filterSaved = this.utilityService.getFormValue();
      this.filter = filterSaved;
      filter = filterSaved;
      this.updateDatasource(filterSaved);
 //Clear the query params
      const params = { ...this.route.snapshot.queryParams };
      delete params.previousUrl
      this.router.navigate([], { queryParams: params });
    } else {
      this.utilityService.setFormValue(filter);
    }
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

  public initTable(actionsTemplate: TemplateRef<any>): void {
    let columns = [
      {
        prop: 'annoTributo',
        name: this.i18n.translate('DICHIARAZIONI.LISTA.TABLE.COLUMNS.ANNO'),
        cellClass: 'align-middle',
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
        prop: 'dataDoc',
        name: this.i18n.translate('DICHIARAZIONI_MR.LISTA.TABLE.COLUMNS.INVIO_RICHIESTA').toUpperCase(),
        cellClass: 'align-middle',
        pipe: this.pipeFormatDate,
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.dataDoc).localeCompare(
              b.dataDoc
            );
          }
          return ('' + b.dataDoc).localeCompare(
            a.dataDoc
          );
        }
      },
      {
        prop: 'lineeRichiesta',
        name: this.i18n.translate('DICHIARAZIONI_MR.LISTA.TABLE.COLUMNS.LINEE_RICHIESTA'),
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

    this.columns = columns;
  }

  public delete(row: IPrevCons): void {
    //metodo che non fa nulla: questo pulsante non è presente nelle azioni
  }

  public initForm(): Form {
    const size = '12|6|6|6|6';
    this.form = new Form({
      header: { show: false },
      filter: false,
      controls: {
        idGestore: new AutocompleteInput({
          label: 'DICHIARAZIONI.CREATE.FORM.GESTORE.LABEL',
          placeholder: 'DICHIARAZIONI.CREATE.FORM.GESTORE.PLACEHOLDER',
          options: this.mrService.getComboDichiarazioneGestori() as Observable<SelectOption<string | number, string>[]>,
          value:
                this.filter && this.filter['idGestore']
                  ? this.filter['idGestore']
                  : null,
              valueChange: (idGestore: string) => {
                let impiantoField = this.form.get(
                  'idImpianto'
                ) as AutocompleteInput;
                // se idGestore è presente mostro gli impianti associati, altrimenti li mostro tutti
                if (idGestore) {
                  impiantoField.setOptions(
                    this.mrService.getComboDichiarazioneImpianti(
                      +idGestore
                    ) as any
                  );
                } else {
                  impiantoField.setOptions(
                    this.mrService.getComboDichiarazioneImpianti() as any
                  );
                }
                impiantoField.setValue(null);
              },
          size,
          clearable: true
        }),
        idImpianto: new AutocompleteInput({
          label: 'DICHIARAZIONI.CREATE.FORM.IMPIANTO.LABEL',
          placeholder: 'DICHIARAZIONI.CREATE.FORM.IMPIANTO.PLACEHOLDER',
          value:
                this.filter && this.filter['idImpianto']
                  ? this.filter['idImpianto']
                  : null,
          options:
              this.filter && this.filter['idGestore']
                ? (this.mrService.getComboDichiarazioneImpianti(
                    +this.filter['idGestore']
                  ) as any)
                : (this.mrService.getComboDichiarazioneImpianti() as any),
          size,
          clearable: true
        }),
        annoTributo: new AutocompleteInput({
          label: 'DICHIARAZIONI.CREATE.FORM.ANNO.LABEL',
          value:   this.filter && this.filter['annoTributo']
          ? this.filter['annoTributo']
          : null,
          placeholder: 'DICHIARAZIONI.CREATE.FORM.ANNO.PLACEHOLDER',
          options: this.mrService.getComboDichiarazioniAnni(this.idTipoDoc, STATO_PREV_CONS.INVIATA) as any,
          size,
          clearable: true,
        })
      }
    });
    return this.form;
  }

}
