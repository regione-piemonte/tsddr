import {
    AsyncValidatorFn,
    FormGroup,
    ValidationErrors,
} from '@angular/forms';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AreaPersonaleService } from '../sevices/area-personale.service';
  
  export class AreaPersonaleValidator {
    static emailValidator(areaPersonaleService: AreaPersonaleService): AsyncValidatorFn {
      return (form: FormGroup): Observable<ValidationErrors> => {
        return areaPersonaleService
          .validatorVerificaCampiObbligatori('email',form.value['email'])
          .pipe(
            map((result: boolean) =>{
              let o = new Object();
              if(!result){
                o['email']=true;
                return o;
              }else{
                return null;
              }
             }
            )
          );
      };
    }
  }