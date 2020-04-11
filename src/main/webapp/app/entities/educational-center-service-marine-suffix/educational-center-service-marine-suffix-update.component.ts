import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IEducationalCenterServiceMarineSuffix } from 'app/shared/model/educational-center-service-marine-suffix.model';
import { EducationalCenterServiceMarineSuffixService } from './educational-center-service-marine-suffix.service';

@Component({
    selector: 'mi-educational-center-service-marine-suffix-update',
    templateUrl: './educational-center-service-marine-suffix-update.component.html'
})
export class EducationalCenterServiceMarineSuffixUpdateComponent implements OnInit {
    educationalCenterService: IEducationalCenterServiceMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(
        protected educationalCenterServiceService: EducationalCenterServiceMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ educationalCenterService }) => {
            this.educationalCenterService = educationalCenterService;
            this.createDate =
                this.educationalCenterService.createDate != null ? this.educationalCenterService.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate =
                this.educationalCenterService.modifyDate != null ? this.educationalCenterService.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.educationalCenterService.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.educationalCenterService.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.educationalCenterService.id !== undefined) {
            this.subscribeToSaveResponse(this.educationalCenterServiceService.update(this.educationalCenterService));
        } else {
            this.subscribeToSaveResponse(this.educationalCenterServiceService.create(this.educationalCenterService));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEducationalCenterServiceMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEducationalCenterServiceMarineSuffix>) => this.onSaveSuccess(),
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
}
