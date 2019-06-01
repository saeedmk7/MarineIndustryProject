import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    JamHelpAuthorityMarineSuffixComponent,
    JamHelpAuthorityMarineSuffixDetailComponent,
    JamHelpAuthorityMarineSuffixUpdateComponent,
    JamHelpAuthorityMarineSuffixDeletePopupComponent,
    JamHelpAuthorityMarineSuffixDeleteDialogComponent,
    jamHelpAuthorityRoute,
    jamHelpAuthorityPopupRoute
} from './';

const ENTITY_STATES = [...jamHelpAuthorityRoute, ...jamHelpAuthorityPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        JamHelpAuthorityMarineSuffixComponent,
        JamHelpAuthorityMarineSuffixDetailComponent,
        JamHelpAuthorityMarineSuffixUpdateComponent,
        JamHelpAuthorityMarineSuffixDeleteDialogComponent,
        JamHelpAuthorityMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        JamHelpAuthorityMarineSuffixComponent,
        JamHelpAuthorityMarineSuffixUpdateComponent,
        JamHelpAuthorityMarineSuffixDeleteDialogComponent,
        JamHelpAuthorityMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojJamHelpAuthorityMarineSuffixModule {}
