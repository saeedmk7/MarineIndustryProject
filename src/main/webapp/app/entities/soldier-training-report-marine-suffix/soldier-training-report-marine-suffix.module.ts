import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    SoldierTrainingReportMarineSuffixComponent,
    SoldierTrainingReportMarineSuffixDetailComponent,
    SoldierTrainingReportMarineSuffixUpdateComponent,
    SoldierTrainingReportMarineSuffixDeletePopupComponent,
    SoldierTrainingReportMarineSuffixDeleteDialogComponent,
    soldierTrainingReportRoute,
    soldierTrainingReportPopupRoute
} from './';
import {NgSelectModule} from "@ng-select/ng-select";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {InputsModule} from "@progress/kendo-angular-inputs";

const ENTITY_STATES = [...soldierTrainingReportRoute, ...soldierTrainingReportPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, BrowserAnimationsModule, InputsModule,RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SoldierTrainingReportMarineSuffixComponent,
        SoldierTrainingReportMarineSuffixDetailComponent,
        SoldierTrainingReportMarineSuffixUpdateComponent,
        SoldierTrainingReportMarineSuffixDeleteDialogComponent,
        SoldierTrainingReportMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        SoldierTrainingReportMarineSuffixComponent,
        SoldierTrainingReportMarineSuffixUpdateComponent,
        SoldierTrainingReportMarineSuffixDeleteDialogComponent,
        SoldierTrainingReportMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojSoldierTrainingReportMarineSuffixModule {}
