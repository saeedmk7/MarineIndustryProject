import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    EffectivenessIndexMarineSuffixComponent,
    EffectivenessIndexMarineSuffixDetailComponent,
    EffectivenessIndexMarineSuffixUpdateComponent,
    EffectivenessIndexMarineSuffixDeletePopupComponent,
    EffectivenessIndexMarineSuffixDeleteDialogComponent,
    effectivenessIndexRoute,
    effectivenessIndexPopupRoute
} from './';

const ENTITY_STATES = [...effectivenessIndexRoute, ...effectivenessIndexPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EffectivenessIndexMarineSuffixComponent,
        EffectivenessIndexMarineSuffixDetailComponent,
        EffectivenessIndexMarineSuffixUpdateComponent,
        EffectivenessIndexMarineSuffixDeleteDialogComponent,
        EffectivenessIndexMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        EffectivenessIndexMarineSuffixComponent,
        EffectivenessIndexMarineSuffixUpdateComponent,
        EffectivenessIndexMarineSuffixDeleteDialogComponent,
        EffectivenessIndexMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojEffectivenessIndexMarineSuffixModule {}
