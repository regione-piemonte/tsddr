import { PrevConsLineeClass } from './../../../models/classes/prev-cons-linee.class';
import { Component, Input, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { Validators } from '@angular/forms';
import { Form, TextareaInput, ValidationStatus } from '@app/shared/form';
import { MrStoreService } from '@app/shared/prev-cons/services/mr-store.service';

@Component({
  selector: 'app-tab-descrizione',
  templateUrl: './tab-descrizione.component.html',
  styleUrls: ['./tab-descrizione.component.scss']
})
export class TabDescrizioneComponent implements OnInit, OnChanges {

  @Input() isEditMode: boolean;
  @Input() prevConsLinea: PrevConsLineeClass;
  @Input() idPrevCons: number = null;

  form: Form;

  constructor(
    private mrStoreService: MrStoreService
  ) { }

  ngOnInit(): void {
    this._initForm();
  }

  ngOnChanges(changes: SimpleChanges){
    const { prevConsLinea } = changes;
    if(prevConsLinea?.currentValue && this.form){
      this.form.get('descrizione').patchValue(prevConsLinea.currentValue.descSommaria)
    }
  }

  private _initForm(){
    const size = '12|6|6|6|6';
    this.form = new Form({
      header: { show: false },
      filter: false,
      controls: {
        descrizione: new TextareaInput({
          label: 'DICHIARAZIONI_MR.TABS.PROCESSO.DESCRIZIONE.NOTA',
          placeholder: this.isEditMode ? 'DICHIARAZIONI_MR.TABS.PROCESSO.DESCRIZIONE.TITLE' : ' ',
          size,
          clearable: true,
          value: this.prevConsLinea?.descSommaria ?? '',
          validatorOrOpts: [Validators.maxLength(1000)],
          validationStatus: [
            ValidationStatus.ERROR.CUSTOM(
              (control) => control.touched && control.hasError('maxlength'),
              {
                text: 'Testo maggiore di 1000 caratteri'
              }
            )
          ],
          readonly: !this.isEditMode,
        })
      },
      validatorOrOpts: { updateOn: 'blur' }
    });
    this.form.valueChanges.subscribe(x => {
      this.mrStoreService.setPropsStatus('processoChanged', true);
      this.prevConsLinea.descSommaria = (x['descrizione']);

      //se ci sono dei cambiamenti nella modifca ho l'idPrevCons, e valorizzo il booleano da mandare al BE,
      if(this.idPrevCons){
        this.prevConsLinea.hasBeenUpdated = true;
      }
    });
  }

}
