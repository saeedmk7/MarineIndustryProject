import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEducationalRecordMarineSuffix } from 'app/shared/model/educational-record-marine-suffix.model';

@Component({
    selector: 'mi-educational-record-marine-suffix-detail',
    templateUrl: './educational-record-marine-suffix-detail.component.html'
})
export class EducationalRecordMarineSuffixDetailComponent implements OnInit {
    educationalRecord: IEducationalRecordMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ educationalRecord }) => {
            this.educationalRecord = educationalRecord;
        });
    }

    previousState() {
        window.history.back();
    }
}
