import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    ResearchRecordMarineSuffixComponent,
    ResearchRecordMarineSuffixDetailComponent,
    ResearchRecordMarineSuffixUpdateComponent,
    ResearchRecordMarineSuffixDeletePopupComponent,
    ResearchRecordMarineSuffixDeleteDialogComponent,
    researchRecordRoute,
    researchRecordPopupRoute
} from './';

const ENTITY_STATES = [...researchRecordRoute, ...researchRecordPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ResearchRecordMarineSuffixComponent,
        ResearchRecordMarineSuffixDetailComponent,
        ResearchRecordMarineSuffixUpdateComponent,
        ResearchRecordMarineSuffixDeleteDialogComponent,
        ResearchRecordMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        ResearchRecordMarineSuffixComponent,
        ResearchRecordMarineSuffixUpdateComponent,
        ResearchRecordMarineSuffixDeleteDialogComponent,
        ResearchRecordMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojResearchRecordMarineSuffixModule {}
