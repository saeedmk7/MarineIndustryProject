import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    RunPhaseMarineSuffixComponent,
    RunPhaseMarineSuffixDetailComponent,
    RunPhaseMarineSuffixUpdateComponent,
    RunPhaseMarineSuffixDeletePopupComponent,
    RunPhaseMarineSuffixDeleteDialogComponent,
    runPhaseRoute,
    runPhasePopupRoute
} from './';

const ENTITY_STATES = [...runPhaseRoute, ...runPhasePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RunPhaseMarineSuffixComponent,
        RunPhaseMarineSuffixDetailComponent,
        RunPhaseMarineSuffixUpdateComponent,
        RunPhaseMarineSuffixDeleteDialogComponent,
        RunPhaseMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        RunPhaseMarineSuffixComponent,
        RunPhaseMarineSuffixUpdateComponent,
        RunPhaseMarineSuffixDeleteDialogComponent,
        RunPhaseMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojRunPhaseMarineSuffixModule {}
