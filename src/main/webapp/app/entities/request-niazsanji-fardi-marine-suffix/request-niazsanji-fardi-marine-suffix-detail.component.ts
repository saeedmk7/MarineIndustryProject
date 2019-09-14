import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {JhiAlertService, JhiDataUtils, JhiEventManager} from 'ng-jhipster';

import { IRequestNiazsanjiFardiMarineSuffix } from 'app/shared/model/request-niazsanji-fardi-marine-suffix.model';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {IFinalNiazsanjiReportPersonMarineSuffix} from "app/shared/model/final-niazsanji-report-person-marine-suffix.model";
import {IEducationalModuleJobMarineSuffix} from "app/shared/model/educational-module-job-marine-suffix.model";
import {FinalNiazsanjiReportPersonMarineSuffixService} from "app/entities/final-niazsanji-report-person-marine-suffix";
import {IFinalNiazsanjiReportMarineSuffix} from "app/shared/model/final-niazsanji-report-marine-suffix.model";
import {FinalNiazsanjiReportMarineSuffixService} from "app/entities/final-niazsanji-report-marine-suffix";
import {
    EducationalModuleMarineSuffix,
    IEducationalModuleMarineSuffix
} from "app/shared/model/educational-module-marine-suffix.model";
import {EducationalModuleMarineSuffixService} from "app/entities/educational-module-marine-suffix";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {IHomePagePersonEducationalModule} from "app/shared/model/custom/home-page-person-educational-module";

@Component({
    selector: 'mi-request-niazsanji-fardi-marine-suffix-detail',
    templateUrl: './request-niazsanji-fardi-marine-suffix-detail.component.html',
    styleUrls:['./request-niazsanji-fardi-marine-suffix.scss']
})
export class RequestNiazsanjiFardiMarineSuffixDetailComponent implements OnInit {
    requestNiazsanjiFardi: IRequestNiazsanjiFardiMarineSuffix;
    finalNiazsanjiReports: IFinalNiazsanjiReportMarineSuffix[];
    homePagePersonEducationalModules: IHomePagePersonEducationalModule[] = [];
    approvedHour: number = 0;
    approvedLevel: string;

    allHour: number = 0;
    allLevel: string;
    rows: number = 0;
    organizationcharts: IOrganizationChartMarineSuffix[];
    hasNoRow:boolean = false;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute,
        private convertObjectDatesService : ConvertObjectDatesService,
        private treeUtilities: TreeUtilities,
        private finalNiazsanjiReportPersonMarineSuffixService: FinalNiazsanjiReportPersonMarineSuffixService,
        private personService: PersonMarineSuffixService,
        private finalNiazsanjiReportMarineSuffixService: FinalNiazsanjiReportMarineSuffixService,
        private educationalModuleMarineSuffixService: EducationalModuleMarineSuffixService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private jhiAlertService: JhiAlertService) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ requestNiazsanjiFardi }) => {
            this.requestNiazsanjiFardi = this.convertObjectDatesService.changeDate(requestNiazsanjiFardi);
            this.rows = this.requestNiazsanjiFardi.conversation.split('\n').length;
            if(this.requestNiazsanjiFardi.approvedEducationalModuleId)
            {
                this.educationalModuleMarineSuffixService.find(this.requestNiazsanjiFardi.approvedEducationalModuleId).subscribe(
                    (resp: HttpResponse<IEducationalModuleMarineSuffix>) =>{
                    let event = resp.body;
                    this.approvedHour = (event.learningTimePractical ? event.learningTimePractical : 0) + (event.learningTimeTheorical ? event.learningTimeTheorical : 0);
                    this.approvedLevel = event.skillableLevelOfSkillTitle;
                });

            }
            if(this.requestNiazsanjiFardi.allEducationalModuleId)
            {
                this.educationalModuleMarineSuffixService.find(this.requestNiazsanjiFardi.allEducationalModuleId).subscribe(
                    (resp: HttpResponse<IEducationalModuleMarineSuffix>) =>{
                        let event = resp.body;
                        this.allHour = (event.learningTimePractical ? event.learningTimePractical : 0) + (event.learningTimeTheorical ? event.learningTimeTheorical : 0);
                        this.allLevel = event.skillableLevelOfSkillTitle;
                    });
            }
            this.personService.find(this.requestNiazsanjiFardi.personId).subscribe(
                (resp: HttpResponse<IPersonMarineSuffix>) =>{
                    let event = resp.body;
                    this.requestNiazsanjiFardi.personFamily = event.fullName;
                    this.requestNiazsanjiFardi["practicaljob"] = (event.practicaljobTitle ? event.practicaljobTitle : "") + " - " + (event.practicaljobCode ? event.practicaljobCode : "");
                    this.requestNiazsanjiFardi["job"] = (event.jobTitle ? event.jobTitle : "") + " - " + (event.jobCode ? event.jobCode : "");


                    this.onPersonChange(event);
                });

            if(this.organizationChartService.organizationchartsAll){
                this.organizationcharts = this.organizationChartService.organizationchartsAll;
                let org = this.organizationcharts.find(a => a.id == this.requestNiazsanjiFardi.organizationChartId);
                this.requestNiazsanjiFardi.organizationChartTitle = org.fullTitle;
                this.requestNiazsanjiFardi.statusMeaning = this.treeUtilities.getStatusMeaning(this.organizationcharts, this.requestNiazsanjiFardi.status, this.requestNiazsanjiFardi.requestStatus);
            }
            else {
                this.organizationChartService.query().subscribe(
                    (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {

                        this.organizationcharts = res.body;
                        let org = this.organizationcharts.find(a => a.id == this.requestNiazsanjiFardi.organizationChartId);
                        this.requestNiazsanjiFardi.organizationChartTitle = org.fullTitle;
                        this.requestNiazsanjiFardi.statusMeaning = this.treeUtilities.getStatusMeaning(this.organizationcharts, this.requestNiazsanjiFardi.status, this.requestNiazsanjiFardi.requestStatus);
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            }

        });
    }


    previousState() {
        window.history.back();
    }


    onPersonChange(event){

        if(event.id){
            /*let criteria = [{
                key:'personId.equals',
                value: event.id
            }];
            this.finalNiazsanjiReportPersonMarineSuffixService.query({
                page: 0,
                size: 20000,
                criteria,
                sort: ["id","asc"]
            }).subscribe((resp: HttpResponse<IFinalNiazsanjiReportPersonMarineSuffix[]>) => this.showEducations(resp.body),
                (error) => this.onError("موردی یافت نشد"));*/

            this.finalNiazsanjiReportMarineSuffixService.getHomePagePersonEducationalModule(event.id).subscribe((resp: HttpResponse<IHomePagePersonEducationalModule[]>) => {

                    this.homePagePersonEducationalModules = resp.body.filter(a => a.status > 0).sort((a,b) => (a.runDate > b.runDate) ? 1 : (a.runDate < b.runDate) ? -1 : 0);
                    this.homePagePersonEducationalModules.forEach(a => {
                        a.totalLearningTime = a.learningTimePractical == undefined ? 0 : a.learningTimePractical + a.learningTimeTheorical == undefined ? 0 : a.learningTimeTheorical;
                        switch (a.status) {
                            case 100:
                                a.statusMeaning = "خاتمه دوره";
                                break;
                            case 90:
                                a.statusMeaning = "اجرا شده";
                                break;
                            case 80:
                                a.statusMeaning = "برنامه ریزی شده";
                                break;
                            case 70:
                                a.statusMeaning = "تصویب شوراء";
                                break;
                            case 0:
                                a.statusMeaning = "شناسنامه آموزشی";
                                break;
                        }
                    });
                    //this.makePersonHourPieChart(resp.body);
                },
                (res: HttpErrorResponse) => this.onError(res.message));
        }
    }

    showEducations(resp: IFinalNiazsanjiReportPersonMarineSuffix[]){

        if(resp.length) {
            this.hasNoRow = false;
            let ids: number[] = [];
            resp.forEach(a => {
                ids.push(a.finalNiazsanjiReportId);
            });
            let criteria = [{
                key: 'id.in',
                value: ids
            }];
            this.finalNiazsanjiReportMarineSuffixService.query({
                page: 0,
                size: 20000,
                criteria,
                sort: ["id", "asc"]
            }).subscribe((resp: HttpResponse<IFinalNiazsanjiReportMarineSuffix[]>) => {

                    this.finalNiazsanjiReports = resp.body;
                },
                (error) => this.onError("موردی یافت نشد"));
        }
        else {
            //this.finalNiazsanjiReports.push("این فرد تا به الان هیچ دوره ای شرکت نکرده است.");
            this.finalNiazsanjiReports = [];
            this.hasNoRow = true;
        }
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

}
