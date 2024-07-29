import {
  Component,
  OnDestroy,
  OnInit,
  TemplateRef,
  ViewChild
} from '@angular/core';
import { DichiarazioniACL } from '@pages/dichiarazioni/models/acl.model';
import {
  AutocompleteInput,
  CheckboxInput,
  DateInput,
  Form,
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
import { DatePipe } from '@angular/common';
import { SecurityService, UtilityService } from '@core/services';
import { ConfirmExitComponent } from '@pages/dichiarazioni/components/confirm-exit/confirm-exit.component';
import { forkJoin, of } from 'rxjs';
import {
  Dichiarazione,
  DichiarazioneEditingStore,
  statusFormEditingStore
} from '../../models/dichiarazione.model';
import { DichiarazioneEditingStoreService } from '../../services/dichiarazione-editing-store.service';
import { ConfirmSaveComponent } from '../../components';
import { Console } from 'console';
import { Validators } from '@angular/forms';

@UntilDestroy()
@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.scss']
})
export class EditComponent implements OnInit, OnDestroy {
  acl: DichiarazioniACL;
  helperTitle: string = '';
  // Form declaration

  mandatoryMessage: any;
  soggettiMessage: any;
  soggettiObiettiviMessage: any;

  form: Form;
  comboGestori: any;

  currentDichiarazione: Dichiarazione;
  dichiarazioneEditingStore: DichiarazioneEditingStore;

  draftEnabled: boolean;
  sendEnabled: boolean;
  duplicateEnabled: boolean;

  editMode: boolean = false;
  readonly: boolean = true;
  keyDichiarazione: string;
  status: statusFormEditingStore;

  isProfileBo: boolean;

  constructor(
    private service: DichiarazioneService,
    private router: Router,
    private modalService: ModalService,
    private editingStoreService: DichiarazioneEditingStoreService,
    private loadingService: LoadingService,
    private utility: UtilityService,
    private datePipe: DatePipe,
    private route: ActivatedRoute,
    private notification: NotificationService,
    private securityService: SecurityService
  ) {}

  ngOnInit(): void {
    this.isProfileBo = this.securityService.isProfileBo();
    this._initACL();
    this._initHelper();
    this.draftEnabled = true;
    this.sendEnabled = false;
    this.editingStoreService.reset();

    this.route.data.pipe(untilDestroyed(this)).subscribe((data: any) => {
      this.editingStoreService.reset();

      this.setDichiarazioneAnnuale(data['dichiarazione'].content);

      this.keyDichiarazione =
        'dichiarazione_' + this.currentDichiarazione.idDichAnnuale.toString();

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

      this.editingStoreService.setDichiarazione(
        {
          key: this.keyDichiarazione,
          editMode: this.editMode,
          viewMode: !this.editMode,
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
        },
        this.keyDichiarazione
      );

      ///this.editingStoreService.reset();
      this.editingStoreService
        .getStoredDichiarazione(this.keyDichiarazione)
        .subscribe((dichiarazione) => {
          //console.log(dichiarazione);
          if (
            dichiarazione &&
            dichiarazione.key &&
            dichiarazione.key === this.keyDichiarazione
          ) {
            this._changeDichiarazione(dichiarazione);
          }
        });
    });
    this.editingStoreService
      .getStoreStatus()
      .subscribe((statusFormEditingStore) => {
        this._changeStatus(statusFormEditingStore);
        /* if(this.status.rifiutiValid && this.status.versamentiValid && this.status.sedeValid){
          this.sendEnabled = true;
        }*/
      });
  }
  //Refactor for sonar issues
  checkDichiarazione(dichiarazione): boolean {
    return (
      dichiarazione?.dichiarazione?.rifiutiConferiti?.rifiutiConferiti ===
        null ||
      dichiarazione?.dichiarazione?.rifiutiConferiti?.rifiutiConferiti
        ?.length == 0 ||
      dichiarazione?.dichiarazione?.rifiutiConferiti?.rifiutiConferiti ==
        undefined
    );
  }
  checkStatus(versamentiIsEmpty, rifiutiConfIsEmpty): boolean {
    return (
      !versamentiIsEmpty &&
      !rifiutiConfIsEmpty &&
      this.status.rifiutiValid &&
      this.status.versamentiValid &&
      this.status.sedeValid
    );
  }
  _changeStatus(s: statusFormEditingStore) {
    let rifiutiConfIsEmpty = false;
    let versamentiIsEmpty = false;

    this.status = { ...s };
    if (this.status.rifiutiValid && this.status.versamentiValid) {
      this.draftEnabled = true;
    } else {
      //this.draftEnabled = false;
    }

    this.editingStoreService
      .getStoredDichiarazione(this.keyDichiarazione)
      .subscribe((dichiarazione) => {
        if (this.checkDichiarazione(dichiarazione)) {
          rifiutiConfIsEmpty = true;
        } else {
          rifiutiConfIsEmpty = false;
        }

        if (dichiarazione?.dichiarazione?.versamenti?.versamenti != undefined) {
          for (const element of dichiarazione?.dichiarazione?.versamenti
            ?.versamenti) {
            if (element.importoVersato != 0) {
              versamentiIsEmpty = false;

              if (this.checkStatus(versamentiIsEmpty, rifiutiConfIsEmpty)) {
                // console.log(
                //   !versamentiIsEmpty,
                //   !rifiutiConfIsEmpty,
                //   this.status.rifiutiValid,
                //   this.status.versamentiValid,
                //   this.status.sedeValid
                // );

                this.sendEnabled = true;
              } else {
                this.sendEnabled = false;
              }
              return;
            } else {
              versamentiIsEmpty = true;
            }
            // return;
          }
        } else {
          versamentiIsEmpty = true;
        }
      });
    /*console.log(
      !versamentiIsEmpty,
      !rifiutiConfIsEmpty,
      this.status.rifiutiValid,
      this.status.versamentiValid,
      this.status.sedeValid
    );*/
    /*   if (
      !versamentiIsEmpty &&
      !rifiutiConfIsEmpty &&
      this.status.rifiutiValid &&
      this.status.versamentiValid &&
      this.status.sedeValid
    ) {
      this.sendEnabled = true;
    } else {
      this.sendEnabled = false;
    }*/
  }

  _changeDichiarazione(dichiarazione: DichiarazioneEditingStore) {
    this.dichiarazioneEditingStore = dichiarazione;
  }

  setDichiarazioneAnnuale(dichBe: Dichiarazione) {
    let dich: Dichiarazione = { ...dichBe };
    if (dich.rifiutiConferiti.rifiutiConferiti != undefined) {
      dich.rifiutiConferiti?.rifiutiConferiti.forEach((item) => {
        item.conferimenti.forEach((c) => {
          c.rifiutoTariffa = item.rifiutoTariffa;
        });
      });
    }
    if (!dich.soggettiMr) {
      dich.soggettiMr = [];
    }
    this.currentDichiarazione = dich;
    // Dichiarazione in stato bozza, ergo modificabile

    if (
      this.currentDichiarazione.statoDichiarazione.idStatoDichiarazione == 1 &&
      this.acl.content.update &&
      !this.isProfileBo
    ) {
      this.editMode = true;
    } else {
      this.editMode = false;
    }

    // Dichiarazione in stato inviata o annullata utente FO che ha acl di insert
    if (
      (this.currentDichiarazione.statoDichiarazione.idStatoDichiarazione == 2 ||
        this.currentDichiarazione.statoDichiarazione.idStatoDichiarazione ==
          3) &&
      this.acl.content.insert &&
      !this.isProfileBo
    ) {
      this.duplicateEnabled = true;
    } else {
      this.duplicateEnabled = false;
    }
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
    if (!this.isProfileBo && this.isEditing()) {
      this.utility.getMessage('A001').subscribe((result: any) => {
        const { content } = result;
        if (content) {
          const dialog = this._preventDataLose(content);

          dialog.closed
            .pipe(untilDestroyed(this))
            .subscribe(() =>
              this.router.navigate(['dichiarazioni-annuali', 'lista'])
            );
        }
      });
      return;
    }

    this.router.navigate(['dichiarazioni-annuali', 'lista']);
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

  private _initForm() {
    const size = '12|6|6|6|6';
    this.form = new Form({
      header: { show: false },
      filter: false,
      controls: {
        idGestoreDich: new AutocompleteInput({
          label: 'DICHIARAZIONI.CREATE.FORM.GESTORE.LABEL',
          placeholder: 'DICHIARAZIONI.CREATE.FORM.GESTORE.PLACEHOLDER',
          options: of(this.comboGestori),
          required: true,
          value:
            this.currentDichiarazione.impianto.gestore.idGestore.toString(),
          valueChange: (idGestore: string) => {
            if (idGestore) {
              let call = this.service.getComboDichiarazioneImpianti(+idGestore);
              call.subscribe((r) => {
                if ((r as any).content && (r as any).content.length == 1) {
                  this.form
                    .get('idImpianto')
                    .setValue((r as any).content[0].id.toString());
                }
              });
              (this.form.get('idImpianto') as AutocompleteInput).setOptions(
                call as any
              );
              (this.form.get('idImpianto') as AutocompleteInput).enable();
            } else {
              (this.form.get('idImpianto') as AutocompleteInput).setOptions(
                of() as any
              );
              (this.form.get('idImpianto') as AutocompleteInput).enable();
            }
          },
          size,
          readonly: true,
          clearable: true
        }),
        idImpianto: new AutocompleteInput({
          label: 'DICHIARAZIONI.CREATE.FORM.IMPIANTO.LABEL',
          placeholder: 'DICHIARAZIONI.CREATE.FORM.IMPIANTO.PLACEHOLDER',
          required: true,
          value: this.currentDichiarazione.impianto.idImpianto.toString(),
          readonly: true,
          options: of() as any,
          size,
          clearable: true
        }),
        anno: new TextInput({
          label: 'DICHIARAZIONI.CREATE.FORM.ANNO.LABEL',
          placeholder: 'DICHIARAZIONI.CREATE.FORM.ANNO.PLACEHOLDER',
          type: 'text',
          size,
          value: this.currentDichiarazione.anno.toString(),
          clearable: true,
          required: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ],
          readonly: true
        }),
        dataDichiarazione: new DateInput({
          label: 'DICHIARAZIONI.CREATE.FORM.DATA.LABEL',
          placeholder: 'DICHIARAZIONI.CREATE.FORM.DATA.PLACEHOLDER',
          size,
          clearable: true,
          value: this.currentDichiarazione.dataDichiarazione
            ? this.datePipe.transform(
                this.currentDichiarazione.dataDichiarazione,
                'yyyy-MM-dd'
              )
            : this.datePipe.transform(new Date(), 'yyyy-MM-dd'),
          //value: this.datePipe.transform(new Date(), 'yyyy-MM-dd'),
          required: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ],
          readonly: true
        }),
        versione: new TextInput({
          label: 'DICHIARAZIONI.CREATE.FORM.VERSIONE.LABEL',
          placeholder: 'DICHIARAZIONI.CREATE.FORM.VERSIONE.PLACEHOLDER',
          type: 'text',
          size,
          clearable: true,
          value: this.currentDichiarazione.versione.toString(),
          readonly: true,
          required: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ]
        }),
        stato: new TextInput({
          label: 'DICHIARAZIONI.CREATE.FORM.STATO.LABEL',
          placeholder: 'DICHIARAZIONI.CREATE.FORM.STATO.PLACEHOLDER',
          type: 'text',
          size,
          clearable: true,
          value:
            this.currentDichiarazione.statoDichiarazione
              .descrStatoDichiarazione,
          readonly: true,
          required: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ]
        }),
        numProtocollo: new TextInput({
          label: 'DICHIARAZIONI.CREATE.FORM.PROTOCOLLO.LABEL',
          placeholder: this.currentDichiarazione.statoDichiarazione
          .idStatoDichiarazione === 1 &&
        this.currentDichiarazione.pregresso ? 'DICHIARAZIONI.CREATE.FORM.PROTOCOLLO.PLACEHOLDER': ' ',
          type: 'text',
          size,
          required:this.acl.content.profiloPregresso? true: false,
          // clearable: true,
          pattern: '^[0-9]{8}(\/)[0-9]{4}$',
          value: this.currentDichiarazione.numProtocollo
            ? this.currentDichiarazione.numProtocollo.toString()
            : null,
          readonly:
            this.acl.content.profiloPregresso &&
            this.currentDichiarazione.statoDichiarazione
              .idStatoDichiarazione === 1 &&
            this.currentDichiarazione.pregresso
              ? false
              : true,
              validatorOrOpts: [Validators.pattern('^[0-9]{8}(\/)[0-9]{4}$'), Validators.required],

          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            }),
            ValidationStatus.ERROR.CUSTOM((control) => control.hasError('pattern'), {
              //da definire se viene tornato dal be
              text: 'Numero protocollo non valido'
            })
          ]
        }),
        dataProtocollo:  new DateInput({
          label: 'DICHIARAZIONI.CREATE.FORM.DATA_PROTOCOLLO.LABEL',
          placeholder: 'DICHIARAZIONI.CREATE.FORM.DATA_PROTOCOLLO.PLACEHOLDER',
          size,
          // clearable: true,
          value: this.currentDichiarazione.dataProtocollo
            ? this.datePipe.transform(
                this.currentDichiarazione.dataProtocollo,
                'yyyy-MM-dd'
              )
            : null,
            required:this.acl.content.profiloPregresso? true: false,

          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ],
          readonly:
            this.acl.content.profiloPregresso &&
            this.currentDichiarazione.statoDichiarazione
              .idStatoDichiarazione === 1
              ? false
              : true
        }),
        pregresso: new CheckboxInput({
          label: 'DICHIARAZIONI.CREATE.FORM.PREGRESSO',
          size: '12',
          value: this.currentDichiarazione?.pregresso,
          required: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ],
          readonly: true
        })
      }
    });

    if (this.currentDichiarazione.impianto.gestore.idGestore) {
      (this.form.get('idImpianto') as AutocompleteInput).setOptions(
        this.service.getComboDichiarazioneImpianti(
          +this.currentDichiarazione.impianto.gestore.idGestore
        ) as any
      );
    }

    //EVOLUTIVA REQ_4
   /* if (this.currentDichiarazione.pregresso) {
      this.form.addControlAfter(
        'dataProtocollo',
        new DateInput({
          label: 'DICHIARAZIONI.CREATE.FORM.DATA_PROTOCOLLO.LABEL',
          placeholder: 'DICHIARAZIONI.CREATE.FORM.DATA_PROTOCOLLO.PLACEHOLDER',
          size,
          // clearable: true,
          value: this.currentDichiarazione.dataProtocollo
            ? this.datePipe.transform(
                this.currentDichiarazione.dataProtocollo,
                'yyyy-MM-dd'
              )
            : this.datePipe.transform(new Date(), 'yyyy-MM-dd'),
          required: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ],
          readonly:
            this.acl.content.profiloPregresso &&
            this.currentDichiarazione.statoDichiarazione
              .idStatoDichiarazione === 1
              ? false
              : true
        }),
        'numProtocollo'
      );
    }*/
    ///////////////////////////
  }
  checkDuplicate(results) {
    if (results.content) {
      this.service
        .duplicaDichiarazione(this.currentDichiarazione.idDichAnnuale)
        .pipe(csiCatchError(), untilDestroyed(this))
        .subscribe((response) => {
          if (response.message) {
            this.notification.info({
              title: response.message.titoloMsg,
              text: response.message.testoMsg
            });
          }
          this.loadingService.hide();
          if (response.content.idDichAnnuale) {
            this.router
              .navigateByUrl('/', { skipLocationChange: true })
              .then(() =>
                this.router.navigate([
                  'dichiarazioni-annuali',
                  response.content.idDichAnnuale,
                  'edit'
                ])
              );
            // this.router.navigate(['dichiarazioni-annuali',response.content.idDichAnnuale,'edit'], {
            //   queryParams: { lastUpdate: Date.now() }
            // });
          }
        });
    } else {
      this.loadingService.hide();
      if (results.message) {
        this.notification.info({
          title: results.message.titoloMsg,
          text: results.message.testoMsg
        });
      }
    }
  }
  onDuplicate() {
    if (this.duplicateEnabled) {
      this.loadingService.show();
      this.service
        .allowDuplicaDichiarazione({
          idImpianto: this.currentDichiarazione.impianto.idImpianto,
          anno: this.currentDichiarazione.anno,
          idGestore: this.currentDichiarazione.impianto.gestore.idGestore,
          versione: this.currentDichiarazione.versione
        })
        .pipe(untilDestroyed(this))
        .subscribe((results) => {
          this.checkDuplicate(results);
        });
    }
  }

  onUpdate() {
    if (this.acl.content.update && this.editMode) {
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
          .subscribe(() => this.confirmUpdate());
      } else if (dich.soggettiMr.length > 0 && obiettiviNonRaggiunti) {
        const dialog = this.modalService.openDialog(ConfirmSaveComponent, {
          sizeModal: 'xs',
          header: this.soggettiObiettiviMessage.titoloMsg,
          showCloseButton: true,
          context: { messageConfirm: this.soggettiObiettiviMessage }
        });

        dialog.closed
          .pipe(untilDestroyed(this))
          .subscribe(() => this.confirmUpdate());
      } else {
        this.confirmUpdate();
      }
    }
  }

  confirmUpdate() {
    let dich = { ...this.dichiarazioneEditingStore.dichiarazione };

    dich = this.parseDichiarazioneAnnualeSave(dich);
    this.loadingService.show();
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

  onDraft() {
    if (this.acl.content.update && this.editMode) {
      let dich: Dichiarazione = {
        ...this.dichiarazioneEditingStore.dichiarazione
      };
      //REQ4
      console.log(this.form.get('pregresso').value);
      if (
        this.acl.content.profiloPregresso &&
        this.dichiarazioneEditingStore.dichiarazione.pregresso
      ) {
        dich.dataProtocollo = this.form.get('dataProtocollo').value;
        dich.numProtocollo = this.form.get('numProtocollo').value;
        dich.pregresso =
          this.form.get('pregresso').value.toString() === 'true' ? true : false;
      }

      dich = this.parseDichiarazioneAnnualeSave(dich);
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
          this.editingStoreService.setDichiarazione(
            {
              key: this.keyDichiarazione,
              editMode: this.editMode,
              viewMode: !this.editMode,
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
            },
            this.keyDichiarazione
          );

          this.loadingService.hide();
        });
    }
  }
  ngOnDestroy(): void {
    //This is intentional
  }
}
