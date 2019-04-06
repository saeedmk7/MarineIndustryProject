import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGoalMarineSuffix } from 'app/shared/model/goal-marine-suffix.model';

@Component({
    selector: 'mi-goal-marine-suffix-detail',
    templateUrl: './goal-marine-suffix-detail.component.html'
})
export class GoalMarineSuffixDetailComponent implements OnInit {
    goal: IGoalMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ goal }) => {
            this.goal = goal;
        });
    }

    previousState() {
        window.history.back();
    }
}
