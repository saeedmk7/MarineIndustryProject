import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMonitorLearningProcessGradeMarineSuffix } from 'app/shared/model/monitor-learning-process-grade-marine-suffix.model';

@Component({
    selector: 'mi-monitor-learning-process-grade-marine-suffix-detail',
    templateUrl: './monitor-learning-process-grade-marine-suffix-detail.component.html'
})
export class MonitorLearningProcessGradeMarineSuffixDetailComponent implements OnInit {
    monitorLearningProcessGrade: IMonitorLearningProcessGradeMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ monitorLearningProcessGrade }) => {
            this.monitorLearningProcessGrade = monitorLearningProcessGrade;
        });
    }

    previousState() {
        window.history.back();
    }
}
