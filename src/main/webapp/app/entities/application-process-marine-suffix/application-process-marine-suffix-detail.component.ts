import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IApplicationProcessMarineSuffix } from 'app/shared/model/application-process-marine-suffix.model';

@Component({
    selector: 'mi-application-process-marine-suffix-detail',
    templateUrl: './application-process-marine-suffix-detail.component.html'
})
export class ApplicationProcessMarineSuffixDetailComponent implements OnInit {
    applicationProcess: IApplicationProcessMarineSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ applicationProcess }) => {
            this.applicationProcess = applicationProcess;
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
