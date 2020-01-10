import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFixCompetencyDeficiencyMarineSuffix } from 'app/shared/model/fix-competency-deficiency-marine-suffix.model';

@Component({
    selector: 'mi-fix-competency-deficiency-marine-suffix-detail',
    templateUrl: './fix-competency-deficiency-marine-suffix-detail.component.html'
})
export class FixCompetencyDeficiencyMarineSuffixDetailComponent implements OnInit {
    fixCompetencyDeficiency: IFixCompetencyDeficiencyMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ fixCompetencyDeficiency }) => {
            this.fixCompetencyDeficiency = fixCompetencyDeficiency;
        });
    }

    previousState() {
        window.history.back();
    }
}
