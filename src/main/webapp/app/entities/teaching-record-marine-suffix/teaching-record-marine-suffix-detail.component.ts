import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITeachingRecordMarineSuffix } from 'app/shared/model/teaching-record-marine-suffix.model';

@Component({
    selector: 'mi-teaching-record-marine-suffix-detail',
    templateUrl: './teaching-record-marine-suffix-detail.component.html'
})
export class TeachingRecordMarineSuffixDetailComponent implements OnInit {
    teachingRecord: ITeachingRecordMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ teachingRecord }) => {
            this.teachingRecord = teachingRecord;
        });
    }

    previousState() {
        window.history.back();
    }
}
