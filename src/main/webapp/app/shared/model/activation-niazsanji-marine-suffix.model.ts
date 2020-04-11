import { Moment } from 'moment';
import {NiazSanjiSource} from "app/shared/model/enums/NiazSanjiSource";

export interface IActivationNiazsanjiMarineSuffix {
    id?: number;
    niazSanjiSource?: NiazSanjiSource;
    isActive?: boolean;
    startDate?: Moment;
    endDate?: Moment;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
}

export class ActivationNiazsanjiMarineSuffix implements IActivationNiazsanjiMarineSuffix {
    constructor(
        public id?: number,
        public niazSanjiSource?: NiazSanjiSource,
        public isActive?: boolean,
        public startDate?: Moment,
        public endDate?: Moment,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment
    ) {
        this.isActive = this.isActive || false;
    }
}
