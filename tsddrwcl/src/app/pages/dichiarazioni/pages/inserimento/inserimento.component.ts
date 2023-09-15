import {
  Component,
  ComponentFactoryResolver,
  OnInit,
  TemplateRef,
  ViewChild
} from '@angular/core';
import { DichiarazioniACL } from '@pages/dichiarazioni/models/acl.model';
import {
  AutocompleteInput,
  DateInput,
  Form,
  SelectOption,
  TextInput,
  ValidationStatus
} from '@shared/form';
import { DichiarazioneService } from '@app/pages/dichiarazioni/services/dichiarazioni.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NotificationService } from '@shared/notification/notification.service';
import { ModalService } from '@shared/modal/modal.service';
import { LoadingService } from '@theme/layouts/loading.services';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { csiCatchError } from '@core/operators/catch-error.operator';
import { SecurityService, UtilityService } from '@core/services';
import { ConfirmExitComponent } from '@pages/dichiarazioni/components/confirm-exit/confirm-exit.component';
import { forkJoin, Observable, of } from 'rxjs';
import { TableColumn } from '@swimlane/ngx-datatable';
import { LocalPagedDataSource } from '@app/shared/table/models/local-paged-data-source';
import { LineaExtended } from '../../models/linea.model';
import { Atto } from '../../models/atto.model';
import {
  Dichiarazione,
  DichiarazioneEditingStore,
  statusFormEditingStore
} from '../../models/dichiarazione.model';
import { DatePipe } from '@angular/common';
import { Gestore } from '../../models/gestore.model';
import { Impianto } from '@app/pages/impianti/models/impianto.model';
import { DichiarazioneEditingStoreService } from '../../services/dichiarazione-editing-store.service';
import { PopupDichiarazioneExistComponent } from '../../components/popup-dichiarazione-exist/popup-dichiarazione-exist.component';
import { ConfirmSaveComponent } from '../../components';
import { Console } from 'console';

@UntilDestroy()
@Component({
  selector: 'app-inserimento',
  templateUrl: './inserimento.component.html',
  styleUrls: ['./inserimento.component.scss']
})
export class InserimentoComponent implements OnInit {
  acl: DichiarazioniACL;
  helperTitle: string = '';
  // Form declaration
  formDatiImpianto: Form;
  formDatiSito: Form;

  comboLinee: SelectOption<string, string>[];
  // Form declaration
  form: Form;
  // Create section
  toCreate: LineaExtended = {};
  comboGestori: any;
  comboImpianti: any;

  mandatoryMessage: any;
  soggettiMessage: any;
  soggettiObiettiviMessage: any;

  comboProfili: any;
  isAtti: boolean;
  tabElements: any[] = [
    {
      title: 'IMPIANTI.CREATE.TABS.LINEA',
      link: '/impianti/inserimento'
    },
    {
      title: 'IMPIANTI.CREATE.TABS.ATTO',
      link: '/impianti/inserimento/atti'
    }
  ];
  activeElement: string;
  columns: TableColumn[] = [];
  dataSource: LocalPagedDataSource<LineaExtended>;
  columnsAtti: TableColumn[] = [];
  dataSourceAtti: LocalPagedDataSource<Atto>;
  comboAtti: SelectOption<string, string>[];
  onVerificaPresenzaDa: boolean = false;
  gestore: Gestore;
  impianto: Impianto;
  dichiarazioneEditingStore: DichiarazioneEditingStore;
  draftEnabled: boolean;
  sendEnabled: boolean;
  status: statusFormEditingStore;
  currentDichiarazione: Dichiarazione;
  isProfileBo: boolean;
  keyDichiarazione: string;
  constructor(
    private service: DichiarazioneService,
    private router: Router,
    private route: ActivatedRoute,
    private notification: NotificationService,
    private datePipe: DatePipe,
    private modalService: ModalService,
    private securityService: SecurityService,
    private loadingService: LoadingService,
    private utility: UtilityService,
    private editingStoreService: DichiarazioneEditingStoreService
  ) {}

  ngOnInit(): void {
    this.isProfileBo = this.securityService.isProfileBo();
    this._initACL();
    this._initHelper();
    this.onVerificaPresenzaDa = false;
    this.draftEnabled = true;
    this.sendEnabled = false;
    this.editingStoreService.reset();

    forkJoin([
      this.service.getComboDichiarazioneGestori().pipe(untilDestroyed(this)),
      this.utility.getMessage('E010').pipe(untilDestroyed(this)),
      this.utility.getMessage('A017').pipe(untilDestroyed(this)),
      this.utility.getMessage('A018').pipe(untilDestroyed(this))
    ]).subscribe((results) => {
      this.comboGestori = results[0];
      this.mandatoryMessage = results[1];
      this.soggettiMessage = (results[2] as any).content;
      this.soggettiObiettiviMessage = (results[3] as any).content;
      this._initForm();
      this.loadingService.hide();
    });

    this.editingStoreService
      .getStoredDichiarazione()
      .subscribe((dichiarazione) => {
        if (dichiarazione && dichiarazione.key && dichiarazione.key === 'new') {
          this._changeDichiarazione(dichiarazione);
          this.keyDichiarazione = dichiarazione.key;
        }
      });

    this.editingStoreService
      .getStoreStatus()
      .subscribe((statusFormEditingStore) => {
        this._changeStatus(statusFormEditingStore);
      });
  }

  checkValidity(dichiarazione, rifiutiConfIsEmpty, versamentiIsEmpty) {
    if (
      dichiarazione?.dichiarazione?.rifiutiConferiti?.rifiutiConferiti ===
        null ||
      dichiarazione?.dichiarazione?.rifiutiConferiti?.rifiutiConferiti
        ?.length == 0
    ) {
      rifiutiConfIsEmpty = true;
    }
    if (dichiarazione?.dichiarazione?.versamenti?.versamenti) {
    }

    if (dichiarazione?.dichiarazione?.versamenti?.versamenti) {
      for (const element of dichiarazione?.dichiarazione?.versamenti
        ?.versamenti) {
        if (element.importoVersato != 0) {


          return versamentiIsEmpty = false;
        } else {
          versamentiIsEmpty = true;
        }
       // return;
      }
    } else {
      versamentiIsEmpty = true;
    }
  }
  _changeStatus(s: statusFormEditingStore) {
    this.status = { ...s };
    // if(this.status.rifiutiValid && this.status.versamentiValid){
    //   this.draftEnabled = true;
    // }else{
    //   this.draftEnabled = false;
    // }
    let rifiutiConfIsEmpty = false;
    let versamentiIsEmpty = false;

    this.editingStoreService
      .getStoredDichiarazione(this.keyDichiarazione)
      .subscribe((dichiarazione) => {
      this.checkValidity(dichiarazione, rifiutiConfIsEmpty, versamentiIsEmpty);
      });
      console.log(!versamentiIsEmpty,
        !rifiutiConfIsEmpty,
        this.status.rifiutiValid,
        this.status.versamentiValid,
        this.status.sedeValid)
    if (
      !versamentiIsEmpty &&
      !rifiutiConfIsEmpty &&
      this.status.rifiutiValid &&
      this.status.versamentiValid &&
      this.status.sedeValid
    ) {
      this.sendEnabled = true;
    } else {
      this.sendEnabled = false;
    }
  }

  _changeDichiarazione(dichiarazione: DichiarazioneEditingStore) {
    this.dichiarazioneEditingStore = dichiarazione;
  }

  onVerificaPresenza() {
    this.loadingService.show();
    this.service
      .existDichiarazione({
        idImpianto: this.form.get('idImpianto').value,
        anno: this.form.get('anno').value,
        idGestore: this.form.get('idGestore').value
      })
      .pipe(untilDestroyed(this))
      .subscribe((results) => {
        if (results.content) {
          // E014
          this.utility.getMessage('E014').subscribe((result: any) => {
            const { content } = result;
            this.loadingService.hide();
            if (content) {
              const dialog = this.modalService.openDialog(
                PopupDichiarazioneExistComponent,
                {
                  sizeModal: 'xs',
                  header: content.titoloMsg,
                  showCloseButton: true,
                  context: { messageConfirm: content }
                }
              );
              dialog.dismissed.pipe(untilDestroyed(this)).subscribe(() => {
                this.router.navigate(['dichiarazioni-annuali']);
              });
            }
          });
          this.utility.getMessage('E014').pipe(untilDestroyed(this));
        } else {
          const annoPrec: number = +this.form.get('anno').value - 1;
          forkJoin([
            this.service
              .getGestore(this.form.get('idGestore').value)
              .pipe(untilDestroyed(this)),
            this.service
              .getImpianto(this.form.get('idImpianto').value)
              .pipe(untilDestroyed(this)),
            this.service
              .getDichiarazioniSingleResult({
                anno: annoPrec,
                idImpianto: this.form.get('idImpianto').value,
                idGestore: this.form.get('idGestore').value
              })
              .pipe(untilDestroyed(this))
          ])
            .pipe(untilDestroyed(this))
            .subscribe((results) => {
              this.gestore = results[0].content;
              this.impianto = results[1].content;
              let dichiarazionePrecedente: Dichiarazione = null;
              if (results[2].content) {
                dichiarazionePrecedente = {
                  idDichAnnuale: +(results[2].content as any).idDichAnnuale,
                  anno: +(results[2].content as any).anno,
                  creditoImposta: +(results[2].content as any).creditoImposta,
                  saldoImposta: +(results[2].content as any).saldoImposta,
                  versione: +(results[2].content as any).saldoImposta
                };
              }
              this.editingStoreService.setDichiarazione({
                key: 'new',
                status: {
                  versamentiValid: false,
                  rifiutiValid: false,
                  sedeValid: false,
                  versamentiChanged: false,
                  rifiutiChanged: false,
                  sedeChanged: false,
                  soggettiChanged: false,
                  annotazioniChanged: false
                },
                versamentiValid: false,
                rifiutiValid: false,
                sedeValid: false,
                dichiarazione: {
                  impianto: {
                    idImpianto: +this.form.get('idImpianto').value,
                    gestore: {
                      idGestore: +this.form.get('idGestore').value
                    }
                  },
                  soggettiMr: [],
                  annotazioni: null,
                  indirizzo: null,
                  rifiutiConferiti: { rifiutiConferiti: [] },
                  anno: +this.form.get('anno').value,
                  versione: +this.form.get('versione').value,
                  dataDichiarazione: this.form.get('dataDichiarazione').value
                },
                dichiarazionePrecedente: dichiarazionePrecedente
              });
              this._setOnVerificaPresenzaDa();
              this.loadingService.hide();
            });
        }
      });
  }

  _setOnVerificaPresenzaDa(v: boolean = true) {
    this.onVerificaPresenzaDa = v;
    if (v) {
      this.form.get('idGestore').disable({ emitEvent: false });
      this.form.get('idImpianto').disable({ emitEvent: false });
      this.form.get('anno').disable();
      this.form.get('dataDichiarazione').disable();
    }
  }

  onCreate() {
    const dich = { ...this.dichiarazioneEditingStore.dichiarazione };
    const obiettiviNonRaggiunti = dich.soggettiMr.find(
      (i) => i.obbRagg === false
    );
    if (dich.soggettiMr.length == 0) {
      const dialog = this.modalService.openDialog(ConfirmSaveComponent, {
        sizeModal: 'xs',
        header: this.soggettiMessage.titoloMsg,
        showCloseButton: true,
        context: { messageConfirm: this.soggettiMessage }
      });

      dialog.closed
        .pipe(untilDestroyed(this))
        .subscribe(() => this.confirmCreate());
    } else if (dich.soggettiMr.length > 0 && obiettiviNonRaggiunti) {
      const dialog = this.modalService.openDialog(ConfirmSaveComponent, {
        sizeModal: 'xs',
        header: this.soggettiObiettiviMessage.titoloMsg,
        showCloseButton: true,
        context: { messageConfirm: this.soggettiObiettiviMessage }
      });

      dialog.closed
        .pipe(untilDestroyed(this))
        .subscribe(() => this.confirmCreate());
    } else {
      this.confirmCreate();
    }
  }

  confirmCreate() {
    this.loadingService.show();
    const dich = this.parseDichiarazioneAnnualeSave({
      ...this.dichiarazioneEditingStore.dichiarazione
    });

    this.service
      .insertDichiarazione(dich)
      .pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response) => {
        this.notification.info({
          title: response.message.titoloMsg,
          text: response.message.testoMsg
        });
        this.loadingService.hide();
        this.router.navigate(['dichiarazioni-annuali']);
      });
  }

  parseDichiarazioneAnnualeSave(dich: Dichiarazione): Dichiarazione {
    dich.rifiutiConferiti?.rifiutiConferiti?.forEach((item) => {
      delete item.totale;
      delete item.idRifiutoConferito;
      delete item.conferimentiReport;
      delete item.isRam;
    });
    //delete dich.rifiutiConferiti?.totali;
    //let obj = dich.rifiutiConferiti?.totali;  
    //let obj = (_c = dich.rifiutiConferiti) === null || _c === void 0 ? void 0 : _c.totali;
    if (!(dich.rifiutiConferiti === null || dich.rifiutiConferiti === void 0))
    	delete dich.rifiutiConferiti.totali;
    dich.soggettiMr?.forEach((item) => {
      if (typeof item.obbRagg == 'boolean') {
        item.obbRagg = item.obbRagg ? 'SI' : 'NO';
      }
      delete item.idSoggettiMr;
      delete item.isRam;
    });
    return dich;
  }

  setDichiarazioneAnnuale(dichBe: Dichiarazione) {
    let dich: Dichiarazione = { ...dichBe };
    console.log(dich.rifiutiConferiti.rifiutiConferiti);
    if (dich.rifiutiConferiti.rifiutiConferiti != undefined) {
      dich.rifiutiConferiti.rifiutiConferiti.forEach((item) => {
        item.conferimenti.forEach((c) => {
          c.rifiutoTariffa = item.rifiutoTariffa;
        });
      });
    }

    if (!dich.soggettiMr) {
      dich.soggettiMr = [];
    }
    this.currentDichiarazione = dich;
  }

  onDraft() {
    const dich = this.parseDichiarazioneAnnualeSave({
      ...this.dichiarazioneEditingStore.dichiarazione
    });
    this.loadingService.show();
    this.service
      .insertDichiarazioneBozza(dich)
      .pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response) => {
        this.loadingService.hide();
        this.notification.info({
          title: response.message.titoloMsg,
          text: response.message.testoMsg
        });
        this.setDichiarazioneAnnuale(response.content);
        this.editingStoreService.setDichiarazione({
          key: 'new',
          editMode: true,
          viewMode: false,
          status: {
            versamentiValid: true,
            rifiutiValid: true,
            sedeValid: this.currentDichiarazione.indirizzo ? true : false,
            versamentiChanged: false,
            rifiutiChanged: false,
            sedeChanged: false,
            soggettiChanged: false,
            annotazioniChanged: false
          },
          versamentiValid: true,
          rifiutiValid: true,
          sedeValid: false,
          dichiarazione: {
            ...this.currentDichiarazione
          },
          dichiarazionePrecedente: null
        });

        this.loadingService.hide();
      });
  }

  isEditing() {
    if (!this.dichiarazioneEditingStore) {
      return false;
    }

    if (
      this.status.versamentiChanged ||
      this.status.rifiutiChanged ||
      this.status.sedeChanged ||
      this.status.annotazioniChanged ||
      this.status.soggettiChanged
    ) {
      return true;
    }
    return false;
  }

  onBack() {
    if (this.isEditing()) {
      this.utility.getMessage('A001').subscribe((result: any) => {
        const { content } = result;
        if (content) {
          const dialog = this._preventDataLose(content);

          dialog.closed
            .pipe(untilDestroyed(this))
            .subscribe(() => this.router.navigate(['dichiarazioni-annuali']));
        }
      });
      return;
    }

    this.router.navigate(['dichiarazioni-annuali']);
  }

  onChangeActiveTab(tabElement) {
    this.activeElement = tabElement.link;
    if (this.activeElement == '/dichiarazioni-annuali/inserimento') {
      this.isAtti = false;
    } else {
      this.isAtti = true;
    }
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

  private _callImpiantiCallback(call: Observable<any>) {
    call.subscribe((r) => {
      if (
        (this.form.get('idGestore') as AutocompleteInput).value &&
        (r as any).content &&
        (r as any).content.length == 1
      ) {
        this.form
          .get('idImpianto')
          .setValue((r as any).content[0].id.toString());
      }
      this.comboImpianti = r as any;
    });
  }

  private _initForm() {
    const size = '12|6|6|6|6';
    this.form = new Form({
      header: { show: false },
      filter: false,
      controls: {
        idGestore: new AutocompleteInput({
          label: 'DICHIARAZIONI.CREATE.FORM.GESTORE.LABEL',
          placeholder: 'DICHIARAZIONI.CREATE.FORM.GESTORE.PLACEHOLDER',
          options: of(this.comboGestori),
          required: true,
          valueChange: (idGestore: string) => {
            if (idGestore) {
              let call = this.service.getComboDichiarazioneImpianti(+idGestore);
              this._callImpiantiCallback(call);
              (this.form.get('idImpianto') as AutocompleteInput).setOptions(
                call as any
              );
            } else {
              let call = this.service.getComboDichiarazioneImpianti();
              this._callImpiantiCallback(call);
              (this.form.get('idImpianto') as AutocompleteInput).setOptions(
                call as any
              );
            }
            (this.form.get('idImpianto') as AutocompleteInput).setValue(null);
          },
          size,
          clearable: true
        }),
        idImpianto: new AutocompleteInput({
          label: 'DICHIARAZIONI.CREATE.FORM.IMPIANTO.LABEL',
          placeholder: 'DICHIARAZIONI.CREATE.FORM.IMPIANTO.PLACEHOLDER',
          required: true,
          options: of() as any,
          valueChange: (selected) => {
            const impianto = this.comboImpianti.content.find(
              (c) => c.id === selected
            );
            if (
              impianto &&
              !(this.form.get('idGestore') as AutocompleteInput).value
            ) {
              this.form
                .get('idGestore')
                .setValue(impianto.additionalValue, { emitEvent: false });
              this.form.get('idGestore').setErrors(null);
            }
          },
          size,
          clearable: true
        }),
        anno: new TextInput({
          label: 'DICHIARAZIONI.CREATE.FORM.ANNO.LABEL',
          placeholder: 'DICHIARAZIONI.CREATE.FORM.ANNO.PLACEHOLDER',
          type: 'text',
          size,
          value: (new Date().getFullYear() - 1).toString(),
          clearable: true,
          required: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ]
        }),
        dataDichiarazione: new DateInput({
          label: 'DICHIARAZIONI.CREATE.FORM.DATA.LABEL',
          placeholder: 'DICHIARAZIONI.CREATE.FORM.DATA.PLACEHOLDER',
          size,
          clearable: true,
          value: this.datePipe.transform(new Date(), 'yyyy-MM-dd'),
          required: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ]
        }),
        versione: new TextInput({
          label: 'DICHIARAZIONI.CREATE.FORM.VERSIONE.LABEL',
          placeholder: 'DICHIARAZIONI.CREATE.FORM.VERSIONE.PLACEHOLDER',
          type: 'text',
          size,
          clearable: true,
          value: '1',
          readonly: true,
          required: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ]
        })
      }
    });

    let call = this.service.getComboDichiarazioneImpianti();
    this._callImpiantiCallback(call);
    (this.form.get('idImpianto') as AutocompleteInput).setOptions(call as any);

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
          required: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ]
        }),
        indirizzo: new TextInput({
          label: 'IMPIANTI.FORMDATISITO.INDIRIZZO.LABEL',
          placeholder: 'IMPIANTI.FORMDATISITO.INDIRIZZO.PLACEHOLDER',
          type: 'text',
          size,
          clearable: true,
          required: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ]
        }),
        idProvincia: new AutocompleteInput({
          label: 'IMPIANTI.FORMDATISITO.PROVINCIA.LABEL',
          placeholder: 'IMPIANTI.FORMDATISITO.PROVINCIA.PLACEHOLDER',
          options: this.service.getComboProvince() as any,
          valueChange: (idProvincia: number) => {
            if (idProvincia) {
              (
                this.formDatiSito.get('idComune') as AutocompleteInput
              ).setOptions(this.service.getComboComuni(+idProvincia) as any);
            } else {
              (
                this.formDatiSito.get('idComune') as AutocompleteInput
              ).setOptions(of() as any);
            }
          },
          size,
          clearable: true,
          required: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ]
        }),
        idComune: new AutocompleteInput({
          label: 'IMPIANTI.FORMDATISITO.COMUNE.LABEL',
          placeholder: 'IMPIANTI.FORMDATISITO.COMUNE.PLACEHOLDER',
          options: of() as any,
          valueChange: (idComune: number) => {
            if (idComune) {
              let cap = this.service.getCap(+idComune);
              this.formDatiSito.get('cap').setValue(cap);
            }
          },
          size,
          clearable: true,
          required: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
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
}
