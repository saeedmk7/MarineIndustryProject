import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWorkIndustryMarineSuffix } from 'app/shared/model/work-industry-marine-suffix.model';

@Component({
    selector: 'mi-work-industry-marine-suffix-detail',
    templateUrl: './work-industry-marine-suffix-detail.component.html'
})
export class WorkIndustryMarineSuffixDetailComponent implements OnInit {
    workIndustry: IWorkIndustryMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ workIndustry }) => {
            this.workIndustry = workIndustry;
        });
    }

    previousState() {
        window.history.back();
    }
}
