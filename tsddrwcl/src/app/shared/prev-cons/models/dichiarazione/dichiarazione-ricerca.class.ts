import { Router } from '@angular/router';
import { IProfiloACL } from '@app/core/models/acl.model';
import { IMessageWrapper } from '@app/core/models/shared.model';
import {
  AutocompleteInput,
  Form,
  SelectOption,
  ValidationStatus
} from '@app/shared/form';
import { Observable, of } from 'rxjs';
import { MrService } from '../../services/mr.service';
import { MrFilterStoreService } from '../../services/mr.filter-store.service';
import { IPath } from '../abstract-factory/prev-cons-edit-abstract';
import { IFilterType } from '../richiesta/richiesta-ricerca.class';
import { RicercaAbstract } from './../abstract-factory/ricerca-abstract';
import { ID_TIPO_DOC } from '../constants';
import { tap } from 'rxjs/operators';
import { ICombo } from '../../interfaces/api-mr.model';

export class DichiarazioneRicerca extends RicercaAbstract {

    //Bottone inserimento dich
    public btnNuovaMr: string = 'DICHIARAZIONI_MR.RICERCA.NUOVA_DICHIARAZIONE';
    public ariaLabelPulsante: string = 'Inserisci nuova dichiarazione MR';

    public path: IPath = 'dichiarazioni-mr';
    public helperTitle: string;
    public filter: IFilterType;
    public idTipoDoc: number = ID_TIPO_DOC.DICHIARAZIONE;
    public messageIdDataTesto: string;
    public profiloACL: IProfiloACL;
    private form: Form;

    private comboLocalImpianti: Observable<ICombo>;

    constructor(
        readonly mrService: MrService,
        readonly router: Router,
        readonly storeService: MrFilterStoreService,
    ) {
        super();
    }

    public setHelperTitle(value: string): void {
        this.helperTitle = value;
    }
    public setMessageIdDataTesto(value: IMessageWrapper): void {
      this.messageIdDataTesto = value.content.testoMsg.toString();
    }
    public setProfiloACL(profilo: IProfiloACL): void {
      this.profiloACL = profilo;
    }
    // commentato nel padre, perchè i filtri di dichiarazione rimanevano per richiesta e viceversa.
    public setFilter(filter: IFilterType): void {
      this.filter = filter;
    }
    public setForm(form: Form): void {
      this.form = form;
    }
    public onSearch(): Promise<boolean> {
      this.storeService.setSearchInput(this.form.value);
      return this.router.navigate([this.path, 'lista']);
    }

    onCreate(): void {
      this.router.navigate([this.path, 'pre-inserimento']);
    }
public onDownloadReport(): void {
    this.mrService.downloadReportPrevCons(this.idTipoDoc, null)
}
    public createForm(
        comboGestori: any
      ): Form {
        const size = '12|6|6|6|6';
        this.form = new Form({
          header: { show: false },
          validatorOrOpts: [
            this.mrService.hasValueValidator(),
            this.mrService.dateValidator()
          ],
          filter: true,
          controls: {
            idGestore: new AutocompleteInput({
              label: 'DICHIARAZIONI.RICERCA.FORM.IDGESTORE.LABEL',
              placeholder: 'DICHIARAZIONI.RICERCA.FORM.IDGESTORE.PLACEHOLDER',
              options: of(comboGestori),
              value:
                this.filter && this.filter['idGestore']
                  ? this.filter['idGestore']
                  : null,
              valueChange: (idGestore: string) => {
                let impiantoField = this.form.get('idImpianto') as AutocompleteInput;
                // se idGestore è presente mostro gli impianti associati, altrimenti li mostro tutti
                if (idGestore) {
                  this.comboLocalImpianti = this.mrService.getComboDichiarazioneImpianti(+idGestore).pipe(
                    tap(resp => {
                      //se ho un solo impianto associato a quel gestore, valirozzo il campo idImpianto con quel dato
                      if(resp.content.length === 1 && idGestore){
                        this.form
                        .get('idImpianto')
                        .setValue(resp.content[0].id.toString());
                      }
                    })
                  );
                  impiantoField.setOptions(
                    this.comboLocalImpianti as any);
                } else {
                  impiantoField.setOptions(
                    this.mrService.getComboDichiarazioneImpianti() as any
                  );
                }
                impiantoField.setValue(null);
              },
              size,
              clearable: true
            }),
            idImpianto: new AutocompleteInput({
              label: 'DICHIARAZIONI.RICERCA.FORM.IMPIANTO.LABEL',
              placeholder: 'DICHIARAZIONI.RICERCA.FORM.IMPIANTO.PLACEHOLDER',
              value:
                this.filter && this.filter['idImpianto']
                  ? this.filter['idImpianto']
                  : null,
              options:
                this.filter && this.filter['idGestore']
                  ? (this.mrService.getComboDichiarazioneImpianti(
                      +this.filter['idGestore']
                    ) as any)
                  : (this.mrService.getComboDichiarazioneImpianti() as any),
              size,
              clearable: true
            }),
            annoTributoDal: new AutocompleteInput({
              label: 'DICHIARAZIONI.RICERCA.FORM.ANNODAL.LABEL',
              placeholder: 'DICHIARAZIONI.RICERCA.FORM.ANNODAL.PLACEHOLDER',
              value:
                this.filter && this.filter['annoTributoDal']
                  ? this.filter['annoTributoDal']
                  : null,
              options: this.mrService.getComboDichiarazioniAnni(
                this.idTipoDoc
              ) as any,
              size,
              clearable: true,
              validationStatus: [
                ValidationStatus.ERROR.CUSTOM(
                  (control) =>
                    control.touched && control.parent?.hasError('annoTributoAl'),
                  {
                    text: this.messageIdDataTesto
                  }
                )
              ]
            }),
            annoTributoAl: new AutocompleteInput({
              label: 'DICHIARAZIONI.RICERCA.FORM.ANNOAL.LABEL',
              placeholder: 'DICHIARAZIONI.RICERCA.FORM.ANNOAL.PLACEHOLDER',
              value:
                this.filter && this.filter['annoTributoAl']
                  ? this.filter['annoTributoAl']
                  : null,
              options: this.mrService.getComboDichiarazioniAnni(
                this.idTipoDoc
              ) as any,
              size,
              clearable: true,
              validationStatus: [
                ValidationStatus.ERROR.CUSTOM(
                  (control) =>
                    control.touched && control.parent?.hasError('annoTributoAl'),
                  {
                    text: this.messageIdDataTesto
                  }
                )
              ]
            })
          }
        });

        if(!this.filter || this.filter['idGestore']){
          if(comboGestori && (comboGestori).content && (comboGestori).content.length==1){
              (
                this.form.get('idGestore') as AutocompleteInput
              ).setValue((comboGestori).content[0].id);
          }
       };

       return this.form;
      }

}
