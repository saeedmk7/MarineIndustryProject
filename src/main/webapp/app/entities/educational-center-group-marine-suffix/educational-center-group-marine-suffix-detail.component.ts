import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEducationalCenterGroupMarineSuffix } from 'app/shared/model/educational-center-group-marine-suffix.model';

@Component({
    selector: 'mi-educational-center-group-marine-suffix-detail',
    templateUrl: './educational-center-group-marine-suffix-detail.component.html'
})
export class EducationalCenterGroupMarineSuffixDetailComponent implements OnInit {
    educationalCenterGroup: IEducationalCenterGroupMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ educationalCenterGroup }) => {
            this.educationalCenterGroup = educationalCenterGroup;
        });
    }

    previousState() {
        window.history.back();
    }
}
