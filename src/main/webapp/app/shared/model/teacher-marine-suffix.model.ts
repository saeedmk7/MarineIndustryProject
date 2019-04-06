import { Moment } from 'moment';
import { IRequestOrganizationNiazsanjiMarineSuffix } from 'app/shared/model//request-organization-niazsanji-marine-suffix.model';
import { IFinalOrganizationNiazsanjiMarineSuffix } from 'app/shared/model//final-organization-niazsanji-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import { IEducationalModuleMarineSuffix } from 'app/shared/model//educational-module-marine-suffix.model';
import { IRequestEducationalModuleMarineSuffix } from 'app/shared/model//request-educational-module-marine-suffix.model';

export interface ITeacherMarineSuffix {
    id?: number;
    name?: string;
    family?: string;
    fullName?: string;
    fatherName?: string;
    scientificBasis?: number;
    inquiry?: boolean;
    schoolConfirmation?: boolean;
    protectiveApproval?: boolean;
    teachingSubject?: string;
    issueDate?: Moment;
    expirationDate?: Moment;
    licenseNumber?: number;
    sessionNumber?: number;
    phoneNumber?: string;
    licenseRenewalDate?: Moment;
    code?: number;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    requestOrganizationNiazsanjis?: IRequestOrganizationNiazsanjiMarineSuffix[];
    finalOrganizationNiazsanjis?: IFinalOrganizationNiazsanjiMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    lastQualificationTitle?: string;
    lastQualificationId?: number;
    lastFieldOfStudyTitle?: string;
    lastFieldOfStudyId?: number;
    serviceUnitTitle?: string;
    serviceUnitId?: number;
    academicRankTitle?: string;
    academicRankId?: number;
    educationalModules?: IEducationalModuleMarineSuffix[];
    requestEducationalModules?: IRequestEducationalModuleMarineSuffix[];
}

export class TeacherMarineSuffix implements ITeacherMarineSuffix {
    constructor(
        public id?: number,
        public name?: string,
        public family?: string,
        public fullName?: string,
        public fatherName?: string,
        public scientificBasis?: number,
        public inquiry?: boolean,
        public schoolConfirmation?: boolean,
        public protectiveApproval?: boolean,
        public teachingSubject?: string,
        public issueDate?: Moment,
        public expirationDate?: Moment,
        public licenseNumber?: number,
        public sessionNumber?: number,
        public phoneNumber?: string,
        public licenseRenewalDate?: Moment,
        public code?: number,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public requestOrganizationNiazsanjis?: IRequestOrganizationNiazsanjiMarineSuffix[],
        public finalOrganizationNiazsanjis?: IFinalOrganizationNiazsanjiMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public lastQualificationTitle?: string,
        public lastQualificationId?: number,
        public lastFieldOfStudyTitle?: string,
        public lastFieldOfStudyId?: number,
        public serviceUnitTitle?: string,
        public serviceUnitId?: number,
        public academicRankTitle?: string,
        public academicRankId?: number,
        public educationalModules?: IEducationalModuleMarineSuffix[],
        public requestEducationalModules?: IRequestEducationalModuleMarineSuffix[]
    ) {
        this.inquiry = this.inquiry || false;
        this.schoolConfirmation = this.schoolConfirmation || false;
        this.protectiveApproval = this.protectiveApproval || false;
        this.archived = this.archived || false;
    }
}
