import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IEvaluationMethodMarineSuffix } from 'app/shared/model/evaluation-method-marine-suffix.model';
import { EvaluationMethodMarineSuffixService } from './evaluation-method-marine-suffix.service';

@Component({
    selector: 'mi-evaluation-method-marine-suffix-update',
    templateUrl: './evaluation-method-marine-suffix-update.component.html'
})
export class EvaluationMethodMarineSuffixUpdateComponent implements OnInit {
    private _evaluationMethod: IEvaluationMethodMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private evaluationMethodService: EvaluationMethodMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ evaluationMethod }) => {
            this.evaluationMethod = evaluationMethod;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.evaluationMethod.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.evaluationMethod.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.evaluationMethod.id !== undefined) {
            this.subscribeToSaveResponse(this.evaluationMethodService.update(this.evaluationMethod));
        } else {
            this.subscribeToSaveResponse(this.evaluationMethodService.create(this.evaluationMethod));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEvaluationMethodMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEvaluationMethodMarineSuffix>) => this.onSaveSuccess(),
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
    get evaluationMethod() {
        return this._evaluationMethod;
    }

    set evaluationMethod(evaluationMethod: IEvaluationMethodMarineSuffix) {
        this._evaluationMethod = evaluationMethod;
        this.createDate = moment(evaluationMethod.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(evaluationMethod.modifyDate).format(DATE_TIME_FORMAT);
    }
}
