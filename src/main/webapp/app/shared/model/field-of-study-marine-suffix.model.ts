import { Moment } from 'moment';
import { IPersonMarineSuffix } from 'app/shared/model//person-marine-suffix.model';
import { ITeacherMarineSuffix } from 'app/shared/model//teacher-marine-suffix.model';
import { IEducationalRecordMarineSuffix } from 'app/shared/model//educational-record-marine-suffix.model';
import { ISoldierMarineSuffix } from 'app/shared/model//soldier-marine-suffix.model';

export interface IFieldOfStudyMarineSuffix {
    id?: number;
    title?: string;
    code?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    people?: IPersonMarineSuffix[];
    teachers?: ITeacherMarineSuffix[];
    educationalRecords?: IEducationalRecordMarineSuffix[];
    soldiers?: ISoldierMarineSuffix[];
}

export class FieldOfStudyMarineSuffix implements IFieldOfStudyMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public code?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public people?: IPersonMarineSuffix[],
        public teachers?: ITeacherMarineSuffix[],
        public educationalRecords?: IEducationalRecordMarineSuffix[],
        public soldiers?: ISoldierMarineSuffix[]
    ) {}
}
