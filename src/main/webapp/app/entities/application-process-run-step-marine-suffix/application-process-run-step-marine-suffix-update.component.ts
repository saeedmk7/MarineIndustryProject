import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IApplicationProcessRunStepMarineSuffix } from 'app/shared/model/application-process-run-step-marine-suffix.model';
import { ApplicationProcessRunStepMarineSuffixService } from './application-process-run-step-marine-suffix.service';
import { IApplicationProcessMarineSuffix } from 'app/shared/model/application-process-marine-suffix.model';
import { ApplicationProcessMarineSuffixService } from 'app/entities/application-process-marine-suffix';
import { IApplicationProcessStepMarineSuffix } from 'app/shared/model/application-process-step-marine-suffix.model';
import { ApplicationProcessStepMarineSuffixService } from 'app/entities/application-process-step-marine-suffix';

@Component({
    selector: 'mi-application-process-run-step-marine-suffix-update',
    templateUrl: './application-process-run-step-marine-suffix-update.component.html'
})
export class ApplicationProcessRunStepMarineSuffixUpdateComponent implements OnInit {
    applicationProcessRunStep: IApplicationProcessRunStepMarineSuffix;
    isSaving: boolean;

    applicationprocesses: IApplicationProcessMarineSuffix[];

    applicationprocesssteps: IApplicationProcessStepMarineSuffix[];
    doneDate: string;
    createDate: string;
    modifyDate: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected applicationProcessRunStepService: ApplicationProcessRunStepMarineSuffixService,
        protected applicationProcessService: ApplicationProcessMarineSuffixService,
        protected applicationProcessStepService: ApplicationProcessStepMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ applicationProcessRunStep }) => {
            this.applicationProcessRunStep = applicationProcessRunStep;
            this.doneDate =
                this.applicationProcessRunStep.doneDate != null ? this.applicationProcessRunStep.doneDate.format(DATE_TIME_FORMAT) : null;
            this.createDate =
                this.applicationProcessRunStep.createDate != null
                    ? this.applicationProcessRunStep.createDate.format(DATE_TIME_FORMAT)
                    : null;
            this.modifyDate =
                this.applicationProcessRunStep.modifyDate != null
                    ? this.applicationProcessRunStep.modifyDate.format(DATE_TIME_FORMAT)
                    : null;
        });
        this.applicationProcessService.query().subscribe(
            (res: HttpResponse<IApplicationProcessMarineSuffix[]>) => {
                this.applicationprocesses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.applicationProcessStepService.query().subscribe(
            (res: HttpResponse<IApplicationProcessStepMarineSuffix[]>) => {
                this.applicationprocesssteps = res.body;
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
        this.applicationProcessRunStep.doneDate = this.doneDate != null ? moment(this.doneDate, DATE_TIME_FORMAT) : null;
        this.applicationProcessRunStep.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.applicationProcessRunStep.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.applicationProcessRunStep.id !== undefined) {
            this.subscribeToSaveResponse(this.applicationProcessRunStepService.update(this.applicationProcessRunStep));
        } else {
            this.subscribeToSaveResponse(this.applicationProcessRunStepService.create(this.applicationProcessRunStep));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IApplicationProcessRunStepMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IApplicationProcessRunStepMarineSuffix>) => this.onSaveSuccess(),
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

    trackApplicationProcessById(index: number, item: IApplicationProcessMarineSuffix) {
        return item.id;
    }

    trackApplicationProcessStepById(index: number, item: IApplicationProcessStepMarineSuffix) {
        return item.id;
    }
}
