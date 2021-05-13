import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    MatchingRunRunningStepMarineSuffixComponent,
    MatchingRunRunningStepMarineSuffixDetailComponent,
    MatchingRunRunningStepMarineSuffixUpdateComponent,
    MatchingRunRunningStepMarineSuffixDeletePopupComponent,
    MatchingRunRunningStepMarineSuffixDeleteDialogComponent,
    matchingRunRunningStepRoute,
    matchingRunRunningStepPopupRoute
} from './';

const ENTITY_STATES = [...matchingRunRunningStepRoute, ...matchingRunRunningStepPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MatchingRunRunningStepMarineSuffixComponent,
        MatchingRunRunningStepMarineSuffixDetailComponent,
        MatchingRunRunningStepMarineSuffixUpdateComponent,
        MatchingRunRunningStepMarineSuffixDeleteDialogComponent,
        MatchingRunRunningStepMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        MatchingRunRunningStepMarineSuffixComponent,
        MatchingRunRunningStepMarineSuffixUpdateComponent,
        MatchingRunRunningStepMarineSuffixDeleteDialogComponent,
        MatchingRunRunningStepMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojMatchingRunRunningStepMarineSuffixModule {}
