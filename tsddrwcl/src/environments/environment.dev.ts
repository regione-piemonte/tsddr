import { backendApi } from '../config/backend-api.conf';

export const environment: any = {
  production: true,
  iconBaseUrl: '/tsddr',
  // il logout chiama prima il logout del BE widfly poi alla risposta effettua la logout sso alla url qui configurate
  auth: {
    ssoLogout: 'https://tst-www.spid.piemonte.it/gasprp_ambiente/logout.do'
  },
  fakeAuth: {
    enabled: false,
    header: {
      key: 'Shib-Iride-IdentitaDigitale',
      value: 'GRSNTN80A01B354W/Antonio/Grussu/SPID//4//'
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
