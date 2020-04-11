import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    NiazsanjiPersonGradeMarineSuffixComponent,
    NiazsanjiPersonGradeMarineSuffixDetailComponent,
    NiazsanjiPersonGradeMarineSuffixUpdateComponent,
    NiazsanjiPersonGradeMarineSuffixDeletePopupComponent,
    NiazsanjiPersonGradeMarineSuffixDeleteDialogComponent,
    niazsanjiPersonGradeRoute,
    niazsanjiPersonGradePopupRoute
} from './';
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {InputsModule} from "@progress/kendo-angular-inputs";

const ENTITY_STATES = [...niazsanjiPersonGradeRoute, ...niazsanjiPersonGradePopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, NgSelectModule, FormsModule, BrowserAnimationsModule, InputsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NiazsanjiPersonGradeMarineSuffixComponent,
        NiazsanjiPersonGradeMarineSuffixDetailComponent,
        NiazsanjiPersonGradeMarineSuffixUpdateComponent,
        NiazsanjiPersonGradeMarineSuffixDeleteDialogComponent,
        NiazsanjiPersonGradeMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        NiazsanjiPersonGradeMarineSuffixComponent,
        NiazsanjiPersonGradeMarineSuffixUpdateComponent,
        NiazsanjiPersonGradeMarineSuffixDeleteDialogComponent,
        NiazsanjiPersonGradeMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojNiazsanjiPersonGradeMarineSuffixModule {}
