import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import {ICourseTypeMarineSuffix} from "app/shared/model/course-type-marine-suffix.model";
import {CourseTypeMarineSuffixService} from "app/entities/course-type-marine-suffix";
import {IPreJobNiazsanjiMarineSuffix} from "app/shared/model/pre-job-niazsanji-marine-suffix.model";
import {IJobNiazsanjiMarineSuffix} from "app/shared/model/job-niazsanji-marine-suffix.model";
import {JobNiazsanjiMarineSuffixService} from "app/entities/job-niazsanji-marine-suffix/job-niazsanji-marine-suffix.service";
import {IRestrictionMarineSuffix} from "app/shared/model/restriction-marine-suffix.model";
import {ITeachingApproachMarineSuffix} from "app/shared/model/teaching-approach-marine-suffix.model";
import {RestrictionMarineSuffixService} from "app/entities/restriction-marine-suffix";
import {TeachingApproachMarineSuffixService} from "app/entities/teaching-approach-marine-suffix";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

@Component({
    selector: 'mi-job-niazsanji-marine-suffix-update',
    templateUrl: './job-niazsanji-marine-suffix-update.component.html'
})
export class JobNiazsanjiMarineSuffixUpdateComponent implements OnInit {
    jobNiazsanji: IJobNiazsanjiMarineSuffix;
    isSaving: boolean;

    documents: IDocumentMarineSuffix[];
    coursetypes: ICourseTypeMarineSuffix[];
    preJobNiazsanjis: IPreJobNiazsanjiMarineSuffix[];

    educationalmodules: IEducationalModuleMarineSuffix[];

    people: IPersonMarineSuffix[];

    organizationcharts: IOrganizationChartMarineSuffix[];

    teachingapproaches: ITeachingApproachMarineSuffix[];
    restrictions: IRestrictionMarineSuffix[];

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        private courseTypeService: CourseTypeMarineSuffixService,
        protected jobNiazsanjiService: JobNiazsanjiMarineSuffixService,
        protected documentService: DocumentMarineSuffixService,
        protected educationalModuleService: EducationalModuleMarineSuffixService,
        protected personService: PersonMarineSuffixService,
        protected organizationChartService: OrganizationChartMarineSuffixService,
        protected restrictionService: RestrictionMarineSuffixService,
        protected teachingApproachService: TeachingApproachMarineSuffixService,
        private convertObjectDatesService: ConvertObjectDatesService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ jobNiazsanji }) => {
            this.jobNiazsanji = jobNiazsanji;
        });
        if(this.educationalModuleService.educationalModules){
            this.educationalmodules = this.educationalModuleService.educationalModules;
        }
        else {
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalmodules = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
        if(this.personService.people){
            this.people = this.convertObjectDatesService.goClone(this.personService.people);
        }
        else {
            this.personService.query().subscribe(
                (res: HttpResponse<IPersonMarineSuffix[]>) => {
                    this.people = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
        if(!this.organizationChartService.organizationchartsAll) {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationcharts = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
        else{
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
        }
        this.courseTypeService.query().subscribe(
            (res: HttpResponse<ICourseTypeMarineSuffix[]>) => {
                this.coursetypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );

        this.restrictionService.query().subscribe(
            (res: HttpResponse<IRestrictionMarineSuffix[]>) => {
                this.restrictions = res.body;
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

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.jobNiazsanji.id !== undefined) {
            this.subscribeToSaveResponse(this.jobNiazsanjiService.update(this.jobNiazsanji));
        } else {
            this.subscribeToSaveResponse(this.jobNiazsanjiService.create(this.jobNiazsanji));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IJobNiazsanjiMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IJobNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
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

    trackPreJobNiazsanjiById(index: number, item: IPreJobNiazsanjiMarineSuffix) {
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
