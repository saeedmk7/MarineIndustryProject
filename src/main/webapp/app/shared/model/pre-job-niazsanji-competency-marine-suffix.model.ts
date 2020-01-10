import { Moment } from 'moment';
import { IPriorityCriteriaValueMarineSuffix } from 'app/shared/model//priority-criteria-value-marine-suffix.model';
import { ITeachingApproachMarineSuffix } from 'app/shared/model//teaching-approach-marine-suffix.model';

export interface IPreJobNiazsanjiCompetencyMarineSuffix {
    id?: number;
    title?: string;
    needToImprove?: number;
    needToImproveDescription?: string;
    fixCompetencyDeficiencyDescription?: string;
    educationalModuleText?: string;
    sumScore?: number;
    priority?: number;
    selected?: boolean;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    priorityCriteriaValues?: IPriorityCriteriaValueMarineSuffix[];
    teachingApproaches?: ITeachingApproachMarineSuffix[];
    fixCompetencyDeficiencyTitle?: string;
    fixCompetencyDeficiencyId?: number;
    educationalModuleTitle?: string;
    educationalModuleId?: number;
    preJobNiazsanjiTitle?: string;
    preJobNiazsanjiId?: number;
    competencyTitle?: string;
    competencyId?: number;
}

export class PreJobNiazsanjiCompetencyMarineSuffix implements IPreJobNiazsanjiCompetencyMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public needToImprove?: number,
        public needToImproveDescription?: string,
        public fixCompetencyDeficiencyDescription?: string,
        public educationalModuleText?: string,
        public sumScore?: number,
        public priority?: number,
        public selected?: boolean,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public priorityCriteriaValues?: IPriorityCriteriaValueMarineSuffix[],
        public teachingApproaches?: ITeachingApproachMarineSuffix[],
        public fixCompetencyDeficiencyTitle?: string,
        public fixCompetencyDeficiencyId?: number,
        public educationalModuleTitle?: string,
        public educationalModuleId?: number,
        public preJobNiazsanjiTitle?: string,
        public preJobNiazsanjiId?: number,
        public competencyTitle?: string,
        public competencyId?: number
    ) {
        this.selected = this.selected || false;
        /*if(this.priorityCriteriaValues && this.priorityCriteriaValues.length > 0){
            this.sumScore = this.priorityCriteriaValues.map(w => w.score).reduce((sum, current) => sum + current);
        }*/
    }
    /*get sumScore(){
        
        if(this.priorityCriteriaValues && this.priorityCriteriaValues.length > 0){
            
            return this.priorityCriteriaValues.map(w => w.score).reduce((sum, current) => sum + current);
        }
        return 0;
    }
    get priority(){
        
        if(this.sumScore >= 85)
            return 1;
        else if(this.sumScore >= 60)
            return 2;
        else
            return 3;
    }*/
}
