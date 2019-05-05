
export interface INiazsanjiChartResult {
    requestNiazsanjiFardiCount?: number;
    requestOrganizationNiazsanjiCount?: number;
}

export class NiazsanjiChartResult implements INiazsanjiChartResult {
    constructor(
        public requestNiazsanjiFardiCount?: number,
        public requestOrganizationNiazsanjiCount?: number
    ) {
    }
}
