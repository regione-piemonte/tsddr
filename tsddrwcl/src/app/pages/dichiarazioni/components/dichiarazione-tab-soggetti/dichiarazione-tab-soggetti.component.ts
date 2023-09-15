import { Component, Input, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { UtilityService } from '@app/core';
import { I18nService } from '@app/core/translate/public-api';
import { Form, TextInput, ValidationStatus } from '@app/shared/form';
import { ModalService } from '@app/shared/modal/modal.service';
import { LocalPagedDataSource } from '@app/shared/table/models/local-paged-data-source';
import { TablePage } from '@app/shared/table/models/table-page';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { TableColumn } from '@swimlane/ngx-datatable';
import { DichiarazioniACL } from '../../models/acl.model';
import { DichiarazioneEditingStore, SoggettoMr, SoggettoMrExtended } from '../../models/dichiarazione.model';
import { DichiarazioneEditingStoreService } from '../../services/dichiarazione-editing-store.service';
import { DichiarazioneService } from '../../services/dichiarazioni.service';
import { ConfirmDeleteSoggettoComponent } from '../confirm-delete-soggetto/confirm-delete-soggetto.component';

@UntilDestroy()
@Component({
  selector: 'app-dichiarazione-tab-soggetti',
  templateUrl: './dichiarazione-tab-soggetti.component.html',
  styleUrls: ['./dichiarazione-tab-soggetti.component.scss']
})
export class DichiarazioneTabSoggettiComponent implements OnInit{
  @Input() soggetti: SoggettoMr[];
  @Input() isEditMode: boolean = false;
  @Input() updateRow: boolean = false;
  @Input() keyDichiarazione: string = 'new';
  
  @ViewChild('actionsSoggettiTemplate') actionsSoggettiTemplate: TemplateRef<any>;
  @ViewChild('codFiscPartivaTemplate') codFiscPartivaTemplate: TemplateRef<any>;
  @ViewChild('ragSocialeTemplate') ragSocialeTemplate: TemplateRef<any>;
  @ViewChild('obiettiviTemplate') obiettiviTemplate: TemplateRef<any>;

  acl: DichiarazioniACL;
  
  helperTitle:string;
  dataSource: LocalPagedDataSource<SoggettoMr>;
  columns: TableColumn[] = [];
  createForm: Form;
  updateForms: {id:number, form:Form, changed:boolean}[]; 
  rows:SoggettoMr[]; 
  idSoggettiMrCount = 0;
  // Create section
  toCreate: SoggettoMr = {
    obbRagg : false
  };
  deleteMessage:any;
  isReadonly:boolean = false;

  idDichAnnuale:number;
  
  constructor( private utility: UtilityService,
    private i18n: I18nService,
    private modalService: ModalService,
    private utilityService: UtilityService,
    private service: DichiarazioneService,
    private editingStoreService: DichiarazioneEditingStoreService
  ) {}

  ngOnInit(): void {
    this._initACL();
    this._initHelper();
    this.utilityService.getMessage('A005').pipe(untilDestroyed(this)).subscribe(
      r=>{
        this.deleteMessage = (r as any).content;
        this._initTable();
        this.updateDatasource();
        this.editingStoreService.getStoredDichiarazione(this.keyDichiarazione).subscribe(
          dichiarazione=>{if(dichiarazione.key===this.keyDichiarazione){this._changeDichiarazione(dichiarazione);}}
        );
        this._initCreateForm();
      }
    );
  }
  
  _changeDichiarazione(dichiarazione: DichiarazioneEditingStore){
    if(dichiarazione && dichiarazione.viewMode){
      this.isReadonly = true;
    }
    this.idDichAnnuale = dichiarazione.dichiarazione?.idDichAnnuale;
  }

  private _initHelper(): void {
    this.utility.getNotaInfo('SOGGETTI').subscribe((result: any) => {
      if (result.content) {
        this.helperTitle = result.content.testoInfo;
      }
    });
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
        prop: 'idSoggettiMr',
        name: this.i18n.translate('DICHIARAZIONI.TABLESOGGETTI.COLUMNS.NUMERO'),
        cellClass: 'align-middle',
        sortable: false
      },
      {
        prop: 'codFiscPartiva',
        name: this.i18n.translate('DICHIARAZIONI.TABLESOGGETTI.COLUMNS.CODFISCPARTIVA'),
        cellClass: 'align-middle',
        cellTemplate: this.codFiscPartivaTemplate,
        sortable: false
      },
      {
        prop: 'ragSociale',
        name: this.i18n.translate('DICHIARAZIONI.TABLESOGGETTI.COLUMNS.RAGSOCIALE'),
        cellTemplate: this.ragSocialeTemplate,
        cellClass: 'align-middle min-w-140',
        sortable: false
      },
      {
        prop: 'obbRagg',
        name: this.i18n.translate('DICHIARAZIONI.TABLESOGGETTI.COLUMNS.OBIETTIVI'),
        cellClass: 'align-middle',
        cellTemplate: this.obiettiviTemplate,
        sortable: false
      },
      {
        name: this.i18n.translate('DICHIARAZIONI.TABLESOGGETTI.COLUMNS.AZIONI'),
        cellTemplate: this.actionsSoggettiTemplate,
        cellClass: 'align-middle',
        sortable: false
      }
    ];
  }
  
  getUpdateFormControl(soggetto:SoggettoMr,name:string):FormControl{
    return this.updateForms.find(i=>i.id==soggetto.idSoggettiMr).form.get(name);
  }

  datasourceElementsChangedSoggetti(soggetti:SoggettoMr[]){
    const size = '12|6|6|6|6';
    this.updateForms = [];
    this.rows = soggetti;
    soggetti.forEach(row=>{
      // row.idLinea+'-'+row.idSottoLinea
      const formSoggetto= new Form({
        header: { show: false },
        filter: true,
        asyncValidator: [this.service.cfValidator()],
        controls: {
          codFiscPartiva: new TextInput({
            label: 'DICHIARAZIONI.CREATESOGGETTI.FORM.CODFISCPARTIVA.LABEL',
            placeholder: 'DICHIARAZIONI.CREATESOGGETTI.FORM.CODFISCPARTIVA.PLACEHOLDER',
            type: 'text',
            value: row.codFiscPartiva,
            readonly:this.isReadonly,
            validationStatus: [
              ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
                text: ''
              }),
              ValidationStatus.ERROR.CUSTOM(
                (control) => control.touched && control.parent?.hasError('cf'),
                {
                  text: ''
                }
              )
            ],
            validatorOrOpts: { updateOn: 'blur' }
          }),
          ragSociale: new TextInput({
            label: 'DICHIARAZIONI.CREATESOGGETTI.FORM.RAGSOCIALE.LABEL',
            placeholder: 'DICHIARAZIONI.CREATESOGGETTI.FORM.RAGSOCIALE.PLACEHOLDER',
            type: 'text',
            value: row.ragSociale,
            readonly:this.isReadonly,
            validatorOrOpts: { updateOn: 'blur' }
          })
        }
      });

      this.updateForms.push({
        'id':row.idSoggettiMr,
        'form' : formSoggetto,
        'changed': false
      });
    });
  }
  updateDatasource() {
    if (!this.dataSource) {
      this.dataSource = new LocalPagedDataSource<SoggettoMr>({
        observable: this.editingStoreService.getStoredSoggetti.bind(this.editingStoreService,this.keyDichiarazione), 
        tablePage: new TablePage()
      });
      this.dataSource.rows$.subscribe(
        (items:SoggettoMr[])=>{
          this.datasourceElementsChangedSoggetti(items);
        }
      );
      return;
    }else{
      this.dataSource.rows$.subscribe(
        (items:SoggettoMr[])=>{
          this.datasourceElementsChangedSoggetti(items);
        }
      );
    }

    this.dataSource.setObservable(
      this.editingStoreService.getStoredSoggetti.bind(this.editingStoreService,this.keyDichiarazione),  
    );
    this.dataSource.refresh();
  }
  private _initCreateForm() {
    const size = '12|6|6|6|6';
    this.createForm = new Form({
      header: { show: false },
      asyncValidator: [this.service.cfValidator()],
      filter: true,
      controls: {
        codFiscPartiva: new TextInput({
          label: 'DICHIARAZIONI.CREATESOGGETTI.FORM.CODFISCPARTIVA.LABEL',
          placeholder: 'DICHIARAZIONI.CREATESOGGETTI.FORM.CODFISCPARTIVA.PLACEHOLDER',
          type: 'text',
          required: true,
          readonly:this.isReadonly,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: ''
            }),
            ValidationStatus.ERROR.CUSTOM(
              (control) => control.touched && control.parent?.hasError('cf'),
              {
                text: ''
              }
            )
          ],
          validatorOrOpts: { updateOn: 'blur' }
        }),
        ragSociale: new TextInput({
          label: 'DICHIARAZIONI.CREATESOGGETTI.FORM.RAGSOCIALE.LABEL',
          placeholder: 'DICHIARAZIONI.CREATESOGGETTI.FORM.RAGSOCIALE.PLACEHOLDER',
          readonly:this.isReadonly,
          type: 'text',
          required:true,
          validatorOrOpts: { updateOn: 'blur' }
        })
      }
    });
    
  }
  private _cleanForm() {
    this.createForm.reset();
    this.toCreate.obbRagg = false;
    // non reinizializza gli errori, forzatura
    this._initCreateForm();
    //this.createEnabled = false;
    //this.hasUnsavedCreate = false;
  }
  createSoggetto(): void{
    if (this.createForm.valid) {
      this.editingStoreService.addSoggetto(({...this.toCreate,...this.createForm.value,...{idSoggettiMr:++this.idSoggettiMrCount,isRam:true}} as SoggettoMrExtended),this.keyDichiarazione);
      this.dataSource.refresh();
      this._cleanForm();
      this.editingStoreService.setStatus('soggettiChanged',true);
    }
  }

  
  deleteSoggetto(soggetto:SoggettoMrExtended): void{
    const dialog = this.modalService.openDialog(ConfirmDeleteSoggettoComponent, {
      sizeModal: 'xs',
      header: this.deleteMessage.titoloMsg,
      showCloseButton: true,
      context: { soggetto:soggetto, idDichAnnuale:this.idDichAnnuale,messageConfirm: this.deleteMessage }
    });

    dialog.closed.pipe(untilDestroyed(this)).subscribe(() => {
      this.editingStoreService.removeSoggetto(soggetto,this.keyDichiarazione);
      this.editingStoreService.setStatus('soggettiChanged',true);
      this.dataSource.refresh();
    });
    
  }

  
}
