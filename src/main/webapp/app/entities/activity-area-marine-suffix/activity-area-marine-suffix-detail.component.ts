import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IActivityAreaMarineSuffix } from 'app/shared/model/activity-area-marine-suffix.model';

@Component({
    selector: 'mi-activity-area-marine-suffix-detail',
    templateUrl: './activity-area-marine-suffix-detail.component.html'
})
export class ActivityAreaMarineSuffixDetailComponent implements OnInit {
    activityArea: IActivityAreaMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ activityArea }) => {
            this.activityArea = activityArea;
        });
    }

    previousState() {
        window.history.back();
    }
}
