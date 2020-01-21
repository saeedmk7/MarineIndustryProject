import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    NiazsanjiIntegrationMarineSuffixComponent,
    NiazsanjiIntegrationMarineSuffixDetailComponent,
    NiazsanjiIntegrationMarineSuffixUpdateComponent,
    NiazsanjiIntegrationMarineSuffixDeletePopupComponent,
    NiazsanjiIntegrationMarineSuffixDeleteDialogComponent,
    niazsanjiIntegrationRoute,
    niazsanjiIntegrationPopupRoute
} from './';

const ENTITY_STATES = [...niazsanjiIntegrationRoute, ...niazsanjiIntegrationPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NiazsanjiIntegrationMarineSuffixComponent,
        NiazsanjiIntegrationMarineSuffixDetailComponent,
        NiazsanjiIntegrationMarineSuffixUpdateComponent,
        NiazsanjiIntegrationMarineSuffixDeleteDialogComponent,
        NiazsanjiIntegrationMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        NiazsanjiIntegrationMarineSuffixComponent,
        NiazsanjiIntegrationMarineSuffixUpdateComponent,
        NiazsanjiIntegrationMarineSuffixDeleteDialogComponent,
        NiazsanjiIntegrationMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojNiazsanjiIntegrationMarineSuffixModule {}
