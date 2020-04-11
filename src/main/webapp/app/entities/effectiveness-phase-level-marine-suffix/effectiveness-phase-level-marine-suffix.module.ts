import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    EffectivenessPhaseLevelMarineSuffixComponent,
    EffectivenessPhaseLevelMarineSuffixDetailComponent,
    EffectivenessPhaseLevelMarineSuffixUpdateComponent,
    EffectivenessPhaseLevelMarineSuffixDeletePopupComponent,
    EffectivenessPhaseLevelMarineSuffixDeleteDialogComponent,
    effectivenessPhaseLevelRoute,
    effectivenessPhaseLevelPopupRoute
} from './';

const ENTITY_STATES = [...effectivenessPhaseLevelRoute, ...effectivenessPhaseLevelPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EffectivenessPhaseLevelMarineSuffixComponent,
        EffectivenessPhaseLevelMarineSuffixDetailComponent,
        EffectivenessPhaseLevelMarineSuffixUpdateComponent,
        EffectivenessPhaseLevelMarineSuffixDeleteDialogComponent,
        EffectivenessPhaseLevelMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        EffectivenessPhaseLevelMarineSuffixComponent,
        EffectivenessPhaseLevelMarineSuffixUpdateComponent,
        EffectivenessPhaseLevelMarineSuffixDeleteDialogComponent,
        EffectivenessPhaseLevelMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojEffectivenessPhaseLevelMarineSuffixModule {}
