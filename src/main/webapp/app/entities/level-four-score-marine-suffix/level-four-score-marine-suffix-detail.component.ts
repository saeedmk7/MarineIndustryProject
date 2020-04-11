import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILevelFourScoreMarineSuffix } from 'app/shared/model/level-four-score-marine-suffix.model';

@Component({
    selector: 'mi-level-four-score-marine-suffix-detail',
    templateUrl: './level-four-score-marine-suffix-detail.component.html'
})
export class LevelFourScoreMarineSuffixDetailComponent implements OnInit {
    levelFourScore: ILevelFourScoreMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ levelFourScore }) => {
            this.levelFourScore = levelFourScore;
        });
    }

    previousState() {
        window.history.back();
    }
}
