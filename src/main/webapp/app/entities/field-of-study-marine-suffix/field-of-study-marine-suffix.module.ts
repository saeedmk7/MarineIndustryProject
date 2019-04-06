import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    FieldOfStudyMarineSuffixComponent,
    FieldOfStudyMarineSuffixDetailComponent,
    FieldOfStudyMarineSuffixUpdateComponent,
    FieldOfStudyMarineSuffixDeletePopupComponent,
    FieldOfStudyMarineSuffixDeleteDialogComponent,
    fieldOfStudyRoute,
    fieldOfStudyPopupRoute
} from './';

const ENTITY_STATES = [...fieldOfStudyRoute, ...fieldOfStudyPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FieldOfStudyMarineSuffixComponent,
        FieldOfStudyMarineSuffixDetailComponent,
        FieldOfStudyMarineSuffixUpdateComponent,
        FieldOfStudyMarineSuffixDeleteDialogComponent,
        FieldOfStudyMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        FieldOfStudyMarineSuffixComponent,
        FieldOfStudyMarineSuffixUpdateComponent,
        FieldOfStudyMarineSuffixDeleteDialogComponent,
        FieldOfStudyMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojFieldOfStudyMarineSuffixModule {}
