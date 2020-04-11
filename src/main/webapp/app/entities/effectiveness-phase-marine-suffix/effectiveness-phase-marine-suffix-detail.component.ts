import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEffectivenessPhaseMarineSuffix } from 'app/shared/model/effectiveness-phase-marine-suffix.model';

@Component({
    selector: 'mi-effectiveness-phase-marine-suffix-detail',
    templateUrl: './effectiveness-phase-marine-suffix-detail.component.html'
})
export class EffectivenessPhaseMarineSuffixDetailComponent implements OnInit {
    effectivenessPhase: IEffectivenessPhaseMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ effectivenessPhase }) => {
            this.effectivenessPhase = effectivenessPhase;
        });
    }

    previousState() {
        window.history.back();
    }
}
