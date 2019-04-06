import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    TeachingApproachMarineSuffixComponent,
    TeachingApproachMarineSuffixDetailComponent,
    TeachingApproachMarineSuffixUpdateComponent,
    TeachingApproachMarineSuffixDeletePopupComponent,
    TeachingApproachMarineSuffixDeleteDialogComponent,
    teachingApproachRoute,
    teachingApproachPopupRoute
} from './';

const ENTITY_STATES = [...teachingApproachRoute, ...teachingApproachPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TeachingApproachMarineSuffixComponent,
        TeachingApproachMarineSuffixDetailComponent,
        TeachingApproachMarineSuffixUpdateComponent,
        TeachingApproachMarineSuffixDeleteDialogComponent,
        TeachingApproachMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        TeachingApproachMarineSuffixComponent,
        TeachingApproachMarineSuffixUpdateComponent,
        TeachingApproachMarineSuffixDeleteDialogComponent,
        TeachingApproachMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojTeachingApproachMarineSuffixModule {}
