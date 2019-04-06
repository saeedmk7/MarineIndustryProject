import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    EvaluationMethodMarineSuffixComponent,
    EvaluationMethodMarineSuffixDetailComponent,
    EvaluationMethodMarineSuffixUpdateComponent,
    EvaluationMethodMarineSuffixDeletePopupComponent,
    EvaluationMethodMarineSuffixDeleteDialogComponent,
    evaluationMethodRoute,
    evaluationMethodPopupRoute
} from './';

const ENTITY_STATES = [...evaluationMethodRoute, ...evaluationMethodPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EvaluationMethodMarineSuffixComponent,
        EvaluationMethodMarineSuffixDetailComponent,
        EvaluationMethodMarineSuffixUpdateComponent,
        EvaluationMethodMarineSuffixDeleteDialogComponent,
        EvaluationMethodMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        EvaluationMethodMarineSuffixComponent,
        EvaluationMethodMarineSuffixUpdateComponent,
        EvaluationMethodMarineSuffixDeleteDialogComponent,
        EvaluationMethodMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojEvaluationMethodMarineSuffixModule {}
