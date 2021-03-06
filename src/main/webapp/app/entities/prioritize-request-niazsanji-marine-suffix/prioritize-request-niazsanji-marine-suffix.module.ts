import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    PrioritizeRequestNiazsanjiMarineSuffixComponent,
    PrioritizeRequestNiazsanjiMarineSuffixDetailComponent,
    PrioritizeRequestNiazsanjiMarineSuffixUpdateComponent,
    PrioritizeRequestNiazsanjiMarineSuffixDeletePopupComponent,
    PrioritizeRequestNiazsanjiMarineSuffixDeleteDialogComponent,
    prioritizeRequestNiazsanjiRoute,
    prioritizeRequestNiazsanjiPopupRoute,
    PrioritizeRequestNiazsanjiMarineSuffixCommentDialogComponent,
    PrioritizeRequestNiazsanjiMarineSuffixCommentPopupComponent
} from './';
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";

const ENTITY_STATES = [...prioritizeRequestNiazsanjiRoute, ...prioritizeRequestNiazsanjiPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PrioritizeRequestNiazsanjiMarineSuffixComponent,
        PrioritizeRequestNiazsanjiMarineSuffixDetailComponent,
        PrioritizeRequestNiazsanjiMarineSuffixUpdateComponent,
        PrioritizeRequestNiazsanjiMarineSuffixDeleteDialogComponent,
        PrioritizeRequestNiazsanjiMarineSuffixDeletePopupComponent,
        PrioritizeRequestNiazsanjiMarineSuffixCommentDialogComponent,
        PrioritizeRequestNiazsanjiMarineSuffixCommentPopupComponent
    ],
    entryComponents: [
        PrioritizeRequestNiazsanjiMarineSuffixComponent,
        PrioritizeRequestNiazsanjiMarineSuffixUpdateComponent,
        PrioritizeRequestNiazsanjiMarineSuffixDeleteDialogComponent,
        PrioritizeRequestNiazsanjiMarineSuffixDeletePopupComponent,
        PrioritizeRequestNiazsanjiMarineSuffixCommentDialogComponent,
        PrioritizeRequestNiazsanjiMarineSuffixCommentPopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojPrioritizeRequestNiazsanjiMarineSuffixModule {}
