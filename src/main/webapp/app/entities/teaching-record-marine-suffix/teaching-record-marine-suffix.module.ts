import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    TeachingRecordMarineSuffixComponent,
    TeachingRecordMarineSuffixDetailComponent,
    TeachingRecordMarineSuffixUpdateComponent,
    TeachingRecordMarineSuffixDeletePopupComponent,
    TeachingRecordMarineSuffixDeleteDialogComponent,
    teachingRecordRoute,
    teachingRecordPopupRoute
} from './';

const ENTITY_STATES = [...teachingRecordRoute, ...teachingRecordPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TeachingRecordMarineSuffixComponent,
        TeachingRecordMarineSuffixDetailComponent,
        TeachingRecordMarineSuffixUpdateComponent,
        TeachingRecordMarineSuffixDeleteDialogComponent,
        TeachingRecordMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        TeachingRecordMarineSuffixComponent,
        TeachingRecordMarineSuffixUpdateComponent,
        TeachingRecordMarineSuffixDeleteDialogComponent,
        TeachingRecordMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojTeachingRecordMarineSuffixModule {}
