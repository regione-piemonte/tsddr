import { PrevConsClass } from './../models/classes/prev-cons.class';
import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Gestore } from '@app/pages/dichiarazioni/models/gestore.model';
import { Impianto } from '@app/pages/impianti/models/impianto.model';
import { ID_TIPO_DOC } from '../models/constants';
import { MrStoreService } from '../services/mr-store.service';

@Component({
  selector: 'app-prev-cons-tabs',
  templateUrl: './tabs.component.html',
  styleUrls: ['./tabs.component.scss']
})
export class TabsComponent implements OnInit, OnChanges {

  @Input() gestore: Gestore;
  @Input() impianto: Impianto;
  @Input() isEditMode: boolean = false;
  @Input() idTipoDoc: number;
  @Input() prevCons: PrevConsClass;

  tabElements:any[]=[{
    id:'gestore',
    title: 'DICHIARAZIONI.TABS.GESTORE',
    link:'/impianti/inserimento/gestore'
  },{
    id:'impianto',
    title:  'DICHIARAZIONI.TABS.IMPIANTO',
    link:'/impianti/inserimento/impianto'
  },{
    id:'richiesta',
    title: 'DICHIARAZIONI_MR.TABS.RICHIESTA.TITLE',
    link:'/impianti/inserimento/richiesta-ammissione'
  },{
    id:'processo',
    title:  'DICHIARAZIONI_MR.TABS.PROCESSO.TITLE',
    link:'/impianti/inserimento/processo-impiantitico'
  },{
      id:'sede',
      title:  'DICHIARAZIONI.TABS.SEDEDOCUMENTI',
      link:'/impianti/inserimento/sede-documenti'
  },];

  activeId: string;
  activeElement: string;

  constructor(
    private route: ActivatedRoute,
    private mrStoreService: MrStoreService
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe( paramMap => {
      if(!paramMap.has('tab')){
        if(this.idTipoDoc == ID_TIPO_DOC.DICHIARAZIONE){
        //se è dichiarazione va in richiesta
         this.activeId = 'richiesta';
        } else if (this.idTipoDoc == ID_TIPO_DOC.RICHIESTA){
          //se è richiesta va su gestore
          this.activeId = 'gestore';
        }
      }
      if(this.tabElements.find(i=>i.id==paramMap.get('tab'))){
        this.activeId = paramMap.get('tab');
      }

      this.activeElement = this.tabElements.find(i=>i.id==this.activeId).link;
    });

    this.mrStoreService.resetStatus();
    this.checkStatus();
  }

  ngOnChanges(changes: SimpleChanges){
    const { prevCons } = changes;
    if(prevCons?.currentValue){
      if(this.idTipoDoc == ID_TIPO_DOC.DICHIARAZIONE){
        //se è dichiarazione va in richiesta
         this.activeId = 'richiesta';
        } else if (this.idTipoDoc == ID_TIPO_DOC.RICHIESTA){
          //se è richiesta va su gestore
          this.activeId = 'gestore';
        }
        this.activeElement = this.tabElements.find(i=>i.id==this.activeId).link;
        this.checkStatus();
    }
  }

  onChangeActiveTab(tabElement){
    this.activeElement = tabElement.link;
    this.activeId = tabElement.id;
  }

  /**
   * @description controlla subito la validità delle sezioni di ogni tab e valorizza lo status
   */
  checkStatus(){

    let checkProcesso : boolean = this.prevCons.prevConsLinee.some(linea =>!!linea.totMat || !!linea.totRii || !!linea.totRru || !!linea.totRu);
    let checkRichiesta : boolean = (this.prevCons.modalita && this.prevCons.dataInvioDoc) ? true : false;
    let checkSede : boolean = (this.prevCons.indirizzo?.indirizzo && this.prevCons.indirizzo?.comune && this.prevCons.indirizzo?.comune.idComune && this.prevCons.indirizzo?.sedime && this.prevCons.indirizzo.cap && this.prevCons.indirizzo.comune.provincia) ? true : false;
console.log(checkProcesso, checkRichiesta, checkSede)
    this.mrStoreService.setPropsStatus('processoValid', checkProcesso);
    this.mrStoreService.setPropsStatus('richiestaValid', checkRichiesta);
    this.mrStoreService.setPropsStatus('sedeValid', checkSede);
  }

}
