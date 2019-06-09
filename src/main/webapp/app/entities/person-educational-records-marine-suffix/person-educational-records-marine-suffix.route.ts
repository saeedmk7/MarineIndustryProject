import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core';
import {PersonEducationalRecordsMarineSuffixComponent} from "./person-educational-records-marine-suffix.component";

export const PersonEducationalRecordsMarineSuffixRoute: Route = {
    path: 'person-educational-records-marine-suffix',
    component: PersonEducationalRecordsMarineSuffixComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'global.menu.account.settings'
    },
    canActivate: [UserRouteAccessService]
};
