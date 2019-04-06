import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEducationalCenterMarineSuffix } from 'app/shared/model/educational-center-marine-suffix.model';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

@Component({
    selector: 'mi-educational-center-marine-suffix-detail',
    templateUrl: './educational-center-marine-suffix-detail.component.html'
})
export class EducationalCenterMarineSuffixDetailComponent implements OnInit {
    educationalCenter: IEducationalCenterMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute,private convertObjectDatesService : ConvertObjectDatesService) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ educationalCenter }) => {
            this.educationalCenter = this.convertObjectDatesService.changeDate(educationalCenter);
        });
    }

    previousState() {
        window.history.back();
    }
}
