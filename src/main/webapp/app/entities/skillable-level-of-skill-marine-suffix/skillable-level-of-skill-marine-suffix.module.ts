import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    SkillableLevelOfSkillMarineSuffixComponent,
    SkillableLevelOfSkillMarineSuffixDetailComponent,
    SkillableLevelOfSkillMarineSuffixUpdateComponent,
    SkillableLevelOfSkillMarineSuffixDeletePopupComponent,
    SkillableLevelOfSkillMarineSuffixDeleteDialogComponent,
    skillableLevelOfSkillRoute,
    skillableLevelOfSkillPopupRoute
} from './';

const ENTITY_STATES = [...skillableLevelOfSkillRoute, ...skillableLevelOfSkillPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SkillableLevelOfSkillMarineSuffixComponent,
        SkillableLevelOfSkillMarineSuffixDetailComponent,
        SkillableLevelOfSkillMarineSuffixUpdateComponent,
        SkillableLevelOfSkillMarineSuffixDeleteDialogComponent,
        SkillableLevelOfSkillMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        SkillableLevelOfSkillMarineSuffixComponent,
        SkillableLevelOfSkillMarineSuffixUpdateComponent,
        SkillableLevelOfSkillMarineSuffixDeleteDialogComponent,
        SkillableLevelOfSkillMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojSkillableLevelOfSkillMarineSuffixModule {}
