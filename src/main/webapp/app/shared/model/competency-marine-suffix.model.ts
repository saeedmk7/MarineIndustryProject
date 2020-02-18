import { Moment } from 'moment';
import { IEducationalModuleMarineSuffix } from 'app/shared/model//educational-module-marine-suffix.model';
import { IRequestEducationalModuleMarineSuffix } from 'app/shared/model//request-educational-module-marine-suffix.model';
import { IPreJobNiazsanjiCompetencyMarineSuffix } from 'app/shared/model//pre-job-niazsanji-competency-marine-suffix.model';

export interface ICompetencyMarineSuffix {
    id?: number;
    title?: string;
    description?: string;
    displayOrder?: number;
    competencyType?: number;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    educationalModules?: IEducationalModuleMarineSuffix[];
    requestEducationalModules?: IRequestEducationalModuleMarineSuffix[];
    selectedItems?: any[];
    preJobNiazsanjiCompetencies?: IPreJobNiazsanjiCompetencyMarineSuffix[];
}

export class CompetencyMarineSuffix implements ICompetencyMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public displayOrder?: number,
        public competencyType?: number,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public educationalModules?: IEducationalModuleMarineSuffix[],
        public requestEducationalModules?: IRequestEducationalModuleMarineSuffix[],
        public preJobNiazsanjiCompetencies?: IPreJobNiazsanjiCompetencyMarineSuffix[],
        public selectedItems?: any[]
    ) {}
}
