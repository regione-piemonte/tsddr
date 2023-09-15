import { Component, OnInit } from '@angular/core';
import { DomandaAccreditamentoACL } from '@pages/domanda-accreditamento/models/acl.model';
import {
  AutocompleteInput,
  Form,
  TextareaInput,
  TextInput,
} from '@shared/form';
import { DomandaAccreditamentoService } from '@app/pages/domanda-accreditamento/services/domanda-accreditamento.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NotificationService } from '@shared/notification/notification.service';
import { ModalService } from '@shared/modal/modal.service';
import { I18nService } from '@eng-ds/translate';
import { LoadingService } from '@theme/layouts/loading.services';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { csiCatchError } from '@core/operators/catch-error.operator';
import { Domanda } from '@app/pages/domanda-accreditamento/models/domanda.model';
import { DatePipe } from '@angular/common';
import { UtilityService } from '@core/services';
import { ConfirmExitComponent } from '@pages/domanda-accreditamento/components/confirm-exit/confirm-exit.component';
import { of } from 'rxjs';

@UntilDestroy()
@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html'
})
export class EditComponent implements OnInit {
  acl: DomandaAccreditamentoACL;
  helperTitle: string;

  currentDomanda: Domanda;
  // Form declaration
  form: Form;

  constructor(
    private service: DomandaAccreditamentoService,
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
    this.route.data.pipe(untilDestroyed(this)).subscribe((data: any) => {
      this.currentDomanda = data['domanda'].content;
      if (this.currentDomanda.stato.id == 4) {
        this.utility
          .getMessage('I002')
          .pipe(untilDestroyed(this))
          .subscribe((message: any) => {
            this._initForm(data['domanda'].content);
            this.loadingService.hide();
            this.notification.info({
              title: message.content.titoloMsg,
              text: message.content.testoMsg
            });
          });
      } else {
        this._initHelper();
        this._initForm(data['domanda'].content);
        this.loadingService.hide();
      }
    });
    this.loadingService.hide();
  }

  private _initHelper(): void {
    this.utility.getNotaInfo('MODIFICA').subscribe((result: any) => {
      if (result.content) {
        this.helperTitle = result.content.testoInfo;
      }
    });
  }

  onEdit() {
    this.loadingService.show();
    const domandaForm = this.form.value;
    console.log(domandaForm);
    let domanda: Partial<Domanda> = {
      idDomanda: this.currentDomanda.idDomanda,
      profilo: {
        idProfilo: domandaForm.profilo
      },
      notaLavorazione: domandaForm.notaLavorazione,
      stato: {
        id: domandaForm.statoDomanda
      }
    };
    this.service
      .editDomanda(domanda)
      .pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response) => {
        this.notification.info({
          title: this.i18n.translate('ACCREDITAMENTO_BO.EDIT.EDITSUCCESSTITLE'),
          text: this.i18n.translate('ACCREDITAMENTO_BO.EDIT.EDITSUCCESSTEXT')
        });
        this.loadingService.hide();
        this.router.navigate(['domanda-accreditamento', 'lista']);
      });
  }

  onBack() {
    if (this.form.valid && this.currentDomanda.stato.id != 4) {
      this.utility.getMessage('A001').subscribe((result: any) => {
        const { content } = result;
        if (content) {
          const dialog = this._preventDataLose(content);

          dialog.closed
            .pipe(untilDestroyed(this))
            .subscribe(() =>
              this.router.navigate(['domanda-accreditamento', 'lista'])
            );
        }
      });
      return;
    }

    this.router.navigate(['domanda-accreditamento', 'lista']);
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

  private _initForm(currentValue: Domanda) {

    let comboProfili;
    if (
      currentValue.stato.id == 2 ||
      currentValue.stato.id == 3 ||
      currentValue.stato.id == 4
    ) {
      comboProfili = of({
        content: [
          {
            id: currentValue?.profilo?.idProfilo.toString(),
            value: currentValue?.profilo?.descProfilo
          }
        ]
      })
    } else {
      comboProfili = (this.service.getComboProfiliAccreditamento(
        currentValue.idDomanda
      )) as any;
    }

    let showPlac;
    if(currentValue.notaLavorazione!=undefined){
      showPlac=false
    }else if(currentValue.stato.id===4 && currentValue.notaLavorazione==undefined ){

      showPlac=false
    }
    else{
      showPlac=true
    }

    const size = '12|6|6|6|6';
    this.form = new Form({
      header: { show: false },
      validatorOrOpts: [this.service.editValidator(currentValue)],
      filter: false,
      controls: {
        statoDomanda: new AutocompleteInput({
          label: 'ACCREDITAMENTO_BO.EDIT.FORM.STATODOMANDA.LABEL',
          placeholder: 'ACCREDITAMENTO_BO.EDIT.FORM.STATODOMANDA.PLACEHOLDER',
          options:
            currentValue.stato.id == 2 ||
            currentValue.stato.id == 3 ||
            currentValue.stato.id == 4
              ? this.service.getComboAllStati()
              : (this.service.getComboStati() as any),
          value: currentValue.stato.id.toString(),
          size,
          validatorOrOpts: { updateOn: 'blur' },
          valueChange: (stato: string) => {
            if (currentValue.stato.id.toString() == '1' && stato == '2') {
              this.form.controls['profilo'].enable();
            } else {
              this.form.controls['profilo'].disable();
            }
          },
          readonly:
            currentValue.stato.id == 2 ||
            currentValue.stato.id == 3 ||
            currentValue.stato.id == 4
              ? true
              : false,
          clearable: true,
          required: true
        }),
        codiceFiscale: new TextInput({
          label: 'ACCREDITAMENTO_BO.EDIT.FORM.CF.LABEL',
          placeholder: 'ACCREDITAMENTO_BO.EDIT.FORM.PLACEHOLDER',
          type: 'text',
          value: currentValue.richiedente.codFiscale ? currentValue.richiedente.codFiscale : '',
          size,
          readonly: true
        }),
        profilo: new AutocompleteInput({
          label: 'ACCREDITAMENTO_BO.EDIT.FORM.PROFILO.LABEL',
          placeholder: 'ACCREDITAMENTO_BO.EDIT.FORM.PROFILO.PLACEHOLDER',
          options: comboProfili as any,
          value: currentValue?.profilo?.idProfilo.toString() ? currentValue?.profilo?.idProfilo.toString() : '',
          size,
          clearable: true,
          readonly: true,
        }),
        cognome: new TextInput({
          label: 'ACCREDITAMENTO_BO.EDIT.FORM.COGNOME.LABEL',
          placeholder: 'ACCREDITAMENTO_BO.EDIT.FORM.COGNOME.PLACEHOLDER',
          type: 'text',
          value: currentValue.richiedente.cognome,
          readonly: true,
          size,
          required: true,
          clearable: true
        }),
        nome: new TextInput({
          label: 'ACCREDITAMENTO_BO.EDIT.FORM.NOME.LABEL',
          placeholder: 'ACCREDITAMENTO_BO.EDIT.FORM.NOME.PLACEHOLDER',
          type: 'text',
          value: currentValue.richiedente.nome,
          readonly: true,
          size,
          required: true,
          validatorOrOpts: { updateOn: 'blur' },
          clearable: true
        }),
        gestore: new AutocompleteInput({
          label: 'ACCREDITAMENTO_BO.EDIT.FORM.GESTORE.LABEL',
          placeholder: 'ACCREDITAMENTO_BO.EDIT.FORM.GESTORE.PLACEHOLDER',
          options: this.service.getComboGestoriAccreditamento() as any,
          value: currentValue.gestore.idGestore.toString() ?  currentValue.gestore.idGestore.toString() : '',
          size,
          clearable: true,
          readonly: true
        }),
        telefono: new TextInput({
          label: 'ACCREDITAMENTO_BO.EDIT.FORM.TELEFONO.LABEL',
          placeholder: 'ACCREDITAMENTO_BO.EDIT.FORM.TELEFONO.PLACEHOLDER',
          type: 'text',
          value: currentValue.richiedente.telefono ? currentValue.richiedente.telefono : ' ',
          readonly: true,
          size,
          clearable: true
        }),
        mail: new TextInput({
          label: 'ACCREDITAMENTO_BO.EDIT.FORM.EMAIL.LABEL',
          placeholder: 'ACCREDITAMENTO_BO.EDIT.FORM.EMAIL.PLACEHOLDER',
          type: 'text',
          readonly: true,
          value: currentValue.richiedente.email ? currentValue.richiedente.email : '',
          size,
          clearable: true
        }),
        notaUtente: new TextareaInput({
          label: 'ACCREDITAMENTO_BO.EDIT.FORM.NOTAUTENTE.LABEL',
          placeholder: 'ACCREDITAMENTO_BO.EDIT.FORM.NOTAUTENTE.PLACEHOLDER',
          value: currentValue.notaUtente ? currentValue.notaUtente : ' ',
          readonly: true,
          size,
          clearable: true
        }),
        notaLavorazione: new TextareaInput({
          label: 'ACCREDITAMENTO_BO.EDIT.FORM.NOTALAVORAZIONE.LABEL',
          placeholder:
            'ACCREDITAMENTO_BO.EDIT.FORM.NOTALAVORAZIONE.PLACEHOLDER',
          value: showPlac ? currentValue?.notaLavorazione : ' ',
          size,
          clearable: true,
          readonly: currentValue.stato.id == 4 ? true : false
        })
      }
    });
  }
}
