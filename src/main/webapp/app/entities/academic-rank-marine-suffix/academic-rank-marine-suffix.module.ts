import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    AcademicRankMarineSuffixComponent,
    AcademicRankMarineSuffixDetailComponent,
    AcademicRankMarineSuffixUpdateComponent,
    AcademicRankMarineSuffixDeletePopupComponent,
    AcademicRankMarineSuffixDeleteDialogComponent,
    academicRankRoute,
    academicRankPopupRoute
} from './';

const ENTITY_STATES = [...academicRankRoute, ...academicRankPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AcademicRankMarineSuffixComponent,
        AcademicRankMarineSuffixDetailComponent,
        AcademicRankMarineSuffixUpdateComponent,
        AcademicRankMarineSuffixDeleteDialogComponent,
        AcademicRankMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        AcademicRankMarineSuffixComponent,
        AcademicRankMarineSuffixUpdateComponent,
        AcademicRankMarineSuffixDeleteDialogComponent,
        AcademicRankMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojAcademicRankMarineSuffixModule {}
