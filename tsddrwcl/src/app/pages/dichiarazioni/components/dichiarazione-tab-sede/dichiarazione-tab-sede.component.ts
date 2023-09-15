import { Component, Input, OnInit  } from '@angular/core';
import { UtilityService } from '@app/core';
import { Indirizzo } from '@app/pages/impianti/models/impianto.model';
import { AutocompleteInput, Form, TextInput, ValidationStatus } from '@app/shared/form';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { DichiarazioneEditingStore } from '../../models/dichiarazione.model';
import { DichiarazioneEditingStoreService } from '../../services/dichiarazione-editing-store.service';
import { DichiarazioneService } from '../../services/dichiarazioni.service';

@UntilDestroy()
@Component({
  selector: 'app-dichiarazione-tab-sede',
  templateUrl: './dichiarazione-tab-sede.component.html',
  styleUrls: ['./dichiarazione-tab-sede.component.scss']
})
export class DichiarazioneTabSedeComponent implements OnInit {
  @Input() isEditMode: boolean = false;
  @Input() keyDichiarazione: string = 'new';
  isReadonly: boolean = false;


  form: Form;
  mandatoryMessage: any;
  helperTitle: any;
  dichiarazioneEditingStore:DichiarazioneEditingStore;

  constructor( private service: DichiarazioneService,
    private utilityService: UtilityService,
    private editingStoreService: DichiarazioneEditingStoreService
  ) {}

  ngOnInit(): void {
    //this._initHelper();
    this.utilityService.getMessage('E010').pipe(untilDestroyed(this)).subscribe(
      r=>{
        this.mandatoryMessage = r;

        this.editingStoreService.getStoredDichiarazione(this.keyDichiarazione).subscribe(
          dichiarazione=>{if(dichiarazione.key===this.keyDichiarazione){this._changeDichiarazione(dichiarazione);}}
        );
        if(!this.form){
          this._initForm();
        }
      }
    );
  }

  _changeDichiarazione(dichiarazione: DichiarazioneEditingStore){
    this.dichiarazioneEditingStore = dichiarazione;
    if(dichiarazione && dichiarazione.viewMode){
      this.isReadonly = true;
    }
    if(this.dichiarazioneEditingStore.dichiarazione.indirizzo){
      if(!this.form){
        this._initForm();
      }
      this._setValueForm(this.dichiarazioneEditingStore.dichiarazione.indirizzo);
    }
  }


  private _initHelper(): void {
    this.utilityService.getNotaInfo('SOGGETTI').subscribe((result: any) => {
      if (result.content) {
        this.helperTitle = result.content.testoInfo;
      }
    });
  }

  private _setValueForm(indirizzo:Indirizzo=null){
    if(indirizzo.sedime && indirizzo.sedime.idSedime && indirizzo.sedime.idSedime!=+this.form.get('sedime').value){
      this.form.get('sedime').setValue(indirizzo.sedime.idSedime.toString());
    }
    if(indirizzo.indirizzo && indirizzo.indirizzo!=this.form.get('indirizzo').value){
      this.form.get('indirizzo').setValue(indirizzo.indirizzo);
    }

    if(indirizzo.comune && indirizzo.comune.provincia && indirizzo.comune.provincia.idProvincia && indirizzo.comune.provincia.idProvincia!=+this.form.get('idProvincia').value){
      this.form.get('idProvincia').setValue(indirizzo.comune.provincia.idProvincia);
    }

    if(indirizzo.comune && indirizzo.comune.idComune && indirizzo.comune.idComune!=+this.form.get('idComune').value){
      this.form.get('idComune').setValue(indirizzo.comune.idComune);
    }

    if(indirizzo.cap && indirizzo.cap!=this.form.get('cap').value){
      this.form.get('cap').setValue(indirizzo.cap);
    }
  }

  private _initForm(indirizzo:Indirizzo=null){
    const size = '12|6|6|6|6';
    this.form = new Form({
      header: { show: false },
      filter: false,
      controls: {
        sedime: new AutocompleteInput({
          label: 'DICHIARAZIONI.SEDEDOCUMENTI.FORM.IDSEDIME.LABEL',
          placeholder: 'DICHIARAZIONI.SEDEDOCUMENTI.FORM.IDSEDIME.PLACEHOLDER',
          options: this.service.getComboSedimi() as any,
          size,
          required:true,
          clearable: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            }),
          ],
          readonly:this.isReadonly
        }),
        indirizzo: new TextInput({
          label: 'DICHIARAZIONI.SEDEDOCUMENTI.FORM.INDIRIZZO.LABEL',
          placeholder: 'DICHIARAZIONI.SEDEDOCUMENTI.FORM.INDIRIZZO.PLACEHOLDER',
          type: 'text',
          required:true,
          size,
          clearable: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            }),
          ],
          readonly:this.isReadonly
        }),
        idProvincia: new AutocompleteInput({
          label: 'DICHIARAZIONI.SEDEDOCUMENTI.FORM.PROVINCIA.LABEL',
          required:true,
          placeholder: 'DICHIARAZIONI.SEDEDOCUMENTI.FORM.PROVINCIA.PLACEHOLDER',
          options: this.service.getComboProvince() as any,
          compareWith: (item, selected) => +item.id === selected,
          valueChange: (idProvincia: number) => {
            if (idProvincia){
              (
                this.form.get('idComune') as AutocompleteInput
              ).setOptions(
                this.service.getComboComuni(+idProvincia) as any
              );
              this.form.get('idComune').setValue(null);
            }else{
              (
                this.form.get('idComune') as AutocompleteInput
              ).setOptions(
                (this.service.getComboComuni()) as any
              );
              this.form.get('idComune').setValue(null);
            }
          },
          size,
          clearable: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            }),
          ],
          readonly:this.isReadonly
        }),
        idComune: new AutocompleteInput({
          label: 'DICHIARAZIONI.SEDEDOCUMENTI.FORM.COMUNE.LABEL',
          placeholder: 'DICHIARAZIONI.SEDEDOCUMENTI.FORM.COMUNE.PLACEHOLDER',

          options:  this.service.getComboComuni() as any,
          compareWith: (item, selected) => +item.id === selected,
          valueChange: (idComune: number) => {
            if (idComune){
              let cap = this.service.getCap(+idComune);
              this.form.get('cap').setValue(cap);
            }
          },
          size,
          clearable: true,
          required:true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.content.testoMsg
            }),
          ],
          readonly:this.isReadonly
        }),
        cap: new TextInput({
          label: 'DICHIARAZIONI.SEDEDOCUMENTI.FORM.CAP.LABEL',
          placeholder: 'DICHIARAZIONI.SEDEDOCUMENTI.FORM.CAP.PLACEHOLDER',
          type: 'text',
          size,
          required:true,
          clearable: true,
          readonly:this.isReadonly,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.testoMsg
            }),
          ]
        })
      }
    });
    this.form.valueChanges.subscribe(x => {
      const indirizzo:Indirizzo = {

      };
      if(x['indirizzo']){
        indirizzo.indirizzo = x['indirizzo'];
      }
      if(x['sedime']){
        indirizzo.sedime={idSedime:+x['sedime']}
      }
      if(x['cap']){
        indirizzo.cap=x['cap'];
      }
      if(x['idProvincia']){
        if(!indirizzo.comune){
          indirizzo.comune ={}
        }
        if(!indirizzo.comune.provincia){
          indirizzo.comune.provincia={}
        }
        indirizzo.comune.provincia.idProvincia = +x['idProvincia'];
      }
      if(x['idComune']){
        if(!indirizzo.comune){
          indirizzo.comune ={}
        }
        indirizzo.comune.idComune=+x['idComune'];
      }

      const sedeValid=(indirizzo.indirizzo && indirizzo.comune && indirizzo.comune.idComune && indirizzo.sedime && indirizzo.cap)?true:false;
      this.editingStoreService.setIndirizzo(indirizzo,sedeValid,this.keyDichiarazione);
      this.editingStoreService.setStatus('sedeValid',sedeValid);
      this.editingStoreService.setStatus('sedeChanged',true);
    });

  }

}
