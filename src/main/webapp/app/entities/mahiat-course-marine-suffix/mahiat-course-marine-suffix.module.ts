import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    MahiatCourseMarineSuffixComponent,
    MahiatCourseMarineSuffixDetailComponent,
    MahiatCourseMarineSuffixUpdateComponent,
    MahiatCourseMarineSuffixDeletePopupComponent,
    MahiatCourseMarineSuffixDeleteDialogComponent,
    mahiatCourseRoute,
    mahiatCoursePopupRoute
} from './';

const ENTITY_STATES = [...mahiatCourseRoute, ...mahiatCoursePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MahiatCourseMarineSuffixComponent,
        MahiatCourseMarineSuffixDetailComponent,
        MahiatCourseMarineSuffixUpdateComponent,
        MahiatCourseMarineSuffixDeleteDialogComponent,
        MahiatCourseMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        MahiatCourseMarineSuffixComponent,
        MahiatCourseMarineSuffixUpdateComponent,
        MahiatCourseMarineSuffixDeleteDialogComponent,
        MahiatCourseMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojMahiatCourseMarineSuffixModule {}
