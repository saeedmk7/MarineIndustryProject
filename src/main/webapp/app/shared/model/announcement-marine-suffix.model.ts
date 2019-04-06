import { Moment } from 'moment';
import {IDocumentMarineSuffix} from "app/shared/model/document-marine-suffix.model";

export interface IAnnouncementMarineSuffix {
    id?: number;
    title?: string;
    code?: string;
    description?: string;
    compeleteText?: any;
    isActive?: boolean;
    announcementLevel?: number;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    documents?: IDocumentMarineSuffix[];
}

export class AnnouncementMarineSuffix implements IAnnouncementMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public code?: string,
        public description?: string,
        public compeleteText?: any,
        public isActive?: boolean,
        public announcementLevel?: number,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public documents?: IDocumentMarineSuffix[]
    ) {
        this.isActive = this.isActive || false;
    }
}
