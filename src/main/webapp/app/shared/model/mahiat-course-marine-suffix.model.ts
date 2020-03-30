import { Moment } from 'moment';
import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model//final-niazsanji-report-marine-suffix.model';
import { ILevelThreeCriteriaMarineSuffix } from 'app/shared/model//level-three-criteria-marine-suffix.model';
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
    finalNiazsanjiReports?: IFinalNiazsanjiReportMarineSuffix[];
    levelThreeCriteria?: ILevelThreeCriteriaMarineSuffix[];
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
        public finalNiazsanjiReports?: IFinalNiazsanjiReportMarineSuffix[],
        public levelThreeCriteria?: ILevelThreeCriteriaMarineSuffix[],
        public designAndPlannings?: IDesignAndPlanningMarineSuffix[]
    ) {}
}
