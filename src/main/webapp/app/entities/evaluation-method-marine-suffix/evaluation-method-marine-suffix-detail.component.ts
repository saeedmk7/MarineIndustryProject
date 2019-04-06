import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEvaluationMethodMarineSuffix } from 'app/shared/model/evaluation-method-marine-suffix.model';

@Component({
    selector: 'mi-evaluation-method-marine-suffix-detail',
    templateUrl: './evaluation-method-marine-suffix-detail.component.html'
})
export class EvaluationMethodMarineSuffixDetailComponent implements OnInit {
    evaluationMethod: IEvaluationMethodMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ evaluationMethod }) => {
            this.evaluationMethod = evaluationMethod;
        });
    }

    previousState() {
        window.history.back();
    }
}
