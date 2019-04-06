import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    WorkIndustryMarineSuffixComponent,
    WorkIndustryMarineSuffixDetailComponent,
    WorkIndustryMarineSuffixUpdateComponent,
    WorkIndustryMarineSuffixDeletePopupComponent,
    WorkIndustryMarineSuffixDeleteDialogComponent,
    workIndustryRoute,
    workIndustryPopupRoute
} from './';

const ENTITY_STATES = [...workIndustryRoute, ...workIndustryPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        WorkIndustryMarineSuffixComponent,
        WorkIndustryMarineSuffixDetailComponent,
        WorkIndustryMarineSuffixUpdateComponent,
        WorkIndustryMarineSuffixDeleteDialogComponent,
        WorkIndustryMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        WorkIndustryMarineSuffixComponent,
        WorkIndustryMarineSuffixUpdateComponent,
        WorkIndustryMarineSuffixDeleteDialogComponent,
        WorkIndustryMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojWorkIndustryMarineSuffixModule {}
