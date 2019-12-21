import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    MediaAwarenessReportMarineSuffixComponent,
    MediaAwarenessReportMarineSuffixDetailComponent,
    MediaAwarenessReportMarineSuffixUpdateComponent,
    MediaAwarenessReportMarineSuffixDeletePopupComponent,
    MediaAwarenessReportMarineSuffixDeleteDialogComponent,
    mediaAwarenessReportRoute,
    mediaAwarenessReportPopupRoute
} from './';
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {InputsModule} from "@progress/kendo-angular-inputs";

const ENTITY_STATES = [...mediaAwarenessReportRoute, ...mediaAwarenessReportPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, BrowserAnimationsModule, InputsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MediaAwarenessReportMarineSuffixComponent,
        MediaAwarenessReportMarineSuffixDetailComponent,
        MediaAwarenessReportMarineSuffixUpdateComponent,
        MediaAwarenessReportMarineSuffixDeleteDialogComponent,
        MediaAwarenessReportMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        MediaAwarenessReportMarineSuffixComponent,
        MediaAwarenessReportMarineSuffixUpdateComponent,
        MediaAwarenessReportMarineSuffixDeleteDialogComponent,
        MediaAwarenessReportMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojMediaAwarenessReportMarineSuffixModule {}
