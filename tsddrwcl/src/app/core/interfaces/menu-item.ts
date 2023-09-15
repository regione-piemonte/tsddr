export interface MenuItem {
  title: string;
  icon: string;
  link: string;
  home: boolean;
  data: any;
  hidden?: boolean;
  opened?: boolean;
  children?: MenuItem[];
  type?: string;
  id?: string;
}
