import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";
import {preJobNiazsanjiStepsRoute} from "app/entities/pre-job-niazsanji-steps-marine-suffix/pre-job-niazsanji-steps-marine-suffix.route";
import {MarineindustryprojPreJobNiazsanjiMarineSuffixModule} from "app/entities/pre-job-niazsanji-marine-suffix/pre-job-niazsanji-marine-suffix.module";
import {
    PreJobNiazsanjiStepsHandlerMarineSuffixUpdateComponent,
    PreJobNiazsanjiStep2UpdateComponent,
    PreJobNiazsanjiStep3UpdateComponent, PreJobNiazsanjiStep4UpdateComponent, PreJobNiazsanjiStep5UpdateComponent
} from "./";
import {MarineindustryprojDesignNiazsanjiMarineSuffixModule} from 'app/entities/design-niazsanji-marine-suffix/design-niazsanji-marine-suffix.module';


const ENTITY_STATES = [...preJobNiazsanjiStepsRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, MarineindustryprojPreJobNiazsanjiMarineSuffixModule, MarineindustryprojDesignNiazsanjiMarineSuffixModule, NgSelectModule, FormsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PreJobNiazsanjiStepsHandlerMarineSuffixUpdateComponent,
        PreJobNiazsanjiStep2UpdateComponent,
        PreJobNiazsanjiStep3UpdateComponent,
        PreJobNiazsanjiStep4UpdateComponent,
        PreJobNiazsanjiStep5UpdateComponent
    ],
    entryComponents: [
        PreJobNiazsanjiStepsHandlerMarineSuffixUpdateComponent,
        PreJobNiazsanjiStep2UpdateComponent,
        PreJobNiazsanjiStep3UpdateComponent,
        PreJobNiazsanjiStep4UpdateComponent,
        PreJobNiazsanjiStep5UpdateComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojPreJobNiazsanjiStepsMarineSuffixModule {}
