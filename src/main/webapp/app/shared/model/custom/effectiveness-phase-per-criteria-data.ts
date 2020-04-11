export interface IEffectivenessPhasePerCriteriaData {
    criteria?: any;
    sumValue?: number;
}

export class EffectivenessPhasePerCriteriaData implements IEffectivenessPhasePerCriteriaData {
    constructor(
        public criteria?: any,
        public sumValue?: number
    ) {
    }
}
