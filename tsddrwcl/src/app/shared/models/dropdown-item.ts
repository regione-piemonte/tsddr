import { Icon } from './';

export interface DropDownItem {
  icon?: Icon;
  label: string;
  detail?: string;
  isHidden?: (row) => boolean;
  onClick?: ($event) => void;
}
