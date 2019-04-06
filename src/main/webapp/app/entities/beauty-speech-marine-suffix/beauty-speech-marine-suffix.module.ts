import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    BeautySpeechMarineSuffixComponent,
    BeautySpeechMarineSuffixDetailComponent,
    BeautySpeechMarineSuffixUpdateComponent,
    BeautySpeechMarineSuffixDeletePopupComponent,
    BeautySpeechMarineSuffixDeleteDialogComponent,
    beautySpeechRoute,
    beautySpeechPopupRoute
} from './';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

const ENTITY_STATES = [...beautySpeechRoute, ...beautySpeechPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        BeautySpeechMarineSuffixComponent,
        BeautySpeechMarineSuffixDetailComponent,
        BeautySpeechMarineSuffixUpdateComponent,
        BeautySpeechMarineSuffixDeleteDialogComponent,
        BeautySpeechMarineSuffixDeletePopupComponent
    ],
    providers: [ ConvertObjectDatesService ],
    entryComponents: [
        BeautySpeechMarineSuffixComponent,
        BeautySpeechMarineSuffixUpdateComponent,
        BeautySpeechMarineSuffixDeleteDialogComponent,
        BeautySpeechMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojBeautySpeechMarineSuffixModule {}
