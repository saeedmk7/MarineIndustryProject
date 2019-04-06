import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRunningStepMarineSuffix } from 'app/shared/model/running-step-marine-suffix.model';

@Component({
    selector: 'mi-running-step-marine-suffix-detail',
    templateUrl: './running-step-marine-suffix-detail.component.html'
})
export class RunningStepMarineSuffixDetailComponent implements OnInit {
    runningStep: IRunningStepMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ runningStep }) => {
            this.runningStep = runningStep;
        });
    }

    previousState() {
        window.history.back();
    }
}
