import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    FinalNiazsanjiEffectivenessPhaseMarineSuffixComponent,
    FinalNiazsanjiReportMarineSuffixDetailComponent,
    FinalNiazsanjiEffectivenessPhaseMarineSuffixUpdateComponent,
    finalNiazsanjiEffectivenessPhaseRoute
} from './';

const ENTITY_STATES = [...finalNiazsanjiEffectivenessPhaseRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FinalNiazsanjiEffectivenessPhaseMarineSuffixComponent,
        FinalNiazsanjiReportMarineSuffixDetailComponent,
        FinalNiazsanjiEffectivenessPhaseMarineSuffixUpdateComponent
    ],
    entryComponents: [
        FinalNiazsanjiEffectivenessPhaseMarineSuffixComponent,
        FinalNiazsanjiReportMarineSuffixDetailComponent,
        FinalNiazsanjiEffectivenessPhaseMarineSuffixUpdateComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FinalNiazsanjiEffectivenessPhaseMarineSuffixModule {}
