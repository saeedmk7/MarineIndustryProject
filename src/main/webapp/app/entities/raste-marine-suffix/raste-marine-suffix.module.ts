import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    RasteMarineSuffixComponent,
    RasteMarineSuffixDetailComponent,
    RasteMarineSuffixUpdateComponent,
    RasteMarineSuffixDeletePopupComponent,
    RasteMarineSuffixDeleteDialogComponent,
    rasteRoute,
    rastePopupRoute
} from './';

const ENTITY_STATES = [...rasteRoute, ...rastePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RasteMarineSuffixComponent,
        RasteMarineSuffixDetailComponent,
        RasteMarineSuffixUpdateComponent,
        RasteMarineSuffixDeleteDialogComponent,
        RasteMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        RasteMarineSuffixComponent,
        RasteMarineSuffixUpdateComponent,
        RasteMarineSuffixDeleteDialogComponent,
        RasteMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojRasteMarineSuffixModule {}
