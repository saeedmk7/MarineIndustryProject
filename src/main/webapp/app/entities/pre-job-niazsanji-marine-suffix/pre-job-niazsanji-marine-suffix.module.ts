import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    PreJobNiazsanjiMarineSuffixComponent,
    PreJobNiazsanjiMarineSuffixDetailComponent,
    PreJobNiazsanjiMarineSuffixUpdateComponent,
    PreJobNiazsanjiMarineSuffixDeletePopupComponent,
    PreJobNiazsanjiMarineSuffixDeleteDialogComponent,
    preJobNiazsanjiRoute,
    preJobNiazsanjiPopupRoute,
    PreJobNiazsanjiMarineSuffixCommentDialogComponent,
    PreJobNiazsanjiMarineSuffixCommentPopupComponent
} from './';
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";

const ENTITY_STATES = [...preJobNiazsanjiRoute, ...preJobNiazsanjiPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PreJobNiazsanjiMarineSuffixComponent,
        PreJobNiazsanjiMarineSuffixDetailComponent,
        PreJobNiazsanjiMarineSuffixUpdateComponent,
        PreJobNiazsanjiMarineSuffixDeleteDialogComponent,
        PreJobNiazsanjiMarineSuffixDeletePopupComponent,
        PreJobNiazsanjiMarineSuffixCommentDialogComponent,
        PreJobNiazsanjiMarineSuffixCommentPopupComponent
    ],
    entryComponents: [
        PreJobNiazsanjiMarineSuffixComponent,
        PreJobNiazsanjiMarineSuffixUpdateComponent,
        PreJobNiazsanjiMarineSuffixDeleteDialogComponent,
        PreJobNiazsanjiMarineSuffixDeletePopupComponent,
        PreJobNiazsanjiMarineSuffixCommentDialogComponent,
        PreJobNiazsanjiMarineSuffixCommentPopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    exports: [PreJobNiazsanjiMarineSuffixDetailComponent]
})
export class MarineindustryprojPreJobNiazsanjiMarineSuffixModule {}
