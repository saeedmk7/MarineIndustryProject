import { Moment } from 'moment';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import {RequestStatus} from "app/shared/model/enums/RequestStatus";

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
    documents?: IDocumentMarineSuffix[];
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
        public documents?: IDocumentMarineSuffix[]
    ) {}
}
