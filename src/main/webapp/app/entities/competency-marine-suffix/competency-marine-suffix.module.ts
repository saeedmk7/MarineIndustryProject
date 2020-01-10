import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    CompetencyMarineSuffixComponent,
    CompetencyMarineSuffixDetailComponent,
    CompetencyMarineSuffixUpdateComponent,
    CompetencyMarineSuffixDeletePopupComponent,
    CompetencyMarineSuffixDeleteDialogComponent,
    competencyRoute,
    competencyPopupRoute
} from './';

const ENTITY_STATES = [...competencyRoute, ...competencyPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CompetencyMarineSuffixComponent,
        CompetencyMarineSuffixDetailComponent,
        CompetencyMarineSuffixUpdateComponent,
        CompetencyMarineSuffixDeleteDialogComponent,
        CompetencyMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        CompetencyMarineSuffixComponent,
        CompetencyMarineSuffixUpdateComponent,
        CompetencyMarineSuffixDeleteDialogComponent,
        CompetencyMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojCompetencyMarineSuffixModule {}
