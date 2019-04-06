import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    SecurityLevelMarineSuffixComponent,
    SecurityLevelMarineSuffixDetailComponent,
    SecurityLevelMarineSuffixUpdateComponent,
    SecurityLevelMarineSuffixDeletePopupComponent,
    SecurityLevelMarineSuffixDeleteDialogComponent,
    securityLevelRoute,
    securityLevelPopupRoute
} from './';

const ENTITY_STATES = [...securityLevelRoute, ...securityLevelPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SecurityLevelMarineSuffixComponent,
        SecurityLevelMarineSuffixDetailComponent,
        SecurityLevelMarineSuffixUpdateComponent,
        SecurityLevelMarineSuffixDeleteDialogComponent,
        SecurityLevelMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        SecurityLevelMarineSuffixComponent,
        SecurityLevelMarineSuffixUpdateComponent,
        SecurityLevelMarineSuffixDeleteDialogComponent,
        SecurityLevelMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojSecurityLevelMarineSuffixModule {}
