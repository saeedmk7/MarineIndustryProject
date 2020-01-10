import {  Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { PreJobNiazsanjiStepsHandlerMarineSuffixUpdateComponent } from './pre-job-niazsanji-steps-handler-marine-suffix-update.component';
import {PreJobNiazsanjiMarineSuffixResolve} from 'app/entities/pre-job-niazsanji-marine-suffix';

export const preJobNiazsanjiStepsRoute: Routes = [
    {
        path: 'pre-job-niazsanji-steps-marine-suffix/:id/edit',
        component: PreJobNiazsanjiStepsHandlerMarineSuffixUpdateComponent,
        resolve: {
            preJobNiazsanji: PreJobNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.preJobNiazsanji.home.title',
            editable: true
        },
        canActivate: [UserRouteAccessService]
    },{
        path: 'pre-job-niazsanji-steps-marine-suffix/:id/view',
        component: PreJobNiazsanjiStepsHandlerMarineSuffixUpdateComponent,
        resolve: {
            preJobNiazsanji: PreJobNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.preJobNiazsanji.home.title',
            editable: false
        },
        canActivate: [UserRouteAccessService]
    }
];
