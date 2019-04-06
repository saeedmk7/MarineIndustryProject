import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    NiazsanjiFardiMarineSuffixComponent,
    NiazsanjiFardiMarineSuffixDetailComponent,
    NiazsanjiFardiMarineSuffixUpdateComponent,
    NiazsanjiFardiMarineSuffixDeletePopupComponent,
    NiazsanjiFardiMarineSuffixDeleteDialogComponent,
    niazsanjiFardiRoute,
    niazsanjiFardiPopupRoute,
    NiazsanjiFardiMarineSuffixCommentDialogComponent,
    NiazsanjiFardiMarineSuffixCommentPopupComponent
} from './';
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";

const ENTITY_STATES = [...niazsanjiFardiRoute, ...niazsanjiFardiPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NiazsanjiFardiMarineSuffixComponent,
        NiazsanjiFardiMarineSuffixDetailComponent,
        NiazsanjiFardiMarineSuffixUpdateComponent,
        NiazsanjiFardiMarineSuffixDeleteDialogComponent,
        NiazsanjiFardiMarineSuffixDeletePopupComponent,
        NiazsanjiFardiMarineSuffixCommentDialogComponent,
        NiazsanjiFardiMarineSuffixCommentPopupComponent
    ],
    entryComponents: [
        NiazsanjiFardiMarineSuffixComponent,
        NiazsanjiFardiMarineSuffixUpdateComponent,
        NiazsanjiFardiMarineSuffixDeleteDialogComponent,
        NiazsanjiFardiMarineSuffixDeletePopupComponent,
        NiazsanjiFardiMarineSuffixCommentDialogComponent,
        NiazsanjiFardiMarineSuffixCommentPopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojNiazsanjiFardiMarineSuffixModule {}
