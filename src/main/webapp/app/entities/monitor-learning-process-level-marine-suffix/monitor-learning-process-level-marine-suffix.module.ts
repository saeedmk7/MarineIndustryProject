import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    MonitorLearningProcessLevelMarineSuffixComponent,
    MonitorLearningProcessLevelMarineSuffixDetailComponent,
    MonitorLearningProcessLevelMarineSuffixUpdateComponent,
    MonitorLearningProcessLevelMarineSuffixDeletePopupComponent,
    MonitorLearningProcessLevelMarineSuffixDeleteDialogComponent,
    monitorLearningProcessLevelRoute,
    monitorLearningProcessLevelPopupRoute
} from './';

const ENTITY_STATES = [...monitorLearningProcessLevelRoute, ...monitorLearningProcessLevelPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MonitorLearningProcessLevelMarineSuffixComponent,
        MonitorLearningProcessLevelMarineSuffixDetailComponent,
        MonitorLearningProcessLevelMarineSuffixUpdateComponent,
        MonitorLearningProcessLevelMarineSuffixDeleteDialogComponent,
        MonitorLearningProcessLevelMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        MonitorLearningProcessLevelMarineSuffixComponent,
        MonitorLearningProcessLevelMarineSuffixUpdateComponent,
        MonitorLearningProcessLevelMarineSuffixDeleteDialogComponent,
        MonitorLearningProcessLevelMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojMonitorLearningProcessLevelMarineSuffixModule {}
