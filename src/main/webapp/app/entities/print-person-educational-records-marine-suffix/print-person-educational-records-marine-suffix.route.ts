import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core';
import {PrintPersonEducationalRecordsMarineSuffixComponent} from "./print-person-educational-records-marine-suffix.component";
import {PersonMarineSuffixResolve} from "app/entities/person-marine-suffix";

export const PrintPersonEducationalRecordsMarineSuffixRoute: Route = {
    path: 'print-person-educational-records-marine-suffix/:id',
    component: PrintPersonEducationalRecordsMarineSuffixComponent,
    resolve: {
        person: PersonMarineSuffixResolve
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'global.menu.entities.personEducationalRecordsMarineSuffix'
    },

    canActivate: [UserRouteAccessService]
};
