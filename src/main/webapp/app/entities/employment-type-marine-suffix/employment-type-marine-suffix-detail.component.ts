import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEmploymentTypeMarineSuffix } from 'app/shared/model/employment-type-marine-suffix.model';

@Component({
    selector: 'mi-employment-type-marine-suffix-detail',
    templateUrl: './employment-type-marine-suffix-detail.component.html'
})
export class EmploymentTypeMarineSuffixDetailComponent implements OnInit {
    employmentType: IEmploymentTypeMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ employmentType }) => {
            this.employmentType = employmentType;
        });
    }

    previousState() {
        window.history.back();
    }
}
