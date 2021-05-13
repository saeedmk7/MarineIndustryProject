import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    ApplicationProcessMarineSuffixComponent,
    ApplicationProcessMarineSuffixDetailComponent,
    ApplicationProcessMarineSuffixUpdateComponent,
    ApplicationProcessMarineSuffixDeletePopupComponent,
    ApplicationProcessMarineSuffixDeleteDialogComponent,
    applicationProcessRoute,
    applicationProcessPopupRoute,
    ApplicationProcessMarineSuffixCommentDialogComponent,
    ApplicationProcessMarineSuffixCommentPopupComponent
} from './';
import { NgSelectModule } from '@ng-select/ng-select';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { InputsModule } from '@progress/kendo-angular-inputs';

const ENTITY_STATES = [...applicationProcessRoute, ...applicationProcessPopupRoute];

@NgModule({
    imports: [
        MarineindustryprojSharedModule,
        NgSelectModule,
        FormsModule,
        BrowserAnimationsModule,
        InputsModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ApplicationProcessMarineSuffixComponent,
        ApplicationProcessMarineSuffixDetailComponent,
        ApplicationProcessMarineSuffixUpdateComponent,
        ApplicationProcessMarineSuffixDeleteDialogComponent,
        ApplicationProcessMarineSuffixDeletePopupComponent,
        ApplicationProcessMarineSuffixCommentDialogComponent,
        ApplicationProcessMarineSuffixCommentPopupComponent
    ],
    entryComponents: [
        ApplicationProcessMarineSuffixComponent,
        ApplicationProcessMarineSuffixUpdateComponent,
        ApplicationProcessMarineSuffixDeleteDialogComponent,
        ApplicationProcessMarineSuffixDeletePopupComponent,
        ApplicationProcessMarineSuffixCommentDialogComponent,
        ApplicationProcessMarineSuffixCommentPopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojApplicationProcessMarineSuffixModule {}
