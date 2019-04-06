import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    TeachTechniqueMarineSuffixComponent,
    TeachTechniqueMarineSuffixDetailComponent,
    TeachTechniqueMarineSuffixUpdateComponent,
    TeachTechniqueMarineSuffixDeletePopupComponent,
    TeachTechniqueMarineSuffixDeleteDialogComponent,
    teachTechniqueRoute,
    teachTechniquePopupRoute
} from './';

const ENTITY_STATES = [...teachTechniqueRoute, ...teachTechniquePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TeachTechniqueMarineSuffixComponent,
        TeachTechniqueMarineSuffixDetailComponent,
        TeachTechniqueMarineSuffixUpdateComponent,
        TeachTechniqueMarineSuffixDeleteDialogComponent,
        TeachTechniqueMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        TeachTechniqueMarineSuffixComponent,
        TeachTechniqueMarineSuffixUpdateComponent,
        TeachTechniqueMarineSuffixDeleteDialogComponent,
        TeachTechniqueMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojTeachTechniqueMarineSuffixModule {}
