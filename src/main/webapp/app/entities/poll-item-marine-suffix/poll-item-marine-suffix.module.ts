import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    PollItemMarineSuffixComponent,
    PollItemMarineSuffixDetailComponent,
    PollItemMarineSuffixUpdateComponent,
    PollItemMarineSuffixDeletePopupComponent,
    PollItemMarineSuffixDeleteDialogComponent,
    pollItemRoute,
    pollItemPopupRoute
} from './';

const ENTITY_STATES = [...pollItemRoute, ...pollItemPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PollItemMarineSuffixComponent,
        PollItemMarineSuffixDetailComponent,
        PollItemMarineSuffixUpdateComponent,
        PollItemMarineSuffixDeleteDialogComponent,
        PollItemMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        PollItemMarineSuffixComponent,
        PollItemMarineSuffixUpdateComponent,
        PollItemMarineSuffixDeleteDialogComponent,
        PollItemMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojPollItemMarineSuffixModule {}
