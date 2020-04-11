import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IActivationNiazsanjiMarineSuffix } from 'app/shared/model/activation-niazsanji-marine-suffix.model';

@Component({
    selector: 'mi-activation-niazsanji-marine-suffix-detail',
    templateUrl: './activation-niazsanji-marine-suffix-detail.component.html'
})
export class ActivationNiazsanjiMarineSuffixDetailComponent implements OnInit {
    activationNiazsanji: IActivationNiazsanjiMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ activationNiazsanji }) => {
            this.activationNiazsanji = activationNiazsanji;
        });
    }

    previousState() {
        window.history.back();
    }
}
