import { NbAclOptions } from '@nebular/security';

export const acl: NbAclOptions = {
  accessControl: {
    USER: {
      view: '*',
      create: '*',
      edit: '*',
      remove: '*',
      viewUser: ['info']
    },
    GUEST: {
      view: ['home', 'pratiche/ricerca', 'menu-item'],
      create: [],
      edit: '*',
      remove: '*',
      viewUser: ['login']
    }
  }
};
