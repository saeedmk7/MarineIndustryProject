import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    TeacherMarineSuffixComponent,
    TeacherMarineSuffixDetailComponent,
    TeacherMarineSuffixUpdateComponent,
    TeacherMarineSuffixDeletePopupComponent,
    TeacherMarineSuffixDeleteDialogComponent,
    teacherRoute,
    teacherPopupRoute
} from './';
import {DpDatePickerModule} from "ng2-jalali-date-picker";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";

const ENTITY_STATES = [...teacherRoute, ...teacherPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, DpDatePickerModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TeacherMarineSuffixComponent,
        TeacherMarineSuffixDetailComponent,
        TeacherMarineSuffixUpdateComponent,
        TeacherMarineSuffixDeleteDialogComponent,
        TeacherMarineSuffixDeletePopupComponent
    ],
    providers: [ ConvertObjectDatesService ],
    entryComponents: [
        TeacherMarineSuffixComponent,
        TeacherMarineSuffixUpdateComponent,
        TeacherMarineSuffixDeleteDialogComponent,
        TeacherMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojTeacherMarineSuffixModule {}
