import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    JobMarineSuffixComponent,
    JobMarineSuffixDetailComponent,
    JobMarineSuffixUpdateComponent,
    JobMarineSuffixDeletePopupComponent,
    JobMarineSuffixDeleteDialogComponent,
    jobRoute,
    jobPopupRoute
} from './';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {FormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {InputsModule} from "@progress/kendo-angular-inputs";

const ENTITY_STATES = [...jobRoute, ...jobPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, FormsModule, BrowserAnimationsModule, InputsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        JobMarineSuffixComponent,
        JobMarineSuffixDetailComponent,
        JobMarineSuffixUpdateComponent,
        JobMarineSuffixDeleteDialogComponent,
        JobMarineSuffixDeletePopupComponent
    ],
    providers: [ ConvertObjectDatesService ],
    entryComponents: [
        JobMarineSuffixComponent,
        JobMarineSuffixUpdateComponent,
        JobMarineSuffixDeleteDialogComponent,
        JobMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojJobMarineSuffixModule {}
