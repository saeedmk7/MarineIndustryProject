import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    EffectivenessLevelMarineSuffixComponent,
    EffectivenessLevelMarineSuffixDetailComponent,
    EffectivenessLevelMarineSuffixUpdateComponent,
    EffectivenessLevelMarineSuffixDeletePopupComponent,
    EffectivenessLevelMarineSuffixDeleteDialogComponent,
    effectivenessLevelRoute,
    effectivenessLevelPopupRoute
} from './';

const ENTITY_STATES = [...effectivenessLevelRoute, ...effectivenessLevelPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EffectivenessLevelMarineSuffixComponent,
        EffectivenessLevelMarineSuffixDetailComponent,
        EffectivenessLevelMarineSuffixUpdateComponent,
        EffectivenessLevelMarineSuffixDeleteDialogComponent,
        EffectivenessLevelMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        EffectivenessLevelMarineSuffixComponent,
        EffectivenessLevelMarineSuffixUpdateComponent,
        EffectivenessLevelMarineSuffixDeleteDialogComponent,
        EffectivenessLevelMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojEffectivenessLevelMarineSuffixModule {}
