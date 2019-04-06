import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    TeachTypeMarineSuffixComponent,
    TeachTypeMarineSuffixDetailComponent,
    TeachTypeMarineSuffixUpdateComponent,
    TeachTypeMarineSuffixDeletePopupComponent,
    TeachTypeMarineSuffixDeleteDialogComponent,
    teachTypeRoute,
    teachTypePopupRoute
} from './';

const ENTITY_STATES = [...teachTypeRoute, ...teachTypePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TeachTypeMarineSuffixComponent,
        TeachTypeMarineSuffixDetailComponent,
        TeachTypeMarineSuffixUpdateComponent,
        TeachTypeMarineSuffixDeleteDialogComponent,
        TeachTypeMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        TeachTypeMarineSuffixComponent,
        TeachTypeMarineSuffixUpdateComponent,
        TeachTypeMarineSuffixDeleteDialogComponent,
        TeachTypeMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojTeachTypeMarineSuffixModule {}
