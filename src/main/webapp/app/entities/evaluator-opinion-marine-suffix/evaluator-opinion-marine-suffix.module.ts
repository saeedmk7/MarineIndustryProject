import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    EvaluatorOpinionMarineSuffixComponent,
    EvaluatorOpinionMarineSuffixDetailComponent,
    EvaluatorOpinionMarineSuffixUpdateComponent,
    EvaluatorOpinionMarineSuffixDeletePopupComponent,
    EvaluatorOpinionMarineSuffixDeleteDialogComponent,
    evaluatorOpinionRoute,
    evaluatorOpinionPopupRoute
} from './';

const ENTITY_STATES = [...evaluatorOpinionRoute, ...evaluatorOpinionPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EvaluatorOpinionMarineSuffixComponent,
        EvaluatorOpinionMarineSuffixDetailComponent,
        EvaluatorOpinionMarineSuffixUpdateComponent,
        EvaluatorOpinionMarineSuffixDeleteDialogComponent,
        EvaluatorOpinionMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        EvaluatorOpinionMarineSuffixComponent,
        EvaluatorOpinionMarineSuffixUpdateComponent,
        EvaluatorOpinionMarineSuffixDeleteDialogComponent,
        EvaluatorOpinionMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojEvaluatorOpinionMarineSuffixModule {}
