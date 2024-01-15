import { Component, Input, OnInit } from '@angular/core';
import { Validators } from '@angular/forms';
import { UtilityService } from '@app/core';
import { Form, TextareaInput, ValidationStatus } from '@app/shared/form';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { DichiarazioneEditingStore } from '../../models/dichiarazione.model';
import { DichiarazioneEditingStoreService } from '../../services/dichiarazione-editing-store.service';
import { DichiarazioneService } from '../../services/dichiarazioni.service';

@UntilDestroy()
@Component({
  selector: 'app-dichiarazione-tab-annotazioni',
  templateUrl: './dichiarazione-tab-annotazioni.component.html',
  styleUrls: ['./dichiarazione-tab-annotazioni.component.scss']
})
export class DichiarazioneTabAnnotazioniComponent implements OnInit {
  @Input() isEditMode: boolean = false;
  @Input() keyDichiarazione: string = 'new';

  form: Form;
  mandatoryMessage: any;
  helperTitle: any;
  dichiarazioneEditingStore:DichiarazioneEditingStore;
  isReadonly: boolean = false;


  constructor( private service: DichiarazioneService,
    private utilityService: UtilityService,
    private editingStoreService: DichiarazioneEditingStoreService
  ) {}

  ngOnInit(): void {
    this._initHelper();
    this.utilityService.getMessage('E010').pipe(untilDestroyed(this)).subscribe(
      r=>{
        this.mandatoryMessage = r;
        this.editingStoreService.getStoredDichiarazione(this.keyDichiarazione).subscribe(
          dichiarazione=>{if(dichiarazione.key===this.keyDichiarazione){this._changeDichiarazione(dichiarazione);}}
        );
        this._initForm();
      }
    );
  }

  _changeDichiarazione(dichiarazione: DichiarazioneEditingStore){
    this.dichiarazioneEditingStore = dichiarazione;
    if(dichiarazione && dichiarazione.viewMode){
      this.isReadonly = true;
    }
  }

  private _initHelper(): void {
    this.utilityService.getNotaInfo('ANNOTA').subscribe((result: any) => {
      if (result.content) {
        this.helperTitle = result.content.testoInfo;
      }
    });
  }

  private _initForm(){
    const size = '12|6|6|6|6';
    this.form = new Form({
      header: { show: false },
      filter: false,
      controls: {
        annotazioni: new TextareaInput({
          label: 'DICHIARAZIONI.ANNOTAZIONI.FORM.ANNOTAZIONI.LABEL',
          placeholder: 'DICHIARAZIONI.ANNOTAZIONI.FORM.ANNOTAZIONI.PLACEHOLDER',
          size,
          clearable: true,
          value: this.dichiarazioneEditingStore.dichiarazione.annotazioni,
          validatorOrOpts: [Validators.maxLength(500)],
          validationStatus: [
            ValidationStatus.ERROR.CUSTOM(
              (control) => control.touched && control.hasError('maxlength'),
              {
                text: 'Testo maggiore di 500 caratteri'
              }
            )
          ],
          readonly:this.isReadonly
        })
      }
    });
    this.form.valueChanges.subscribe(x => {
        this.editingStoreService.setAnnotazioni(x['annotazioni'],this.keyDichiarazione);
        this.editingStoreService.setStatus('annotazioniChanged',true);
    });
  }

}
