import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    JobNiazsanjiMarineSuffixComponent,
    JobNiazsanjiMarineSuffixDetailComponent,
    JobNiazsanjiMarineSuffixUpdateComponent,
    JobNiazsanjiMarineSuffixDeletePopupComponent,
    JobNiazsanjiMarineSuffixDeleteDialogComponent,
    jobNiazsanjiRoute,
    jobNiazsanjiPopupRoute,
    JobNiazsanjiMarineSuffixCommentDialogComponent,
    JobNiazsanjiMarineSuffixCommentPopupComponent
} from './';
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";

const ENTITY_STATES = [...jobNiazsanjiRoute, ...jobNiazsanjiPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        JobNiazsanjiMarineSuffixComponent,
        JobNiazsanjiMarineSuffixDetailComponent,
        JobNiazsanjiMarineSuffixUpdateComponent,
        JobNiazsanjiMarineSuffixDeleteDialogComponent,
        JobNiazsanjiMarineSuffixDeletePopupComponent,
        JobNiazsanjiMarineSuffixCommentDialogComponent,
        JobNiazsanjiMarineSuffixCommentPopupComponent
    ],
    entryComponents: [
        JobNiazsanjiMarineSuffixComponent,
        JobNiazsanjiMarineSuffixUpdateComponent,
        JobNiazsanjiMarineSuffixDeleteDialogComponent,
        JobNiazsanjiMarineSuffixDeletePopupComponent,
        JobNiazsanjiMarineSuffixCommentDialogComponent,
        JobNiazsanjiMarineSuffixCommentPopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojJobNiazsanjiMarineSuffixModule {}
