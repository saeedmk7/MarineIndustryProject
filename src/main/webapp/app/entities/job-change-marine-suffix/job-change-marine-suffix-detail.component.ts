import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IJobChangeMarineSuffix } from 'app/shared/model/job-change-marine-suffix.model';

@Component({
    selector: 'mi-job-change-marine-suffix-detail',
    templateUrl: './job-change-marine-suffix-detail.component.html'
})
export class JobChangeMarineSuffixDetailComponent implements OnInit {
    jobChange: IJobChangeMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ jobChange }) => {
            this.jobChange = jobChange;
        });
    }

    previousState() {
        window.history.back();
    }
}
