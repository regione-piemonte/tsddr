import { Component, Input, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { AutocompleteInput, Form, TextInput } from '@app/shared/form';
import { LoadingService } from '@app/theme/layouts/loading.services';
import { UtilityService } from '@app/core';
import { ModalService } from '@app/shared/modal/modal.service';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { forkJoin, of } from 'rxjs';
import {
  Conferimento,
  Dichiarazione,
  DichiarazioneEditingStore,
  Periodo,
  RiepilogoTotale,
  RifiutiConferiti,
  RifiutoConferito,
  RifiutoTariffa,
  UnitaMisura
} from '../../models/dichiarazione.model';
import { DichiarazioneEditingStoreService } from '../../services/dichiarazione-editing-store.service';
import { DichiarazioneService } from '../../services/dichiarazioni.service';
import { UtentiACL } from '@app/pages/utenti/models/acl.model';
import { IMessage } from '@app/core/models/shared.model';
import { ConfirmDeleteRifiutoConferitoComponent } from '../confirm-delete-rifiuto-conferito/confirm-delete-rifiuto-conferito.component';

@UntilDestroy()
@Component({
  selector: 'app-dichiarazione-tab-rifiuti',
  templateUrl: './dichiarazione-tab-rifiuti.component.html',
  styleUrls: ['./dichiarazione-tab-rifiuti.component.scss']
})
export class DichiarazioneTabRifiutiComponent implements OnInit {
  @Input() isEditMode: boolean = false;
  @Input() keyDichiarazione: string = 'new';

  acl: UtentiACL;

  comboRifiutiTariffaCompleta: any;
  comboRifiutiTariffa: any;

  periodi: Periodo[];
  createForm: Form;
  createFormChanged: boolean = false;
  totalForm: Form;
  updateForms: { id: number; form: Form; changed: boolean; combo: any }[];
  rifiutiTariffa: RifiutoTariffa[];

  dichiarazioneEditingStore: DichiarazioneEditingStore;
  rifiutiConferiti: RifiutiConferiti;
  unitaMisura: UnitaMisura = {
    idUnitaMisura: 1
  };
  idRifiutoConferito: number = 1;
  deleteRifiutoMessage: IMessage;
  showRows: boolean = false;
  isReadonly: boolean = false;
  resetCreateForm = {
    unitamisura: 'Tributo(€/Kg)',
    importo: '0',
    importo_0: '0',
    importo_1: '0',
    importo_2: '0',
    importo_3: '0',
    quantita_0: '0',
    quantita_1: '0',
    quantita_2: '0',
    quantita_3: '0',
    totale_quantita: '0',
    totale_importo: '0',
    rifiutoTariffa: null,
    riduzione: ' '
  };
year: any;

  constructor(
    private loadingService: LoadingService,
    private service: DichiarazioneService,
    private editingStoreService: DichiarazioneEditingStoreService,
    private modalService: ModalService,
    private utilityService: UtilityService
  ) {
    this.updateForms = [];
    this.idRifiutoConferito = 1;

  }

  ngOnInit(): void {
    this._initACL();
    this.editingStoreService
    .getStoredDichiarazione(this.keyDichiarazione).subscribe((data)=>{

this.year= data.dichiarazione.anno
    })

    forkJoin([
      this.service.getRifiutiTariffa(null, this.year),
      this.service.getPeriodi(),
      this.utilityService.getMessage('A005')
    ])
      .pipe(untilDestroyed(this))
      .subscribe((results) => {
        this.rifiutiTariffa = (results[0] as any).content ? (results[0] as any).content : [] ;


        let periodi = (results[1] as any).content;
        periodi.forEach((item, index, object) => {
          if (item.idPeriodo === 5) {
            object.splice(index, 1);
          }
        });

        this.periodi = periodi;
        this._populateComboRifiuti();
        this.editingStoreService
          .getStoredDichiarazione(this.keyDichiarazione)
          .subscribe((dichiarazione) => {
           // console.log(dichiarazione)
            if (dichiarazione && dichiarazione.key === this.keyDichiarazione) {
              this._changeDichiarazione(dichiarazione);
            }
          });

        this.deleteRifiutoMessage = results[2]?.content;
        this._initCreateForm();
        this._calculateTotal();
        this.loadingService.hide();
      });
  }

  _populateComboRifiuti() {
    let items: any[] = [];
    this.rifiutiTariffa.forEach((i) => {
      let _tipologia3;

      if (i.idTipologia3 === undefined) {
        _tipologia3 = '';
      } else {
        _tipologia3 = i.idTipologia3 + ', ';
      }

      items.push({
        id: i.idRifiutoTariffa.toString(),
        value:
          i.idTipoRifiuto +
          ', ' +
          i.idTipologia2 +
          ', ' +
          _tipologia3 +
          i.descrizione,
        descrizione: i.descrizione
      });
    });



    //sort by property description

    items.sort((a, b) => {
      if (a.descrizione < b.descrizione) {
          return -1;
      }
      if (a.descrizione > b.descrizione) {
          return 1;
      }
      return 0;
    });

    this.comboRifiutiTariffaCompleta = {
      content: [...items]
    };
    this.comboRifiutiTariffa = {
      content: [...items]
    };
  }

  _changeDichiarazione(dichiarazione: DichiarazioneEditingStore) {
    if (dichiarazione.dichiarazione) {
      // Controllo dei rifiuti nel caso in cui non siano stati inseriti nel tab rifiuti conferiti
      if (dichiarazione.dichiarazione.rifiutiConferiti?.rifiutiConferiti) {
        this.idRifiutoConferito = dichiarazione.dichiarazione.rifiutiConferiti
          ?.rifiutiConferiti
          ? dichiarazione.dichiarazione.rifiutiConferiti?.rifiutiConferiti
              .length + 1
          : 1;
      }

      if (dichiarazione && dichiarazione.viewMode) {
        this.isReadonly = true;
      }

      this._drawRows(dichiarazione.dichiarazione);

      if (
        dichiarazione.dichiarazione.rifiutiConferiti &&
        dichiarazione.dichiarazione.rifiutiConferiti.rifiutiConferiti
      ) {
        this.showRows = true;

        this.rifiutiConferiti = {
          ...dichiarazione.dichiarazione.rifiutiConferiti
        };
      }
    }
    if (!dichiarazione.viewMode) {
      this._calculateTotal();
    }

    this.dichiarazioneEditingStore = dichiarazione;
  }

  _saveRifiutiConferiti(rifiutiConferiti: RifiutiConferiti) {
    let rifiutiValid =
      rifiutiConferiti.rifiutiConferiti.length > 0 ? true : false;
    let rifiutiChanged = false;
    this.updateForms?.forEach((update) => {
      if (update.changed) {
        rifiutiChanged = true;
      }
      if (update.form.invalid) {
        rifiutiValid = false;
      }
    });
    this.editingStoreService.setStatus('rifiutiValid', rifiutiValid);
    this.editingStoreService.setRifiutiConferiti(
      rifiutiConferiti,
      this.keyDichiarazione
    );
  }

  createRifiutoConferito() {
    if (this.createForm && this.createForm.valid) {
      let conferimenti: Conferimento[] = [];
      // get dalla lista
      let rifiutoTariffa: RifiutoTariffa = this._getRifiutoTariffa(
        this.createForm.get('rifiutoTariffa').value
      );

      let riepilogoTotale: RiepilogoTotale = {
        quantita: +this.createForm.get('totale_quantita').value,
        importo: +this.createForm.get('totale_importo').value
      };
      // aggiungi i conferimenti
      this.periodi.forEach((periodo) => {
        //if(+this.createForm.get('quantita_'+(periodo.idPeriodo-1).toString()).value>0){
        let conferimento = {
          periodo: periodo,
          quantita: +this.createForm.get(
            'quantita_' + (periodo.idPeriodo - 1).toString()
          ).value,
          importo: +this.createForm.get(
            'importo_' + (periodo.idPeriodo - 1).toString()
          ).value,
          rifiutoTariffa: rifiutoTariffa,
          unitaMisura: this.unitaMisura,
          anno: this.dichiarazioneEditingStore?.dichiarazione?.anno
        };

        conferimenti.push(conferimento);
        //}
      });

      let rifiutoConferito: RifiutoConferito = {
        rifiutoTariffa: rifiutoTariffa,
        conferimenti: conferimenti,
        totale: riepilogoTotale,
        idRifiutoConferito: this.idRifiutoConferito++,
        isRam: true
      };
      if (!this.rifiutiConferiti) {
        this.rifiutiConferiti = { rifiutiConferiti: [] };
      }
      let rifiutiConferiti = { ...this.rifiutiConferiti };
      rifiutiConferiti.rifiutiConferiti.push(rifiutoConferito);
      this._removeRifiutoTariffa(
        rifiutoConferito.rifiutoTariffa.idRifiutoTariffa
      );

      this._saveRifiutiConferiti(rifiutiConferiti);
      this._cleanCreateForm();
    }
  }

  getIdConferimento(rifiutoConferito: RifiutoConferito, periodo: Periodo) {
    let current = rifiutoConferito.conferimenti.find(
      (i) => i.periodo.idPeriodo == periodo.idPeriodo
    );
    if (current?.idConferimento) {
      return current.idConferimento;
    } else {
      return null;
    }
  }

  updateRifiutoConferito(form: Form, rifiutoConferito: RifiutoConferito) {
    let conferimenti: Conferimento[] = [];
    // get dalla lista
    let rifiutoTariffa: RifiutoTariffa = this._getRifiutoTariffa(
      form.get('rifiutoTariffa').value
    );

    let riepilogoTotale: RiepilogoTotale = {
      quantita: +form.get('totale_quantita').value,
      importo: +form.get('totale_importo').value
    };

    this.periodi.forEach((periodo) => {
      //if(+this.createForm.get('quantita_'+(periodo.idPeriodo-1).toString()).value>0){
      let conferimento: Conferimento = {
        periodo: periodo,
        quantita: +this.getUpdateFormControl(
          rifiutoConferito,
          'quantita_' + (periodo.idPeriodo - 1).toString()
        ).value,
        importo: +this.getUpdateFormControl(
          rifiutoConferito,
          'importo_' + (periodo.idPeriodo - 1).toString()
        ).value,
        rifiutoTariffa: rifiutoTariffa,
        unitaMisura: this.unitaMisura,
        anno: this.dichiarazioneEditingStore.dichiarazione.anno
      };
      if (this.getIdConferimento(rifiutoConferito, periodo)) {
        conferimento.idConferimento = this.getIdConferimento(
          rifiutoConferito,
          periodo
        );
      }
      conferimenti.push(conferimento);
      //}
    });

    let rifiutoConferitoSave: RifiutoConferito = {
      rifiutoTariffa: rifiutoTariffa,
      conferimenti: conferimenti,
      totale: riepilogoTotale,
      unitaMisura: this.unitaMisura,
      idRifiutoConferito: rifiutoConferito.idRifiutoConferito,
      isRam: rifiutoConferito.isRam ?? false
    };
    let rf = [
      ...this.dichiarazioneEditingStore.dichiarazione.rifiutiConferiti
        .rifiutiConferiti
    ];
    let current = rf.find(
      (i) => i.idRifiutoConferito == rifiutoConferito.idRifiutoConferito
    );

    const newRF = rf.map((obj) => {
      if (obj.idRifiutoConferito === rifiutoConferito.idRifiutoConferito) {
        return { ...obj, ...rifiutoConferitoSave };
      }
      return obj;
    });

    let rifiutiConferiti = {
      ...this.dichiarazioneEditingStore.dichiarazione.rifiutiConferiti,
      rifiutiConferiti: newRF
    };

    this._addRifiutoTariffa(current.rifiutoTariffa.idRifiutoTariffa);
    this._removeRifiutoTariffa(
      rifiutoConferitoSave.rifiutoTariffa.idRifiutoTariffa
    );
    this._saveRifiutiConferiti(rifiutiConferiti);
  }

  onDeleteRifiutoConferito(rifiutoConferito: RifiutoConferito): void {
    // modale di conferma eliminazione.
    //se il rifiuto è presente a DB viene chiamato il servizio di eliminazione al suo interno
    const dialog = this.modalService.openDialog(
      ConfirmDeleteRifiutoConferitoComponent,
      {
        sizeModal: 'xs',
        header: this.deleteRifiutoMessage?.titoloMsg,
        showCloseButton: true,
        context: {
          idDichAnnuale:
            this.dichiarazioneEditingStore.dichiarazione.idDichAnnuale,
          rifiuto: rifiutoConferito,
          messageConfirm: this.deleteRifiutoMessage
        }
      }
    );
    // azioni effettuate se l'utente clicca "si" nella modale
    dialog.closed.pipe(untilDestroyed(this)).subscribe(() => {
      //elimino il rifiuto dalla dichiarazione che sto aggiornando
      this.dichiarazioneEditingStore.dichiarazione.rifiutiConferiti.rifiutiConferiti =
        this.dichiarazioneEditingStore.dichiarazione.rifiutiConferiti.rifiutiConferiti.filter(
          (rifiuto) =>
            rifiuto.idRifiutoConferito !== rifiutoConferito.idRifiutoConferito
        );

      // reinserisco il record nella combobox per la creazione di un rifiuto
      this._addRifiutoTariffa(rifiutoConferito.rifiutoTariffa.idRifiutoTariffa);

      // Aggiorno lo stato del tab (rifiutiChanged e rifiutiValid) e la dichiarazione editing
      // questo forza il refresh del tab con i dati aggiornati
      this._saveRifiutiConferiti(
        this.dichiarazioneEditingStore.dichiarazione.rifiutiConferiti
      );
    });
  }

  _removeRifiutoTariffa(idRifiutoTariffa: number) {
    this.comboRifiutiTariffa.content.forEach((item, index, object) => {
      if (item.id === idRifiutoTariffa.toString()) {
        object.splice(index, 1);
      }
    });
  }

  _addRifiutoTariffa(idRifiutoTariffa: number) {
    this.comboRifiutiTariffa.content.push(
      this.comboRifiutiTariffaCompleta.content.find(
        (i) => i.id == idRifiutoTariffa
      )
    );
  }

  getUpdateFormControl(
    rifiutoConferito: RifiutoConferito,
    name: string,
    i: number = null
  ): FormControl {
    if (i || i == 0) {
      name += '_' + i.toString();
    }
    return this.updateForms
      .find((i) => i.id == rifiutoConferito.idRifiutoConferito)
      ?.form.get(name);
  }

  getUpdateForm(rifiutoConferito: RifiutoConferito): Form {
    return this.updateForms.find(
      (i) => i.id == rifiutoConferito.idRifiutoConferito
    )
      ? this.updateForms.find(
          (i) => i.id == rifiutoConferito.idRifiutoConferito
        ).form
      : null;
  }

  hasUnsavedChangeRow(rifiutoConferito: RifiutoConferito): boolean {
    return this.updateForms.find(
      (i) => i.id == rifiutoConferito.idRifiutoConferito
    ).changed;
  }

  setUpdateFormChanged(rifiutoConferito: RifiutoConferito): void {
    this.updateForms.find(
      (i) => i.id == rifiutoConferito.idRifiutoConferito
    ).changed = true;
  }

  getCreateFormControl(k: string, i: number): FormControl {
    return this.createForm.get(k + '_' + i);
  }

  getTotalFormControl(k: string, i: number): FormControl {
    return this.totalForm.get(k + '_' + i);
  }

  getComboRifiutiTariffa(idRifiutoTariffa: string = null) {
    const items = [...this.comboRifiutiTariffa.content];
    let _tipologia3;
    if (idRifiutoTariffa) {
      const rt = this._getRifiutoTariffa(idRifiutoTariffa);
      if (rt?.idTipologia3 === undefined) {
        _tipologia3 = '';
      } else {
        _tipologia3 = rt.idTipologia3 + ', ';
      }

      items.push({
        id: rt?.idRifiutoTariffa.toString(),
        value:
          rt?.idTipoRifiuto +
          ', ' +
          rt?.idTipologia2 +
          ', ' +
          _tipologia3 +
          rt?.descrizione,
        descrizione: rt?.descrizione
      });
    }
    //items.sort((a, b) => (a.idRifiutoTariffa < b.idRifiutoTariffa ? -1 : 1));
    items.sort((a, b) => a.descrizione < b.descrizione ? -1 : a.descrizione > b.descrizione ? 1 : 0);
    return of({ content: items });
  }

  blurEvent(f: Form) {
    this._calculateForm(f);
  }

  _drawRows(dichiarazione: Dichiarazione) {
    this.showRows = false;
    this.updateForms = [];
    const size = '12|6|6|6|6';
    let idRifiutoConferitoIncr = 1;

    if (dichiarazione.rifiutiConferiti?.rifiutiConferiti === undefined) {
      return;
    } else {
      dichiarazione.rifiutiConferiti?.rifiutiConferiti.forEach(
        (rifiutoConferito) => {
          // nel caso in cui arrivano dal BE devo toglierle al volo
          if (rifiutoConferito.rifiutoTariffa?.idRifiutoTariffa) {
            this._removeRifiutoTariffa(
              rifiutoConferito.rifiutoTariffa.idRifiutoTariffa
            );
          }
          let f = new Form({
            header: { show: false },
            filter: true,
            validatorOrOpts: [this.service.rifiutoConferitoValidator()],
            controls: {
              unitaMisura: new TextInput({
                label:
                  'DICHIARAZIONI.CREATECONFERIMENTO.FORM.UNITAMISURA.LABEL',
                placeholder:
                  'DICHIARAZIONI.CREATECONFERIMENTO.FORM.UNITAMISURA.PLACEHOLDER',
                type: 'text',
                value: 'Tributo(€/Kg)',
                required: true,
                readonly: true,
                validatorOrOpts: { updateOn: 'blur' }
              }),
              importo: new TextInput({
                label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.IMPORTO.LABEL',
                placeholder:
                  'DICHIARAZIONI.CREATECONFERIMENTO.FORM.IMPORTO.PLACEHOLDER',
                type: 'text',
                value: rifiutoConferito.rifiutoTariffa.importo.toString(),
                required: true,
                readonly: true,
                validatorOrOpts: { updateOn: 'blur' }
              }),
              rifiutoTariffa: new AutocompleteInput({
                label:
                  'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIFIUTOTARIFFA.LABEL',
                placeholder:
                  'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIFIUTOTARIFFA.PLACEHOLDER',
                options: of() as any,
                required: true,
                value:
                  rifiutoConferito.rifiutoTariffa.idRifiutoTariffa.toString(),
                valueChange: (idRifiutoTariffa: string) => {
                  if (idRifiutoTariffa) {
                    this._onChangeRifiutoTariffa(
                      this.getUpdateForm(rifiutoConferito),
                      idRifiutoTariffa
                    );
                    this._calculateForm(this.getUpdateForm(rifiutoConferito));
                  }
                },
                size,
                clearable: false,
                readonly: this.isReadonly,
                validatorOrOpts: { updateOn: 'blur' }
              }),
              riduzione: new TextInput({
                label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
                placeholder:
                  'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
                type: 'text',
                value: rifiutoConferito.rifiutoTariffa.flagRiduzione
                  ? 'Presente'
                  : ' ',
                required: true,
                readonly: true,
                validatorOrOpts: { updateOn: 'blur' }
              }),
              quantita_0: new TextInput({
                label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
                placeholder:
                  'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
                type: 'number',
                value: parseFloat(
                  rifiutoConferito.conferimenti[0]?.quantita.toFixed(4)
                ).toString(),
                required: true,
                readonly: this.isReadonly,

                validatorOrOpts: { updateOn: 'blur' }
              }),
              quantita_1: new TextInput({
                label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
                placeholder:
                  'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
                type: 'number',
                value: parseFloat(
                  rifiutoConferito.conferimenti[1]?.quantita.toFixed(4)
                ).toString(),
                required: true,
                readonly: this.isReadonly,
                validatorOrOpts: { updateOn: 'blur' }
              }),
              quantita_2: new TextInput({
                label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
                placeholder:
                  'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
                type: 'number',
                value: parseFloat(
                  rifiutoConferito.conferimenti[2]?.quantita.toFixed(4)
                ).toString(),
                required: true,
                readonly: this.isReadonly,
                validatorOrOpts: { updateOn: 'blur' }
              }),
              quantita_3: new TextInput({
                label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
                placeholder:
                  'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
                type: 'number',
                value: parseFloat(
                  rifiutoConferito.conferimenti[3]?.quantita.toFixed(4)
                ).toString(),
                required: true,
                readonly: this.isReadonly,
                validatorOrOpts: { updateOn: 'blur' }
              }),
              importo_0: new TextInput({
                label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
                placeholder:
                  'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
                type: 'number',
                value: parseFloat(
                  rifiutoConferito.conferimenti[0]?.importo.toFixed(2)
                ).toString(),
                required: true,
                readonly: true,
                validatorOrOpts: { updateOn: 'blur' }
              }),
              importo_1: new TextInput({
                label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
                placeholder:
                  'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
                type: 'number',
                value: parseFloat(
                  rifiutoConferito.conferimenti[1]?.importo.toFixed(2)
                ).toString(),
                required: true,
                readonly: true,
                validatorOrOpts: { updateOn: 'blur' }
              }),
              importo_2: new TextInput({
                label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
                placeholder:
                  'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
                type: 'number',
                value: parseFloat(
                  rifiutoConferito.conferimenti[2]?.importo.toFixed(2)
                ).toString(),
                required: true,
                readonly: true,
                validatorOrOpts: { updateOn: 'blur' }
              }),
              importo_3: new TextInput({
                label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
                placeholder:
                  'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
                type: 'number',
                value: parseFloat(
                  rifiutoConferito.conferimenti[3]?.importo.toFixed(2)
                ).toString(),
                required: true,
                readonly: true,
                validatorOrOpts: { updateOn: 'blur' }
              }),
              totale_quantita: new TextInput({
                label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
                placeholder:
                  'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
                type: 'number',
                value:
                  rifiutoConferito.totale && rifiutoConferito.totale.quantita
                    ? parseFloat(
                        rifiutoConferito.totale.quantita.toFixed(4)
                      ).toString()
                    : '0',
                required: true,
                readonly: true,
                validatorOrOpts: { updateOn: 'blur' }
              }),
              totale_importo: new TextInput({
                label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
                placeholder:
                  'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
                type: 'number',
                value:
                  rifiutoConferito.totale && rifiutoConferito.totale.importo
                    ? parseFloat(
                        rifiutoConferito.totale.importo.toFixed(2)
                      ).toString()
                    : '0',
                valueChange: (v: string) => {
                  this.updateRifiutoConferito(
                    this.getUpdateForm(rifiutoConferito),
                    rifiutoConferito
                  );
                },
                required: true,
                readonly: true,
                validatorOrOpts: { updateOn: 'blur' }
              })
            }
          });

          const idRifiutoConferito = rifiutoConferito.idRifiutoConferito
            ? rifiutoConferito.idRifiutoConferito
            : idRifiutoConferitoIncr++;
          rifiutoConferito.idRifiutoConferito = idRifiutoConferito;

          this.updateForms.push({
            id: idRifiutoConferito,
            form: f,
            changed: false,
            combo: null
          });

          (
            this.getUpdateForm(rifiutoConferito).get(
              'rifiutoTariffa'
            ) as AutocompleteInput
          ).setOptions(
            this.getComboRifiutiTariffa(
              rifiutoConferito.rifiutoTariffa.idRifiutoTariffa.toString()
            ) as any
          );

          this.getUpdateForm(rifiutoConferito)
            .valueChanges.pipe(untilDestroyed(this))
            .subscribe((x) => {
              this.setUpdateFormChanged(rifiutoConferito);
            });
        }
      );
    }
    if (this.createForm) {
      (this.createForm.get('rifiutoTariffa') as AutocompleteInput).setOptions(
        this.getComboRifiutiTariffa() as any
      );
    }
  }

  _onChangeRifiutoTariffa(form: Form, idRifiutoTariffa: string) {
    const rt: RifiutoTariffa = this.rifiutiTariffa.find(
      (i) => i.idRifiutoTariffa == +idRifiutoTariffa
    );
    form.get('riduzione').setValue(rt.flagRiduzione ? 'Presente' : ' ');
    form.get('importo').setValue(rt.importo);
  }
  _getRifiutoTariffa(idRifiutoTariffa: string) {
    return this.rifiutiTariffa.find(
      (i) => i.idRifiutoTariffa == +idRifiutoTariffa
    );
  }

  private _initCreateForm() {
    const size = '12|6|6|6|6';
    this.createForm = new Form({
      header: { show: false },
      filter: true,
      validatorOrOpts: [this.service.rifiutoConferitoValidator()],
      controls: {
        unitaMisura: new TextInput({
          label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.UNITAMISURA.LABEL',
          placeholder:
            'DICHIARAZIONI.CREATECONFERIMENTO.FORM.UNITAMISURA.PLACEHOLDER',
          type: 'text',
          value: '',
          required: true,
          readonly: true
        }),
        importo: new TextInput({
          label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.IMPORTO.LABEL',
          placeholder:
            'DICHIARAZIONI.CREATECONFERIMENTO.FORM.IMPORTO.PLACEHOLDER',
          type: 'text',
          value: '0.00',
          required: true,
          readonly: true
        }),
        rifiutoTariffa: new AutocompleteInput({
          label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIFIUTOTARIFFA.LABEL',
          placeholder:
            'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIFIUTOTARIFFA.PLACEHOLDER',
          options: this.getComboRifiutiTariffa() as any,
          required: true,
          valueChange: (idRifiutoTariffa: string) => {
            if (idRifiutoTariffa) {
              this._onChangeRifiutoTariffa(this.createForm, idRifiutoTariffa);
              this._calculateForm(this.createForm);
            }
          },
          size,
          clearable: true,
          readonly: this.isReadonly
        }),
        riduzione: new TextInput({
          label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
          placeholder:
            'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
          type: 'text',
          value: ' ',
          required: true,
          readonly: true
        }),
        quantita_0: new TextInput({
          label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
          placeholder:
            'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
          type: 'number',
          value: '0',
          required: true,
          readonly: this.isReadonly,
          validatorOrOpts: { updateOn: 'blur' }
        }),
        quantita_1: new TextInput({
          label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
          placeholder:
            'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
          type: 'number',
          value: '0',
          required: true,
          readonly: this.isReadonly,
          validatorOrOpts: { updateOn: 'blur' }
        }),
        quantita_2: new TextInput({
          label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
          placeholder:
            'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
          type: 'number',
          value: '0',
          required: true,
          readonly: this.isReadonly,
          validatorOrOpts: { updateOn: 'blur' }
        }),
        quantita_3: new TextInput({
          label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
          placeholder:
            'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
          type: 'number',
          value: '0',
          required: true,
          readonly: this.isReadonly,
          validatorOrOpts: { updateOn: 'blur' }
        }),
        importo_0: new TextInput({
          label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
          placeholder:
            'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
          type: 'number',
          value: '0',
          required: true,
          readonly: true
        }),
        importo_1: new TextInput({
          label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
          placeholder:
            'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
          type: 'number',
          value: '0',
          required: true,
          readonly: true
        }),
        importo_2: new TextInput({
          label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
          placeholder:
            'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
          type: 'number',
          value: '0',
          required: true,
          readonly: true
        }),
        importo_3: new TextInput({
          label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
          placeholder:
            'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
          type: 'number',
          value: '0',
          required: true,
          readonly: true
        }),
        totale_quantita: new TextInput({
          label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
          placeholder:
            'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
          type: 'number',
          value: '0',
          required: true,
          readonly: true
        }),
        totale_importo: new TextInput({
          label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
          placeholder:
            'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
          type: 'number',
          value: '0',
          required: true,
          readonly: true
        })
      }
    });

    (this.createForm.get('rifiutoTariffa') as AutocompleteInput).setOptions(
      this.getComboRifiutiTariffa() as any
    );

    this.totalForm = new Form({
      header: { show: false },
      filter: true,
      controls: {
        quantita_0: new TextInput({
          label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
          placeholder:
            'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
          type: 'number',
          value: '0',
          required: true,
          readonly: true
        }),
        quantita_1: new TextInput({
          label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
          placeholder:
            'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
          type: 'number',
          value: '0',
          required: true,
          readonly: true
        }),
        quantita_2: new TextInput({
          label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
          placeholder:
            'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
          type: 'number',
          value: '0',
          required: true,
          readonly: true
        }),
        quantita_3: new TextInput({
          label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
          placeholder:
            'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
          type: 'number',
          value: '0',
          required: true,
          readonly: true
        }),
        importo_0: new TextInput({
          label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
          placeholder:
            'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
          type: 'number',
          value: '0',
          required: true,
          readonly: true
        }),
        importo_1: new TextInput({
          label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
          placeholder:
            'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
          type: 'number',
          value: '0',
          required: true,
          readonly: true
        }),
        importo_2: new TextInput({
          label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
          placeholder:
            'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
          type: 'number',
          value: '0',
          required: true,
          readonly: true
        }),
        importo_3: new TextInput({
          label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
          placeholder:
            'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
          type: 'number',
          value: '0',
          required: true,
          readonly: true
        }),
        totale_quantita: new TextInput({
          label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
          placeholder:
            'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
          type: 'number',
          value: '0',
          required: true,
          readonly: true
        }),
        totale_importo: new TextInput({
          label: 'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.LABEL',
          placeholder:
            'DICHIARAZIONI.CREATECONFERIMENTO.FORM.RIDUZIONE.PLACEHOLDER',
          type: 'number',
          value: '0',
          required: true,
          readonly: true
        })
      }
    });

    if (
      this.dichiarazioneEditingStore?.dichiarazione?.rifiutiConferiti?.totali
    ) {
      const importo_0: number =
        this.dichiarazioneEditingStore?.dichiarazione?.rifiutiConferiti?.totali
          .totaliPeriodi[0]?.totale.importo;
      const importo_1: number =
        this.dichiarazioneEditingStore?.dichiarazione?.rifiutiConferiti?.totali
          .totaliPeriodi[1]?.totale.importo;
      const importo_2: number =
        this.dichiarazioneEditingStore?.dichiarazione?.rifiutiConferiti?.totali
          .totaliPeriodi[2]?.totale.importo;
      const importo_3: number =
        this.dichiarazioneEditingStore?.dichiarazione?.rifiutiConferiti?.totali
          .totaliPeriodi[3]?.totale.importo;

      const quantita_0: number =
        this.dichiarazioneEditingStore?.dichiarazione?.rifiutiConferiti.totali
          .totaliPeriodi[0].totale.quantita;
      const quantita_1: number =
        this.dichiarazioneEditingStore?.dichiarazione?.rifiutiConferiti.totali
          .totaliPeriodi[1].totale.quantita;
      const quantita_2: number =
        this.dichiarazioneEditingStore?.dichiarazione?.rifiutiConferiti.totali
          .totaliPeriodi[2].totale.quantita;
      const quantita_3: number =
        this.dichiarazioneEditingStore?.dichiarazione?.rifiutiConferiti.totali
          .totaliPeriodi[3].totale.quantita;

      const totale_importo: number =
        this.dichiarazioneEditingStore?.dichiarazione?.rifiutiConferiti.totali
          .totale.importo;
      const totale_quantita: number =
        this.dichiarazioneEditingStore?.dichiarazione?.rifiutiConferiti.totali
          .totale.quantita;
      this.totalForm
        .get('importo_0')
        .setValue(parseFloat(importo_0.toFixed(2)));
      this.totalForm
        .get('importo_1')
        .setValue(parseFloat(importo_1.toFixed(2)));
      this.totalForm
        .get('importo_2')
        .setValue(parseFloat(importo_2.toFixed(2)));
      this.totalForm
        .get('importo_3')
        .setValue(parseFloat(importo_3.toFixed(2)));
      this.totalForm
        .get('quantita_0')
        .setValue(parseFloat(quantita_0.toFixed(4)));
      this.totalForm
        .get('quantita_1')
        .setValue(parseFloat(quantita_1.toFixed(4)));
      this.totalForm
        .get('quantita_2')
        .setValue(parseFloat(quantita_2.toFixed(4)));
      this.totalForm
        .get('quantita_3')
        .setValue(parseFloat(quantita_3.toFixed(4)));
      this.totalForm
        .get('totale_quantita')
        .setValue(parseFloat(totale_quantita.toString()));
      this.totalForm
        .get('totale_importo')
        .setValue(parseFloat(totale_importo.toString()));
    }

    this.createForm.valueChanges.subscribe((x) => {
      this.createFormChanged = true;
      this.editingStoreService.setStatus('rifiutiChanged', true);
    });
  }
  private _cleanCreateForm() {
    for (let key in this.resetCreateForm) {
      let value = this.resetCreateForm[key];
      if (this.createForm.get(key)) {
        this.createForm.get(key).setValue(value);
      }
    }
    this.createFormChanged = false;
  }
  _calculateForm(form: Form) {
    if (form) {
      const importo = form.get('rifiutoTariffa').value
        ? +form.get('importo').value
        : 0;
      const quantita_0: number = +form.get('quantita_0').value;
      const quantita_1: number = +form.get('quantita_1').value;
      const quantita_2: number = +form.get('quantita_2').value;
      const quantita_3: number = +form.get('quantita_3').value;
      form
        .get('importo_0')
        .setValue(
          parseFloat((+form.get('quantita_0').value * importo).toFixed(2))
        );
      form
        .get('importo_1')
        .setValue(
          parseFloat((+form.get('quantita_1').value * importo).toFixed(2))
        );
      form
        .get('importo_2')
        .setValue(
          parseFloat((+form.get('quantita_2').value * importo).toFixed(2))
        );
      form
        .get('importo_3')
        .setValue(
          parseFloat((+form.get('quantita_3').value * importo).toFixed(2))
        );
      const importo_0: number = +form.get('importo_0').value;
      const importo_1: number = +form.get('importo_1').value;
      const importo_2: number = +form.get('importo_2').value;
      const importo_3: number = +form.get('importo_3').value;
      const totale_quantita: number =
        quantita_0 + quantita_1 + quantita_2 + quantita_3;
      const totale_importo: number =
        importo_0 + importo_1 + importo_2 + importo_3;
      form
        .get('totale_quantita')
        .setValue(parseFloat(totale_quantita.toFixed(4).toString()));
      form
        .get('totale_importo')
        .setValue(parseFloat(totale_importo.toFixed(2)));
      if (this.createForm != form) {
        this._calculateTotal();
      }
    }
  }

  _calculateTotal() {
    let importo_0 = 0;
    let importo_1 = 0;
    let importo_2 = 0;
    let importo_3 = 0;
    let quantita_0 = 0;
    let quantita_1 = 0;
    let quantita_2 = 0;
    let quantita_3 = 0;
    let totale_quantita: number = 0;
    let totale_importo: number = 0;

    this.updateForms.forEach((item) => {
      importo_0 += +item.form.get('importo_0').value;
      importo_1 += +item.form.get('importo_1').value;
      importo_2 += +item.form.get('importo_2').value;
      importo_3 += +item.form.get('importo_3').value;
      quantita_0 += +item.form.get('quantita_0').value;
      quantita_1 += +item.form.get('quantita_1').value;
      quantita_2 += +item.form.get('quantita_2').value;
      quantita_3 += +item.form.get('quantita_3').value;
      totale_quantita += +item.form.get('totale_quantita').value;
      totale_importo += +item.form.get('totale_importo').value;
    });
    if (this.totalForm) {
      this.totalForm
        .get('importo_0')
        .setValue(parseFloat(importo_0.toFixed(2)));
      this.totalForm
        .get('importo_1')
        .setValue(parseFloat(importo_1.toFixed(2)));
      this.totalForm
        .get('importo_2')
        .setValue(parseFloat(importo_2.toFixed(2)));
      this.totalForm
        .get('importo_3')
        .setValue(parseFloat(importo_3.toFixed(2)));
      this.totalForm
        .get('quantita_0')
        .setValue(parseFloat(quantita_0.toFixed(4)));
      this.totalForm
        .get('quantita_1')
        .setValue(parseFloat(quantita_1.toFixed(4)));
      this.totalForm
        .get('quantita_2')
        .setValue(parseFloat(quantita_2.toFixed(4)));
      this.totalForm
        .get('quantita_3')
        .setValue(parseFloat(quantita_3.toFixed(4)));
      this.totalForm
        .get('totale_quantita')
        .setValue(parseFloat(totale_quantita.toFixed(4)));
      this.totalForm
        .get('totale_importo')
        .setValue(parseFloat(totale_importo.toFixed(2)));
      this.editingStoreService.setReport(totale_importo, this.keyDichiarazione);
    }
  }

  private _initACL(): void {
    this.service
      .getStoredAcl()
      .pipe(untilDestroyed(this))
      .subscribe((acl) => (this.acl = acl));
  }
}
