import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    FinalNiazsanjiReportMarineSuffixComponent,
    FinalNiazsanjiReportMarineSuffixDetailComponent,
    FinalNiazsanjiReportMarineSuffixUpdateComponent,
    FinalNiazsanjiReportMarineSuffixDeletePopupComponent,
    FinalNiazsanjiReportMarineSuffixDeleteDialogComponent,
    finalNiazsanjiReportRoute,
    finalNiazsanjiReportPopupRoute,
    FinalNiazsanjiReportMarineSuffixDetailPeopleComponent
} from './';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ExcelModule, GridModule} from "@progress/kendo-angular-grid";

const ENTITY_STATES = [...finalNiazsanjiReportRoute, ...finalNiazsanjiReportPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, BrowserAnimationsModule, GridModule , ExcelModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FinalNiazsanjiReportMarineSuffixComponent,
        FinalNiazsanjiReportMarineSuffixDetailPeopleComponent,
        FinalNiazsanjiReportMarineSuffixDetailComponent,
        FinalNiazsanjiReportMarineSuffixUpdateComponent,
        FinalNiazsanjiReportMarineSuffixDeleteDialogComponent,
        FinalNiazsanjiReportMarineSuffixDeletePopupComponent
    ],
    providers: [ ConvertObjectDatesService ],
    entryComponents: [
        FinalNiazsanjiReportMarineSuffixComponent,
        FinalNiazsanjiReportMarineSuffixUpdateComponent,
        FinalNiazsanjiReportMarineSuffixDeleteDialogComponent,
        FinalNiazsanjiReportMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojFinalNiazsanjiReportMarineSuffixModule {}
