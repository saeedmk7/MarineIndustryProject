import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    EducationalModuleMarineSuffixComponent,
    EducationalModuleMarineSuffixDetailComponent,
    EducationalModuleMarineSuffixUpdateComponent,
    EducationalModuleMarineSuffixDeletePopupComponent,
    EducationalModuleMarineSuffixDeleteDialogComponent,
    educationalModuleRoute,
    educationalModulePopupRoute
} from './';
import {DpDatePickerModule} from "ng2-jalali-date-picker";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";

const ENTITY_STATES = [...educationalModuleRoute, ...educationalModulePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, DpDatePickerModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EducationalModuleMarineSuffixComponent,
        EducationalModuleMarineSuffixDetailComponent,
        EducationalModuleMarineSuffixUpdateComponent,
        EducationalModuleMarineSuffixDeleteDialogComponent,
        EducationalModuleMarineSuffixDeletePopupComponent
    ],
    providers: [ ConvertObjectDatesService ],
    entryComponents: [
        EducationalModuleMarineSuffixComponent,
        EducationalModuleMarineSuffixUpdateComponent,
        EducationalModuleMarineSuffixDeleteDialogComponent,
        EducationalModuleMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojEducationalModuleMarineSuffixModule {}
