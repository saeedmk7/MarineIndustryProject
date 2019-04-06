import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    OrganizationMarineSuffixComponent,
    OrganizationMarineSuffixDetailComponent,
    OrganizationMarineSuffixUpdateComponent,
    OrganizationMarineSuffixDeletePopupComponent,
    OrganizationMarineSuffixDeleteDialogComponent,
    organizationRoute,
    organizationPopupRoute
} from './';

const ENTITY_STATES = [...organizationRoute, ...organizationPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        OrganizationMarineSuffixComponent,
        OrganizationMarineSuffixDetailComponent,
        OrganizationMarineSuffixUpdateComponent,
        OrganizationMarineSuffixDeleteDialogComponent,
        OrganizationMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        OrganizationMarineSuffixComponent,
        OrganizationMarineSuffixUpdateComponent,
        OrganizationMarineSuffixDeleteDialogComponent,
        OrganizationMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojOrganizationMarineSuffixModule {}
