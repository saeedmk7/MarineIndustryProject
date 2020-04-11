import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    LevelFourEffectivenessMarineSuffixComponent,
    LevelFourEffectivenessMarineSuffixDetailComponent,
    LevelFourEffectivenessMarineSuffixUpdateComponent,
    LevelFourEffectivenessMarineSuffixDeletePopupComponent,
    LevelFourEffectivenessMarineSuffixDeleteDialogComponent,
    levelFourEffectivenessRoute,
    levelFourEffectivenessPopupRoute
} from './';

const ENTITY_STATES = [...levelFourEffectivenessRoute, ...levelFourEffectivenessPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        LevelFourEffectivenessMarineSuffixComponent,
        LevelFourEffectivenessMarineSuffixDetailComponent,
        LevelFourEffectivenessMarineSuffixUpdateComponent,
        LevelFourEffectivenessMarineSuffixDeleteDialogComponent,
        LevelFourEffectivenessMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        LevelFourEffectivenessMarineSuffixComponent,
        LevelFourEffectivenessMarineSuffixUpdateComponent,
        LevelFourEffectivenessMarineSuffixDeleteDialogComponent,
        LevelFourEffectivenessMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojLevelFourEffectivenessMarineSuffixModule {}
