import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICriterionMarineSuffix } from 'app/shared/model/criterion-marine-suffix.model';
import { CriterionMarineSuffixService } from './criterion-marine-suffix.service';

@Component({
    selector: 'mi-criterion-marine-suffix-update',
    templateUrl: './criterion-marine-suffix-update.component.html'
})
export class CriterionMarineSuffixUpdateComponent implements OnInit {
    private _criterion: ICriterionMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private criterionService: CriterionMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ criterion }) => {
            this.criterion = criterion;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.criterion.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.criterion.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.criterion.id !== undefined) {
            this.subscribeToSaveResponse(this.criterionService.update(this.criterion));
        } else {
            this.subscribeToSaveResponse(this.criterionService.create(this.criterion));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICriterionMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<ICriterionMarineSuffix>) => this.onSaveSuccess(),
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
    get criterion() {
        return this._criterion;
    }

    set criterion(criterion: ICriterionMarineSuffix) {
        this._criterion = criterion;
        this.createDate = moment(criterion.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(criterion.modifyDate).format(DATE_TIME_FORMAT);
    }
}
