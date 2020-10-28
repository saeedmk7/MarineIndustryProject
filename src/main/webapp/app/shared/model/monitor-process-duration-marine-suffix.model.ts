import { Moment } from 'moment';
import { IMonitorLearningProcessMarineSuffix } from 'app/shared/model//monitor-learning-process-marine-suffix.model';
import { IMonitorLearningProcessGradeMarineSuffix } from 'app/shared/model//monitor-learning-process-grade-marine-suffix.model';

export interface IMonitorProcessDurationMarineSuffix {
    id?: number;
    title?: string;
    code?: string;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    guid?: string;
    monitorLearningProcesses?: IMonitorLearningProcessMarineSuffix[];
    monitorLearningProcessGrades?: IMonitorLearningProcessGradeMarineSuffix[];
}

export class MonitorProcessDurationMarineSuffix implements IMonitorProcessDurationMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public code?: string,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public guid?: string,
        public monitorLearningProcesses?: IMonitorLearningProcessMarineSuffix[],
        public monitorLearningProcessGrades?: IMonitorLearningProcessGradeMarineSuffix[]
    ) {}
}
