export class SearchPanelModel {
    constructor(
        public entityName?: string,
        public fieldName?: string,
        public type?: string,
        public searchType?: string,
        public values?: any,
        public bindLabel: string = 'title',
        public className: string = '',
        public selectedValue?: string){
    }
}
