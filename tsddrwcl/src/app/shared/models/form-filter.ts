import { Form } from '../form';

export interface FormFilter {
    key: string;
    value: string | number | unknown;
    form?: Form;
}
