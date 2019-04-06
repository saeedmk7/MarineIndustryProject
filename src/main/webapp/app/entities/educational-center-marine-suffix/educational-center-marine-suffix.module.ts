import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    EducationalCenterMarineSuffixComponent,
    EducationalCenterMarineSuffixDetailComponent,
    EducationalCenterMarineSuffixUpdateComponent,
    EducationalCenterMarineSuffixDeletePopupComponent,
    EducationalCenterMarineSuffixDeleteDialogComponent,
    educationalCenterRoute,
    educationalCenterPopupRoute
} from './';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";

const ENTITY_STATES = [...educationalCenterRoute, ...educationalCenterPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EducationalCenterMarineSuffixComponent,
        EducationalCenterMarineSuffixDetailComponent,
        EducationalCenterMarineSuffixUpdateComponent,
        EducationalCenterMarineSuffixDeleteDialogComponent,
        EducationalCenterMarineSuffixDeletePopupComponent
    ],
    providers: [ ConvertObjectDatesService ],
    entryComponents: [
        EducationalCenterMarineSuffixComponent,
        EducationalCenterMarineSuffixUpdateComponent,
        EducationalCenterMarineSuffixDeleteDialogComponent,
        EducationalCenterMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojEducationalCenterMarineSuffixModule {}
