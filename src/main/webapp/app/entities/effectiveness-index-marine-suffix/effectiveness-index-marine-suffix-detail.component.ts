import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEffectivenessIndexMarineSuffix } from 'app/shared/model/effectiveness-index-marine-suffix.model';

@Component({
    selector: 'mi-effectiveness-index-marine-suffix-detail',
    templateUrl: './effectiveness-index-marine-suffix-detail.component.html'
})
export class EffectivenessIndexMarineSuffixDetailComponent implements OnInit {
    effectivenessIndex: IEffectivenessIndexMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ effectivenessIndex }) => {
            this.effectivenessIndex = effectivenessIndex;
        });
    }

    previousState() {
        window.history.back();
    }
}
