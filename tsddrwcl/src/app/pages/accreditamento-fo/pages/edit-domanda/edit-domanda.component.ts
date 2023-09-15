import { Component, Inject, LOCALE_ID, OnInit } from '@angular/core';
import { Form, TextareaInput, TextInput, ValidationStatus } from '@shared/form';
import { ActivatedRoute, Router } from '@angular/router';
import { NotificationService } from '@shared/notification/notification.service';
import { ModalService } from '@shared/modal/modal.service';
import { I18nService } from '@eng-ds/translate';
import { LoadingService } from '@theme/layouts/loading.services';
import { UtilityService } from '@core/services';
import { AccreditamentoFoService } from '@pages/accreditamento-fo/services/accreditamento-fo.service';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import {
  DomandaAccreditamento,
  DomandaAccreditamentoResponse
} from '@pages/accreditamento-fo/models/domanda-accreditamento.model';
import { csiCatchError } from '@core/operators/catch-error.operator';
import { ConfirmExitComponent } from '@pages/accreditamento-fo/components/confirm-exit/confirm-exit.component';
import { formatDate } from '@angular/common';

@UntilDestroy()
@Component({
  selector: 'app-edit-domanda',
  templateUrl: './edit-domanda.component.html'
})
export class EditDomandaComponent implements OnInit {
  domanda: DomandaAccreditamento;

  helperTitle: string;
  isEditing = false;
  isReadonly = false;
  // Form declaration
  form: Form;
  mandatoryMessage: any;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private notification: NotificationService,
    private modalService: ModalService,
    private i18n: I18nService,
    private loadingService: LoadingService,
    private utility: UtilityService,
    private accreditamentoFo: AccreditamentoFoService,
    @Inject(LOCALE_ID) public locale: string
  ) {}

  ngOnInit(): void {
    const { content } = this.route.snapshot.data['domanda'];
    this.domanda = content;
    this._checkStatus();
    this.utility
      .getMessage('E010')
      .pipe(untilDestroyed(this))
      .subscribe((message) => {
        this.mandatoryMessage = message;
        this._initForm();
        this.loadingService.hide();
      });
  }

  onUpdate() {
    this.loadingService.show();
    const form = this.form.value;
    const domanda: DomandaAccreditamento = this.domanda;
    domanda.richiedente.email = form.mail;
    domanda.richiedente.telefono = form.telefono;
    domanda.notaUtente = form.nota;

    this.accreditamentoFo
      .editDomandaAccreditamento(domanda)
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
        this.router.navigate(['accreditamento', 'gestione-domande']);
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
              this.router.navigate(['accreditamento', 'gestione-domande'])
            );
        }
      });
      return;
    }

    this.router.navigate(['accreditamento', 'gestione-domande']);
  }

  setIsEditing(value) {
    this.isEditing = value;
  }

  private _checkStatus(): void {
    if (this.domanda.stato.desc !== 'IN_LAVORAZIONE') {
      this.isReadonly = true;

      this.utility.getMessage('I002').subscribe((result: any) => {
        if (result.content) {
          this.notification.warning({
            title: result.content.titoloMsg,
            text: result.content.testoMsg
          });
        }
      });
    } else {
      this._initHelper();
    }
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

  private _initForm() {
    const size = '12|6|6|6|6';
    const sizeSm = '12|6|4|4|4';
    this.form = new Form({
      header: { show: false },
      asyncValidator: [],
      filter: false,
      controls: {
        codiceFiscale: new TextInput({
          forceFocus: true,
          label: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.CF.LABEL',
          placeholder: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.CF.PLACEHOLDER',
          type: 'text',
          readonly: true,
          size: sizeSm,
          value: this.domanda.richiedente.codFiscale
        }),
        stato: new TextInput({
          label: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.STATO.LABEL',
          placeholder: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.STATO.PLACEHOLDER',
          type: 'text',
          readonly: true,
          size: sizeSm,
          value: this.domanda.stato.desc
        }),
        dataRichiesta: new TextInput({
          label: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.DATA_RICHIESTA.LABEL',
          placeholder:
            'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.DATA_RICHIESTA.PLACEHOLDER',
          type: 'text',
          readonly: true,
          size: sizeSm,
          value: this.domanda.dataRichiesta
            ? formatDate(this.domanda.dataRichiesta, 'dd/MM/yyyy', this.locale)
            : null
        }),
        cognome: new TextInput({
          label: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.COGNOME.LABEL',
          placeholder:
            'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.COGNOME.PLACEHOLDER',
          type: 'text',
          readonly: true,
          size,
          value: this.domanda.richiedente.cognome
        }),
        nome: new TextInput({
          label: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.NOME.LABEL',
          placeholder: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.NOME.PLACEHOLDER',
          type: 'text',
          readonly: true,
          size,
          value: this.domanda.richiedente.nome
        }),
        idGestore: new TextInput({
          label: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.GESTORE.LABEL',
          placeholder:
            'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.GESTORE.PLACEHOLDER',
          type: 'text',
          size: sizeSm,
          value: this.domanda.gestore.ragSociale,
          readonly: true,
          clearable: true
        }),
        dataInizioValidità: new TextInput({
          label: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.DATA_INIZIO.LABEL',
          placeholder:
            'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.DATA_INIZIO.PLACEHOLDER',
          type: 'text',
          size: sizeSm,
          value: this.domanda.gestore.dataInizioValidita
            ? formatDate(
                this.domanda.gestore.dataInizioValidita,
                'dd/MM/yyyy',
                this.locale
              )
            : null,
          readonly: true,
          clearable: true
        }),
        dataFineValidità: new TextInput({
          label: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.DATA_FINE.LABEL',
          placeholder:
            'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.DATA_FINE.PLACEHOLDER',
          type: 'text',
          size: sizeSm,
          value: this.domanda.gestore.dataFineValidita
            ? formatDate(
                this.domanda.gestore.dataFineValidita,
                'dd/MM/yyyy',
                this.locale
              )
            : ' ',
          readonly: true,
          clearable: true
        }),
        telefono: new TextInput({
          label: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.TELEFONO.LABEL',
          placeholder:
            'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.TELEFONO.PLACEHOLDER',
          type: 'text',
          forceFocus: true,
          size,
          readonly: this.isReadonly,
          value: this.domanda.richiedente.telefono
            ? this.domanda.richiedente.telefono
            : ' ',
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        mail: new TextInput({
          label: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.MAIL.LABEL',
          placeholder: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.MAIL.PLACEHOLDER',
          type: 'text',
          required: true,
          readonly: this.isReadonly,
          value: this.domanda.richiedente.email,
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
          value: this.domanda.notaUtente ? this.domanda.notaUtente : ' ',
          readonly: this.isReadonly,
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        notaLavorazione: new TextareaInput({
          label: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.NOTE_LAVORAZIONE.LABEL',
          placeholder:
            'ACCREDITAMENTO_FO.NUOVA_DOMANDA.FORM.NOTE_LAVORAZIONE.PLACEHOLDER',
          size: '12|12|12|12|12',
          maxLenght: 500 ,
          value: this.domanda.notaLavorazione
            ? this.domanda.notaLavorazione
            : ' ',
          readonly: true
        })
      }
    });
    this.form.valueChanges
      .pipe(untilDestroyed(this))
      .subscribe(() => this.setIsEditing(true));
  }
}
