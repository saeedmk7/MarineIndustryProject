import { Moment } from 'moment';
import {RequestStatus} from "app/shared/model/enums/RequestStatus";


export interface IEducationalHistoryMarineSuffix {
    id?: number;
    educationalModuleTitle?: string;
    learningTimeTheorical?: number;
    learningTimePractical?: number;
    totalTime?: number;
    educationalCenter?: string;
    dateOfStart?: string;
    fileDoc?: any;
    description?: any;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    statusMeaning?: string;
    conversation?: any;
    requestStatus?: RequestStatus;
    changeStatusUserLogin?: string;
    personFullName?: string;
    personName?: string;
    personFamily?: string;
    personId?: number;
    organizationChartTitle?: string;
    organizationChartId?: number;
}

export class EducationalHistoryMarineSuffix implements IEducationalHistoryMarineSuffix {
    constructor(
        public id?: number,
        public educationalModuleTitle?: string,
        public learningTimeTheorical?: number,
        public learningTimePractical?: number,
        public totalTime?: number,
        public educationalCenter?: string,
        public dateOfStart?: string,
        public fileDoc?: any,
        public description?: any,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public statusMeaning?: string,
        public conversation?: any,
        public requestStatus?: RequestStatus,
        public changeStatusUserLogin?: string,
        public personFullName?: string,
        public personName?: string,
        public personFamily?: string,
        public personId?: number,
        public organizationChartTitle?: string,
        public organizationChartId?: number
    ) {
        this.archived = this.archived || false;
    }
}
