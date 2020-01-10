import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    PriorityCriteriaValueMarineSuffixComponent,
    PriorityCriteriaValueMarineSuffixDetailComponent,
    PriorityCriteriaValueMarineSuffixUpdateComponent,
    PriorityCriteriaValueMarineSuffixDeletePopupComponent,
    PriorityCriteriaValueMarineSuffixDeleteDialogComponent,
    priorityCriteriaValueRoute,
    priorityCriteriaValuePopupRoute
} from './';

const ENTITY_STATES = [...priorityCriteriaValueRoute, ...priorityCriteriaValuePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PriorityCriteriaValueMarineSuffixComponent,
        PriorityCriteriaValueMarineSuffixDetailComponent,
        PriorityCriteriaValueMarineSuffixUpdateComponent,
        PriorityCriteriaValueMarineSuffixDeleteDialogComponent,
        PriorityCriteriaValueMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        PriorityCriteriaValueMarineSuffixComponent,
        PriorityCriteriaValueMarineSuffixUpdateComponent,
        PriorityCriteriaValueMarineSuffixDeleteDialogComponent,
        PriorityCriteriaValueMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojPriorityCriteriaValueMarineSuffixModule {}
