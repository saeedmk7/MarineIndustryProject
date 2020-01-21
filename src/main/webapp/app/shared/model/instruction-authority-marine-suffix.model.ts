import { Moment } from 'moment';

export interface IInstructionAuthorityMarineSuffix {
    id?: number;
    authorityName?: string;
    hasEditPermission?: boolean;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    instructionTitle?: string;
    instructionId?: number;
}

export class InstructionAuthorityMarineSuffix implements IInstructionAuthorityMarineSuffix {
    constructor(
        public id?: number,
        public authorityName?: string,
        public hasEditPermission?: boolean,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public instructionTitle?: string,
        public instructionId?: number
    ) {
        this.hasEditPermission = this.hasEditPermission || false;
    }
}
