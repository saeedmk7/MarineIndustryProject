import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDesignAndPlanningMarineSuffix } from 'app/shared/model/design-and-planning-marine-suffix.model';

@Component({
    selector: 'mi-design-and-planning-marine-suffix-detail',
    templateUrl: './design-and-planning-marine-suffix-detail.component.html'
})
export class DesignAndPlanningMarineSuffixDetailComponent implements OnInit {
    designAndPlanning: IDesignAndPlanningMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ designAndPlanning }) => {
            this.designAndPlanning = designAndPlanning;
        });
    }

    previousState() {
        window.history.back();
    }
}
