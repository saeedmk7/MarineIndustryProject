import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    JobTypeMarineSuffixComponent,
    JobTypeMarineSuffixDetailComponent,
    JobTypeMarineSuffixUpdateComponent,
    JobTypeMarineSuffixDeletePopupComponent,
    JobTypeMarineSuffixDeleteDialogComponent,
    jobTypeRoute,
    jobTypePopupRoute
} from './';

const ENTITY_STATES = [...jobTypeRoute, ...jobTypePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        JobTypeMarineSuffixComponent,
        JobTypeMarineSuffixDetailComponent,
        JobTypeMarineSuffixUpdateComponent,
        JobTypeMarineSuffixDeleteDialogComponent,
        JobTypeMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        JobTypeMarineSuffixComponent,
        JobTypeMarineSuffixUpdateComponent,
        JobTypeMarineSuffixDeleteDialogComponent,
        JobTypeMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojJobTypeMarineSuffixModule {}
