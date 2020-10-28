import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    MonitorProcessDurationMarineSuffixComponent,
    MonitorProcessDurationMarineSuffixDetailComponent,
    MonitorProcessDurationMarineSuffixUpdateComponent,
    MonitorProcessDurationMarineSuffixDeletePopupComponent,
    MonitorProcessDurationMarineSuffixDeleteDialogComponent,
    monitorProcessDurationRoute,
    monitorProcessDurationPopupRoute
} from './';

const ENTITY_STATES = [...monitorProcessDurationRoute, ...monitorProcessDurationPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MonitorProcessDurationMarineSuffixComponent,
        MonitorProcessDurationMarineSuffixDetailComponent,
        MonitorProcessDurationMarineSuffixUpdateComponent,
        MonitorProcessDurationMarineSuffixDeleteDialogComponent,
        MonitorProcessDurationMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        MonitorProcessDurationMarineSuffixComponent,
        MonitorProcessDurationMarineSuffixUpdateComponent,
        MonitorProcessDurationMarineSuffixDeleteDialogComponent,
        MonitorProcessDurationMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojMonitorProcessDurationMarineSuffixModule {}
