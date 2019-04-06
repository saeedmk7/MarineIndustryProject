import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    CriterionMarineSuffixComponent,
    CriterionMarineSuffixDetailComponent,
    CriterionMarineSuffixUpdateComponent,
    CriterionMarineSuffixDeletePopupComponent,
    CriterionMarineSuffixDeleteDialogComponent,
    criterionRoute,
    criterionPopupRoute
} from './';

const ENTITY_STATES = [...criterionRoute, ...criterionPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CriterionMarineSuffixComponent,
        CriterionMarineSuffixDetailComponent,
        CriterionMarineSuffixUpdateComponent,
        CriterionMarineSuffixDeleteDialogComponent,
        CriterionMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        CriterionMarineSuffixComponent,
        CriterionMarineSuffixUpdateComponent,
        CriterionMarineSuffixDeleteDialogComponent,
        CriterionMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojCriterionMarineSuffixModule {}
