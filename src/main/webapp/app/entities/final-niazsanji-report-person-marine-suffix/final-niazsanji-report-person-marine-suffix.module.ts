import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    FinalNiazsanjiReportPersonMarineSuffixComponent,
    FinalNiazsanjiReportPersonMarineSuffixDetailComponent,
    FinalNiazsanjiReportPersonMarineSuffixUpdateComponent,
    FinalNiazsanjiReportPersonMarineSuffixDeletePopupComponent,
    FinalNiazsanjiReportPersonMarineSuffixDeleteDialogComponent,
    finalNiazsanjiReportPersonRoute,
    finalNiazsanjiReportPersonPopupRoute
} from './';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

const ENTITY_STATES = [...finalNiazsanjiReportPersonRoute, ...finalNiazsanjiReportPersonPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, BrowserAnimationsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FinalNiazsanjiReportPersonMarineSuffixComponent,
        FinalNiazsanjiReportPersonMarineSuffixDetailComponent,
        FinalNiazsanjiReportPersonMarineSuffixUpdateComponent,
        FinalNiazsanjiReportPersonMarineSuffixDeleteDialogComponent,
        FinalNiazsanjiReportPersonMarineSuffixDeletePopupComponent
    ],
    providers: [ ConvertObjectDatesService ],
    entryComponents: [
        FinalNiazsanjiReportPersonMarineSuffixComponent,
        FinalNiazsanjiReportPersonMarineSuffixUpdateComponent,
        FinalNiazsanjiReportPersonMarineSuffixDeleteDialogComponent,
        FinalNiazsanjiReportPersonMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojFinalNiazsanjiReportPersonMarineSuffixModule {}
