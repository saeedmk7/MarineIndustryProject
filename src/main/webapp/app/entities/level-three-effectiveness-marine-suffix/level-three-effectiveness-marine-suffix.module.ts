import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    LevelThreeEffectivenessMarineSuffixComponent,
    LevelThreeEffectivenessMarineSuffixDetailComponent,
    LevelThreeEffectivenessMarineSuffixUpdateComponent,
    LevelThreeEffectivenessMarineSuffixDeletePopupComponent,
    LevelThreeEffectivenessMarineSuffixDeleteDialogComponent,
    levelThreeEffectivenessRoute,
    levelThreeEffectivenessPopupRoute
} from './';
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {InputsModule} from "@progress/kendo-angular-inputs";

const ENTITY_STATES = [...levelThreeEffectivenessRoute, ...levelThreeEffectivenessPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, BrowserAnimationsModule, InputsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        LevelThreeEffectivenessMarineSuffixComponent,
        LevelThreeEffectivenessMarineSuffixDetailComponent,
        LevelThreeEffectivenessMarineSuffixUpdateComponent,
        LevelThreeEffectivenessMarineSuffixDeleteDialogComponent,
        LevelThreeEffectivenessMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        LevelThreeEffectivenessMarineSuffixComponent,
        LevelThreeEffectivenessMarineSuffixUpdateComponent,
        LevelThreeEffectivenessMarineSuffixDeleteDialogComponent,
        LevelThreeEffectivenessMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojLevelThreeEffectivenessMarineSuffixModule {}
