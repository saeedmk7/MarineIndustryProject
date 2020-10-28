import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    TeacherCriteriaGroupMarineSuffixComponent,
    TeacherCriteriaGroupMarineSuffixDetailComponent,
    TeacherCriteriaGroupMarineSuffixUpdateComponent,
    TeacherCriteriaGroupMarineSuffixDeletePopupComponent,
    TeacherCriteriaGroupMarineSuffixDeleteDialogComponent,
    teacherCriteriaGroupRoute,
    teacherCriteriaGroupPopupRoute
} from './';

const ENTITY_STATES = [...teacherCriteriaGroupRoute, ...teacherCriteriaGroupPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TeacherCriteriaGroupMarineSuffixComponent,
        TeacherCriteriaGroupMarineSuffixDetailComponent,
        TeacherCriteriaGroupMarineSuffixUpdateComponent,
        TeacherCriteriaGroupMarineSuffixDeleteDialogComponent,
        TeacherCriteriaGroupMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        TeacherCriteriaGroupMarineSuffixComponent,
        TeacherCriteriaGroupMarineSuffixUpdateComponent,
        TeacherCriteriaGroupMarineSuffixDeleteDialogComponent,
        TeacherCriteriaGroupMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojTeacherCriteriaGroupMarineSuffixModule {}
