import { TranslateModuleConfig } from '@ngx-translate/core';
import { Language } from './language.model';

export class I18nServiceConfig {
  langs?: Language[];
  translate?: TranslateModuleConfig;
}
