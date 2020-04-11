import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INiazsanjiPersonGradeScoreMarineSuffix } from 'app/shared/model/niazsanji-person-grade-score-marine-suffix.model';

@Component({
    selector: 'mi-niazsanji-person-grade-score-marine-suffix-detail',
    templateUrl: './niazsanji-person-grade-score-marine-suffix-detail.component.html'
})
export class NiazsanjiPersonGradeScoreMarineSuffixDetailComponent implements OnInit {
    niazsanjiPersonGradeScore: INiazsanjiPersonGradeScoreMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ niazsanjiPersonGradeScore }) => {
            this.niazsanjiPersonGradeScore = niazsanjiPersonGradeScore;
        });
    }

    previousState() {
        window.history.back();
    }
}
