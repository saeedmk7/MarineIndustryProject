import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    ServiceUnitMarineSuffixComponent,
    ServiceUnitMarineSuffixDetailComponent,
    ServiceUnitMarineSuffixUpdateComponent,
    ServiceUnitMarineSuffixDeletePopupComponent,
    ServiceUnitMarineSuffixDeleteDialogComponent,
    serviceUnitRoute,
    serviceUnitPopupRoute
} from './';

const ENTITY_STATES = [...serviceUnitRoute, ...serviceUnitPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ServiceUnitMarineSuffixComponent,
        ServiceUnitMarineSuffixDetailComponent,
        ServiceUnitMarineSuffixUpdateComponent,
        ServiceUnitMarineSuffixDeleteDialogComponent,
        ServiceUnitMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        ServiceUnitMarineSuffixComponent,
        ServiceUnitMarineSuffixUpdateComponent,
        ServiceUnitMarineSuffixDeleteDialogComponent,
        ServiceUnitMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojServiceUnitMarineSuffixModule {}
