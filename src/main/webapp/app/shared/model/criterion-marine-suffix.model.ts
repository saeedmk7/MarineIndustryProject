import { Moment } from 'moment';
import { IPollItemMarineSuffix } from 'app/shared/model//poll-item-marine-suffix.model';
import {CriterionType} from "app/shared/model/enums/CriterionType";



export interface ICriterionMarineSuffix {
    id?: number;
    title?: string;
    displayOrder?: number;
    coefficient?: number;
    criterionType?: CriterionType;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    pollItems?: IPollItemMarineSuffix[];
}

export class CriterionMarineSuffix implements ICriterionMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public displayOrder?: number,
        public coefficient?: number,
        public criterionType?: CriterionType,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public pollItems?: IPollItemMarineSuffix[]
    ) {}
}
