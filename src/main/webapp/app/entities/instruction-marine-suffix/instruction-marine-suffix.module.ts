import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    InstructionMarineSuffixComponent,
    InstructionMarineSuffixDetailComponent,
    InstructionMarineSuffixUpdateComponent,
    InstructionMarineSuffixDeletePopupComponent,
    InstructionMarineSuffixDeleteDialogComponent,
    instructionRoute,
    instructionPopupRoute
} from './';

const ENTITY_STATES = [...instructionRoute, ...instructionPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        InstructionMarineSuffixComponent,
        InstructionMarineSuffixDetailComponent,
        InstructionMarineSuffixUpdateComponent,
        InstructionMarineSuffixDeleteDialogComponent,
        InstructionMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        InstructionMarineSuffixComponent,
        InstructionMarineSuffixUpdateComponent,
        InstructionMarineSuffixDeleteDialogComponent,
        InstructionMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojInstructionMarineSuffixModule {}
