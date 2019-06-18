import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    PersonEducationalRecordsMarineSuffixComponent,
    PersonEducationalRecordsMarineSuffixRoute,
    PersonEducationalRecordsMarineSuffixService
} from './';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";

const ENTITY_STATES = [PersonEducationalRecordsMarineSuffixRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PersonEducationalRecordsMarineSuffixComponent
    ],
    providers: [ ConvertObjectDatesService ],
    entryComponents: [
        PersonEducationalRecordsMarineSuffixComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojPersonEducationalRecordsMarineSuffixModule {}
