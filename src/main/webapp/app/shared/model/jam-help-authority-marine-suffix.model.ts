import { Moment } from 'moment';

export interface IJamHelpAuthorityMarineSuffix {
    id?: number;
    authorityName?: string;
    hasEditPermission?: boolean;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    jamHelpTitle?: string;
    jamHelpId?: number;
}

export class JamHelpAuthorityMarineSuffix implements IJamHelpAuthorityMarineSuffix {
    constructor(
        public id?: number,
        public authorityName?: string,
        public hasEditPermission?: boolean,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public jamHelpTitle?: string,
        public jamHelpId?: number
    ) {
        this.hasEditPermission = this.hasEditPermission || false;
    }
}
