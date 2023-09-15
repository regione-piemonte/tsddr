import { Component, OnInit } from '@angular/core';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
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
import { Router } from '@angular/router';
import { NotificationService } from '@shared/notification/notification.service';
import { ModalService } from '@shared/modal/modal.service';
import { I18nService } from '@eng-ds/translate';
import { LoadingService } from '@theme/layouts/loading.services';
import { UtilityService } from '@core/services';
import { csiCatchError } from '@core/operators/catch-error.operator';
import { GestoriService } from '@pages/gestori/services/gestori.service';
import { forkJoin, of } from 'rxjs';
import { DatePipe } from '@angular/common';
import { ConfirmExitComponent } from '@pages/gestori/components/confirm-exit/confirm-exit.component';
import { take, tap } from 'rxjs/operators';

@UntilDestroy()
@Component({
  selector: 'app-inserimento',
  templateUrl: './inserimento.component.html',
  styleUrls: ['./inserimento.component.scss']
})
export class InserimentoComponent implements OnInit {
  acl: FunzionalitaProfiliACL;
  helperTitle: string = 'GESTORI.CREATE.HELPER';
  isEditing = false;
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

  constructor(
    private service: GestoriService,
    private router: Router,
    private notification: NotificationService,
    private modalService: ModalService,
    private i18n: I18nService,
    private datePipe: DatePipe,
    private loadingService: LoadingService,
    private utility: UtilityService
  ) {}

  ngOnInit(): void {
    this._initHelper();
    forkJoin([
      this.utility.getMessage('E010').pipe(untilDestroyed(this)),
      this.utility.getMessage('E002').pipe(untilDestroyed(this))
    ]).subscribe((results) => {
      this.mandatoryMessage = results[0];
      this.messageIdDataTesto = (results[1] as any).content.testoMsg as string;
      this._initForm();
      this.loadingService.hide();
    });

    this._initACL();
  }

  onCreate() {
    this.service
      .createGestore(this.createDTO())
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
    const rappresentante = this.formLegaleRappresentante;
    const nome = this.formLegaleRappresentante.get('nome').value;
    const cognome = this.formLegaleRappresentante.get('cognome').value;
    const codFiscale = this.formLegaleRappresentante.get('codFiscale').value;
    const qualifica = this.formLegaleRappresentante.get('qualifica').value;
    const dataValidita = this.formValidita.value;
    delete anagrafica.divider;
    delete sedeLegale.divider1;
    delete sedeLegale.divider2;
    delete contatti.divider;
    delete anagrafica.dataInizioValidita;
    delete anagrafica.dataFineValidita;
    this.loadingService.show();
    const gestore = { ...anagrafica, ...contatti, ...dataValidita };
    gestore.naturaGiuridica = { idNaturaGiuridica: gestore.idNaturaGiuridica };
    delete gestore.idNaturaGiuridica;
    gestore.sedeLegale = {};

    if (sedeLegale.idNazione) {
      gestore.sedeLegale = { nazione: { idNazione: sedeLegale.idNazione } };
    } else {
      gestore.sedeLegale = {
        indirizzo: sedeLegale.indirizzo,
        cap: sedeLegale.cap,
        sedime: { idSedime: sedeLegale.idSedime },
        comune: {
          idComune: sedeLegale.idComune,
          provincia: { idProvincia: sedeLegale.idProvincia }
        }
      };
    }

    gestore.legaleRappresentante = {
      datiSogg: { nome, cognome, codFiscale },
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
            .subscribe(() => this.router.navigate(['gestori']));
        }
      });
      return;
    }

    this.router.navigate(['gestori']);
  }

  setIsEditing(value) {
    this.isEditing = value;
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
    const sizeSm = '12|12|4|4|4';
    const sizeXs = '12|12|3|3|3';
    this.form = new Form({
      header: { show: false },
      asyncValidator: [
        this.service.cfValidator(),
        this.service.existValidator()
      ],
      filter: false,
      controls: {
        codFiscPartiva: new TextInput({
          forceFocus: true,
          label: 'GESTORI.CREATE.FORM.CF.LABEL',
          placeholder: 'GESTORI.CREATE.FORM.CF.PLACEHOLDER',
          type: 'text',
          size,
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
          placeholder: 'GESTORI.CREATE.FORM.RAGIONE_SOCIALE.PLACEHOLDER',
          type: 'text',
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
          placeholder: 'GESTORI.RICERCA.FORM.NATURA_GIURIDICA.PLACEHOLDER',
          options: this.service.getComboNaturaGiuridica() as any,
          size,
          required: true,
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
          placeholder: 'GESTORI.CREATE.FORM.NAZIONE.PLACEHOLDER',
          options: this.service.getComboNazioni(),
          size: '12|12|3|3|3',
          // required: true,
          valueChange: (value) => {
            const idNazione = this.formSedeLegale.get(
              'idNazione'
            ) as AutocompleteInput;

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
           
            if (value) {
              comune.reset();
              idSedime.reset();
              indirizzo.reset();
              idProvincia.reset();
              cap.reset();
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
          clearable: true,
          validatorOrOpts: { updateOn: 'blur' }
        }),
        divider1: new Divider(),
        idSedime: new AutocompleteInput({
          label: 'GESTORI.CREATE.FORM.SEDIME.LABEL',
          placeholder: 'GESTORI.CREATE.FORM.SEDIME.PLACEHOLDER',
          options: this.service.getComboSedime(),
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
          label: 'GESTORI.CREATE.FORM.INDIRIZZO.LABEL',
          placeholder: 'GESTORI.CREATE.FORM.INDIRIZZO.PLACEHOLDER',
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
          label: 'GESTORI.RICERCA.FORM.PROVINCIA.LABEL',
          placeholder: 'GESTORI.RICERCA.FORM.PROVINCIA.PLACEHOLDER',
          options: this.service.getComboProvincia() as any,
          valueChange: (value) => {
            const comune = this.formSedeLegale.get(
              'idComune'
            ) as AutocompleteInput;
            if (value) {
             
              comune.enable();
              comune.reset();
              comune.setOptions(
                this.service.getComboComune(value).pipe(
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
          label: 'GESTORI.CREATE.FORM.COMUNE.LABEL',
          placeholder: 'GESTORI.CREATE.FORM.COMUNE.PLACEHOLDER',
          options: of([]),
          size: '12|12|3|3|3',
          valueChange: (value) => {
            if (value) {
              const selected = this.comuni.find((i) => i.id === value);
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
          label: 'GESTORI.CREATE.FORM.CAP.LABEL',
          placeholder: 'GESTORI.CREATE.FORM.CAP.PLACEHOLDER',
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
    
    const idComune = this.formSedeLegale.get('idComune') as AutocompleteInput;
    idComune.disable();

    this.formContatti = new Form({
      header: { show: false },
      asyncValidator: [
        this.service.emailValidator()
        // this.service.pecValidator()
      ],
      filter: false,
      controls: {
        telefono: new TextInput({
          label: 'GESTORI.CREATE.FORM.TELEFONO.LABEL',
          placeholder: 'GESTORI.CREATE.FORM.TELEFONO.PLACEHOLDER',
          type: 'text',
          size: sizeSm,
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        email: new TextInput({
          label: 'GESTORI.CREATE.FORM.MAIL.LABEL',
          placeholder: 'GESTORI.CREATE.FORM.MAIL.PLACEHOLDER',
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
          label: 'GESTORI.CREATE.FORM.PEC.LABEL',
          placeholder: 'GESTORI.CREATE.FORM.PEC.PLACEHOLDER',
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
          label: 'GESTORI.CREATE.FORM.CF.LABEL',
          placeholder: 'GESTORI.CREATE.FORM.CF.PLACEHOLDER',
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
                  cognome.setValue(rappresentante.content.cognome);
                  nome.setValue(rappresentante.content.nome);
                  cognome.disable();
                  nome.disable();
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
          label: 'GESTORI.CREATE.FORM.COGNOME.LABEL',
          placeholder: 'GESTORI.CREATE.FORM.COGNOME.PLACEHOLDER',
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
          label: 'GESTORI.CREATE.FORM.NOME.LABEL',
          placeholder: 'GESTORI.CREATE.FORM.NOME.PLACEHOLDER',
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
           placeholder: 'GESTORI.CREATE.FORM.QUALIFICA.PLACEHOLDER',
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
        })
      }
    });

    this.formValidita = new Form({
      header: { show: false },
      validatorOrOpts: [this.service.dateValidator()],
      filter: false,
      controls: {
        dataInizioValidita: new DateInput({
          label: 'GESTORI.CREATE.FORM.INIZIO.LABEL',
          placeholder: 'GESTORI.CREATE.FORM.INIZIO.PLACEHOLDER',
          required: true,
          value: this.datePipe.transform(new Date(), 'yyyy-MM-dd'),
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ],
          size,
          clearable: true
        }),
        dataFineValidita: new DateInput({
          label: 'GESTORI.CREATE.FORM.FINE.LABEL',
          placeholder: 'GESTORI.CREATE.FORM.FINE.PLACEHOLDER',
          size,
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
