import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    EducationalCenterGradeScoreMarineSuffixComponent,
    EducationalCenterGradeScoreMarineSuffixDetailComponent,
    EducationalCenterGradeScoreMarineSuffixUpdateComponent,
    EducationalCenterGradeScoreMarineSuffixDeletePopupComponent,
    EducationalCenterGradeScoreMarineSuffixDeleteDialogComponent,
    educationalCenterGradeScoreRoute,
    educationalCenterGradeScorePopupRoute
} from './';

const ENTITY_STATES = [...educationalCenterGradeScoreRoute, ...educationalCenterGradeScorePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EducationalCenterGradeScoreMarineSuffixComponent,
        EducationalCenterGradeScoreMarineSuffixDetailComponent,
        EducationalCenterGradeScoreMarineSuffixUpdateComponent,
        EducationalCenterGradeScoreMarineSuffixDeleteDialogComponent,
        EducationalCenterGradeScoreMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        EducationalCenterGradeScoreMarineSuffixComponent,
        EducationalCenterGradeScoreMarineSuffixUpdateComponent,
        EducationalCenterGradeScoreMarineSuffixDeleteDialogComponent,
        EducationalCenterGradeScoreMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojEducationalCenterGradeScoreMarineSuffixModule {}
