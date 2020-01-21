import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    NiazsanjiOtherMarineSuffixComponent,
    NiazsanjiOtherMarineSuffixDetailComponent,
    NiazsanjiOtherMarineSuffixUpdateComponent,
    NiazsanjiOtherMarineSuffixDeletePopupComponent,
    NiazsanjiOtherMarineSuffixDeleteDialogComponent,
    niazsanjiOtherRoute,
    niazsanjiOtherPopupRoute
} from './';

const ENTITY_STATES = [...niazsanjiOtherRoute, ...niazsanjiOtherPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NiazsanjiOtherMarineSuffixComponent,
        NiazsanjiOtherMarineSuffixDetailComponent,
        NiazsanjiOtherMarineSuffixUpdateComponent,
        NiazsanjiOtherMarineSuffixDeleteDialogComponent,
        NiazsanjiOtherMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        NiazsanjiOtherMarineSuffixComponent,
        NiazsanjiOtherMarineSuffixUpdateComponent,
        NiazsanjiOtherMarineSuffixDeleteDialogComponent,
        NiazsanjiOtherMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojNiazsanjiOtherMarineSuffixModule {}
