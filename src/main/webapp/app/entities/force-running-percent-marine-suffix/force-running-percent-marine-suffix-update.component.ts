import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IForceRunningPercentMarineSuffix } from 'app/shared/model/force-running-percent-marine-suffix.model';
import { ForceRunningPercentMarineSuffixService } from './force-running-percent-marine-suffix.service';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import {MONTHS} from "app/shared/constants/months.constants";
import {GREGORIAN_START_END_DATE} from "app/shared/constants/years.constants";

@Component({
    selector: 'mi-force-running-percent-marine-suffix-update',
    templateUrl: './force-running-percent-marine-suffix-update.component.html'
})
export class ForceRunningPercentMarineSuffixUpdateComponent implements OnInit {
    forceRunningPercent: IForceRunningPercentMarineSuffix;
    isSaving: boolean;

    organizationcharts: IOrganizationChartMarineSuffix[];
    groups: IOrganizationChartMarineSuffix[];
    runMonths: any = MONTHS.sort(function(a,b)
    {
        return (a.id > b.id) ? 1 : ((b.id > a.id) ? -1 : 0);
    });
    years: any = GREGORIAN_START_END_DATE.map(a => a.year);
    constructor(
        protected jhiAlertService: JhiAlertService,
        protected forceRunningPercentService: ForceRunningPercentMarineSuffixService,
        protected organizationChartService: OrganizationChartMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ forceRunningPercent }) => {
            this.forceRunningPercent = forceRunningPercent;
        });

        this.loadOrgCharts();
    }

    loadOrgCharts(){
        if(this.organizationChartService.organizationchartsAll)
        {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            this.groups = this.organizationcharts.filter(a => a.parentId == null);
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationcharts = res.body;
                    this.groups = this.organizationcharts.filter(a => a.parentId == null);
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
        if (this.forceRunningPercent.id !== undefined) {
            this.subscribeToSaveResponse(this.forceRunningPercentService.update(this.forceRunningPercent));
        } else {
            this.subscribeToSaveResponse(this.forceRunningPercentService.create(this.forceRunningPercent));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IForceRunningPercentMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IForceRunningPercentMarineSuffix>) => this.onSaveSuccess(),
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

    trackOrganizationChartById(index: number, item: IOrganizationChartMarineSuffix) {
        return item.id;
    }
    selectAll(){
        this.forceRunningPercent.organizationCharts = this.groups;
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
