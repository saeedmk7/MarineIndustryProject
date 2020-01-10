import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPreJobNiazsanjiCompetencyMarineSuffix } from 'app/shared/model/pre-job-niazsanji-competency-marine-suffix.model';

@Component({
    selector: 'mi-pre-job-niazsanji-competency-marine-suffix-detail',
    templateUrl: './pre-job-niazsanji-competency-marine-suffix-detail.component.html'
})
export class PreJobNiazsanjiCompetencyMarineSuffixDetailComponent implements OnInit {
    preJobNiazsanjiCompetency: IPreJobNiazsanjiCompetencyMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ preJobNiazsanjiCompetency }) => {
            this.preJobNiazsanjiCompetency = preJobNiazsanjiCompetency;
        });
    }

    previousState() {
        window.history.back();
    }
}
