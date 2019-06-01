import { Moment } from 'moment';
import { IJamHelpAuthorityMarineSuffix } from 'app/shared/model//jam-help-authority-marine-suffix.model';

export interface IJamHelpMarineSuffix {
    id?: number;
    title?: string;
    description?: string;
    code?: string;
    fileDoc?: any;
    pageUrl?: string;
    authorityNames?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    jamHelpAuthorities?: IJamHelpAuthorityMarineSuffix[];
}

export class JamHelpMarineSuffix implements IJamHelpMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public code?: string,
        public fileDoc?: any,
        public pageUrl?: string,
        public authorityNames?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public jamHelpAuthorities?: IJamHelpAuthorityMarineSuffix[]
    ) {}
}
