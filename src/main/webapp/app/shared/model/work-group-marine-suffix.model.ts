import { Moment } from 'moment';
import { IPersonMarineSuffix } from 'app/shared/model//person-marine-suffix.model';
import { IWorkUnitMarineSuffix } from 'app/shared/model//work-unit-marine-suffix.model';

export interface IWorkGroupMarineSuffix {
    id?: number;
    title?: string;
    code?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    people?: IPersonMarineSuffix[];
    workUnits?: IWorkUnitMarineSuffix[];
}

export class WorkGroupMarineSuffix implements IWorkGroupMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public code?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public people?: IPersonMarineSuffix[],
        public workUnits?: IWorkUnitMarineSuffix[]
    ) {}
}
