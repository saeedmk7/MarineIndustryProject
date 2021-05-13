import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    MatchingRunningStepMarineSuffixComponent,
    MatchingRunningStepMarineSuffixDetailComponent,
    MatchingRunningStepMarineSuffixUpdateComponent,
    MatchingRunningStepMarineSuffixDeletePopupComponent,
    MatchingRunningStepMarineSuffixDeleteDialogComponent,
    matchingRunningStepRoute,
    matchingRunningStepPopupRoute
} from './';

const ENTITY_STATES = [...matchingRunningStepRoute, ...matchingRunningStepPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MatchingRunningStepMarineSuffixComponent,
        MatchingRunningStepMarineSuffixDetailComponent,
        MatchingRunningStepMarineSuffixUpdateComponent,
        MatchingRunningStepMarineSuffixDeleteDialogComponent,
        MatchingRunningStepMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        MatchingRunningStepMarineSuffixComponent,
        MatchingRunningStepMarineSuffixUpdateComponent,
        MatchingRunningStepMarineSuffixDeleteDialogComponent,
        MatchingRunningStepMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojMatchingRunningStepMarineSuffixModule {}
