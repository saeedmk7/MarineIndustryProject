import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    PollMarineSuffixComponent,
    PollMarineSuffixDetailComponent,
    PollMarineSuffixUpdateComponent,
    PollMarineSuffixDeletePopupComponent,
    PollMarineSuffixDeleteDialogComponent,
    pollRoute,
    pollPopupRoute
} from './';

const ENTITY_STATES = [...pollRoute, ...pollPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PollMarineSuffixComponent,
        PollMarineSuffixDetailComponent,
        PollMarineSuffixUpdateComponent,
        PollMarineSuffixDeleteDialogComponent,
        PollMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        PollMarineSuffixComponent,
        PollMarineSuffixUpdateComponent,
        PollMarineSuffixDeleteDialogComponent,
        PollMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojPollMarineSuffixModule {}
