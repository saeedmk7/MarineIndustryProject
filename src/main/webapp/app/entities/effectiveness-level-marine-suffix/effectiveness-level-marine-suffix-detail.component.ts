import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEffectivenessLevelMarineSuffix } from 'app/shared/model/effectiveness-level-marine-suffix.model';

@Component({
    selector: 'mi-effectiveness-level-marine-suffix-detail',
    templateUrl: './effectiveness-level-marine-suffix-detail.component.html'
})
export class EffectivenessLevelMarineSuffixDetailComponent implements OnInit {
    effectivenessLevel: IEffectivenessLevelMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ effectivenessLevel }) => {
            this.effectivenessLevel = effectivenessLevel;
        });
    }

    previousState() {
        window.history.back();
    }
}
