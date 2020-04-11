import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILevelThreeCriteriaGroupMarineSuffix } from 'app/shared/model/level-three-criteria-group-marine-suffix.model';

@Component({
    selector: 'mi-level-three-criteria-group-marine-suffix-detail',
    templateUrl: './level-three-criteria-group-marine-suffix-detail.component.html'
})
export class LevelThreeCriteriaGroupMarineSuffixDetailComponent implements OnInit {
    levelThreeCriteriaGroup: ILevelThreeCriteriaGroupMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ levelThreeCriteriaGroup }) => {
            this.levelThreeCriteriaGroup = levelThreeCriteriaGroup;
        });
    }

    previousState() {
        window.history.back();
    }
}
