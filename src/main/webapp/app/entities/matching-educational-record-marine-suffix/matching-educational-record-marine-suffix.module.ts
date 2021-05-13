import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    MatchingEducationalRecordMarineSuffixComponent,
    MatchingEducationalRecordMarineSuffixDetailComponent,
    MatchingEducationalRecordMarineSuffixUpdateComponent,
    MatchingEducationalRecordMarineSuffixDeletePopupComponent,
    MatchingEducationalRecordMarineSuffixDeleteDialogComponent,
    matchingEducationalRecordRoute,
    matchingEducationalRecordPopupRoute,
    MatchingEducationalRecordMarineSuffixCommentDialogComponent,
    MatchingEducationalRecordMarineSuffixCommentPopupComponent
} from './';
import { NgSelectModule } from '@ng-select/ng-select';
import { FormsModule } from '@angular/forms';

const ENTITY_STATES = [...matchingEducationalRecordRoute, ...matchingEducationalRecordPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MatchingEducationalRecordMarineSuffixComponent,
        MatchingEducationalRecordMarineSuffixDetailComponent,
        MatchingEducationalRecordMarineSuffixUpdateComponent,
        MatchingEducationalRecordMarineSuffixDeleteDialogComponent,
        MatchingEducationalRecordMarineSuffixDeletePopupComponent,
        MatchingEducationalRecordMarineSuffixCommentDialogComponent,
        MatchingEducationalRecordMarineSuffixCommentPopupComponent
    ],
    entryComponents: [
        MatchingEducationalRecordMarineSuffixComponent,
        MatchingEducationalRecordMarineSuffixUpdateComponent,
        MatchingEducationalRecordMarineSuffixDeleteDialogComponent,
        MatchingEducationalRecordMarineSuffixDeletePopupComponent,
        MatchingEducationalRecordMarineSuffixCommentDialogComponent,
        MatchingEducationalRecordMarineSuffixCommentPopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojMatchingEducationalRecordMarineSuffixModule {}
