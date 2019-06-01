import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IRequestEducationalModuleMarineSuffix } from 'app/shared/model/request-educational-module-marine-suffix.model';
import {RequestStatus} from "app/shared/model/enums/RequestStatus";
import {Principal} from "app/core";
import {Observable} from "rxjs";
import {HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {RequestEducationalModuleMarineSuffixService} from "app/entities/request-educational-module-marine-suffix/request-educational-module-marine-suffix.service";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {IPersonMarineSuffix, PersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";

@Component({
    selector: 'mi-request-educational-module-marine-suffix-detail',
    templateUrl: './request-educational-module-marine-suffix-detail.component.html'
})
export class RequestEducationalModuleMarineSuffixDetailComponent implements OnInit {
    requestEducationalModule: IRequestEducationalModuleMarineSuffix;
    organizationcharts: IOrganizationChartMarineSuffix[];
    rows: number = 0;

    constructor(private dataUtils: JhiDataUtils,
        private activatedRoute: ActivatedRoute,
        private principal: Principal,
        private convertObjectDatesService: ConvertObjectDatesService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private treeUtilities: TreeUtilities,
        private personService: PersonMarineSuffixService,
        private requestEducationalModuleMarineSuffixService: RequestEducationalModuleMarineSuffixService) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ requestEducationalModule }) => {
            this.principal.identity().then(account => {
                this.requestEducationalModule = requestEducationalModule;
                this.rows = this.requestEducationalModule.conversation.split('\n').length;
                this.requestEducationalModule = this.convertObjectDatesService.changeDate(requestEducationalModule);
                //this.requestEducationalModule.statusMeaning = this.treeUtilities.getStatusMeaning(this.organizationcharts, this.educationalHistory.status, this.educationalHistory.requestStatus);
                let criteria = [{
                    key: 'nationalId.equals',
                    value: this.requestEducationalModule.createUserLogin
                }];
                this.personService.query({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: ["id", "asc"]
                }).subscribe((resp: HttpResponse<IPersonMarineSuffix[]>) => {
                        const orgPeople = resp.body;
                        if (orgPeople.length > 0) {
                            this.requestEducationalModule.createUserLoginName = orgPeople[0].fullName;
                        }
                    },
                    (error) => this.onSaveError());

                if(this.organizationChartService.organizationchartsAll){
                    this.organizationcharts = this.organizationChartService.organizationchartsAll;
                    //let org = this.organizationcharts.find(a => a.id == this.requestEducationalModule.organizationChartId);
                    //this.requestEducationalModule.organizationChartTitle = org.fullTitle;
                    this.requestEducationalModule.statusMeaning = this.treeUtilities.getStatusMeaning(this.organizationcharts, this.requestEducationalModule.status, this.requestEducationalModule.requestStatus);
                }
                else {
                    this.organizationChartService.query().subscribe(
                        (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {

                            this.organizationcharts = res.body;
                            //let org = this.organizationcharts.find(a => a.id == this.requestEducationalModule.organizationChartId);
                            //this.requestEducationalModule.organizationChartTitle = org.fullTitle;
                            this.requestEducationalModule.statusMeaning = this.treeUtilities.getStatusMeaning(this.organizationcharts, this.requestEducationalModule.status, this.requestEducationalModule.requestStatus);
                        },
                        (res: HttpErrorResponse) => this.onSaveError()
                    );
                }
            });

        });
    }
    private subscribeToSaveResponse(result: Observable<HttpResponse<IRequestEducationalModuleMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IRequestEducationalModuleMarineSuffix>) => this.onSaveSuccess(),
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
