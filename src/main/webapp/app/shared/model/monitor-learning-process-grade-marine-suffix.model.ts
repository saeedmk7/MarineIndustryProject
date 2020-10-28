import { Moment } from 'moment';

export interface IMonitorLearningProcessGradeMarineSuffix {
    id?: number;
    firstNumber?: number;
    secondNumber?: number;
    thirdNumber?: number;
    result?: number;
    goal?: number;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    guid?: string;
    formula?: string;
    monitorLearningProcessLevelTitle?: string;
    monitorLearningProcessLevelId?: number;
    monitorLearningProcessTitle?: string;
    monitorLearningProcessId?: number;
    monitorProcessDurationTitle?: string;
    monitorProcessDurationId?: number;
}

export class MonitorLearningProcessGradeMarineSuffix implements IMonitorLearningProcessGradeMarineSuffix {
    constructor(
        public id?: number,
        public firstNumber?: number,
        public secondNumber?: number,
        public thirdNumber?: number,
        public result?: number,
        public goal?: number,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public guid?: string,
        public formula?: string,
        public monitorLearningProcessLevelTitle?: string,
        public monitorLearningProcessLevelId?: number,
        public monitorLearningProcessTitle?: string,
        public monitorLearningProcessId?: number,
        public monitorProcessDurationTitle?: string,
        public monitorProcessDurationId?: number
    ) {}
}
