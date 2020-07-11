import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    ReportGeneratorMarineSuffixComponent,
    ReportGeneratorMarineSuffixDetailComponent,
    ReportGeneratorMarineSuffixUpdateComponent,
    ReportGeneratorMarineSuffixDeletePopupComponent,
    ReportGeneratorMarineSuffixDeleteDialogComponent,
    reportGeneratorRoute,
    reportGeneratorPopupRoute
} from './';
import { NgSelectModule } from '@ng-select/ng-select';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

const ENTITY_STATES = [...reportGeneratorRoute, ...reportGeneratorPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, BrowserAnimationsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ReportGeneratorMarineSuffixComponent,
        ReportGeneratorMarineSuffixDetailComponent,
        ReportGeneratorMarineSuffixUpdateComponent,
        ReportGeneratorMarineSuffixDeleteDialogComponent,
        ReportGeneratorMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        ReportGeneratorMarineSuffixComponent,
        ReportGeneratorMarineSuffixUpdateComponent,
        ReportGeneratorMarineSuffixDeleteDialogComponent,
        ReportGeneratorMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojReportGeneratorMarineSuffixModule {}
