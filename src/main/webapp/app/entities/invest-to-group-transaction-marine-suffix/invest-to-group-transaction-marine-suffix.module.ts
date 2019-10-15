import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    InvestToGroupTransactionMarineSuffixComponent,
    InvestToGroupTransactionMarineSuffixDetailComponent,
    InvestToGroupTransactionMarineSuffixUpdateComponent,
    InvestToGroupTransactionMarineSuffixDeletePopupComponent,
    InvestToGroupTransactionMarineSuffixDeleteDialogComponent,
    investToGroupTransactionRoute,
    investToGroupTransactionPopupRoute
} from './';
import {NgSelectModule} from "@ng-select/ng-select";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {InputsModule} from "@progress/kendo-angular-inputs";

const ENTITY_STATES = [...investToGroupTransactionRoute, ...investToGroupTransactionPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, BrowserAnimationsModule, InputsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        InvestToGroupTransactionMarineSuffixComponent,
        InvestToGroupTransactionMarineSuffixDetailComponent,
        InvestToGroupTransactionMarineSuffixUpdateComponent,
        InvestToGroupTransactionMarineSuffixDeleteDialogComponent,
        InvestToGroupTransactionMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        InvestToGroupTransactionMarineSuffixComponent,
        InvestToGroupTransactionMarineSuffixUpdateComponent,
        InvestToGroupTransactionMarineSuffixDeleteDialogComponent,
        InvestToGroupTransactionMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojInvestToGroupTransactionMarineSuffixModule {}
