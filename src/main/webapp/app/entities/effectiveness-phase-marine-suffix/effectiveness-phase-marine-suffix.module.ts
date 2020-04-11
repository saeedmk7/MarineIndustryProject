import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    EffectivenessPhaseMarineSuffixComponent,
    EffectivenessPhaseMarineSuffixDetailComponent,
    EffectivenessPhaseMarineSuffixUpdateComponent,
    EffectivenessPhaseMarineSuffixDeletePopupComponent,
    EffectivenessPhaseMarineSuffixDeleteDialogComponent,
    effectivenessPhaseRoute,
    effectivenessPhasePopupRoute,
    EffectivenessPhaseLevelOneMarineSuffixComponent,
    EffectivenessPhaseLevelTwoMarineSuffixComponent,
    EffectivenessPhaseLevelThreeMarineSuffixComponent,
    EffectivenessPhaseLevelFourMarineSuffixComponent
} from './';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule} from "@angular/forms";
import {NgSelectModule} from "@ng-select/ng-select";
import {ChartModule} from "angular-highcharts";

const ENTITY_STATES = [...effectivenessPhaseRoute, ...effectivenessPhasePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, ChartModule, NgSelectModule, FormsModule, BrowserAnimationsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EffectivenessPhaseMarineSuffixComponent,
        EffectivenessPhaseLevelOneMarineSuffixComponent,
        EffectivenessPhaseLevelTwoMarineSuffixComponent,
        EffectivenessPhaseLevelThreeMarineSuffixComponent,
        EffectivenessPhaseLevelFourMarineSuffixComponent,
        EffectivenessPhaseMarineSuffixDetailComponent,
        EffectivenessPhaseMarineSuffixUpdateComponent,
        EffectivenessPhaseMarineSuffixDeleteDialogComponent,
        EffectivenessPhaseMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        EffectivenessPhaseMarineSuffixComponent,
        EffectivenessPhaseLevelOneMarineSuffixComponent,
        EffectivenessPhaseLevelTwoMarineSuffixComponent,
        EffectivenessPhaseLevelThreeMarineSuffixComponent,
        EffectivenessPhaseLevelFourMarineSuffixComponent,
        EffectivenessPhaseMarineSuffixUpdateComponent,
        EffectivenessPhaseMarineSuffixDeleteDialogComponent,
        EffectivenessPhaseMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojEffectivenessPhaseMarineSuffixModule {}
