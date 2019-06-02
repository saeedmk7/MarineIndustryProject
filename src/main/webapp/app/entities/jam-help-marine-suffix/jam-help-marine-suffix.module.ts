import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    JamHelpMarineSuffixComponent,
    JamHelpMarineSuffixDetailComponent,
    JamHelpMarineSuffixUpdateComponent,
    JamHelpMarineSuffixDeletePopupComponent,
    JamHelpMarineSuffixDeleteDialogComponent,
    jamHelpRoute,
    jamHelpPopupRoute
} from './';
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";

const ENTITY_STATES = [...jamHelpRoute, ...jamHelpPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        JamHelpMarineSuffixComponent,
        JamHelpMarineSuffixDetailComponent,
        JamHelpMarineSuffixUpdateComponent,
        JamHelpMarineSuffixDeleteDialogComponent,
        JamHelpMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        JamHelpMarineSuffixComponent,
        JamHelpMarineSuffixUpdateComponent,
        JamHelpMarineSuffixDeleteDialogComponent,
        JamHelpMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojJamHelpMarineSuffixModule {}
