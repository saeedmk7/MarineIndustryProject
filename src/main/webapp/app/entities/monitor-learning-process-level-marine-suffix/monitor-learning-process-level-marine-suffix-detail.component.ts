import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMonitorLearningProcessLevelMarineSuffix } from 'app/shared/model/monitor-learning-process-level-marine-suffix.model';

@Component({
    selector: 'mi-monitor-learning-process-level-marine-suffix-detail',
    templateUrl: './monitor-learning-process-level-marine-suffix-detail.component.html'
})
export class MonitorLearningProcessLevelMarineSuffixDetailComponent implements OnInit {
    monitorLearningProcessLevel: IMonitorLearningProcessLevelMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ monitorLearningProcessLevel }) => {
            this.monitorLearningProcessLevel = monitorLearningProcessLevel;
        });
    }

    previousState() {
        window.history.back();
    }
}
