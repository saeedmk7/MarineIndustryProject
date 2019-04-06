import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    SubTaskMarineSuffixComponent,
    SubTaskMarineSuffixDetailComponent,
    SubTaskMarineSuffixUpdateComponent,
    SubTaskMarineSuffixDeletePopupComponent,
    SubTaskMarineSuffixDeleteDialogComponent,
    subTaskRoute,
    subTaskPopupRoute
} from './';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

const ENTITY_STATES = [...subTaskRoute, ...subTaskPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SubTaskMarineSuffixComponent,
        SubTaskMarineSuffixDetailComponent,
        SubTaskMarineSuffixUpdateComponent,
        SubTaskMarineSuffixDeleteDialogComponent,
        SubTaskMarineSuffixDeletePopupComponent
    ],
    providers: [ ConvertObjectDatesService ],
    entryComponents: [
        SubTaskMarineSuffixComponent,
        SubTaskMarineSuffixUpdateComponent,
        SubTaskMarineSuffixDeleteDialogComponent,
        SubTaskMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojSubTaskMarineSuffixModule {}
