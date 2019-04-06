import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    RunRunningStepMarineSuffixComponent,
    RunRunningStepMarineSuffixDetailComponent,
    RunRunningStepMarineSuffixUpdateComponent,
    RunRunningStepMarineSuffixDeletePopupComponent,
    RunRunningStepMarineSuffixDeleteDialogComponent,
    runRunningStepRoute,
    runRunningStepPopupRoute
} from './';

const ENTITY_STATES = [...runRunningStepRoute, ...runRunningStepPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RunRunningStepMarineSuffixComponent,
        RunRunningStepMarineSuffixDetailComponent,
        RunRunningStepMarineSuffixUpdateComponent,
        RunRunningStepMarineSuffixDeleteDialogComponent,
        RunRunningStepMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        RunRunningStepMarineSuffixComponent,
        RunRunningStepMarineSuffixUpdateComponent,
        RunRunningStepMarineSuffixDeleteDialogComponent,
        RunRunningStepMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojRunRunningStepMarineSuffixModule {}
