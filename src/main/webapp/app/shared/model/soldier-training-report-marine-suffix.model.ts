import { Moment } from 'moment';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';

export interface ISoldierTrainingReportMarineSuffix {
    id?: number;
    title?: string;
    personHour?: number;
    executiveTrainingCompany?: string;
    certificateStatus?: string;
    certificateNumber?: string;
    year?: number;
    month?: number;
    reportTime?: number;
    fileDoc?: any;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    guid?: string;
    documents?: IDocumentMarineSuffix[];
    soldierName?: string;
    soldierFamily?: string;
    soldierFullName?: string;
    soldierOrganizationChartId?: number;
    soldierOrganizationChartTitle?: string;
    soldierNationalId?: string;
    soldierEmploymentDate?: any;
    soldierId?: number;
}

export class SoldierTrainingReportMarineSuffix implements ISoldierTrainingReportMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public personHour?: number,
        public executiveTrainingCompany?: string,
        public certificateStatus?: string,
        public certificateNumber?: string,
        public year?: number,
        public month?: number,
        public reportTime?: number,
        public fileDoc?: any,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public guid?: string,
        public documents?: IDocumentMarineSuffix[],
        public soldierName?: string,
        public soldierFamily?: string,
        public soldierFullName?: string,
        public soldierOrganizationChartId?: number,
        public soldierOrganizationChartTitle?: string,
        public soldierNationalId?: string,
        public soldierReleaseDate?: any,
        public soldierId?: number
    ) {}
}
