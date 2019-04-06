import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    GoalMarineSuffixComponent,
    GoalMarineSuffixDetailComponent,
    GoalMarineSuffixUpdateComponent,
    GoalMarineSuffixDeletePopupComponent,
    GoalMarineSuffixDeleteDialogComponent,
    goalRoute,
    goalPopupRoute
} from './';

const ENTITY_STATES = [...goalRoute, ...goalPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        GoalMarineSuffixComponent,
        GoalMarineSuffixDetailComponent,
        GoalMarineSuffixUpdateComponent,
        GoalMarineSuffixDeleteDialogComponent,
        GoalMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        GoalMarineSuffixComponent,
        GoalMarineSuffixUpdateComponent,
        GoalMarineSuffixDeleteDialogComponent,
        GoalMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojGoalMarineSuffixModule {}
