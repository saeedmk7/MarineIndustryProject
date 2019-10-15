import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    ForceRunningPercentMarineSuffixComponent,
    ForceRunningPercentMarineSuffixDetailComponent,
    ForceRunningPercentMarineSuffixUpdateComponent,
    ForceRunningPercentMarineSuffixDeletePopupComponent,
    ForceRunningPercentMarineSuffixDeleteDialogComponent,
    forceRunningPercentRoute,
    forceRunningPercentPopupRoute
} from './';

const ENTITY_STATES = [...forceRunningPercentRoute, ...forceRunningPercentPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ForceRunningPercentMarineSuffixComponent,
        ForceRunningPercentMarineSuffixDetailComponent,
        ForceRunningPercentMarineSuffixUpdateComponent,
        ForceRunningPercentMarineSuffixDeleteDialogComponent,
        ForceRunningPercentMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        ForceRunningPercentMarineSuffixComponent,
        ForceRunningPercentMarineSuffixUpdateComponent,
        ForceRunningPercentMarineSuffixDeleteDialogComponent,
        ForceRunningPercentMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojForceRunningPercentMarineSuffixModule {}
