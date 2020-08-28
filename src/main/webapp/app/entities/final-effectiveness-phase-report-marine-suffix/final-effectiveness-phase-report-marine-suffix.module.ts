import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    FinalEffectivenessPhaseReportMarineSuffixComponent,
    FinalEffectivenessPhaseReportMarineSuffixDetailPopupComponent,
    FinalEffectivenessPhaseReportMarineSuffixDetailDialogComponent,
    finalEffectivenessPhaseReportPopupRoute,
    finalEffectivenessPhaseReportRoute
} from './';

const ENTITY_STATES = [...finalEffectivenessPhaseReportRoute, ...finalEffectivenessPhaseReportPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FinalEffectivenessPhaseReportMarineSuffixComponent,
        FinalEffectivenessPhaseReportMarineSuffixDetailDialogComponent,
        FinalEffectivenessPhaseReportMarineSuffixDetailPopupComponent
    ],
    entryComponents: [
        FinalEffectivenessPhaseReportMarineSuffixComponent,
        FinalEffectivenessPhaseReportMarineSuffixDetailDialogComponent,
        FinalEffectivenessPhaseReportMarineSuffixDetailPopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojFinalEffectivenessPhaseReportMarineSuffixModule {}
