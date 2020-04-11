import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    TeacherGradeScoreMarineSuffixComponent,
    TeacherGradeScoreMarineSuffixDetailComponent,
    TeacherGradeScoreMarineSuffixUpdateComponent,
    TeacherGradeScoreMarineSuffixDeletePopupComponent,
    TeacherGradeScoreMarineSuffixDeleteDialogComponent,
    teacherGradeScoreRoute,
    teacherGradeScorePopupRoute
} from './';

const ENTITY_STATES = [...teacherGradeScoreRoute, ...teacherGradeScorePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TeacherGradeScoreMarineSuffixComponent,
        TeacherGradeScoreMarineSuffixDetailComponent,
        TeacherGradeScoreMarineSuffixUpdateComponent,
        TeacherGradeScoreMarineSuffixDeleteDialogComponent,
        TeacherGradeScoreMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        TeacherGradeScoreMarineSuffixComponent,
        TeacherGradeScoreMarineSuffixUpdateComponent,
        TeacherGradeScoreMarineSuffixDeleteDialogComponent,
        TeacherGradeScoreMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojTeacherGradeScoreMarineSuffixModule {}
