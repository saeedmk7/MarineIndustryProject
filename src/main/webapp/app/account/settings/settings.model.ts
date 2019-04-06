import { Moment } from 'moment';
import { IEducationalCenterMarineSuffix } from 'app/shared/model//educational-center-marine-suffix.model';

export interface ISettingsModel {
    url?: string;
    isOk?: boolean;
}

export class SettingsModel implements ISettingsModel {
    constructor(
        public url?: string,
        public isOk?: boolean
    ) {}
}
