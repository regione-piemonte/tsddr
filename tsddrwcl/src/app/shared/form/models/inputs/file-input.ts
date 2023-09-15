import { BaseInput, BaseInputConstructor } from './base-input';

export interface FileInputConstructor<T>
  extends Omit<BaseInputConstructor<T>, 'pattern'> {
  buttonPlaceholder: string;
  showUploadBtn?: boolean;
  labelUploadBtn?: string;
  upload?: (file: File) => void;
}

export class FileInput<T = string> extends BaseInput<T> {
  inputType = 'file';
  buttonPlaceholder: string;
  showUploadBtn?: boolean;
  labelUploadBtn = 'FILE_INPUT_UPLOAD';
  upload?: (file: File) => void;

  constructor(options: FileInputConstructor<T>) {
    super(options);
    this.buttonPlaceholder = options.buttonPlaceholder;
    this.showUploadBtn = options.showUploadBtn;
    this.upload = options.upload;

    if (options.labelUploadBtn) {
      this.labelUploadBtn = options.labelUploadBtn;
    }
  }
}
