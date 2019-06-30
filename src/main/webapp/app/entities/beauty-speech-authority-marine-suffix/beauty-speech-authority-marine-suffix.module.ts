import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    BeautySpeechAuthorityMarineSuffixComponent,
    BeautySpeechAuthorityMarineSuffixDetailComponent,
    BeautySpeechAuthorityMarineSuffixUpdateComponent,
    BeautySpeechAuthorityMarineSuffixDeletePopupComponent,
    BeautySpeechAuthorityMarineSuffixDeleteDialogComponent,
    beautySpeechAuthorityRoute,
    beautySpeechAuthorityPopupRoute
} from './';

const ENTITY_STATES = [...beautySpeechAuthorityRoute, ...beautySpeechAuthorityPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        BeautySpeechAuthorityMarineSuffixComponent,
        BeautySpeechAuthorityMarineSuffixDetailComponent,
        BeautySpeechAuthorityMarineSuffixUpdateComponent,
        BeautySpeechAuthorityMarineSuffixDeleteDialogComponent,
        BeautySpeechAuthorityMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        BeautySpeechAuthorityMarineSuffixComponent,
        BeautySpeechAuthorityMarineSuffixUpdateComponent,
        BeautySpeechAuthorityMarineSuffixDeleteDialogComponent,
        BeautySpeechAuthorityMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojBeautySpeechAuthorityMarineSuffixModule {}
