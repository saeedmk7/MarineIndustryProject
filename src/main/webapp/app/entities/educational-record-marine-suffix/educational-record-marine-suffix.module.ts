import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    EducationalRecordMarineSuffixComponent,
    EducationalRecordMarineSuffixDetailComponent,
    EducationalRecordMarineSuffixUpdateComponent,
    EducationalRecordMarineSuffixDeletePopupComponent,
    EducationalRecordMarineSuffixDeleteDialogComponent,
    educationalRecordRoute,
    educationalRecordPopupRoute
} from './';
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {InputsModule} from "@progress/kendo-angular-inputs";

const ENTITY_STATES = [...educationalRecordRoute, ...educationalRecordPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, BrowserAnimationsModule, InputsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EducationalRecordMarineSuffixComponent,
        EducationalRecordMarineSuffixDetailComponent,
        EducationalRecordMarineSuffixUpdateComponent,
        EducationalRecordMarineSuffixDeleteDialogComponent,
        EducationalRecordMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        EducationalRecordMarineSuffixComponent,
        EducationalRecordMarineSuffixUpdateComponent,
        EducationalRecordMarineSuffixDeleteDialogComponent,
        EducationalRecordMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojEducationalRecordMarineSuffixModule {}
