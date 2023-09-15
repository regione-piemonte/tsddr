import { AfterViewInit, Component, Inject, LOCALE_ID, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { merge } from 'rxjs';
import { map, take } from 'rxjs/operators';
import { I18nService } from '@eng-ds/translate';

import { SecurityService, UtilityService } from '@app/core/services';
import { ListaAbstract } from '../models/abstract-factory/lista-abstract';
import { DichiarazioneLista } from '../models/dichiarazione/dichiarazione-lista';
import { RichiestaLista } from '../models/richiesta/richiesta-lista';
import { MrService } from '../services/mr.service';
import { MrFilterStoreService } from '../services/mr.filter-store.service';
import { LoadingService } from '@theme/layouts/loading.services';
import { ModalService } from '@shared/modal/modal.service';
import { NotificationService } from '@shared/notification/notification.service';
import { InserimentoLista } from '../models/dichiarazione/inserimento-lista';
import { IProfiloACL } from '@app/core/models/acl.model';
import { Form } from '@app/shared/form';
import { IPrevCons } from '../interfaces/prev-cons.interface';

@UntilDestroy()
@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.scss']
})
export class ListaComponent implements OnInit, AfterViewInit {
  @ViewChild('actionsTemplate') actionsTemplate: TemplateRef<any>;
  mr: ListaAbstract;
  acl: IProfiloACL;
  isProfileBO: boolean = false;
  checkSuperAdmn: boolean = false;
  /** 
   * isPreInserimento mi serve per sapere se mi trovo nella pagina di inserimento
   * Dichiarazione in Misura Ridotta in cui visualizzo la lista
   * delle Richieste MR che possono essere legate alla DMR.
   * Questa pagina ha delle regole diverse rispetto alle normali liste.
   * */
  isPreInserimento: boolean = false;

  form: Form = null;

  constructor(
    readonly mrService: MrService,
    readonly utilityService: UtilityService,
    readonly notification: NotificationService,
    readonly filterStoreService: MrFilterStoreService,
    readonly router: Router,
    readonly route: ActivatedRoute,
    readonly securityService: SecurityService,
    readonly loadingService: LoadingService,
    readonly modalService: ModalService,
    readonly i18n: I18nService,
    @Inject(LOCALE_ID) public locale: string
  ) {
    // recupero parametro "page" dal data e so in che pagina mi trovo.
    this.route.data.subscribe(
      res => this.mr = this._createClassFactory(res?.page)
    )
  }

  ngOnInit(): void {
    this.isProfileBO = this.securityService.isProfileBo();
    this._fork().subscribe(res => {
      if('acl' in res) {
        this.acl = res.acl;
      }
      if('filter' in res){
        if(!this.isPreInserimento) {
          if (!res.filter) {
            this.mr.onBack();
            return;
          } else {
            delete res.filter.divider;
            this.mr.setFilter(res.filter);
          }
        } else {
          // nel preinserimento i filtri li prendo dal form
          this.mr.setFilter({});
          this.form = this.mr.initForm();
        }
      }
      if('messageA002' in res) {
        this.mr.initListenOnSize(res.messageA002.content);
      }
      if('profilo' in res) {
        this.checkSuperAdmn = res.profilo.content;
      }
      if('help' in res){
        this.mr.setHelperTitle(res.help.content.testoInfo);
      }
      this.loadingService.hide();
    })
  }

  ngAfterViewInit(): void {
    this.mr.initTable(this.actionsTemplate);
  }

  private _fork() {
    return merge(
      this.mrService.getStoredAcl().pipe(map(res => ({acl : res}))),
      this.filterStoreService.getSearchInput().pipe(map(res => ({filter : res}))),
      this.utilityService.getMessage('A002').pipe(take(1),map(res => ({messageA002 : res}))),
      this.mrService.getProfilo(this.securityService.getIdProfilo()).pipe(map(res => ({profilo : res}))),
      this.utilityService.getNotaInfo('PARAMETRORMR').pipe(map(res => ({help:res}))),
    ).pipe(untilDestroyed(this))

  }
  public downloadPdf(row: IPrevCons): void {
    this.mrService
      .getDichiarazioneDownload(row.idPrevCons, this.mr.idTipoDoc)
      .pipe(untilDestroyed(this))
      .subscribe((response) => {
        if (response.content.file && response.content.filename) {
          const blob = this._b64toBlob(response.content.file, 'application/pdf');
          const blobUrl = URL.createObjectURL(blob);
          const dlink: HTMLAnchorElement = document.createElement('a');
          dlink.download = response.content.filename; // the file name
          dlink.href = blobUrl;
          dlink.click(); // this will trigger the dialog window
          dlink.remove();
        }
        if (response.message) {
          this.notification.info({
            title: response.message.titoloMsg,
            text: response.message.testoMsg
          });
        }
      });
  }

  public _b64toBlob(b64Data, contentType = '', sliceSize = 512): Blob {
    const byteCharacters = atob(b64Data);
    const byteArrays = [];

    for (let offset = 0; offset < byteCharacters.length; offset += sliceSize) {
      const slice = byteCharacters.slice(offset, offset + sliceSize);

      const byteNumbers = new Array(slice.length);
      for (let i = 0; i < slice.length; i++) {
        byteNumbers[i] = slice.charCodeAt(i);
      }

      const byteArray = new Uint8Array(byteNumbers);
      byteArrays.push(byteArray);
    }

    const blob = new Blob(byteArrays, { type: contentType });
    return blob;
  }

  private _createClassFactory(parametro: string): ListaAbstract{
    switch (parametro) {
      case 'richiesta-mr':
        this.isPreInserimento = false;
        return new RichiestaLista(this.mrService, this.router, this.utilityService, this.notification, this.modalService, this.i18n);
      case 'dichiarazioni-mr':
        this.isPreInserimento = false;
        return new DichiarazioneLista(this.mrService, this.router, this.route, this.utilityService, this.notification, this.modalService, this.i18n);
      case 'pre-inserimento':
        this.isPreInserimento = true;
        return new InserimentoLista(this.mrService, this.router,this.route,  this.utilityService, this.notification, this.modalService, this.i18n, this.locale);
    }
  }

}
