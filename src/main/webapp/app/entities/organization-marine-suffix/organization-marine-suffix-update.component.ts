import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IOrganizationMarineSuffix } from 'app/shared/model/organization-marine-suffix.model';
import { OrganizationMarineSuffixService } from './organization-marine-suffix.service';

@Component({
    selector: 'mi-organization-marine-suffix-update',
    templateUrl: './organization-marine-suffix-update.component.html'
})
export class OrganizationMarineSuffixUpdateComponent implements OnInit {
    private _organization: IOrganizationMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private organizationService: OrganizationMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ organization }) => {
            this.organization = organization;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.organization.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.organization.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.organization.id !== undefined) {
            this.subscribeToSaveResponse(this.organizationService.update(this.organization));
        } else {
            this.subscribeToSaveResponse(this.organizationService.create(this.organization));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IOrganizationMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IOrganizationMarineSuffix>) => this.onSaveSuccess(),
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
    get organization() {
        return this._organization;
    }

    set organization(organization: IOrganizationMarineSuffix) {
        this._organization = organization;
        this.createDate = moment(organization.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(organization.modifyDate).format(DATE_TIME_FORMAT);
    }
}
