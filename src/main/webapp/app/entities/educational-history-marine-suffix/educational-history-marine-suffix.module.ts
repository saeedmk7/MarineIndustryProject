import { NgModule, CUSTOM_ELEMENTS_SCHEMA, LOCALE_ID } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    EducationalHistoryMarineSuffixComponent,
    EducationalHistoryMarineSuffixDetailComponent,
    EducationalHistoryMarineSuffixUpdateComponent,
    EducationalHistoryMarineSuffixDeletePopupComponent,
    EducationalHistoryMarineSuffixDeleteDialogComponent,
    educationalHistoryRoute,
    educationalHistoryPopupRoute
} from './';
import {InputsModule} from "@progress/kendo-angular-inputs";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {
    EducationalHistoryMarineSuffixCommentDialogComponent,
    EducationalHistoryMarineSuffixCommentPopupComponent
} from "app/entities/educational-history-marine-suffix/educational-history-marine-suffix-comment-dialog.component";
import {NgSelectModule} from "@ng-select/ng-select";

const ENTITY_STATES = [...educationalHistoryRoute, ...educationalHistoryPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, BrowserAnimationsModule, InputsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EducationalHistoryMarineSuffixComponent,
        EducationalHistoryMarineSuffixDetailComponent,
        EducationalHistoryMarineSuffixUpdateComponent,
        EducationalHistoryMarineSuffixDeleteDialogComponent,
        EducationalHistoryMarineSuffixDeletePopupComponent,
        EducationalHistoryMarineSuffixCommentDialogComponent,
        EducationalHistoryMarineSuffixCommentPopupComponent
    ],
    entryComponents: [
        EducationalHistoryMarineSuffixComponent,
        EducationalHistoryMarineSuffixUpdateComponent,
        EducationalHistoryMarineSuffixDeleteDialogComponent,
        EducationalHistoryMarineSuffixDeletePopupComponent,
        EducationalHistoryMarineSuffixCommentDialogComponent,
        EducationalHistoryMarineSuffixCommentPopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    providers: [ { provide: LOCALE_ID, useValue: 'en' } ]
})
export class MarineindustryprojEducationalHistoryMarineSuffixModule {}
