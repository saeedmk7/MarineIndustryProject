import { Moment } from 'moment';
import { IMonitorLearningProcessGradeMarineSuffix } from 'app/shared/model//monitor-learning-process-grade-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';

export interface IMonitorLearningProcessMarineSuffix {
    id?: number;
    title?: string;
    code?: string;
    processDate?: string;
    classification?: string;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    guid?: string;
    monitorLearningProcessGrades?: IMonitorLearningProcessGradeMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    monitorProcessDurationTitle?: string;
    monitorProcessDurationId?: number;
}

export class MonitorLearningProcessMarineSuffix implements IMonitorLearningProcessMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public code?: string,
        public processDate?: string,
        public classification?: string,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public guid?: string,
        public monitorLearningProcessGrades?: IMonitorLearningProcessGradeMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public monitorProcessDurationTitle?: string,
        public monitorProcessDurationId?: number
    ) {}
}
