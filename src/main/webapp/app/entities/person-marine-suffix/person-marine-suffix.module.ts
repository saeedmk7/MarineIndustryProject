import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    PersonMarineSuffixComponent,
    PersonMarineSuffixDetailComponent,
    PersonMarineSuffixUpdateComponent,
    PersonMarineSuffixDeletePopupComponent,
    PersonMarineSuffixDeleteDialogComponent,
    personRoute,
    personPopupRoute
} from './';
import {DpDatePickerModule} from 'ng2-jalali-date-picker';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";

const ENTITY_STATES = [...personRoute, ...personPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, DpDatePickerModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PersonMarineSuffixComponent,
        PersonMarineSuffixDetailComponent,
        PersonMarineSuffixUpdateComponent,
        PersonMarineSuffixDeleteDialogComponent,
        PersonMarineSuffixDeletePopupComponent
    ],
    providers: [ ConvertObjectDatesService ],
    entryComponents: [
        PersonMarineSuffixComponent,
        PersonMarineSuffixUpdateComponent,
        PersonMarineSuffixDeleteDialogComponent,
        PersonMarineSuffixDeletePopupComponent

    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojPersonMarineSuffixModule {}
