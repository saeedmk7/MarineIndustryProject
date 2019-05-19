import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    RunPhaseMarineSuffixComponent,
    RunPhaseMarineSuffixDetailComponent,
    RunPhaseMarineSuffixUpdateComponent,
    RunPhaseMarineSuffixDeletePopupComponent,
    RunPhaseMarineSuffixDeleteDialogComponent,
    runPhaseRoute,
    runPhasePopupRoute
} from './';
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ExcelModule, GridModule} from "@progress/kendo-angular-grid";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

const ENTITY_STATES = [...runPhaseRoute, ...runPhasePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, BrowserAnimationsModule, GridModule , ExcelModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RunPhaseMarineSuffixComponent,
        RunPhaseMarineSuffixDetailComponent,
        RunPhaseMarineSuffixUpdateComponent,
        RunPhaseMarineSuffixDeleteDialogComponent,
        RunPhaseMarineSuffixDeletePopupComponent
    ],
    providers: [ ConvertObjectDatesService ],
    entryComponents: [
        RunPhaseMarineSuffixComponent,
        RunPhaseMarineSuffixUpdateComponent,
        RunPhaseMarineSuffixDeleteDialogComponent,
        RunPhaseMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojRunPhaseMarineSuffixModule {}
