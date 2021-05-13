import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IApplicationProcessRunStepMarineSuffix } from 'app/shared/model/application-process-run-step-marine-suffix.model';

@Component({
    selector: 'mi-application-process-run-step-marine-suffix-detail',
    templateUrl: './application-process-run-step-marine-suffix-detail.component.html'
})
export class ApplicationProcessRunStepMarineSuffixDetailComponent implements OnInit {
    applicationProcessRunStep: IApplicationProcessRunStepMarineSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ applicationProcessRunStep }) => {
            this.applicationProcessRunStep = applicationProcessRunStep;
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
