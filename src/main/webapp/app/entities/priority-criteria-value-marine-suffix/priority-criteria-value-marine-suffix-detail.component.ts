import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPriorityCriteriaValueMarineSuffix } from 'app/shared/model/priority-criteria-value-marine-suffix.model';

@Component({
    selector: 'mi-priority-criteria-value-marine-suffix-detail',
    templateUrl: './priority-criteria-value-marine-suffix-detail.component.html'
})
export class PriorityCriteriaValueMarineSuffixDetailComponent implements OnInit {
    priorityCriteriaValue: IPriorityCriteriaValueMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ priorityCriteriaValue }) => {
            this.priorityCriteriaValue = priorityCriteriaValue;
        });
    }

    previousState() {
        window.history.back();
    }
}
