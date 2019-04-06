import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    MainTaskMarineSuffixComponent,
    MainTaskMarineSuffixDetailComponent,
    MainTaskMarineSuffixUpdateComponent,
    MainTaskMarineSuffixDeletePopupComponent,
    MainTaskMarineSuffixDeleteDialogComponent,
    mainTaskRoute,
    mainTaskPopupRoute
} from './';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

const ENTITY_STATES = [...mainTaskRoute, ...mainTaskPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MainTaskMarineSuffixComponent,
        MainTaskMarineSuffixDetailComponent,
        MainTaskMarineSuffixUpdateComponent,
        MainTaskMarineSuffixDeleteDialogComponent,
        MainTaskMarineSuffixDeletePopupComponent
    ],
    providers: [ ConvertObjectDatesService ],
    entryComponents: [
        MainTaskMarineSuffixComponent,
        MainTaskMarineSuffixUpdateComponent,
        MainTaskMarineSuffixDeleteDialogComponent,
        MainTaskMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojMainTaskMarineSuffixModule {}
