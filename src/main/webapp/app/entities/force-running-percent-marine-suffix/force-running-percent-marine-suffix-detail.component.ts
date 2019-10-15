import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IForceRunningPercentMarineSuffix } from 'app/shared/model/force-running-percent-marine-suffix.model';

@Component({
    selector: 'mi-force-running-percent-marine-suffix-detail',
    templateUrl: './force-running-percent-marine-suffix-detail.component.html'
})
export class ForceRunningPercentMarineSuffixDetailComponent implements OnInit {
    forceRunningPercent: IForceRunningPercentMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ forceRunningPercent }) => {
            this.forceRunningPercent = forceRunningPercent;
        });
    }

    previousState() {
        window.history.back();
    }
}
