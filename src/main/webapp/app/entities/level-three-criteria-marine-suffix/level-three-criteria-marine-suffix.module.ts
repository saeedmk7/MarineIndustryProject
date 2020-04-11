import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    LevelThreeCriteriaMarineSuffixComponent,
    LevelThreeCriteriaMarineSuffixDetailComponent,
    LevelThreeCriteriaMarineSuffixUpdateComponent,
    LevelThreeCriteriaMarineSuffixDeletePopupComponent,
    LevelThreeCriteriaMarineSuffixDeleteDialogComponent,
    levelThreeCriteriaRoute,
    levelThreeCriteriaPopupRoute
} from './';
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {InputsModule} from "@progress/kendo-angular-inputs";

const ENTITY_STATES = [...levelThreeCriteriaRoute, ...levelThreeCriteriaPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, BrowserAnimationsModule, InputsModule,RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        LevelThreeCriteriaMarineSuffixComponent,
        LevelThreeCriteriaMarineSuffixDetailComponent,
        LevelThreeCriteriaMarineSuffixUpdateComponent,
        LevelThreeCriteriaMarineSuffixDeleteDialogComponent,
        LevelThreeCriteriaMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        LevelThreeCriteriaMarineSuffixComponent,
        LevelThreeCriteriaMarineSuffixUpdateComponent,
        LevelThreeCriteriaMarineSuffixDeleteDialogComponent,
        LevelThreeCriteriaMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojLevelThreeCriteriaMarineSuffixModule {}
