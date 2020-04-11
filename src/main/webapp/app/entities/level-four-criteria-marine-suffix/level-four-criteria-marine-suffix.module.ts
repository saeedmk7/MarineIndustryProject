import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    LevelFourCriteriaMarineSuffixComponent,
    LevelFourCriteriaMarineSuffixDetailComponent,
    LevelFourCriteriaMarineSuffixUpdateComponent,
    LevelFourCriteriaMarineSuffixDeletePopupComponent,
    LevelFourCriteriaMarineSuffixDeleteDialogComponent,
    levelFourCriteriaRoute,
    levelFourCriteriaPopupRoute
} from './';

const ENTITY_STATES = [...levelFourCriteriaRoute, ...levelFourCriteriaPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        LevelFourCriteriaMarineSuffixComponent,
        LevelFourCriteriaMarineSuffixDetailComponent,
        LevelFourCriteriaMarineSuffixUpdateComponent,
        LevelFourCriteriaMarineSuffixDeleteDialogComponent,
        LevelFourCriteriaMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        LevelFourCriteriaMarineSuffixComponent,
        LevelFourCriteriaMarineSuffixUpdateComponent,
        LevelFourCriteriaMarineSuffixDeleteDialogComponent,
        LevelFourCriteriaMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojLevelFourCriteriaMarineSuffixModule {}
