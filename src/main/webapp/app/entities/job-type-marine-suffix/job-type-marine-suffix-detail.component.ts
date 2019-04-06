import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IJobTypeMarineSuffix } from 'app/shared/model/job-type-marine-suffix.model';

@Component({
    selector: 'mi-job-type-marine-suffix-detail',
    templateUrl: './job-type-marine-suffix-detail.component.html'
})
export class JobTypeMarineSuffixDetailComponent implements OnInit {
    jobType: IJobTypeMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ jobType }) => {
            this.jobType = jobType;
        });
    }

    previousState() {
        window.history.back();
    }
}
