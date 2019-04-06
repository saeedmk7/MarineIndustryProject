import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import {
    DesignAndPlanningMarineSuffixComponent,
    DesignAndPlanningMarineSuffixDetailComponent,
    DesignAndPlanningMarineSuffixUpdateComponent,
    DesignAndPlanningMarineSuffixDeletePopupComponent,
    DesignAndPlanningMarineSuffixDeleteDialogComponent,
    designAndPlanningRoute,
    designAndPlanningPopupRoute
} from './';

const ENTITY_STATES = [...designAndPlanningRoute, ...designAndPlanningPopupRoute];

@NgModule({
    imports: [MarineindustryprojSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DesignAndPlanningMarineSuffixComponent,
        DesignAndPlanningMarineSuffixDetailComponent,
        DesignAndPlanningMarineSuffixUpdateComponent,
        DesignAndPlanningMarineSuffixDeleteDialogComponent,
        DesignAndPlanningMarineSuffixDeletePopupComponent
    ],
    entryComponents: [
        DesignAndPlanningMarineSuffixComponent,
        DesignAndPlanningMarineSuffixUpdateComponent,
        DesignAndPlanningMarineSuffixDeleteDialogComponent,
        DesignAndPlanningMarineSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojDesignAndPlanningMarineSuffixModule {}
