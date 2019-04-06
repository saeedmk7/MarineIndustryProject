import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    WorkUnitMarineSuffixComponent,
    WorkUnitMarineSuffixDetailComponent,
    WorkUnitMarineSuffixUpdateComponent,
    WorkUnitMarineSuffixDeletePopupComponent,
    WorkUnitMarineSuffixDeleteDialogComponent,
    workUnitRoute,
    workUnitPopupRoute
} from './';

const ENTITY_STATES = [...workUnitRoute, ...workUnitPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        WorkUnitMarineSuffixComponent,
        WorkUnitMarineSuffixDetailComponent,
        WorkUnitMarineSuffixUpdateComponent,
        WorkUnitMarineSuffixDeleteDialogComponent,
        WorkUnitMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        WorkUnitMarineSuffixComponent,
        WorkUnitMarineSuffixUpdateComponent,
        WorkUnitMarineSuffixDeleteDialogComponent,
        WorkUnitMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojWorkUnitMarineSuffixModule {}
