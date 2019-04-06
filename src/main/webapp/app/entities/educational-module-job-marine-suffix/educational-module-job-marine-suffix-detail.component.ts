import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEducationalModuleJobMarineSuffix } from 'app/shared/model/educational-module-job-marine-suffix.model';

@Component({
    selector: 'mi-educational-module-job-marine-suffix-detail',
    templateUrl: './educational-module-job-marine-suffix-detail.component.html'
})
export class EducationalModuleJobMarineSuffixDetailComponent implements OnInit {
    educationalModuleJob: IEducationalModuleJobMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ educationalModuleJob }) => {
            this.educationalModuleJob = educationalModuleJob;
        });
    }

    previousState() {
        window.history.back();
    }
}
