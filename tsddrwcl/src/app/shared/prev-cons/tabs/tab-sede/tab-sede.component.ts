import { Component, Input, OnInit } from '@angular/core';
import { UtilityService } from '@app/core';
import { IMessage, IMessageWrapper } from '@app/core/models/shared.model';
import { Indirizzo } from '@app/pages/impianti/models/impianto.model';
import { Form, AutocompleteInput, ValidationStatus, TextInput } from '@app/shared/form';
import { untilDestroyed, UntilDestroy } from '@ngneat/until-destroy';
import { PrevConsClass } from '../../models/classes/prev-cons.class';
import { MrStoreService } from '../../services/mr-store.service';
import { MrService } from '../../services/mr.service';

@UntilDestroy()
@Component({
  selector: 'app-tab-sede',
  templateUrl: './tab-sede.component.html',
  styleUrls: ['./tab-sede.component.scss']
})
export class TabSedeComponent implements OnInit {

  @Input() isEditMode: boolean = false;
  @Input() prevCons: PrevConsClass;

  helperTitle: string;

  form: Form;

  mandatoryMessage: IMessage;

  constructor(
    private utilityService: UtilityService,
    private mrService: MrService,
    private mrStoreService: MrStoreService
  ) { }

  ngOnInit(): void {
    this.utilityService.getMessage('E010').pipe(untilDestroyed(this)).subscribe(res => {
      this.mandatoryMessage = (res as IMessageWrapper).content;

      if(!this.form){
        this._initForm();
      }
    });
    this._initHelper();
  }

  private _initForm() {
    const size = '12|6|6|6|6';
    this.form = new Form({
      header: { show: false },
      filter: false,
      controls: {
        sedime: new AutocompleteInput({
          label: 'DICHIARAZIONI.SEDEDOCUMENTI.FORM.IDSEDIME.LABEL',
          placeholder: this.isEditMode ? 'DICHIARAZIONI.SEDEDOCUMENTI.FORM.IDSEDIME.PLACEHOLDER' : ' ',
          options: this.mrService.getComboSedimi() as any,
          value: this.prevCons.indirizzo?.sedime?.idSedime.toString() ?? null,
          size,
          required: true,
          clearable: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.testoMsg
            }),
          ],
          readonly: !this.isEditMode,
        }),
        indirizzo: new TextInput({
          label: 'DICHIARAZIONI.SEDEDOCUMENTI.FORM.INDIRIZZO.LABEL',
          placeholder: this.isEditMode ? 'DICHIARAZIONI.SEDEDOCUMENTI.FORM.INDIRIZZO.PLACEHOLDER' : ' ',
          type: 'text',
          value: this.prevCons.indirizzo?.indirizzo ?? null,
          required: true,
          size,
          clearable: true,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.testoMsg
            }),
          ],
          readonly: !this.isEditMode,
        }),
        idProvincia: new AutocompleteInput({
          label: 'DICHIARAZIONI.SEDEDOCUMENTI.FORM.PROVINCIA.LABEL',
          required: true,
          placeholder: this.isEditMode ? 'DICHIARAZIONI.SEDEDOCUMENTI.FORM.PROVINCIA.PLACEHOLDER' : ' ',
          options: this.mrService.getComboProvince() as any,
          value: this.prevCons.indirizzo?.comune?.provincia?.idProvincia ?? null,
          compareWith: (item, selected) => +item.id === selected,
          valueChange: (idProvincia: number) => {
            if (idProvincia){
              (
                this.form.get('idComune') as AutocompleteInput
              ).setOptions(
                this.mrService.getComboComuni(+idProvincia) as any
              );
              this.form.get('idComune').setValue(null);
            }else{
              (
                this.form.get('idComune') as AutocompleteInput
              ).setOptions(
                (this.mrService.getComboComuni()) as any
              );
                this.form.get('idComune').setValue(null);
            }
          },
          size,
          clearable: true,
          readonly: !this.isEditMode,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.testoMsg
            }),
          ],
        }),
        idComune: new AutocompleteInput({
          label: 'DICHIARAZIONI.SEDEDOCUMENTI.FORM.COMUNE.LABEL',
          placeholder: this.isEditMode ? 'DICHIARAZIONI.SEDEDOCUMENTI.FORM.COMUNE.PLACEHOLDER' : ' ',
          options:  this.mrService.getComboComuni() as any,
          value: this.prevCons.indirizzo?.comune?.idComune ?? null,
          compareWith: (item, selected) => +item.id === selected,
          valueChange: (idComune: number) => {
            if (idComune){
              let cap = this.mrService.getCap(+idComune);
              this.form.get('cap').setValue(cap);
            }
          },
          size,
          clearable: true,
          required: true,
          readonly: !this.isEditMode,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.testoMsg
            }),
          ],
        }),
        cap: new TextInput({
          label: 'DICHIARAZIONI.SEDEDOCUMENTI.FORM.CAP.LABEL',
          placeholder: this.isEditMode ? 'DICHIARAZIONI.SEDEDOCUMENTI.FORM.CAP.PLACEHOLDER' : ' ',
          type: 'text',
          value: this.prevCons.indirizzo?.cap ?? null,
          size,
          required: true,
          clearable: true,
          readonly: !this.isEditMode,
          validationStatus: [
            ValidationStatus.ERROR.REQUIRED_WITH_MESSAGE({
              text: this.mandatoryMessage.testoMsg
            }),
          ]
        })
      }
    });

    this.form.valueChanges.subscribe(x => {
      const indirizzo:Indirizzo = {};
      if(x['indirizzo']){
        indirizzo.indirizzo = x['indirizzo'];
      }
      if(x['sedime']){
        indirizzo.sedime = {idSedime: +x['sedime']}
      }
      if(x['cap']){
        indirizzo.cap = x['cap'];
      }
      if(x['idProvincia']){
        if(!indirizzo.comune){
          indirizzo.comune = {}
        }
        if(!indirizzo.comune.provincia){
          indirizzo.comune.provincia = {}
        }
        indirizzo.comune.provincia.idProvincia = +x['idProvincia'];
      }
      if(x['idComune']){
        if(!indirizzo.comune){
          indirizzo.comune = {}
        }
        indirizzo.comune.idComune= +x['idComune'];
      }

      const sedeValid: boolean = (indirizzo.indirizzo && indirizzo.comune && indirizzo.comune.idComune && indirizzo.sedime && indirizzo.cap && indirizzo.comune.provincia) ? true : false;
      this.mrStoreService.setPropsStatus('sedeChanged', true);
      this.mrStoreService.setPropsStatus('sedeValid', sedeValid);
      this.prevCons.indirizzo = indirizzo;

      //se ci sono dei cambiamenti nella modifca ho l'idPrevCons, e valorizzo il booleano da mandare al BE,
      if(this.prevCons.idPrevCons){
        this.prevCons.indirizzo.hasBeenUpdated = true;
      }
    });

  }

  private _initHelper(): void {
    this.utilityService.getNotaInfo('SEDE_R').subscribe((result: any) => {
      if (result.content) {
        this.helperTitle = result.content.testoInfo;
      }
    });
  }

}
