import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

@Component({
    selector: 'mi-educational-module-marine-suffix-detail',
    templateUrl: './educational-module-marine-suffix-detail.component.html'
})
export class EducationalModuleMarineSuffixDetailComponent implements OnInit {
    educationalModule: IEducationalModuleMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute,private convertObjectDatesService : ConvertObjectDatesService) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ educationalModule }) => {
            this.educationalModule = this.convertObjectDatesService.changeDate(educationalModule);
        });
    }

    previousState() {
        window.history.back();
    }
}
