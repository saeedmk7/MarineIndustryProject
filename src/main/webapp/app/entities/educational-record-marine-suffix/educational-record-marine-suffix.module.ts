import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    EducationalRecordMarineSuffixComponent,
    EducationalRecordMarineSuffixDetailComponent,
    EducationalRecordMarineSuffixUpdateComponent,
    EducationalRecordMarineSuffixDeletePopupComponent,
    EducationalRecordMarineSuffixDeleteDialogComponent,
    educationalRecordRoute,
    educationalRecordPopupRoute
} from './';

const ENTITY_STATES = [...educationalRecordRoute, ...educationalRecordPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EducationalRecordMarineSuffixComponent,
        EducationalRecordMarineSuffixDetailComponent,
        EducationalRecordMarineSuffixUpdateComponent,
        EducationalRecordMarineSuffixDeleteDialogComponent,
        EducationalRecordMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        EducationalRecordMarineSuffixComponent,
        EducationalRecordMarineSuffixUpdateComponent,
        EducationalRecordMarineSuffixDeleteDialogComponent,
        EducationalRecordMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojEducationalRecordMarineSuffixModule {}
