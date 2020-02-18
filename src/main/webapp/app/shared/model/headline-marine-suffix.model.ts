import { Moment } from 'moment';
import {HeadlineLevel} from "app/shared/model/enums/HeadlineLevel";
import {HeadlineScope} from "app/shared/model/enums/HeadlineScope";

export interface IHeadlineMarineSuffix {
    id?: number;
    title?: string;
    headlineLevel?: HeadlineLevel;
    headlineScope?: HeadlineScope;
    totalHour?: number;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    requestEducationalModuleTitle?: string;
    requestEducationalModuleId?: number;
    educationalModuleTitle?: string;
    educationalModuleId?: number;
    selectedItems?: any[];
    isNew?: boolean;
}

export class HeadlineMarineSuffix implements IHeadlineMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public headlineLevel?: HeadlineLevel,
        public headlineScope?: HeadlineScope,
        public totalHour?: number,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public requestEducationalModuleTitle?: string,
        public requestEducationalModuleId?: number,
        public educationalModuleTitle?: string,
        public educationalModuleId?: number,
        public selectedItems?: any[],
        public isNew?: boolean
    ) {}
}
