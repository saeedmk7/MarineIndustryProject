import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    OrganizationChartAuthorityMarineSuffixComponent,
    OrganizationChartAuthorityMarineSuffixDetailComponent,
    OrganizationChartAuthorityMarineSuffixUpdateComponent,
    OrganizationChartAuthorityMarineSuffixDeletePopupComponent,
    OrganizationChartAuthorityMarineSuffixDeleteDialogComponent,
    organizationChartAuthorityRoute,
    organizationChartAuthorityPopupRoute
} from './';

const ENTITY_STATES = [...organizationChartAuthorityRoute, ...organizationChartAuthorityPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        OrganizationChartAuthorityMarineSuffixComponent,
        OrganizationChartAuthorityMarineSuffixDetailComponent,
        OrganizationChartAuthorityMarineSuffixUpdateComponent,
        OrganizationChartAuthorityMarineSuffixDeleteDialogComponent,
        OrganizationChartAuthorityMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        OrganizationChartAuthorityMarineSuffixComponent,
        OrganizationChartAuthorityMarineSuffixUpdateComponent,
        OrganizationChartAuthorityMarineSuffixDeleteDialogComponent,
        OrganizationChartAuthorityMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojOrganizationChartAuthorityMarineSuffixModule {}
