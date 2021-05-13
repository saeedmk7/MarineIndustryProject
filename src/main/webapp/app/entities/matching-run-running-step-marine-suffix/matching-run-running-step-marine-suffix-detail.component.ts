import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMatchingRunRunningStepMarineSuffix } from 'app/shared/model/matching-run-running-step-marine-suffix.model';

@Component({
    selector: 'mi-matching-run-running-step-marine-suffix-detail',
    templateUrl: './matching-run-running-step-marine-suffix-detail.component.html'
})
export class MatchingRunRunningStepMarineSuffixDetailComponent implements OnInit {
    matchingRunRunningStep: IMatchingRunRunningStepMarineSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ matchingRunRunningStep }) => {
            this.matchingRunRunningStep = matchingRunRunningStep;
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
