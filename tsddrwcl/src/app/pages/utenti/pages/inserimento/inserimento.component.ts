import { Component, OnInit } from '@angular/core';
import { FunzionalitaProfiliACL } from '@pages/configurazione-profili/models/acl.model';
import {
  AutocompleteInput,
  Divider,
  Form,
  TextInput,
  ValidationStatus
} from '@shared/form';
import { UtentiService } from '@pages/utenti/services/utenti.service';
import { Router } from '@angular/router';
import { NotificationService } from '@shared/notification/notification.service';
import { ModalService } from '@shared/modal/modal.service';
import { I18nService } from '@eng-ds/translate';
import { LoadingService } from '@theme/layouts/loading.services';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { csiCatchError } from '@core/operators/catch-error.operator';
import { UtilityService } from '@core/services';
import { tap } from 'rxjs/operators';
import { TipoProfilo } from '@core/enums/tipo-profilo.enum';
import { Validators } from '@angular/forms';
import { ConfirmExitComponent } from '@pages/utenti/components/confirm-exit/confirm-exit.component';

@UntilDestroy()
@Component({
  selector: 'app-inserimento',
  templateUrl: './inserimento.component.html'
})
export class InserimentoComponent implements OnInit {
  acl: FunzionalitaProfiliACL;
  helperTitle: string = 'UTENTI.CREATE.HELPER';
  isEditing = false;
  // Form declaration
  form: Form;
  mandatoryMessage: any;
  comboProfili: any;

  constructor(
    private service: UtentiService,
    private router: Router,
    private notification: NotificationService,
    private modalService: ModalService,
    private i18n: I18nService,
    private loadingService: LoadingService,
    private utility: UtilityService
  ) {}

  ngOnInit(): void {
    this._initHelper();
    this.utility
      .getMessage('E010')
      .pipe(untilDestroyed(this))
      .subscribe((message) => {
        this.mandatoryMessage = message;
        this._initForm();
        this.loadingService.hide();
      });
    this._initACL();
  }

  onCreate() {
    const utente = this.form.value;
    delete utente.divider;
    delete utente.dataInizioValidita;
    delete utente.dataFineValidita;
    this.loadingService.show();

    this.service
      .createUtente(utente)
      .pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response) => {
        this.notification.info({
          title: response.message.titoloMsg,
          text: response.message.testoMsg
        });
        this.setIsEditing(false);
        this.loadingService.hide();
        this.router.navigate(['gestione-utenti-e-profili', 'gestione-utenti']);
      });
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
                'gestione-utenti-e-profili',
                'gestione-utenti'
              ])
            );
        }
      });
      return;
    }

    this.router.navigate(['gestione-utenti-e-profili', 'gestione-utenti']);
  }

  setIsEditing(value) {
    this.isEditing = value;
  }

  private _initHelper(): void {
    this.utility.getNotaInfo('UTENTE').subscribe((result: any) => {
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
      asyncValidator: [
        this.service.validateUser(),
        this.service.cfValidator(),
        this.service.emailValidator()
      ],
      filter: false,
      controls: {
        codiceFiscale: new TextInput({
          forceFocus: true,
          label: 'UTENTI.CREATE.FORM.CF.LABEL',
          placeholder: 'UTENTI.CREATE.FORM.CF.PLACEHOLDER',
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
        cognome: new TextInput({
          label: 'UTENTI.CREATE.FORM.COGNOME.LABEL',
          placeholder: 'UTENTI.CREATE.FORM.COGNOME.PLACEHOLDER',
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
        nome: new TextInput({
          label: 'UTENTI.CREATE.FORM.NOME.LABEL',
          placeholder: 'UTENTI.CREATE.FORM.NOME.PLACEHOLDER',
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
        idGestore: new AutocompleteInput({
          label: 'UTENTI.CREATE.FORM.GESTORE.LABEL',
          placeholder: 'UTENTI.CREATE.FORM.GESTORE.PLACEHOLDER',
          options: this.service.getComboGestori() as any,
          size,
          validatorOrOpts: { updateOn: 'blur' },
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ],
          clearable: true
        }),
        idProfilo: new AutocompleteInput({
          label: 'UTENTI.CREATE.FORM.PROFILO.LABEL',
          placeholder: 'UTENTI.CREATE.FORM.PROFILO.PLACEHOLDER',
          options: this.service
            .getComboProfili()
            .pipe(tap((combo: any) => (this.comboProfili = combo))) as any,
          size,
          required: true,
          valueChange: (selected) => {
            const profilo = this.comboProfili.content.find(
              (c) => c.id === selected
            );
            if (+profilo.additionalValue === TipoProfilo.FO) {
              this.form.get('idGestore').setValidators(Validators.required);              
            }

            if (+profilo.additionalValue === TipoProfilo.BO) {
              this.form.get('idGestore').clearValidators();   
            }
            this.form.get('idGestore').updateValueAndValidity();
          },
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ],
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        telefono: new TextInput({
          label: 'UTENTI.CREATE.FORM.TELEFONO.LABEL',
          placeholder: 'UTENTI.CREATE.FORM.TELEFONO.PLACEHOLDER',
          type: 'text',
          size,
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        mail: new TextInput({
          label: 'UTENTI.CREATE.FORM.MAIL.LABEL',
          placeholder: 'UTENTI.CREATE.FORM.MAIL.PLACEHOLDER',
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
          size,
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        })
      }
    });
    this.form.valueChanges
      .pipe(untilDestroyed(this))
      .subscribe(() => this.setIsEditing(true));
  }
}
