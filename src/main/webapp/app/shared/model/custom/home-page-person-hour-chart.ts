
export interface IHomePagePersonHourChart {
    designAndPlanning?: number;
    passed?: number;
    remaining?: number;
}

export class HomePagePersonHourChart implements IHomePagePersonHourChart {
    constructor(
        public designAndPlanning?: number,
        public passed?: number,
        public remaining?: number
    ) {
    }
}
