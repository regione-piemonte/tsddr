import { IMessage } from "./shared.model";

/**
 * FIXME: Esternalizzare questo model dove già presente ed usare questo come riferimento.
 *
 * Models uguali trovati nel progetto:
 * AccreditamentoACL, FunzionalitaProfiliACL, DichiarazioniACL,
 * DomandaAccreditamentoACL, GestoriACL, ImpiantiACL,
 * ProfiliACL, UtentiACL, UtentiProfiloACL
 *
 */
export interface IProfiloACL {
  content: {
    idProfilo: number;
    idFunzione: number;
    descFunzione: string;
    delete: boolean;
    insert: boolean;
    read: boolean;
    update: boolean;
    profiloPregresso?:boolean;
  };
  message: IMessage;
}
