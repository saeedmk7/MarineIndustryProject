import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    RequestOrganizationNiazsanjiMarineSuffixComponent,
    RequestOrganizationNiazsanjiMarineSuffixDetailComponent,
    RequestOrganizationNiazsanjiMarineSuffixUpdateComponent,
    RequestOrganizationNiazsanjiMarineSuffixDeletePopupComponent,
    RequestOrganizationNiazsanjiMarineSuffixDeleteDialogComponent,
    requestOrganizationNiazsanjiRoute,
    requestOrganizationNiazsanjiPopupRoute,
    RequestOrganizationNiazsanjiMarineSuffixCommentDialogComponent,
    RequestOrganizationNiazsanjiMarineSuffixCommentPopupComponent
} from './';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";

const ENTITY_STATES = [...requestOrganizationNiazsanjiRoute, ...requestOrganizationNiazsanjiPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RequestOrganizationNiazsanjiMarineSuffixComponent,
        RequestOrganizationNiazsanjiMarineSuffixDetailComponent,
        RequestOrganizationNiazsanjiMarineSuffixUpdateComponent,
        RequestOrganizationNiazsanjiMarineSuffixDeleteDialogComponent,
        RequestOrganizationNiazsanjiMarineSuffixDeletePopupComponent,
        RequestOrganizationNiazsanjiMarineSuffixCommentDialogComponent,
        RequestOrganizationNiazsanjiMarineSuffixCommentPopupComponent
    ],
    providers: [ ConvertObjectDatesService ],
    entryComponents: [
        RequestOrganizationNiazsanjiMarineSuffixComponent,
        RequestOrganizationNiazsanjiMarineSuffixUpdateComponent,
        RequestOrganizationNiazsanjiMarineSuffixDeleteDialogComponent,
        RequestOrganizationNiazsanjiMarineSuffixDeletePopupComponent,
        RequestOrganizationNiazsanjiMarineSuffixCommentDialogComponent,
        RequestOrganizationNiazsanjiMarineSuffixCommentPopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojRequestOrganizationNiazsanjiMarineSuffixModule {}
