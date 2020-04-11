import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    EducationalCenterServiceMarineSuffixComponent,
    EducationalCenterServiceMarineSuffixDetailComponent,
    EducationalCenterServiceMarineSuffixUpdateComponent,
    EducationalCenterServiceMarineSuffixDeletePopupComponent,
    EducationalCenterServiceMarineSuffixDeleteDialogComponent,
    educationalCenterServiceRoute,
    educationalCenterServicePopupRoute
} from './';

const ENTITY_STATES = [...educationalCenterServiceRoute, ...educationalCenterServicePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EducationalCenterServiceMarineSuffixComponent,
        EducationalCenterServiceMarineSuffixDetailComponent,
        EducationalCenterServiceMarineSuffixUpdateComponent,
        EducationalCenterServiceMarineSuffixDeleteDialogComponent,
        EducationalCenterServiceMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        EducationalCenterServiceMarineSuffixComponent,
        EducationalCenterServiceMarineSuffixUpdateComponent,
        EducationalCenterServiceMarineSuffixDeleteDialogComponent,
        EducationalCenterServiceMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojEducationalCenterServiceMarineSuffixModule {}
