import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IDesignNiazsanjiMarineSuffix } from 'app/shared/model/design-niazsanji-marine-suffix.model';

@Component({
    selector: 'mi-design-niazsanji-marine-suffix-detail',
    templateUrl: './design-niazsanji-marine-suffix-detail.component.html'
})
export class DesignNiazsanjiMarineSuffixDetailComponent implements OnInit {
    designNiazsanji: IDesignNiazsanjiMarineSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ designNiazsanji }) => {
            this.designNiazsanji = designNiazsanji;
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
