import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEffectivenessPhaseLevelMarineSuffix } from 'app/shared/model/effectiveness-phase-level-marine-suffix.model';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

@Component({
    selector: 'mi-effectiveness-phase-level-marine-suffix-detail',
    templateUrl: './effectiveness-phase-level-marine-suffix-detail.component.html'
})
export class EffectivenessPhaseLevelMarineSuffixDetailComponent implements OnInit {
    effectivenessPhaseLevel: IEffectivenessPhaseLevelMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute, private convertObjectDatesService: ConvertObjectDatesService) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ effectivenessPhaseLevel }) => {
            this.effectivenessPhaseLevel = this.convertObjectDatesService.changeArrayDate(effectivenessPhaseLevel);
        });
    }

    previousState() {
        window.history.back();
    }
}
