import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    TeacherCriteriaMarineSuffixComponent,
    TeacherCriteriaMarineSuffixDetailComponent,
    TeacherCriteriaMarineSuffixUpdateComponent,
    TeacherCriteriaMarineSuffixDeletePopupComponent,
    TeacherCriteriaMarineSuffixDeleteDialogComponent,
    teacherCriteriaRoute,
    teacherCriteriaPopupRoute
} from './';

const ENTITY_STATES = [...teacherCriteriaRoute, ...teacherCriteriaPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TeacherCriteriaMarineSuffixComponent,
        TeacherCriteriaMarineSuffixDetailComponent,
        TeacherCriteriaMarineSuffixUpdateComponent,
        TeacherCriteriaMarineSuffixDeleteDialogComponent,
        TeacherCriteriaMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        TeacherCriteriaMarineSuffixComponent,
        TeacherCriteriaMarineSuffixUpdateComponent,
        TeacherCriteriaMarineSuffixDeleteDialogComponent,
        TeacherCriteriaMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojTeacherCriteriaMarineSuffixModule {}
