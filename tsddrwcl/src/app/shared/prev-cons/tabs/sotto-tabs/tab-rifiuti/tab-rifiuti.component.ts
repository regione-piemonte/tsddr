import {
  AfterViewInit,
  Component,
  EventEmitter,
  Inject,
  Input,
  LOCALE_ID,
  OnChanges,
  OnInit,
  Output,
  SimpleChanges,
  TemplateRef,
  ViewChild
} from '@angular/core';

import { I18nService } from '@app/core/translate/public-api';
import {
  AutocompleteInput,
  Form,
  TextInput,
  ValidationStatus
} from '@app/shared/form';
import { ModalService } from '@app/shared/modal/modal.service';
import { PrevConsDettDeleteComponent } from '@app/shared/prev-cons/modals/prev-cons-dett-delete/prev-cons-dett-delete.component';
import {
  IPrevConsDettEntity,
  IRifiutiObject
} from '@app/shared/prev-cons/interfaces/prev-cons.interface';
import { MrStoreService } from '@app/shared/prev-cons/services/mr-store.service';
import { MrService } from '@app/shared/prev-cons/services/mr.service';
import { TabsRifiutiService } from '@app/shared/prev-cons/services/tabs-rifiuti.service';
import { LocalPagedDataSource } from '@app/shared/table/models/local-paged-data-source';
import { TablePage } from '@app/shared/table/models/table-page';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { TableColumn } from '@shared/table/models/column.model';
import { EMPTY, Observable, Subject, forkJoin, merge, of } from 'rxjs';
import { SEZIONE, TOT_LABEL } from '@app/shared/prev-cons/models/constants';
import { FormControl } from '@angular/forms';
import { map, share, switchMap } from 'rxjs/operators';
import { TypeMessages } from '../../tab-processo/tab-processo.component';
import { formatNumber } from '@angular/common';
import { ICombo } from '@app/shared/prev-cons/interfaces/api-mr.model';
import { LoadingService } from '@app/theme/layouts/loading.services';

export type ActionType = 'add' | 'update' | 'delete';
@UntilDestroy()
@Component({
  selector: 'app-tab-rifiuti',
  templateUrl: './tab-rifiuti.component.html',
  styleUrls: ['./tab-rifiuti.component.scss']
})
export class TabRifiutiComponent implements OnInit, AfterViewInit, OnChanges {
  @Input() isEditMode: boolean = false;
  @Input() isLegameRmr: boolean = false;
  @Input() idPrevCons: number = null;
  @Input() tabName: 'rii' | 'mat' | 'rru' | 'ru';
  @Input() selectedTabObject: IRifiutiObject;
  @Input() messages: TypeMessages;
  @Input() annoTributoPrevCons: number;

  @Output() sendEvent = new EventEmitter<ActionType>();

  @ViewChild('codiceEer') codiceEer: TemplateRef<any>;
  @ViewChild('descrizioneEer') descrizioneEer: TemplateRef<any>;
  @ViewChild('descMatUscita') descMatUscita: TemplateRef<any>;
  @ViewChild('destinazione') destinazione: TemplateRef<any>;
  @ViewChild('quantita') quantita: TemplateRef<any>;
  @ViewChild('actionsTemplate') actionsTemplate: TemplateRef<any>;

  prevConsDett: IPrevConsDettEntity[] = [];
  totLabel: string;
  columns: TableColumn[] = [];
  labelCodiceEer: string;
rifiutiList : ICombo;
  createForm: Form;
  updateForms: { id: number; form: Form }[] = [];

  dataSource: LocalPagedDataSource<any>;
  pipeFormatQuantita = {
    transform: (value: string) =>
      value ? formatNumber(+value, this.locale, '1.0-4') : ''
  };

  obforms$: Observable<any>[] = [];
  action$ = new Subject();

  // numero di elementi per ogni pagina della tabella
  itemsPerPage = 10;

  constructor(
    private i18n: I18nService,
    private mrStoreService: MrStoreService,
    private mrService: MrService,
    private tabsService: TabsRifiutiService,
    private modalService: ModalService,
    @Inject(LOCALE_ID) public locale: string,
    private loadingService: LoadingService,
  ) {


this.rifiutiList =  this.tabsService.getRifiuti()


  }

  ngOnInit(): void {
     //This is intentional


  }

  ngAfterViewInit(): void {

    this._populateColumns();

    this.action$
    .pipe(
      switchMap(() => {
       //in tutti i tab (eccetto mat) alla selezione di un codice eer, popolo la rispettiva descrizione
       if(this.tabName != 'mat') {
         let ob = this.updateForms.map(value => {
            return value.form?.get('codiceEer')?.valueChanges.pipe(
            map(codice => ({codice, form: value.form}))
           );
          })
          return merge(...ob);
        }
        return EMPTY;
      }),
      untilDestroyed(this)
    )
    .subscribe({
      next: (value) => {
        if(value && value.form instanceof Form){
          const selectedEer = this.tabsService.comboEer.content.find(
            (eer) => eer.id == value.codice
          );

        value.form.get('descrizioneEer')?.setValue(selectedEer?.additionalValue);

      }

      }

    })
  }

  ngOnChanges(changes: SimpleChanges): void {

    let { tabName, selectedTabObject } = changes;
    //Setto il nuovo valore delle prevConsDett (che può essere anche un array vuoto) e mostro la tabella aggiornata
    if (selectedTabObject.currentValue) {
      this._setTabDatas(selectedTabObject.currentValue);
    }

    if (tabName.currentValue) {

      this._populateColumns();
      this.createForm = this._initCreateForm();


      //quando cambio tab il form viene rigenerato e devo di nuovo sottoscrivermi ai cambiamenti del codice eer

      this.createForm.get('codiceEer')?.valueChanges
      .pipe(
        share(),
        untilDestroyed(this))
      .subscribe(codiceEer => {
        const selectedEer = this.rifiutiList.content.find(
          (eer) => eer.id == codiceEer
        );
        console.log('finish2')
        this.createForm.get('descrizioneEer')?.setValue(selectedEer?.additionalValue);
   //this.loadingService.hide()
      })
    }
    /**
     * Se è la prima volta che entro nel tab setto il datasource da zero
     * altrimenti, per avere i valori aggiornati, devo solo fare l'update del datasource
     * e forzare la tabella ad andare a pagina 1, altrimenti rischio di non vedere alcuni dati.
     * Se quando aggiorno provo a richiamare il setDatasource, le tabelle non funzionano (vai a capire perchè...)
     */
this.loadingService.show()
    if (this.dataSource) {
      this._updateDatasource();
      this.dataSource.paging(1, 10);
   //   this.loadingService.hide()

    } else {
      this._setDatasource();
     // this.loadingService.hide()
    }
  }

  //ACTIONS
  onAddPrevConsDett() {
    let temporaryPCD: IPrevConsDettEntity = {};
    const countId = this.tabsService.createIdCountPrevConsDett(this.prevConsDett);

    if (this.createForm && this.createForm.valid) {
      const {
        codiceEer,
        descrizioneEer,
        descMatUscita,
        destinazione,
        quantita
      } = this.createForm.getRawValue();

      switch (this.tabName) {
        case 'rii':
          temporaryPCD.eer = {
            idEer: codiceEer,
            codiceEer: codiceEer,
            descrizione: descrizioneEer
          };
          temporaryPCD.quantita = +quantita;
          temporaryPCD.sezione = {
            idSezione: SEZIONE.ID_TAB_RII
          };
          break;

        case 'mat':
          temporaryPCD.descMatUscita = descMatUscita;
          temporaryPCD.quantita = +quantita;
          temporaryPCD.sezione = {
            idSezione: SEZIONE.ID_TAB_MAT
          };
          break;

        case 'rru':
          (temporaryPCD.eer = {
            idEer: codiceEer,
            codiceEer: codiceEer,
            descrizione: descrizioneEer
          }),
            (temporaryPCD.destinazione = destinazione),
            (temporaryPCD.quantita = +quantita),
            (temporaryPCD.sezione = {
              idSezione: SEZIONE.ID_TAB_RRU
            });
          break;

        case 'ru':
          (temporaryPCD.eer = {
            idEer: codiceEer,
            codiceEer: codiceEer,
            descrizione: descrizioneEer
          }),
            (temporaryPCD.destinazione = destinazione),
            (temporaryPCD.quantita = +quantita),
            (temporaryPCD.sezione = {
              idSezione: SEZIONE.ID_TAB_RU
            });
          break;
      }
      temporaryPCD.unitaMisura = {
        idUnitaMisura: 2
      };
      temporaryPCD.isRam = true;
      temporaryPCD.idPrevConsDett = countId;

      //popolo l'array prevConsDett con il mio nuovo valore
      this.prevConsDett.push(temporaryPCD);
    }
    this._updateTotalValue();
    this._updateDatasource();

    // in base alla quantità di elementi nell'array calcolo se devo cambiare pagina nella tabella
    const totalCount = this.prevConsDett.length;
    const currentPage = Math.ceil(totalCount / this.itemsPerPage);
    if (totalCount > this.itemsPerPage) {
      this.dataSource.paging(currentPage, this.itemsPerPage);
    }

    this.createForm.reset();
    this.mrStoreService.setPropsStatus('processoChanged', true);
    this.sendEvent.emit('add');
  }

  onEdit(idPrevConsDett: number) {
    //prendo il nuovo valore della prevConsDett corrispondente alla row che gli passo
    const updatedRow = this.updateForms.find(
      (item) => item.id == idPrevConsDett
    );

    let newPrevConsDett = this.prevConsDett.find(
      (item) => item.idPrevConsDett == idPrevConsDett
    );
    //valori della prevConsDett modificata
    const { codiceEer, descrizioneEer, descMatUscita, destinazione, quantita } = updatedRow.form.getRawValue();
    if((!!descMatUscita) || (!!codiceEer)){
      switch (this.tabName) {
        case 'rii':
          newPrevConsDett = {
            eer: {
              idEer: codiceEer,
              codiceEer: codiceEer,
              descrizione: descrizioneEer
            },
            quantita: +quantita,
            sezione: {
              idSezione: SEZIONE.ID_TAB_RII
            },
            idPrevConsDett: idPrevConsDett,
            unitaMisura: {
              idUnitaMisura: 2
            },
            //stiamo aggiornando il dato di una prevConsDett, il BE vuole il booleano
            hasBeenUpdated: true
          };
          break;
        case 'mat':
          newPrevConsDett = {
            descMatUscita: descMatUscita,
            quantita: +quantita,
            sezione: {
              idSezione: SEZIONE.ID_TAB_MAT
            },
            idPrevConsDett: idPrevConsDett,
            unitaMisura: {
              idUnitaMisura: 2
            },
            //stiamo aggiornando il dato di una prevConsDett, il BE vuole il booleano
            hasBeenUpdated: true
          };
          break;
        case 'rru':
          newPrevConsDett = {
            eer: {
              idEer: codiceEer,
              codiceEer: codiceEer,
              descrizione: descrizioneEer
            },
            quantita: +quantita,
            destinazione: destinazione,
            sezione: {
              idSezione: SEZIONE.ID_TAB_RRU
            },
            idPrevConsDett: idPrevConsDett,
            unitaMisura: {
              idUnitaMisura: 2
            },
            //stiamo aggiornando il dato di una prevConsDett, il BE vuole il booleano
            hasBeenUpdated: true
          };
          break;
        case 'ru':
          newPrevConsDett = {
            eer: {
              idEer: codiceEer,
              codiceEer: codiceEer,
              descrizione: descrizioneEer
            },
            quantita: +quantita,
            destinazione: destinazione,
            sezione: {
              idSezione: SEZIONE.ID_TAB_RU
            },
            idPrevConsDett: idPrevConsDett,
            unitaMisura: {
              idUnitaMisura: 2
            },
            //stiamo aggiornando il dato di una prevConsDett, il BE vuole il booleano
            hasBeenUpdated: true
          };
          break;
      }
      //aggiorno la prevConsDett con i nuovi valori
      this.selectedTabObject.prevConsDett = this.tabsService.updatePrevConsDett(
        this.prevConsDett,
        newPrevConsDett
      );
      //devo riassociare il rapporto tra le prevConsDett che visualizzo e l'input
      this.prevConsDett = this.selectedTabObject.prevConsDett;
      this._updateTotalValue();
      this._updateDatasource();
      this.mrStoreService.setPropsStatus('processoChanged', true);
      this.sendEvent.emit('update');
    }
  }

  onDelete(prevConsDett: IPrevConsDettEntity) {
    const dialog = this.modalService.openDialog(PrevConsDettDeleteComponent, {
      sizeModal: 'xs',
      header: this.messages?.deleteDettMessage?.titoloMsg,
      showCloseButton: true,
      context: {
        idPrevConsDett: prevConsDett.idPrevConsDett,
        messageConfirm: this.messages?.deleteDettMessage,
        idPrevCons: this.idPrevCons,
      }
    });

    dialog.closed.pipe(untilDestroyed(this)).subscribe(() => {
      this.selectedTabObject.prevConsDett = this.tabsService.removePrevConsDett(this.prevConsDett, prevConsDett);
      //devo riassociare il rapporto tra le prevConsDett che visualizzo e l'input
      this.prevConsDett = this.selectedTabObject.prevConsDett;
      this._updateTotalValue();
      this._updateDatasource();

      // in base alla quantità di elementi nell'array calcolo se devo cambiare pagina nella tabella
      const totalCount = this.prevConsDett.length;
      const currentPage = Math.ceil(totalCount / this.itemsPerPage);
      if (totalCount % this.itemsPerPage === 0 && totalCount > 0) {
        this.dataSource.paging(currentPage, this.itemsPerPage);
      }
      this.mrStoreService.setPropsStatus('processoChanged', true);
      this.sendEvent.emit('delete');
    });
  }
  //END ACTIONS

  getUpdateFormControl(prevConsDett: IPrevConsDettEntity, name: string): FormControl {


    const formControl = this.updateForms.find((i) => i.id == prevConsDett?.idPrevConsDett);
    return formControl?.form.get(name);
  }

  /**
   * @description aggiorna il totale del tab in caso di cambiamenti
   */
  private _updateTotalValue() {
    this.selectedTabObject.totValue = this.tabsService.setTotali(this.prevConsDett);
  }

  private _setTabDatas(value: IRifiutiObject) {
    this.prevConsDett = value.prevConsDett;
    switch (this.tabName) {
      case 'rii':
        this.totLabel = TOT_LABEL.RII;
        this.labelCodiceEer = 'DICHIARAZIONI_MR.TABS.PROCESSO.TABLE.RII';
        break;
      case 'mat':
        this.totLabel = TOT_LABEL.MAT;
        this.labelCodiceEer = 'DICHIARAZIONI_MR.TABS.PROCESSO.TABLE.MAT_USCITA';
        break;
      case 'rru':
        this.totLabel = TOT_LABEL.RRU;
        this.labelCodiceEer =
          'DICHIARAZIONI_MR.TABS.PROCESSO.TABLE.RECUPERABILI';
        break;
      case 'ru':
        this.totLabel = TOT_LABEL.RU;
        this.labelCodiceEer = 'DICHIARAZIONI_MR.TABS.PROCESSO.TABLE.RIF_USCITA';
        break;
    }
  }

  /**
   * @description primo settaggio del datasource
   */
  private _setDatasource(): void {

    this.dataSource = new LocalPagedDataSource<IPrevConsDettEntity>({
      observable: this._getPrevConsDett.bind(this),
      tablePage: new TablePage()
    });
    this.dataSource.rows$.subscribe(
      (items:IPrevConsDettEntity[])=>{
        this.updateForms = items.map(row =>({id: row.idPrevConsDett, form: this._initCreateForm(row)}))
        this.obforms$ = this.updateForms.map(form => form.form.valueChanges);
        if(!!this.updateForms?.length) {
          this.action$.next();
        }
        this.loadingService.hide()
      }

    );

  }

  /**
   * @description aggiornamenti dell'observable (quindi delle prevConsDett) successivi al primo settaggio del datasource
   */
  private _updateDatasource(): void {

    this.dataSource.setObservable(this._getPrevConsDett.bind(this));
    this.dataSource.refresh();
    this.loadingService.hide()

  }

  private _initCreateForm(prevConsDett?: IPrevConsDettEntity): Form {
    const size = '12|6|6|6|6';
    const sizeS = '12|6|1|1|1';

    switch (this.tabName) {
      case 'rii':

        return new Form({
          header: { show: false },
          filter: false,
          validatorOrOpts: [
            this.mrService.notCodiceEerAndQuantitaValuedValidator(),
          ],
          controls: {
            codiceEer: new AutocompleteInput({
              placeholder: 'Seleziona codice eer',
              value: prevConsDett?.eer?.idEer ?? '',
              options:of(this.rifiutiList) as any,
            // options: this.tabsService.getComboEerObservable(this.annoTributoPrevCons) as any,

              size: sizeS,
              clearable: true,
              readonly: !this.isEditMode || !!prevConsDett?.idPrevConsDettRmr,
            }),
            descrizioneEer: new TextInput({
              placeholder: 'Descrizione',
              type: 'text',
              required: false,
              readonly: true,
              value: prevConsDett?.eer?.descrizione ?? null,
              size
            }),
            quantita: new TextInput({
              placeholder: 'Tonnellate',
              type: 'number',
              value: prevConsDett?.quantita?.toString() ?? '',
              readonly: !this.isEditMode,
              required: true,
              size: sizeS,
              validationStatus: [
                ValidationStatus.ERROR.CUSTOM(
                  (control) =>
                    control.touched &&
                    control.parent?.hasError('notCodiceValued'),
                  {
                    text: this.messages?.validationMessage?.testoMsg,
                  }
                )
              ]
            })
          }

        });
      case 'mat':
        return new Form({
          header: { show: false },
          filter: false,
          validatorOrOpts: [
            this.mrService.notDescMatUscitaAndQuantitaValuedValidator()
          ],
          controls: {
            descMatUscita: new TextInput({
              placeholder: 'Descrizione',
              type: 'text',
              value: prevConsDett?.descMatUscita ?? '',
              required: false,
              size,
              readonly: !this.isEditMode || !!prevConsDett?.idPrevConsDettRmr,
              maxLenght: 50,
            }),
            quantita: new TextInput({
              placeholder: 'Tonnellate',
              type: 'number',
              value: prevConsDett?.quantita?.toString() ?? '',
              readonly: !this.isEditMode,
              required: true,
              size: sizeS,
              validationStatus: [
                ValidationStatus.ERROR.CUSTOM(
                  (control) =>
                    control.touched &&
                    control.parent?.hasError('notDescMatUscitaValued'),
                  {
                    text: this.messages?.validationMessage?.testoMsg,
                  }
                )
              ]
            })
          }
        });
      case 'rru': case 'ru':
        return new Form({
          header: { show: false },
          filter: false,
          validatorOrOpts: [
            this.mrService.notCodiceEerAndQuantitaValuedValidator()
          ],
          controls: {
            codiceEer: new AutocompleteInput({
              placeholder: 'Seleziona codice eer',
              value: prevConsDett?.eer?.idEer ?? '',
              options: of(this.rifiutiList) as any,
            //  options: this.tabsService.getComboEerObservable(this.annoTributoPrevCons) as any,
              size: sizeS,
              clearable: true,
              readonly: !this.isEditMode || !!prevConsDett?.idPrevConsDettRmr,
            }),
            descrizioneEer: new TextInput({
              placeholder: 'Descrizione',
              type: 'text',
              required: false,
              value: prevConsDett?.eer?.descrizione ?? null,
              readonly: true,
            }),
            destinazione: new TextInput({
              placeholder: this.isEditMode ? 'Destinazione' : ' ',
              type: 'text',
              readonly: !this.isEditMode,
              value: prevConsDett?.destinazione ?? '',
              required: false
            }),
            quantita: new TextInput({
              placeholder: 'Tonnellate',
              type: 'number',
              value: prevConsDett?.quantita?.toString() ?? '',
              readonly: !this.isEditMode,
              required: true,
              size: sizeS,
              validationStatus: [
                ValidationStatus.ERROR.CUSTOM(
                  (control) =>
                    control.touched &&
                    control.parent?.hasError('notCodiceValued'),
                  {
                    text: this.messages?.validationMessage?.testoMsg,
                  }
                )
              ]
            })
          }
        });
    }

  }

  private _populateColumns(): void {
    let columns = [
      {
        prop: 'idPrevConsDett',
        name: this.i18n.translate('COMMONS.NUM'),
        cellClass: 'align-middle',
        sortable: false
      },
      {
        prop: 'eer.codiceEer',
        name: this.i18n.translate(this.labelCodiceEer),
        cellClass: 'align-middle w-25',
        cellTemplate: this.codiceEer,
        sortable: false
      },
      {
        prop: 'eer.descrizione',
        name: this.i18n.translate('COMMONS.DESCRIZIONE'),
        cellClass: 'align-middle w-25',
        cellTemplate: this.descrizioneEer,
        sortable: false
      },
      {
        prop: 'destinazione',
        name: this.i18n.translate('COMMONS.DESTINAZIONE'),
        cellClass: 'align-middle w-50',
        cellTemplate: this.destinazione,
        sortable: false
      },
      {
        prop: 'descMatUscita',
        name: this.i18n
          .translate('DICHIARAZIONI_MR.TABS.PROCESSO.TABLE.MAT_USCITA')

      ,
        cellClass: 'align-middle',
        cellTemplate: this.descMatUscita,
        sortable: false
      },
      {
        prop: 'quantita',
        name: this.i18n.translate('COMMONS.TONNELLATE'), //DICHIARAZIONI_MR.TABS.PROCESSO.TABLE.TONNELLATE_DICHIARAZIONE / TONNELLATE_RICHIESTA
        cellClass: 'align-middle',
        cellTemplate: this.quantita,
        sortable: false
      }
    ];

    //controllo qual'è il tabName ed imposto le colonne
    switch (this.tabName) {
      case 'rii':
        columns.splice(3, 2);
        this.columns = columns;
        break;
      case 'mat':
        columns.splice(1, 3);
        this.columns = columns;
        break;
      case 'rru':
      case 'ru':
        columns.splice(4, 1);
        this.columns = columns;
        break;
    }

    //se sono in inserimento/modifica di una D-MR legata ad una R-MR, vedo questa colonna
    if (this.isLegameRmr && this.isEditMode) {
      let index = this.columns.length - 1;
      let newColumn: TableColumn = {
        prop: 'quantitaRichiesta',
        pipe: this.pipeFormatQuantita,
        name: this.i18n
          .translate(
            'DICHIARAZIONI_MR.TABS.PROCESSO.TABLE.TONNELLATE_RICHIESTA'
          ),
          //.toUpperCase(),
        cellClass: 'align-middle',
        sortable: false
      };
      this.columns.splice(index, 0, newColumn);
    }

    //se sono in inserimento/modifica, vedo questa colonna
    if (this.isEditMode) {
      this.columns.push({
        name: this.i18n.translate('COMMONS.AZIONI'),
        cellTemplate: this.actionsTemplate,
        cellClass: 'align-middle',
        sortable: false
      });
    }
  }

  private _getPrevConsDett(): Observable<IPrevConsDettEntity[]> {
    return of(this.prevConsDett);
  }
}
