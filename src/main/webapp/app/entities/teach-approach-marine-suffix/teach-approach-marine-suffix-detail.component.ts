import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITeachApproachMarineSuffix } from 'app/shared/model/teach-approach-marine-suffix.model';

@Component({
    selector: 'mi-teach-approach-marine-suffix-detail',
    templateUrl: './teach-approach-marine-suffix-detail.component.html'
})
export class TeachApproachMarineSuffixDetailComponent implements OnInit {
    teachApproach: ITeachApproachMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ teachApproach }) => {
            this.teachApproach = teachApproach;
        });
    }

    previousState() {
        window.history.back();
    }
}
