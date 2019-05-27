import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import {JhiAlertService, JhiDataUtils} from 'ng-jhipster';

import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model/final-niazsanji-report-marine-suffix.model';
import { FinalNiazsanjiReportMarineSuffixService } from './final-niazsanji-report-marine-suffix.service';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';
import Bytes = jest.Bytes;
import {IReportMarineSuffix} from "app/shared/model/report-marine-suffix.model";
import {trigger} from "@angular/animations";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";
import {FinalNiazsanjiReportPersonMarineSuffixService} from "app/entities/final-niazsanji-report-person-marine-suffix";
import {IJobMarineSuffix} from "app/shared/model/job-marine-suffix.model";
import {
    FinalNiazsanjiReportPersonMarineSuffix,
    IFinalNiazsanjiReportPersonMarineSuffix
} from "app/shared/model/final-niazsanji-report-person-marine-suffix.model";
import {CourseTypeMarineSuffix, ICourseTypeMarineSuffix} from "app/shared/model/course-type-marine-suffix.model";
import {CourseTypeMarineSuffixService} from "app/entities/course-type-marine-suffix";

@Component({
    selector: 'mi-final-niazsanji-report-marine-suffix-update',
    templateUrl: './final-niazsanji-report-marine-suffix-update.component.html'
})
export class FinalNiazsanjiReportMarineSuffixUpdateComponent implements OnInit {
    private _finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix;
    _report : IReportMarineSuffix;
    isSaving: boolean;

    educationalmodules: IEducationalModuleMarineSuffix[];
    organizationCharts: IOrganizationChartMarineSuffix[];
    finalNiazsanjiReportPeople: IFinalNiazsanjiReportPersonMarineSuffix[];
    people: IPersonMarineSuffix[];
    selectedPeople: IPersonMarineSuffix[];
    selectedPersonIds: number[];
    coursetypes: ICourseTypeMarineSuffix[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private finalNiazsanjiReportService: FinalNiazsanjiReportMarineSuffixService,
        private personService: PersonMarineSuffixService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private documentService: DocumentMarineSuffixService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private dataUtils: JhiDataUtils,
        private finalNiazsanjiReportPersonService: FinalNiazsanjiReportPersonMarineSuffixService,
        private courseTypeService: CourseTypeMarineSuffixService
    ) { }

    ngOnInit() {
        this.isSaving = false;

        this.activatedRoute.data.subscribe(({ finalNiazsanjiReport }) => {
            this.finalNiazsanjiReport = finalNiazsanjiReport;

            let criteria = [{
                key: 'finalNiazsanjiReportId.equals',
                value: this.finalNiazsanjiReport.id
            }];

            this.finalNiazsanjiReportPersonService.query({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: ["id", "asc"]
            })
            .subscribe(
                (res: HttpResponse<IFinalNiazsanjiReportPersonMarineSuffix[]>) => {

                    this.finalNiazsanjiReportPeople = res.body;
                    this.selectedPersonIds = this.finalNiazsanjiReportPeople.map(a => a.personId);

                    if(this.personService.people) {
                        this.people = this.personService.people;
                        this.selectedPeople = this.people.filter(a => this.selectedPersonIds.includes(a.id));
                    }
                    else{
                        this.personService.query().subscribe(
                            (res: HttpResponse<IPersonMarineSuffix[]>) => {

                                this.people = res.body;
                                this.selectedPeople = this.people.filter(a => this.selectedPersonIds.includes(a.id));
                            },
                            (res: HttpErrorResponse) => this.onError(res.message)
                        );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );


        });
        if(this.educationalModuleService.educationalModules) {
            this.educationalmodules = this.educationalModuleService.educationalModules;
        }
        else{
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalmodules = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
        if(this.organizationChartService.organizationchartsAll) {
            this.organizationCharts = this.organizationChartService.organizationchartsAll;
        }
        else{
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationCharts = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
        this.courseTypeService.query().subscribe((resp: HttpResponse<ICourseTypeMarineSuffix[]>) =>{
            this.coursetypes = resp.body;
        },
            (res: HttpErrorResponse) => this.onError(res.message));
    }
    change(i){

        this.router.navigateByUrl(i);
    }
    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;


        const finalSelectedIds = this.selectedPeople.map(a => a.id);
        if(finalSelectedIds.length <= 0) {
            this.onError('حداقل یک نفر را باید انتخاب نمائید.');
            return;
        }
        const  finalPersonIds = this.finalNiazsanjiReportPeople.map(a => a.personId);
        const  mustDeletedPersonIds = finalPersonIds.filter(a => !finalSelectedIds.includes(a));
        const  nustDelete = this.finalNiazsanjiReportPeople.filter(a => mustDeletedPersonIds.includes(a.personId));
        nustDelete.forEach(a => {
            this.finalNiazsanjiReportPersonService.delete(a.id).subscribe(response => {});
        });
        const  mustInsertPersonIds = finalSelectedIds.filter(a => !finalPersonIds.includes(a));
        const  mustInsert = this.people.filter(a => mustInsertPersonIds.includes(a.id));
        mustInsert.forEach(a => {
            let final: IFinalNiazsanjiReportPersonMarineSuffix = new FinalNiazsanjiReportPersonMarineSuffix();
            final.personId = a.id;
            final.finalNiazsanjiReportId = this.finalNiazsanjiReport.id;
            final.archived = false;
            final.niazSanjiSource = this.finalNiazsanjiReport.niazSanjiSource;
            final.priceCost = this.finalNiazsanjiReport.priceCost;
            final.sourceId = this.finalNiazsanjiReportPeople[0].sourceId;
            final.status = 0;
            this.finalNiazsanjiReportPersonService.create(final).subscribe((resp: IFinalNiazsanjiReportPersonMarineSuffix) => {

            });
        });

        if (this.finalNiazsanjiReport.id !== undefined) {
            this.subscribeToSaveResponse(this.finalNiazsanjiReportService.update(this.finalNiazsanjiReport));
        } else {
            this.subscribeToSaveResponse(this.finalNiazsanjiReportService.create(this.finalNiazsanjiReport));
        }
    }
    private subscribeToSaveResponse(result: Observable<HttpResponse<IFinalNiazsanjiReportMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IFinalNiazsanjiReportMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackPersonById(index: number, item: IPersonMarineSuffix) {
        return item.id;
    }

    trackEducationalModuleById(index: number, item: IEducationalModuleMarineSuffix) {
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
    get finalNiazsanjiReport() {
        return this._finalNiazsanjiReport;
    }

    set finalNiazsanjiReport(finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix) {
        this._finalNiazsanjiReport = finalNiazsanjiReport;
    }
    /*set report(report: IReportMarineSuffix) {
        this._report = report;
    }*/
}
