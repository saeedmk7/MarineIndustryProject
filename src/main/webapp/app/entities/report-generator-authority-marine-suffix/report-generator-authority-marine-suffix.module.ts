import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    ReportGeneratorAuthorityMarineSuffixComponent,
    ReportGeneratorAuthorityMarineSuffixDetailComponent,
    ReportGeneratorAuthorityMarineSuffixUpdateComponent,
    ReportGeneratorAuthorityMarineSuffixDeletePopupComponent,
    ReportGeneratorAuthorityMarineSuffixDeleteDialogComponent,
    reportGeneratorAuthorityRoute,
    reportGeneratorAuthorityPopupRoute
} from './';

const ENTITY_STATES = [...reportGeneratorAuthorityRoute, ...reportGeneratorAuthorityPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ReportGeneratorAuthorityMarineSuffixComponent,
        ReportGeneratorAuthorityMarineSuffixDetailComponent,
        ReportGeneratorAuthorityMarineSuffixUpdateComponent,
        ReportGeneratorAuthorityMarineSuffixDeleteDialogComponent,
        ReportGeneratorAuthorityMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        ReportGeneratorAuthorityMarineSuffixComponent,
        ReportGeneratorAuthorityMarineSuffixUpdateComponent,
        ReportGeneratorAuthorityMarineSuffixDeleteDialogComponent,
        ReportGeneratorAuthorityMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojReportGeneratorAuthorityMarineSuffixModule {}
