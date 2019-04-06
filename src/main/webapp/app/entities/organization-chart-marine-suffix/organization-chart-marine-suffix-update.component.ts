import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from './organization-chart-marine-suffix.service';

@Component({
    selector: 'mi-organization-chart-marine-suffix-update',
    templateUrl: './organization-chart-marine-suffix-update.component.html'
})
export class OrganizationChartMarineSuffixUpdateComponent implements OnInit {
    private _organizationChart: IOrganizationChartMarineSuffix;
    isSaving: boolean;

    organizationcharts: IOrganizationChartMarineSuffix[];
    createDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {

        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ organizationChart }) => {

            this.organizationChart = organizationChart;
        });
        if(this.organizationChartService.organizationchartsAll)
        {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {

                    this.organizationcharts = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.organizationChart.status = 0;
        this.organizationChart.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        if(this.organizationChart.parentId == 0)
            this.organizationChart.parentId = null;
        if (this.organizationChart.id !== undefined) {
            this.subscribeToSaveResponse(this.organizationChartService.update(this.organizationChart));
        } else {
            this.subscribeToSaveResponse(this.organizationChartService.create(this.organizationChart));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IOrganizationChartMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IOrganizationChartMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError(res)
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError(res) {

        this.isSaving = false;
        this.onError(res)
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage);
    }

    trackOrganizationChartById(index: number, item: IOrganizationChartMarineSuffix) {
        return item.id;
    }
    get organizationChart() {
        return this._organizationChart;
    }

    set organizationChart(organizationChart: IOrganizationChartMarineSuffix) {

        this._organizationChart = organizationChart;
        if(!this._organizationChart.id)
        {

            this.activatedRoute.params.subscribe((params) => {

                this._organizationChart.parentId = params['parentId'];
             });
        }
        this.createDate = moment(organizationChart.createDate).format(DATE_TIME_FORMAT);
    }
}
