import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    PriorityCriteriaMarineSuffixComponent,
    PriorityCriteriaMarineSuffixDetailComponent,
    PriorityCriteriaMarineSuffixUpdateComponent,
    PriorityCriteriaMarineSuffixDeletePopupComponent,
    PriorityCriteriaMarineSuffixDeleteDialogComponent,
    priorityCriteriaRoute,
    priorityCriteriaPopupRoute
} from './';

const ENTITY_STATES = [...priorityCriteriaRoute, ...priorityCriteriaPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PriorityCriteriaMarineSuffixComponent,
        PriorityCriteriaMarineSuffixDetailComponent,
        PriorityCriteriaMarineSuffixUpdateComponent,
        PriorityCriteriaMarineSuffixDeleteDialogComponent,
        PriorityCriteriaMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        PriorityCriteriaMarineSuffixComponent,
        PriorityCriteriaMarineSuffixUpdateComponent,
        PriorityCriteriaMarineSuffixDeleteDialogComponent,
        PriorityCriteriaMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojPriorityCriteriaMarineSuffixModule {}
