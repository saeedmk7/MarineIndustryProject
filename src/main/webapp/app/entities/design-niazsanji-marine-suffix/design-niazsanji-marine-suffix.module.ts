import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    DesignNiazsanjiMarineSuffixComponent,
    DesignNiazsanjiMarineSuffixDetailComponent,
    DesignNiazsanjiMarineSuffixUpdateComponent,
    DesignNiazsanjiMarineSuffixDeletePopupComponent,
    DesignNiazsanjiMarineSuffixDeleteDialogComponent,
    designNiazsanjiRoute,
    designNiazsanjiPopupRoute
} from './';
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";

const ENTITY_STATES = [...designNiazsanjiRoute, ...designNiazsanjiPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DesignNiazsanjiMarineSuffixComponent,
        DesignNiazsanjiMarineSuffixDetailComponent,
        DesignNiazsanjiMarineSuffixUpdateComponent,
        DesignNiazsanjiMarineSuffixDeleteDialogComponent,
        DesignNiazsanjiMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        DesignNiazsanjiMarineSuffixComponent,
        DesignNiazsanjiMarineSuffixUpdateComponent,
        DesignNiazsanjiMarineSuffixDeleteDialogComponent,
        DesignNiazsanjiMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    exports: [DesignNiazsanjiMarineSuffixUpdateComponent]
})
export class MarineindustryprojDesignNiazsanjiMarineSuffixModule {}
