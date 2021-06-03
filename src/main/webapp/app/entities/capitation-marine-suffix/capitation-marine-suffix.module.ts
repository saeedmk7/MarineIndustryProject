import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    CapitationMarineSuffixComponent,
    CapitationMarineSuffixDetailComponent,
    CapitationMarineSuffixUpdateComponent,
    CapitationMarineSuffixDeletePopupComponent,
    CapitationMarineSuffixDeleteDialogComponent,
    capitationRoute,
    capitationPopupRoute,
    CapitationReportMarineSuffixComponent
} from './';

const ENTITY_STATES = [...capitationRoute, ...capitationPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CapitationMarineSuffixComponent,
        CapitationMarineSuffixDetailComponent,
        CapitationMarineSuffixUpdateComponent,
        CapitationMarineSuffixDeleteDialogComponent,
        CapitationMarineSuffixDeletePopupComponent,
        CapitationReportMarineSuffixComponent
    ],
    entryComponents: [
        CapitationMarineSuffixComponent,
        CapitationMarineSuffixUpdateComponent,
        CapitationMarineSuffixDeleteDialogComponent,
        CapitationMarineSuffixDeletePopupComponent,
        CapitationReportMarineSuffixComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojCapitationMarineSuffixModule {}
