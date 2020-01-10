import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IJobNiazsanjiMarineSuffix } from 'app/shared/model/job-niazsanji-marine-suffix.model';

@Component({
    selector: 'mi-job-niazsanji-marine-suffix-detail',
    templateUrl: './job-niazsanji-marine-suffix-detail.component.html'
})
export class JobNiazsanjiMarineSuffixDetailComponent implements OnInit {
    jobNiazsanji: IJobNiazsanjiMarineSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ jobNiazsanji }) => {
            this.jobNiazsanji = jobNiazsanji;
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
