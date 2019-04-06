import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IOrganizationChartAuthorityMarineSuffix } from 'app/shared/model/organization-chart-authority-marine-suffix.model';
import { OrganizationChartAuthorityMarineSuffixService } from './organization-chart-authority-marine-suffix.service';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';

@Component({
    selector: 'mi-organization-chart-authority-marine-suffix-update',
    templateUrl: './organization-chart-authority-marine-suffix-update.component.html'
})
export class OrganizationChartAuthorityMarineSuffixUpdateComponent implements OnInit {
    private _organizationChartAuthority: IOrganizationChartAuthorityMarineSuffix;
    isSaving: boolean;

    organizationcharts: IOrganizationChartMarineSuffix[];
    createDate: string;
    modifyDate: string;
    archivedDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private organizationChartAuthorityService: OrganizationChartAuthorityMarineSuffixService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ organizationChartAuthority }) => {
            this.organizationChartAuthority = organizationChartAuthority;
        });
        this.organizationChartService.query().subscribe(
            (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                this.organizationcharts = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.organizationChartAuthority.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.organizationChartAuthority.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        this.organizationChartAuthority.archivedDate = moment(this.archivedDate, DATE_TIME_FORMAT);
        if (this.organizationChartAuthority.id !== undefined) {
            this.subscribeToSaveResponse(this.organizationChartAuthorityService.update(this.organizationChartAuthority));
        } else {
            this.subscribeToSaveResponse(this.organizationChartAuthorityService.create(this.organizationChartAuthority));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IOrganizationChartAuthorityMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IOrganizationChartAuthorityMarineSuffix>) => this.onSaveSuccess(),
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

    trackOrganizationChartById(index: number, item: IOrganizationChartMarineSuffix) {
        return item.id;
    }
    get organizationChartAuthority() {
        return this._organizationChartAuthority;
    }

    set organizationChartAuthority(organizationChartAuthority: IOrganizationChartAuthorityMarineSuffix) {
        this._organizationChartAuthority = organizationChartAuthority;
        this.createDate = moment(organizationChartAuthority.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(organizationChartAuthority.modifyDate).format(DATE_TIME_FORMAT);
        this.archivedDate = moment(organizationChartAuthority.archivedDate).format(DATE_TIME_FORMAT);
    }
}
