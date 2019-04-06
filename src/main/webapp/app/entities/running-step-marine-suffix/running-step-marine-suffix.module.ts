import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    RunningStepMarineSuffixComponent,
    RunningStepMarineSuffixDetailComponent,
    RunningStepMarineSuffixUpdateComponent,
    RunningStepMarineSuffixDeletePopupComponent,
    RunningStepMarineSuffixDeleteDialogComponent,
    runningStepRoute,
    runningStepPopupRoute
} from './';

const ENTITY_STATES = [...runningStepRoute, ...runningStepPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RunningStepMarineSuffixComponent,
        RunningStepMarineSuffixDetailComponent,
        RunningStepMarineSuffixUpdateComponent,
        RunningStepMarineSuffixDeleteDialogComponent,
        RunningStepMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        RunningStepMarineSuffixComponent,
        RunningStepMarineSuffixUpdateComponent,
        RunningStepMarineSuffixDeleteDialogComponent,
        RunningStepMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojRunningStepMarineSuffixModule {}
