import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    NiazsanjiGroupMarineSuffixComponent,
    NiazsanjiGroupMarineSuffixDetailComponent,
    NiazsanjiGroupMarineSuffixUpdateComponent,
    NiazsanjiGroupMarineSuffixDeletePopupComponent,
    NiazsanjiGroupMarineSuffixDeleteDialogComponent,
    niazsanjiGroupRoute,
    niazsanjiGroupPopupRoute
} from './';
import {DpDatePickerModule} from "ng2-jalali-date-picker";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";

const ENTITY_STATES = [...niazsanjiGroupRoute, ...niazsanjiGroupPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, DpDatePickerModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NiazsanjiGroupMarineSuffixComponent,
        NiazsanjiGroupMarineSuffixDetailComponent,
        NiazsanjiGroupMarineSuffixUpdateComponent,
        NiazsanjiGroupMarineSuffixDeleteDialogComponent,
        NiazsanjiGroupMarineSuffixDeletePopupComponent
    ],
    providers: [ ConvertObjectDatesService ],
    entryComponents: [
        NiazsanjiGroupMarineSuffixComponent,
        NiazsanjiGroupMarineSuffixUpdateComponent,
        NiazsanjiGroupMarineSuffixDeleteDialogComponent,
        NiazsanjiGroupMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojNiazsanjiGroupMarineSuffixModule {}
