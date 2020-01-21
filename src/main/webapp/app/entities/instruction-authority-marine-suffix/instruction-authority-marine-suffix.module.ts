import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    InstructionAuthorityMarineSuffixComponent,
    InstructionAuthorityMarineSuffixDetailComponent,
    InstructionAuthorityMarineSuffixUpdateComponent,
    InstructionAuthorityMarineSuffixDeletePopupComponent,
    InstructionAuthorityMarineSuffixDeleteDialogComponent,
    instructionAuthorityRoute,
    instructionAuthorityPopupRoute
} from './';

const ENTITY_STATES = [...instructionAuthorityRoute, ...instructionAuthorityPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        InstructionAuthorityMarineSuffixComponent,
        InstructionAuthorityMarineSuffixDetailComponent,
        InstructionAuthorityMarineSuffixUpdateComponent,
        InstructionAuthorityMarineSuffixDeleteDialogComponent,
        InstructionAuthorityMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        InstructionAuthorityMarineSuffixComponent,
        InstructionAuthorityMarineSuffixUpdateComponent,
        InstructionAuthorityMarineSuffixDeleteDialogComponent,
        InstructionAuthorityMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojInstructionAuthorityMarineSuffixModule {}
