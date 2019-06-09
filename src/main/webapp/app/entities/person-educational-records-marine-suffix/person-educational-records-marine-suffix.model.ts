export interface IPersonEducationalRecordsMarineSuffixModel {
    url?: string;
    isOk?: boolean;
}

export class PersonEducationalRecordsMarineSuffixModel implements IPersonEducationalRecordsMarineSuffixModel {
    constructor(
        public url?: string,
        public isOk?: boolean
    ) {}
}
