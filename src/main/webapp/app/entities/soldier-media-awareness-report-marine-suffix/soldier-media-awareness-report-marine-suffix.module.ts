import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    SoldierMediaAwarenessReportMarineSuffixComponent,
    SoldierMediaAwarenessReportMarineSuffixDetailComponent,
    SoldierMediaAwarenessReportMarineSuffixUpdateComponent,
    SoldierMediaAwarenessReportMarineSuffixDeletePopupComponent,
    SoldierMediaAwarenessReportMarineSuffixDeleteDialogComponent,
    soldierMediaAwarenessReportRoute,
    soldierMediaAwarenessReportPopupRoute
} from './';
import { NgSelectModule } from '@ng-select/ng-select';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { InputsModule } from '@progress/kendo-angular-inputs';

const ENTITY_STATES = [...soldierMediaAwarenessReportRoute, ...soldierMediaAwarenessReportPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, BrowserAnimationsModule, InputsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SoldierMediaAwarenessReportMarineSuffixComponent,
        SoldierMediaAwarenessReportMarineSuffixDetailComponent,
        SoldierMediaAwarenessReportMarineSuffixUpdateComponent,
        SoldierMediaAwarenessReportMarineSuffixDeleteDialogComponent,
        SoldierMediaAwarenessReportMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        SoldierMediaAwarenessReportMarineSuffixComponent,
        SoldierMediaAwarenessReportMarineSuffixUpdateComponent,
        SoldierMediaAwarenessReportMarineSuffixDeleteDialogComponent,
        SoldierMediaAwarenessReportMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojSoldierMediaAwarenessReportMarineSuffixModule {}
