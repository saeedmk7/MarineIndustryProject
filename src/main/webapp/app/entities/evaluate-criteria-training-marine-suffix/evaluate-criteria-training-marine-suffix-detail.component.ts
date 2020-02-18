import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEvaluateCriteriaTrainingMarineSuffix } from 'app/shared/model/evaluate-criteria-training-marine-suffix.model';

@Component({
    selector: 'mi-evaluate-criteria-training-marine-suffix-detail',
    templateUrl: './evaluate-criteria-training-marine-suffix-detail.component.html'
})
export class EvaluateCriteriaTrainingMarineSuffixDetailComponent implements OnInit {
    evaluateCriteriaTraining: IEvaluateCriteriaTrainingMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ evaluateCriteriaTraining }) => {
            this.evaluateCriteriaTraining = evaluateCriteriaTraining;
        });
    }

    previousState() {
        window.history.back();
    }
}
