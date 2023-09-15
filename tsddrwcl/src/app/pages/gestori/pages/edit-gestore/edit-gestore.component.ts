import { Component, Input, OnInit } from '@angular/core';
import { FunzionalitaProfiliACL } from '@pages/configurazione-profili/models/acl.model';
import {
  AutocompleteInput,
  DateInput,
  Divider,
  Form,
  SelectOption,
  TextInput,
  ValidationStatus
} from '@shared/form';
import { GestoriService } from '@pages/gestori/services/gestori.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NotificationService } from '@shared/notification/notification.service';
import { ModalService } from '@shared/modal/modal.service';
import { I18nService } from '@eng-ds/translate';
import { DatePipe } from '@angular/common';
import { LoadingService } from '@theme/layouts/loading.services';
import { UtilityService } from '@core/services';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { csiCatchError } from '@core/operators/catch-error.operator';
import { ConfirmExitComponent } from '@pages/utenti/components/confirm-exit/confirm-exit.component';
import { forkJoin, of } from 'rxjs';
import { Gestore } from '@pages/gestori/models/gestore.model';
import { take, tap } from 'rxjs/operators';
import { ConfirmEditComponent } from '@pages/gestori/components/confirm-edit/confirm-edit.component';

@UntilDestroy()
@Component({
  selector: 'app-edit-gestore',
  templateUrl: './edit-gestore.component.html'
})
export class EditGestoreComponent implements OnInit {
  @Input() gestore: Gestore;
  acl: FunzionalitaProfiliACL;
  helperTitle: string = 'GESTORI.CREATE.HELPER';
  isEditing = false;

  currentGestore: Gestore;
  // Form declaration
  form: Form;
  formSedeLegale: Form;
  formContatti: Form;
  formLegaleRappresentante: Form;
  formValidita: Form;
  mandatoryMessage: any;
  messageIdDataTesto: any;
  comboProfili: any;
  comuni: SelectOption[];
  isReadonly:boolean = false;
  isEmbed:boolean = false;

  constructor(
    private service: GestoriService,
    private router: Router,
    private route: ActivatedRoute,
    private notification: NotificationService,
    private modalService: ModalService,
    private i18n: I18nService,
    private datePipe: DatePipe,
    private loadingService: LoadingService,
    private utility: UtilityService
  ) {}

  ngOnInit(): void {
    this._initHelper();
    this._initGestore();
    this._initACL();
  }

  onUpdate() {
    this.utility.getMessage('A012').subscribe((result: any) => {
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
    this.service
      .editGestore(this.createDTO())
      .pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response) => {
        this.notification.info({
          title: response.message.titoloMsg,
          text: response.message.testoMsg
        });
        this.setIsEditing(false);
        this.loadingService.hide();
        this.router.navigate(['gestori']);
      });
  }

  createDTO() {
    const anagrafica = this.form.value;
    const sedeLegale = this.formSedeLegale.value;
    const contatti = this.formContatti.value;
    const rappresentante = this.formLegaleRappresentante.value;
    const nome = this.formLegaleRappresentante.get('nome').value;
    const cognome = this.formLegaleRappresentante.get('cognome').value;
    const codFiscale = this.formLegaleRappresentante.get('codFiscale').value;
    const qualifica = this.formLegaleRappresentante.get('qualifica').value;
    const dataValidita = this.formValidita.value;
    delete anagrafica.divider;
    delete sedeLegale.divider1;
    delete sedeLegale.divider2;
    delete contatti.divider;
    delete rappresentante.divider;
    delete anagrafica.dataInizioValidita;
    delete anagrafica.dataFineValidita;
    this.loadingService.show();
    const gestore = {
      ...this.currentGestore,
      ...anagrafica,
      ...contatti,
      ...dataValidita
    };
    gestore.naturaGiuridica = { idNaturaGiuridica: gestore.idNaturaGiuridica };
    delete gestore.idNaturaGiuridica;

    gestore.sedeLegale = {};

    if (sedeLegale.idNazione) {
      gestore.sedeLegale = { nazione: { idNazione: sedeLegale.idNazione } };
    } else {
      gestore.sedeLegale = {
        ...this.currentGestore.sedeLegale,
        indirizzo: sedeLegale.indirizzo,
        cap: sedeLegale.cap
      };
      delete gestore.sedeLegale.nazione;
      gestore.sedeLegale.sedime = { idSedime: sedeLegale.idSedime };
      gestore.sedeLegale.comune = {
        idComune: sedeLegale.idComune,
        provincia: { idProvincia: sedeLegale.idProvincia }
      };
    }

    const datiSogg=(this.currentGestore.legaleRappresentante)?this.currentGestore.legaleRappresentante.datiSogg:{};


    gestore.legaleRappresentante = {
      ...this.currentGestore.legaleRappresentante,
      datiSogg: {
        ...datiSogg,
        nome,
        cognome,
        codFiscale,
      },
      qualifica: qualifica
    };
    return gestore;
  }

  onBack() {
    if (this.isEditing) {
      this.utility.getMessage('A001').subscribe((result: any) => {
        const { content } = result;
        if (content) {
          const dialog = this._preventDataLose(content);

          dialog.closed
            .pipe(untilDestroyed(this))
            .subscribe(() => this.router.navigate(['gestori', 'lista']));
        }
      });
      return;
    }

    this.router.navigate(['gestori', 'lista']);
  }

  setIsEditing(value) {
    this.isEditing = value;
  }

  private _initReadonly() {
    if(this.isEmbed || !this.acl.content.update || this.currentGestore.dataFineValidita){
      this.isReadonly = true;
    }else{
      this.isReadonly = false;
    }
  }

  private _initGestore() {
    // caso in cui viene embeddato il gestore in sola lettura
    if(this.gestore){
      this.currentGestore = this.gestore;
      this.isEmbed = true;
      this._initReadonly();
      // per non avere problemi nel form in sola lettura
      this.mandatoryMessage = {
        content:{
          text: 'Campo obbligatorio'
        }
      }
      this._initForm(this.currentGestore);
      this.loadingService.hide();
    }
    // caso in cui si visualizza il routing del gestore
    this.route.data.pipe(untilDestroyed(this)).subscribe((data) => {
      // procedo qui solo se sono passato dal resolver
      if(data && data['gestore']){
        forkJoin([
          this.utility.getMessage('E010'),
          this.utility.getMessage('E002')
        ])
          .pipe(untilDestroyed(this))
          .subscribe((results) => {
            this.mandatoryMessage = results[0];
            this.messageIdDataTesto = (results[1] as any).content
              .testoMsg as string;
            
            this.currentGestore = data['gestore'].content;
            
            this._initReadonly();
            this._initForm(this.currentGestore);
            this.loadingService.hide();
          });
      }
    });
  }

  private _initHelper(): void {
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

  private _initForm(gestore: Gestore) {
    const size = '12|6|6|6|6';
    const sizeSm = '12|12|4|4|4';
    const sizeXs = '12|12|3|3|3';
    this.form = new Form({
      header: { show: false },
      asyncValidator: [this.service.cfValidator()],
      filter: false,
      controls: {
        codFiscPartiva: new TextInput({
          forceFocus: true,
          label: 'GESTORI.CREATE.FORM.CF.LABEL',
          placeholder: !this.isReadonly ? 'GESTORI.CREATE.FORM.CF.PLACEHOLDER' : ' ',
          type: 'text',
          readonly: this.isReadonly,
          size,
          value: gestore.codFiscPartiva,
          required: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            }),
            ValidationStatus.ERROR.CUSTOM(
              (control) => control.touched && control.parent?.hasError('cf'),
              {
                text: ''
              }
            )
          ],
          clearable: true,
          validatorOrOpts: { updateOn: 'blur' }
        }),
        divider: new Divider(),
        ragSociale: new TextInput({
          label: 'GESTORI.CREATE.FORM.RAGIONE_SOCIALE.LABEL',
          placeholder: !this.isReadonly ? 'GESTORI.CREATE.FORM.RAGIONE_SOCIALE.PLACEHOLDER' : ' ',
          type: 'text',
          value: gestore.ragSociale,
          readonly: this.isReadonly,
          size,
          required: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ],
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        idNaturaGiuridica: new AutocompleteInput({
          label: 'GESTORI.RICERCA.FORM.NATURA_GIURIDICA.LABEL',
          placeholder: !this.isReadonly ? 'GESTORI.RICERCA.FORM.NATURA_GIURIDICA.PLACEHOLDER' : ' ',
          options: this.service.getComboNaturaGiuridica() as any,
          size,
          value: gestore.naturaGiuridica?.idNaturaGiuridica,
          compareWith: (item, selected) => +item.id === selected,
          required: true,
          readonly: this.isReadonly,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ],
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        })
      }
    });

    this.formSedeLegale = new Form({
      header: { show: false },
      asyncValidator: [],
      validatorOrOpts: [
        this.service.formSedeLegaleValidator(),
      ],
      filter: false,
      controls: {
        idNazione: new AutocompleteInput({
          label: 'GESTORI.CREATE.FORM.NAZIONE.LABEL',
          placeholder: !this.isReadonly ? 'GESTORI.CREATE.FORM.NAZIONE.PLACEHOLDER': ' ',
          options: this.service.getComboNazioni(),
          compareWith: (item, selected) => +item.id === selected,
          size: '12|12|3|3|3',
          readonly: this.isReadonly,
          value: (!gestore.sedeLegale.sedime?.idSedime)?gestore.sedeLegale.nazione?.idNazione:null,
          // required: true,
          valueChange: (value) => {
            const comune = this.formSedeLegale.get(
              'idComune'
            ) as AutocompleteInput;

            const idSedime = this.formSedeLegale.get(
              'idSedime'
            ) as AutocompleteInput;

            const indirizzo = this.formSedeLegale.get(
              'indirizzo'
            ) as AutocompleteInput;

            const idProvincia = this.formSedeLegale.get(
              'idProvincia'
            ) as AutocompleteInput;

            const cap = this.formSedeLegale.get('cap') as AutocompleteInput;

            comune.reset();
            idSedime.reset();
            indirizzo.reset();
            idProvincia.reset();
            cap.reset();

            if (value) {
              comune.disable();
              idSedime.disable();
              indirizzo.disable();
              idProvincia.disable();
              cap.disable();
            } else {
              comune.enable();
              idSedime.enable();
              indirizzo.enable();
              idProvincia.enable();
              cap.enable();
            }            
          },
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ],
          clearable: true,
          validatorOrOpts: { updateOn: 'blur' }
        }),
        divider1: new Divider(),
        idSedime: new AutocompleteInput({
          value: gestore.sedeLegale.sedime?.idSedime as any,
          label: 'GESTORI.CREATE.FORM.SEDIME.LABEL',
          readonly: this.isReadonly,
          placeholder: !this.isReadonly ? 'GESTORI.CREATE.FORM.SEDIME.PLACEHOLDER' : ' ',
          options: this.service.getComboSedime(),
          compareWith: (item, selected) => +item.id === selected,
          size: '12|12|3|3|3',
          // required: true,
          validationStatus: [
            ValidationStatus.ERROR.CUSTOM(
              (control) => control.touched && control.parent?.hasError('formSedeLegaleSedime'),
              {
                text: this.mandatoryMessage.content.testoMsg
              }
            )
          ],
          clearable: true,
          validatorOrOpts: { updateOn: 'blur' }
        }),
        divider2: new Divider(),
        indirizzo: new TextInput({
          value: gestore.sedeLegale.indirizzo,
          label: 'GESTORI.CREATE.FORM.INDIRIZZO.LABEL',
          readonly: this.isReadonly,
          placeholder: !this.isReadonly ? 'GESTORI.CREATE.FORM.INDIRIZZO.PLACEHOLDER' : ' ',
          type: 'text',
          size: '12|12|4|4|4',
          // required: true,
          validationStatus: [
            ValidationStatus.ERROR.CUSTOM(
              (control) => control.touched && control.parent?.hasError('formSedeLegaleIndirizzo'),
              {
                text: this.mandatoryMessage.content.testoMsg
              }
            )
          ],
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        idProvincia: new AutocompleteInput({
          value: gestore.sedeLegale.comune?.provincia?.idProvincia,
          label: 'GESTORI.RICERCA.FORM.PROVINCIA.LABEL',
          readonly: this.isReadonly,
          placeholder: !this.isReadonly ? 'GESTORI.RICERCA.FORM.PROVINCIA.PLACEHOLDER' : ' ',
          options: this.service.getComboProvincia() as any,
          compareWith: (item, selected) => +item.id === selected,
          valueChange: (value) => {
            const comune = this.formSedeLegale.get(
              'idComune'
            ) as AutocompleteInput;
            if (value) {
              comune.setValue(null);
              comune.enable();
              comune.setOptions(
                this.service.getComboComune(value + '').pipe(
                  tap((response: any) => {
                    this.comuni = response.content;
                  })
                )
              );
            }else{
              comune.disable()
              comune.reset();
              comune.setOptions(
                of([])
              );
            }
          },
          size: '12|12|3|3|3',
          // required: true,
          validationStatus: [
            ValidationStatus.ERROR.CUSTOM(
              (control) => control.touched && control.parent?.hasError('formSedeLegaleProvincia'),
              {
                text: this.mandatoryMessage.content.testoMsg
              }
            )
          ],
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        idComune: new AutocompleteInput({
          value: gestore.sedeLegale.comune?.idComune,
          label: 'GESTORI.CREATE.FORM.COMUNE.LABEL',
          readonly: this.isReadonly,
          placeholder: !this.isReadonly ? 'GESTORI.CREATE.FORM.COMUNE.PLACEHOLDER' : ' ',
          options: this.service
            .getComboComune(
              gestore.sedeLegale.comune?.provincia?.idProvincia + ''
            )
            .pipe(
              tap((response: any) => {
                this.comuni = response.content;
              })
            ),
          compareWith: (item, selected) => +item.id === selected,
          size: '12|12|3|3|3',

          valueChange: (value) => {
            if (value && this.comuni) {
              const selected = this.comuni.find((i) => +i.id === +value);
              const cap = this.formSedeLegale.get('cap') as TextInput;
              cap.setValue((selected as any).additionalValue);
            }
          },
          // required: true,
          validatorOrOpts: { updateOn: 'blur' },
          validationStatus: [
            ValidationStatus.ERROR.CUSTOM(
              (control) => control.touched && control.parent?.hasError('formSedeLegaleComune'),
              {
                text: this.mandatoryMessage.content.testoMsg
              }
            )
          ],
          clearable: true
        }),
        cap: new TextInput({
          value: gestore.sedeLegale.cap,
          label: 'GESTORI.CREATE.FORM.CAP.LABEL',
          readonly: this.isReadonly,
          placeholder: !this.isReadonly ? 'GESTORI.CREATE.FORM.CAP.PLACEHOLDER' : ' ',
          type: 'text',
          size: '12|12|2|2|2',
          validatorOrOpts: { updateOn: 'blur' },
          validationStatus: [
            ValidationStatus.ERROR.CUSTOM(
              (control) => control.touched && control.parent?.hasError('formSedeLegaleCap'),
              {
                text: this.mandatoryMessage.content.testoMsg
              }
            )
          ],
          clearable: true
        })
      }
    });

    const comune = this.formSedeLegale.get(
      'idComune'
    ) as AutocompleteInput;

    const idSedime = this.formSedeLegale.get(
      'idSedime'
    ) as AutocompleteInput;

    const indirizzo = this.formSedeLegale.get(
      'indirizzo'
    ) as AutocompleteInput;

    const idProvincia = this.formSedeLegale.get(
      'idProvincia'
    ) as AutocompleteInput;
    const cap = this.formSedeLegale.get('cap') as AutocompleteInput;

     /*  in base alla risposta del BE abilito o disabilito i campi*/
    if(gestore.sedeLegale.sedime?.idSedime){
      if(!this.isReadonly){
        comune.enable();
        idSedime.enable();
        indirizzo.enable();
        idProvincia.enable();
        cap.enable();
      }
      if(gestore.sedeLegale.comune.provincia.idProvincia){
        comune.setOptions(
          this.service
            .getComboComune(
              gestore.sedeLegale.comune?.provincia?.idProvincia + ''
            )
            .pipe(untilDestroyed(this))
            .pipe(
              tap((response: any) => {
                this.comuni = response.content;
                comune.setValue(gestore.sedeLegale.comune?.idComune);
                cap.setValue(gestore.sedeLegale.cap);
              })
            )
        )
      }
    }else{
      comune.disable();
      idSedime.disable();
      indirizzo.disable();
      idProvincia.disable();
      cap.disable();
    }
   


    this.formContatti = new Form({
      header: { show: false },
      asyncValidator: [
        this.service.emailValidator()
        // this.service.pecValidator()
      ],
      filter: false,
      controls: {
        telefono: new TextInput({
          value: gestore.telefono,
          readonly: this.isReadonly,
          label: 'GESTORI.CREATE.FORM.TELEFONO.LABEL',
          placeholder: !this.isReadonly ? 'GESTORI.CREATE.FORM.TELEFONO.PLACEHOLDER' : ' ',
          type: 'text',
          size: sizeSm,
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        email: new TextInput({
          value: gestore.email,
          readonly: this.isReadonly,
          label: 'GESTORI.CREATE.FORM.MAIL.LABEL',
          placeholder: !this.isReadonly ? 'GESTORI.CREATE.FORM.MAIL.PLACEHOLDER' : ' ',
          type: 'text',
          required: true,

          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            }),
            ValidationStatus.ERROR.CUSTOM(
              (control) => control.touched && control.parent?.hasError('email'),
              {
                text: ''
              }
            )
          ],
          size: sizeSm,
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        pec: new TextInput({
          value: gestore.pec,
          readonly: this.isReadonly,
          label: 'GESTORI.CREATE.FORM.PEC.LABEL',
          placeholder: !this.isReadonly ? 'GESTORI.CREATE.FORM.PEC.PLACEHOLDER' : ' ',
          type: 'text',
          required: true,

          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            }),
            ValidationStatus.ERROR.CUSTOM(
              (control) => control.touched && control.parent?.hasError('pec'),
              {
                text: ''
              }
            )
          ],
          size: sizeSm,
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        })
      }
    });

    this.formLegaleRappresentante = new Form({
      header: { show: false },
      asyncValidator: [this.service.cfValidator()],
      filter: false,
      controls: {
        codFiscale: new TextInput({
          readonly: this.isReadonly,
          value: gestore.legaleRappresentante?.datiSogg?.codFiscale,
          label: 'GESTORI.CREATE.FORM.CF.LABEL',
          placeholder: !this.isReadonly ? 'GESTORI.CREATE.FORM.CF.PLACEHOLDER' : ' ',
          type: 'text',
          size: sizeXs,
          required: true,
          valueChange: (value: string) => {
            const cognome = this.formLegaleRappresentante.get('cognome');
            const nome = this.formLegaleRappresentante.get('nome');
            if (value.length === 16) {
              this.service
                .getRappresentanteLegale(value)
                .pipe(take(1))
                .subscribe((rappresentante) => {
                  if (rappresentante.content) {
                    cognome.setValue(rappresentante.content.cognome);
                    nome.setValue(rappresentante.content.nome);
                    cognome.disable();
                    nome.disable();
                  }
                });
            } else {
              cognome.reset();
              nome.reset();
              cognome.enable();
              nome.enable();
            }
          },
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            }),
            ValidationStatus.ERROR.CUSTOM(
              (control) => control.touched && control.parent?.hasError('cf'),
              {
                text: ''
              }
            )
          ],
          clearable: true,
          validatorOrOpts: { updateOn: 'blur' }
        }),
        cognome: new TextInput({
          readonly: this.isReadonly,
          value: gestore.legaleRappresentante?.datiSogg?.cognome,
          label: 'GESTORI.CREATE.FORM.COGNOME.LABEL',
          placeholder: !this.isReadonly ? 'GESTORI.CREATE.FORM.COGNOME.PLACEHOLDER' : ' ',
          type: 'text',
          size: sizeXs,
          required: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ],
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        nome: new TextInput({
          readonly: this.isReadonly,
          value: gestore.legaleRappresentante?.datiSogg?.nome,
          label: 'GESTORI.CREATE.FORM.NOME.LABEL',
          placeholder: !this.isReadonly ? 'GESTORI.CREATE.FORM.NOME.PLACEHOLDER' : ' ',
          type: 'text',
          size: sizeXs,
          required: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ],
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        qualifica: new TextInput({
           label: 'GESTORI.CREATE.FORM.QUALIFICA.LABEL',
           placeholder: !this.isReadonly ? 'GESTORI.CREATE.FORM.QUALIFICA.PLACEHOLDER' : ' ',
           type: 'text',
           size: sizeXs,
           required: true,
           validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
           ],
           readonly: this.isReadonly,
           value: gestore.legaleRappresentante?.qualifica,
           validatorOrOpts: { updateOn: 'blur' },
           clearable: true
        })
      }
    });

    this.formValidita = new Form({
      header: { show: false },
      validatorOrOpts: [this.service.dateValidator()],
      filter: false,
      controls: {
        dataInizioValidita: new DateInput({
          readonly: this.isReadonly,
          label: 'GESTORI.CREATE.FORM.INIZIO.LABEL',
          placeholder: !this.isReadonly ? 'GESTORI.CREATE.FORM.INIZIO.PLACEHOLDER' : ' ',
          required: true,
          value: gestore.dataInizioValidita
            ? this.datePipe.transform(gestore.dataInizioValidita, 'yyyy-MM-dd')
            : this.datePipe.transform(new Date(), 'yyyy-MM-dd'),
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ],
          size,
          clearable: true
        }),
        dataFineValidita: new DateInput({
          readonly: this.isReadonly,
          label: 'GESTORI.CREATE.FORM.FINE.LABEL',
          placeholder: !this.isReadonly ? 'GESTORI.CREATE.FORM.FINE.PLACEHOLDER' : ' ',
          size,
          value: gestore.dataFineValidita
            ? this.datePipe.transform(gestore.dataFineValidita, 'yyyy-MM-dd')
            : '',
          clearable: true,
          validationStatus: [
            ValidationStatus.ERROR.CUSTOM(
              (control) =>
                control.touched && control.parent?.hasError('dataFineValidita'),
              {
                text: this.messageIdDataTesto
              }
            )
          ]
        })
      }
    });

    this.form.valueChanges
      .pipe(untilDestroyed(this))
      .subscribe(() => this.setIsEditing(true));
    this.formValidita.valueChanges
      .pipe(untilDestroyed(this))
      .subscribe(() => this.setIsEditing(true));
    this.formContatti.valueChanges
      .pipe(untilDestroyed(this))
      .subscribe(() => this.setIsEditing(true));
    this.formSedeLegale.valueChanges
      .pipe(untilDestroyed(this))
      .subscribe(() => this.setIsEditing(true));
    this.formLegaleRappresentante.valueChanges
      .pipe(untilDestroyed(this))
      .subscribe(() => this.setIsEditing(true));
  }
}
