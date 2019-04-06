import { Moment } from 'moment';

export interface IOrganizationChartAuthorityMarineSuffix {
    id?: number;
    authorityName?: string;
    hasEditPermission?: boolean;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    organizationChartTitle?: string;
    organizationChartId?: number;
}

export class OrganizationChartAuthorityMarineSuffix implements IOrganizationChartAuthorityMarineSuffix {
    constructor(
        public id?: number,
        public authorityName?: string,
        public hasEditPermission?: boolean,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public organizationChartTitle?: string,
        public organizationChartId?: number
    ) {
        this.hasEditPermission = this.hasEditPermission || false;
        this.archived = this.archived || false;
    }
}
