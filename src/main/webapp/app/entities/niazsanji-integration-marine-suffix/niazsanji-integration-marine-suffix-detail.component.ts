import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { INiazsanjiIntegrationMarineSuffix } from 'app/shared/model/niazsanji-integration-marine-suffix.model';

@Component({
    selector: 'mi-niazsanji-integration-marine-suffix-detail',
    templateUrl: './niazsanji-integration-marine-suffix-detail.component.html'
})
export class NiazsanjiIntegrationMarineSuffixDetailComponent implements OnInit {
    niazsanjiIntegration: INiazsanjiIntegrationMarineSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ niazsanjiIntegration }) => {
            this.niazsanjiIntegration = niazsanjiIntegration;
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
