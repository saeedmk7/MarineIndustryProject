import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { INiazsanjiFardiMarineSuffix } from 'app/shared/model/niazsanji-fardi-marine-suffix.model';
import { NiazsanjiFardiMarineSuffixService } from './niazsanji-fardi-marine-suffix.service';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { IRequestNiazsanjiFardiMarineSuffix } from 'app/shared/model/request-niazsanji-fardi-marine-suffix.model';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import {ICourseTypeMarineSuffix} from "app/shared/model/course-type-marine-suffix.model";
import {CourseTypeMarineSuffixService} from "app/entities/course-type-marine-suffix";

@Component({
    selector: 'mi-niazsanji-fardi-marine-suffix-update',
    templateUrl: './niazsanji-fardi-marine-suffix-update.component.html'
})
export class NiazsanjiFardiMarineSuffixUpdateComponent implements OnInit {
    niazsanjiFardi: INiazsanjiFardiMarineSuffix;
    isSaving: boolean;

    documents: IDocumentMarineSuffix[];
    coursetypes: ICourseTypeMarineSuffix[];
    requestniazsanjifardis: IRequestNiazsanjiFardiMarineSuffix[];

    educationalmodules: IEducationalModuleMarineSuffix[];

    people: IPersonMarineSuffix[];

    organizationcharts: IOrganizationChartMarineSuffix[];
    createDate: string;
    modifyDate: string;
    archivedDate: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        private courseTypeService: CourseTypeMarineSuffixService,
        protected niazsanjiFardiService: NiazsanjiFardiMarineSuffixService,
        protected documentService: DocumentMarineSuffixService,
        protected educationalModuleService: EducationalModuleMarineSuffixService,
        protected personService: PersonMarineSuffixService,
        protected organizationChartService: OrganizationChartMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ niazsanjiFardi }) => {
            this.niazsanjiFardi = niazsanjiFardi;
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
            this.people = this.personService.people;
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
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.niazsanjiFardi.id !== undefined) {
            this.subscribeToSaveResponse(this.niazsanjiFardiService.update(this.niazsanjiFardi));
        } else {
            this.subscribeToSaveResponse(this.niazsanjiFardiService.create(this.niazsanjiFardi));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<INiazsanjiFardiMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<INiazsanjiFardiMarineSuffix>) => this.onSaveSuccess(),
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

    trackRequestNiazsanjiFardiById(index: number, item: IRequestNiazsanjiFardiMarineSuffix) {
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
