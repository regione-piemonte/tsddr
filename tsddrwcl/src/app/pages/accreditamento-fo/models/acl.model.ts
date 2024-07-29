export interface AccreditamentoACL {
  message: any;
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
}
