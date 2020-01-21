import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { INiazsanjiOtherMarineSuffix } from 'app/shared/model/niazsanji-other-marine-suffix.model';
import { NiazsanjiOtherMarineSuffixService } from './niazsanji-other-marine-suffix.service';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { IRestrictionMarineSuffix } from 'app/shared/model/restriction-marine-suffix.model';
import { RestrictionMarineSuffixService } from 'app/entities/restriction-marine-suffix';
import { INiazsanjiInputMarineSuffix } from 'app/shared/model/niazsanji-input-marine-suffix.model';
import { NiazsanjiInputMarineSuffixService } from 'app/entities/niazsanji-input-marine-suffix';
import { ICourseTypeMarineSuffix } from 'app/shared/model/course-type-marine-suffix.model';
import { CourseTypeMarineSuffixService } from 'app/entities/course-type-marine-suffix';
import { IRequestOtherNiazsanjiMarineSuffix } from 'app/shared/model/request-other-niazsanji-marine-suffix.model';
import { RequestOtherNiazsanjiMarineSuffixService } from 'app/entities/request-other-niazsanji-marine-suffix';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import { ITeachingApproachMarineSuffix } from 'app/shared/model/teaching-approach-marine-suffix.model';
import { TeachingApproachMarineSuffixService } from 'app/entities/teaching-approach-marine-suffix';

@Component({
    selector: 'mi-niazsanji-other-marine-suffix-update',
    templateUrl: './niazsanji-other-marine-suffix-update.component.html'
})
export class NiazsanjiOtherMarineSuffixUpdateComponent implements OnInit {
    niazsanjiOther: INiazsanjiOtherMarineSuffix;
    isSaving: boolean;

    documents: IDocumentMarineSuffix[];

    restrictions: IRestrictionMarineSuffix[];

    niazsanjiinputs: INiazsanjiInputMarineSuffix[];

    coursetypes: ICourseTypeMarineSuffix[];

    requestotherniazsanjis: IRequestOtherNiazsanjiMarineSuffix[];

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
        protected niazsanjiOtherService: NiazsanjiOtherMarineSuffixService,
        protected documentService: DocumentMarineSuffixService,
        protected restrictionService: RestrictionMarineSuffixService,
        protected niazsanjiInputService: NiazsanjiInputMarineSuffixService,
        protected courseTypeService: CourseTypeMarineSuffixService,
        protected requestOtherNiazsanjiService: RequestOtherNiazsanjiMarineSuffixService,
        protected educationalModuleService: EducationalModuleMarineSuffixService,
        protected personService: PersonMarineSuffixService,
        protected organizationChartService: OrganizationChartMarineSuffixService,
        protected teachingApproachService: TeachingApproachMarineSuffixService,
        protected activatedRoute: ActivatedRoute,
        protected router: Router
    ) {}

    change(i){
        this.router.navigateByUrl(i);
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ niazsanjiOther }) => {
            this.niazsanjiOther = niazsanjiOther;
            this.createDate = this.niazsanjiOther.createDate != null ? this.niazsanjiOther.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate = this.niazsanjiOther.modifyDate != null ? this.niazsanjiOther.modifyDate.format(DATE_TIME_FORMAT) : null;
            this.archivedDate = this.niazsanjiOther.archivedDate != null ? this.niazsanjiOther.archivedDate.format(DATE_TIME_FORMAT) : null;
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
        this.requestOtherNiazsanjiService.query().subscribe(
            (res: HttpResponse<IRequestOtherNiazsanjiMarineSuffix[]>) => {
                this.requestotherniazsanjis = res.body;
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
        this.niazsanjiOther.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.niazsanjiOther.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        this.niazsanjiOther.archivedDate = this.archivedDate != null ? moment(this.archivedDate, DATE_TIME_FORMAT) : null;
        if (this.niazsanjiOther.id !== undefined) {
            this.subscribeToSaveResponse(this.niazsanjiOtherService.update(this.niazsanjiOther));
        } else {
            this.subscribeToSaveResponse(this.niazsanjiOtherService.create(this.niazsanjiOther));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<INiazsanjiOtherMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<INiazsanjiOtherMarineSuffix>) => this.onSaveSuccess(),
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

    trackNiazsanjiInputById(index: number, item: INiazsanjiInputMarineSuffix) {
        return item.id;
    }

    trackCourseTypeById(index: number, item: ICourseTypeMarineSuffix) {
        return item.id;
    }

    trackRequestOtherNiazsanjiById(index: number, item: IRequestOtherNiazsanjiMarineSuffix) {
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
