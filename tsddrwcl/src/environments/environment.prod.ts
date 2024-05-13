import { backendApi } from '../config/backend-api.conf';
export const environment = {
  production: true,
  iconBaseUrl: '/tsddr',
  auth: {
    ssoLogoutBO: 'https://tsddr.ruparpiemonte.it/tsddr_rup_443sliv1irup/Shibboleth.sso/Logout',
    //ssoLogoutFO: 'https://tsddr.regione.piemonte.it/tsddr_int_443s_liv3_sispliv1spid_gasp_regione/logout.do'
    ssoLogoutFO: 'https://tsddr.regione.piemonte.it/tsddr_int_443s_liv3_sispliv2spid_gasp_regione/logout.do'
  },
  fakeAuth: {
    enabled: false,
    header: {
      key: 'Shib-Iride-IdentitaDigitale',
      value: 'GRSNTN80A01B354W/Antonio/Grussu/SPID//4//'
    }
  },
  backend: {
    environment: 'PROD',
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
