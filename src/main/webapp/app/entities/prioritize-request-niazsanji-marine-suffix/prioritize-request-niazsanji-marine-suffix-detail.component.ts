import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IPrioritizeRequestNiazsanjiMarineSuffix } from 'app/shared/model/prioritize-request-niazsanji-marine-suffix.model';

@Component({
    selector: 'mi-prioritize-request-niazsanji-marine-suffix-detail',
    templateUrl: './prioritize-request-niazsanji-marine-suffix-detail.component.html'
})
export class PrioritizeRequestNiazsanjiMarineSuffixDetailComponent implements OnInit {
    prioritizeRequestNiazsanji: IPrioritizeRequestNiazsanjiMarineSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ prioritizeRequestNiazsanji }) => {
            this.prioritizeRequestNiazsanji = prioritizeRequestNiazsanji;
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
