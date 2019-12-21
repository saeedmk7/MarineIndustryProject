import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    PrintPersonEducationalRecordsMarineSuffixComponent,
    PrintPersonEducationalRecordsMarineSuffixRoute
} from './';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {FormsModule} from "@angular/forms";

const ENTITY_STATES = [PrintPersonEducationalRecordsMarineSuffixRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, FormsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PrintPersonEducationalRecordsMarineSuffixComponent
    ],
    providers: [ ConvertObjectDatesService ],
    entryComponents: [
        PrintPersonEducationalRecordsMarineSuffixComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojPrintPersonEducationalRecordsMarineSuffixModule {}
