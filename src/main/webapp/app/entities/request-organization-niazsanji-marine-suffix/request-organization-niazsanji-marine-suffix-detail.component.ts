import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {
    IRequestOrganizationNiazsanjiMarineSuffix
} from 'app/shared/model/request-organization-niazsanji-marine-suffix.model';
import {RequestOrganizationNiazsanjiMarineSuffixService} from 'app/entities/request-organization-niazsanji-marine-suffix';
import {Observable} from "rxjs";
import {HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {Principal} from "app/core";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {RequestStatus} from "app/shared/model/enums/RequestStatus";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";
import {EducationalModuleMarineSuffixService} from "app/entities/educational-module-marine-suffix";
import {IEducationalModuleMarineSuffix} from "app/shared/model/educational-module-marine-suffix.model";

@Component({
    selector: 'mi-request-organization-niazsanji-marine-suffix-detail',
    templateUrl: './request-organization-niazsanji-marine-suffix-detail.component.html',
    styleUrls: ['./request-organization-niazsanji-marine-suffix.scss']
})
export class RequestOrganizationNiazsanjiMarineSuffixDetailComponent implements OnInit {
    requestOrganizationNiazsanji: IRequestOrganizationNiazsanjiMarineSuffix;
    organizationCharts: IOrganizationChartMarineSuffix[];
    educationalModule: IEducationalModuleMarineSuffix;
    rows: number = 0;
    constructor(private activatedRoute: ActivatedRoute,
        private requestOrganizationNiazsanjiMarineSuffixService :RequestOrganizationNiazsanjiMarineSuffixService,
        private organizationChartService :OrganizationChartMarineSuffixService,
        private educationalModuleService :EducationalModuleMarineSuffixService,
        private principal: Principal,private convertObjectDatesService : ConvertObjectDatesService,
        private treeUtils: TreeUtilities) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ requestOrganizationNiazsanji }) => {
            this.principal.identity().then(account => {
                this.requestOrganizationNiazsanji = requestOrganizationNiazsanji;
                this.educationalModuleService.find(this.requestOrganizationNiazsanji.educationalModuleId).subscribe(
                    (resp: HttpResponse<IEducationalModuleMarineSuffix>) => {
                        this.educationalModule = resp.body;
                        this.requestOrganizationNiazsanji.totalLearningTime = (this.educationalModule.learningTimePractical == undefined ? 0 : this.educationalModule.learningTimePractical) +
                            (this.educationalModule.learningTimeTheorical == undefined ? 0 : this.educationalModule.learningTimeTheorical);
                        this.requestOrganizationNiazsanji.skillLevelOfSkillTitle = this.educationalModule.skillableLevelOfSkillTitle;
                    },
                (res: HttpErrorResponse) => this.onSaveError()
                )
                this.rows = this.requestOrganizationNiazsanji.conversation.split('\n').length;
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
                    if(this.requestOrganizationNiazsanji.requestStatus === 'NEW') {
                        this.requestOrganizationNiazsanji.requestStatus = RequestStatus.READ;
                        this.subscribeToSaveResponse(this.requestOrganizationNiazsanjiMarineSuffixService.update(this.requestOrganizationNiazsanji));
                    }
                }*/
                this.requestOrganizationNiazsanji = this.convertObjectDatesService.changeDate(requestOrganizationNiazsanji);
            });

        });

    }
    private onError(errorMessage: string) {
        //this.jhiAlertService.error(errorMessage, null, null);
    }
    getStatus(){
        this.requestOrganizationNiazsanji.statusMeaning = this.treeUtils.getStatusMeaning(this.organizationCharts, this.requestOrganizationNiazsanji.status,
            this.requestOrganizationNiazsanji.requestStatus);
    }
    private subscribeToSaveResponse(result: Observable<HttpResponse<IRequestOrganizationNiazsanjiMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IRequestOrganizationNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }
    private onSaveSuccess() {
    }

    private onSaveError() {
    }
    previousState() {
        window.history.back();
    }
}
