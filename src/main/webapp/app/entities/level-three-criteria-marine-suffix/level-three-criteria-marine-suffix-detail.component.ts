import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILevelThreeCriteriaMarineSuffix } from 'app/shared/model/level-three-criteria-marine-suffix.model';

@Component({
    selector: 'mi-level-three-criteria-marine-suffix-detail',
    templateUrl: './level-three-criteria-marine-suffix-detail.component.html'
})
export class LevelThreeCriteriaMarineSuffixDetailComponent implements OnInit {
    levelThreeCriteria: ILevelThreeCriteriaMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ levelThreeCriteria }) => {
            this.levelThreeCriteria = levelThreeCriteria;
        });
    }

    previousState() {
        window.history.back();
    }
}
