import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWorkGroupMarineSuffix } from 'app/shared/model/work-group-marine-suffix.model';

@Component({
    selector: 'mi-work-group-marine-suffix-detail',
    templateUrl: './work-group-marine-suffix-detail.component.html'
})
export class WorkGroupMarineSuffixDetailComponent implements OnInit {
    workGroup: IWorkGroupMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ workGroup }) => {
            this.workGroup = workGroup;
        });
    }

    previousState() {
        window.history.back();
    }
}
