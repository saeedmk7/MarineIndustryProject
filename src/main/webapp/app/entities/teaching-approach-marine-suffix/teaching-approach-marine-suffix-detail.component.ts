import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITeachingApproachMarineSuffix } from 'app/shared/model/teaching-approach-marine-suffix.model';

@Component({
    selector: 'mi-teaching-approach-marine-suffix-detail',
    templateUrl: './teaching-approach-marine-suffix-detail.component.html'
})
export class TeachingApproachMarineSuffixDetailComponent implements OnInit {
    teachingApproach: ITeachingApproachMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ teachingApproach }) => {
            this.teachingApproach = teachingApproach;
        });
    }

    previousState() {
        window.history.back();
    }
}
