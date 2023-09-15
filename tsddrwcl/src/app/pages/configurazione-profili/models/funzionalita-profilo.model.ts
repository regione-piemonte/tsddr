export interface FunzionalitaProfiliResponse {
  message: any;
  content: FunzionalitaProfili[];
}

export interface FunzionalitaProfili {
  idProfilo?: number;
  idFunzione?: number;
  descFunzione?: string;
  delete?: boolean;
  insert?: boolean;
  read?: boolean;
  update?: boolean;
}

export interface FunzionalitaProfiliUpdateResponse {
  content: FunzionalitaProfili;
  message: {
    campo: string;
    codMsg: string;
    descTipoMsg: string;
    testoMsg: string;
    titoloMsg: string;
  };
}

export interface FunzionalitaProfiliDeleteResponse {
  content: FunzionalitaProfili;
  message: {
    campo: string;
    codMsg: string;
    descTipoMsg: string;
    testoMsg: string;
    titoloMsg: string;
  };
}
