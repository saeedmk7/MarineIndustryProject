import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IRunRunningStepMarineSuffix } from 'app/shared/model/run-running-step-marine-suffix.model';
import { RunRunningStepMarineSuffixService } from './run-running-step-marine-suffix.service';
import { IRunPhaseMarineSuffix } from 'app/shared/model/run-phase-marine-suffix.model';
import { RunPhaseMarineSuffixService } from 'app/entities/run-phase-marine-suffix';
import { IRunningStepMarineSuffix } from 'app/shared/model/running-step-marine-suffix.model';
import { RunningStepMarineSuffixService } from 'app/entities/running-step-marine-suffix';

@Component({
    selector: 'mi-run-running-step-marine-suffix-update',
    templateUrl: './run-running-step-marine-suffix-update.component.html'
})
export class RunRunningStepMarineSuffixUpdateComponent implements OnInit {
    private _runRunningStep: IRunRunningStepMarineSuffix;
    isSaving: boolean;

    runphases: IRunPhaseMarineSuffix[];

    runningsteps: IRunningStepMarineSuffix[];
    doneDate: string;
    createDate: string;
    modifyDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private runRunningStepService: RunRunningStepMarineSuffixService,
        private runPhaseService: RunPhaseMarineSuffixService,
        private runningStepService: RunningStepMarineSuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ runRunningStep }) => {
            this.runRunningStep = runRunningStep;
        });
        this.runPhaseService.query().subscribe(
            (res: HttpResponse<IRunPhaseMarineSuffix[]>) => {
                this.runphases = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.runningStepService.query().subscribe(
            (res: HttpResponse<IRunningStepMarineSuffix[]>) => {
                this.runningsteps = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.runRunningStep.doneDate = moment(this.doneDate, DATE_TIME_FORMAT);
        this.runRunningStep.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.runRunningStep.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.runRunningStep.id !== undefined) {
            this.subscribeToSaveResponse(this.runRunningStepService.update(this.runRunningStep));
        } else {
            this.subscribeToSaveResponse(this.runRunningStepService.create(this.runRunningStep));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRunRunningStepMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IRunRunningStepMarineSuffix>) => this.onSaveSuccess(),
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

    trackRunPhaseById(index: number, item: IRunPhaseMarineSuffix) {
        return item.id;
    }

    trackRunningStepById(index: number, item: IRunningStepMarineSuffix) {
        return item.id;
    }
    get runRunningStep() {
        return this._runRunningStep;
    }

    set runRunningStep(runRunningStep: IRunRunningStepMarineSuffix) {
        this._runRunningStep = runRunningStep;
        this.doneDate = moment(runRunningStep.doneDate).format(DATE_TIME_FORMAT);
        this.createDate = moment(runRunningStep.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(runRunningStep.modifyDate).format(DATE_TIME_FORMAT);
    }
}
