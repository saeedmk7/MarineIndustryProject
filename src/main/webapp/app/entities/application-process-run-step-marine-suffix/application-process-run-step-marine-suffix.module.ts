import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    ApplicationProcessRunStepMarineSuffixComponent,
    ApplicationProcessRunStepMarineSuffixDetailComponent,
    ApplicationProcessRunStepMarineSuffixUpdateComponent,
    ApplicationProcessRunStepMarineSuffixDeletePopupComponent,
    ApplicationProcessRunStepMarineSuffixDeleteDialogComponent,
    applicationProcessRunStepRoute,
    applicationProcessRunStepPopupRoute
} from './';

const ENTITY_STATES = [...applicationProcessRunStepRoute, ...applicationProcessRunStepPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ApplicationProcessRunStepMarineSuffixComponent,
        ApplicationProcessRunStepMarineSuffixDetailComponent,
        ApplicationProcessRunStepMarineSuffixUpdateComponent,
        ApplicationProcessRunStepMarineSuffixDeleteDialogComponent,
        ApplicationProcessRunStepMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        ApplicationProcessRunStepMarineSuffixComponent,
        ApplicationProcessRunStepMarineSuffixUpdateComponent,
        ApplicationProcessRunStepMarineSuffixDeleteDialogComponent,
        ApplicationProcessRunStepMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojApplicationProcessRunStepMarineSuffixModule {}
