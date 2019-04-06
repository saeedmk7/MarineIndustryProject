import { Moment } from 'moment';

export interface INavBarItemAuthorityMarineSuffix {
    id?: number;
    authorityName?: string;
    hasEditPermission?: boolean;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    navBarItemTitle?: string;
    navBarItemId?: number;
}

export class NavBarItemAuthorityMarineSuffix implements INavBarItemAuthorityMarineSuffix {
    constructor(
        public id?: number,
        public authorityName?: string,
        public hasEditPermission?: boolean,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public navBarItemTitle?: string,
        public navBarItemId?: number
    ) {
        this.hasEditPermission = this.hasEditPermission || false;
    }
}
