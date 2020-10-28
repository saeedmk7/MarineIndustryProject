import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IMonitorProcessDurationMarineSuffix } from 'app/shared/model/monitor-process-duration-marine-suffix.model';
import { MonitorProcessDurationMarineSuffixService } from './monitor-process-duration-marine-suffix.service';

@Component({
    selector: 'mi-monitor-process-duration-marine-suffix-update',
    templateUrl: './monitor-process-duration-marine-suffix-update.component.html'
})
export class MonitorProcessDurationMarineSuffixUpdateComponent implements OnInit {
    monitorProcessDuration: IMonitorProcessDurationMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(
        protected monitorProcessDurationService: MonitorProcessDurationMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ monitorProcessDuration }) => {
            this.monitorProcessDuration = monitorProcessDuration;
            this.createDate =
                this.monitorProcessDuration.createDate != null ? this.monitorProcessDuration.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate =
                this.monitorProcessDuration.modifyDate != null ? this.monitorProcessDuration.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.monitorProcessDuration.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.monitorProcessDuration.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.monitorProcessDuration.id !== undefined) {
            this.subscribeToSaveResponse(this.monitorProcessDurationService.update(this.monitorProcessDuration));
        } else {
            this.subscribeToSaveResponse(this.monitorProcessDurationService.create(this.monitorProcessDuration));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IMonitorProcessDurationMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IMonitorProcessDurationMarineSuffix>) => this.onSaveSuccess(),
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
