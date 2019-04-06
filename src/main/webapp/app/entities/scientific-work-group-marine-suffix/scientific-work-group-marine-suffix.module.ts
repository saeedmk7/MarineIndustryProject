import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    ScientificWorkGroupMarineSuffixComponent,
    ScientificWorkGroupMarineSuffixDetailComponent,
    ScientificWorkGroupMarineSuffixUpdateComponent,
    ScientificWorkGroupMarineSuffixDeletePopupComponent,
    ScientificWorkGroupMarineSuffixDeleteDialogComponent,
    scientificWorkGroupRoute,
    scientificWorkGroupPopupRoute
} from './';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

const ENTITY_STATES = [...scientificWorkGroupRoute, ...scientificWorkGroupPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ScientificWorkGroupMarineSuffixComponent,
        ScientificWorkGroupMarineSuffixDetailComponent,
        ScientificWorkGroupMarineSuffixUpdateComponent,
        ScientificWorkGroupMarineSuffixDeleteDialogComponent,
        ScientificWorkGroupMarineSuffixDeletePopupComponent
    ],
    providers: [ ConvertObjectDatesService ],
    entryComponents: [
        ScientificWorkGroupMarineSuffixComponent,
        ScientificWorkGroupMarineSuffixUpdateComponent,
        ScientificWorkGroupMarineSuffixDeleteDialogComponent,
        ScientificWorkGroupMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojScientificWorkGroupMarineSuffixModule {}
