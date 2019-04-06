import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFieldOfStudyMarineSuffix } from 'app/shared/model/field-of-study-marine-suffix.model';

@Component({
    selector: 'mi-field-of-study-marine-suffix-detail',
    templateUrl: './field-of-study-marine-suffix-detail.component.html'
})
export class FieldOfStudyMarineSuffixDetailComponent implements OnInit {
    fieldOfStudy: IFieldOfStudyMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ fieldOfStudy }) => {
            this.fieldOfStudy = fieldOfStudy;
        });
    }

    previousState() {
        window.history.back();
    }
}
