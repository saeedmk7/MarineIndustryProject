import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    MonitorLearningProcessMarineSuffixComponent,
    MonitorLearningProcessMarineSuffixDetailComponent,
    MonitorLearningProcessMarineSuffixUpdateComponent,
    MonitorLearningProcessMarineSuffixDeletePopupComponent,
    MonitorLearningProcessMarineSuffixDeleteDialogComponent,
    monitorLearningProcessRoute,
    monitorLearningProcessPopupRoute
} from './';
import { NgSelectModule } from '@ng-select/ng-select';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { InputsModule } from '@progress/kendo-angular-inputs';

const ENTITY_STATES = [...monitorLearningProcessRoute, ...monitorLearningProcessPopupRoute];

@NgModule({
    imports: [
        MarineindustryprojSharedModule,
        NgSelectModule,
        FormsModule,
        BrowserAnimationsModule,
        InputsModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        MonitorLearningProcessMarineSuffixComponent,
        MonitorLearningProcessMarineSuffixDetailComponent,
        MonitorLearningProcessMarineSuffixUpdateComponent,
        MonitorLearningProcessMarineSuffixDeleteDialogComponent,
        MonitorLearningProcessMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        MonitorLearningProcessMarineSuffixComponent,
        MonitorLearningProcessMarineSuffixUpdateComponent,
        MonitorLearningProcessMarineSuffixDeleteDialogComponent,
        MonitorLearningProcessMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojMonitorLearningProcessMarineSuffixModule {}
