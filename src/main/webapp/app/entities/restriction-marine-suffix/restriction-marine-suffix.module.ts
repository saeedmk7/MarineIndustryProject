import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    RestrictionMarineSuffixComponent,
    RestrictionMarineSuffixDetailComponent,
    RestrictionMarineSuffixUpdateComponent,
    RestrictionMarineSuffixDeletePopupComponent,
    RestrictionMarineSuffixDeleteDialogComponent,
    restrictionRoute,
    restrictionPopupRoute
} from './';

const ENTITY_STATES = [...restrictionRoute, ...restrictionPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RestrictionMarineSuffixComponent,
        RestrictionMarineSuffixDetailComponent,
        RestrictionMarineSuffixUpdateComponent,
        RestrictionMarineSuffixDeleteDialogComponent,
        RestrictionMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        RestrictionMarineSuffixComponent,
        RestrictionMarineSuffixUpdateComponent,
        RestrictionMarineSuffixDeleteDialogComponent,
        RestrictionMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojRestrictionMarineSuffixModule {}
