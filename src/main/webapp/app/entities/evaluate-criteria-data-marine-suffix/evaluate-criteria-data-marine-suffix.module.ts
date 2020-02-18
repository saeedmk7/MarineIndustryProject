import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    EvaluateCriteriaDataMarineSuffixComponent,
    EvaluateCriteriaDataMarineSuffixDetailComponent,
    EvaluateCriteriaDataMarineSuffixUpdateComponent,
    EvaluateCriteriaDataMarineSuffixDeletePopupComponent,
    EvaluateCriteriaDataMarineSuffixDeleteDialogComponent,
    evaluateCriteriaDataRoute,
    evaluateCriteriaDataPopupRoute
} from './';
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

const ENTITY_STATES = [...evaluateCriteriaDataRoute, ...evaluateCriteriaDataPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, BrowserAnimationsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EvaluateCriteriaDataMarineSuffixComponent,
        EvaluateCriteriaDataMarineSuffixDetailComponent,
        EvaluateCriteriaDataMarineSuffixUpdateComponent,
        EvaluateCriteriaDataMarineSuffixDeleteDialogComponent,
        EvaluateCriteriaDataMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        EvaluateCriteriaDataMarineSuffixComponent,
        EvaluateCriteriaDataMarineSuffixUpdateComponent,
        EvaluateCriteriaDataMarineSuffixDeleteDialogComponent,
        EvaluateCriteriaDataMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojEvaluateCriteriaDataMarineSuffixModule {}
