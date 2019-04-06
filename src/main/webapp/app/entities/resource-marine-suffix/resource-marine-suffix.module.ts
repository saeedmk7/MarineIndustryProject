import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    ResourceMarineSuffixComponent,
    ResourceMarineSuffixDetailComponent,
    ResourceMarineSuffixUpdateComponent,
    ResourceMarineSuffixDeletePopupComponent,
    ResourceMarineSuffixDeleteDialogComponent,
    resourceRoute,
    resourcePopupRoute
} from './';

const ENTITY_STATES = [...resourceRoute, ...resourcePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ResourceMarineSuffixComponent,
        ResourceMarineSuffixDetailComponent,
        ResourceMarineSuffixUpdateComponent,
        ResourceMarineSuffixDeleteDialogComponent,
        ResourceMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        ResourceMarineSuffixComponent,
        ResourceMarineSuffixUpdateComponent,
        ResourceMarineSuffixDeleteDialogComponent,
        ResourceMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojResourceMarineSuffixModule {}
