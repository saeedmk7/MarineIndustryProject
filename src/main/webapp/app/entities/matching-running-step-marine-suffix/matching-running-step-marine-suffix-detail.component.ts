import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMatchingRunningStepMarineSuffix } from 'app/shared/model/matching-running-step-marine-suffix.model';

@Component({
    selector: 'mi-matching-running-step-marine-suffix-detail',
    templateUrl: './matching-running-step-marine-suffix-detail.component.html'
})
export class MatchingRunningStepMarineSuffixDetailComponent implements OnInit {
    matchingRunningStep: IMatchingRunningStepMarineSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ matchingRunningStep }) => {
            this.matchingRunningStep = matchingRunningStep;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
