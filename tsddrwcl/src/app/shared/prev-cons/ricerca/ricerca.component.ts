import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SecurityService, UtilityService } from '@app/core/services';
import { Form } from '@app/shared/form';
import { LoadingService } from '@app/theme/layouts/loading.services';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { merge } from 'rxjs';
import { map } from 'rxjs/operators';
import { RicercaAbstract } from '../models/abstract-factory/ricerca-abstract';
import { DichiarazioneRicerca } from '../models/dichiarazione/dichiarazione-ricerca.class';
import { RicercaRichiesta } from '../models/richiesta/richiesta-ricerca.class';
import { MrService } from '../services/mr.service';
import { MrFilterStoreService } from '../services/mr.filter-store.service';

@UntilDestroy()
@Component({
  selector: 'app-ricerca',
  templateUrl: './ricerca.component.html',
  styleUrls: ['./ricerca.component.scss']
})
export class RicercaComponent implements OnInit {

  mr: RicercaAbstract;
  form!: Form;
  isProfileBO = false;

  constructor(
    readonly mrService: MrService,
    readonly utilityService: UtilityService,
    readonly filterStoreService: MrFilterStoreService,
    readonly router: Router,
    readonly route: ActivatedRoute,
    readonly securityService: SecurityService,
    readonly loadingService: LoadingService,
  ) {
    // recupero parametro "page" dal data e so in che pagina mi trovo.
    this.route.data.subscribe(
      res => this.mr = this._createClassFactory(res?.page)
    )
  }

  ngOnInit(): void {
   this.isProfileBO = this.securityService.isProfileBo();
   this._fork().subscribe((res) => {
    if('help' in res){
      this.mr.setHelperTitle(res.help.content.testoInfo);
    }
    if('acl' in res){
      this.mr.setProfiloACL(res.acl);
    }
    // commentato perchè i filtri di dichiarazione venivano già impostati per richiesta e viceversa.
    // if('filter' in res){
    //   this.mr.setFilter(res.filter);
    // }
    if('combo' in res){
      //TODO: tipizza combo con ICombo
      this.form = this.mr.createForm(res.combo);
      this.mr.setForm(this.form);
    }
    if('msg' in res){
      this.mr.setMessageIdDataTesto(res.msg)
    }
    this.loadingService.hide();
   })
  }

  public onBack(): Promise<boolean> {
    return this.router.navigate(['home']);
  }
  
  private _fork(){
    return merge(
      this.utilityService.getMessage('E002').pipe(map(res => ({msg:res}))),
      this.utilityService.getNotaInfo('PARAMETRO').pipe(map(res => ({help:res}))),
      this.mrService.getStoredAcl().pipe(map(res => ({acl:res}))),
      //commentato perchè i filtri di dichiarazione venivano già impostati per richiesta e viceversa.
      //this.filterStoreService.getSearchInput().pipe(map(res => ({filter:res}))),
      this.mrService.getComboDichiarazioneGestori().pipe(map(res => ({combo:res}))),
    ).pipe(
      untilDestroyed(this)
    )
  }

  private _createClassFactory(parametro: string): RicercaAbstract{
    switch (parametro) {
      case 'richiesta-mr':
        return new RicercaRichiesta(this.mrService, this.router, this.filterStoreService);
      case 'dichiarazioni-mr':
        return new DichiarazioneRicerca(this.mrService, this.router, this.filterStoreService);
    }
  }

}
