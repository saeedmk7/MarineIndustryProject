import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    ToolsAndFacilityMarineSuffixComponent,
    ToolsAndFacilityMarineSuffixDetailComponent,
    ToolsAndFacilityMarineSuffixUpdateComponent,
    ToolsAndFacilityMarineSuffixDeletePopupComponent,
    ToolsAndFacilityMarineSuffixDeleteDialogComponent,
    toolsAndFacilityRoute,
    toolsAndFacilityPopupRoute
} from './';

const ENTITY_STATES = [...toolsAndFacilityRoute, ...toolsAndFacilityPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ToolsAndFacilityMarineSuffixComponent,
        ToolsAndFacilityMarineSuffixDetailComponent,
        ToolsAndFacilityMarineSuffixUpdateComponent,
        ToolsAndFacilityMarineSuffixDeleteDialogComponent,
        ToolsAndFacilityMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        ToolsAndFacilityMarineSuffixComponent,
        ToolsAndFacilityMarineSuffixUpdateComponent,
        ToolsAndFacilityMarineSuffixDeleteDialogComponent,
        ToolsAndFacilityMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojToolsAndFacilityMarineSuffixModule {}
