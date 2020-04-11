import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILevelThreeScoreMarineSuffix } from 'app/shared/model/level-three-score-marine-suffix.model';

@Component({
    selector: 'mi-level-three-score-marine-suffix-detail',
    templateUrl: './level-three-score-marine-suffix-detail.component.html'
})
export class LevelThreeScoreMarineSuffixDetailComponent implements OnInit {
    levelThreeScore: ILevelThreeScoreMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ levelThreeScore }) => {
            this.levelThreeScore = levelThreeScore;
        });
    }

    previousState() {
        window.history.back();
    }
}
