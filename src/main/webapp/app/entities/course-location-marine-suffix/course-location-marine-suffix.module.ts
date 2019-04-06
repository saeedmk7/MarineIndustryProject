import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    CourseLocationMarineSuffixComponent,
    CourseLocationMarineSuffixDetailComponent,
    CourseLocationMarineSuffixUpdateComponent,
    CourseLocationMarineSuffixDeletePopupComponent,
    CourseLocationMarineSuffixDeleteDialogComponent,
    courseLocationRoute,
    courseLocationPopupRoute
} from './';

const ENTITY_STATES = [...courseLocationRoute, ...courseLocationPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CourseLocationMarineSuffixComponent,
        CourseLocationMarineSuffixDetailComponent,
        CourseLocationMarineSuffixUpdateComponent,
        CourseLocationMarineSuffixDeleteDialogComponent,
        CourseLocationMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        CourseLocationMarineSuffixComponent,
        CourseLocationMarineSuffixUpdateComponent,
        CourseLocationMarineSuffixDeleteDialogComponent,
        CourseLocationMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojCourseLocationMarineSuffixModule {}
