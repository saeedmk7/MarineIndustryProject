import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    ConditionsOfParticipantMarineSuffixComponent,
    ConditionsOfParticipantMarineSuffixDetailComponent,
    ConditionsOfParticipantMarineSuffixUpdateComponent,
    ConditionsOfParticipantMarineSuffixDeletePopupComponent,
    ConditionsOfParticipantMarineSuffixDeleteDialogComponent,
    conditionsOfParticipantRoute,
    conditionsOfParticipantPopupRoute
} from './';

const ENTITY_STATES = [...conditionsOfParticipantRoute, ...conditionsOfParticipantPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ConditionsOfParticipantMarineSuffixComponent,
        ConditionsOfParticipantMarineSuffixDetailComponent,
        ConditionsOfParticipantMarineSuffixUpdateComponent,
        ConditionsOfParticipantMarineSuffixDeleteDialogComponent,
        ConditionsOfParticipantMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        ConditionsOfParticipantMarineSuffixComponent,
        ConditionsOfParticipantMarineSuffixUpdateComponent,
        ConditionsOfParticipantMarineSuffixDeleteDialogComponent,
        ConditionsOfParticipantMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojConditionsOfParticipantMarineSuffixModule {}
