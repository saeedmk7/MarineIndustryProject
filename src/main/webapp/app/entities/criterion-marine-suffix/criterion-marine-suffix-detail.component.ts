import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICriterionMarineSuffix } from 'app/shared/model/criterion-marine-suffix.model';

@Component({
    selector: 'mi-criterion-marine-suffix-detail',
    templateUrl: './criterion-marine-suffix-detail.component.html'
})
export class CriterionMarineSuffixDetailComponent implements OnInit {
    criterion: ICriterionMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ criterion }) => {
            this.criterion = criterion;
        });
    }

    previousState() {
        window.history.back();
    }
}
