import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    UsersRequestMarineSuffixComponent,
    UsersRequestMarineSuffixDetailComponent,
    UsersRequestMarineSuffixUpdateComponent,
    UsersRequestMarineSuffixDeletePopupComponent,
    UsersRequestMarineSuffixDeleteDialogComponent,
    usersRequestRoute,
    usersRequestPopupRoute
} from './';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

const ENTITY_STATES = [...usersRequestRoute, ...usersRequestPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        UsersRequestMarineSuffixComponent,
        UsersRequestMarineSuffixDetailComponent,
        UsersRequestMarineSuffixUpdateComponent,
        UsersRequestMarineSuffixDeleteDialogComponent,
        UsersRequestMarineSuffixDeletePopupComponent
    ],
    providers: [ ConvertObjectDatesService ],
    entryComponents: [
        UsersRequestMarineSuffixComponent,
        UsersRequestMarineSuffixUpdateComponent,
        UsersRequestMarineSuffixDeleteDialogComponent,
        UsersRequestMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojUsersRequestMarineSuffixModule {}
