import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEducationalCenterServiceMarineSuffix } from 'app/shared/model/educational-center-service-marine-suffix.model';

@Component({
    selector: 'mi-educational-center-service-marine-suffix-detail',
    templateUrl: './educational-center-service-marine-suffix-detail.component.html'
})
export class EducationalCenterServiceMarineSuffixDetailComponent implements OnInit {
    educationalCenterService: IEducationalCenterServiceMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ educationalCenterService }) => {
            this.educationalCenterService = educationalCenterService;
        });
    }

    previousState() {
        window.history.back();
    }
}
