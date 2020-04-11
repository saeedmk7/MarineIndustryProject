import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    LevelFourScoreMarineSuffixComponent,
    LevelFourScoreMarineSuffixDetailComponent,
    LevelFourScoreMarineSuffixUpdateComponent,
    LevelFourScoreMarineSuffixDeletePopupComponent,
    LevelFourScoreMarineSuffixDeleteDialogComponent,
    levelFourScoreRoute,
    levelFourScorePopupRoute
} from './';

const ENTITY_STATES = [...levelFourScoreRoute, ...levelFourScorePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        LevelFourScoreMarineSuffixComponent,
        LevelFourScoreMarineSuffixDetailComponent,
        LevelFourScoreMarineSuffixUpdateComponent,
        LevelFourScoreMarineSuffixDeleteDialogComponent,
        LevelFourScoreMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        LevelFourScoreMarineSuffixComponent,
        LevelFourScoreMarineSuffixUpdateComponent,
        LevelFourScoreMarineSuffixDeleteDialogComponent,
        LevelFourScoreMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojLevelFourScoreMarineSuffixModule {}
