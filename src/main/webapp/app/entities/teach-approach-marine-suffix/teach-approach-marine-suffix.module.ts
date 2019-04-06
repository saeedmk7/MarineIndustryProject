import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    TeachApproachMarineSuffixComponent,
    TeachApproachMarineSuffixDetailComponent,
    TeachApproachMarineSuffixUpdateComponent,
    TeachApproachMarineSuffixDeletePopupComponent,
    TeachApproachMarineSuffixDeleteDialogComponent,
    teachApproachRoute,
    teachApproachPopupRoute
} from './';

const ENTITY_STATES = [...teachApproachRoute, ...teachApproachPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TeachApproachMarineSuffixComponent,
        TeachApproachMarineSuffixDetailComponent,
        TeachApproachMarineSuffixUpdateComponent,
        TeachApproachMarineSuffixDeleteDialogComponent,
        TeachApproachMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        TeachApproachMarineSuffixComponent,
        TeachApproachMarineSuffixUpdateComponent,
        TeachApproachMarineSuffixDeleteDialogComponent,
        TeachApproachMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojTeachApproachMarineSuffixModule {}
