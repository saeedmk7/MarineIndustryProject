import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    NiazsanjiInputMarineSuffixComponent,
    NiazsanjiInputMarineSuffixDetailComponent,
    NiazsanjiInputMarineSuffixUpdateComponent,
    NiazsanjiInputMarineSuffixDeletePopupComponent,
    NiazsanjiInputMarineSuffixDeleteDialogComponent,
    niazsanjiInputRoute,
    niazsanjiInputPopupRoute
} from './';

const ENTITY_STATES = [...niazsanjiInputRoute, ...niazsanjiInputPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NiazsanjiInputMarineSuffixComponent,
        NiazsanjiInputMarineSuffixDetailComponent,
        NiazsanjiInputMarineSuffixUpdateComponent,
        NiazsanjiInputMarineSuffixDeleteDialogComponent,
        NiazsanjiInputMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        NiazsanjiInputMarineSuffixComponent,
        NiazsanjiInputMarineSuffixUpdateComponent,
        NiazsanjiInputMarineSuffixDeleteDialogComponent,
        NiazsanjiInputMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojNiazsanjiInputMarineSuffixModule {}
