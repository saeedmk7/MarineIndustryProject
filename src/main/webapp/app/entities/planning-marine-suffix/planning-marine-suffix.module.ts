import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    PlanningMarineSuffixComponent,
    PlanningMarineSuffixDetailComponent,
    PlanningMarineSuffixUpdateComponent,
    PlanningMarineSuffixDeleteDialogComponent,
    PlanningMarineSuffixDetailPeopleComponent,
    planningRoute,
    planningPopupRoute,
    PlanningMarineSuffixDeletePopupComponent
} from './';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ExcelModule, GridModule} from "@progress/kendo-angular-grid";

const ENTITY_STATES = [...planningRoute, ...planningPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, BrowserAnimationsModule, GridModule , ExcelModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PlanningMarineSuffixComponent,
        PlanningMarineSuffixDetailPeopleComponent,
        PlanningMarineSuffixDetailComponent,
        PlanningMarineSuffixUpdateComponent,
        PlanningMarineSuffixDeleteDialogComponent,
        PlanningMarineSuffixDeletePopupComponent
    ],
    providers: [ ConvertObjectDatesService ],
    entryComponents: [
        PlanningMarineSuffixComponent,
        PlanningMarineSuffixUpdateComponent,
        PlanningMarineSuffixDeleteDialogComponent,
        PlanningMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojPlanningMarineSuffixModule {}
