import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICompetencyMarineSuffix } from 'app/shared/model/competency-marine-suffix.model';

@Component({
    selector: 'mi-competency-marine-suffix-detail',
    templateUrl: './competency-marine-suffix-detail.component.html'
})
export class CompetencyMarineSuffixDetailComponent implements OnInit {
    competency: ICompetencyMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ competency }) => {
            this.competency = competency;
        });
    }

    previousState() {
        window.history.back();
    }
}
