import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IApplicationProcessStepMarineSuffix } from 'app/shared/model/application-process-step-marine-suffix.model';

@Component({
    selector: 'mi-application-process-step-marine-suffix-detail',
    templateUrl: './application-process-step-marine-suffix-detail.component.html'
})
export class ApplicationProcessStepMarineSuffixDetailComponent implements OnInit {
    applicationProcessStep: IApplicationProcessStepMarineSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ applicationProcessStep }) => {
            this.applicationProcessStep = applicationProcessStep;
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
