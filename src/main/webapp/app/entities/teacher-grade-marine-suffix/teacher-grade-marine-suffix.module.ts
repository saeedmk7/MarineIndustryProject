import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    TeacherGradeMarineSuffixComponent,
    TeacherGradeMarineSuffixDetailComponent,
    TeacherGradeMarineSuffixUpdateComponent,
    TeacherGradeMarineSuffixDeletePopupComponent,
    TeacherGradeMarineSuffixDeleteDialogComponent,
    teacherGradeRoute,
    teacherGradePopupRoute
} from './';
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {InputsModule} from "@progress/kendo-angular-inputs";

const ENTITY_STATES = [...teacherGradeRoute, ...teacherGradePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, BrowserAnimationsModule, InputsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TeacherGradeMarineSuffixComponent,
        TeacherGradeMarineSuffixDetailComponent,
        TeacherGradeMarineSuffixUpdateComponent,
        TeacherGradeMarineSuffixDeleteDialogComponent,
        TeacherGradeMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        TeacherGradeMarineSuffixComponent,
        TeacherGradeMarineSuffixUpdateComponent,
        TeacherGradeMarineSuffixDeleteDialogComponent,
        TeacherGradeMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojTeacherGradeMarineSuffixModule {}
