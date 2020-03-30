import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model/final-niazsanji-report-marine-suffix.model';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {IFinalNiazsanjiReportPersonMarineSuffix} from "app/shared/model/final-niazsanji-report-person-marine-suffix.model";
import {FinalNiazsanjiReportPersonMarineSuffixService} from "app/entities/final-niazsanji-report-person-marine-suffix";
import {HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {JhiAlertService} from "ng-jhipster";
import {IEducationalModuleMarineSuffix} from "app/shared/model/educational-module-marine-suffix.model";
import {EducationalModuleMarineSuffixService} from "app/entities/educational-module-marine-suffix";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";

@Component({
    selector: 'mi-final-niazsanji-report-marine-suffix-detail',
    templateUrl: './final-niazsanji-report-marine-suffix-detail.component.html'
})
export class FinalNiazsanjiReportMarineSuffixDetailComponent implements OnInit {
    finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix;
    finalNiazsanjiReportPeople: IFinalNiazsanjiReportPersonMarineSuffix[];
    educationalmodules: IEducationalModuleMarineSuffix[];
    organizationcharts: IOrganizationChartMarineSuffix[];
    constructor(private activatedRoute: ActivatedRoute,private convertObjectDatesService : ConvertObjectDatesService,
        private finalNiazsanjiReportPersonService: FinalNiazsanjiReportPersonMarineSuffixService,
                private educationalModuleService: EducationalModuleMarineSuffixService,
                private organizationChartService: OrganizationChartMarineSuffixService,
        private jhiAlertService: JhiAlertService) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ finalNiazsanjiReport }) => {
            this.finalNiazsanjiReport = this.convertObjectDatesService.changeDate(finalNiazsanjiReport);
            if(this.educationalModuleService.educationalModules) {
                this.educationalmodules = this.educationalModuleService.educationalModules;
                this.loadOrgs();
            }
            else{
                this.educationalModuleService.query().subscribe(
                    (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                        this.educationalmodules = res.body;
                        this.loadOrgs();
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            }

            let criteria = [{
                key: "finalNiazsanjiReportId.equals",
                value: this.finalNiazsanjiReport.id
            }];
            this.finalNiazsanjiReportPersonService.query({
                page: 0,
                size: 20000,
                criteria,
                sort: ["id","asc"]
            })
                .subscribe(
                    (res: HttpResponse<IFinalNiazsanjiReportPersonMarineSuffix[]>) => this.finalNiazsanjiReportPeople = res.body,
                    (res: HttpErrorResponse) => this.onError(res.message)
                );

        });
    }
    loadOrgs(){
        if(this.organizationChartService.organizationchartsAll)
        {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            this.loadFinalData();
        }
        else{
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationcharts = res.body;
                    this.loadFinalData();
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }
    loadFinalData(){
        const educationalModule = this.educationalmodules.find(e => e.id == this.finalNiazsanjiReport.educationalModuleId);
        const orgChart = this.organizationcharts.find(o => o.id == this.finalNiazsanjiReport.organizationChartId);
        this.finalNiazsanjiReport = this.convertObjectDatesService.fillFinalNiazsanjiData(this.finalNiazsanjiReport, educationalModule, orgChart);
    }
    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
    previousState() {
        window.history.back();
    }
}
