import { Component, Input, OnInit, TemplateRef, ViewChild } from '@angular/core';
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
import { DatePipe, formatDate } from '@angular/common';
import { UtilityService } from '@core/services';
import { ConfirmExitComponent } from '@pages/impianti/components/confirm-exit/confirm-exit.component';
import { forkJoin, of } from 'rxjs';
import { Impianto, Indirizzo } from '../../models/impianto.model';
import { LineaExtended } from '../../models/linea.model';
import { Atto } from '../../models/atto.model';
import { TableColumn } from '@swimlane/ngx-datatable';
import { LocalPagedDataSource } from '@app/shared/table/models/local-paged-data-source';
import { TablePage } from '@app/shared/table/models/table-page';
import { FormControl } from '@angular/forms';
import { ConfirmDeleteLineaComponent } from '@pages/impianti/components/confirm-delete-linea/confirm-delete-linea.component';
import { ConfirmDeleteAttoComponent } from '@pages/impianti/components/confirm-delete-atto/confirm-delete-atto.component';
import { ConfirmEditComponent } from '@pages/impianti/components/confirm-edit/confirm-edit.component';
import { ConfirmChangeGestoreComponent } from '../../components/confirm-change-gestore/confirm-change-gestore.component';

@UntilDestroy()
@Component({
  selector: 'app-edit-impianto',
  templateUrl: './edit-impianto.component.html',
  styleUrls: ['./edit-impianto.component.scss']
})
export class EditImpiantoComponent implements OnInit {
  @Input() impianto: Impianto;
  acl: ImpiantiACL;
  helperTitle: string = '';
  // Form declaration
  formDatiImpianto: Form;
  formDatiSito: Form;
  isEditing = false;
  messageIdDataTesto: string;
  isEmbed:boolean = false;

  @ViewChild('actionsTemplate', {static: true}) actionsTemplate: TemplateRef<any>;
  @ViewChild('lineaTemplate', {static: true}) lineaTemplate: TemplateRef<any>;
  @ViewChild('dataInizioValiditaTemplate', {static: true}) dataInizioValiditaTemplate: TemplateRef<any>;
  @ViewChild('dataFineValiditaTemplate', {static: true}) dataFineValiditaTemplate: TemplateRef<any>;

  @ViewChild('actionsTemplateAtto', {static: true}) actionsTemplateAtto: TemplateRef<any>;
  @ViewChild('numProvvedimentoTemplateAtto', {static: true}) numProvvedimentoTemplateAtto: TemplateRef<any>;
  @ViewChild('tipoProvvedimentoTemplateAtto', {static: true}) tipoProvvedimentoTemplateAtto: TemplateRef<any>;
  @ViewChild('dataProvvedimentoTemplateAtto', {static: true}) dataProvvedimentoTemplateAtto: TemplateRef<any>;
  @ViewChild('dataInizioAutorizzazioneTemplateAtto', {static: true}) dataInizioAutorizzazioneTemplateAtto: TemplateRef<any>;
  @ViewChild('dataFineAutorizzazioneTemplateAtto', {static: true}) dataFineAutorizzazioneTemplateAtto: TemplateRef<any>;

  comboLinee:SelectOption<string, string>[];
  // Form declaration
  createForm: Form;
  updateForms: {id:string, form:Form, changed:boolean}[];
  rows:LineaExtended[];
  hasUnsavedRow:number[]=[];
  // Create section
  toCreate: LineaExtended = {

  };

  createFormAtto: Form;
  updateFormsAtti: {id:string, form:Form, changed:boolean}[];
  rowsAtti:Atto[];
  toCreateAtto: Atto = {

  };

  mandatoryMessage: any;
  deleteLineaMessage: any;
  deleteAttoMessage: any;
  changeGestoreMessage: any;
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

  currentImpianto:Impianto;

  isReadonly:boolean = false;

  constructor(
    private service: ImpiantiService,
    private router: Router,
    private notification: NotificationService,
    private modalService: ModalService,
    private i18n: I18nService,
    private datePipe: DatePipe,
    private loadingService: LoadingService,
    private utility: UtilityService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this._initACL();
    this._initHelper();
    // caso in cui viene embeddato il gestore in sola lettura
    if(this.impianto){
      this.currentImpianto = this.impianto;
      this.isReadonly = true;
      this.isEmbed = true;
      // per non avere problemi nel form in sola lettura
      this.mandatoryMessage = {
        content:{
          text: 'Campo obbligatorio'
        }
      }

      forkJoin([
        this.service.getComboLineeImpianto(this.currentImpianto.idImpianto).pipe(untilDestroyed(this)),
        this.service.getComboAtti().pipe(untilDestroyed(this)),
      ]).subscribe((results) => {

        // parte delle linee
        this.comboLinee = results[0] as SelectOption<string, string>[];
        this._initTable();
        this.updateDatasource();
        this._initCreateForm();

        this.comboAtti = results[1] as SelectOption<string, string>[];
        this._initTableAtti();
        this.updateDatasourceAtti();
        this._initCreateFormAtti();

        this._initForm(this.currentImpianto);
        this.activeElement = '/impianti/inserimento';
        this.isAtti = false;
        this.rows = [];
        this.rowsAtti = [];
        this.loadingService.hide();
      });
    }

    // caso in cui si visualizza il routing del gestore
    this.route.data.pipe(untilDestroyed(this)).subscribe((data: any) => {
      if(data && data['impianto']){
        this.currentImpianto = data['impianto'].content;

        this.isReadonly = this.currentImpianto.readOnly;

        if(!this.acl.content.update && this.acl.content.read){
          this.isReadonly = true;
        }

        forkJoin([
          this.utility.getMessage('E010').pipe(untilDestroyed(this)),
          this.utility.getMessage('A013').pipe(untilDestroyed(this)),
          this.utility.getMessage('A014').pipe(untilDestroyed(this)),
          this.utility.getMessage('E002').pipe(untilDestroyed(this)),
          this.utility.getMessage('A012').pipe(untilDestroyed(this)),
          this.service.getComboLineeImpianto(this.currentImpianto.idImpianto).pipe(untilDestroyed(this)),
          this.service.getComboAtti().pipe(untilDestroyed(this)),
        ]).subscribe((results) => {
          this.mandatoryMessage = results[0];

          this.deleteLineaMessage = (results[1] as any).content;

          this.deleteAttoMessage = (results[2] as any).content;

          this.messageIdDataTesto = ((results[3] as any).content.testoMsg) as string;

          this.changeGestoreMessage = (results[4] as any).content;

          // parte delle linee
          this.comboLinee = results[5] as SelectOption<string, string>[];
          this._initTable();
          this.updateDatasource();
          this._initCreateForm();

          this.comboAtti = results[6] as SelectOption<string, string>[];
          this._initTableAtti();
          this.updateDatasourceAtti();
          this._initCreateFormAtti();

          this._initForm(this.currentImpianto);
          this.loadingService.hide();
        });

          const routeSnapshot = this.route.snapshot;
          const routeData = routeSnapshot.data;

          this.activeElement = '/impianti/inserimento';
          this.isAtti = routeData.isAtti;
          this.rows = [];
          this.rowsAtti = [];
        }
      });

      this.loadingService.hide();

  }

  onEdit() {
    this.utility.getMessage('A14').subscribe((result: any) => {
      const { content } = result;
      if (content) {
        const dialog = this.modalService.openDialog(ConfirmEditComponent, {
          sizeModal: 'xs',
          header: content.titoloMsg,
          showCloseButton: true,
          context: { messageConfirm: content }
        });

        dialog.closed
          .pipe(untilDestroyed(this))
          .subscribe(() => this.confirmUpdate());
      }
    });
  }


  confirmUpdate() {
    const formDatiImpianto = this.formDatiImpianto.value;
    const formDatiSito = this.formDatiSito.value;
    const indirizzoChanged= false;
    let formAll={
      ...formDatiImpianto,
      ...formDatiSito,
    }

    let req:Impianto={
      idImpianto: this.currentImpianto.idImpianto,
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
      }
    }
    const indirizzo={
      idIndirizzo: this.currentImpianto.indirizzo.idIndirizzo,
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
      }
    };
    if(this.isIndirizzoChanged(indirizzo)){
      req.indirizzo=indirizzo;
    }
    console.log(req);

    this.loadingService.show();
    this.service
      .editImpianto(req)
      .pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response) => {
        this.notification.info({
          title: response.message.titoloMsg,
          text: response.message.testoMsg
        });
        this.setIsEditing(false);
        this.loadingService.hide();
        this.router.navigate(['impianti','lista']);
      });
  }

  isIndirizzoChanged(indirizzo:Indirizzo):boolean{
    if(this.currentImpianto.indirizzo.indirizzo!=indirizzo.indirizzo
      || this.currentImpianto.indirizzo.comune.idComune!=+indirizzo.comune.idComune
      || this.currentImpianto.indirizzo.comune.cap!=indirizzo.comune.cap
      || this.currentImpianto.indirizzo.comune.provincia.idProvincia!=+indirizzo.comune.provincia.idProvincia
      || this.currentImpianto.indirizzo.sedime.idSedime!=+indirizzo.sedime.idSedime
      ){
        return true;
    }
    return false;
  }

  onBack() {
    if (this.isEditing) {
      this.utility.getMessage('A001').subscribe((result: any) => {
        const { content } = result;
        if (content) {
          const dialog = this._preventDataLose(content);

          dialog.closed
            .pipe(untilDestroyed(this))
            .subscribe(() =>
              this.router.navigate([
                'impianti',
                'lista'
              ])
            );
        }
      });
      return;
    }

    this.router.navigate([
      'impianti',
      'lista'
    ]);
  }

  onChangeActiveTab(tabElement){
    this.activeElement = tabElement.link;
    if(this.activeElement.indexOf('atti')>-1){
      this.isAtti = true;
    }else{
      this.isAtti = false;
    }
  }

  setIsEditing(value) {
    this.isEditing = value;
  }

  private _initHelper(): void {
    // TODO change codNotaInfo
    this.utility.getNotaInfo('MODIFICA').subscribe((result: any) => {
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

  confirmChangeGestore(): void{
    const dialog = this.modalService.openDialog(ConfirmChangeGestoreComponent, {
      sizeModal: 'xs',
      header: this.changeGestoreMessage.titoloMsg,
      showCloseButton: true,
      context: { messageConfirm: this.changeGestoreMessage }
    });

    dialog.closed.pipe(untilDestroyed(this)).subscribe((a=null) => {
      if(!a){
        (this.formDatiImpianto.get('idGestore') as AutocompleteInput).setValue(this.currentImpianto.gestore.idGestore.toString());
      }
    });
  }

  private _initForm(currentValue:Impianto) {
    const size = '12|6|6|6|6';
    this.formDatiImpianto = new Form({
      header: { show: false },
      validatorOrOpts: [
        this.service.dateValidator(),
      ],
      filter: false,
      controls: {
        idGestore: new AutocompleteInput({
          label: 'IMPIANTI.FORMDATIIMPIANTO.IDGESTORE.LABEL',
          placeholder: !this.isReadonly ? 'IMPIANTI.FORMDATIIMPIANTO.IDGESTORE.PLACEHOLDER' : ' ',
          options: this.service.getComboGestori() as any,
          size,
          required:true,
          clearable: true,
          value: currentValue.gestore.idGestore.toString(),
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            }),
          ],
          valueChange: (idGestore: string) => {
            if(idGestore && +idGestore!==+this.currentImpianto.gestore.idGestore){
              this.confirmChangeGestore();
            }
          },
          readonly:this.isReadonly
        }),
        denominazione: new TextInput({
          label: 'IMPIANTI.FORMDATIIMPIANTO.DENOMINAZIONE.LABEL',
          placeholder: !this.isReadonly ? 'IMPIANTI.FORMDATIIMPIANTO.DENOMINAZIONE.PLACEHOLDER' : ' ',
          type: 'text',
          size,
          clearable: true,
          value: currentValue.denominazione,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            }),
          ],
          required: true,
          readonly:this.isReadonly
        }),
        idTipoImpianto: new AutocompleteInput({
          label: 'IMPIANTI.FORMDATIIMPIANTO.IDTIPOIMPIANTO.LABEL',
          placeholder: !this.isReadonly ? 'IMPIANTI.FORMDATIIMPIANTO.IDTIPOIMPIANTO.PLACEHOLDER' : ' ',
          options: this.service.getComboImpiantiTipi() as any,
          size,
          required:true,
          clearable: true,
          value: currentValue.tipoImpianto.idTipoImpianto.toString(),
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            }),
          ],
          readonly:this.isReadonly
        }),
        idStatoImpianto: new AutocompleteInput({
          label: 'IMPIANTI.FORMDATIIMPIANTO.IDSTATOIMPIANTO.LABEL',
          placeholder: !this.isReadonly ? 'IMPIANTI.FORMDATIIMPIANTO.IDSTATOIMPIANTO.PLACEHOLDER' : ' ',
          options: this.service.getComboImpiantiStati() as any,
          size,
          required:true,
          clearable: true,
          value: currentValue.statoImpianto.idStatoImpianto.toString(),
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            }),
          ],
          readonly:this.isReadonly
        }),
        dataInizioValidita: new DateInput({
          label: 'IMPIANTI.FORMDATIIMPIANTO.DATAINIZIOVALIDITA.LABEL',
          placeholder: !this.isReadonly ? 'IMPIANTI.FORMDATIIMPIANTO.DATAINIZIOVALIDITA.PLACEHOLDER' : ' ',
          size,
          clearable: true,
          required:true,
          value: currentValue.dataInizioValidita
          ? this.datePipe.transform(currentValue.dataInizioValidita, 'yyyy-MM-dd')
          : this.datePipe.transform(new Date(), 'yyyy-MM-dd'),
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            }),
          ],
          readonly:this.isReadonly
        }),
        dataFineValidita: new DateInput({
          label: 'IMPIANTI.FORMDATIIMPIANTO.DATAFINEVALIDITA.LABEL',
          placeholder: !this.isReadonly ? 'IMPIANTI.FORMDATIIMPIANTO.DATAFINEVALIDITA.PLACEHOLDER' : ' ',
          size,
          value: currentValue.dataFineValidita
          ? this.datePipe.transform(currentValue.dataFineValidita, 'yyyy-MM-dd')
          : '',
          clearable: true,
          readonly:this.isReadonly,
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
    let cap=null;
    if(currentValue.indirizzo.cap){
      cap = currentValue.indirizzo.cap;
    }else if(this.isReadonly && currentValue.indirizzo.comune.cap){
      cap = currentValue.indirizzo.comune.cap;
    }
    this.formDatiSito = new Form({
      header: { show: false },
      filter: false,
      controls: {
        sedime: new AutocompleteInput({
          label: 'IMPIANTI.FORMDATISITO.IDSEDIME.LABEL',
          placeholder: !this.isReadonly ? 'IMPIANTI.FORMDATISITO.IDSEDIME.PLACEHOLDER' : ' ',
          options: this.service.getComboSedimi() as any,
          size,
          clearable: true,
          required:true,
          value: currentValue.indirizzo.sedime.idSedime.toString(),
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            }),
          ],
          readonly:this.isReadonly
        }),
        indirizzo: new TextInput({
          label: 'IMPIANTI.FORMDATISITO.INDIRIZZO.LABEL',
          placeholder: !this.isReadonly ? 'IMPIANTI.FORMDATISITO.INDIRIZZO.PLACEHOLDER' : ' ',
          type: 'text',
          size,
          clearable: true,
          required:true,
          value: currentValue.indirizzo.indirizzo,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            }),
          ],
          readonly:this.isReadonly
        }),
        idProvincia: new AutocompleteInput({
          label: 'IMPIANTI.FORMDATISITO.PROVINCIA.LABEL',
          placeholder: !this.isReadonly ? 'IMPIANTI.FORMDATISITO.PROVINCIA.PLACEHOLDER' : ' ',
          options: this.service.getComboProvince() as any,
          value: currentValue.indirizzo?.comune?.provincia?.idProvincia,
          compareWith: (item, selected) => +item.id === selected,
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
          ],
          readonly:this.isReadonly
        }),
        idComune: new AutocompleteInput({
          label: 'IMPIANTI.FORMDATISITO.COMUNE.LABEL',
          placeholder: !this.isReadonly ? 'IMPIANTI.FORMDATISITO.COMUNE.PLACEHOLDER' : ' ',
          options:  (this.service.getComboComuni(+currentValue.indirizzo?.comune?.provincia?.idProvincia)) as any,
          value: currentValue.indirizzo?.comune?.idComune,
          compareWith: (item, selected) => +item.id === selected,
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
          ],
          readonly:this.isReadonly
        }),
        cap: new TextInput({
          label: 'IMPIANTI.FORMDATISITO.CAP.LABEL',
          placeholder: !this.isReadonly ? 'IMPIANTI.FORMDATISITO.CAP.PLACEHOLDER' : ' ',
          type: 'text',
          value: cap,
          size,
          clearable: true,
          readonly:this.isReadonly
        })
      }
    });

    this.formDatiImpianto.valueChanges
      .pipe(untilDestroyed(this))
      .subscribe(() => this.setIsEditing(true));
    this.formDatiSito.valueChanges
      .pipe(untilDestroyed(this))
      .subscribe(() => this.setIsEditing(true));

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
      row.idFormUpdate = (row.idSottoLinea)?'s_'+row.idSottoLinea:'l_'+row.idLinea;
      let f=new Form({
        header: { show: false },
        filter: true,
        validatorOrOpts: [
          this.service.dateValidator(),
        ],
        controls: {
          dataInizioValidita: new DateInput({
            placeholder: !this.isReadonly ? 'IMPIANTI.FORMLINEA.FIELDS.DATAINIZIOVALIDITA.PLACEHOLDER' : ' ',
            size: size,
            value: row.dataInizioValidita?formatDate(row.dataInizioValidita, 'yyyy-MM-dd', 'en'):null,
            clearable: true,
            readonly:this.isReadonly
          }),
          dataFineValidita: new DateInput({
            placeholder: !this.isReadonly ? 'IMPIANTI.FORMLINEA.FIELDS.DATAFINEVALIDITA.PLACEHOLDER' : ' ',
            size: size,
            value: row.dataFineValidita?formatDate(row.dataFineValidita, 'yyyy-MM-dd', 'en'):null,
            clearable: true,
            readonly:this.isReadonly,
            validationStatus: [
              ValidationStatus.ERROR.CUSTOM(
                (control) => control.touched && control.parent?.hasError('dataFineValidita'),
                {
                  text: this.messageIdDataTesto
                }
              )
            ],
          }),
        }});
      this.updateForms.push({
              'id':row.idFormUpdate,
              'form' : f,
              'changed': false
      });

      this.getUpdateForm(row).valueChanges.pipe(untilDestroyed(this)).subscribe(x => {
        this.setUpdateFormChanged(row);
      });
    });
  }
  getUpdateFormControl(linea:LineaExtended,name:string):FormControl{
    return this.updateForms.find(i=>i.id==linea.idFormUpdate).form.get(name);
  }
  getUpdateForm(linea:LineaExtended):Form{
    return this.updateForms.find(i=>i.id==linea.idFormUpdate).form;
  }
  setUpdateFormChanged(linea:LineaExtended):void{
    this.updateForms.find(i=>i.id==linea.idFormUpdate).changed=true;
  }
  hasUnsavedChangeRow(linea: LineaExtended):boolean{
    return this.updateForms.find(i=>i.id==linea.idFormUpdate).changed;
  }
  updateDatasource() {
    if (!this.dataSource) {
      this.dataSource = new LocalPagedDataSource<LineaExtended>({
        observable: this.service.getImpiantoLinee.bind(this.service,this.currentImpianto.idImpianto.toString()),
        tablePage: new TablePage()
      });
      this.dataSource.rows$.subscribe(
        (items:LineaExtended[])=>{
          this.rows = items;
          this.datasourceElementsChangedLinee(items);
        }
      );
      return;
    }else{
      this.dataSource.rows$.subscribe(
        (items:LineaExtended[])=>{
          this.rows = items;
          this.datasourceElementsChangedLinee(items);
        }
      );
    }

    this.dataSource.setObservable(
      (this.service.getImpiantoLinee.bind(this.service,this.currentImpianto.idImpianto.toString())),
    );
    this.dataSource.refresh();
  }
  checkIdLineaExtend(idLineaExtend){
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
          placeholder: !this.isReadonly ? 'IMPIANTI.FORMLINEA.FIELDS.LINEA.PLACEHOLDER' : ' ',
          options: of(this.comboLinee),
          valueChange: (idLineaExtend: string) => {
            let descrizione='';
            if(idLineaExtend){
              ((this.comboLinee as any).content).forEach(
                item=>{
                  if(item && item.id===idLineaExtend){
                    descrizione = item.value;
                  this.checkIdLineaExtend(idLineaExtend)
                  }
                }
              );
            }
            this.toCreate.descrizione = descrizione;
          },
          size,
          clearable: true,
          required: true,
          readonly:this.isReadonly
        }),
        dataInizioValidita: new DateInput({
          placeholder: !this.isReadonly ? 'IMPIANTI.FORMLINEA.FIELDS.DATAINIZIOVALIDITA.PLACEHOLDER' : ' ',
          size: size,
          clearable: true,
          readonly:this.isReadonly
        }),
        dataFineValidita: new DateInput({
          placeholder: !this.isReadonly ? 'PROFILI.FORM.FIELDS.DATAFINEVALIDITA.PLACEHOLDER' : ' ',
          size: size,
          clearable: true,
          readonly:this.isReadonly,
          validationStatus: [
            ValidationStatus.ERROR.CUSTOM(
              (control) => control.touched && control.parent?.hasError('dataFineValidita'),
              {
                text: this.messageIdDataTesto
              }
            )
          ],
        }),
      }
    });
    this.createForm.valueChanges
    .pipe(untilDestroyed(this))
    .subscribe(() => this.setIsEditing(true));
  }
  private _cleanForm() {
    this.createForm.reset();
    // non reinizializza gli errori, forzatura
    this._initCreateForm();
    this.toCreate = {};
    this.setIsEditing(false);
  }
  private _callbackLinea(response:any=null){
    if(response && response.message){
      this.notification.info({
        title: response.message.titoloMsg,
        text: response.message.testoMsg
      });
    }
    this.dataSource.refresh();
    this._cleanForm();
    (
      this.createForm.get('idLinea') as AutocompleteInput
    ).setOptions(this.service.getComboLineeImpianto(this.currentImpianto.idImpianto) as any);
  }
  createLinea(): void{
    if (this.createForm.valid) {
      if(this.toCreate.isSottolinea){
        this.service.createSottoLineaImpianto({...this.toCreate,...this.createForm.value} as LineaExtended,this.currentImpianto.idImpianto).
        pipe(csiCatchError(), untilDestroyed(this))
        .subscribe((response) => {
          this._callbackLinea(response);
        });
      }else{
        this.service.createLineaImpianto({...this.toCreate,...this.createForm.value} as LineaExtended,this.currentImpianto.idImpianto).
        pipe(csiCatchError(), untilDestroyed(this))
        .subscribe((response) => {
          this._callbackLinea(response);
        });
      }
    }
  }
  deleteLinea(linea:LineaExtended): void{
    const dialog = this.modalService.openDialog(ConfirmDeleteLineaComponent, {
      sizeModal: 'xs',
      header: this.deleteLineaMessage.titoloMsg,
      showCloseButton: true,
      context: { linea:linea, idImpianto: this.currentImpianto.idImpianto, messageConfirm: this.deleteLineaMessage }
    });

    dialog.closed.pipe(untilDestroyed(this)).subscribe(() => {
      this._callbackLinea();
    });
  }
  editLinea(linea:LineaExtended): void{
    const form = this.getUpdateForm(linea);
    if(linea.idLinea){
      const lineaUpdate:LineaExtended={
        idLinea: linea.idLinea,
        ...form.value
      };
      this.service.editImpiantoLinea(this.currentImpianto.idImpianto, lineaUpdate).
      pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response) => {
        this._callbackLinea(response);
      });
    }else if(linea.idSottoLinea){
      const lineaUpdate:LineaExtended={
        idSottoLinea: linea.idSottoLinea,
        ...form.value
      }
      this.service.editImpiantoSottolinea(this.currentImpianto.idImpianto, lineaUpdate).
      pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response) => {
        this._callbackLinea(response);
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
  datasourceElementsChangedAtti(atti:Atto[]){
    const size = '12|6|6|6|6';
    this.updateFormsAtti = [];
    this.rowsAtti = atti;
    atti.forEach(atto=>{
      let formAtto = new Form({
        header: { show: false },
        validatorOrOpts: [
          this.service.dateValidator('dataInizioAutorizzazione','dataFineAutorizzazione'),
        ],
        filter: true,
        controls: {
          numProvvedimento:new TextInput({
            label: 'IMPIANTI.FORMATTI.FIELDS.NUMERO.LABEL',
            placeholder: !this.isReadonly ? 'IMPIANTI.FORMATTI.FIELDS.NUMERO.PLACEHOLDER' : ' ',
            value: atto.numProvvedimento,
            type: 'text',
            size,
            clearable: true,
            required: true,
            readonly:this.isReadonly
          }),
          dataProvvedimento: new DateInput({
            placeholder: !this.isReadonly ? 'IMPIANTI.FORMATTI.FIELDS.DATAINIZIOVALIDITA.PLACEHOLDER' : ' ',
            value: atto.dataProvvedimento?formatDate(atto.dataProvvedimento, 'yyyy-MM-dd', 'en'):null,
            size: size,
            clearable: true,
            required: true,
            readonly:this.isReadonly
          }),
          tipoProvvedimento: new AutocompleteInput({
            placeholder: !this.isReadonly ? 'IMPIANTI.FORMATTI.FIELDS.TIPOPROVVEDIMENTO.PLACEHOLDER' : ' ',
            options: of(this.comboAtti),
            value: atto.tipoProvvedimento.idTipoProvvedimento.toString(),
            size,
            clearable: true,
            required: true,
            readonly:this.isReadonly
          }),
          dataInizioAutorizzazione: new DateInput({
            placeholder: !this.isReadonly ? 'IMPIANTI.FORMATTI.FIELDS.DATAINIZIOVALIDITA.PLACEHOLDER' : ' ',
            value: atto.dataInizioAutorizzazione?formatDate(atto.dataInizioAutorizzazione, 'yyyy-MM-dd', 'en'):null,
            size: size,
            clearable: true,
            required: true,
            readonly:this.isReadonly
          }),
          dataFineAutorizzazione: new DateInput({
            placeholder: !this.isReadonly ? 'IMPIANTI.FORMATTI.FIELDS.DATAFINEVALIDITA.PLACEHOLDER' : ' ',
            value: atto.dataFineAutorizzazione?formatDate(atto.dataFineAutorizzazione, 'yyyy-MM-dd', 'en'):null,
            size: size,
            clearable: true,
            readonly:this.isReadonly,
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

    //

      this.updateFormsAtti.push({
              'id':atto.idAtto.toString(),
              'form' : formAtto,
              'changed': false
      });

      this.getUpdateFormAtto(atto).valueChanges.pipe(untilDestroyed(this)).subscribe(x => {
        this.setUpdateFormChangedAtto(atto);
      });
    });
  }

  getUpdateFormControlAtto(atto:Atto,name:string):FormControl{
    return this.updateFormsAtti.find(i=>i.id==atto.idAtto.toString()).form.get(name);
  }
  getUpdateFormAtto(atto:Atto):Form{
    return this.updateFormsAtti.find(i=>i.id==atto.idAtto.toString()).form;
  }
  setUpdateFormChangedAtto(atto:Atto):void{
    this.updateFormsAtti.find(i=>i.id==atto.idAtto.toString()).changed=true;
  }
  hasUnsavedChangeRowAtto(atto: Atto):boolean{
    return this.updateFormsAtti.find(i=>i.id==atto.idAtto.toString()).changed;
  }

  updateDatasourceAtti() {
    if (!this.dataSourceAtti) {
      this.dataSourceAtti = new LocalPagedDataSource<Atto>({
        observable: this.service.getImpiantoAtti.bind(this.service,this.currentImpianto.idImpianto.toString()),
        tablePage: new TablePage()
      });
      this.dataSourceAtti.rows$.subscribe(
        (items:Atto[])=>{
          this.datasourceElementsChangedAtti(items);
        }
      );
      return;
    }else{
      this.dataSourceAtti.rows$.subscribe(
        (items:Atto[])=>{
          this.datasourceElementsChangedAtti(items);
        }
      );
    }

    this.dataSourceAtti.setObservable(
      this.service.getImpiantoAtti.bind(this.service,this.currentImpianto.idImpianto.toString())
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
          placeholder: !this.isReadonly ? 'IMPIANTI.FORMATTI.FIELDS.NUMERO.PLACEHOLDER' : ' ',
          type: 'text',
          size,
          clearable: true,
          required: true,
          readonly:this.isReadonly
        }),
        dataProvvedimento: new DateInput({
          placeholder: !this.isReadonly ? 'IMPIANTI.FORMATTI.FIELDS.DATAINIZIOVALIDITA.PLACEHOLDER' : ' ',
          size: size,
          clearable: true,
          required: true,
          readonly:this.isReadonly
        }),
        tipoProvvedimento: new AutocompleteInput({
          placeholder: !this.isReadonly ? 'IMPIANTI.FORMATTI.FIELDS.TIPOPROVVEDIMENTO.PLACEHOLDER' : ' ',
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
          readonly:this.isReadonly
        }),
        dataInizioAutorizzazione: new DateInput({
          placeholder: !this.isReadonly ? 'IMPIANTI.FORMATTI.FIELDS.DATAINIZIOVALIDITA.PLACEHOLDER' : ' ',
          size: size,
          clearable: true,
          required: true,
          readonly:this.isReadonly
        }),
        dataFineAutorizzazione: new DateInput({
          placeholder: !this.isReadonly ? 'IMPIANTI.FORMATTI.FIELDS.DATAFINEVALIDITA.PLACEHOLDER' : ' ',
          size: size,
          clearable: true,
          readonly:this.isReadonly,
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
    this.createFormAtto.valueChanges
    .pipe(untilDestroyed(this))
    .subscribe(() => this.setIsEditing(true));
  }

  private _cleanFormAtti() {
    this.createFormAtto.reset();
    // non reinizializza gli errori, forzatura
    this._initCreateFormAtti();
    this.setIsEditing(false);
  }

  private _callbackAtto(response:any=null){
    if(response && response.message){
      this.notification.info({
        title: response.message.titoloMsg,
        text: response.message.testoMsg
      });
    }
    this.dataSourceAtti.refresh();
    this._cleanFormAtti();

  }

  createAtto(): void{
    if (this.createFormAtto.valid) {
      this.service.createImpiantoAtto({...this.createFormAtto.value,...this.toCreateAtto},this.currentImpianto.idImpianto).
      pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response) => {
        this._callbackAtto(response);
      });
    }
  }

   deleteAtto(atto:Atto): void{
    const dialog = this.modalService.openDialog(ConfirmDeleteAttoComponent, {
      sizeModal: 'xs',
      header: this.deleteAttoMessage.titoloMsg,
      showCloseButton: true,
      context: { atto:atto, idImpianto: this.currentImpianto.idImpianto, messageConfirm: this.deleteAttoMessage }
    });

    dialog.closed.pipe(untilDestroyed(this)).subscribe(() => {
      this._callbackAtto();
    });
  }

  editAtto(atto:Atto): void{
    const form = this.getUpdateFormAtto(atto);
    this.service.editImpiantoAtto({...form.value, idAtto:atto.idAtto, tipoProvvedimento:{idTipoProvvedimento:form.value.tipoProvvedimento}},this.currentImpianto.idImpianto).
    pipe(csiCatchError(), untilDestroyed(this))
    .subscribe((response) => {
      this._callbackAtto(response);
    });
  }
}
