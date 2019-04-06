import { Moment } from 'moment';
import { IPersonMarineSuffix } from 'app/shared/model//person-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import {RequestStatus} from "app/shared/model/enums/RequestStatus";



export interface IFinalOrganizationNiazsanjiMarineSuffix {
    id?: number;
    niazsanjiYear?: number;
    code?: string;
    recommendedByOrgchart?: string;
    neededSoftwares?: string;
    neededHardware?: string;
    studentsType?: string;
    teacherName?: string;
    teacherMobile?: string;
    conversation?: any;
    requestStatus?: RequestStatus;
    changeStatusUserLogin?: string;
    trainingGoals?: string;
    description?: string;
    priceCost?: number;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    people?: IPersonMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    organizationChartTitle?: string;
    organizationChartId?: number;
    teacherFamily?: string;
    teacherId?: number;
    educationalModuleTitle?: string;
    educationalModuleId?: number;
    teachApproachTitle?: string;
    teachApproachId?: number;
    requestOrganizationNiazsanjiRecommendedByOrgchart?: string;
    requestOrganizationNiazsanjiId?: number;
}

export class FinalOrganizationNiazsanjiMarineSuffix implements IFinalOrganizationNiazsanjiMarineSuffix {
    constructor(
        public id?: number,
        public niazsanjiYear?: number,
        public code?: string,
        public recommendedByOrgchart?: string,
        public neededSoftwares?: string,
        public neededHardware?: string,
        public studentsType?: string,
        public teacherName?: string,
        public teacherMobile?: string,
        public conversation?: any,
        public requestStatus?: RequestStatus,
        public changeStatusUserLogin?: string,
        public trainingGoals?: string,
        public description?: string,
        public priceCost?: number,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public people?: IPersonMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public organizationChartTitle?: string,
        public organizationChartId?: number,
        public teacherFamily?: string,
        public teacherId?: number,
        public educationalModuleTitle?: string,
        public educationalModuleId?: number,
        public teachApproachTitle?: string,
        public teachApproachId?: number,
        public requestOrganizationNiazsanjiRecommendedByOrgchart?: string,
        public requestOrganizationNiazsanjiId?: number
    ) {
        this.archived = this.archived || false;
    }
}
