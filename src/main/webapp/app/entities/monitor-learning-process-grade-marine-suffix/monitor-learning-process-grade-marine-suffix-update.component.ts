import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IMonitorLearningProcessGradeMarineSuffix } from 'app/shared/model/monitor-learning-process-grade-marine-suffix.model';
import { MonitorLearningProcessGradeMarineSuffixService } from './monitor-learning-process-grade-marine-suffix.service';
import { IMonitorLearningProcessLevelMarineSuffix } from 'app/shared/model/monitor-learning-process-level-marine-suffix.model';
import { MonitorLearningProcessLevelMarineSuffixService } from 'app/entities/monitor-learning-process-level-marine-suffix';
import { IMonitorLearningProcessMarineSuffix } from 'app/shared/model/monitor-learning-process-marine-suffix.model';
import { MonitorLearningProcessMarineSuffixService } from 'app/entities/monitor-learning-process-marine-suffix';
import { IMonitorProcessDurationMarineSuffix } from 'app/shared/model/monitor-process-duration-marine-suffix.model';
import { MonitorProcessDurationMarineSuffixService } from 'app/entities/monitor-process-duration-marine-suffix';

@Component({
    selector: 'mi-monitor-learning-process-grade-marine-suffix-update',
    templateUrl: './monitor-learning-process-grade-marine-suffix-update.component.html'
})
export class MonitorLearningProcessGradeMarineSuffixUpdateComponent implements OnInit {
    monitorLearningProcessGrade: IMonitorLearningProcessGradeMarineSuffix;
    isSaving: boolean;

    monitorlearningprocesslevels: IMonitorLearningProcessLevelMarineSuffix[];

    monitorlearningprocesses: IMonitorLearningProcessMarineSuffix[];

    monitorprocessdurations: IMonitorProcessDurationMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected monitorLearningProcessGradeService: MonitorLearningProcessGradeMarineSuffixService,
        protected monitorLearningProcessLevelService: MonitorLearningProcessLevelMarineSuffixService,
        protected monitorLearningProcessService: MonitorLearningProcessMarineSuffixService,
        protected monitorProcessDurationService: MonitorProcessDurationMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ monitorLearningProcessGrade }) => {
            this.monitorLearningProcessGrade = monitorLearningProcessGrade;
            this.createDate =
                this.monitorLearningProcessGrade.createDate != null
                    ? this.monitorLearningProcessGrade.createDate.format(DATE_TIME_FORMAT)
                    : null;
            this.modifyDate =
                this.monitorLearningProcessGrade.modifyDate != null
                    ? this.monitorLearningProcessGrade.modifyDate.format(DATE_TIME_FORMAT)
                    : null;
        });
        this.monitorLearningProcessLevelService.query().subscribe(
            (res: HttpResponse<IMonitorLearningProcessLevelMarineSuffix[]>) => {
                this.monitorlearningprocesslevels = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.monitorLearningProcessService.query().subscribe(
            (res: HttpResponse<IMonitorLearningProcessMarineSuffix[]>) => {
                this.monitorlearningprocesses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.monitorProcessDurationService.query().subscribe(
            (res: HttpResponse<IMonitorProcessDurationMarineSuffix[]>) => {
                this.monitorprocessdurations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.monitorLearningProcessGrade.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.monitorLearningProcessGrade.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.monitorLearningProcessGrade.id !== undefined) {
            this.subscribeToSaveResponse(this.monitorLearningProcessGradeService.update(this.monitorLearningProcessGrade));
        } else {
            this.subscribeToSaveResponse(this.monitorLearningProcessGradeService.create(this.monitorLearningProcessGrade));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IMonitorLearningProcessGradeMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IMonitorLearningProcessGradeMarineSuffix>) => this.onSaveSuccess(),
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

    trackMonitorLearningProcessLevelById(index: number, item: IMonitorLearningProcessLevelMarineSuffix) {
        return item.id;
    }

    trackMonitorLearningProcessById(index: number, item: IMonitorLearningProcessMarineSuffix) {
        return item.id;
    }

    trackMonitorProcessDurationById(index: number, item: IMonitorProcessDurationMarineSuffix) {
        return item.id;
    }
}
