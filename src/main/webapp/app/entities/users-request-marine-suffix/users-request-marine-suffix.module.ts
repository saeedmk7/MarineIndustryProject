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
    usersRequestPopupRoute,
    UsersRequestMarineSuffixReferDialogComponent,
    UsersRequestMarineSuffixReferPopupComponent
} from './';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { NgSelectModule } from '@ng-select/ng-select';
import { FormsModule } from '@angular/forms';

const ENTITY_STATES = [...usersRequestRoute, ...usersRequestPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        UsersRequestMarineSuffixComponent,
        UsersRequestMarineSuffixDetailComponent,
        UsersRequestMarineSuffixUpdateComponent,
        UsersRequestMarineSuffixDeleteDialogComponent,
        UsersRequestMarineSuffixDeletePopupComponent,
        UsersRequestMarineSuffixReferDialogComponent,
        UsersRequestMarineSuffixReferPopupComponent
    ],
    providers: [ConvertObjectDatesService],
    entryComponents: [
        UsersRequestMarineSuffixComponent,
        UsersRequestMarineSuffixUpdateComponent,
        UsersRequestMarineSuffixDeleteDialogComponent,
        UsersRequestMarineSuffixDeletePopupComponent,
        UsersRequestMarineSuffixReferDialogComponent,
        UsersRequestMarineSuffixReferPopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojUsersRequestMarineSuffixModule {}
