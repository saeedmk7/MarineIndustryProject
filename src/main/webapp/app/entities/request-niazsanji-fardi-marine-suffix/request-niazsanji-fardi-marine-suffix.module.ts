import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    RequestNiazsanjiFardiMarineSuffixComponent,
    RequestNiazsanjiFardiMarineSuffixDetailComponent,
    RequestNiazsanjiFardiMarineSuffixUpdateComponent,
    RequestNiazsanjiFardiMarineSuffixDeletePopupComponent,
    RequestNiazsanjiFardiMarineSuffixDeleteDialogComponent,
    requestNiazsanjiFardiRoute,
    requestNiazsanjiFardiPopupRoute
} from './';
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";

import {
    RequestNiazsanjiFardiMarineSuffixCommentDialogComponent,
    RequestNiazsanjiFardiMarineSuffixCommentPopupComponent
} from "app/entities/request-niazsanji-fardi-marine-suffix/request-niazsanji-fardi-marine-suffix-comment-dialog.component";

const ENTITY_STATES = [...requestNiazsanjiFardiRoute, ...requestNiazsanjiFardiPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RequestNiazsanjiFardiMarineSuffixComponent,
        RequestNiazsanjiFardiMarineSuffixDetailComponent,
        RequestNiazsanjiFardiMarineSuffixUpdateComponent,
        RequestNiazsanjiFardiMarineSuffixDeleteDialogComponent,
        RequestNiazsanjiFardiMarineSuffixDeletePopupComponent,
        RequestNiazsanjiFardiMarineSuffixCommentDialogComponent,
        RequestNiazsanjiFardiMarineSuffixCommentPopupComponent
    ],
    entryComponents: [
        RequestNiazsanjiFardiMarineSuffixComponent,
        RequestNiazsanjiFardiMarineSuffixUpdateComponent,
        RequestNiazsanjiFardiMarineSuffixDeleteDialogComponent,
        RequestNiazsanjiFardiMarineSuffixDeletePopupComponent,
        RequestNiazsanjiFardiMarineSuffixCommentDialogComponent,
        RequestNiazsanjiFardiMarineSuffixCommentPopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojRequestNiazsanjiFardiMarineSuffixModule {}
