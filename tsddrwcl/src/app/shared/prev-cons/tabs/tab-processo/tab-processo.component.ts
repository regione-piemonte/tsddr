import { Component, Input, OnInit, AfterViewInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UtilityService } from '@app/core';
import { IMessage } from '@app/core/models/shared.model';
import { DichiarazioniACL } from '@app/pages/dichiarazioni/models/acl.model';
import { AutocompleteInput, SelectInput, Form, TextInput } from '@app/shared/form';
import { ModalService } from '@app/shared/modal/modal.service';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { map, switchMap, toArray } from 'rxjs/operators';
import {
  IPrevConsLineeEntity,
  IRifiutiObject
} from '../../interfaces/prev-cons.interface';
import { PrevConsClass } from '../../models/classes/prev-cons.class';
import { MrService } from '../../services/mr.service';
import { PrevConsLineeClass } from '../../models/classes/prev-cons-linee.class';
import { ConfirmLineaDeleteComponent } from '../../modals/confirm-linea-delete/confirm-linea-delete.component';
import { SEZIONE } from '../../models/constants';
import { ActionType } from '../sotto-tabs/tab-rifiuti/tab-rifiuti.component';
import { MrStoreService } from '../../services/mr-store.service';
import { forkJoin } from 'rxjs';

export interface ITabElem {
  id: string;
  title: string;
  link: string;
}

export type TypeMessages = Record<string, IMessage>;

@UntilDestroy()
@Component({
  selector: 'app-tab-processo',
  templateUrl: './tab-processo.component.html',
  styleUrls: ['./tab-processo.component.scss']
})
export class TabProcessoComponent implements OnInit, AfterViewInit {
  @Input() isEditMode: boolean = false;
  @Input() idImpianto: string | number;
  @Input() prevCons: PrevConsClass;

  helperTitle: string;
  form: Form;

  checkPercRecuperoVisible = false;
  checkPercScartoVisible = false;

  lineeFiltered: any;
  found: any;

  tabElements: ITabElem[] = [
    {
      id: 'descrizione',
      title: 'DICHIARAZIONI_MR.TABS.PROCESSO.DESCRIZIONE.TAB',
      link: '/processo-impiantistico/descrizione-sommaria'
    },
    {
      id: 'rii',
      title: 'DICHIARAZIONI_MR.TABS.PROCESSO.RIFIUTI_INGRESSO',
      link: '/processo-impiantistico/rif-ingresso'
    },
    {
      id: 'mat',
      title: 'DICHIARAZIONI_MR.TABS.PROCESSO.MATERIALE',
      link: '/processo-impiantistico/materiale-uscita'
    },
    {
      id: 'rru',
      title: 'DICHIARAZIONI_MR.TABS.PROCESSO.RIFIUTI_RECUPERABILI',
      link: '/processo-impiantistico/rif-recuperabili'
    },
    {
      id: 'ru',
      title: 'DICHIARAZIONI_MR.TABS.PROCESSO.RIFIUTI_USCITA',
      link: '/processo-impiantistico/rif-uscita'
    }
  ];
  activeElement: string;
  activeId: string;

  acl: DichiarazioniACL;

  idPrevCons: number;
  checkIdPrevCons: number;
  selectedPrevConsLinea: IPrevConsLineeEntity;
  prevConsLineeClass: PrevConsLineeClass;
  selectedTabObject: IRifiutiObject;
  messages: TypeMessages;

  isLegameRmr: boolean = false;

  percRecupero: number;
  percScarto: number;
  totRii: number;
  totMat: number;
  totRru: number;
  totRu: number;

  constructor(
    private utility: UtilityService,
    private mrService: MrService,
    private mrStoreService: MrStoreService,
    private modalService: ModalService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this._initHelper();
    this._initACL();

    forkJoin([
      this.utility.getMessage('A021'),
      this.utility.getMessage('A013'),
      this.utility.getMessage('A020'),
      this.utility.getMessage('P001')
    ])
      .pipe(
        switchMap((lista) => lista),
        map((mes) => mes.content),
        toArray(),
        untilDestroyed(this)
      )
      .subscribe(
        ([
          deleteLineaMessage,
          deleteDettMessage,
          validationMessage,
          confirmDataChange
        ]) => {
          this.messages = {
            deleteLineaMessage,
            deleteDettMessage,
            validationMessage,
            confirmDataChange
          };
        }
      );

    this.route.paramMap.subscribe((paramMap) => {
      if (!paramMap.has('tab')) {
        this.activeId = 'descrizione';
      }
      if (this.tabElements.find((i) => i.id == paramMap.get('tab'))) {
        this.activeId = paramMap.get('tab');
      }

      this.activeElement = this.tabElements.find(
        (i) => i.id == this.activeId
      ).link;
    });

    if (this.prevCons.statoDichiarazione?.idStatoDichiarazione === 2) {
      this.checkIdPrevCons = this.prevCons.idPrevCons;
    }

    //valirizzo subito una linea selezionata, al primo atterraggio sarà la prima che mi arriva
    this.selectedPrevConsLinea = this._createPrevConsLinea();

    this._setValue();
    this._setPercentuali();
    this._initForm();
    this._setPopulatedLines();
    this.isLegameRmr = !!this.prevCons.idPrevConsRMr;
    this._checkStatus();

  }



	ngAfterViewInit():void{

		//console.log(this.found);
 		//this.form.get('linee')?.setValue(this.found?.idImpiantoLinea)
	 setTimeout(()=>{


	    this.form.get('linee')?.setValue(this.found?.id);
	    console.log('found ' , this.found);

	},500

)

	}

  /**
   * @description al cambiamento del tab, aggiorno la proprietà da mandare in input al figlio
   */
  onChangeActiveTab(tabElement: ITabElem, changeLinea: boolean = false) {
    this.activeElement = tabElement.link;
    this.activeId = tabElement.id;

    if (this.selectedTabObject?.prevConsDett?.length && !changeLinea) {
      let condition = this.selectedTabObject.prevConsDett[0].sezione.idSezione;
      switch (condition) {
        case SEZIONE.ID_TAB_RII:
          this.selectedPrevConsLinea.dettRii =
            this.selectedTabObject.prevConsDett;
          this.totRii = this.selectedTabObject.totValue;
          break;
        case SEZIONE.ID_TAB_MAT:
          this.selectedPrevConsLinea.dettMat =
            this.selectedTabObject.prevConsDett;
          this.totMat = this.selectedTabObject.totValue;
          break;
        case SEZIONE.ID_TAB_RRU:
          this.selectedPrevConsLinea.dettRru =
            this.selectedTabObject.prevConsDett;
          this.totRru = this.selectedTabObject.totValue;
          break;
        case SEZIONE.ID_TAB_RU:
          this.selectedPrevConsLinea.dettRu =
            this.selectedTabObject.prevConsDett;
          this.totRu = this.selectedTabObject.totValue;
          break;
      }
    }

    switch (tabElement.id) {
      case 'rii':
        this.selectedTabObject = {
          totValue: this.totRii,
          prevConsDett: this.selectedPrevConsLinea?.dettRii ?? []
        };
        break;
      case 'mat':
        this.selectedTabObject = {
          totValue: this.totMat,
          prevConsDett: this.selectedPrevConsLinea?.dettMat ?? []
        };
        break;
      case 'rru':
        this.selectedTabObject = {
          totValue: this.totRru,
          prevConsDett: this.selectedPrevConsLinea?.dettRru ?? []
        };
        break;
      case 'ru':
        this.selectedTabObject = {
          totValue: this.totRu,
          prevConsDett: this.selectedPrevConsLinea?.dettRu ?? []
        };
        break;
    }
    /////
    //////
    this._setValue();
    this._setPercentuali();

    //
  }

  onDeleteLinea() {
    const dialog = this.modalService.openDialog(ConfirmLineaDeleteComponent, {
      sizeModal: 'xs',
      header: this.messages?.deleteLineaMessage?.titoloMsg,
      showCloseButton: true,
      context: {
        selectedLinea: this.selectedPrevConsLinea,
        messageConfirm: this.messages?.deleteLineaMessage
      }
    });

    dialog.closed.pipe(untilDestroyed(this)).subscribe(() => {
      /*
        1-passo il valore della linea da svuotare al metodo removePrevConsLinee
        2-aggiorno il valore di this.prevCons.prevConsLinee con il nuovo array con la linea selezionata svuotata
        3-aggiorno this.selectedPrevConsLinea con il nuovo valore della linea appena svuotata + rimango su quella linea
        4-aggiotno il this.selectedTabObject con i dati aggiornati della linea da mostrare
      */
      let updatedPrevConsLinee = this.prevCons.removePrevConsLinee(
        this.selectedPrevConsLinea,
        this.prevCons.prevConsLinee
      );
      this.prevCons.prevConsLinee = updatedPrevConsLinee;
      this.selectedPrevConsLinea =
        this.prevConsLineeClass.setSelectedPrevConsLinea(
          this.prevCons.prevConsLinee,
          this.selectedPrevConsLinea.idImpiantoLinea
        );
      this.selectedTabObject = this.selectedPrevConsLinea;

      this._setValue();
      this._setPercentuali();
      this._checkStatus();
      this._setPopulatedLines();
      // mi sposto sul tab descrizione
      this.onChangeActiveTab(
        this.tabElements.find((element) => element.id === 'descrizione')
      );
      this.mrStoreService.setPropsStatus('processoChanged', true);
    });
  }

  /**
   * @description ascolta l'evento emesso dal figlio
   */
  listenerAction(actionType?: ActionType) {
    let condition = this.selectedTabObject?.prevConsDett[0]?.sezione.idSezione;
    switch (condition) {
      case SEZIONE.ID_TAB_RII:
        this.selectedPrevConsLinea.dettRii =
          this.selectedTabObject.prevConsDett;
        this.selectedPrevConsLinea.totRii = this.selectedTabObject.totValue;
        this.totRii = this.selectedPrevConsLinea.totRii;
        break;
      case SEZIONE.ID_TAB_MAT:
        this.selectedPrevConsLinea.dettMat =
          this.selectedTabObject.prevConsDett;
        this.selectedPrevConsLinea.totMat = this.selectedTabObject.totValue;
        this.totMat = this.selectedPrevConsLinea.totMat;
        break;
      case SEZIONE.ID_TAB_RRU:
        this.selectedPrevConsLinea.dettRru =
          this.selectedTabObject.prevConsDett;
        this.selectedPrevConsLinea.totRru = this.selectedTabObject.totValue;
        this.totRru = this.selectedPrevConsLinea.totRru;
        break;
      case SEZIONE.ID_TAB_RU:
        this.selectedPrevConsLinea.dettRu = this.selectedTabObject.prevConsDett;
        this.selectedPrevConsLinea.totRu = this.selectedTabObject.totValue;
        this.totRu = this.selectedPrevConsLinea.totRu;
        break;
      default:
        let dettArray =
          this.activeId === 'rii'
            ? 'dettRii'
            : this.activeId === 'mat'
            ? 'dettMat'
            : this.activeId === 'rru'
            ? 'dettRru'
            : 'dettRu';
        let dettTot =
          this.activeId === 'rii'
            ? 'totRii'
            : this.activeId === 'mat'
            ? 'totMat'
            : this.activeId === 'rru'
            ? 'totRru'
            : 'totRu';
        this.selectedPrevConsLinea[`${dettArray}`] = [];
        this.selectedPrevConsLinea[`${dettTot}`] = 0;
        this[`${dettTot}`] = this.selectedPrevConsLinea[`${dettTot}`];
        break;
    }
    this._setPercentuali();
    this._setPopulatedLines();
    this._checkStatus();
  }

  private _createPrevConsLinea(): IPrevConsLineeEntity {
    this.prevConsLineeClass = new PrevConsLineeClass();
    return this.prevConsLineeClass.setSelectedPrevConsLinea(
      this.prevCons.prevConsLinee
    );
  }

  /**
   * @description aggiorna il calcolo delle percentuali
   */
	private _setPercentuali() {
		this.mrService
			.getPercRecuperoVisible(this.selectedPrevConsLinea.codLinea?this.selectedPrevConsLinea.codLinea:this.selectedPrevConsLinea.codSottoLinea, this.prevCons.annoTributo)
			.subscribe((response: any) => {
				this.checkPercRecuperoVisible = response.content;
				if (this.checkPercRecuperoVisible) {
					if (this.totRii === 0 || null) {
						this.selectedPrevConsLinea.percRecupero = 0;
					} else {
						this.selectedPrevConsLinea.percRecupero =
							(this.totMat + this.totRru) / this.totRii;
					}
				} else {
					this.selectedPrevConsLinea.percRecupero = null;
				}
				this.percRecupero = this.selectedPrevConsLinea.percRecupero;
			});


		this.mrService
			.getPercScartoVisible(this.selectedPrevConsLinea.codLinea?this.selectedPrevConsLinea.codLinea:this.selectedPrevConsLinea.codSottoLinea, this.prevCons.annoTributo)
			.subscribe((response: any) => {
				this.checkPercScartoVisible = response.content;
				if (this.checkPercScartoVisible) {
					if (this.totRii === 0 || null) {
						this.selectedPrevConsLinea.percScarto = 0;
					} else {
						this.selectedPrevConsLinea.percScarto = this.totRu / this.totRii;
					}
				} else {
					this.selectedPrevConsLinea.percScarto = null;
				}
				this.percScarto = this.selectedPrevConsLinea.percScarto;
			});


  }

  /**
   * @description setta il valore iniziale dei totali e delle percentuali
   */
  private _setValue() {
    this.percRecupero = this.selectedPrevConsLinea?.percRecupero ?? 0;
    this.percScarto = this.selectedPrevConsLinea?.percScarto ?? 0;
    this.totRii = this.selectedPrevConsLinea?.totRii ?? 0;
    this.totMat = this.selectedPrevConsLinea?.totMat ?? 0;
    this.totRru = this.selectedPrevConsLinea?.totRru ?? 0;
    this.totRu = this.selectedPrevConsLinea?.totRu ?? 0;
  }

  private _initForm() {
    const size = '12|6|6|6|6';
    const sizeM = '10|4|4|4|4';
    this.form = new Form({
      header: { show: false },
      filter: false,
      controls: {
        linee: new SelectInput({
          label: 'DICHIARAZIONI_MR.TABS.PROCESSO.FORM.LINEE',
          placeholder: 'DICHIARAZIONI_MR.TABS.PROCESSO.FORM.LINEE',
          options: this._getLineeOptions() as any,
          value: this.selectedPrevConsLinea?.idImpiantoLinea,
          size,
       
          required: false,
          clearable: false,
          readonly: false,
          valueChange: (selectedLine) => {
            //passo il valore selezionato idImpiantoLinea al mio subject per aggiornarlo + naviga sulla pagina descrizione sommaria
            this.selectedPrevConsLinea =
              this.prevConsLineeClass.setSelectedPrevConsLinea(
                this.prevCons.prevConsLinee,
                +selectedLine
              );
            this.selectedTabObject = this.selectedPrevConsLinea;
            this.onChangeActiveTab(
              this.tabElements.find((element) => element.id === 'descrizione'),
              true
            );
          }
        }),
        lineeCompilate: new TextInput({
          label: 'DICHIARAZIONI_MR.TABS.PROCESSO.FORM.LINEE_COMPILATE',
          placeholder: ' ',
          type: 'text',
          size: sizeM,
          clearable: true,
          required: false,
          readonly: true
        })
      }
    });



  }

  private _initHelper(): void {
    this.utility
      .getNotaInfo('SELE-LINEA')
      .pipe(untilDestroyed(this))
      .subscribe((result: any) => {
        if (result.content) {
          this.helperTitle = result.content.testoInfo;
        }
      });
  }

  private _initACL(): void {
    this.mrService
      .getStoredAcl()
      .pipe(untilDestroyed(this))
      .subscribe((acl) => (this.acl = acl));
  }

  /**
   * @description controlla che in ogni prevConsDett inserita ci sia almeno un valore 'quantita', per abilitare l'invio
   */
  private _checkStatus(): void {
    let check = this.prevCons.prevConsLinee.some(
      (linea) =>
        !!linea.totMat || !!linea.totRii || !!linea.totRru || !!linea.totRu
    );

    this.mrStoreService.setPropsStatus('processoValid', check);
  }

  private _getLineeOptions() {
    let linee: IPrevConsLineeEntity[] = this.prevCons.prevConsRichiesta
      ? this.prevCons.prevConsLinee
      : null;
    return this.mrService
      .getLineeObject(this.idImpianto.toString(), linee, this.checkIdPrevCons)
      .pipe(
        untilDestroyed(this),
        map((res) => {
          //elimino gli undefined
          //console.log(res);
		  let newArray = [];
          res.forEach((line: any, index) => {
			  //console.log('line=', line);
			  if (!(line.value === undefined)) {
				newArray.push(line);
			  }
          });
          res = newArray;
          this.lineeFiltered = newArray;
          console.log(newArray);

          this.found = this.lineeFiltered[0];

          console.log('1 ',this.found);
          return {
            content: res
          };
        })
      );
  }

  private _setPopulatedLines(): void {
    let lineePopolate: string[] = this.prevCons.prevConsLinee?.map((item) => {
      if (!!item.totMat || !!item.totRii || !!item.totRru || !!item.totRu) {
        return item.codLinea ? item.codLinea : item.codSottoLinea;
      }
      return '';
    });
    this.form
      ?.get('lineeCompilate')
      .setValue(lineePopolate.filter((linea) => !!linea).join('; '));
  }
}
