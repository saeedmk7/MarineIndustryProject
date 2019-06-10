import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    JobRecordMarineSuffixComponent,
    JobRecordMarineSuffixDetailComponent,
    JobRecordMarineSuffixUpdateComponent,
    JobRecordMarineSuffixDeletePopupComponent,
    JobRecordMarineSuffixDeleteDialogComponent,
    jobRecordRoute,
    jobRecordPopupRoute
} from './';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {InputsModule} from "@progress/kendo-angular-inputs";

const ENTITY_STATES = [...jobRecordRoute, ...jobRecordPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, BrowserAnimationsModule, InputsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        JobRecordMarineSuffixComponent,
        JobRecordMarineSuffixDetailComponent,
        JobRecordMarineSuffixUpdateComponent,
        JobRecordMarineSuffixDeleteDialogComponent,
        JobRecordMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        JobRecordMarineSuffixComponent,
        JobRecordMarineSuffixUpdateComponent,
        JobRecordMarineSuffixDeleteDialogComponent,
        JobRecordMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojJobRecordMarineSuffixModule {}
