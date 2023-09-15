import { Component, OnInit } from '@angular/core';
import { FunzionalitaProfiliACL } from '@pages/configurazione-profili/models/acl.model';
import {
  DateInput,
  Divider,
  Form,
  TextInput,
  ValidationStatus
} from '@shared/form';
import { UtentiService } from '@pages/utenti/services/utenti.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NotificationService } from '@shared/notification/notification.service';
import { ModalService } from '@shared/modal/modal.service';
import { I18nService } from '@eng-ds/translate';
import { LoadingService } from '@theme/layouts/loading.services';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { csiCatchError } from '@core/operators/catch-error.operator';
import { Utente } from '@pages/utenti/models/utente.model';
import { DatePipe } from '@angular/common';
import { UtilityService } from '@core/services';
import { ConfirmExitComponent } from '@pages/utenti/components/confirm-exit/confirm-exit.component';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@UntilDestroy()
@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html'
})
export class EditComponent implements OnInit {
  acl: FunzionalitaProfiliACL;
  helperTitle: string = 'UTENTI.EDIT.HELPER';
  profiliUtente$: Observable<any>;

  currentUser: Utente;
  isEditing = false;
  isReadonly = true;
  isReadonlyAll = false;
  // Form declaration
  form: Form;
  mandatoryMessage: any;

  constructor(
    private service: UtentiService,
    private router: Router,
    private notification: NotificationService,
    private modalService: ModalService,
    private i18n: I18nService,
    private datePipe: DatePipe,
    private loadingService: LoadingService,
    private utility: UtilityService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this._initACL();
    // this._initHelper();
    this.route.data.pipe(untilDestroyed(this)).subscribe((data: any) => {
      this.currentUser = data['utente'].content;
      this.profiliUtente$ = this.service
        .getComboProfiliUtente(this.currentUser.idUtente)
        .pipe(
          tap((r) => {
            if(!this.acl.content.update){
              this.isReadonlyAll = true;
            }

            if (r.content.find((i) => i.additionalValue == '1')) {
              this.isReadonly = false;
            } else {
              this.isReadonly = true;
            }
            // chiamo qui per avere a disposizione isReadonly quando popoli il form
            this.utility
              .getMessage('E010')
              .pipe(untilDestroyed(this))
              .subscribe((message) => {
                this.mandatoryMessage = message;
                this._initForm(data['utente'].content);
                this.loadingService.hide();
              });
          })
        );
    });

    this.loadingService.hide();
  }

  onEdit() {
    this.loadingService.show();
    const utente = {...this.currentUser, ...this.form.value};
    delete utente.divider;
    utente.idUtente = this.currentUser.idUtente;

    this.service
      .editUtente(utente)
      .pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response) => {
        this.notification.info({
          title: response.message.titoloMsg,
          text: response.message.testoMsg
        });
        this.loadingService.hide();
        this.onBack();
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
                'gestione-utenti',
                'lista'
              ])
            );
        }
      });
      return;
    }

    this.router.navigate([
      'gestione-utenti-e-profili',
      'gestione-utenti',
      'lista'
    ]);
  }

  setIsEditing(value) {
    this.isEditing = value;
  }

  private _initHelper(): void {
    // TODO change codNotaInfo
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

  private _initForm(currentValue: Utente) {
    const size = '12|6|6|6|6';
    this.form = new Form({
      header: { show: false },
      asyncValidator: [
        this.service.cfValidator(),
        this.service.emailValidator()
      ],
      filter: false,
      controls: {
        codiceFiscale: new TextInput({
          forceFocus: true,
          label: 'UTENTI.EDIT.FORM.CF.LABEL',
          placeholder: 'UTENTI.EDIT.FORM.CF.PLACEHOLDER',
          type: 'text',
          value: currentValue.codiceFiscale,
          size,
          required: true,
          readonly: this.isReadonly || this.isReadonlyAll,
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
          label: 'UTENTI.EDIT.FORM.COGNOME.LABEL',
          placeholder: 'UTENTI.EDIT.FORM.COGNOME.PLACEHOLDER',
          type: 'text',
          value: currentValue.cognome,
          size,
          required: true,
          readonly: this.isReadonly || this.isReadonlyAll,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ],
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        nome: new TextInput({
          label: 'UTENTI.EDIT.FORM.NOME.LABEL',
          placeholder: 'UTENTI.EDIT.FORM.NOME.PLACEHOLDER',
          type: 'text',
          value: currentValue.nome,
          size,
          required: true,
          readonly: this.isReadonly || this.isReadonlyAll,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ],
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        telefono: new TextInput({
          label: 'UTENTI.EDIT.FORM.TELEFONO.LABEL',
          placeholder: 'UTENTI.EDIT.FORM.TELEFONO.PLACEHOLDER',
          type: 'text',
          value: currentValue.telefono,
          readonly: this.isReadonlyAll,
          size,
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        mail: new TextInput({
          label: 'UTENTI.EDIT.FORM.MAIL.LABEL',
          placeholder: 'UTENTI.EDIT.FORM.MAIL.PLACEHOLDER',
          type: 'text',
          required: true,
          value: currentValue.mail,
          readonly: this.isReadonlyAll,
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
        dataInizioValidita: new DateInput({
          label: 'UTENTI.EDIT.FORM.INIZIO.LABEL',
          placeholder: 'UTENTI.EDIT.FORM.INIZIO.PLACEHOLDER',
          required: true,
          readonly: this.isReadonlyAll,
          value: this.datePipe.transform(
            new Date(currentValue.dataInizioValidita),
            'yyyy-MM-dd'
          ),
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            })
          ],
          size,
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        dataFineValidita: new DateInput({
          label: 'UTENTI.EDIT.FORM.FINE.LABEL',
          placeholder: 'UTENTI.EDIT.FORM.FINE.PLACEHOLDER',
          value: new Date(currentValue.dataFineValidita),
          size,
          validatorOrOpts: { updateOn: 'blur' },
          readonly: this.isReadonlyAll,
          clearable: true
        })
      }
    });
  }
}
