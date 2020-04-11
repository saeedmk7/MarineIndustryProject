import { Moment } from 'moment';
import { IEffectivenessPhaseMarineSuffix } from 'app/shared/model//effectiveness-phase-marine-suffix.model';
import {UnitOfMeasurement} from "app/shared/model/enums/UnitOfMeasurement";



export interface IEffectivenessPhaseLevelMarineSuffix {
    id?: number;
    title?: string;
    effectivenessLevel?: number;
    effectivenessLevelTitle?: string;
    effectivenessLevelUse?: number;
    effectivenessLevelUseTitle?: string;
    weight?: number;
    goal?: number;
    unitOfMeasurement?: UnitOfMeasurement;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    effectivenessPhases?: IEffectivenessPhaseMarineSuffix[];
}

export class EffectivenessPhaseLevelMarineSuffix implements IEffectivenessPhaseLevelMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public effectivenessLevel?: number,
        public effectivenessLevelTitle?: string,
        public effectivenessLevelUse?: number,
        public effectivenessLevelUseTitle?: string,
        public weight?: number,
        public goal?: number,
        public unitOfMeasurement?: UnitOfMeasurement,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public effectivenessPhases?: IEffectivenessPhaseMarineSuffix[]
    ) {}
}
