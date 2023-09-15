import { backendApi } from '../config/backend-api.conf';

export const environment: any = {
  production: true,
  iconBaseUrl: '/tsddr',
  // il logout chiama prima il logout del BE widfly poi alla risposta effettua la logout sso alla url qui configurate
  auth: {
    ssoLogoutBO: 'https://ts-tsddr.ruparpiemonte.it/ts_tsddr_rup_443sliv1irup/Shibboleth.sso/Logout',
    ssoLogoutFO: 'https://ts-tsddr.regione.piemonte.it/ts_tsddr_int_443s_liv3_sispliv1spid_gasp_regione/logout.do'
  },
  fakeAuth: {
    enabled: true,
    header: {
      key: 'Shib-Iride-IdentitaDigitale',
      value:
    //    'KKNDNG98D28E960U/Simone/Coletti/Engineering/20201203143417/16/72Fm0h3s08aROqA0Up6IMg=='
    'KKNDNG98D28E960U/Gianluca/Sinigaglia/Engineering/20201203143417/16/72Fm0h3s08aROqA0Up6IMg=='

  }
  },
  backend: {
    environment: 'DEV',
    baseUrl: '/tsddr/rest',
    api: backendApi,
    // corrisponde a utent non accreditato
    profilesEditAreaPersonalDisabled: [4]
  },
  logger: {
    level: 0, // all log
    hasRemote: false,
    remoteLogUrl: null,
    cache: false
  }
};
