import { Component, OnInit } from '@angular/core';
import { AutocompleteInput, Form, ValidationStatus } from '@shared/form';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { Router } from '@angular/router';
import { LoadingService } from '@theme/layouts/loading.services';
import { DichiarazioneService } from '@app/pages/dichiarazioni/services/dichiarazioni.service';
import { DichiarazioniACL } from '@pages/dichiarazioni/models/acl.model';
import { DichiarazioniStoreService } from '@app/pages/dichiarazioni/services/dichiarazioni.store';
import { SecurityService, UtilityService } from '@core/services';
import { Observable, forkJoin, of } from 'rxjs';
import { ICombo } from '@app/shared/prev-cons/interfaces/api-mr.model';
import { tap } from 'rxjs/operators';

@UntilDestroy()
@Component({
  selector: 'app-ricerca',
  templateUrl: './ricerca.component.html',
  styleUrls: ['./ricerca.component.scss']
})
export class RicercaComponent implements OnInit {
  acl: DichiarazioniACL;
  helperTitle: string;

  // Form declaration
  searchForm: Form;

  comboGestori:any;
  messageIdDataTesto: string;
  filter: any;
  isProfileBo:boolean;

  private comboLocalImpianti: Observable<ICombo>;

  constructor(
    private service: DichiarazioneService,
    private router: Router,
    private securityService: SecurityService,
    private utility: UtilityService,
    private loadingService: LoadingService,
    private store: DichiarazioniStoreService,
    private utilityService: UtilityService
  ) {}

  ngOnInit(): void {
    this.isProfileBo = this.securityService.isProfileBo();
    this._initACL();
    this._initStore();
    forkJoin([ this.service.getComboDichiarazioneGestori().pipe(
      untilDestroyed(this)
    ), this.utilityService
      .getMessage('E002')
      .pipe(untilDestroyed(this))
    ]).subscribe(results => {
        this.comboGestori = results[0];
        this.messageIdDataTesto = ((results[1] as any).content.testoMsg) as string;
        this._initSearchForm();
    });

    this._initHelper();
    this.loadingService.hide();
  }

  private _initStore() {
    this.store
      .getSearchInput()
      .pipe(untilDestroyed(this))
      .subscribe((filter: any) => {
        this.filter = filter;
      });
  }
  onExportExcelDichRicerca(){
    this.service.downloadReportDichiarazioni()
  }
  onSearch() {
    this.store.setSearchInput(this.searchForm.value);
    this.router.navigate([
      'dichiarazioni-annuali',
      'lista'
    ]);
  }

  onBack() {
    this.router.navigate(['home']);
  }

  onCreate() {
    this.router.navigate([
      'dichiarazioni-annuali',
      'inserimento'
    ]);
  }

  private _initHelper(): void {
    this.utility.getNotaInfo('PARAMETRO').subscribe((result: any) => {
      if (result.content) {
        this.helperTitle = result.content.testoInfo;
      }
    });
  }

  private _initACL(): void {
    this.service
      .getStoredAcl()
      .pipe(untilDestroyed(this))
      .subscribe((acl) => (this.acl = acl));
  }

  private _initSearchForm() {
    const size = '12|6|6|6|6';
    this.searchForm = new Form({
      header: { show: false },
      validatorOrOpts: [
        this.service.searchValidator(),
        this.service.dateValidator(),
      ],
      filter: true,
      controls: {
        idGestore: new AutocompleteInput({
          label: 'DICHIARAZIONI.RICERCA.FORM.IDGESTORE.LABEL',
          placeholder: 'DICHIARAZIONI.RICERCA.FORM.IDGESTORE.PLACEHOLDER',
          options: of(this.comboGestori),
          value: this.filter && this.filter['idGestore']?this.filter['idGestore']:null,
          valueChange: (idGestore: string) => {
            if (idGestore){
              this.comboLocalImpianti = this.service.getComboDichiarazioneImpianti(+idGestore).pipe(
                tap(resp => {
                  //se ho un solo impianto associato a quel gestore, valirozzo il campo idImpianto con quel dato
                  if(resp.content.length === 1 && idGestore){
                    this.searchForm
                    .get('idImpianto')
                    .setValue(resp.content[0].id.toString());
                  }
                })
              );
              (this.searchForm.get('idImpianto') as AutocompleteInput).setOptions(
                this.comboLocalImpianti as any);
            } else {
              (
                this.searchForm.get('idImpianto') as AutocompleteInput
              ).setOptions(
                this.service.getComboDichiarazioneImpianti() as any
              );
            }
            (
              this.searchForm.get('idImpianto') as AutocompleteInput
            ).setValue(null);
          },
          size,
          clearable: true
        }),
        idImpianto: new AutocompleteInput({
          label: 'DICHIARAZIONI.RICERCA.FORM.IMPIANTO.LABEL',
          placeholder: 'DICHIARAZIONI.RICERCA.FORM.IMPIANTO.PLACEHOLDER',
          value: this.filter && this.filter['idImpianto']?this.filter['idImpianto']:null,
          options:  this.filter && this.filter['idGestore']? this.service.getComboDichiarazioneImpianti(+this.filter['idGestore']) as any:( this.service.getComboDichiarazioneImpianti() ) as any,
          size,
          clearable: true
        }),
        annoDal: new AutocompleteInput({
          label: 'DICHIARAZIONI.RICERCA.FORM.ANNODAL.LABEL',
          placeholder: 'DICHIARAZIONI.RICERCA.FORM.ANNODAL.PLACEHOLDER',
          value: this.filter && this.filter['annoDal']?this.filter['annoDal']:null,
          options: this.service.getComboDichiarazioniAnni() as any,
          size,
          clearable: true
        }),
        annoAl: new AutocompleteInput({
          label: 'DICHIARAZIONI.RICERCA.FORM.ANNOAL.LABEL',
          placeholder: 'DICHIARAZIONI.RICERCA.FORM.ANNOAL.PLACEHOLDER',
          value: this.filter && this.filter['annoAl']?this.filter['annoAl']:null,
          options: this.service.getComboDichiarazioniAnni() as any,
          size,
          clearable: true,
          validationStatus: [
            ValidationStatus.ERROR.CUSTOM(
              (control) => control.touched && control.parent?.hasError('annoAl'),
              {
                text: this.messageIdDataTesto
              }
            )
          ],
        })
      }
    });

   if(!this.filter || this.filter['idGestore']){
      if(this.comboGestori && (this.comboGestori).content && (this.comboGestori).content.length==1){
          (
            this.searchForm.get('idGestore') as AutocompleteInput
          ).setValue((this.comboGestori).content[0].id);
      }
   }
  }

}
