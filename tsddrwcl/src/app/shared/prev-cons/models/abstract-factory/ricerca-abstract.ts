import { IProfiloACL } from "@app/core/models/acl.model";
import { IMessageWrapper } from "@app/core/models/shared.model";
import { Form } from "@app/shared/form";
import { IFilterType } from "../richiesta/richiesta-ricerca.class";
import { IPath } from "./prev-cons-edit-abstract";

export abstract class RicercaAbstract {

    public abstract idTipoDoc: number;
    public abstract helperTitle: string;
    public abstract messageIdDataTesto: string;
    public abstract filter: IFilterType;
    public abstract profiloACL: IProfiloACL;
    public abstract ariaLabelPulsante: string;
    public abstract btnNuovaMr: string;
    public abstract path: IPath;
    public abstract setForm(form: Form): void;
    public abstract createForm(combo: any, searchForm?: Form): Form;
    public abstract setHelperTitle(value: string): void;
    public abstract setProfiloACL(profilo: IProfiloACL): void;
    public abstract setFilter(filter: IFilterType): void;
    public abstract setMessageIdDataTesto(value: IMessageWrapper): void;
    public abstract onSearch(): Promise<boolean>;
    public abstract onCreate(): void;
    public abstract onDownloadReport(): void;
}
