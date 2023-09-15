import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UntilDestroy } from '@ngneat/until-destroy';
import { Dichiarazione } from '../../models/dichiarazione.model';
import { Gestore } from '../../models/gestore.model';
import { Impianto } from '@app/pages/impianti/models/impianto.model';

@UntilDestroy()
@Component({
  selector: 'app-dichiarazione-tabs',
  templateUrl: './dichiarazione-tabs.component.html',
  styleUrls: ['./dichiarazione-tabs.component.scss']
})
export class DichiarazioneTabsComponent implements OnInit {
  @Input() dichiarazione: Dichiarazione;
  @Input() gestore: Gestore;
  @Input() impianto: Impianto;
  @Input() isEditMode: boolean = false;
  @Input() keyDichiarazione: string = 'new';

  tabElements:any[]=[{
    id:'gestore',
    title: 'DICHIARAZIONI.TABS.GESTORE',
    link:'/impianti/inserimento/gestore'
  },{
    id:'impianto',
    title:  'DICHIARAZIONI.TABS.IMPIANTO',
    link:'/impianti/inserimento/impianto'
  },{
    id:'rifiuti',
    title: 'DICHIARAZIONI.TABS.RIFIUTI',
    link:'/impianti/inserimento/rifiuti'
  },{
    id:'versamenti',
    title:  'DICHIARAZIONI.TABS.VERSAMENTI',
    link:'/impianti/inserimento/versamenti'
  },{
    id:'soggetti',
    title:  'DICHIARAZIONI.TABS.SOGGETTI',
    link:'/impianti/inserimento/soggetti'
  },{
    id:'sededocumenti',
    title: 'DICHIARAZIONI.TABS.SEDEDOCUMENTI',
    link:'/impianti/inserimento/sededocumenti'
  },{
    id:'annotazioni',
    title:  'DICHIARAZIONI.TABS.ANNOTAZIONI',
    link:'/impianti/inserimento/annotazioni'
  }];
  activeId: string;
  activeElement: string;

  constructor(
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {

    this.route.paramMap.subscribe( paramMap => {
      if(!paramMap.has('tab')){
         this.activeId = 'rifiuti';
      }
      if(this.tabElements.find(i=>i.id==paramMap.get('tab'))){
        this.activeId = paramMap.get('tab');
      }

      this.activeElement = this.tabElements.find(i=>i.id==this.activeId).link;
    })

  }

  onChangeActiveTab(tabElement){
    this.activeElement = tabElement.link;
    this.activeId = tabElement.id;

  }
}
