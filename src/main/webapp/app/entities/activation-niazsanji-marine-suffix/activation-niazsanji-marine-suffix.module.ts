import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    ActivationNiazsanjiMarineSuffixComponent,
    ActivationNiazsanjiMarineSuffixDetailComponent,
    ActivationNiazsanjiMarineSuffixUpdateComponent,
    ActivationNiazsanjiMarineSuffixDeletePopupComponent,
    ActivationNiazsanjiMarineSuffixDeleteDialogComponent,
    activationNiazsanjiRoute,
    activationNiazsanjiPopupRoute
} from './';

const ENTITY_STATES = [...activationNiazsanjiRoute, ...activationNiazsanjiPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ActivationNiazsanjiMarineSuffixComponent,
        ActivationNiazsanjiMarineSuffixDetailComponent,
        ActivationNiazsanjiMarineSuffixUpdateComponent,
        ActivationNiazsanjiMarineSuffixDeleteDialogComponent,
        ActivationNiazsanjiMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        ActivationNiazsanjiMarineSuffixComponent,
        ActivationNiazsanjiMarineSuffixUpdateComponent,
        ActivationNiazsanjiMarineSuffixDeleteDialogComponent,
        ActivationNiazsanjiMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojActivationNiazsanjiMarineSuffixModule {}
