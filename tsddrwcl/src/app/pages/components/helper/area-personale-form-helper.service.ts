import { Injectable } from '@angular/core';
import { Form, SelectInput, TextInput } from '@shared/form';
import { AbstractFormHelperService } from '@core/helper/abstract-form-helper.service';
import { AreaPersonaleService } from '../sevices/area-personale.service';
import { AreaPersonaleValidator } from './area-personale-validator.service';

@Injectable({
  providedIn: 'root'
})
export class AreaPersonaleFormHelperService extends AbstractFormHelperService {
  size = '12|6|6|4|4';
  areapersonaleTopValues = null;
  areapersonaleBottomValues = null;
  areapersonaleBottomValuesInitial = null;
  formBottom: Form;
  readOnly:boolean;
  errors: {};

  constructor(
    private areaPersonaleService:AreaPersonaleService
  ) {
    super();
    this.areaPersonaleService
    .getStream()
    .subscribe((packet) => {
      switch (packet.dataTag) {
        case "validatorVerificaCampiObbligatoriErrors":
          this.areaPersonaleBottomFormErrors(packet.data);
          break;
      }
    });
  }

  public initForms(areapersonaleTop = null,areapersonaleBottom=null, readOnly:boolean=false){
    this.areapersonaleTopValues = areapersonaleTop;
    this.areapersonaleBottomValues = areapersonaleBottom;
    this.areapersonaleBottomValuesInitial = areapersonaleBottom;
    this.readOnly = readOnly;
    this._initForms();
  }

  private areaPersonaleBottomFormErrors(errorsBE){
    // annullo tutti gli errori
    this.errors={};
    if(errorsBE && Array.isArray(errorsBE)){
      errorsBE.forEach(element => {
          this.errors[element.campo]=element.titoloMsg;
      });
    }
  }

  getError(k:string):string{
    if(this.errors && k in this.errors){
      return this.errors[k];
    }
  }

  protected _initForms(areapersonaleTop = null,areapersonaleBottom = null) {
    this._items.set(
      'areapersonaleTop',
      this._getAreaPersonaleTopForm.bind(this)
    );
    this._items.set(
      'areapersonaleBottom',
      this._getAreaPersonaleBottomForm.bind(this)
    );
  }
 
  protected _getAreaPersonaleTopForm(values = null) {
    const form = new Form({
      header: { show: false },
      filter: true,
      controls: {
        profilo: new SelectInput({
          label: 'DIALOG.AREAPERSONALE.FORMTOP.FIELDS.PROFILE.LABEL',
          placeholder: 'DIALOG.AREAPERSONALE.FORMTOP.FIELDS.PROFILE.PLACEHOLDER',
          options: this.areaPersonaleService.getProfiles(),
          multiple: false,
          size: this.size,
          clearable: false,
          value: (this.areapersonaleTopValues  && this.areapersonaleTopValues.profilo)?this.areapersonaleTopValues.profilo:null,
          valueChange: (value) => {
            if (value) {
              this.areaPersonaleService.changeProfile(value);
            }
          }
        }), 
      }
    });
    return form;
  }
  protected _getAreaPersonaleBottomForm(values = null) {
    const form = new Form({
      header: { show: false },
      filter: true,
      asyncValidator: [
        AreaPersonaleValidator.emailValidator(this.areaPersonaleService)
      ],
      controls: {
        telefono: new TextInput({
          type: 'text',
          label: 'DIALOG.AREAPERSONALE.FORMBOTTOM.FIELDS.NUMBER.LABEL',
          placeholder: 'DIALOG.AREAPERSONALE.FORMBOTTOM.FIELDS.NUMBER.PLACEHOLDER',
          size: this.size,
          clearable: false,
          required: false,
          readonly: this.readOnly,
          value: (this.areapersonaleBottomValues  && this.areapersonaleBottomValues.telefono)?this.areapersonaleBottomValues.telefono:null
        }), 
        email: new TextInput({
          type: 'text',
          label: 'DIALOG.AREAPERSONALE.FORMBOTTOM.FIELDS.EMAIL.LABEL',
          placeholder: 'DIALOG.AREAPERSONALE.FORMBOTTOM.FIELDS.EMAIL.PLACEHOLDER',
          size: this.size,
          clearable: false,
          required: true,
          readonly: this.readOnly,
          value: (this.areapersonaleBottomValues  && this.areapersonaleBottomValues.email)?this.areapersonaleBottomValues.email:null,
          validatorOrOpts: { updateOn: 'blur' }
        }) 
      }
    });
    this.formBottom =form;
    return form;
  }

  public isNotChangedFormBottom(values){
    if(values['telefono']==this.areapersonaleBottomValues['telefono']
      && values['email']==this.areapersonaleBottomValues['email']){
        return true;
    }
    return false;
  }
}

