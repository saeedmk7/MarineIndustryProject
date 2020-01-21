import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IPrioritizeRequestNiazsanjiMarineSuffix } from 'app/shared/model/prioritize-request-niazsanji-marine-suffix.model';
import { PrioritizeRequestNiazsanjiMarineSuffixService } from './prioritize-request-niazsanji-marine-suffix.service';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { IRestrictionMarineSuffix } from 'app/shared/model/restriction-marine-suffix.model';
import { RestrictionMarineSuffixService } from 'app/entities/restriction-marine-suffix';
import { IRequestNiazsanjiFardiMarineSuffix } from 'app/shared/model/request-niazsanji-fardi-marine-suffix.model';
import { RequestNiazsanjiFardiMarineSuffixService } from 'app/entities/request-niazsanji-fardi-marine-suffix';
import { IPreJobNiazsanjiMarineSuffix } from 'app/shared/model/pre-job-niazsanji-marine-suffix.model';
import { PreJobNiazsanjiMarineSuffixService } from 'app/entities/pre-job-niazsanji-marine-suffix';
import { IRequestOtherNiazsanjiMarineSuffix } from 'app/shared/model/request-other-niazsanji-marine-suffix.model';
import { RequestOtherNiazsanjiMarineSuffixService } from 'app/entities/request-other-niazsanji-marine-suffix';
import { INiazsanjiInputMarineSuffix } from 'app/shared/model/niazsanji-input-marine-suffix.model';
import { NiazsanjiInputMarineSuffixService } from 'app/entities/niazsanji-input-marine-suffix';
import { ICourseTypeMarineSuffix } from 'app/shared/model/course-type-marine-suffix.model';
import { CourseTypeMarineSuffixService } from 'app/entities/course-type-marine-suffix';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import { ITeachingApproachMarineSuffix } from 'app/shared/model/teaching-approach-marine-suffix.model';
import { TeachingApproachMarineSuffixService } from 'app/entities/teaching-approach-marine-suffix';

@Component({
    selector: 'mi-prioritize-request-niazsanji-marine-suffix-update',
    templateUrl: './prioritize-request-niazsanji-marine-suffix-update.component.html'
})
export class PrioritizeRequestNiazsanjiMarineSuffixUpdateComponent implements OnInit {
    prioritizeRequestNiazsanji: IPrioritizeRequestNiazsanjiMarineSuffix;
    isSaving: boolean;

    documents: IDocumentMarineSuffix[];

    restrictions: IRestrictionMarineSuffix[];

    requestniazsanjifardis: IRequestNiazsanjiFardiMarineSuffix[];

    prejobniazsanjis: IPreJobNiazsanjiMarineSuffix[];

    requestotherniazsanjis: IRequestOtherNiazsanjiMarineSuffix[];

    niazsanjiinputs: INiazsanjiInputMarineSuffix[];

    coursetypes: ICourseTypeMarineSuffix[];

    educationalmodules: IEducationalModuleMarineSuffix[];

    people: IPersonMarineSuffix[];

    organizationcharts: IOrganizationChartMarineSuffix[];

    teachingapproaches: ITeachingApproachMarineSuffix[];
    createDate: string;
    modifyDate: string;
    archivedDate: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected prioritizeRequestNiazsanjiService: PrioritizeRequestNiazsanjiMarineSuffixService,
        protected documentService: DocumentMarineSuffixService,
        protected restrictionService: RestrictionMarineSuffixService,
        protected requestNiazsanjiFardiService: RequestNiazsanjiFardiMarineSuffixService,
        protected preJobNiazsanjiService: PreJobNiazsanjiMarineSuffixService,
        protected requestOtherNiazsanjiService: RequestOtherNiazsanjiMarineSuffixService,
        protected niazsanjiInputService: NiazsanjiInputMarineSuffixService,
        protected courseTypeService: CourseTypeMarineSuffixService,
        protected educationalModuleService: EducationalModuleMarineSuffixService,
        protected personService: PersonMarineSuffixService,
        protected organizationChartService: OrganizationChartMarineSuffixService,
        protected teachingApproachService: TeachingApproachMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ prioritizeRequestNiazsanji }) => {
            this.prioritizeRequestNiazsanji = prioritizeRequestNiazsanji;
            this.createDate =
                this.prioritizeRequestNiazsanji.createDate != null
                    ? this.prioritizeRequestNiazsanji.createDate.format(DATE_TIME_FORMAT)
                    : null;
            this.modifyDate =
                this.prioritizeRequestNiazsanji.modifyDate != null
                    ? this.prioritizeRequestNiazsanji.modifyDate.format(DATE_TIME_FORMAT)
                    : null;
            this.archivedDate =
                this.prioritizeRequestNiazsanji.archivedDate != null
                    ? this.prioritizeRequestNiazsanji.archivedDate.format(DATE_TIME_FORMAT)
                    : null;
        });
        this.documentService.query().subscribe(
            (res: HttpResponse<IDocumentMarineSuffix[]>) => {
                this.documents = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.restrictionService.query().subscribe(
            (res: HttpResponse<IRestrictionMarineSuffix[]>) => {
                this.restrictions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.requestNiazsanjiFardiService.query().subscribe(
            (res: HttpResponse<IRequestNiazsanjiFardiMarineSuffix[]>) => {
                this.requestniazsanjifardis = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.preJobNiazsanjiService.query().subscribe(
            (res: HttpResponse<IPreJobNiazsanjiMarineSuffix[]>) => {
                this.prejobniazsanjis = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.requestOtherNiazsanjiService.query().subscribe(
            (res: HttpResponse<IRequestOtherNiazsanjiMarineSuffix[]>) => {
                this.requestotherniazsanjis = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.niazsanjiInputService.query().subscribe(
            (res: HttpResponse<INiazsanjiInputMarineSuffix[]>) => {
                this.niazsanjiinputs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.courseTypeService.query().subscribe(
            (res: HttpResponse<ICourseTypeMarineSuffix[]>) => {
                this.coursetypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.educationalModuleService.query().subscribe(
            (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                this.educationalmodules = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.personService.query().subscribe(
            (res: HttpResponse<IPersonMarineSuffix[]>) => {
                this.people = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.organizationChartService.query().subscribe(
            (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                this.organizationcharts = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.teachingApproachService.query().subscribe(
            (res: HttpResponse<ITeachingApproachMarineSuffix[]>) => {
                this.teachingapproaches = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.prioritizeRequestNiazsanji.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.prioritizeRequestNiazsanji.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        this.prioritizeRequestNiazsanji.archivedDate = this.archivedDate != null ? moment(this.archivedDate, DATE_TIME_FORMAT) : null;
        if (this.prioritizeRequestNiazsanji.id !== undefined) {
            this.subscribeToSaveResponse(this.prioritizeRequestNiazsanjiService.update(this.prioritizeRequestNiazsanji));
        } else {
            this.subscribeToSaveResponse(this.prioritizeRequestNiazsanjiService.create(this.prioritizeRequestNiazsanji));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPrioritizeRequestNiazsanjiMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPrioritizeRequestNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackDocumentById(index: number, item: IDocumentMarineSuffix) {
        return item.id;
    }

    trackRestrictionById(index: number, item: IRestrictionMarineSuffix) {
        return item.id;
    }

    trackRequestNiazsanjiFardiById(index: number, item: IRequestNiazsanjiFardiMarineSuffix) {
        return item.id;
    }

    trackPreJobNiazsanjiById(index: number, item: IPreJobNiazsanjiMarineSuffix) {
        return item.id;
    }

    trackRequestOtherNiazsanjiById(index: number, item: IRequestOtherNiazsanjiMarineSuffix) {
        return item.id;
    }

    trackNiazsanjiInputById(index: number, item: INiazsanjiInputMarineSuffix) {
        return item.id;
    }

    trackCourseTypeById(index: number, item: ICourseTypeMarineSuffix) {
        return item.id;
    }

    trackEducationalModuleById(index: number, item: IEducationalModuleMarineSuffix) {
        return item.id;
    }

    trackPersonById(index: number, item: IPersonMarineSuffix) {
        return item.id;
    }

    trackOrganizationChartById(index: number, item: IOrganizationChartMarineSuffix) {
        return item.id;
    }

    trackTeachingApproachById(index: number, item: ITeachingApproachMarineSuffix) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
