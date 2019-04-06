import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWorkUnitMarineSuffix } from 'app/shared/model/work-unit-marine-suffix.model';

@Component({
    selector: 'mi-work-unit-marine-suffix-detail',
    templateUrl: './work-unit-marine-suffix-detail.component.html'
})
export class WorkUnitMarineSuffixDetailComponent implements OnInit {
    workUnit: IWorkUnitMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ workUnit }) => {
            this.workUnit = workUnit;
        });
    }

    previousState() {
        window.history.back();
    }
}
