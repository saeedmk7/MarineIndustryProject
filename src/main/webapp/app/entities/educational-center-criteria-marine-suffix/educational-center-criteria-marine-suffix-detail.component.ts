import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEducationalCenterCriteriaMarineSuffix } from 'app/shared/model/educational-center-criteria-marine-suffix.model';

@Component({
    selector: 'mi-educational-center-criteria-marine-suffix-detail',
    templateUrl: './educational-center-criteria-marine-suffix-detail.component.html'
})
export class EducationalCenterCriteriaMarineSuffixDetailComponent implements OnInit {
    educationalCenterCriteria: IEducationalCenterCriteriaMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ educationalCenterCriteria }) => {
            this.educationalCenterCriteria = educationalCenterCriteria;
        });
    }

    previousState() {
        window.history.back();
    }
}
