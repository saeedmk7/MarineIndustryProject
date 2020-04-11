import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    LevelThreeScoreMarineSuffixComponent,
    LevelThreeScoreMarineSuffixDetailComponent,
    LevelThreeScoreMarineSuffixUpdateComponent,
    LevelThreeScoreMarineSuffixDeletePopupComponent,
    LevelThreeScoreMarineSuffixDeleteDialogComponent,
    levelThreeScoreRoute,
    levelThreeScorePopupRoute
} from './';

const ENTITY_STATES = [...levelThreeScoreRoute, ...levelThreeScorePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        LevelThreeScoreMarineSuffixComponent,
        LevelThreeScoreMarineSuffixDetailComponent,
        LevelThreeScoreMarineSuffixUpdateComponent,
        LevelThreeScoreMarineSuffixDeleteDialogComponent,
        LevelThreeScoreMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        LevelThreeScoreMarineSuffixComponent,
        LevelThreeScoreMarineSuffixUpdateComponent,
        LevelThreeScoreMarineSuffixDeleteDialogComponent,
        LevelThreeScoreMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojLevelThreeScoreMarineSuffixModule {}
