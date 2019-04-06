import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    RadehMarineSuffixComponent,
    RadehMarineSuffixDetailComponent,
    RadehMarineSuffixUpdateComponent,
    RadehMarineSuffixDeletePopupComponent,
    RadehMarineSuffixDeleteDialogComponent,
    radehRoute,
    radehPopupRoute
} from './';

const ENTITY_STATES = [...radehRoute, ...radehPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RadehMarineSuffixComponent,
        RadehMarineSuffixDetailComponent,
        RadehMarineSuffixUpdateComponent,
        RadehMarineSuffixDeleteDialogComponent,
        RadehMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        RadehMarineSuffixComponent,
        RadehMarineSuffixUpdateComponent,
        RadehMarineSuffixDeleteDialogComponent,
        RadehMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojRadehMarineSuffixModule {}
