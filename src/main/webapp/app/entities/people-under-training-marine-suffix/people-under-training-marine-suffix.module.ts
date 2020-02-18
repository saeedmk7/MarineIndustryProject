import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    PeopleUnderTrainingMarineSuffixComponent,
    PeopleUnderTrainingMarineSuffixDetailComponent,
    PeopleUnderTrainingMarineSuffixUpdateComponent,
    PeopleUnderTrainingMarineSuffixDeletePopupComponent,
    PeopleUnderTrainingMarineSuffixDeleteDialogComponent,
    peopleUnderTrainingRoute,
    peopleUnderTrainingPopupRoute
} from './';

const ENTITY_STATES = [...peopleUnderTrainingRoute, ...peopleUnderTrainingPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PeopleUnderTrainingMarineSuffixComponent,
        PeopleUnderTrainingMarineSuffixDetailComponent,
        PeopleUnderTrainingMarineSuffixUpdateComponent,
        PeopleUnderTrainingMarineSuffixDeleteDialogComponent,
        PeopleUnderTrainingMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        PeopleUnderTrainingMarineSuffixComponent,
        PeopleUnderTrainingMarineSuffixUpdateComponent,
        PeopleUnderTrainingMarineSuffixDeleteDialogComponent,
        PeopleUnderTrainingMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojPeopleUnderTrainingMarineSuffixModule {}
