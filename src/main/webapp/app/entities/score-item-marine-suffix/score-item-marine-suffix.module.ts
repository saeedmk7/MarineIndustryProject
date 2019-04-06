import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    ScoreItemMarineSuffixComponent,
    ScoreItemMarineSuffixDetailComponent,
    ScoreItemMarineSuffixUpdateComponent,
    ScoreItemMarineSuffixDeletePopupComponent,
    ScoreItemMarineSuffixDeleteDialogComponent,
    scoreItemRoute,
    scoreItemPopupRoute
} from './';

const ENTITY_STATES = [...scoreItemRoute, ...scoreItemPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ScoreItemMarineSuffixComponent,
        ScoreItemMarineSuffixDetailComponent,
        ScoreItemMarineSuffixUpdateComponent,
        ScoreItemMarineSuffixDeleteDialogComponent,
        ScoreItemMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        ScoreItemMarineSuffixComponent,
        ScoreItemMarineSuffixUpdateComponent,
        ScoreItemMarineSuffixDeleteDialogComponent,
        ScoreItemMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojScoreItemMarineSuffixModule {}
