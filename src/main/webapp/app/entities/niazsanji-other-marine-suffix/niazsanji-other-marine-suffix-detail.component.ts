import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { INiazsanjiOtherMarineSuffix } from 'app/shared/model/niazsanji-other-marine-suffix.model';

@Component({
    selector: 'mi-niazsanji-other-marine-suffix-detail',
    templateUrl: './niazsanji-other-marine-suffix-detail.component.html'
})
export class NiazsanjiOtherMarineSuffixDetailComponent implements OnInit {
    niazsanjiOther: INiazsanjiOtherMarineSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ niazsanjiOther }) => {
            this.niazsanjiOther = niazsanjiOther;
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
