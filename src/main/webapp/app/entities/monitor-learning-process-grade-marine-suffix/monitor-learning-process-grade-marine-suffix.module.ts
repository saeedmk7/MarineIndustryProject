import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    MonitorLearningProcessGradeMarineSuffixComponent,
    MonitorLearningProcessGradeMarineSuffixDetailComponent,
    MonitorLearningProcessGradeMarineSuffixUpdateComponent,
    MonitorLearningProcessGradeMarineSuffixDeletePopupComponent,
    MonitorLearningProcessGradeMarineSuffixDeleteDialogComponent,
    monitorLearningProcessGradeRoute,
    monitorLearningProcessGradePopupRoute
} from './';

const ENTITY_STATES = [...monitorLearningProcessGradeRoute, ...monitorLearningProcessGradePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MonitorLearningProcessGradeMarineSuffixComponent,
        MonitorLearningProcessGradeMarineSuffixDetailComponent,
        MonitorLearningProcessGradeMarineSuffixUpdateComponent,
        MonitorLearningProcessGradeMarineSuffixDeleteDialogComponent,
        MonitorLearningProcessGradeMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        MonitorLearningProcessGradeMarineSuffixComponent,
        MonitorLearningProcessGradeMarineSuffixUpdateComponent,
        MonitorLearningProcessGradeMarineSuffixDeleteDialogComponent,
        MonitorLearningProcessGradeMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojMonitorLearningProcessGradeMarineSuffixModule {}
