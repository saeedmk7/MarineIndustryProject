import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    PollScoreMarineSuffixComponent,
    PollScoreMarineSuffixDetailComponent,
    PollScoreMarineSuffixUpdateComponent,
    PollScoreMarineSuffixDeletePopupComponent,
    PollScoreMarineSuffixDeleteDialogComponent,
    pollScoreRoute,
    pollScorePopupRoute
} from './';

const ENTITY_STATES = [...pollScoreRoute, ...pollScorePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PollScoreMarineSuffixComponent,
        PollScoreMarineSuffixDetailComponent,
        PollScoreMarineSuffixUpdateComponent,
        PollScoreMarineSuffixDeleteDialogComponent,
        PollScoreMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        PollScoreMarineSuffixComponent,
        PollScoreMarineSuffixUpdateComponent,
        PollScoreMarineSuffixDeleteDialogComponent,
        PollScoreMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojPollScoreMarineSuffixModule {}
