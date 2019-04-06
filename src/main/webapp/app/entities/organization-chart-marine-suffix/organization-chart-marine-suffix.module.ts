import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TreeModule } from 'angular-tree-component';
import { MarineindustryprojSharedModule } from 'app/shared';
import {
    OrganizationChartMarineSuffixComponent,
    OrganizationChartMarineSuffixDetailComponent,
    OrganizationChartMarineSuffixUpdateComponent,
    OrganizationChartMarineSuffixDeletePopupComponent,
    OrganizationChartMarineSuffixDeleteDialogComponent,
    organizationChartRoute,
    organizationChartPopupRoute
} from './';
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";

const ENTITY_STATES = [...organizationChartRoute, ...organizationChartPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, TreeModule.forRoot(), RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        OrganizationChartMarineSuffixComponent,
        OrganizationChartMarineSuffixDetailComponent,
        OrganizationChartMarineSuffixUpdateComponent,
        OrganizationChartMarineSuffixDeleteDialogComponent,
        OrganizationChartMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        OrganizationChartMarineSuffixComponent,
        OrganizationChartMarineSuffixUpdateComponent,
        OrganizationChartMarineSuffixDeleteDialogComponent,
        OrganizationChartMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojOrganizationChartMarineSuffixModule {}
