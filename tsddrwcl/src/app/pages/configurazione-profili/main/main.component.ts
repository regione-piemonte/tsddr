import {
  AfterViewInit,
  Component,
  OnInit,
  TemplateRef,
  ViewChild
} from '@angular/core';
import { LoadingService } from '@theme/layouts/loading.services';
import { AutocompleteInput, Form } from '@shared/form';
import { ConfigurazioneProfiliService } from '@pages/configurazione-profili/services/configurazione-profili.service';
import { LocalPagedDataSource } from '@shared/table/models/local-paged-data-source';
import { TablePage } from '@shared/table/models/table-page';
import {
  FunzionalitaProfili,
  FunzionalitaProfiliUpdateResponse
} from '@pages/configurazione-profili/models/funzionalita-profilo.model';
import { TableColumn } from '@shared/table/models/column.model';
import { I18nService } from '@eng-ds/translate';
import { FunzionalitaProfiliACL } from '@pages/configurazione-profili/models/acl.model';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { NotificationService } from '@shared/notification/notification.service';
import { of } from 'rxjs';
import { ModalService } from '@shared/modal/modal.service';
import { ConfirmDeleteComponent } from '@pages/configurazione-profili/components/confirm-delete/confirm-delete.component';
import { csiCatchError } from '@core/operators/catch-error.operator';
import { Router } from '@angular/router';
import { ConfirmExitComponent } from '@pages/configurazione-profili/components/confirm-exit/confirm-exit.component';
import { UtilityService } from '@core/services';

@UntilDestroy()
@Component({
  selector: 'app-main',
  templateUrl: './main.component.html'
})
export class MainComponent implements OnInit, AfterViewInit {
  @ViewChild('switchReadTemplate') switchReadTemplate: TemplateRef<any>;
  @ViewChild('switchUpdateTemplate') switchUpdateTemplate: TemplateRef<any>;
  @ViewChild('switchInsertTemplate') switchInsertTemplate: TemplateRef<any>;
  @ViewChild('switchDeleteTemplate') switchDeleteTemplate: TemplateRef<any>;
  @ViewChild('actionsTemplate') actionsTemplate: TemplateRef<any>;
  @ViewChild('descriptionTemplate') descriptionTemplate: TemplateRef<any>;

  acl: FunzionalitaProfiliACL;
  helperTitle: string = 'CONFIGURAZIONE_PROFILI.HELPER.SELECT';
  helperTitleConfig: string = 'CONFIGURAZIONE_PROFILI.HELPER.SELECTED';
  // Unsaved change
  hasUnsavedChange = false;

  // Form declaration
  searchForm: Form;
  createForm: Form;
  profilo: number;
  funzionalita: number;
  createEnabled: boolean = false;

  // Create section
  toCreate: FunzionalitaProfili = {
    delete: false,
    insert: false,
    read: false,
    update: false
  };

  // Table declaration
  dataSource: LocalPagedDataSource<FunzionalitaProfili>;
  columns: TableColumn[] = [];

  constructor(
    private service: ConfigurazioneProfiliService,
    private router: Router,
    private notification: NotificationService,
    private modalService: ModalService,
    private i18n: I18nService,
    private loadingService: LoadingService,
    private utilityService: UtilityService
  ) {}

  ngOnInit(): void {
    this._initHelper();
    this._initACL();
    this._initSearchForm();
    this._initCreateForm();
    this.loadingService.hide();
  }

  ngAfterViewInit() {
    this._initTable();
  }

  create() {
    (this.createForm.get('idFunzione') as AutocompleteInput).setOptions(
      this.service.getComboNuovaFunzionalita(+this.profilo) as any
    );

    if (this.createEnabled) {
      this.loadingService.show();
      const toSave = { ...this.toCreate };
      toSave.idFunzione = +this.createForm.get('idFunzione').value;
      toSave.idProfilo = +this.profilo;

      this.service
        .createFunzionalita(toSave)
        .pipe(csiCatchError(), untilDestroyed(this))
        .subscribe((response) => {
          this.notification.info({
            title: response.message.titoloMsg,
            text: response.message.testoMsg
          });
          this._cleanForm();
          this.loadingService.hide();
          this.dataSource.refresh();
          (
            this.searchForm.get('idFunzionalita') as AutocompleteInput
          ).setOptions(this.service.getComboFunzionalita(this.profilo) as any);
        });
      return;
    }
    this.createEnabled = !this.createEnabled;
  }

  update(row: FunzionalitaProfili) {
    this.loadingService.show();
    this.service
      .updateFunzionalita(row)
      .pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response: FunzionalitaProfiliUpdateResponse) => {
        this.notification.info({
          title: response.message.titoloMsg,
          text: response.message.testoMsg
        });

        this.hasUnsavedChange = false;
        this.loadingService.hide();
        this.dataSource.refresh();
      });
  }

  delete(row: FunzionalitaProfili) {
    this.utilityService.getMessage('A005').subscribe((result: any) => {
      const { content } = result;
      if (content) {
        const dialog = this.modalService.openDialog(ConfirmDeleteComponent, {
          sizeModal: 'xs',
          header: content.titoloMsg,
          showCloseButton: true,
          context: { funzionalitaProfili: row, messageConfirm: content }
        });

        dialog.closed
          .pipe(untilDestroyed(this))
          .subscribe(() => this.dataSource.refresh());
      }
    });
  }

  updateDatasource(profilo: number, funzionalita?: number) {
    if (!this.dataSource) {
      this.dataSource = new LocalPagedDataSource<FunzionalitaProfili>({
        observable: this.service.getFunzionalitaProfili.bind(
          this.service,
          profilo,
          funzionalita
        ),
        tablePage: new TablePage()
      });
      return;
    }

    this.dataSource.setObservable(
      this.service.getFunzionalitaProfili.bind(
        this.service,
        profilo,
        funzionalita
      )
    );

    this.dataSource.refresh();
  }

  onBack() {
    if (this.hasUnsavedChange || this.createEnabled) {
      this.utilityService.getMessage('A001').subscribe((result: any) => {
        const { content } = result;
        if (content) {
          const dialog = this._preventDataLose(content);

          dialog.closed
            .pipe(untilDestroyed(this))
            .subscribe(() => this.router.navigate(['']));
        }
      });
      return;
    }
    this.router.navigate(['home']);
  }

  private _initHelper(): void {
    this.utilityService.getNotaInfo('PROFILO').subscribe((result: any) => {
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

  private _initTable(): void {
    this.columns = [
      {
        prop: 'idFunzione',
        name: this.i18n.translate('CONFIGURAZIONE_PROFILI.TABLE.COLUMNS.ID'),
        cellClass: 'align-middle',
        sortable: false
      },
      {
        prop: 'descFunzione',
        name: this.i18n.translate(
          'CONFIGURAZIONE_PROFILI.TABLE.COLUMNS.FUNZIONALITA'
        ),
        cellClass: 'align-middle min-w-250',
        cellTemplate: this.descriptionTemplate,
        sortable: false
      },
      {
        prop: 'read',
        name: this.i18n.translate('CONFIGURAZIONE_PROFILI.TABLE.COLUMNS.READ'),
        cellClass: 'align-middle min-w-140',
        cellTemplate: this.switchReadTemplate,
        sortable: false
      },
      {
        prop: 'update',
        name: this.i18n.translate(
          'CONFIGURAZIONE_PROFILI.TABLE.COLUMNS.UPDATE'
        ),
        cellClass: 'align-middle min-w-140',
        cellTemplate: this.switchUpdateTemplate,
        sortable: false
      },
      {
        prop: 'insert',
        name: this.i18n.translate(
          'CONFIGURAZIONE_PROFILI.TABLE.COLUMNS.INSERT'
        ),
        cellClass: 'align-middle min-w-140',
        cellTemplate: this.switchInsertTemplate,
        sortable: false
      },
      {
        prop: 'delete',
        name: this.i18n.translate(
          'CONFIGURAZIONE_PROFILI.TABLE.COLUMNS.DELETE'
        ),
        cellClass: 'align-middle min-w-140',
        cellTemplate: this.switchDeleteTemplate,
        sortable: false
      },
      {
        name: this.i18n.translate(
          'CONFIGURAZIONE_PROFILI.TABLE.COLUMNS.AZIONI'
        ),
        cellClass: 'align-middle',
        cellTemplate: this.actionsTemplate,
        sortable: false
      }
    ];
  }

  private _initSearchForm() {
    const size = '12|6|6|6|6';
    this.searchForm = new Form({
      header: { show: false },
      filter: true,
      controls: {
        idProfilo: new AutocompleteInput({
          forceFocus: true,
          label: 'CONFIGURAZIONE_PROFILI.FORM.PROFILO.LABEL',
          placeholder: 'CONFIGURAZIONE_PROFILI.FORM.PROFILO.PLACEHOLDER',
          options: this.service.getComboProfili() as any,
          valueChange: (profilo: number) => {
            if (profilo) {
              this.updateDatasource(profilo);
              this.profilo = profilo;
              this.helperTitle = this.helperTitleConfig;
              if (!this.searchForm.get('idFunzionalita')) {
                this.searchForm.addControlAfter(
                  'idFunzionalita',
                  new AutocompleteInput({
                    label: 'CONFIGURAZIONE_PROFILI.FORM.FUNZIONALITA.LABEL',
                    placeholder:
                      'CONFIGURAZIONE_PROFILI.FORM.FUNZIONALITA.PLACEHOLDER',
                    options: this.service.getComboFunzionalita(+profilo) as any,
                    valueChange: (funzionalita: number) => {
                      if (funzionalita) {
                        this.funzionalita = funzionalita;
                        this.updateDatasource(this.profilo, funzionalita);
                      }
                    },
                    size,
                    clearable: true
                  }),
                  'idProfilo'
                );
              } else {
                (
                  this.searchForm.get('idFunzionalita') as AutocompleteInput
                ).setOptions(
                  this.service.getComboFunzionalita(+profilo) as any
                );
              }
              this.searchForm.get('idFunzionalita').reset();
            }
          },
          size,
          clearable: true
        })
      }
    });
  }

  private _initCreateForm() {
    const size = '12|6|6|6|6';
    this.createForm = new Form({
      header: { show: false },
      filter: true,
      controls: {
        idFunzione: new AutocompleteInput({
          absolute: true,
          label: 'CONFIGURAZIONE_PROFILI.FORM.FUNZIONALITA.LABEL',
          placeholder: 'CONFIGURAZIONE_PROFILI.FORM.FUNZIONALITA.PLACEHOLDER',
          options: of([]) as any,
          size,
          clearable: true
        })
      }
    });
  }

  private _cleanForm() {
    this.toCreate = {
      delete: false,
      insert: false,
      read: false,
      update: false
    };
    this.createEnabled = false;
    this.createForm.reset();
  }

  private _preventDataLose(content: any) {
    const dialog = this.modalService.openDialog(ConfirmExitComponent, {
      sizeModal: 'xs',
      header: content.titoloMsg,
      showCloseButton: true,
      context: { messageConfirm: content }
    });

    return dialog;
  }
}
