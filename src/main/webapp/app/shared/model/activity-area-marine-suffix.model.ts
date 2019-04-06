import { Moment } from 'moment';
import { IEducationalCenterMarineSuffix } from 'app/shared/model//educational-center-marine-suffix.model';

export interface IActivityAreaMarineSuffix {
    id?: number;
    title?: string;
    code?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    educationalCenters?: IEducationalCenterMarineSuffix[];
}

export class ActivityAreaMarineSuffix implements IActivityAreaMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public code?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public educationalCenters?: IEducationalCenterMarineSuffix[]
    ) {}
}
