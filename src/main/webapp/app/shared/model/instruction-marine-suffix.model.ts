import { Moment } from 'moment';
import { IInstructionAuthorityMarineSuffix } from 'app/shared/model//instruction-authority-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';

export interface IInstructionMarineSuffix {
    id?: number;
    title?: string;
    description?: string;
    code?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    guid?: string;
    instructionAuthorities?: IInstructionAuthorityMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
}

export class InstructionMarineSuffix implements IInstructionMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public code?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public guid?: string,
        public instructionAuthorities?: IInstructionAuthorityMarineSuffix[],
        public documents?: IDocumentMarineSuffix[]
    ) {}
}
