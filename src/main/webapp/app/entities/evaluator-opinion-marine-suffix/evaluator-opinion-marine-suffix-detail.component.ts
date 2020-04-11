import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEvaluatorOpinionMarineSuffix } from 'app/shared/model/evaluator-opinion-marine-suffix.model';

@Component({
    selector: 'mi-evaluator-opinion-marine-suffix-detail',
    templateUrl: './evaluator-opinion-marine-suffix-detail.component.html'
})
export class EvaluatorOpinionMarineSuffixDetailComponent implements OnInit {
    evaluatorOpinion: IEvaluatorOpinionMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ evaluatorOpinion }) => {
            this.evaluatorOpinion = evaluatorOpinion;
        });
    }

    previousState() {
        window.history.back();
    }
}
