import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    DocumentMarineSuffixComponent,
    DocumentMarineSuffixDetailComponent,
    DocumentMarineSuffixUpdateComponent,
    DocumentMarineSuffixDeletePopupComponent,
    DocumentMarineSuffixDeleteDialogComponent,
    documentRoute,
    documentPopupRoute
} from './';

const ENTITY_STATES = [...documentRoute, ...documentPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DocumentMarineSuffixComponent,
        DocumentMarineSuffixDetailComponent,
        DocumentMarineSuffixUpdateComponent,
        DocumentMarineSuffixDeleteDialogComponent,
        DocumentMarineSuffixDeletePopupComponent,

    ],
    entryComponents: [
        DocumentMarineSuffixComponent,
        DocumentMarineSuffixUpdateComponent,
        DocumentMarineSuffixDeleteDialogComponent,
        DocumentMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojDocumentMarineSuffixModule {}
