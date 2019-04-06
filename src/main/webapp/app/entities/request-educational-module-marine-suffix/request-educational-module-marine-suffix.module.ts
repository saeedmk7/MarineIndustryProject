import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    RequestEducationalModuleMarineSuffixComponent,
    RequestEducationalModuleMarineSuffixDetailComponent,
    RequestEducationalModuleMarineSuffixUpdateComponent,
    RequestEducationalModuleMarineSuffixDeletePopupComponent,
    RequestEducationalModuleMarineSuffixDeleteDialogComponent,
    requestEducationalModuleRoute,
    requestEducationalModulePopupRoute
} from './';
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";
import {DpDatePickerModule} from "ng2-jalali-date-picker";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

const ENTITY_STATES = [...requestEducationalModuleRoute, ...requestEducationalModulePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, DpDatePickerModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RequestEducationalModuleMarineSuffixComponent,
        RequestEducationalModuleMarineSuffixDetailComponent,
        RequestEducationalModuleMarineSuffixUpdateComponent,
        RequestEducationalModuleMarineSuffixDeleteDialogComponent,
        RequestEducationalModuleMarineSuffixDeletePopupComponent
    ],
    providers: [ ConvertObjectDatesService ],
    entryComponents: [
        RequestEducationalModuleMarineSuffixComponent,
        RequestEducationalModuleMarineSuffixUpdateComponent,
        RequestEducationalModuleMarineSuffixDeleteDialogComponent,
        RequestEducationalModuleMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojRequestEducationalModuleMarineSuffixModule {}
