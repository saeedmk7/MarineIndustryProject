import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IEducationalCenterGroupMarineSuffix } from 'app/shared/model/educational-center-group-marine-suffix.model';
import { EducationalCenterGroupMarineSuffixService } from './educational-center-group-marine-suffix.service';

@Component({
    selector: 'mi-educational-center-group-marine-suffix-update',
    templateUrl: './educational-center-group-marine-suffix-update.component.html'
})
export class EducationalCenterGroupMarineSuffixUpdateComponent implements OnInit {
    educationalCenterGroup: IEducationalCenterGroupMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(
        protected educationalCenterGroupService: EducationalCenterGroupMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ educationalCenterGroup }) => {
            this.educationalCenterGroup = educationalCenterGroup;
            this.createDate =
                this.educationalCenterGroup.createDate != null ? this.educationalCenterGroup.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate =
                this.educationalCenterGroup.modifyDate != null ? this.educationalCenterGroup.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.educationalCenterGroup.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.educationalCenterGroup.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.educationalCenterGroup.id !== undefined) {
            this.subscribeToSaveResponse(this.educationalCenterGroupService.update(this.educationalCenterGroup));
        } else {
            this.subscribeToSaveResponse(this.educationalCenterGroupService.create(this.educationalCenterGroup));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEducationalCenterGroupMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEducationalCenterGroupMarineSuffix>) => this.onSaveSuccess(),
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
