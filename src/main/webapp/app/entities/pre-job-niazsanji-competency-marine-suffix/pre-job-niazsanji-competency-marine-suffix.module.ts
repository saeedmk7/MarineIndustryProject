import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    PreJobNiazsanjiCompetencyMarineSuffixComponent,
    PreJobNiazsanjiCompetencyMarineSuffixDetailComponent,
    PreJobNiazsanjiCompetencyMarineSuffixUpdateComponent,
    PreJobNiazsanjiCompetencyMarineSuffixDeletePopupComponent,
    PreJobNiazsanjiCompetencyMarineSuffixDeleteDialogComponent,
    preJobNiazsanjiCompetencyRoute,
    preJobNiazsanjiCompetencyPopupRoute
} from './';
import {MarineindustryprojPreJobNiazsanjiMarineSuffixModule} from "app/entities/pre-job-niazsanji-marine-suffix/pre-job-niazsanji-marine-suffix.module";

const ENTITY_STATES = [...preJobNiazsanjiCompetencyRoute, ...preJobNiazsanjiCompetencyPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, MarineindustryprojPreJobNiazsanjiMarineSuffixModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PreJobNiazsanjiCompetencyMarineSuffixComponent,
        PreJobNiazsanjiCompetencyMarineSuffixDetailComponent,
        PreJobNiazsanjiCompetencyMarineSuffixUpdateComponent,
        PreJobNiazsanjiCompetencyMarineSuffixDeleteDialogComponent,
        PreJobNiazsanjiCompetencyMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        PreJobNiazsanjiCompetencyMarineSuffixComponent,
        PreJobNiazsanjiCompetencyMarineSuffixUpdateComponent,
        PreJobNiazsanjiCompetencyMarineSuffixDeleteDialogComponent,
        PreJobNiazsanjiCompetencyMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojPreJobNiazsanjiCompetencyMarineSuffixModule {}
