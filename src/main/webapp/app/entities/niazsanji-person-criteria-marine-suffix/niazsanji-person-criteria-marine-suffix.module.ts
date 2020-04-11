import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    NiazsanjiPersonCriteriaMarineSuffixComponent,
    NiazsanjiPersonCriteriaMarineSuffixDetailComponent,
    NiazsanjiPersonCriteriaMarineSuffixUpdateComponent,
    NiazsanjiPersonCriteriaMarineSuffixDeletePopupComponent,
    NiazsanjiPersonCriteriaMarineSuffixDeleteDialogComponent,
    niazsanjiPersonCriteriaRoute,
    niazsanjiPersonCriteriaPopupRoute
} from './';

const ENTITY_STATES = [...niazsanjiPersonCriteriaRoute, ...niazsanjiPersonCriteriaPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NiazsanjiPersonCriteriaMarineSuffixComponent,
        NiazsanjiPersonCriteriaMarineSuffixDetailComponent,
        NiazsanjiPersonCriteriaMarineSuffixUpdateComponent,
        NiazsanjiPersonCriteriaMarineSuffixDeleteDialogComponent,
        NiazsanjiPersonCriteriaMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        NiazsanjiPersonCriteriaMarineSuffixComponent,
        NiazsanjiPersonCriteriaMarineSuffixUpdateComponent,
        NiazsanjiPersonCriteriaMarineSuffixDeleteDialogComponent,
        NiazsanjiPersonCriteriaMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojNiazsanjiPersonCriteriaMarineSuffixModule {}
