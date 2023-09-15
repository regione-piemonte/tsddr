export interface AccordionCard {
    name: string;
    header: {
        title: string,
        cssClass?: string,
        showLeft?: boolean,
        showActive?: boolean,
        template?: any,
    };
    open?: boolean;
    template?: any;
    templateData?: any;
}
