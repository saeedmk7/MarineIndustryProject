import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { INiazsanjiPersonGradeMarineSuffix } from 'app/shared/model/niazsanji-person-grade-marine-suffix.model';

@Component({
    selector: 'mi-niazsanji-person-grade-marine-suffix-detail',
    templateUrl: './niazsanji-person-grade-marine-suffix-detail.component.html'
})
export class NiazsanjiPersonGradeMarineSuffixDetailComponent implements OnInit {
    niazsanjiPersonGrade: INiazsanjiPersonGradeMarineSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ niazsanjiPersonGrade }) => {
            this.niazsanjiPersonGrade = niazsanjiPersonGrade;
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
