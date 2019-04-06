import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRunPhaseMarineSuffix } from 'app/shared/model/run-phase-marine-suffix.model';

@Component({
    selector: 'mi-run-phase-marine-suffix-detail',
    templateUrl: './run-phase-marine-suffix-detail.component.html'
})
export class RunPhaseMarineSuffixDetailComponent implements OnInit {
    runPhase: IRunPhaseMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ runPhase }) => {
            this.runPhase = runPhase;
        });
    }

    previousState() {
        window.history.back();
    }
}
