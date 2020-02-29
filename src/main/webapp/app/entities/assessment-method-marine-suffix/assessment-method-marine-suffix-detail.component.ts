import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAssessmentMethodMarineSuffix } from 'app/shared/model/assessment-method-marine-suffix.model';

@Component({
    selector: 'mi-assessment-method-marine-suffix-detail',
    templateUrl: './assessment-method-marine-suffix-detail.component.html'
})
export class AssessmentMethodMarineSuffixDetailComponent implements OnInit {
    assessmentMethod: IAssessmentMethodMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ assessmentMethod }) => {
            this.assessmentMethod = assessmentMethod;
        });
    }

    previousState() {
        window.history.back();
    }
}
