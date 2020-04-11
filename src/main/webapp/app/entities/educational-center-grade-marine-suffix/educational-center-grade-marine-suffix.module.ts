import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    EducationalCenterGradeMarineSuffixComponent,
    EducationalCenterGradeMarineSuffixDetailComponent,
    EducationalCenterGradeMarineSuffixUpdateComponent,
    EducationalCenterGradeMarineSuffixDeletePopupComponent,
    EducationalCenterGradeMarineSuffixDeleteDialogComponent,
    educationalCenterGradeRoute,
    educationalCenterGradePopupRoute
} from './';
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {InputsModule} from "@progress/kendo-angular-inputs";

const ENTITY_STATES = [...educationalCenterGradeRoute, ...educationalCenterGradePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, BrowserAnimationsModule, InputsModule,  RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EducationalCenterGradeMarineSuffixComponent,
        EducationalCenterGradeMarineSuffixDetailComponent,
        EducationalCenterGradeMarineSuffixUpdateComponent,
        EducationalCenterGradeMarineSuffixDeleteDialogComponent,
        EducationalCenterGradeMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        EducationalCenterGradeMarineSuffixComponent,
        EducationalCenterGradeMarineSuffixUpdateComponent,
        EducationalCenterGradeMarineSuffixDeleteDialogComponent,
        EducationalCenterGradeMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojEducationalCenterGradeMarineSuffixModule {}
