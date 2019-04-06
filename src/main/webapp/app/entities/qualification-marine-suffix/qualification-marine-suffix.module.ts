import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    QualificationMarineSuffixComponent,
    QualificationMarineSuffixDetailComponent,
    QualificationMarineSuffixUpdateComponent,
    QualificationMarineSuffixDeletePopupComponent,
    QualificationMarineSuffixDeleteDialogComponent,
    qualificationRoute,
    qualificationPopupRoute
} from './';

const ENTITY_STATES = [...qualificationRoute, ...qualificationPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        QualificationMarineSuffixComponent,
        QualificationMarineSuffixDetailComponent,
        QualificationMarineSuffixUpdateComponent,
        QualificationMarineSuffixDeleteDialogComponent,
        QualificationMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        QualificationMarineSuffixComponent,
        QualificationMarineSuffixUpdateComponent,
        QualificationMarineSuffixDeleteDialogComponent,
        QualificationMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojQualificationMarineSuffixModule {}
