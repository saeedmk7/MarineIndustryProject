import { Moment } from 'moment';
import { ISoldierTrainingReportMarineSuffix } from 'app/shared/model//soldier-training-report-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';

export interface ISoldierMarineSuffix {
    id?: number;
    name?: string;
    family?: string;
    fullName?: string;
    fullNameAndNationalId?: string;
    fatherName?: string;
    certificateNumber?: string;
    nationalId?: string;
    birthDate?: Moment;
    birthDatePersian?:string;
    releaseDate?: Moment;
    releaseDatePersian?: string;

    personelCode?: string;
    employmentDate?: Moment;
    employmentDatePersian?: string;
    phoneNumber?: string;
    mobile?: string;
    address?: string;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    guid?: string;
    soldierTrainingReports?: ISoldierTrainingReportMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    lastQualificationTitle?: string;
    lastQualificationId?: number;
    lastFieldOfStudyTitle?: string;
    lastFieldOfStudyId?: number;
    jobTitle?: string;
    jobId?: number;
    organizationChartTitle?: string;
    organizationChartId?: number;
}

export class SoldierMarineSuffix implements ISoldierMarineSuffix {
    constructor(
        public id?: number,
        public name?: string,
        public family?: string,
        public fullName?: string,
        public fatherName?: string,
        public certificateNumber?: string,
        public nationalId?: string,
        public birthDate?: Moment,
        public birthDatePersian?: string,
        public releaseDate?: Moment,
        public releaseDatePersian?: string,
        public personelCode?: string,
        public employmentDate?: Moment,
        public employmentDatePersian?: string,
        public phoneNumber?: string,
        public mobile?: string,
        public address?: string,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public guid?: string,
        public soldierTrainingReports?: ISoldierTrainingReportMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public lastQualificationTitle?: string,
        public lastQualificationId?: number,
        public lastFieldOfStudyTitle?: string,
        public lastFieldOfStudyId?: number,
        public jobTitle?: string,
        public jobId?: number,
        public organizationChartTitle?: string,
        public organizationChartId?: number
    ) {
        this.archived = this.archived || false;
    }
}
