import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPollScoreMarineSuffix } from 'app/shared/model/poll-score-marine-suffix.model';

@Component({
    selector: 'mi-poll-score-marine-suffix-detail',
    templateUrl: './poll-score-marine-suffix-detail.component.html'
})
export class PollScoreMarineSuffixDetailComponent implements OnInit {
    pollScore: IPollScoreMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pollScore }) => {
            this.pollScore = pollScore;
        });
    }

    previousState() {
        window.history.back();
    }
}
