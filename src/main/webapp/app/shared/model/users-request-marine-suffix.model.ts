import { Moment } from 'moment';
import { IPersonMarineSuffix } from 'app/shared/model//person-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import { RequestStatus } from 'app/shared/model/enums/RequestStatus';

export interface IUsersRequestMarineSuffix {
    id?: number;
    title?: string;
    telephone?: string;
    description?: any;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    conversation?: any;
    requestStatus?: RequestStatus;
    changeStatusUserLogin?: string;
    guid?: string;
    hasImportantMessage?: boolean;
    people?: IPersonMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    orgChartRoot?: string;
}

export class UsersRequestMarineSuffix implements IUsersRequestMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public telephone?: string,
        public description?: any,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public conversation?: any,
        public requestStatus?: RequestStatus,
        public changeStatusUserLogin?: string,
        public guid?: string,
        public hasImportantMessage?: boolean,
        public people?: IPersonMarineSuffix[],
        public documents?: IDocumentMarineSuffix[]
    ) {
        this.hasImportantMessage = this.hasImportantMessage || false;
    }
}
