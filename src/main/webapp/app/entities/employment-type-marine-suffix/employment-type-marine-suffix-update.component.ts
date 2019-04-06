import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IEmploymentTypeMarineSuffix } from 'app/shared/model/employment-type-marine-suffix.model';
import { EmploymentTypeMarineSuffixService } from './employment-type-marine-suffix.service';

@Component({
    selector: 'mi-employment-type-marine-suffix-update',
    templateUrl: './employment-type-marine-suffix-update.component.html'
})
export class EmploymentTypeMarineSuffixUpdateComponent implements OnInit {
    private _employmentType: IEmploymentTypeMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private employmentTypeService: EmploymentTypeMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ employmentType }) => {
            this.employmentType = employmentType;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.employmentType.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.employmentType.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.employmentType.id !== undefined) {
            this.subscribeToSaveResponse(this.employmentTypeService.update(this.employmentType));
        } else {
            this.subscribeToSaveResponse(this.employmentTypeService.create(this.employmentType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEmploymentTypeMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEmploymentTypeMarineSuffix>) => this.onSaveSuccess(),
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
    get employmentType() {
        return this._employmentType;
    }

    set employmentType(employmentType: IEmploymentTypeMarineSuffix) {
        this._employmentType = employmentType;
        this.createDate = moment(employmentType.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(employmentType.modifyDate).format(DATE_TIME_FORMAT);
    }
}
