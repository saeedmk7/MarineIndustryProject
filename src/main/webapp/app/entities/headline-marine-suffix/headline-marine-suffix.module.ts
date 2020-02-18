import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    HeadlineMarineSuffixComponent,
    HeadlineMarineSuffixDetailComponent,
    HeadlineMarineSuffixUpdateComponent,
    HeadlineMarineSuffixDeletePopupComponent,
    HeadlineMarineSuffixDeleteDialogComponent,
    headlineRoute,
    headlinePopupRoute
} from './';

const ENTITY_STATES = [...headlineRoute, ...headlinePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        HeadlineMarineSuffixComponent,
        HeadlineMarineSuffixDetailComponent,
        HeadlineMarineSuffixUpdateComponent,
        HeadlineMarineSuffixDeleteDialogComponent,
        HeadlineMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        HeadlineMarineSuffixComponent,
        HeadlineMarineSuffixUpdateComponent,
        HeadlineMarineSuffixDeleteDialogComponent,
        HeadlineMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojHeadlineMarineSuffixModule {}
