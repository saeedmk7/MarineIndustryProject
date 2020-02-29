import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    AssessmentMethodMarineSuffixComponent,
    AssessmentMethodMarineSuffixDetailComponent,
    AssessmentMethodMarineSuffixUpdateComponent,
    AssessmentMethodMarineSuffixDeletePopupComponent,
    AssessmentMethodMarineSuffixDeleteDialogComponent,
    assessmentMethodRoute,
    assessmentMethodPopupRoute
} from './';

const ENTITY_STATES = [...assessmentMethodRoute, ...assessmentMethodPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AssessmentMethodMarineSuffixComponent,
        AssessmentMethodMarineSuffixDetailComponent,
        AssessmentMethodMarineSuffixUpdateComponent,
        AssessmentMethodMarineSuffixDeleteDialogComponent,
        AssessmentMethodMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        AssessmentMethodMarineSuffixComponent,
        AssessmentMethodMarineSuffixUpdateComponent,
        AssessmentMethodMarineSuffixDeleteDialogComponent,
        AssessmentMethodMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojAssessmentMethodMarineSuffixModule {}
