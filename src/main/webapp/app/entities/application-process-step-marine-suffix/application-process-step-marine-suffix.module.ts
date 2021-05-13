import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    ApplicationProcessStepMarineSuffixComponent,
    ApplicationProcessStepMarineSuffixDetailComponent,
    ApplicationProcessStepMarineSuffixUpdateComponent,
    ApplicationProcessStepMarineSuffixDeletePopupComponent,
    ApplicationProcessStepMarineSuffixDeleteDialogComponent,
    applicationProcessStepRoute,
    applicationProcessStepPopupRoute
} from './';

const ENTITY_STATES = [...applicationProcessStepRoute, ...applicationProcessStepPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ApplicationProcessStepMarineSuffixComponent,
        ApplicationProcessStepMarineSuffixDetailComponent,
        ApplicationProcessStepMarineSuffixUpdateComponent,
        ApplicationProcessStepMarineSuffixDeleteDialogComponent,
        ApplicationProcessStepMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        ApplicationProcessStepMarineSuffixComponent,
        ApplicationProcessStepMarineSuffixUpdateComponent,
        ApplicationProcessStepMarineSuffixDeleteDialogComponent,
        ApplicationProcessStepMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojApplicationProcessStepMarineSuffixModule {}
