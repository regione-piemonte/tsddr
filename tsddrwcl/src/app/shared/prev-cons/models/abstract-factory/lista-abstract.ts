import { IProfiloACL } from '@app/core/models/acl.model';
import { LocalPagedDataSource } from '@shared/table/models/local-paged-data-source';
import { TableColumn } from '@shared/table/models/column.model';
import { IMessage } from '@app/core/models/shared.model';
import { TemplateRef } from '@angular/core';
import { Form } from '@app/shared/form';
import { IPrevCons } from '../../interfaces/prev-cons.interface';

export abstract class ListaAbstract {
  // Table declaration
  public abstract dataSource: LocalPagedDataSource<IProfiloACL>;
  public abstract columns: TableColumn[];
  public abstract filter: any;
  public abstract filtro: string;
  public abstract isProfileBo: boolean;
  public abstract checkSuperAdmn: boolean;
  public abstract btnNuovaMr: string;
  public abstract labelShow: string;
  public abstract labelDownload: string;
  public abstract labelDelete: string;
  public abstract idTipoDoc: number;

  public abstract onBack(): Promise<boolean>;
  public abstract onCreate(): Promise<boolean>;
  public abstract detail(row: IPrevCons): Promise<boolean>;
  public abstract delete(row: IPrevCons): void;
  public abstract setFilter(filter: any): void;
  public abstract updateDatasource(filter: any): void;
  public abstract initParametriFiltro(filter: any): void;
  public abstract setHelperTitle(value: string): void;
  /**
   * @description messaggio da far apparire se non trovo risultati a BE
   */
  public abstract initListenOnSize(messageA002: IMessage): void;
  public abstract initTable(actionsTemplate: TemplateRef<any>,pregressoTemplate?: TemplateRef<any>): void;
  public abstract onDowloadReport(): void;
  public abstract initForm(): Form;
}
