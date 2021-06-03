export interface ICapitationReportDetailItem {
    groupId: number;
    groupTitle: string;
    industryTitle: string;
    organizationChartIds: number[];
    //Employees
    totalEmployee?: number;
    totalBoss?: number;
    totalExpert?: number;

    //Capitation
    avgLearningTimeExpert?: number;
    avgLearningTimeBoss?: number;
    maximumAllowablePersonHour?: number;
    maximumAllowableCost?: number;

    //HSE
    hseNumberOfLearner?: number;
    hseCourseTypePersonHour?: number;
    hseCourseTypeCost?: number;

    //short times
    shortTimeBossShare?: number;
    shortTimeExpertShare?: number;
    predicateBeforeAcceptPersonHour?: number;
    predicateAfterAcceptPersonHour?: number;
    predicatePersonHourTotal?: number;
    predicateBeforeAcceptCost?: number;
    predicateAfterAcceptCost?: number;
    predicateCostTotal?: number;

    percentNiazsanjiProgramToMaximumPersonHour?: number;
    percentNiazsanjiProgramToMaximumCost?: number;

    //Organization Niazsanjis
    organizationPredicatePersonHour?: number;
    organizationPredicateCost?: number;

    //securityAndInforming
    securityAndInformingNumberOfLearner?: number;
    securityAndInformingPersonHour?: number;
    securityAndInformingCost?: number;

    //Informing Courses
    informingPersonHour?: number;
    informingCost?: number;

    //Security
    securityPersonHour?: number;
    securityCost?: number;

    //capitationReportDetails?: CapitationReportDetailItemDetail[]
}

export class CapitationReportDetailItem implements ICapitationReportDetailItem {
    constructor(
        public groupId: number,
        public groupTitle: string,
        public industryTitle: string,
        public organizationChartIds: number[],
        //Employees
        public totalEmployee?: number,
        public totalBoss?: number,
        public totalExpert?: number,
        //Capitation
        public avgLearningTimeExpert?: number,
        public avgLearningTimeBoss?: number,
        public maximumAllowablePersonHour?: number,
        public maximumAllowableCost?: number,
        //HSE
        public hseNumberOfLearner?: number,
        public hseCourseTypePersonHour?: number,
        public hseCourseTypeCost?: number,
        //short times
        public shortTimeBossShare?: number,
        public shortTimeExpertShare?: number,
        public predicateBeforeAcceptPersonHour?: number,
        public predicateAfterAcceptPersonHour?: number,
        public predicatePersonHourTotal?: number,
        public predicateBeforeAcceptCost?: number,
        public predicateAfterAcceptCost?: number,
        public predicateCostTotal?: number,
        public percentNiazsanjiProgramToMaximumPersonHour?: number,
        public percentNiazsanjiProgramToMaximumCost?: number,
        //Organization Niazsanjis
        public organizationPredicatePersonHour?: number,
        public organizationPredicateCost?: number,
        //securityAndInforming
        public securityAndInformingNumberOfLearner?: number,
        public securityAndInformingPersonHour?: number,
        public securityAndInformingCost?: number,
        //Informing Courses
        public informingPersonHour?: number,
        public informingCost?: number,
        //Security
        public securityPersonHour?: number,
        public securityCost?: number //capitationReportDetails?: CapitationReportDetailItemDetail[]
    ) {}
}
