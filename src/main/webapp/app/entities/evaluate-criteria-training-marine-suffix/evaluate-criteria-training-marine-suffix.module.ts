import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    EvaluateCriteriaTrainingMarineSuffixComponent,
    EvaluateCriteriaTrainingMarineSuffixDetailComponent,
    EvaluateCriteriaTrainingMarineSuffixUpdateComponent,
    EvaluateCriteriaTrainingMarineSuffixDeletePopupComponent,
    EvaluateCriteriaTrainingMarineSuffixDeleteDialogComponent,
    evaluateCriteriaTrainingRoute,
    evaluateCriteriaTrainingPopupRoute
} from './';
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

const ENTITY_STATES = [...evaluateCriteriaTrainingRoute, ...evaluateCriteriaTrainingPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, BrowserAnimationsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EvaluateCriteriaTrainingMarineSuffixComponent,
        EvaluateCriteriaTrainingMarineSuffixDetailComponent,
        EvaluateCriteriaTrainingMarineSuffixUpdateComponent,
        EvaluateCriteriaTrainingMarineSuffixDeleteDialogComponent,
        EvaluateCriteriaTrainingMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        EvaluateCriteriaTrainingMarineSuffixComponent,
        EvaluateCriteriaTrainingMarineSuffixUpdateComponent,
        EvaluateCriteriaTrainingMarineSuffixDeleteDialogComponent,
        EvaluateCriteriaTrainingMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojEvaluateCriteriaTrainingMarineSuffixModule {}
