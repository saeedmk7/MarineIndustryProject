import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    ActivityAreaMarineSuffixComponent,
    ActivityAreaMarineSuffixDetailComponent,
    ActivityAreaMarineSuffixUpdateComponent,
    ActivityAreaMarineSuffixDeletePopupComponent,
    ActivityAreaMarineSuffixDeleteDialogComponent,
    activityAreaRoute,
    activityAreaPopupRoute
} from './';

const ENTITY_STATES = [...activityAreaRoute, ...activityAreaPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ActivityAreaMarineSuffixComponent,
        ActivityAreaMarineSuffixDetailComponent,
        ActivityAreaMarineSuffixUpdateComponent,
        ActivityAreaMarineSuffixDeleteDialogComponent,
        ActivityAreaMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        ActivityAreaMarineSuffixComponent,
        ActivityAreaMarineSuffixUpdateComponent,
        ActivityAreaMarineSuffixDeleteDialogComponent,
        ActivityAreaMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojActivityAreaMarineSuffixModule {}
