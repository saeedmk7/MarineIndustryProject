import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    NavBarItemAuthorityMarineSuffixComponent,
    NavBarItemAuthorityMarineSuffixDetailComponent,
    NavBarItemAuthorityMarineSuffixUpdateComponent,
    NavBarItemAuthorityMarineSuffixDeletePopupComponent,
    NavBarItemAuthorityMarineSuffixDeleteDialogComponent,
    navBarItemAuthorityRoute,
    navBarItemAuthorityPopupRoute
} from './';
import {TreeModule} from "angular-tree-component";

const ENTITY_STATES = [...navBarItemAuthorityRoute, ...navBarItemAuthorityPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, TreeModule.forRoot(), RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NavBarItemAuthorityMarineSuffixComponent,
        NavBarItemAuthorityMarineSuffixDetailComponent,
        NavBarItemAuthorityMarineSuffixUpdateComponent,
        NavBarItemAuthorityMarineSuffixDeleteDialogComponent,
        NavBarItemAuthorityMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        NavBarItemAuthorityMarineSuffixComponent,
        NavBarItemAuthorityMarineSuffixUpdateComponent,
        NavBarItemAuthorityMarineSuffixDeleteDialogComponent,
        NavBarItemAuthorityMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojNavBarItemAuthorityMarineSuffixModule {}
