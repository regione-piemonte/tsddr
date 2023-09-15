import { Component, OnInit } from '@angular/core';
import {
  AutocompleteInput,
  Divider,
  Form,
  TextareaInput,
  TextInput,
  ValidationStatus
} from '@shared/form';
import { Router } from '@angular/router';
import { NotificationService } from '@shared/notification/notification.service';
import { ModalService } from '@shared/modal/modal.service';
import { I18nService } from '@eng-ds/translate';
import { LoadingService } from '@theme/layouts/loading.services';
import { SecurityService, UtilityService } from '@core/services';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { UserInfo } from '@core/backend/model';
import { AccreditamentoFoService } from '@pages/accreditamento-fo/services/accreditamento-fo.service';
import { ConfirmExitComponent } from '@pages/accreditamento-fo/components/confirm-exit/confirm-exit.component';
import { csiCatchError } from '@core/operators/catch-error.operator';
import {
  DomandaAccreditamento,
  DomandaAccreditamentoResponse
} from '@pages/accreditamento-fo/models/domanda-accreditamento.model';

@UntilDestroy()
@Component({
  selector: 'app-nuova-domanda',
  templateUrl: './nuova-domanda.component.html'
})
export class NuovaDomandaComponent implements OnInit {
  user: UserInfo;

  helperTitle: string;
  isEditing = false;
  // Form declaration
  form: Form;
  mandatoryMessage: any;
  comboProfili: any;

  constructor(
    private router: Router,
    private notification: NotificationService,
    private modalService: ModalService,
    private i18n: I18nService,
    private loadingService: LoadingService,
    private utility: UtilityService,
    private accreditamentoFo: AccreditamentoFoService,
    private securityService: SecurityService
  ) {}

  ngOnInit(): void {
    this._initHelper();
    this.securityService
      .getUser()
      .pipe(untilDestroyed(this))
      .subscribe((user) => {
        this.user = user;
      });
    this.utility
      .getMessage('E010')
      .pipe(untilDestroyed(this))
      .subscribe((message) => {
        this.mandatoryMessage = message;
        this._initForm();
        this.loadingService.hide();
      });
  }

  onCreate() {
    this.loadingService.show();
    const form = this.form.value;
    const domanda: DomandaAccreditamento = {
      gestore: { idGestore: form.idGestore },
      richiedente: { email: form.mail, telefono: form.telefono },
      notaUtente: form.nota
    };

    this.accreditamentoFo
      .createDomandaAccreditamento(domanda)
      .pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response: DomandaAccreditamentoResponse) => {
        if (response.message) {
          this.notification.info({
            title: response.message.titoloMsg,
            text: response.message.testoMsg
          });
        }
        this.setIsEditing(false);
        this.loadingService.hide();
        this.router.navigate(['home']);
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
            .subscribe(() => this.router.navigate(['']));
        }
      });
      return;
    }

    this.router.navigate(['home']);
  }

  setIsEditing(value) {
    this.isEditing = value;
  }

  private _initHelper(): void {
    this.utility.getNotaInfo('INVIO DOMANDA').subscribe((result: any) => {
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

  private _initForm() {
    const size = '12|6|6|6|6';
    this.form = new Form({
      header: { show: false },
      asyncValidator: [],
      filter: false,
      controls: {
        codiceFiscale: new TextInput({
          label: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.CF.LABEL',
          placeholder: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.CF.PLACEHOLDER',
          type: 'text',
          readonly: true,
          size,
          value: this.user.codiceFiscale
        }),
        divider: new Divider(),
        cognome: new TextInput({
          label: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.COGNOME.LABEL',
          placeholder:
            'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.COGNOME.PLACEHOLDER',
          type: 'text',
          readonly: true,
          size,
          value: this.user.cognome
        }),
        nome: new TextInput({
          label: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.NOME.LABEL',
          placeholder: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.NOME.PLACEHOLDER',
          type: 'text',
          readonly: true,
          size,
          value: this.user.nome
        }),
        idGestore: new AutocompleteInput({
          label: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.GESTORE.LABEL',
          placeholder:
            'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.GESTORE.PLACEHOLDER',
          options: this.accreditamentoFo.getComboGestoriAccreditamento() as any,
          size,
          forceFocus: true,
          required: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ],
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        divider1: new Divider(),
        telefono: new TextInput({
          label: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.TELEFONO.LABEL',
          placeholder:
            'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.TELEFONO.PLACEHOLDER',
          type: 'text',
          size,
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        mail: new TextInput({
          label: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.MAIL.LABEL',
          placeholder: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.MAIL.PLACEHOLDER',
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
        }),
        nota: new TextareaInput({
          label: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.NOTE.LABEL',
          placeholder: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.NOTE.PLACEHOLDER',
          size: '12|12|12|12|12',
          maxLenght: 500 ,
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
