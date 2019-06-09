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

const ENTITY_STATES = [...jobRecordRoute, ...jobRecordPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
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
