import { InputStatus, CheckFn } from './input-status';
import { StatusMessage } from './status-message';

/* tslint:disable:semicolon */
/* tslint:disable:member-ordering */
export class ValidationErrorStatus {
    private static create = (error: string, message?: StatusMessage) =>
        InputStatus.create(
            (control) => control.touched && control.hasError(error),
            'danger',
            message,
        );

    public static CUSTOM = (checkFn: CheckFn, message?: StatusMessage) =>
        InputStatus.create(checkFn, 'danger', message);

    public static REQUIRED_WITH_MESSAGE = (message?: StatusMessage) =>
        ValidationErrorStatus.create('required', message);

    public static REQUIRED = ValidationErrorStatus.REQUIRED_WITH_MESSAGE({
        text: 'VALIDATIONS.REQUIRED',
    });
}
