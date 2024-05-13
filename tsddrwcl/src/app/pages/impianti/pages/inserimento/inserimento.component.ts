import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { ImpiantiACL } from '@pages/impianti/models/acl.model';
import {
  AutocompleteInput,
  DateInput,
  Form,
  SelectOption,
  TextInput,
  ValidationStatus
} from '@shared/form';
import { ImpiantiService } from '@pages/impianti/services/impianti.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NotificationService } from '@shared/notification/notification.service';
import { ModalService } from '@shared/modal/modal.service';
import { I18nService } from '@eng-ds/translate';
import { LoadingService } from '@theme/layouts/loading.services';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { csiCatchError } from '@core/operators/catch-error.operator';
import { UtilityService } from '@core/services';
import { ConfirmExitComponent } from '@pages/impianti/components/confirm-exit/confirm-exit.component';
import { of } from 'rxjs';
import { TableColumn } from '@swimlane/ngx-datatable';
import { LocalPagedDataSource } from '@app/shared/table/models/local-paged-data-source';
import { Linea, LineaExtended } from '../../models/linea.model';
import { TablePage } from '@app/shared/table/models/table-page';
import { StoreGridMemoryService } from '../../services/store-grid-memory.service';
import { DatePipe, formatDate } from '@angular/common';
import { Atto } from '../../models/atto.model';
import { Impianto } from '../../models/impianto.model';

@UntilDestroy()
@Component({
  selector: 'app-inserimento',
  templateUrl: './inserimento.component.html',
  styleUrls: ['./inserimento.component.scss']
})
export class InserimentoComponent implements OnInit {
  acl: ImpiantiACL;
  helperTitle: string = '';
  // Form declaration
  formDatiImpianto: Form;
  formDatiSito: Form;

  @ViewChild('actionsTemplate') actionsTemplate: TemplateRef<any>;
  @ViewChild('lineaTemplate') lineaTemplate: TemplateRef<any>;
  @ViewChild('dataInizioValiditaTemplate') dataInizioValiditaTemplate: TemplateRef<any>;
  @ViewChild('dataFineValiditaTemplate') dataFineValiditaTemplate: TemplateRef<any>;

  @ViewChild('actionsTemplateAtto') actionsTemplateAtto: TemplateRef<any>;
  @ViewChild('numProvvedimentoTemplateAtto') numProvvedimentoTemplateAtto: TemplateRef<any>;
  @ViewChild('tipoProvvedimentoTemplateAtto') tipoProvvedimentoTemplateAtto: TemplateRef<any>;
  @ViewChild('dataProvvedimentoTemplateAtto') dataProvvedimentoTemplateAtto: TemplateRef<any>;
  @ViewChild('dataInizioAutorizzazioneTemplateAtto') dataInizioAutorizzazioneTemplateAtto: TemplateRef<any>;
  @ViewChild('dataFineAutorizzazioneTemplateAtto') dataFineAutorizzazioneTemplateAtto: TemplateRef<any>;

  comboLinee:SelectOption<string, string>[];
  // Form declaration
  createForm: Form;
  updateForms: Form[];
  rows:LineaExtended[];
  hasUnsavedRow:number[]=[];
  // Create section
  toCreate: LineaExtended = {

  };

  createFormAtto: Form;
  rowsAtti:Atto[];
  toCreateAtto: Atto = {

  };

  mandatoryMessage: any;
  comboProfili: any;
  isAtti: boolean;
  tabElements:any[]=[{
    title: 'IMPIANTI.CREATE.TABS.LINEA',
    link:'/impianti/inserimento'
  },{
    title: 'IMPIANTI.CREATE.TABS.ATTO',
    link:'/impianti/inserimento/atti'
  }];
  activeElement: string;
  columns: TableColumn[] = [];
  dataSource: LocalPagedDataSource<LineaExtended>;
  columnsAtti: TableColumn[] = [];
  dataSourceAtti: LocalPagedDataSource<Atto>;
  comboAtti:SelectOption<string, string>[];
  messageIdDataTesto: string;

  constructor(
    private service: ImpiantiService,
    private router: Router,
    private route: ActivatedRoute,
    private notification: NotificationService,
    private modalService: ModalService,
    private i18n: I18nService,
    private datePipe: DatePipe,
    private loadingService: LoadingService,
    private utility: UtilityService,
    private storeLineeGridMemoryService: StoreGridMemoryService<LineaExtended>
  ) {}

  ngOnInit(): void {
    this._initACL();
    this._initHelper();
    this.storeLineeGridMemoryService.reset();
    this.utility.getMessage('E002').pipe(untilDestroyed(this)).subscribe((message) => {
      this.messageIdDataTesto = ((message as any).content.testoMsg) as string;
    });

    this.utility
      .getMessage('E010')
      .pipe(untilDestroyed(this))
      .subscribe((message) => {
        this.mandatoryMessage = message;
        this._initForm();
        this.loadingService.hide();
      });
    this.service.getComboLinee().pipe(
        untilDestroyed(this)
      ).subscribe(
        response=>{
          this.comboLinee= response;
          this._initTable();
          this.updateDatasource();
          this._initCreateForm();
        }
      );

    this.service.getComboAtti().pipe(
        untilDestroyed(this)
      ).subscribe(
        response=>{
          console.log('sono io');
          console.log(response);
          this.comboAtti = response;
          this._initTableAtti();
          this.updateDatasourceAtti();
          this._initCreateFormAtti();
        }
      );
    const routeSnapshot = this.route.snapshot;
    const routeData = routeSnapshot.data;
    this.isAtti = routeData.isAtti;
    if(!this.isAtti){
      this.activeElement = '/impianti/inserimento';
    }else{
      this.activeElement = '/impianti/inserimento/atti';
    }
    this.rows = [];
    this.rowsAtti = [];
  }

  onCreate() {

    const formDatiImpianto = this.formDatiImpianto.value;
    const formDatiSito = this.formDatiSito.value;

    let linee = [];
    if(this.rows && this.rows.length>0){
      this.rows.forEach(l=>{
        if(l.isSottolinea){
          linee.push({
            idSottoLinea: l.idLineaFiglia,
            dataInizioValidita: l.dataInizioValidita,
            dataFineValidita: l.dataFineValidita,
            descLinea:l.descrizione,

          });
        }else{
          linee.push({
            idLinea: l.idLinea,
            dataInizioValidita: l.dataInizioValidita,
            dataFineValidita: l.dataFineValidita,
            descLinea:l.descrizione
          });
        }
      });
    }



    let atti = [];
    if(this.rowsAtti && this.rowsAtti.length>0){
      this.rowsAtti.forEach(a=>{
 //This is intentional
      });
    }

    let formAll={
      ...formDatiImpianto,
      ...formDatiSito,
    }
    let req:Impianto={
      denominazione: formAll['denominazione'],
      dataInizioValidita: formAll['dataInizioValidita'],
      dataFineValidita: formAll['dataFineValidita'],
      statoImpianto:{
        idStatoImpianto: formAll['idStatoImpianto'],
      },
      tipoImpianto: {
        idTipoImpianto: formAll['idTipoImpianto'],
      },
      gestore: {
        idGestore: formAll['idGestore'],
      },
      indirizzo:{
        indirizzo: formAll['indirizzo'],
        cap: formAll['cap'],
        comune: {
            idComune: formAll['idComune'],
            provincia: {
              idProvincia: formAll['idProvincia'],
            }
        },
        sedime: {
            idSedime: formAll['sedime'],
        },
      },
      linee: linee,
      atti: this.rowsAtti
    }
    this.loadingService.show();

    this.service
      .createImpianto(req)
      .pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response) => {
        console.log(response);
        this.notification.info({
          title: response.message.titoloMsg,
          text: response.message.testoMsg
        });
        this.loadingService.hide();
        this.router.navigate(['impianti']);
      });
  }

  onBack() {
    if (this.isEditing()) {
      this.utility.getMessage('A001').subscribe((result: any) => {
        const { content } = result;
        if (content) {
          const dialog = this._preventDataLose(content);

          dialog.closed
            .pipe(untilDestroyed(this))
            .subscribe(() =>
              this.router.navigate([
                'impianti'
              ])
            );
        }
      });
      return;
    }

    this.router.navigate(['impianti']);
  }


  onChangeActiveTab(tabElement){
    this.activeElement = tabElement.link;
    if(this.activeElement=='/impianti/inserimento'){
      this.isAtti = false;
    }else{
      this.isAtti = true;
    }
  }

  isEditing():boolean {
    if(
      !this.service.isEmpyForm(this.formDatiImpianto) ||
      !this.service.isEmpyForm(this.formDatiSito) ||
      !this.service.isEmpyForm(this.createForm) ||
      !this.service.isEmpyForm(this.createFormAtto) ||
      this.rows.length>0 ||
      this.rowsAtti.length> 0
    ){
      return true;
    }
    return false;
  }

  private _initHelper(): void {
    this.utility.getNotaInfo('INSERISCI').subscribe((result: any) => {
      if (result.content) {
        this.helperTitle = result.content.testoInfo;
      }
    });
  }

  private _preventDataLose(content: any) {
    const dialog = this.modalService.openDialog(ConfirmExitComponent, {
      sizeModal: 'xs',
      header: content.titoloMsg,
      showCloseButton: true,
      context: { messageConfirm: content }
    });

    return dialog;
  }

  private _initACL(): void {
    this.service
      .getStoredAcl()
      .pipe(untilDestroyed(this))
      .subscribe((acl) => (this.acl = acl));
  }

  private _initForm() {
    const size = '12|6|6|6|6';
    this.formDatiImpianto = new Form({
      header: { show: false },
      filter: false,
      validatorOrOpts: [
        this.service.dateValidator(),
      ],
      controls: {
        idGestore: new AutocompleteInput({
          label: 'IMPIANTI.FORMDATIIMPIANTO.IDGESTORE.LABEL_',
          placeholder: 'IMPIANTI.FORMDATIIMPIANTO.IDGESTORE.PLACEHOLDER',
          options: this.service.getComboGestori() as any,
          size,
          required:true,
          clearable: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            }),
          ],
        }),
        denominazione: new TextInput({
          label: 'IMPIANTI.FORMDATIIMPIANTO.DENOMINAZIONE.LABEL',
          placeholder: 'IMPIANTI.FORMDATIIMPIANTO.DENOMINAZIONE.PLACEHOLDER',
          type: 'text',
          size,
          clearable: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            }),
          ],
          required: true,
        }),
        idTipoImpianto: new AutocompleteInput({
          label: 'IMPIANTI.FORMDATIIMPIANTO.IDTIPOIMPIANTO.LABEL',
          placeholder: 'IMPIANTI.FORMDATIIMPIANTO.IDTIPOIMPIANTO.PLACEHOLDER',
          options: this.service.getComboImpiantiTipi() as any,
          size,
          required:true,
          clearable: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            }),
          ]
        }),
        idStatoImpianto: new AutocompleteInput({
          label: 'IMPIANTI.FORMDATIIMPIANTO.IDSTATOIMPIANTO.LABEL',
          placeholder: 'IMPIANTI.FORMDATIIMPIANTO.IDSTATOIMPIANTO.PLACEHOLDER',
          options: this.service.getComboImpiantiStati() as any,
          size,
          required:true,
          clearable: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            }),
          ]
        }),
        dataInizioValidita: new DateInput({
          label: 'IMPIANTI.FORMDATIIMPIANTO.DATAINIZIOVALIDITA.LABEL',
          placeholder: 'IMPIANTI.FORMDATIIMPIANTO.DATAINIZIOVALIDITA.PLACEHOLDER',
          size,
          clearable: true,
          required:true,
          value: this.datePipe.transform(new Date(), 'yyyy-MM-dd'),
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            }),
          ]
        }),
        dataFineValidita: new DateInput({
          label: 'IMPIANTI.FORMDATIIMPIANTO.DATAFINEVALIDITA.LABEL',
          placeholder: 'IMPIANTI.FORMDATIIMPIANTO.DATAFINEVALIDITA.PLACEHOLDER',
          size,
          clearable: true,
          validationStatus: [
            ValidationStatus.ERROR.CUSTOM(
              (control) => control.touched && control.parent?.hasError('dataFineValidita'),
              {
                text: this.messageIdDataTesto
              }
            )
          ],
        })
      }
    });
    this.formDatiSito = new Form({
      header: { show: false },
      filter: false,
      controls: {
        sedime: new AutocompleteInput({
          label: 'IMPIANTI.FORMDATISITO.IDSEDIME.LABEL',
          placeholder: 'IMPIANTI.FORMDATISITO.IDSEDIME.PLACEHOLDER',
          options: this.service.getComboSedimi() as any,
          size,
          clearable: true,
          required:true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            }),
          ]
        }),
        indirizzo: new TextInput({
          label: 'IMPIANTI.FORMDATISITO.INDIRIZZO.LABEL',
          placeholder: 'IMPIANTI.FORMDATISITO.INDIRIZZO.PLACEHOLDER',
          type: 'text',
          size,
          clearable: true,
          required:true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            }),
          ]
        }),
        idProvincia: new AutocompleteInput({
          label: 'IMPIANTI.FORMDATISITO.PROVINCIA.LABEL',
          placeholder: 'IMPIANTI.FORMDATISITO.PROVINCIA.PLACEHOLDER',
          options: this.service.getComboProvince() as any,
          valueChange: (idProvincia: number) => {
            if (idProvincia){
              (
                this.formDatiSito.get('idComune') as AutocompleteInput
              ).setOptions(
                this.service.getComboComuni(+idProvincia) as any
              );
            }else{
              (
                this.formDatiSito.get('idComune') as AutocompleteInput
              ).setOptions(
                (of()) as any
              );
            }
          },
          size,
          clearable: true,
          required:true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            }),
          ]
        }),
        idComune: new AutocompleteInput({
          label: 'IMPIANTI.FORMDATISITO.COMUNE.LABEL',
          placeholder: 'IMPIANTI.FORMDATISITO.COMUNE.PLACEHOLDER',
          options:  (of()) as any,
          valueChange: (idComune: number) => {
            if (idComune){
              let cap = this.service.getCap(+idComune);
              this.formDatiSito.get('cap').setValue(cap);
            }
          },
          size,
          clearable: true,
          required:true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            }),
          ]
        }),
        cap: new TextInput({
          label: 'IMPIANTI.FORMDATISITO.CAP.LABEL',
          placeholder: 'IMPIANTI.FORMDATISITO.CAP.PLACEHOLDER',
          type: 'text',
          size,
          clearable: true
        })
      }
    });
  }
  /*  gestione tabella linee e azioni relative */
  private _initTable(): void {
    this.columns = [
      {
        prop: 'idLinea',
        name: this.i18n.translate('IMPIANTI.TABLELINEE.COLUMNS.LINEA'),
        cellClass: 'align-middle',
        cellTemplate: this.lineaTemplate,
        sortable: false
      },
      {
        prop: 'dataInizioValidita',
        name: this.i18n.translate('IMPIANTI.TABLELINEE.COLUMNS.DATAINIZIOVALIDITA'),
        cellTemplate: this.dataInizioValiditaTemplate,
        cellClass: 'align-middle min-w-140',
        sortable: false
      },
      {
        prop: 'dataFineValidita',
        name: this.i18n.translate('IMPIANTI.TABLELINEE.COLUMNS.DATAFINEVALIDITA'),
        cellTemplate: this.dataFineValiditaTemplate,
        cellClass: 'align-middle min-w-140',
        sortable: false
      },
      {
        name: this.i18n.translate('IMPIANTI.TABLELINEE.COLUMNS.AZIONI'),
        cellTemplate: this.actionsTemplate,
        cellClass: 'align-middle',
        sortable: false
      }
    ];
  }
  datasourceElementsChangedLinee(linee:LineaExtended[]){
    const size = '12|6|6|6|6';
    this.updateForms = [];
    this.rows = linee;
    linee.forEach(row=>{
      // row.idLinea+'-'+row.idSottoLinea
      let id = (row.isSottolinea)?'l_'+row.idLinea+'_s_'+row.idLineaPadre:'l_'+row.idLinea;
      this.updateForms[id] = new Form({
        header: { show: false },
        filter: true,
        validatorOrOpts: [
          this.service.dateValidator(),
        ],
        controls: {
          idLinea: new AutocompleteInput({
            placeholder: 'IMPIANTI.FORMLINEA.FIELDS.LINEA.PLACEHOLDER',
            options: of(this.comboLinee),
            size,
            clearable: true,
            //value: row.idLinea,
            required: true,
          }),
          dataInizioValidita: new DateInput({
            placeholder: 'IMPIANTI.FORMLINEA.FIELDS.DATAINIZIOVALIDITA.PLACEHOLDER',
            size: size,
            value: row.dataInizioValidita?formatDate(row.dataInizioValidita, 'yyyy-MM-dd', 'en'):null,
            clearable: true
          }),
          dataFineValidita: new DateInput({
            placeholder: 'IMPIANTI.FORMLINEA.FIELDS.DATAFINEVALIDITA.PLACEHOLDER',
            size: size,
            value: row.dataFineValidita?formatDate(row.dataFineValidita, 'yyyy-MM-dd', 'en'):null,
            clearable: true,
            validationStatus: [
              ValidationStatus.ERROR.CUSTOM(
                (control) => control.touched && control.parent?.hasError('dataFineValidita'),
                {
                  text: this.messageIdDataTesto
                }
              )
            ]
          }),
        }
      });
    });
  }
  hasUnsavedChangeRow(row: Linea):boolean{
    const index = this.hasUnsavedRow.indexOf(row.idLineaImpianto, 0);
    if (index > -1) {
      return true;
    }else{
      return false;
    }
  }
  updateDatasource() {
    if (!this.dataSource) {
      this.dataSource = new LocalPagedDataSource<Linea>({
        observable: this.storeLineeGridMemoryService.getStored.bind(this.storeLineeGridMemoryService),
        tablePage: new TablePage()
      });
      this.dataSource.rows$.subscribe(
        (items:LineaExtended[])=>{
          this.rows = items;
        }
      );
      return;
    }else{
      this.dataSource.rows$.subscribe(
        (items:LineaExtended[])=>{
          this.rows = items;
        }
      );
    }

    this.dataSource.setObservable(
      this.storeLineeGridMemoryService.getStored.bind(this.storeLineeGridMemoryService),
    );
    /*this.hasUnsavedChange = false;
    this.hasUnsavedCreate = false;
    this.hasUnsavedRow = [];
    this.hasErroRows = [];*/
    this.dataSource.refresh();
  }
  private _initCreateForm() {
    const size = '12|6|6|6|6';
    this.createForm = new Form({
      header: { show: false },
      filter: true,
      validatorOrOpts: [
        this.service.dateValidator(),
      ],
      controls: {
        idLinea: new AutocompleteInput({
          placeholder: 'IMPIANTI.FORMLINEA.FIELDS.LINEA.PLACEHOLDER',
          options: of(this.comboLinee),
          valueChange: (idLineaExtend: string) => {
            let descrizione='';
            if(idLineaExtend){
              ((this.comboLinee as any).content).forEach(
                item=>{
                  if(item && item.id===idLineaExtend){
                    descrizione = item.value;
                    if(idLineaExtend.indexOf('_')>-1){
                      const parts = idLineaExtend.split('_');
                      this.toCreate.idLineaFiglia = +parts[0];
                      this.toCreate.idLineaPadre = +parts[1];
                      this.toCreate.isSottolinea = true;
                    }else{
                      this.toCreate.idLinea = +idLineaExtend;
                      this.toCreate.idLineaFiglia = -1;
                      this.toCreate.idLineaPadre = -1;
                      this.toCreate.isSottolinea = false;
                    }
                  }
                }
              );
            }
            this.toCreate.descrizione = descrizione;
          },
          size,
          clearable: true,
          required: true,
        }),
        dataInizioValidita: new DateInput({
          placeholder: 'IMPIANTI.FORMLINEA.FIELDS.DATAINIZIOVALIDITA.PLACEHOLDER',
          size: size,
          clearable: true
        }),
        dataFineValidita: new DateInput({
          placeholder: 'PROFILI.FORM.FIELDS.DATAFINEVALIDITA.PLACEHOLDER',
          size: size,
          clearable: true,
          validationStatus: [
            ValidationStatus.ERROR.CUSTOM(
              (control) => control.touched && control.parent?.hasError('dataFineValidita'),
              {
                text: this.messageIdDataTesto
              }
            )
          ]
        }),
      }
    });
    this.createForm.valueChanges.subscribe(x => {

     // this._verifyProfiloCreate(x);
    });
  }
  private _cleanForm() {
    this.createForm.reset();
    // non reinizializza gli errori, forzatura
    this._initCreateForm();
    //this.createEnabled = false;
    //this.hasUnsavedCreate = false;
  }
  createLinea(): void{

    if (this.createForm.valid) {
      if(this.createForm.get('dataInizioValidita').value === null){
        this.createForm.get('dataInizioValidita').setValue(this.datePipe.transform(new Date(), 'yyyy-MM-dd'))
      }
      this.storeLineeGridMemoryService.addLinea(({...this.toCreate,...this.createForm.value} as LineaExtended));
      this._comboLineFollowGrid(({...this.toCreate,...this.createForm.value} as LineaExtended),true);
      this.dataSource.refresh();
      this._cleanForm();
    }
  }
  deleteLinea(linea:LineaExtended): void{
    this.storeLineeGridMemoryService.removeLinea(linea);
    this._comboLineFollowGrid(linea,false);
    this.dataSource.refresh();
  }
  _comboLineFollowGrid(linea:LineaExtended ,mode:boolean){
    if(mode==false && linea.isSottolinea){
      ((this.comboLinee as any).content).push(
        {
          id:linea.idLinea,
          value:linea.descrizione,
          additionalValue: linea.idLineaPadre
        }
      );
    }
    if(mode==false && !linea.isSottolinea){
      ((this.comboLinee as any).content).push(
        {
          id:linea.idLinea,
          value:linea.descrizione
        }
      );
    }
    if(mode==true){
      ((this.comboLinee as any).content).forEach((item, index, object)=>{
        if(item && item.id===linea.idLinea){
          object.splice(index, 1);
        }
      });
    }
  }


  /*  gestione tabella atti e azioni relative */
  private _initTableAtti(): void {
    this.columnsAtti = [
      {
        prop: 'numProvvedimento',
        name: this.i18n.translate('IMPIANTI.TABLEATTI.COLUMNS.NUMERO'),
        cellClass: 'align-middle',
        cellTemplate: this.numProvvedimentoTemplateAtto,
        sortable: false
      },
      {
        prop: 'dataProvvedimento',
        name: this.i18n.translate('IMPIANTI.TABLEATTI.COLUMNS.DATAPROVVEDIMENTO'),
        cellTemplate: this.dataProvvedimentoTemplateAtto,
        cellClass: 'align-middle min-w-140',
        sortable: false
      },
      {
        prop: 'tipoProvvedimento',
        name: this.i18n.translate('IMPIANTI.TABLEATTI.COLUMNS.TIPOPROVVEDIMENTO'),
        cellTemplate: this.tipoProvvedimentoTemplateAtto,
        cellClass: 'align-middle min-w-140',
        sortable: false
      },
      {
        prop: 'dataInizioAutorizzazione',
        name: this.i18n.translate('IMPIANTI.TABLEATTI.COLUMNS.DATADECORRENZA'),
        cellTemplate: this.dataInizioAutorizzazioneTemplateAtto,
        cellClass: 'align-middle min-w-140',
        sortable: false
      },
      {
        prop: 'dataFineAutorizzazione',
        name: this.i18n.translate('IMPIANTI.TABLEATTI.COLUMNS.DATASCADENZA'),
        cellTemplate: this.dataFineAutorizzazioneTemplateAtto,
        cellClass: 'align-middle min-w-140',
        sortable: false
      },
      {
        name: this.i18n.translate('IMPIANTI.TABLEATTI.COLUMNS.AZIONI'),
        cellTemplate: this.actionsTemplateAtto,
        cellClass: 'align-middle',
        sortable: false
      }
    ];
  }

  updateDatasourceAtti() {

    if (!this.dataSourceAtti) {
      this.dataSourceAtti = new LocalPagedDataSource<Atto>({
        observable: this.storeLineeGridMemoryService.getStoredAtti.bind(this.storeLineeGridMemoryService),
        tablePage: new TablePage()
      });
      this.dataSourceAtti.rows$.subscribe(
        (items:Atto[])=>{
          this.rowsAtti = items;
        }
      );
      return;
    }else{
      this.dataSourceAtti.rows$.subscribe(
        (items:Atto[])=>{
          this.rowsAtti = items;
        }
      );
    }

    this.dataSourceAtti.setObservable(
      this.storeLineeGridMemoryService.getStoredAtti.bind(this.storeLineeGridMemoryService),
    );

    this.dataSourceAtti.refresh();
  }
  private _initCreateFormAtti() {
    const size = '12|6|6|6|6';
    this.createFormAtto = new Form({
      header: { show: false },
      filter: true,
      validatorOrOpts: [
        this.service.dateValidator('dataInizioAutorizzazione','dataFineAutorizzazione'),
      ],
      controls: {
        numProvvedimento:new TextInput({
          label: 'IMPIANTI.FORMATTI.FIELDS.NUMERO.LABEL',
          placeholder: 'IMPIANTI.FORMATTI.FIELDS.NUMERO.PLACEHOLDER',
          type: 'text',
          size,
          clearable: true,
          required: true,
        }),
        dataProvvedimento: new DateInput({
          placeholder: 'IMPIANTI.FORMATTI.FIELDS.DATAINIZIOVALIDITA.PLACEHOLDER',
          size: size,
          clearable: true,
        }),
        tipoProvvedimento: new AutocompleteInput({
          placeholder: 'IMPIANTI.FORMATTI.FIELDS.TIPOPROVVEDIMENTO.PLACEHOLDER',
          options: of(this.comboAtti),
          valueChange: (id: string) => {
            let tipoProvvedimento=null;
            if(id){
              ((this.comboAtti as any).content).forEach(
                item=>{
                  if(item && item.id===id){
                    tipoProvvedimento = {idTipoProvvedimento:id, descTipoProvvedimento:item.value};
                  }
                }
              );
            }
            this.toCreateAtto.tipoProvvedimento = tipoProvvedimento;
          },
          size,
          clearable: true,
          required: true,
        }),
        dataInizioAutorizzazione: new DateInput({
          placeholder: 'IMPIANTI.FORMATTI.FIELDS.DATAINIZIOVALIDITA.PLACEHOLDER',
          size: size,
          clearable: true,
          required: true,
        }),
        dataFineAutorizzazione: new DateInput({
          placeholder: 'IMPIANTI.FORMATTI.FIELDS.DATAFINEVALIDITA.PLACEHOLDER',
          size: size,
          clearable: true,
          validationStatus: [
            ValidationStatus.ERROR.CUSTOM(
              (control) => control.touched && control.parent?.hasError('dataFineValidita'),
              {
                text: this.messageIdDataTesto
              }
            )
          ]
        }),
      }
    });
    this.createFormAtto.valueChanges.subscribe(x => {
     // this._verifyProfiloCreate(x);
    });
  }

  private _cleanFormAtti() {
    this.createFormAtto.reset();
    // non reinizializza gli errori, forzatura
    this._initCreateFormAtti();
  }

  createAtto(): void{

    if (this.createFormAtto.valid) {
      this.storeLineeGridMemoryService.addAtto({...this.createFormAtto.value,...this.toCreateAtto});
      this.dataSourceAtti.refresh();
      this._cleanFormAtti();
    }
  }

  deleteAtto(atto:Atto): void{
    this.storeLineeGridMemoryService.removeAtto(atto);
    this.dataSource.refresh();
  }

}
