import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Data, Router } from '@angular/router';
import { SecurityService, UtilityService } from '@app/core/services';
import {
  AutocompleteInput,
  DateInput,
  Form,
  SelectOption,
  TextInput,
  ValidationStatus
} from '@app/shared/form';
import { distinct, map, mergeMap, switchMap, tap } from 'rxjs/operators';
import { concat, forkJoin, iif, Observable, of, throwError } from 'rxjs';
import { DatePipe } from '@angular/common';
import { MrService } from '../services/mr.service';
import { Gestore } from '@app/pages/dichiarazioni/models/gestore.model';
import { Impianto } from '@app/pages/impianti/models/impianto.model';
import { LoadingService } from '@app/theme/layouts/loading.services';
import { IMessage } from '@app/core/models/shared.model';
import { ModalService } from '@app/shared/modal/modal.service';
import { csiCatchError } from '@app/core/operators/catch-error.operator';
import { NotificationService } from '@app/shared/notification/notification.service';
import { IPrevCons } from '../interfaces/prev-cons.interface';
import { PrevConsEditAbstract } from '../models/abstract-factory/prev-cons-edit-abstract';
import { RichiestaInserimento } from '../models/richiesta/richiesta-inserimento';
import { DichiarazioneInserimento } from '../models/dichiarazione/dichiarazione-inserimento';
import { PrevConsClass } from '../models/classes/prev-cons.class';
import { PopupMrExistComponent } from '../modals/popup-mr-exist/popup-mr-exist.component';
import { MrStoreService } from '../services/mr-store.service';
import { ConfirmMrExitComponent } from '../modals/confirm-mr-exit/confirm-mr-exit.component';
import { ICombo } from '../interfaces/api-mr.model';
import { DichiarazioneModifica } from '../models/dichiarazione/dichiarazione-modifica';
import { RichiestaModifica } from '../models/richiesta/richiesta-modifica';
import { STATO_PREV_CONS } from '../models/constants';

@UntilDestroy()
@Component({
  selector: 'app-prev-cons-edit',
  templateUrl: './prev-cons-edit.component.html',
  styleUrls: ['./prev-cons-edit.component.scss']
})
export class PrevConsEditComponent implements OnInit {
  mr: PrevConsEditAbstract;
  form!: Form;
  onVerificaPresenzaMr = false;
  gestore: Gestore;
  impianto: Impianto;
  isProfileBO = false;

  private comboLocalImpianti: Observable<ICombo>;
  E014message: IMessage;
  A001message: IMessage;
  A019message: IMessage;
  mandatoryMessage: IMessage;

  prevConsRMR?: PrevConsClass;

  // booleani per mostrare o meno i pulsanti appropriati alla sezione
  showNewPrevConsBtn: boolean;
  showTabs: boolean;
  showDraftCreateBtn: boolean;

  disabledForm = true;
  draftEnabled: boolean = true;

  isInserimento: boolean;

  constructor(
    readonly route: ActivatedRoute,
    readonly router: Router,
    readonly utilityService: UtilityService,
    readonly datePipe: DatePipe,
    readonly mrService: MrService,
    readonly securityService: SecurityService,
    private loadingService: LoadingService,
    private modalService: ModalService,
    private notification: NotificationService,
    private storeService: MrStoreService
  ) {
    // recupero parametro "page" dal data e so in che pagina mi trovo.
    this.route.data.pipe().subscribe((res) => {
      this.mr = this._createClassFactory(res);
    });
  }

  ngOnInit(): void {
    this.isProfileBO = this.securityService.isProfileBo();
    this._fork().subscribe((res) => {
      if ('help' in res) {
        this.mr.setHelperTitle(res.help.content.testoInfo);
      }
      if ('acl' in res) {
        this.mr.setProfiloACL(res.acl);
        // controllo per verificare se ho la prevCons o la R-MR
        const specificChek = this.isInserimento
          ? this.onVerificaPresenzaMr
          : this.mr.prevCons != null && this.mr.prevCons != undefined;
        this._setIsEditMode();
        this.showNewPrevConsBtn = this.mr.showNewPrevConsBtn(specificChek);
        this.showTabs = this.mr.showTabs(specificChek);
        this.showDraftCreateBtn =
          this.mr.showDraftCreateBtn(specificChek) && !this.isProfileBO;
      }
      if ('code' in res) {
        const [E010, E014, A001, A019, ...arg] = res.code;
        this.mandatoryMessage = E010.content;
        this.E014message = E014.content;
        this.A001message = A001.content;
        this.mr.setModalPros(A019.content);
        this.A019message = A019.content;
      }
      if ('combo' in res) {
        this.form = this.initForm(res.combo, this.prevConsRMR);
        this.form.statusChanges.pipe(distinct()).subscribe((res) => {
          this.disabledForm = res === 'INVALID';
        });
      }
      this.loadingService.hide();
    });
  }

  /**
   * @description Controlla che non siano già presenti prevCons con i parametri idImpianto,idGestorePrevCons ed annoTributo selezionati,
   * questa funzione viene utilizzata solo in caso di inserimento.
   */
  onVerificaPresenza() {
    this.loadingService.show();
    this.mrService
      .existPrevCons({
        idImpianto: this.form.get('idImpianto').value,
        annoTributo: this.form.get('annoTributo').value,
        idGestore: this.form.get('idGestorePrevCons').value,
        idTipoDoc: this.mr.idTipoDoc
      })
      .pipe(
        mergeMap((response) => {
          if (!!response?.content) {
            return throwError(() => new Error('response'));
          }
          return forkJoin([
            this.mrService.getGestore(this.form.get('idGestorePrevCons').value),
            this.mrService.getImpianto(this.form.get('idImpianto').value),
            this.mrService.getImpiantiLinee({
              idImpianto: this.form.get('idImpianto').value
            })
          ]);
        }),
        untilDestroyed(this)
      )
      .subscribe(
        ([gestore, impianto, impiantiLinee]) => {
          this.gestore = gestore.content;
          this.impianto = impianto.content;

          //se inserisco una D-MR o una R-MR semplici, solo qui creo la mia classe e setto le linee.
          if (!this.prevConsRMR) {
            this.mr.createPrevCons();
            this.mr.prevCons.prevConsLinee =
              this.mr.prevCons.setPrevConsLineeInsert(impiantiLinee);
          }
          this.mr.prevCons.annoTributo = +this.form.get('annoTributo').value;
          this.mr.prevCons.dataDoc = this.form.get('dataDoc').value;

          this._setOnVerificaPresenzaMr();
          this.loadingService.hide();
        },
        (error) => {
          this.loadingService.hide();
          const dialog = this.modalService.openDialog(PopupMrExistComponent, {
            sizeModal: 'xs',
            header: this.E014message.titoloMsg,
            showCloseButton: true,
            context: { messageConfirm: this.E014message }
          });
          dialog.dismissed.pipe(untilDestroyed(this)).subscribe(() => {
            if (this.mr.path === 'dichiarazioni-mr') {

              //In case of error E014 is set the previousPage param to restore the filters
              //In dichiarazioni-mr/pre-nserimento the param is istantly removed
              this.router.navigate([this.mr.path, 'pre-inserimento'], {queryParams: {previousUrl: 'da-duplicate'}});
            } else {
              this.router.navigate([this.mr.path]);
            }
          });
        }
      );
  }

  /**
   * @description Controlla che non siano state fatte modifiche e naviga sulla pagina precedente
   */
  onBack() {
    if (this.mr.isEditing() && this.mr.isEditMode) {
      const dialog = this._preventDataLose(this.A001message);
      dialog.closed
        .pipe(untilDestroyed(this))
        .subscribe(() => this.router.navigate([this.mr.path]));
      return;
    }
    this.router.navigate([this.mr.path]);
  }

  /**
   * @description Salvataggio in bozza
   */
  onDraft() {
    const prevCons = this._parsePrevConsSave({
      ...this.mr.prevCons
    });
    this.loadingService.show();
    this.mrService
      .insertDichiarazioneBozza(prevCons, {
        idImpianto: this.form.get('idImpianto').value,
        idGestore: this.form.get('idGestorePrevCons').value
      })
      .pipe(
        switchMap((response) => {
          return iif(
            () => this.isInserimento,
            of(response),
            this.mrService.getPrevConsResolver(
              this.mr.prevCons.idPrevCons,
              this.mr.idTipoDoc,
              response.message
            )
          );
        }),
        csiCatchError(),
        untilDestroyed(this)
      )
      .subscribe((response) => {
        this.loadingService.hide();
        this.notification.info({
          title: response.message.titoloMsg,
          text: response.message.testoMsg
        });
        if (this.isInserimento) {
          this.router.navigate([this.mr.path, 'lista']);
        } else {
          //in caso di salvataggio in bozza alla modifica, aggiorno i dati con quelli ricevuti da BE
          this.mr.prevCons = this.mr.createPrevCons();
          this.mr.prevCons.setValueFromPrevCons(response);
          this.storeService.resetStatus();
        }
        this.loadingService.hide();
      });
  }

  onCreate() {
    //se lo status si aggiorna correttamente, non passerà mai per questo caso, valutare se eliminare
    if (this.mr.isOpenModalCreate(this.mr.prevCons)) {
      // A019, valori 'Richiesta-ammissione' mancanti
      const dialog = this.modalService.openDialog(PopupMrExistComponent, {
        sizeModal: 'xs',
        header: this.mr.titleModal,
        showCloseButton: true,
        context: { messageConfirm: this.mr.msgModal }
      });
      return dialog;
    } else {
      this._confirmCreate();
    }
  }

  private _fork() {
    return concat(
      this.utilityService
        .getNotaInfo('INSERISCI')
        .pipe(map((res) => ({ help: res }))),
      forkJoin([
        ...['E010', 'E014', 'A001', 'A019'].map((code) =>
          this.utilityService.getMessage(code)
        )
      ]).pipe(map((res) => ({ code: res }))),
      this.mrService
        .getComboDichiarazioneGestori()
        .pipe(map((res) => ({ combo: res }))),
      this.mrService.getStoredAcl().pipe(map((res) => ({ acl: res })))
    ).pipe(untilDestroyed(this));
  }

  //se dichiarazione legata a RMR, gestori e impianto saranno readonly e popolati con i dati che arrivano da RMR
  public initForm(comboGestori: any, prevConsRMR?: PrevConsClass): Form {
    const size = '12|6|6|6|6';
    this.form = new Form({
      header: { show: false },
      filter: false,
      controls: {
        idGestorePrevCons: new AutocompleteInput({
          label: 'DICHIARAZIONI.CREATE.FORM.GESTORE.LABEL',
          placeholder: 'DICHIARAZIONI.CREATE.FORM.GESTORE.PLACEHOLDER',
          value:
            this.mr.prevCons?.impianto.gestore.idGestore.toString() ?? null,
          options: of(comboGestori),
          required: true,
          valueChange: (idGestore: string | number) => {
            //crea variabile global
            this.comboLocalImpianti = this.mrService
              .getComboDichiarazioneImpianti(+idGestore)
              .pipe(
                tap((resp) => {
                  //se ho un solo impianto associato a quel gestore, valirozzo il campo idImpianto con quel dato
                  if (resp.content.length === 1 && idGestore) {
                    this.form
                      .get('idImpianto')
                      .setValue(resp.content[0].id.toString());
                  }
                })
              );
            (this.form.get('idImpianto') as AutocompleteInput).setOptions(
              this.comboLocalImpianti as any
            );

            (this.form.get('idImpianto') as AutocompleteInput).setValue(null);
          },
          size,
          clearable: true,
          validatorOrOpts: { updateOn: 'blur' }
        }),
        idImpianto: new AutocompleteInput({
          label: 'DICHIARAZIONI.CREATE.FORM.IMPIANTO.LABEL',
          placeholder: 'DICHIARAZIONI.CREATE.FORM.IMPIANTO.PLACEHOLDER',
          required: true,
          value: this.mr.prevCons?.impianto.idImpianto.toString() ?? null,
          options: this.mrService.getComboDichiarazioneImpianti() as Observable<
            SelectOption<string | number, string>[]
          >,
          valueChange: (selected) => {
            if (!(this.form.get('idGestorePrevCons') as AutocompleteInput).value) {
              this.comboLocalImpianti.subscribe((value) => {
                let impianto = value.content.find(
                  (item) => item.id === selected
                );
                this.form
                  .get('idGestorePrevCons')
                  .setValue(impianto.additionalValue.toString() ?? null, {
                    emitEvent: false
                  });
                this.form.get('idGestorePrevCons').setErrors(null);
              });
            }
          },
          size,
          clearable: true,
          validatorOrOpts: { updateOn: 'blur' }
        }),
        annoTributo: new TextInput({
          label: 'DICHIARAZIONI.CREATE.FORM.ANNO.LABEL',
          placeholder: 'DICHIARAZIONI.CREATE.FORM.ANNO.PLACEHOLDER',
          type: 'text',
          size,
          value:
            this.mr.prevCons?.annoTributo?.toString() ??
            new Date().getFullYear().toString(),
          clearable: true,
          required: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.testoMsg
            })
          ],
          validatorOrOpts: { updateOn: 'blur' }
        }),
        dataDoc: new DateInput({
          label:
            this.mr.idTipoDoc == 1
              ? 'RICHIESTA_MR.CREATE.FORM.DATA.LABEL'
              : 'DICHIARAZIONI.CREATE.FORM.DATA.LABEL',
          placeholder:
            this.mr.idTipoDoc == 1
              ? 'RICHIESTA_MR.CREATE.FORM.DATA.PLACEHOLDER'
              : 'DICHIARAZIONI.CREATE.FORM.DATA.PLACEHOLDER',
          size,
          clearable: true,
          value:
            this.mr.prevCons?.dataDoc ??
            this.datePipe.transform(new Date(), 'yyyy-MM-dd'),
          required: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.testoMsg
            })
          ],
          validatorOrOpts: { updateOn: 'blur' }
        }),
        statoRichiesta: new TextInput({
          label:
            this.mr.idTipoDoc == 1
              ? 'RICHIESTA_MR.CREATE.FORM.STATO.LABEL'
              : 'DICHIARAZIONI_MR.CREATE.FORM.STATO.LABEL',
          placeholder:
            this.mr.idTipoDoc == 1
              ? 'RICHIESTA_MR.CREATE.FORM.STATO.LABEL'
              : 'DICHIARAZIONI_MR.CREATE.FORM.STATO.LABEL',
          type: 'text',
          size,
          value:
            this.mr.prevCons?.statoDichiarazione?.descrStatoDichiarazione ??
            'BOZZA',
          readonly: true,
          validatorOrOpts: { updateOn: 'blur' }
        })
      }
    });

    // in inserimento DMR legato a RMR prendo gestore e impianto dalla RMR e non sono modificabili
    this.checkPrevConsRMR(prevConsRMR);

    // in visualizzazione e modifica nessun campo in testata è modificabile
    this.checkInserimento();

    // se sono in visualizzazione devo vedere anche la colonna protocollo
    if (
      this.mr.prevCons?.statoDichiarazione?.idStatoDichiarazione ==
        STATO_PREV_CONS.INVIATA ||
      this.isProfileBO
    ) {
      this.form.addControl(
        'numProtocollo',
        new TextInput({
          label: 'DICHIARAZIONI.CREATE.FORM.PROTOCOLLO.LABEL',
          placeholder: ' ',
          type: 'text',
          size,
          clearable: true,
          value: this.mr.prevCons?.numProtocollo ?? null,
          readonly: true
        })
      );
    }

    this.comboLocalImpianti = this.mrService.getComboDichiarazioneImpianti();
    (this.form.get('idImpianto') as AutocompleteInput).setOptions(
      this.comboLocalImpianti as any
    );

    return this.form;
  }

  checkPrevConsRMR(prevConsRMR) {
    if (prevConsRMR) {
      this.form.get('idGestorePrevCons').disable({ emitEvent: false });
      this.form.get('idImpianto').disable({ emitEvent: false });
      this.disabledForm = false;
    }
  }
  checkInserimento() {
    if (!this.isInserimento) {
      this.form.get('idGestorePrevCons').disable({ emitEvent: false });
      this.form.get('idImpianto').disable({ emitEvent: false });
      this.form.get('annoTributo').disable({ emitEvent: false });
      this.form.get('dataDoc').disable({ emitEvent: false });
    }
  }
  /**
   * @description Disabilita i campi del form di ricerca
   */
  private _setOnVerificaPresenzaMr(v: boolean = true) {
    this.onVerificaPresenzaMr = v;
    if (v) {
      this.form.get('idGestorePrevCons').disable({ emitEvent: false });
      this.form.get('idImpianto').disable({ emitEvent: false });
      this.form.get('annoTributo').disable();
      this.form.get('dataDoc').disable();
      this.showNewPrevConsBtn = this.mr.showNewPrevConsBtn(v);
      this.showTabs = this.mr.showTabs(v);
      this.showDraftCreateBtn = this.mr.showDraftCreateBtn(v);
    }
  }

  private _preventDataLose(content: any) {
    const dialog = this.modalService.openDialog(ConfirmMrExitComponent, {
      sizeModal: 'xs',
      header: content.titoloMsg,
      showCloseButton: true,
      context: { messageConfirm: content }
    });
    return dialog;
  }

  /**
   * @description modifica l'oggetto da inviare al BE priva di un salvataggio bozza/invio
   */
  private _parsePrevConsSave(
    prevCons: IPrevCons & { idCount: number }
  ): IPrevCons {
    delete prevCons.idPrevCons,
      delete prevCons.prevConsRichiesta,
      delete prevCons.idCount,
      delete prevCons.impianto,
      // serve a BE per sapere se sto aggiungendo una dichiarazione o una richiesta
      (prevCons.tipoDoc = {
        idTipoDoc: this.mr.idTipoDoc
      });

    prevCons.prevConsLinee = prevCons.prevConsLinee?.map(
      ({
        //elimino questi valori, mi torneranno valorizzati nella risposta del BE
        //elimino solo gli idPrevConsLinee < 0, perchè sono quelli che creo in locale
        percRecupero,
        percScarto,
        totMat,
        totRii,
        totRru,
        totRu,
        idPrevConsLinee,
        descLinea,
        codLinea,
        codSottoLinea,
        isLegameRmr,
        dettRii = [],
        dettMat = [],
        dettRru = [],
        dettRu = [],
        ...response
      }) => {
        response.prevConsDett = [...dettRii, ...dettMat, ...dettRru, ...dettRu];
        if (idPrevConsLinee > 0) {
          response['idPrevConsLinee'] = idPrevConsLinee;
        }
        return {
          ...response,
          prevConsDett: response.prevConsDett?.map(
            //elimino gli idPrevConsDett assegnati da FE, la quantità richiesta e la proprietà isRam che serve solo in memoria locale
            ({ idPrevConsDett, isRam, quantitaRichiesta, ...item }) =>
              !!isRam ? { ...item } : { ...item, idPrevConsDett }
          )
        };
      }
    );
    return prevCons;
  }

  /**
   * @description chiama il servizio per l'invio della prevCons + naviga alla pagina principale
   */
  private _confirmCreate() {
    this.loadingService.show();
    const prevCons = this._parsePrevConsSave({
      ...this.mr.prevCons
    });

    this.mrService
      .insertDichiarazioneMr(prevCons, {
        idImpianto: this.form.get('idImpianto').value,
        idGestore: this.form.get('idGestorePrevCons').value
      })
      .pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response) => {
        this.notification.info({
          title: response.message.titoloMsg,
          text: response.message.testoMsg
        });
        this.loadingService.hide();
        this.router.navigate([this.mr.path]);
      });
  }

  private _createClassFactory(res: Data): PrevConsEditAbstract {
    switch (res.page) {
      case 'richiesta-mr/inserimento':
        this.isInserimento = true;
        return new RichiestaInserimento(this.mrService, this.storeService);
      case 'dichiarazioni-mr/inserimento':
        this.isInserimento = true;
        return new DichiarazioneInserimento(this.mrService, this.storeService);
      case 'dichiarazioni-mr/inserimento/id':
        this.isInserimento = true;
        //questo paramentro mi arriva SOLO se sto inserendo una D-MR a partire da una R-MR
        this.prevConsRMR = res['richiestaToDichiarazione'];
        let dmrJoinTormr = new DichiarazioneInserimento(
          this.mrService,
          this.storeService
        );
        dmrJoinTormr.prevCons = dmrJoinTormr.createPrevCons();
        dmrJoinTormr.prevCons.setDichiarazioneFromRichiesta(this.prevConsRMR);
        return dmrJoinTormr;
      case 'dichiarazioni-mr/edit':
        this.isInserimento = false;
        let dichiarazione = new DichiarazioneModifica(
          this.mrService,
          this.storeService
        );
        dichiarazione.prevCons = dichiarazione.createPrevCons();
        dichiarazione.prevCons.setValueFromPrevCons(res['prevCons']);
        this.impianto = dichiarazione.prevCons.impianto;
        this.gestore = dichiarazione.prevCons.impianto.gestore;
        return dichiarazione;
      case 'richiesta-mr/edit':
        this.isInserimento = false;
        let richiesta = new RichiestaModifica(
          this.mrService,
          this.storeService
        );
        richiesta.prevCons = richiesta.createPrevCons();
        richiesta.prevCons.setValueFromPrevCons(res['prevCons']);
        this.impianto = richiesta.prevCons.impianto;
        this.gestore = richiesta.prevCons.impianto.gestore;
        return richiesta;
    }
  }

  /**
   * @description setto l'editMode in base al caso d'uso
   */
  private _setIsEditMode(): void {
    //impostazioni SOLO in inserimento
    if (
      this.isInserimento &&
      this.mr.profiloACL.content.insert &&
      !this.isProfileBO
    ) {
      this.mr.isEditMode = true;
      return;
    }
    //impostazioni di modifica/visualizzazione
    if (
      this.mr.prevCons?.statoDichiarazione.idStatoDichiarazione ==
        STATO_PREV_CONS.BOZZA &&
      this.mr.profiloACL.content.update &&
      !this.isProfileBO
    ) {
      this.mr.isEditMode = true;
    } else {
      this.mr.isEditMode = false;
    }
  }
}
