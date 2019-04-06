import { Moment } from 'moment';

export interface IBeautySpeechMarineSuffix {
    id?: number;
    title?: string;
    description?: string;
    isActive?: boolean;
    showDate?: Moment;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
}

export class BeautySpeechMarineSuffix implements IBeautySpeechMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public isActive?: boolean,
        public showDate?: Moment,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment
    ) {
        this.isActive = this.isActive || false;
    }
}
