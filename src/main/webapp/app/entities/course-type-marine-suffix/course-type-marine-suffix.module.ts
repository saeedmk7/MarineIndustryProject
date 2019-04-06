import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    CourseTypeMarineSuffixComponent,
    CourseTypeMarineSuffixDetailComponent,
    CourseTypeMarineSuffixUpdateComponent,
    CourseTypeMarineSuffixDeletePopupComponent,
    CourseTypeMarineSuffixDeleteDialogComponent,
    courseTypeRoute,
    courseTypePopupRoute
} from './';

const ENTITY_STATES = [...courseTypeRoute, ...courseTypePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CourseTypeMarineSuffixComponent,
        CourseTypeMarineSuffixDetailComponent,
        CourseTypeMarineSuffixUpdateComponent,
        CourseTypeMarineSuffixDeleteDialogComponent,
        CourseTypeMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        CourseTypeMarineSuffixComponent,
        CourseTypeMarineSuffixUpdateComponent,
        CourseTypeMarineSuffixDeleteDialogComponent,
        CourseTypeMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojCourseTypeMarineSuffixModule {}
