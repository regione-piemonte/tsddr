import { catchError } from 'rxjs/operators';
import { of, pipe } from 'rxjs';
import { Router } from '@angular/router';
import { rootInjector } from '@app/core/root-injector';
import { LoadingService } from '@theme/layouts/loading.services';
import { NotificationService } from '@shared/notification/notification.service';

/**
 * RxJS operator that catch error
 *
 * @param layout
 * @param router
 * @param errorKeys
 * @param checkError
 */
// eslint-disable-next-line prefer-arrow/prefer-arrow-functions
export function csiCatchError() {
  const loading: LoadingService = rootInjector.get(LoadingService);
  const notification: NotificationService =
    rootInjector.get(NotificationService);

  return pipe(
    catchError((response: any) => {
      loading.hide();
      response.error.errors.forEach((e) => {
        notification.error({ title: e.titoloMsg, text: e.testoMsg });
      });

      return of(null);
    })
  );
}

export function csiCatchErrorForValidators() {
  const loading: LoadingService = rootInjector.get(LoadingService);
  const notification: NotificationService =
    rootInjector.get(NotificationService);

  return pipe(
    catchError((response: any) => {
      loading.hide();
      response?.error?.errors?.forEach((e) => {
        notification.error({ title: e.titoloMsg, text: e.testoMsg });
      });

      return of(response.error.errors);
    })
  );
}
