import { Moment } from 'moment';

export interface IBeautySpeechAuthorityMarineSuffix {
    id?: number;
    authorityName?: string;
    hasEditPermission?: boolean;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    beautySpeechTitle?: string;
    beautySpeechId?: number;
}

export class BeautySpeechAuthorityMarineSuffix implements IBeautySpeechAuthorityMarineSuffix {
    constructor(
        public id?: number,
        public authorityName?: string,
        public hasEditPermission?: boolean,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public beautySpeechTitle?: string,
        public beautySpeechId?: number
    ) {
        this.hasEditPermission = this.hasEditPermission || false;
    }
}
