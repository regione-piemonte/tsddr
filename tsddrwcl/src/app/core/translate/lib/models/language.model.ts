
export class Language {
  public code: string;
  public label: string;
  public isDefault: boolean;
  public translations?: any | null;

  constructor(
      language: Language
  ) {
      this.code = language.code;
      this.label = language.label;
      this.isDefault = language.isDefault;
      this.translations = language.translations || null;
  }
}
