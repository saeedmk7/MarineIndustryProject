import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INiazsanjiPersonCriteriaMarineSuffix } from 'app/shared/model/niazsanji-person-criteria-marine-suffix.model';

@Component({
    selector: 'mi-niazsanji-person-criteria-marine-suffix-detail',
    templateUrl: './niazsanji-person-criteria-marine-suffix-detail.component.html'
})
export class NiazsanjiPersonCriteriaMarineSuffixDetailComponent implements OnInit {
    niazsanjiPersonCriteria: INiazsanjiPersonCriteriaMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ niazsanjiPersonCriteria }) => {
            this.niazsanjiPersonCriteria = niazsanjiPersonCriteria;
        });
    }

    previousState() {
        window.history.back();
    }
}
