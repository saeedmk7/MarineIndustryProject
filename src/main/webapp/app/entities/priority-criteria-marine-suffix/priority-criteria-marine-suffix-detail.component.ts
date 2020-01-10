import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPriorityCriteriaMarineSuffix } from 'app/shared/model/priority-criteria-marine-suffix.model';

@Component({
    selector: 'mi-priority-criteria-marine-suffix-detail',
    templateUrl: './priority-criteria-marine-suffix-detail.component.html'
})
export class PriorityCriteriaMarineSuffixDetailComponent implements OnInit {
    priorityCriteria: IPriorityCriteriaMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ priorityCriteria }) => {
            this.priorityCriteria = priorityCriteria;
        });
    }

    previousState() {
        window.history.back();
    }
}
