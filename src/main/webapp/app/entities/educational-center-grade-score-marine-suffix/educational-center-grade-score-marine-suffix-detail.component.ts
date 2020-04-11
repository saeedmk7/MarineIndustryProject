import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEducationalCenterGradeScoreMarineSuffix } from 'app/shared/model/educational-center-grade-score-marine-suffix.model';

@Component({
    selector: 'mi-educational-center-grade-score-marine-suffix-detail',
    templateUrl: './educational-center-grade-score-marine-suffix-detail.component.html'
})
export class EducationalCenterGradeScoreMarineSuffixDetailComponent implements OnInit {
    educationalCenterGradeScore: IEducationalCenterGradeScoreMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ educationalCenterGradeScore }) => {
            this.educationalCenterGradeScore = educationalCenterGradeScore;
        });
    }

    previousState() {
        window.history.back();
    }
}
