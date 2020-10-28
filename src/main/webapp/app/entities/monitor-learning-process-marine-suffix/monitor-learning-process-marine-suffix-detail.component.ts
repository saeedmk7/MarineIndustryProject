import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMonitorLearningProcessMarineSuffix } from 'app/shared/model/monitor-learning-process-marine-suffix.model';

@Component({
    selector: 'mi-monitor-learning-process-marine-suffix-detail',
    templateUrl: './monitor-learning-process-marine-suffix-detail.component.html'
})
export class MonitorLearningProcessMarineSuffixDetailComponent implements OnInit {
    monitorLearningProcess: IMonitorLearningProcessMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ monitorLearningProcess }) => {
            this.monitorLearningProcess = monitorLearningProcess;
        });
    }

    previousState() {
        window.history.back();
    }
}
