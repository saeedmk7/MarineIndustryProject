import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    AuthorityComponent,
    AuthorityUpdateComponent,
    AuthorityDeletePopupComponent,
    AuthorityDeleteDialogComponent,
    authorityRoute,
    authorityPopupRoute
} from './';

const ENTITY_STATES = [...authorityRoute, ...authorityPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AuthorityComponent,
        AuthorityUpdateComponent,
        AuthorityDeleteDialogComponent,
        AuthorityDeletePopupComponent
    ],
    entryComponents: [
        AuthorityComponent,
        AuthorityUpdateComponent,
        AuthorityDeleteDialogComponent,
        AuthorityDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojAuthorityModule {}
