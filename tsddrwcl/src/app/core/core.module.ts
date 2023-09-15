import {
  APP_INITIALIZER,
  Injector,
  LOCALE_ID,
  ModuleWithProviders,
  NgModule,
  Optional,
  SkipSelf
} from '@angular/core';
import { NbRoleProvider, NbSecurityModule } from '@nebular/security';
import { NbSimpleRoleProvider } from './auth/role-provider.class';
import { AuthGuard } from './services/auth-guard.service';
import { TranslateModule } from '@eng-ds/translate';
import { MenuService, UtilityService } from './services';
import { SecurityService } from './services/security.service';
import { acl } from '@app/core/auth/access-control-list';
import { LoggerModule } from '@eng-ds/logger';
import { CommonModule } from '@angular/common';
import { ApiClientModule } from '@eng-ds/api-client';
import { environment } from '@env/environment';
import { throwIfAlreadyLoaded } from '@core/module-import-guard';
import { IT } from '@assets/i18n/it';
import {
  ErrorInterceptor,
  LoggerInterceptor
} from '@core/backend/interceptors';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { NotificationModule } from '@shared/notification/notification.module';
import { AlertModule } from '@shared/alert/alert.module';
import { AuthInterceptor } from './backend/interceptors/auth-interceptor.service';
import { ProfileInterceptor } from './backend/interceptors/profile-interceptor.service';

export const CORE_PROVIDERS = [
  NbSecurityModule.forRoot(acl).providers,
  {
    provide: NbRoleProvider,
    useClass: NbSimpleRoleProvider
  },
  AuthGuard,
  MenuService,
  SecurityService,
  UtilityService,
  {
    provide: HTTP_INTERCEPTORS,
    useClass: LoggerInterceptor,
    multi: true
  },
  {
    provide: HTTP_INTERCEPTORS,
    useClass: ErrorInterceptor,
    multi: true
  },
  {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  },
  {
    provide: HTTP_INTERCEPTORS,
    useClass: ProfileInterceptor,
    multi: true
  },
  { provide: LOCALE_ID, useValue: 'it' }
];

@NgModule({
  imports: [
    CommonModule,
    LoggerModule,
    ApiClientModule,
    TranslateModule,
    NotificationModule,
    AlertModule
  ],
  exports: [],
  declarations: []
})
export class CoreModule {
  static injector: Injector;

  constructor(
    @Optional() @SkipSelf() parentModule: CoreModule,
    injector: Injector
  ) {
    throwIfAlreadyLoaded(parentModule, 'CoreModule');
    CoreModule.injector = injector;
  }

  static forRoot(): ModuleWithProviders<CoreModule> {
    return {
      ngModule: CoreModule,
      providers: [
        ...CORE_PROVIDERS,
        ...LoggerModule.forRoot(environment.logger).providers,
        ...ApiClientModule.forRoot({
          backend: environment.backend
        }).providers,
        ...TranslateModule.forRoot({
          langs: [
            { code: 'it', isDefault: true, label: 'Italiano', translations: IT }
          ]
        }).providers,

        /*
        allo start le chiamate qui sotto sono indispensabili per far partire il progetto
        {
          provide: APP_INITIALIZER,
          useFactory: (mapper: ValueMapperService) => (() => {
            return mapper.getConfig();
          }),
          deps: [ValueMapperService],
          multi: true
        },*/
        {
          provide: APP_INITIALIZER,
          useFactory: (security: SecurityService) => () => {
            return security.initUserInfo();
          },
          deps: [SecurityService],
          multi: true
        }
      ]
    };
  }
}
