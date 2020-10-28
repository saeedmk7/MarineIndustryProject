import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    EducationalCenterGroupMarineSuffixComponent,
    EducationalCenterGroupMarineSuffixDetailComponent,
    EducationalCenterGroupMarineSuffixUpdateComponent,
    EducationalCenterGroupMarineSuffixDeletePopupComponent,
    EducationalCenterGroupMarineSuffixDeleteDialogComponent,
    educationalCenterGroupRoute,
    educationalCenterGroupPopupRoute
} from './';

const ENTITY_STATES = [...educationalCenterGroupRoute, ...educationalCenterGroupPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EducationalCenterGroupMarineSuffixComponent,
        EducationalCenterGroupMarineSuffixDetailComponent,
        EducationalCenterGroupMarineSuffixUpdateComponent,
        EducationalCenterGroupMarineSuffixDeleteDialogComponent,
        EducationalCenterGroupMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        EducationalCenterGroupMarineSuffixComponent,
        EducationalCenterGroupMarineSuffixUpdateComponent,
        EducationalCenterGroupMarineSuffixDeleteDialogComponent,
        EducationalCenterGroupMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojEducationalCenterGroupMarineSuffixModule {}
