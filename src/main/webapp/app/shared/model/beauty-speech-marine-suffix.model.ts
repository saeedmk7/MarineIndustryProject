import { Moment } from 'moment';
import { IBeautySpeechAuthorityMarineSuffix } from 'app/shared/model//beauty-speech-authority-marine-suffix.model';

export interface IBeautySpeechMarineSuffix {
    id?: number;
    title?: string;
    description?: string;
    isActive?: boolean;
    showDate?: Moment;
    authorityNames?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    beautySpeechAuthorities?: IBeautySpeechAuthorityMarineSuffix[];
}

export class BeautySpeechMarineSuffix implements IBeautySpeechMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public isActive?: boolean,
        public showDate?: Moment,
        public authorityNames?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public beautySpeechAuthorities?: IBeautySpeechAuthorityMarineSuffix[]
    ) {
        this.isActive = this.isActive || false;
    }
}
