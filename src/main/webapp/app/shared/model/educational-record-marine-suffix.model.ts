import { Moment } from 'moment';

export interface IEducationalRecordMarineSuffix {
    id?: number;
    qualificationText?: string;
    fieldOfStudyText?: string;
    educationalCenterText?: string;
    totalAverage?: string;
    totalHour?: number;
    startDate?: string;
    endDate?: string;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    guid?: string;
    fileDoc?: any;
    qualificationTitle?: string;
    qualificationId?: number;
    fieldOfStudyTitle?: string;
    fieldOfStudyId?: number;
    personFamily?: string;
    personId?: number;
    personGuid?: string;
}

export class EducationalRecordMarineSuffix implements IEducationalRecordMarineSuffix {
    constructor(
        public id?: number,
        public qualificationText?: string,
        public fieldOfStudyText?: string,
        public educationalCenterText?: string,
        public totalAverage?: string,
        public totalHour?: number,
        public startDate?: string,
        public endDate?: string,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public guid?: string,
        public fileDoc?: any,
        public qualificationTitle?: string,
        public qualificationId?: number,
        public fieldOfStudyTitle?: string,
        public fieldOfStudyId?: number,
        public personFamily?: string,
        public personId?: number,
        public personGuid?: string
    ) {}
}
