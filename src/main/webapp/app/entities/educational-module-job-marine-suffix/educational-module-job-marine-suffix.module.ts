import {NgModule, CUSTOM_ELEMENTS_SCHEMA, LOCALE_ID} from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    EducationalModuleJobMarineSuffixComponent,
    EducationalModuleJobMarineSuffixDetailComponent,
    EducationalModuleJobMarineSuffixUpdateComponent,
    EducationalModuleJobMarineSuffixDeletePopupComponent,
    EducationalModuleJobMarineSuffixDeleteDialogComponent,
    educationalModuleJobRoute,
    educationalModuleJobPopupRoute
} from './';
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";
import {ExcelModule, GridModule} from "@progress/kendo-angular-grid";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {RTL} from "@progress/kendo-angular-l10n";

const ENTITY_STATES = [...educationalModuleJobRoute, ...educationalModuleJobPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, BrowserAnimationsModule, GridModule , ExcelModule, FormsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EducationalModuleJobMarineSuffixComponent,
        EducationalModuleJobMarineSuffixDetailComponent,
        EducationalModuleJobMarineSuffixUpdateComponent,
        EducationalModuleJobMarineSuffixDeleteDialogComponent,
        EducationalModuleJobMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        EducationalModuleJobMarineSuffixComponent,
        EducationalModuleJobMarineSuffixUpdateComponent,
        EducationalModuleJobMarineSuffixDeleteDialogComponent,
        EducationalModuleJobMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    providers:    [{ provide: RTL, useValue: true }]
})
export class MarineindustryprojEducationalModuleJobMarineSuffixModule {}
