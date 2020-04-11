import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    EducationalCenterCriteriaMarineSuffixComponent,
    EducationalCenterCriteriaMarineSuffixDetailComponent,
    EducationalCenterCriteriaMarineSuffixUpdateComponent,
    EducationalCenterCriteriaMarineSuffixDeletePopupComponent,
    EducationalCenterCriteriaMarineSuffixDeleteDialogComponent,
    educationalCenterCriteriaRoute,
    educationalCenterCriteriaPopupRoute
} from './';

const ENTITY_STATES = [...educationalCenterCriteriaRoute, ...educationalCenterCriteriaPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EducationalCenterCriteriaMarineSuffixComponent,
        EducationalCenterCriteriaMarineSuffixDetailComponent,
        EducationalCenterCriteriaMarineSuffixUpdateComponent,
        EducationalCenterCriteriaMarineSuffixDeleteDialogComponent,
        EducationalCenterCriteriaMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        EducationalCenterCriteriaMarineSuffixComponent,
        EducationalCenterCriteriaMarineSuffixUpdateComponent,
        EducationalCenterCriteriaMarineSuffixDeleteDialogComponent,
        EducationalCenterCriteriaMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojEducationalCenterCriteriaMarineSuffixModule {}
