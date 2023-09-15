import {
  AfterViewInit,
  Component,
  OnInit,
  TemplateRef,
  ViewChild
} from '@angular/core';
import { LoadingService } from '@theme/layouts/loading.services';
import { AutocompleteInput, DateInput, Form, SelectOption, TextInput } from '@shared/form';
import { ProfiliService } from '@pages/profili/services/profili.service';
import { LocalPagedDataSource } from '@shared/table/models/local-paged-data-source';
import { TablePage } from '@shared/table/models/table-page';
import {
  Profilo,
  ProfiloUpdateResponse,
  VerificaProfiloResponse
} from '@pages/profili/models/profili.model';
import { TableColumn } from '@shared/table/models/column.model';
import { I18nService } from '@eng-ds/translate';
import { ProfiliACL } from '@pages/profili/models/acl.model';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { NotificationService } from '@shared/notification/notification.service';
import { of } from 'rxjs';
import { ModalService } from '@shared/modal/modal.service';
import { ConfirmDeleteComponent } from '@pages/profili/components/confirm-delete/confirm-delete.component';
import { csiCatchError } from '@core/operators/catch-error.operator';
import { Router } from '@angular/router';
import { ConfirmExitComponent } from '@pages/profili/components/confirm-exit/confirm-exit.component';
import { UtilityService } from '@core/services';
import { formatDate } from '@angular/common';

@UntilDestroy()
@Component({
  selector: 'app-main',
  templateUrl: './main.component.html'
})
export class MainComponent implements OnInit, AfterViewInit {
  @ViewChild('switchReadTemplate') switchReadTemplate: TemplateRef<any>;
  @ViewChild('switchUpdateTemplate') switchUpdateTemplate: TemplateRef<any>;
  @ViewChild('switchInsertTemplate') switchInsertTemplate: TemplateRef<any>;
  @ViewChild('switchDeleteTemplate') switchDeleteTemplate: TemplateRef<any>;
  @ViewChild('actionsTemplate') actionsTemplate: TemplateRef<any>;
  @ViewChild('descriptionTemplate') descriptionTemplate: TemplateRef<any>;

  
  @ViewChild('tipologiaProfiloTemplate') tipologiaProfiloTemplate: TemplateRef<any>;
  @ViewChild('dataInizioValiditaTemplate') dataInizioValiditaTemplate: TemplateRef<any>;
  @ViewChild('dataFineValiditaTemplate') dataFineValiditaTemplate: TemplateRef<any>;


  

  acl: ProfiliACL;
  helperTitle: string = '';

  // Unsaved change
  hasUnsavedChange = false;
  hasUnsavedRow:number[]=[];
  hasErroRows:number[]=[];
  rows:Profilo[];

  hasUnsavedCreate = false;

  // Form declaration
  createForm: Form;
  profilo: number;
  funzionalita: number;
  createEnabled: boolean = false;

  updateForms:Form[];

  // Create section
  toCreate: Profilo = {
    descProfilo: '',
    dataInizioValidita: '',
    dataFineValidita: '',
    tipologiaProfilo: null
  };

  // Table declaration
  dataSource: LocalPagedDataSource<Profilo>;
  columns: TableColumn[] = [];
  comboTipiProfilo:SelectOption<number, string>[];
  constructor(
    private service: ProfiliService,
    private router: Router,
    private notification: NotificationService,
    private modalService: ModalService,
    private i18n: I18nService,
    private loadingService: LoadingService,
    private utilityService: UtilityService
  ) {}

  ngOnInit(): void {
    this._initACL();
   
    this.loadingService.hide();
    this.updateDatasource();
    this.service.getComboTipiProfilo().pipe(
      untilDestroyed(this)
    ).subscribe(
      response=>{
        this.comboTipiProfilo= response;
        this._initCreateForm();
      }
    )
  }

  ngAfterViewInit() {
    this._initTable();
    
  }

  create() {
    if (this.createForm.valid) {
      this.loadingService.show();
      const toSave = { ...this.toCreate };
      toSave.descProfilo = this.createForm.get('descProfilo').value;
      toSave.dataInizioValidita=this.createForm.get('dataInizioValidita').value?this.createForm.get('dataInizioValidita').value+'T06:00:00.000+00:00':null,
      toSave.dataFineValidita=this.createForm.get('dataFineValidita').value?this.createForm.get('dataFineValidita').value+'T06:00:00.000+00:00':null,
      toSave.tipologiaProfilo={idTipoProfilo:+this.createForm.get('tipologiaProfilo').value}
      this.service
        .createProfilo(toSave)
        .pipe(csiCatchError(), untilDestroyed(this))
        .subscribe((response) => {
          this.notification.info({
            title: response.message.titoloMsg,
            text: response.message.testoMsg
          });
          this.loadingService.hide();
          this.dataSource.refresh();
          this._cleanForm();
        });
      return;
    }
  }

  _getUpdatesRow(row: Profilo):Profilo {
    const rowForm: Profilo={
      ...row , 
      descProfilo:this.updateForms[row.idProfilo].get('descProfilo').value,
      dataInizioValidita:this.updateForms[row.idProfilo].get('dataInizioValidita').value?this.updateForms[row.idProfilo].get('dataInizioValidita').value+'T06:00:00.000+00:00':null,
      dataFineValidita:this.updateForms[row.idProfilo].get('dataFineValidita').value?this.updateForms[row.idProfilo].get('dataFineValidita').value+'T06:00:00.000+00:00':null,
      tipologiaProfilo:{idTipoProfilo:+this.updateForms[row.idProfilo].get('tipologiaProfilo').value}
    };
    if(rowForm.descProfilo==''){
      delete rowForm.descProfilo;
    }
    return rowForm;
  }

  _verifyProfilo(change:Profilo){
    const row = this.rows.find(r=>r.idProfilo==change.idProfilo);
    const index = this.hasErroRows.indexOf(row.idProfilo, 0);
    if (index > -1) {
      this.hasErroRows.splice(index, 1);
    }

    this.service
      .verifyProfilo(this._getUpdatesRow(row))
      .pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response: VerificaProfiloResponse) => {
        if(response.errors){
          this.notification.error({
            title: response.errors[0].titoloMsg,
            text: response.errors[0].testoMsg
          });
          this.updateForms[row.idProfilo].get(response.errors[0].campo).setErrors({'incorrect': true});
          this.hasErroRows.push(row.idProfilo);
        }
      });
  }
  _verifyProfiloCreate(change:Profilo){
    //this.hasUnsavedCreate = false;   
    if(change.descProfilo=='' && change.tipologiaProfilo==null && change.dataFineValidita==null && change.dataInizioValidita==null){
      this.hasUnsavedCreate = false; 
    }else{
      this.hasUnsavedCreate = true; 
    }
  }

  _setHasUnsavedChange(change:Profilo){
    const row = this.rows.find(r=>r.idProfilo==change.idProfilo);
    const formDataIV=formatDate(change.dataInizioValidita, 'yyyy-MM-dd', 'en');
    const rowDataIV=formatDate(row.dataInizioValidita, 'yyyy-MM-dd', 'en'); 
    const formDataFV=(change.dataFineValidita)?formatDate(change.dataFineValidita, 'yyyy-MM-dd', 'en'):null;
    const rowDataFV=(row.dataFineValidita)?formatDate(row.dataFineValidita, 'yyyy-MM-dd', 'en'):null; 
    if(change.descProfilo!=row.descProfilo
      || change.tipologiaProfilo!=row.tipologiaProfilo.idTipoProfilo
      || formDataIV!=rowDataIV
      || formDataFV!=rowDataFV
      ){
        if(!this.hasUnsavedRow.includes(row.idProfilo)){
          this.hasUnsavedRow.push(row.idProfilo);
        }
    }else{
      const index = this.hasUnsavedRow.indexOf(row.idProfilo, 0);
      if (index > -1) {
        this.hasUnsavedRow.splice(index, 1);
      }
    }
    this.hasUnsavedChange=(this.hasUnsavedRow.length>0)?true:false;
  }
  
  hasUnsavedChangeRow(row: Profilo):boolean{
    const index = this.hasUnsavedRow.indexOf(row.idProfilo, 0);
    if (index > -1) {
      return true; 
    }else{
      return false;
    }
  }
  hasErroRow(row: Profilo):boolean{
    const index = this.hasErroRows.indexOf(row.idProfilo, 0);
    if (index > -1) {
      return true; 
    }else{
      return false;
    }
  }

  update(row: Profilo) {
    this.loadingService.show();
    this.service
      .updateProfilo(this._getUpdatesRow(row))
      .pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response: ProfiloUpdateResponse) => {
        this.notification.info({
          title: response.message.titoloMsg,
          text: response.message.testoMsg
        });
        this.hasUnsavedChange = false;
        this.hasUnsavedRow = [];
        this.hasErroRows = [];
        this.loadingService.hide();
        this.dataSource.refresh();
      });
  }

  delete(row: Profilo) {
    this.utilityService.getMessage('A005').subscribe((result: any) => {
      const { content } = result;
      if (content) {
        const dialog = this.modalService.openDialog(ConfirmDeleteComponent, {
          sizeModal: 'xs',
          header: content.titoloMsg,
          showCloseButton: true,
          context: { profilo: row, messageConfirm: content }
        });

        dialog.closed
          .pipe(untilDestroyed(this))
          .subscribe(() => { this.hasUnsavedChange = false; this.hasUnsavedCreate = false; this.hasUnsavedRow = []; this.hasErroRows = []; this.dataSource.refresh();});
      }
    });
  }

  
  datasourceElementsChanged(rows:Profilo[]){
    const size = '12|6|6|6|6';
    this.updateForms = [];
    this.rows = rows;
    rows.forEach(row=>{
      this.updateForms[row.idProfilo] = new Form({
        header: { show: false },
        filter: true,
        controls: {
          idProfilo:new TextInput({
            type: 'number',
            placeholder: 'PROFILI.FORM.FIELDS.IDPROFILO.PLACEHOLDER',
            size: size,
            clearable: false,
            value: row.idProfilo.toString(),
            required: true,
          }),
          descProfilo:new TextInput({
            type: 'text',
            placeholder: 'PROFILI.FORM.FIELDS.DESCRIZIONE.PLACEHOLDER',
            size: size,
            clearable: false,
            value: row.descProfilo,
            required: true,
          }),
          tipologiaProfilo: new AutocompleteInput({
            absolute: true,
            placeholder: 'PROFILI.FORM.FIELDS.TIPOLOGIAPROFILO.PLACEHOLDER',
            options: of(this.comboTipiProfilo),
            value: row.tipologiaProfilo.idTipoProfilo,
            size: size,
            clearable: true,
            required: true,
          }),
          dataInizioValidita: new DateInput({
            placeholder: 'PROFILI.FORM.FIELDS.DATAINIZIOVALIDITA.PLACEHOLDER',
            size: size,
            value: row.dataInizioValidita?formatDate(row.dataInizioValidita, 'yyyy-MM-dd', 'en'):null,
            clearable: true
          }),
          dataFineValidita: new DateInput({
            placeholder: 'PROFILI.FORM.FIELDS.DATAFINEVALIDITA.PLACEHOLDER',
            size: size,
            value: row.dataFineValidita?formatDate(row.dataFineValidita, 'yyyy-MM-dd', 'en'):null,
            clearable: true
          }), 
        }
      });
      this.updateForms[row.idProfilo].valueChanges.subscribe(x => {
        this._setHasUnsavedChange(x);
        // validazione lato require del form
        //this._verifyProfilo(x);
      });
    });
  }

  updateDatasource() {
    if (!this.dataSource) {
      this.dataSource = new LocalPagedDataSource<Profilo>({
        observable: this.service.getProfili.bind(
          this.service
        ), 
        tablePage: new TablePage()
      });
      this.dataSource.rows$.subscribe(
        (items:Profilo[])=>{
          this.datasourceElementsChanged(items);
        }
      );
      return;
    }else{
      this.dataSource.rows$.subscribe(
        (items:Profilo[])=>{
          this.datasourceElementsChanged(items);
        }
      );
    }

    this.dataSource.setObservable(
      this.service.getProfili.bind(
        this.service
      )
    );
    this.hasUnsavedChange = false; 
    this.hasUnsavedCreate = false; 
    this.hasUnsavedRow = [];
    this.hasErroRows = [];
    this.dataSource.refresh();
  }

  onBack() {
    if (this.hasUnsavedChange || this.hasUnsavedCreate) {
      this.utilityService.getMessage('A001').subscribe((result: any) => {
        const { content } = result;
        if (content) {
          const dialog = this._preventDataLose(content);

          dialog.closed
            .pipe(untilDestroyed(this))
            .subscribe(() => this.router.navigate(['']));
        }
      });
      return;
    }
    this.router.navigate(['home']);
  }

  private _initACL(): void {
    this.service
      .getStoredAcl()
      .pipe(untilDestroyed(this))
      .subscribe((acl) => (this.acl = acl));
  }

  private _initTable(): void {
    this.columns = [
      {
        prop: 'idProfilo',
        name: this.i18n.translate('PROFILI.TABLE.COLUMNS.ID'),
        cellClass: 'align-middle',
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.idProfilo).localeCompare(b.idProfilo, undefined, { numeric: true });
          }
          return ('' + b.idProfilo).localeCompare(a.idProfilo, undefined, { numeric: true });
        }
      },
      {
        prop: 'descProfilo',
        name: this.i18n.translate(
          'PROFILI.TABLE.COLUMNS.DESC'
        ),
        cellClass: 'align-middle min-w-140',
        cellTemplate: this.descriptionTemplate,
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.descProfilo).localeCompare(b.descProfilo);
          }
          return ('' + b.descProfilo).localeCompare(a.descProfilo);
        }
      },
      {
        prop: 'tipologiaProfilo',
        name: this.i18n.translate(
          'PROFILI.TABLE.COLUMNS.TIPOLOGIAPROFILO'
        ),
        cellClass: 'align-middle',
        cellTemplate: this.tipologiaProfiloTemplate,
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.tipologiaProfilo.descTipoProfilo).localeCompare(
              b.tipologiaProfilo.descTipoProfilo
            );
          }
          return ('' + b.tipologiaProfilo.descTipoProfilo).localeCompare(
            a.tipologiaProfilo.descTipoProfilo
          );
        }
      },
      {
        prop: 'dataInizioValidita',
        name: this.i18n.translate(
          'PROFILI.TABLE.COLUMNS.DATAINIZIOVALIDITA'
        ),
        cellClass: 'align-middle',
        cellTemplate: this.dataInizioValiditaTemplate,
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
          'PROFILI.TABLE.COLUMNS.DATAFINEVALIDITA'
        ),
        cellClass: 'align-middle',
        cellTemplate: this.dataFineValiditaTemplate,
        sortable: true,
        sortFn: (a, b, dir) => {
          if (dir === 'asc') {
            return ('' + a.dataFineValidita).localeCompare(b.dataFineValidita);
          }
          return ('' + b.dataFineValidita).localeCompare(a.dataFineValidita);
        }
      },
      {
        name: this.i18n.translate(
          'PROFILI.TABLE.COLUMNS.AZIONI'
        ),
        cellClass: 'align-middle',
        cellTemplate: this.actionsTemplate,
        sortable: false
      },
    ];
  }

  private _initCreateForm() {
    const size = '12|6|6|6|6';
    this.createForm = new Form({
      header: { show: false },
      filter: true,
      controls: {
        descProfilo:new TextInput({
          type: 'text',
          placeholder: 'PROFILI.FORM.FIELDS.DESCRIZIONE.PLACEHOLDER',
          size: size,
          clearable: false,
          required: true,
        }), 
        tipologiaProfilo: new AutocompleteInput({
          absolute: true,
          placeholder: 'PROFILI.FORM.FIELDS.TIPOLOGIAPROFILO.PLACEHOLDER',
          options: of(this.comboTipiProfilo),
          size,
          clearable: true,
          required: true,
        }),
        dataInizioValidita: new DateInput({
          placeholder: 'PROFILI.FORM.FIELDS.DATAINIZIOVALIDITA.PLACEHOLDER',
          size: size,
          clearable: true
        }),
        dataFineValidita: new DateInput({
          placeholder: 'PROFILI.FORM.FIELDS.DATAFINEVALIDITA.PLACEHOLDER',
          size: size,
          clearable: true
        }),
      }
    });
    this.createForm.valueChanges.subscribe(x => {
      this._verifyProfiloCreate(x);
    });
  }

  private _cleanForm() {
    this.toCreate = {
      descProfilo: '',
      dataInizioValidita: '',
      dataFineValidita: '',
      tipologiaProfilo: null
    };
    this.createForm.reset();
    // non reinizializza gli errori, forzatura
    this._initCreateForm();
    this.createEnabled = false;
    this.hasUnsavedCreate = false;
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
}
