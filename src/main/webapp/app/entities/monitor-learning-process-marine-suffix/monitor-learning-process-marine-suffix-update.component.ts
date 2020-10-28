import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IMonitorLearningProcessMarineSuffix } from 'app/shared/model/monitor-learning-process-marine-suffix.model';
import { MonitorLearningProcessMarineSuffixService } from './monitor-learning-process-marine-suffix.service';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { IMonitorProcessDurationMarineSuffix } from 'app/shared/model/monitor-process-duration-marine-suffix.model';
import { MonitorProcessDurationMarineSuffixService } from 'app/entities/monitor-process-duration-marine-suffix';
import * as persianMoment from 'jalali-moment';
import { IMonitorLearningProcessLevelMarineSuffix } from 'app/shared/model/monitor-learning-process-level-marine-suffix.model';
import { IMonitorLearningProcessGradeMarineSuffix } from 'app/shared/model/monitor-learning-process-grade-marine-suffix.model';
import { MonitorLearningProcessLevelMarineSuffixService } from 'app/entities/monitor-learning-process-level-marine-suffix';
import { MonitorLearningProcessGradeMarineSuffixService } from 'app/entities/monitor-learning-process-grade-marine-suffix';
import { ITeacherGradeScoreMarineSuffix, TeacherGradeScoreMarineSuffix } from 'app/shared/model/teacher-grade-score-marine-suffix.model';
import { ITeacherCriteriaMarineSuffix } from 'app/shared/model/teacher-criteria-marine-suffix.model';

@Component({
    selector: 'mi-monitor-learning-process-marine-suffix-update',
    templateUrl: './monitor-learning-process-marine-suffix-update.component.html'
})
export class MonitorLearningProcessMarineSuffixUpdateComponent implements OnInit {
    monitorLearningProcess: IMonitorLearningProcessMarineSuffix;
    isSaving: boolean;

    monitorprocessdurations: IMonitorProcessDurationMarineSuffix[];
    monitorLearningProcessLevels: IMonitorLearningProcessLevelMarineSuffix[];
    monitorLearningProcessGrades: IMonitorLearningProcessGradeMarineSuffix[];

    finishDateValidation: number;
    constructor(
        protected jhiAlertService: JhiAlertService,
        protected monitorLearningProcessService: MonitorLearningProcessMarineSuffixService,
        protected monitorLearningProcessLevelService: MonitorLearningProcessLevelMarineSuffixService,
        protected monitorLearningProcessGradeService: MonitorLearningProcessGradeMarineSuffixService,
        protected documentService: DocumentMarineSuffixService,
        protected monitorProcessDurationService: MonitorProcessDurationMarineSuffixService,
        protected activatedRoute: ActivatedRoute,
        private router: Router
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ monitorLearningProcess }) => {
            debugger;
            this.monitorLearningProcess = monitorLearningProcess;
            if (this.monitorLearningProcess.id === undefined) {
                this.buildMonitorLearningProcessGrades();
            } else {
                const criteria = [
                    {
                        key: 'monitorLearningProcessId.equals',
                        value: this.monitorLearningProcess.id
                    }
                ];
                this.monitorLearningProcessGradeService
                    .query({
                        page: 0,
                        size: 20000,
                        criteria,
                        sort: ['id', 'asc']
                    })
                    .subscribe(
                        (res: HttpResponse<IMonitorLearningProcessGradeMarineSuffix[]>) => {
                            this.monitorLearningProcess.monitorLearningProcessGrades = res.body;
                        },
                        (res: HttpErrorResponse) => this.onError(res.message)
                    );
            }
        });
        this.monitorProcessDurationService.query().subscribe(
            (res: HttpResponse<IMonitorProcessDurationMarineSuffix[]>) => {
                this.monitorprocessdurations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    buildMonitorLearningProcessGrades() {
        debugger;
        this.monitorLearningProcessLevelService.query().subscribe(
            (res: HttpResponse<IMonitorLearningProcessLevelMarineSuffix[]>) => {
                debugger;
                this.monitorLearningProcessLevels = res.body.sort((a, b) => (a.id > b.id ? 1 : a.id < b.id ? -1 : 0));

                this.monitorLearningProcess.monitorLearningProcessGrades = [];
                this.monitorLearningProcessLevels.forEach(w => {
                    let monitorLearningProcessGrade: IMonitorLearningProcessGradeMarineSuffix = {};
                    monitorLearningProcessGrade.monitorLearningProcessLevelId = w.id;
                    monitorLearningProcessGrade.monitorLearningProcessLevelTitle = w.title;
                    monitorLearningProcessGrade.formula = w.formula;
                    monitorLearningProcessGrade.goal = w.goal;
                    this.monitorLearningProcess.monitorLearningProcessGrades.push(monitorLearningProcessGrade);
                });
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    changeDurations(monitorProcessDurationId) {
        debugger;
        this.monitorLearningProcess.monitorLearningProcessGrades.forEach(w => {
            w.monitorProcessDurationId = monitorProcessDurationId;
        });
    }
    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        debugger;
        this.monitorLearningProcess.title = 'فرم پایش';
        if (this.monitorLearningProcess.id !== undefined) {
            this.subscribeToSaveResponse(this.monitorLearningProcessService.update(this.monitorLearningProcess));
        } else {
            this.subscribeToSaveResponse(this.monitorLearningProcessService.create(this.monitorLearningProcess));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IMonitorLearningProcessMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IMonitorLearningProcessMarineSuffix>) => this.onSaveSuccess(),
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

    trackDocumentById(index: number, item: IDocumentMarineSuffix) {
        return item.id;
    }

    trackMonitorProcessDurationById(index: number, item: IMonitorProcessDurationMarineSuffix) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
    change(i) {
        //this.router.navigateByUrl(i);
        this.router.navigateByUrl(i);
    }
    checkDateValidation(event) {
        try {
            if (persianMoment(event.target.value, 'jYYYY/jMM/jDD').isValid()) {
                this.finishDateValidation = 1;
            } else {
                this.finishDateValidation = 2;
            }
        } catch (e) {
            this.finishDateValidation = 2;
        }
    }
}
