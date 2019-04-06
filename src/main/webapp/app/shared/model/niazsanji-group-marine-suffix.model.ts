import { Moment } from 'moment';
import { IJobMarineSuffix } from 'app/shared/model//job-marine-suffix.model';
import { IEducationalModuleMarineSuffix } from 'app/shared/model//educational-module-marine-suffix.model';

export interface INiazsanjiGroupMarineSuffix {
    id?: number;
    editorPerson?: string;
    reviewDate?: Moment;
    scheduleDate?: Moment;
    firstThreeJobCode?: string;
    priceCost?: number;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    jobs?: IJobMarineSuffix[];
    educationalModules?: IEducationalModuleMarineSuffix[];
    scientificWorkGroupTitle?: string;
    scientificWorkGroupId?: number;
}

export class NiazsanjiGroupMarineSuffix implements INiazsanjiGroupMarineSuffix {
    constructor(
        public id?: number,
        public editorPerson?: string,
        public reviewDate?: Moment,
        public scheduleDate?: Moment,
        public firstThreeJobCode?: string,
        public priceCost?: number,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public jobs?: IJobMarineSuffix[],
        public educationalModules?: IEducationalModuleMarineSuffix[],
        public scientificWorkGroupTitle?: string,
        public scientificWorkGroupId?: number
    ) {
        this.archived = this.archived || false;
    }
}
