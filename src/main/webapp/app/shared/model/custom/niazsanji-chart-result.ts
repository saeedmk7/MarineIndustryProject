
export interface IHomePageNiazsanjiReport {
    niazsanjiFardiCount?: number;
    niazsanjiFardiSucceedCount?: number;
    organizationNiazsanjiCount?: number;
    organizationNiazsanjiSucceedCount?: number;
    designAndPlanningStepCount?: number;
    runningStepCount?: number;
}

export class HomePageNiazsanjiReport implements IHomePageNiazsanjiReport {
    constructor(
        public niazsanjiFardiCount?: number,
        public niazsanjiFardiSucceedCount?: number,
        public organizationNiazsanjiCount?: number,
        public organizationNiazsanjiSucceedCount?: number,
        public designAndPlanningStepCount?: number,
        public runningStepCount?: number
    ) {
    }
}
