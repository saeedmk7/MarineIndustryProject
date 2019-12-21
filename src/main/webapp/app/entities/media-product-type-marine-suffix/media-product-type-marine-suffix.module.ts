import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    MediaProductTypeMarineSuffixComponent,
    MediaProductTypeMarineSuffixDetailComponent,
    MediaProductTypeMarineSuffixUpdateComponent,
    MediaProductTypeMarineSuffixDeletePopupComponent,
    MediaProductTypeMarineSuffixDeleteDialogComponent,
    mediaProductTypeRoute,
    mediaProductTypePopupRoute
} from './';

const ENTITY_STATES = [...mediaProductTypeRoute, ...mediaProductTypePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MediaProductTypeMarineSuffixComponent,
        MediaProductTypeMarineSuffixDetailComponent,
        MediaProductTypeMarineSuffixUpdateComponent,
        MediaProductTypeMarineSuffixDeleteDialogComponent,
        MediaProductTypeMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        MediaProductTypeMarineSuffixComponent,
        MediaProductTypeMarineSuffixUpdateComponent,
        MediaProductTypeMarineSuffixDeleteDialogComponent,
        MediaProductTypeMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojMediaProductTypeMarineSuffixModule {}
