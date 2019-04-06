import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IEducationalHistoryMarineSuffix } from 'app/shared/model/educational-history-marine-suffix.model';
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {JhiAlertService} from "ng-jhipster/src/service/alert.service";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix/organization-chart-marine-suffix.service";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix/person-marine-suffix.service";
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {HttpErrorResponse, HttpResponse} from "@angular/common/http/src/response";

@Component({
    selector: 'mi-educational-history-marine-suffix-detail',
    templateUrl: './educational-history-marine-suffix-detail.component.html'
})
export class EducationalHistoryMarineSuffixDetailComponent implements OnInit {
    educationalHistory: IEducationalHistoryMarineSuffix;
    organizationcharts: IOrganizationChartMarineSuffix[];
    rows: number = 0;
    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute,
    private convertObjectDatesService : ConvertObjectDatesService,
    private treeUtilities: TreeUtilities,
    private personService: PersonMarineSuffixService,
    private organizationChartService: OrganizationChartMarineSuffixService,
    private jhiAlertService: JhiAlertService) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ educationalHistory }) => {
            this.educationalHistory = this.convertObjectDatesService.changeDate(educationalHistory);
            this.rows = this.educationalHistory.conversation.split('\n').length;
        });
        this.personService.find(this.educationalHistory.personId).subscribe(
            (resp: HttpResponse<IPersonMarineSuffix>) =>{
                let event = resp.body;
                this.educationalHistory.personFamily = event.fullName;
                this.educationalHistory["practicaljob"] = (event.practicaljobTitle ? event.practicaljobTitle : "") + " - " + (event.practicaljobCode ? event.practicaljobCode : "");
                this.educationalHistory["job"] = (event.jobTitle ? event.jobTitle : "") + " - " + (event.jobCode ? event.jobCode : "");
            });
        if(this.organizationChartService.organizationchartsAll){
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            let org = this.organizationcharts.find(a => a.id == this.educationalHistory.organizationChartId);
            this.educationalHistory.organizationChartTitle = org.fullTitle;
            this.educationalHistory.statusMeaning = this.treeUtilities.getStatusMeaning(this.organizationcharts, this.educationalHistory.status, this.educationalHistory.requestStatus);
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {

                    this.organizationcharts = res.body;
                    let org = this.organizationcharts.find(a => a.id == this.educationalHistory.organizationChartId);
                    this.educationalHistory.organizationChartTitle = org.fullTitle;
                    this.educationalHistory.statusMeaning = this.treeUtilities.getStatusMeaning(this.organizationcharts, this.educationalHistory.status, this.educationalHistory.requestStatus);
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }
    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
    previousState() {
        window.history.back();
    }
}
