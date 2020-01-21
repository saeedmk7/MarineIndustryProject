import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IRequestOtherNiazsanjiMarineSuffix } from 'app/shared/model/request-other-niazsanji-marine-suffix.model';
import {HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {IEducationalModuleMarineSuffix} from "app/shared/model/educational-module-marine-suffix.model";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";
import {EducationalModuleMarineSuffixService} from "app/entities/educational-module-marine-suffix";
import {Principal} from "app/core";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";

@Component({
    selector: 'mi-request-other-niazsanji-marine-suffix-detail',
    templateUrl: './request-other-niazsanji-marine-suffix-detail.component.html'
})
export class RequestOtherNiazsanjiMarineSuffixDetailComponent implements OnInit {
    requestOtherNiazsanji: IRequestOtherNiazsanjiMarineSuffix;
    organizationCharts: IOrganizationChartMarineSuffix[];
    educationalModule: IEducationalModuleMarineSuffix;
    rows: number = 0;
    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute,
                private organizationChartService :OrganizationChartMarineSuffixService,
                private educationalModuleService :EducationalModuleMarineSuffixService,
                private principal: Principal,private convertObjectDatesService : ConvertObjectDatesService,
                private treeUtils: TreeUtilities) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ requestOtherNiazsanji }) => {
            this.requestOtherNiazsanji = requestOtherNiazsanji;
            this.educationalModuleService.find(this.requestOtherNiazsanji.educationalModuleId).subscribe(
                (resp: HttpResponse<IEducationalModuleMarineSuffix>) => {
                    this.educationalModule = resp.body;
                    this.requestOtherNiazsanji.totalLearningTime = (!this.educationalModule.learningTimePractical  ? 0 : this.educationalModule.learningTimePractical) +
                        (!this.educationalModule.learningTimeTheorical ? 0 : this.educationalModule.learningTimeTheorical);
                    this.requestOtherNiazsanji.skillLevelOfSkillTitle = this.educationalModule.skillableLevelOfSkillTitle;
                },
                (res: HttpErrorResponse) => this.onSaveError()
            )
            this.rows = this.requestOtherNiazsanji.conversation.split('\n').length;
            if(this.organizationChartService.organizationchartsAll){
                this.organizationCharts = this.organizationChartService.organizationchartsAll;
                this.getStatus();
            }
            else{
                this.organizationChartService.query().subscribe((resp: HttpResponse<IOrganizationChartMarineSuffix[]>) =>{
                        this.organizationCharts = resp.body;
                        this.getStatus();
                    },
                    (res: HttpErrorResponse) => this.onError(res.message));
            }

            /*if(account.authorities.find(a => a == "ROLE_ADMIN") !== undefined)
            {
                if(this.requestOtherNiazsanji.requestStatus === 'NEW') {
                    this.requestOtherNiazsanji.requestStatus = RequestStatus.READ;
                    this.subscribeToSaveResponse(this.requestOtherNiazsanjiMarineSuffixService.update(this.requestOtherNiazsanji));
                }
            }*/
            this.requestOtherNiazsanji = this.convertObjectDatesService.changeDate(requestOtherNiazsanji);
        });
    }
    private onError(errorMessage: string) {
        //this.jhiAlertService.error(errorMessage, null, null);
    }
    getStatus(){
        this.requestOtherNiazsanji.statusMeaning = this.treeUtils.getStatusMeaning(this.organizationCharts, this.requestOtherNiazsanji.status,
            this.requestOtherNiazsanji.requestStatus);
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    private onSaveSuccess() {
    }

    private onSaveError() {
    }
    previousState() {
        window.history.back();
    }
}
