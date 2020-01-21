import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INiazsanjiInputMarineSuffix } from 'app/shared/model/niazsanji-input-marine-suffix.model';

@Component({
    selector: 'mi-niazsanji-input-marine-suffix-detail',
    templateUrl: './niazsanji-input-marine-suffix-detail.component.html'
})
export class NiazsanjiInputMarineSuffixDetailComponent implements OnInit {
    niazsanjiInput: INiazsanjiInputMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ niazsanjiInput }) => {
            this.niazsanjiInput = niazsanjiInput;
        });
    }

    previousState() {
        window.history.back();
    }
}
