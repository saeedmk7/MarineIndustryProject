import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IJobMarineSuffix } from 'app/shared/model/job-marine-suffix.model';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

@Component({
    selector: 'mi-job-marine-suffix-detail',
    templateUrl: './job-marine-suffix-detail.component.html'
})
export class JobMarineSuffixDetailComponent implements OnInit {
    job: IJobMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute,private convertObjectDatesService : ConvertObjectDatesService) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ job }) => {
            this.job = this.convertObjectDatesService.changeDate(job);
        });
    }

    previousState() {
        window.history.back();
    }
}
