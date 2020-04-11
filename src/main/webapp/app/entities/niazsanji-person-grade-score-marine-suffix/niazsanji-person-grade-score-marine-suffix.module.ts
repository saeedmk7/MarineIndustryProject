import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    NiazsanjiPersonGradeScoreMarineSuffixComponent,
    NiazsanjiPersonGradeScoreMarineSuffixDetailComponent,
    NiazsanjiPersonGradeScoreMarineSuffixUpdateComponent,
    NiazsanjiPersonGradeScoreMarineSuffixDeletePopupComponent,
    NiazsanjiPersonGradeScoreMarineSuffixDeleteDialogComponent,
    niazsanjiPersonGradeScoreRoute,
    niazsanjiPersonGradeScorePopupRoute
} from './';

const ENTITY_STATES = [...niazsanjiPersonGradeScoreRoute, ...niazsanjiPersonGradeScorePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NiazsanjiPersonGradeScoreMarineSuffixComponent,
        NiazsanjiPersonGradeScoreMarineSuffixDetailComponent,
        NiazsanjiPersonGradeScoreMarineSuffixUpdateComponent,
        NiazsanjiPersonGradeScoreMarineSuffixDeleteDialogComponent,
        NiazsanjiPersonGradeScoreMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        NiazsanjiPersonGradeScoreMarineSuffixComponent,
        NiazsanjiPersonGradeScoreMarineSuffixUpdateComponent,
        NiazsanjiPersonGradeScoreMarineSuffixDeleteDialogComponent,
        NiazsanjiPersonGradeScoreMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojNiazsanjiPersonGradeScoreMarineSuffixModule {}
