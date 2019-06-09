import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IJobRecordMarineSuffix } from 'app/shared/model/job-record-marine-suffix.model';

@Component({
    selector: 'mi-job-record-marine-suffix-detail',
    templateUrl: './job-record-marine-suffix-detail.component.html'
})
export class JobRecordMarineSuffixDetailComponent implements OnInit {
    jobRecord: IJobRecordMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ jobRecord }) => {
            this.jobRecord = jobRecord;
        });
    }

    previousState() {
        window.history.back();
    }
}
