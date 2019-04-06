import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRunRunningStepMarineSuffix } from 'app/shared/model/run-running-step-marine-suffix.model';

@Component({
    selector: 'mi-run-running-step-marine-suffix-detail',
    templateUrl: './run-running-step-marine-suffix-detail.component.html'
})
export class RunRunningStepMarineSuffixDetailComponent implements OnInit {
    runRunningStep: IRunRunningStepMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ runRunningStep }) => {
            this.runRunningStep = runRunningStep;
        });
    }

    previousState() {
        window.history.back();
    }
}
