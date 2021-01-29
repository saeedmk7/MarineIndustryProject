import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    JobChangeMarineSuffixComponent,
    JobChangeMarineSuffixDetailComponent,
    JobChangeMarineSuffixUpdateComponent,
    JobChangeMarineSuffixDeletePopupComponent,
    JobChangeMarineSuffixDeleteDialogComponent,
    jobChangeRoute,
    jobChangePopupRoute
} from './';

const ENTITY_STATES = [...jobChangeRoute, ...jobChangePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        JobChangeMarineSuffixComponent,
        JobChangeMarineSuffixDetailComponent,
        JobChangeMarineSuffixUpdateComponent,
        JobChangeMarineSuffixDeleteDialogComponent,
        JobChangeMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        JobChangeMarineSuffixComponent,
        JobChangeMarineSuffixUpdateComponent,
        JobChangeMarineSuffixDeleteDialogComponent,
        JobChangeMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojJobChangeMarineSuffixModule {}
