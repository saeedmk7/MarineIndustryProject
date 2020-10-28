import { Moment } from 'moment';
import { IMonitorLearningProcessGradeMarineSuffix } from 'app/shared/model//monitor-learning-process-grade-marine-suffix.model';

export interface IMonitorLearningProcessLevelMarineSuffix {
    id?: number;
    title?: string;
    code?: string;
    description?: string;
    goal?: number;
    formula?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    guid?: string;
    monitorLearningProcessGrades?: IMonitorLearningProcessGradeMarineSuffix[];
}

export class MonitorLearningProcessLevelMarineSuffix implements IMonitorLearningProcessLevelMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public code?: string,
        public description?: string,
        public goal?: number,
        public formula?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public guid?: string,
        public monitorLearningProcessGrades?: IMonitorLearningProcessGradeMarineSuffix[]
    ) {}
}
