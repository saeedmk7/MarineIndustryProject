import { Moment } from 'moment';
import { IDesignAndPlanningMarineSuffix } from 'app/shared/model//design-and-planning-marine-suffix.model';

export interface IMahiatCourseMarineSuffix {
    id?: number;
    title?: string;
    code?: string;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    designAndPlannings?: IDesignAndPlanningMarineSuffix[];
}

export class MahiatCourseMarineSuffix implements IMahiatCourseMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public code?: string,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public designAndPlannings?: IDesignAndPlanningMarineSuffix[]
    ) {}
}
