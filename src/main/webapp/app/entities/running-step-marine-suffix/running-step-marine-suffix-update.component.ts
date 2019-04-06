import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRunningStepMarineSuffix } from 'app/shared/model/running-step-marine-suffix.model';
import { RunningStepMarineSuffixService } from './running-step-marine-suffix.service';

@Component({
    selector: 'mi-running-step-marine-suffix-update',
    templateUrl: './running-step-marine-suffix-update.component.html'
})
export class RunningStepMarineSuffixUpdateComponent implements OnInit {
    private _runningStep: IRunningStepMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;
    archivedDate: string;

    constructor(private runningStepService: RunningStepMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ runningStep }) => {
            this.runningStep = runningStep;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.runningStep.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.runningStep.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        this.runningStep.archivedDate = moment(this.archivedDate, DATE_TIME_FORMAT);
        if (this.runningStep.id !== undefined) {
            this.subscribeToSaveResponse(this.runningStepService.update(this.runningStep));
        } else {
            this.subscribeToSaveResponse(this.runningStepService.create(this.runningStep));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRunningStepMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IRunningStepMarineSuffix>) => this.onSaveSuccess(),
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
    get runningStep() {
        return this._runningStep;
    }

    set runningStep(runningStep: IRunningStepMarineSuffix) {
        this._runningStep = runningStep;
        this.createDate = moment(runningStep.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(runningStep.modifyDate).format(DATE_TIME_FORMAT);
        this.archivedDate = moment(runningStep.archivedDate).format(DATE_TIME_FORMAT);
    }
}
