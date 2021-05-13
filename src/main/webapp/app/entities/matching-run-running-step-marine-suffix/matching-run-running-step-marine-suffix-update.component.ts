import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IMatchingRunRunningStepMarineSuffix } from 'app/shared/model/matching-run-running-step-marine-suffix.model';
import { MatchingRunRunningStepMarineSuffixService } from './matching-run-running-step-marine-suffix.service';
import { IMatchingEducationalRecordMarineSuffix } from 'app/shared/model/matching-educational-record-marine-suffix.model';
import { MatchingEducationalRecordMarineSuffixService } from 'app/entities/matching-educational-record-marine-suffix';
import { IMatchingRunningStepMarineSuffix } from 'app/shared/model/matching-running-step-marine-suffix.model';
import { MatchingRunningStepMarineSuffixService } from 'app/entities/matching-running-step-marine-suffix';

@Component({
    selector: 'mi-matching-run-running-step-marine-suffix-update',
    templateUrl: './matching-run-running-step-marine-suffix-update.component.html'
})
export class MatchingRunRunningStepMarineSuffixUpdateComponent implements OnInit {
    matchingRunRunningStep: IMatchingRunRunningStepMarineSuffix;
    isSaving: boolean;

    matchingeducationalrecords: IMatchingEducationalRecordMarineSuffix[];

    matchingrunningsteps: IMatchingRunningStepMarineSuffix[];
    doneDate: string;
    createDate: string;
    modifyDate: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected matchingRunRunningStepService: MatchingRunRunningStepMarineSuffixService,
        protected matchingEducationalRecordService: MatchingEducationalRecordMarineSuffixService,
        protected matchingRunningStepService: MatchingRunningStepMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ matchingRunRunningStep }) => {
            this.matchingRunRunningStep = matchingRunRunningStep;
            this.doneDate =
                this.matchingRunRunningStep.doneDate != null ? this.matchingRunRunningStep.doneDate.format(DATE_TIME_FORMAT) : null;
            this.createDate =
                this.matchingRunRunningStep.createDate != null ? this.matchingRunRunningStep.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate =
                this.matchingRunRunningStep.modifyDate != null ? this.matchingRunRunningStep.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
        this.matchingEducationalRecordService.query().subscribe(
            (res: HttpResponse<IMatchingEducationalRecordMarineSuffix[]>) => {
                this.matchingeducationalrecords = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.matchingRunningStepService.query().subscribe(
            (res: HttpResponse<IMatchingRunningStepMarineSuffix[]>) => {
                this.matchingrunningsteps = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.matchingRunRunningStep.doneDate = this.doneDate != null ? moment(this.doneDate, DATE_TIME_FORMAT) : null;
        this.matchingRunRunningStep.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.matchingRunRunningStep.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.matchingRunRunningStep.id !== undefined) {
            this.subscribeToSaveResponse(this.matchingRunRunningStepService.update(this.matchingRunRunningStep));
        } else {
            this.subscribeToSaveResponse(this.matchingRunRunningStepService.create(this.matchingRunRunningStep));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IMatchingRunRunningStepMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IMatchingRunRunningStepMarineSuffix>) => this.onSaveSuccess(),
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

    trackMatchingEducationalRecordById(index: number, item: IMatchingEducationalRecordMarineSuffix) {
        return item.id;
    }

    trackMatchingRunningStepById(index: number, item: IMatchingRunningStepMarineSuffix) {
        return item.id;
    }
}
