import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    SoldierMarineSuffixComponent,
    SoldierMarineSuffixDetailComponent,
    SoldierMarineSuffixUpdateComponent,
    SoldierMarineSuffixDeletePopupComponent,
    SoldierMarineSuffixDeleteDialogComponent,
    soldierRoute,
    soldierPopupRoute
} from './';
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {InputsModule} from "@progress/kendo-angular-inputs";

const ENTITY_STATES = [...soldierRoute, ...soldierPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, BrowserAnimationsModule, InputsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SoldierMarineSuffixComponent,
        SoldierMarineSuffixDetailComponent,
        SoldierMarineSuffixUpdateComponent,
        SoldierMarineSuffixDeleteDialogComponent,
        SoldierMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        SoldierMarineSuffixComponent,
        SoldierMarineSuffixUpdateComponent,
        SoldierMarineSuffixDeleteDialogComponent,
        SoldierMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojSoldierMarineSuffixModule {}
