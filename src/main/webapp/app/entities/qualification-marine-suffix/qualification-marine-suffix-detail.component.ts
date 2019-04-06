import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IQualificationMarineSuffix } from 'app/shared/model/qualification-marine-suffix.model';

@Component({
    selector: 'mi-qualification-marine-suffix-detail',
    templateUrl: './qualification-marine-suffix-detail.component.html'
})
export class QualificationMarineSuffixDetailComponent implements OnInit {
    qualification: IQualificationMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ qualification }) => {
            this.qualification = qualification;
        });
    }

    previousState() {
        window.history.back();
    }
}
