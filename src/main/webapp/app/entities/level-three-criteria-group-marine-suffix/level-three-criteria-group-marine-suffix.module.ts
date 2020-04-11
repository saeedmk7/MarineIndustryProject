import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    LevelThreeCriteriaGroupMarineSuffixComponent,
    LevelThreeCriteriaGroupMarineSuffixDetailComponent,
    LevelThreeCriteriaGroupMarineSuffixUpdateComponent,
    LevelThreeCriteriaGroupMarineSuffixDeletePopupComponent,
    LevelThreeCriteriaGroupMarineSuffixDeleteDialogComponent,
    levelThreeCriteriaGroupRoute,
    levelThreeCriteriaGroupPopupRoute
} from './';

const ENTITY_STATES = [...levelThreeCriteriaGroupRoute, ...levelThreeCriteriaGroupPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        LevelThreeCriteriaGroupMarineSuffixComponent,
        LevelThreeCriteriaGroupMarineSuffixDetailComponent,
        LevelThreeCriteriaGroupMarineSuffixUpdateComponent,
        LevelThreeCriteriaGroupMarineSuffixDeleteDialogComponent,
        LevelThreeCriteriaGroupMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        LevelThreeCriteriaGroupMarineSuffixComponent,
        LevelThreeCriteriaGroupMarineSuffixUpdateComponent,
        LevelThreeCriteriaGroupMarineSuffixDeleteDialogComponent,
        LevelThreeCriteriaGroupMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojLevelThreeCriteriaGroupMarineSuffixModule {}
