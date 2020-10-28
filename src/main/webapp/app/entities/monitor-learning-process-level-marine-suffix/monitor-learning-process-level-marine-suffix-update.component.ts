import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IMonitorLearningProcessLevelMarineSuffix } from 'app/shared/model/monitor-learning-process-level-marine-suffix.model';
import { MonitorLearningProcessLevelMarineSuffixService } from './monitor-learning-process-level-marine-suffix.service';

@Component({
    selector: 'mi-monitor-learning-process-level-marine-suffix-update',
    templateUrl: './monitor-learning-process-level-marine-suffix-update.component.html'
})
export class MonitorLearningProcessLevelMarineSuffixUpdateComponent implements OnInit {
    monitorLearningProcessLevel: IMonitorLearningProcessLevelMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(
        protected monitorLearningProcessLevelService: MonitorLearningProcessLevelMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ monitorLearningProcessLevel }) => {
            this.monitorLearningProcessLevel = monitorLearningProcessLevel;
            this.createDate =
                this.monitorLearningProcessLevel.createDate != null
                    ? this.monitorLearningProcessLevel.createDate.format(DATE_TIME_FORMAT)
                    : null;
            this.modifyDate =
                this.monitorLearningProcessLevel.modifyDate != null
                    ? this.monitorLearningProcessLevel.modifyDate.format(DATE_TIME_FORMAT)
                    : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.monitorLearningProcessLevel.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.monitorLearningProcessLevel.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.monitorLearningProcessLevel.id !== undefined) {
            this.subscribeToSaveResponse(this.monitorLearningProcessLevelService.update(this.monitorLearningProcessLevel));
        } else {
            this.subscribeToSaveResponse(this.monitorLearningProcessLevelService.create(this.monitorLearningProcessLevel));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IMonitorLearningProcessLevelMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IMonitorLearningProcessLevelMarineSuffix>) => this.onSaveSuccess(),
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
