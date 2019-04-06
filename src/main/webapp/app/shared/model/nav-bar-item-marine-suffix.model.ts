import { Moment } from 'moment';
import { INavBarItemMarineSuffix } from 'app/shared/model//nav-bar-item-marine-suffix.model';
import { INavBarItemAuthorityMarineSuffix } from 'app/shared/model//nav-bar-item-authority-marine-suffix.model';

export interface INavBarItemMarineSuffix {
    id?: number;
    title?: string;
    code?: string;
    description?: string;
    url?: string;
    faicon?: string;
    isActive?: boolean;
    displayOrder?: number;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    navBarItems?: INavBarItemMarineSuffix[];
    navBarItemAuthorities?: INavBarItemAuthorityMarineSuffix[];
    parentTitle?: string;
    parentId?: number;
}

export class NavBarItemMarineSuffix implements INavBarItemMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public code?: string,
        public description?: string,
        public url?: string,
        public faicon?: string,
        public isActive?: boolean,
        public displayOrder?: number,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public navBarItems?: INavBarItemMarineSuffix[],
        public navBarItemAuthorities?: INavBarItemAuthorityMarineSuffix[],
        public parentTitle?: string,
        public parentId?: number
    ) {
        this.isActive = this.isActive || false;
    }
}
