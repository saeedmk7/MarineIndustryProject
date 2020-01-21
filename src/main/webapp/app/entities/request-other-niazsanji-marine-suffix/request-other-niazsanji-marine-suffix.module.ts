import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    RequestOtherNiazsanjiMarineSuffixComponent,
    RequestOtherNiazsanjiMarineSuffixDetailComponent,
    RequestOtherNiazsanjiMarineSuffixUpdateComponent,
    RequestOtherNiazsanjiMarineSuffixDeletePopupComponent,
    RequestOtherNiazsanjiMarineSuffixDeleteDialogComponent,
    requestOtherNiazsanjiRoute,
    requestOtherNiazsanjiPopupRoute,
    RequestOtherNiazsanjiMarineSuffixCommentDialogComponent,
    RequestOtherNiazsanjiMarineSuffixCommentPopupComponent
} from './';
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";

const ENTITY_STATES = [...requestOtherNiazsanjiRoute, ...requestOtherNiazsanjiPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RequestOtherNiazsanjiMarineSuffixComponent,
        RequestOtherNiazsanjiMarineSuffixDetailComponent,
        RequestOtherNiazsanjiMarineSuffixUpdateComponent,
        RequestOtherNiazsanjiMarineSuffixDeleteDialogComponent,
        RequestOtherNiazsanjiMarineSuffixDeletePopupComponent,
        RequestOtherNiazsanjiMarineSuffixCommentDialogComponent,
        RequestOtherNiazsanjiMarineSuffixCommentPopupComponent
    ],
    entryComponents: [
        RequestOtherNiazsanjiMarineSuffixComponent,
        RequestOtherNiazsanjiMarineSuffixUpdateComponent,
        RequestOtherNiazsanjiMarineSuffixDeleteDialogComponent,
        RequestOtherNiazsanjiMarineSuffixDeletePopupComponent,
        RequestOtherNiazsanjiMarineSuffixCommentDialogComponent,
        RequestOtherNiazsanjiMarineSuffixCommentPopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojRequestOtherNiazsanjiMarineSuffixModule {}
