import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMonitorProcessDurationMarineSuffix } from 'app/shared/model/monitor-process-duration-marine-suffix.model';

@Component({
    selector: 'mi-monitor-process-duration-marine-suffix-detail',
    templateUrl: './monitor-process-duration-marine-suffix-detail.component.html'
})
export class MonitorProcessDurationMarineSuffixDetailComponent implements OnInit {
    monitorProcessDuration: IMonitorProcessDurationMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ monitorProcessDuration }) => {
            this.monitorProcessDuration = monitorProcessDuration;
        });
    }

    previousState() {
        window.history.back();
    }
}
