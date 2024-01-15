import { Component, Input, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { UtilityService } from '@app/core';
import { I18nService } from '@app/core/translate/public-api';
import { DateInput, Form, TextInput, ValidationStatus } from '@app/shared/form';
import { LoadingService } from '@app/theme/layouts/loading.services';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { forkJoin } from 'rxjs';
import { DatePipe } from '@angular/common';
import {
  DichiarazioneEditingStore,
  Periodo,
  reportsDichiarazioneEditingStore,
  Versamenti,
  Versamento
} from '../../models/dichiarazione.model';
import { DichiarazioneEditingStoreService } from '../../services/dichiarazione-editing-store.service';
import { DichiarazioneService } from '../../services/dichiarazioni.service';

@UntilDestroy()
@Component({
  selector: 'app-dichiarazione-tab-versamenti',
  templateUrl: './dichiarazione-tab-versamenti.component.html',
  styleUrls: ['./dichiarazione-tab-versamenti.component.scss']
})
export class DichiarazioneTabVersamentiComponent implements OnInit {
  @Input() versamenti: Versamenti;
  @Input() isEditMode: boolean = false;
  @Input() keyDichiarazione: string = 'new';

  dichiarazioneEditingStore: DichiarazioneEditingStore;
  periodi: Periodo[];
  form: Form;
  conguaglioMessage: any;
  isReadonly: boolean = false;
  helperTitle: string;
  showHelper: boolean;

  constructor(
    private loadingService: LoadingService,
    private service: DichiarazioneService,
    private editingStoreService: DichiarazioneEditingStoreService,
    private datePipe: DatePipe,
    private utility: UtilityService,
    private i18n: I18nService
  ) {}

  ngOnInit(): void {
    this.showHelper = false;
    this._initHelper();
    forkJoin([
      this.service.getPeriodi().pipe(untilDestroyed(this)),
      this.utility.getMessage('I005').pipe(untilDestroyed(this))
    ]).subscribe((results) => {
      this.periodi = (results[0] as any).content;
      this.conguaglioMessage = (results[1] as any).content;

      this.editingStoreService
        .getStoredDichiarazione(this.keyDichiarazione)
        .subscribe((dichiarazione) => {
          if (dichiarazione.key === this.keyDichiarazione) {
            this._changeDichiarazione(dichiarazione);
          }
        });
      this._initCreateForm();
      this.editingStoreService
        .getStoreReport(this.keyDichiarazione)
        .subscribe((report) => {
          this._changeReport(report);
        });

      this.loadingService.hide();
    });
  }

  private _initHelper(): void {
    this.utility.getNotaInfo('VERSAMENTI').subscribe((result: any) => {
      if (result.content) {
        this.helperTitle = result.content.testoInfo;
      }
    });
  }

  _changeDichiarazione(dichiarazione: DichiarazioneEditingStore) {
    this.dichiarazioneEditingStore = dichiarazione;
    if (dichiarazione && dichiarazione.viewMode) {
      this.isReadonly = true;
    }
    if (dichiarazione.dichiarazione.idDichAnnuale) {
      this.showHelper = true;
    }
  }

  _changeReport(report: reportsDichiarazioneEditingStore) {
    if (this.form) {
      const current: number = +this.form.get('impostaTotale').value;
      if (
        report?.totaleImporto &&
        report?.totaleImporto > 0 &&
        report?.totaleImporto != current
      ) {
        this.form
          .get('impostaTotale')
          .setValue(parseFloat(report.totaleImporto.toFixed(2)));
      }
    }
  }

  getCreateFormControl(k: string, i: number): FormControl {
    return this.form.get(k + '_' + i);
  }

  private _initCreateForm() {
    const size = '12|12|12|12|12';
    let creditoImposta: string = '0';
    if (
      this.dichiarazioneEditingStore?.dichiarazionePrecedente?.creditoImposta
    ) {
      creditoImposta =
        this.dichiarazioneEditingStore.dichiarazionePrecedente.creditoImposta.toString();
    }
    if (this.dichiarazioneEditingStore?.dichiarazione?.versamenti?.creditoAP) {
      creditoImposta =
        this.dichiarazioneEditingStore.dichiarazione.versamenti.creditoAP.toString();
    }

    const totale: string = this.dichiarazioneEditingStore?.dichiarazione
      ?.versamenti?.totale
      ? this.dichiarazioneEditingStore?.dichiarazione?.versamenti?.totale.toString()
      : '0';
    let dataVersamento_0: string = null;
    let dataVersamento_1: string = null;
    let dataVersamento_2: string = null;
    let dataVersamento_3: string = null;
    let dataVersamento_4: string = null;
    let importoVersato_0: string = '0';
    let importoVersato_1: string = '0';
    let importoVersato_2: string = '0';
    let importoVersato_3: string = '0';
    let importoVersato_4: string = '0';
    let impostaTotale: string = '0';
    if (this.dichiarazioneEditingStore.dichiarazione) {
      this.dichiarazioneEditingStore.dichiarazione.versamenti?.versamenti?.forEach(
        (versamento) => {
          const idPeriodo: number = versamento.periodo.idPeriodo;
          if (idPeriodo == 1) {
            dataVersamento_0 = this.datePipe.transform(
              versamento.dataVersamento,
              'yyyy-MM-dd'
            );
            importoVersato_0 = versamento.importoVersato.toString();
          }
          if (idPeriodo == 2) {
            dataVersamento_1 = this.datePipe.transform(
              versamento.dataVersamento,
              'yyyy-MM-dd'
            );
            importoVersato_1 = versamento.importoVersato.toString();
          }
          if (idPeriodo == 3) {
            dataVersamento_2 = this.datePipe.transform(
              versamento.dataVersamento,
              'yyyy-MM-dd'
            );
            importoVersato_2 = versamento.importoVersato.toString();
          }
          if (idPeriodo == 4) {
            dataVersamento_3 = this.datePipe.transform(
              versamento.dataVersamento,
              'yyyy-MM-dd'
            );
            importoVersato_3 = versamento.importoVersato.toString();
          }
          if (idPeriodo == 5) {
            dataVersamento_4 = this.datePipe.transform(
              versamento.dataVersamento,
              'yyyy-MM-dd'
            );
            importoVersato_4 = versamento.importoVersato.toString();
          }
        }
      );

      if (
        this.dichiarazioneEditingStore?.dichiarazione?.rifiutiConferiti?.totali
          ?.totale?.importo
      ) {
        impostaTotale =
          this.dichiarazioneEditingStore?.dichiarazione?.rifiutiConferiti?.totali?.totale?.importo.toString();
      }
    }

    this.form = new Form({
      header: { show: false },
      filter: true,
      validatorOrOpts: [
        this.service.versamentiValidator(),
        this.service.conguaglioValidator(),

      ],
      controls: {
        totale: new TextInput({
          type: 'number',
          value: totale,
          readonly: true
        }),
        dataVersamento_0: new DateInput({
          placeholder: 'DICHIARAZIONI.TABVERSAMENTI.FORM.DATA.PLACEHOLDER',
          size: size,
          clearable: true,
          validatorOrOpts: { updateOn: 'blur' },
          valueChange: (v: string) => {
            console.log(dataVersamento_0);
            this._saveVersamenti();
          },
          value: dataVersamento_0,
          readonly: this.isReadonly
        }),
        dataVersamento_1: new DateInput({
          placeholder: 'DICHIARAZIONI.TABVERSAMENTI.FORM.DATA.PLACEHOLDER',
          size: size,
          clearable: true,
          validatorOrOpts: { updateOn: 'blur' },
          valueChange: (v: string) => {
            this._saveVersamenti();
          },
          value: dataVersamento_1,
          readonly: this.isReadonly
        }),
        dataVersamento_2: new DateInput({
          placeholder: 'DICHIARAZIONI.TABVERSAMENTI.FORM.DATA.PLACEHOLDER',
          size: size,
          clearable: true,
          validatorOrOpts: { updateOn: 'blur' },
          valueChange: (v: string) => {
            this._saveVersamenti();
          },
          value: dataVersamento_2,
          readonly: this.isReadonly
        }),
        dataVersamento_3: new DateInput({
          placeholder: 'DICHIARAZIONI.TABVERSAMENTI.FORM.DATA.PLACEHOLDER',
          size: size,
          clearable: true,
          validatorOrOpts: { updateOn: 'blur' },
          valueChange: (v: string) => {
            this._saveVersamenti();
          },
          value: dataVersamento_3,
          readonly: this.isReadonly
        }),
        dataVersamento_4: new DateInput({
          placeholder: 'DICHIARAZIONI.TABVERSAMENTI.FORM.DATA.PLACEHOLDER',
          size: size,
          clearable: true,
          validatorOrOpts: { updateOn: 'blur' },
          valueChange: (v: string) => {
            this._saveVersamenti();
          },
          value: dataVersamento_4,
          readonly: this.isReadonly
        }),
        importoVersato_0: new TextInput({
          label: 'DICHIARAZIONI.TABVERSAMENTI.FORM.IMPORTO.LABEL',
          placeholder: ' ',
          type: 'number',
          validatorOrOpts: { updateOn: 'blur' },
          valueChange: (v: string) => {
            if (!v) {
              this.form
                .get('importoVersato_0')
                .setValue('0', { emitEvent: false, onlySelf: true });
            }
            this._calculateForm(true);
          },
          value: importoVersato_0,
          readonly: this.isReadonly
        }),
        importoVersato_1: new TextInput({
          label: 'DICHIARAZIONI.TABVERSAMENTI.FORM.IMPORTO.LABEL',
          placeholder: ' ',
          type: 'number',
          value: importoVersato_1,
          validatorOrOpts: { updateOn: 'blur' },
          valueChange: (v: string) => {
            if (!v) {
              this.form
                .get('importoVersato_1')
                .setValue('0', { emitEvent: false, onlySelf: true });
            }
            this._calculateForm(true);
          },
          readonly: this.isReadonly
        }),
        importoVersato_2: new TextInput({
          label: 'DICHIARAZIONI.TABVERSAMENTI.FORM.IMPORTO.LABEL',
          placeholder: ' ',
          type: 'number',
          value: importoVersato_2,
          validatorOrOpts: { updateOn: 'blur' },
          valueChange: (v: string) => {
            if (!v) {
              this.form
                .get('importoVersato_2')
                .setValue('0', { emitEvent: false, onlySelf: true });
            }
            this._calculateForm(true);
          },
          readonly: this.isReadonly
        }),
        importoVersato_3: new TextInput({
          label: 'DICHIARAZIONI.TABVERSAMENTI.FORM.IMPORTO.LABEL',
          placeholder: ' ',
          type: 'number',
          value: importoVersato_3,
          validatorOrOpts: { updateOn: 'blur' },
          valueChange: (v: string) => {
            if (!v) {
              this.form
                .get('importoVersato_3')
                .setValue('0', { emitEvent: false, onlySelf: true });
            }
            this._calculateForm(true);
          },
          readonly: this.isReadonly
        }),
        importoVersato_4: new TextInput({
          label: 'DICHIARAZIONI.TABVERSAMENTI.FORM.IMPORTO.LABEL',
          placeholder: ' ',
          type: 'number',

          value: importoVersato_4,
          // validatorOrOpts: [Validators.pattern('^-?[0-9]+(.[0-9]+)?$')],
          validatorOrOpts: { updateOn: 'blur' },
          valueChange: (v: string) => {
            /* this.form
              .get('importoVersato_4')
              .setValidators([Validators.pattern('^-?[0-9]+(.[0-9]+)?$')]);*/
            if (!v) {
              this.form
                .get('importoVersato_4')
                .setValue('0', { emitEvent: false, onlySelf: true });
            }
            this._calculateForm(true);
          },
          validationStatus: [
            ValidationStatus.ERROR.CUSTOM(
              (control) =>
                control.touched && control.parent?.hasError('conguaglio'),
              {
                text: this.conguaglioMessage.testoMsg
              }
            )
          ],
          readonly: this.isReadonly
        }),
        impostaTotale: new TextInput({
          label: 'DICHIARAZIONI.TABVERSAMENTI.FORM.IMPOSTATOTALE.LABEL',
          placeholder:
            'DICHIARAZIONI.TABVERSAMENTI.FORM.IMPOSTATOTALE.PLACEHOLDER',
          type: 'number',
          value: impostaTotale,
          validatorOrOpts: { updateOn: 'blur' },
          valueChange: (v: string) => {
            this._calculateForm();
          },
          readonly: true
        }),
        importoTotale: new TextInput({
          type: 'number',
          value: '0',
          readonly: true
        }),
        credito: new TextInput({
          type: 'number',
          value: creditoImposta,
          readonly: true
        }),
        importoDebito: new TextInput({
          type: 'number',
          value: '0',
          readonly: true,
          validationStatus: [
            ValidationStatus.ERROR.CUSTOM(
              (control) => control.parent?.hasError('conguaglio'),
              {
                text: this.conguaglioMessage.testoMsg
              }
            )
          ]
        }),
        importoCredito: new TextInput({
          type: 'number',
          value: '0',
          readonly: true
        })
      }
    });

    this._calculateForm(false);

    this.form.valueChanges.pipe(untilDestroyed(this)).subscribe((x) => {
      this.editingStoreService.setStatus(
        'versamentiValid',
        !this.form.hasError('nessunVersamento')
      );
////


//
      this.editingStoreService.setStatus('versamentiChanged', true);
    });
  }

  _calculateForm(save: boolean = false) {
    const importoVersato_0: number = +this.form.get('importoVersato_0').value;
    const importoVersato_1: number = +this.form.get('importoVersato_1').value;
    const importoVersato_2: number = +this.form.get('importoVersato_2').value;
    const importoVersato_3: number = +this.form.get('importoVersato_3').value;
    const importoVersato_4: number = +this.form.get('importoVersato_4').value;
    const impostaTotale: number = +this.form.get('impostaTotale').value;
    const credito: number = +this.form.get('credito').value;
    const importoTotale: number =
      importoVersato_0 +
      importoVersato_1 +
      importoVersato_2 +
      importoVersato_3 +
      importoVersato_4;
    this.form.get('totale').setValue(parseFloat(importoTotale.toFixed(2)));
    this.form
      .get('importoTotale')
      .setValue(parseFloat(importoTotale.toFixed(2)));

    let importoDebito: number = impostaTotale - credito - importoTotale;
    if (importoDebito > -(1 / 100)) {
      this.form
        .get('importoDebito')
        .setValue(parseFloat(importoDebito.toFixed(2)));
      this.form.get('importoCredito').setValue(0);
    } else if (importoDebito <= -(1 / 100)) {
      this.form.get('importoDebito').setValue(0);
      this.form
        .get('importoCredito')
        .setValue(parseFloat(Math.abs(importoDebito).toFixed(2)));
    }
    /*else if(importoDebito>-1 && importoDebito<0){
      this.form.get('importoDebito').setValue(0);
      this.form
        .get('importoCredito')
        .setValue(Math.abs(importoDebito).toFixed(2));
    }*/

    if (save) {
      this._saveVersamenti();
    }
  }

  _saveVersamenti() {
    // modificato per task#9 gitlab: ad ogni cambiamento mando al BE anche i versamenti con data e/o importo null.
    let versamenti: Versamenti = {
      versamenti: []
    };

    this.periodi.forEach((periodo) => {
      const dataVersamento = this.form.get(
        'dataVersamento_' + (periodo.idPeriodo - 1).toString()
      ).value;
      const importoVersato: number = +this.form.get(
        'importoVersato_' + (periodo.idPeriodo - 1).toString()
      ).value;

      let versamento: Versamento = {
        periodo: periodo,
        dataVersamento: dataVersamento,
        importoVersato: importoVersato
      };
      if (
        (versamento.dataVersamento === '' ||
          versamento.dataVersamento === null) &&
        versamento.importoVersato != 0
      ) {

        versamento.dataVersamento = null;
        versamento.importoVersato = 0;


      }

      versamenti.versamenti.push(versamento);

    });

    this.editingStoreService.setVersamenti(versamenti, this.keyDichiarazione);
  }
}
