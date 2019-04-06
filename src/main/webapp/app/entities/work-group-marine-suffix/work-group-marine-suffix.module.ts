import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    WorkGroupMarineSuffixComponent,
    WorkGroupMarineSuffixDetailComponent,
    WorkGroupMarineSuffixUpdateComponent,
    WorkGroupMarineSuffixDeletePopupComponent,
    WorkGroupMarineSuffixDeleteDialogComponent,
    workGroupRoute,
    workGroupPopupRoute
} from './';

const ENTITY_STATES = [...workGroupRoute, ...workGroupPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        WorkGroupMarineSuffixComponent,
        WorkGroupMarineSuffixDetailComponent,
        WorkGroupMarineSuffixUpdateComponent,
        WorkGroupMarineSuffixDeleteDialogComponent,
        WorkGroupMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        WorkGroupMarineSuffixComponent,
        WorkGroupMarineSuffixUpdateComponent,
        WorkGroupMarineSuffixDeleteDialogComponent,
        WorkGroupMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojWorkGroupMarineSuffixModule {}
