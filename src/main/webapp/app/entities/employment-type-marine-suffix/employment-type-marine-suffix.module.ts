import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    EmploymentTypeMarineSuffixComponent,
    EmploymentTypeMarineSuffixDetailComponent,
    EmploymentTypeMarineSuffixUpdateComponent,
    EmploymentTypeMarineSuffixDeletePopupComponent,
    EmploymentTypeMarineSuffixDeleteDialogComponent,
    employmentTypeRoute,
    employmentTypePopupRoute
} from './';

const ENTITY_STATES = [...employmentTypeRoute, ...employmentTypePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EmploymentTypeMarineSuffixComponent,
        EmploymentTypeMarineSuffixDetailComponent,
        EmploymentTypeMarineSuffixUpdateComponent,
        EmploymentTypeMarineSuffixDeleteDialogComponent,
        EmploymentTypeMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        EmploymentTypeMarineSuffixComponent,
        EmploymentTypeMarineSuffixUpdateComponent,
        EmploymentTypeMarineSuffixDeleteDialogComponent,
        EmploymentTypeMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojEmploymentTypeMarineSuffixModule {}
