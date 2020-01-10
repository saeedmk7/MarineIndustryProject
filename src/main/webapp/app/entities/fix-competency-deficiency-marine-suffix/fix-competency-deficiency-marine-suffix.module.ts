import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    FixCompetencyDeficiencyMarineSuffixComponent,
    FixCompetencyDeficiencyMarineSuffixDetailComponent,
    FixCompetencyDeficiencyMarineSuffixUpdateComponent,
    FixCompetencyDeficiencyMarineSuffixDeletePopupComponent,
    FixCompetencyDeficiencyMarineSuffixDeleteDialogComponent,
    fixCompetencyDeficiencyRoute,
    fixCompetencyDeficiencyPopupRoute
} from './';

const ENTITY_STATES = [...fixCompetencyDeficiencyRoute, ...fixCompetencyDeficiencyPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FixCompetencyDeficiencyMarineSuffixComponent,
        FixCompetencyDeficiencyMarineSuffixDetailComponent,
        FixCompetencyDeficiencyMarineSuffixUpdateComponent,
        FixCompetencyDeficiencyMarineSuffixDeleteDialogComponent,
        FixCompetencyDeficiencyMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        FixCompetencyDeficiencyMarineSuffixComponent,
        FixCompetencyDeficiencyMarineSuffixUpdateComponent,
        FixCompetencyDeficiencyMarineSuffixDeleteDialogComponent,
        FixCompetencyDeficiencyMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojFixCompetencyDeficiencyMarineSuffixModule {}
