import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILevelFourCriteriaMarineSuffix } from 'app/shared/model/level-four-criteria-marine-suffix.model';

@Component({
    selector: 'mi-level-four-criteria-marine-suffix-detail',
    templateUrl: './level-four-criteria-marine-suffix-detail.component.html'
})
export class LevelFourCriteriaMarineSuffixDetailComponent implements OnInit {
    levelFourCriteria: ILevelFourCriteriaMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ levelFourCriteria }) => {
            this.levelFourCriteria = levelFourCriteria;
        });
    }

    previousState() {
        window.history.back();
    }
}
