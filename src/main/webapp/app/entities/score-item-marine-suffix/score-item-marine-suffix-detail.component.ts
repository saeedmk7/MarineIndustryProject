import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IScoreItemMarineSuffix } from 'app/shared/model/score-item-marine-suffix.model';

@Component({
    selector: 'mi-score-item-marine-suffix-detail',
    templateUrl: './score-item-marine-suffix-detail.component.html'
})
export class ScoreItemMarineSuffixDetailComponent implements OnInit {
    scoreItem: IScoreItemMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ scoreItem }) => {
            this.scoreItem = scoreItem;
        });
    }

    previousState() {
        window.history.back();
    }
}
