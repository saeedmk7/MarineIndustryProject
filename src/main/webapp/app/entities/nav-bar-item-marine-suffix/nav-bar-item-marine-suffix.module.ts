import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    NavBarItemMarineSuffixComponent,
    NavBarItemMarineSuffixDetailComponent,
    NavBarItemMarineSuffixUpdateComponent,
    NavBarItemMarineSuffixDeletePopupComponent,
    NavBarItemMarineSuffixDeleteDialogComponent,
    navBarItemRoute,
    navBarItemPopupRoute
} from './';

const ENTITY_STATES = [...navBarItemRoute, ...navBarItemPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NavBarItemMarineSuffixComponent,
        NavBarItemMarineSuffixDetailComponent,
        NavBarItemMarineSuffixUpdateComponent,
        NavBarItemMarineSuffixDeleteDialogComponent,
        NavBarItemMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        NavBarItemMarineSuffixComponent,
        NavBarItemMarineSuffixUpdateComponent,
        NavBarItemMarineSuffixDeleteDialogComponent,
        NavBarItemMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojNavBarItemMarineSuffixModule {}
