import { IMessage } from '@app/core/models/shared.model';
import { Component, Input, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';

import { UtilityService } from '@app/core';
import { DateInput, Form, TextInput, ValidationStatus } from '@app/shared/form';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { forkJoin } from 'rxjs';
import { MrService } from '../../services/mr.service';
import { IPrevCons } from '../../interfaces/prev-cons.interface';
import { PrevConsClass } from '../../models/classes/prev-cons.class';
import { MrStoreService } from '../../services/mr-store.service';

/**
 * Se la D-MR NON è legata ad una Richiesta presente a sistema
 * valorizzo il form Modalità e prendo dalla MIA prev cons DATA_INVIO_DOC e MODALITA
 *
 * IN ALTERNATIVA,
 *
 * se non valorizzati i campi DATA_INVIO_DOC e MODALITA, è valorizzato il campo ID_PREV_CONS_R_MR, che lega la D-MR ad una Richiesta MR
 * quindi vado a prendere la R-MR legata alla mia D-MR in this.prevCons.prevConsRichiesta
 * e vado a valorizzare il form DataValue con i valori presi dalla RMR
 *
 */
@UntilDestroy()
@Component({
  selector: 'app-tab-richiesta',
  templateUrl: './tab-richiesta.component.html',
  styleUrls: ['./tab-richiesta.component.scss']
})
export class TabRichiestaComponent implements OnInit {
  @Input() isEditMode: boolean = false;
  @Input() prevCons: PrevConsClass;

  helperTitle: any;

  formData: Form;
  formModalita: Form;
  modalitaReadonly: boolean = false;

  mandatoryMessage: IMessage;
  validationMessage: IMessage;

  richiestaRMR: IPrevCons = {};

  constructor(
    private utilityService: UtilityService,
    private mrService: MrService,
    private mrStoreService: MrStoreService,
    private datePipe: DatePipe,
  ) {}

  ngOnInit(): void {
    this._initHelper();
    this.modalitaReadonly = !this.isEditMode;
    
    forkJoin([
      this.utilityService.getMessage('E010').pipe(untilDestroyed(this)),
      this.utilityService.getMessage('A019').pipe(untilDestroyed(this))
    ]).subscribe(([messageE010, messageA019]) => {
      this.mandatoryMessage = messageE010.content;
      this.validationMessage = messageA019.content;
      
      // verifico se è presente una richiesta RMR associata per valorizzare il giusto form
      if (this.prevCons.prevConsRichiesta) {
        this.richiestaRMR = this.prevCons.prevConsRichiesta;
        // se ho già una RMR legata, nel form modalità non devo inserire nulla, neanche in modalità modifica o inserimento
        this.modalitaReadonly = true;
        this.mrStoreService.setPropsStatus('richiestaValid', true);
      }

      if (!this.formData || !this.formModalita) {
        this._initForm();
      }
    });
  }

  private _initForm() {
    const size = '12|6|9|9|9';
    const sizeS = '12|6|1|1|1';
    const sizeM = '12|6|2|2|2';

    //I dati visualizzati in questo form fanno riferimento ad una Richiesta R-MR precedente e devono essere solamente visualizzati
    this.formData = new Form({
      header: { show: false },
      filter: false,
      controls: {
        _r_annoTributo: new TextInput({
          label: 'DICHIARAZIONI.CREATE.FORM.ANNO.LABEL',
          placeholder: ' ',
          type: 'text',
          size: sizeS,
          readonly: true,
          value: this.richiestaRMR?.annoTributo
            ? this.richiestaRMR.annoTributo.toString()
            : ''
        }),
        _r_dataDoc: new TextInput({
          label: 'DICHIARAZIONI_MR.CREATE.FORM.DATA_REGISTRAZIONE.LABEL',
          placeholder: ' ',
          type: 'text',
          size: sizeM,
          readonly: true,
          value: this.datePipe.transform(
            this.richiestaRMR?.dataDoc,
            'dd/MM/yyyy'
          )
          ?? ''
        }),
        _r_numProtocollo: new TextInput({
          label: 'DICHIARAZIONI_MR.CREATE.FORM.PROTOCOLLO.LABEL',
          placeholder: ' ',
          type: 'text',
          readonly: true,
          size: sizeM,
          value: this.richiestaRMR?.numProtocollo ?? ''
        })
      }
    });

    this.formModalita = new Form({
      header: { show: false },
      filter: false,
      validatorOrOpts: [this.mrService.notBothValuedValidator()],
      controls: {
        _richiesta_dataInvioDoc: new DateInput({
          label: 'DICHIARAZIONI_MR.CREATE.FORM.MODALITA.LABEL',
          placeholder: ' ',
          size: sizeM,
          readonly: this.modalitaReadonly,
          value:
            this.prevCons.dataInvioDoc ?? '',
          validationStatus: [
            ValidationStatus.ERROR.CUSTOM(
              (control) =>
                control.touched && control.parent?.hasError('notBothValued'),
              {
                text: this.validationMessage.testoMsg
              }
            )
          ],
          validatorOrOpts: { updateOn: 'blur' }
        }),
        _richiesta_modalita: new TextInput({
          label: 'DICHIARAZIONI_MR.CREATE.FORM.MODALITA.MODALITA',
          placeholder: ' ',
          type: 'text',
          readonly: this.modalitaReadonly,
          size:'4|4|4|4|4',
          value: this.prevCons.modalita ?? '',
          validationStatus: [
            ValidationStatus.ERROR.CUSTOM(
              (control) =>
                control.touched && control.parent?.hasError('notBothValued'),
              {
                text: this.validationMessage.testoMsg
              }
            )
          ],
          validatorOrOpts: { updateOn: 'blur' }
        }),
        _richiesta_numProtDoc: new TextInput({
          label: 'DICHIARAZIONI_MR.CREATE.FORM.PROTOCOLLO.LABEL',
          placeholder: ' ',
          type: 'text',
          readonly: this.modalitaReadonly,
          size: '2|2|2|2|2',
          value: this.prevCons.numProtDoc ?? '',
        /*  validationStatus: [
            ValidationStatus.ERROR.CUSTOM(
              (control) =>
                control.touched && control.parent?.hasError('notBothValued'),
              {
                text: this.validationMessage.testoMsg
              }
            )
          ],*/
          validatorOrOpts: { updateOn: 'blur' }
        })
      }
    });

    this.formModalita.valueChanges.subscribe((field) => {
      if (this.isEditMode) {
        this.prevCons.modalita = field['_richiesta_modalita'];
        this.prevCons.dataInvioDoc = field['_richiesta_dataInvioDoc'];
        this.prevCons.numProtDoc= field['_richiesta_numProtDoc'];
        // notifico allo status che qualcosa è cambiato e/o la sezione è valida
        this.mrStoreService.setPropsStatus('richiestaChanged', true);
        this.mrStoreService.setPropsStatus('richiestaValid', this.formModalita.valid);

        //se ci sono dei cambiamenti nella modifca ho l'idPrevCons, e valorizzo il booleano da mandare al BE,
        if(this.prevCons.idPrevCons){
          this.prevCons.richiestaAmmissioneHasBeenUpdated = true;
        }
      }
    });
  }

  private _initHelper(): void {
    this.utilityService.getNotaInfo('RIC_AMM_R_MR').subscribe((result: any) => {
      if (result.content) {
        this.helperTitle = result.content.testoInfo;
      }
    });
  }
}
