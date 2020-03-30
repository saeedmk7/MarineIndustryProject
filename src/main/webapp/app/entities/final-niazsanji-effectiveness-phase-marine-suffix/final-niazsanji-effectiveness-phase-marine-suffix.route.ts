import { Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { FinalNiazsanjiEffectivenessPhaseMarineSuffixComponent } from './final-niazsanji-effectiveness-phase-marine-suffix.component';
import { FinalNiazsanjiReportMarineSuffixDetailComponent } from './final-niazsanji-effectiveness-phase-marine-suffix-detail.component';
import { FinalNiazsanjiEffectivenessPhaseMarineSuffixUpdateComponent } from './final-niazsanji-effectiveness-phase-marine-suffix-update.component';
import {FinalNiazsanjiReportMarineSuffixResolve} from "app/entities/final-niazsanji-report-marine-suffix";

export const finalNiazsanjiEffectivenessPhaseRoute: Routes = [
    {
        path: 'final-niazsanji-effectiveness-phase-marine-suffix',
        component: FinalNiazsanjiEffectivenessPhaseMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.finalNiazsanjiReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'final-niazsanji-effectiveness-phase-marine-suffix/:id/view',
        component: FinalNiazsanjiReportMarineSuffixDetailComponent,
        resolve: {
            finalNiazsanjiReport: FinalNiazsanjiReportMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.finalNiazsanjiReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'final-niazsanji-report-marine-suffix/new',
        component: FinalNiazsanjiEffectivenessPhaseMarineSuffixUpdateComponent,
        resolve: {
            finalNiazsanjiReport: FinalNiazsanjiReportMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.finalNiazsanjiReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'final-niazsanji-report-marine-suffix/:id/edit',
        component: FinalNiazsanjiEffectivenessPhaseMarineSuffixUpdateComponent,
        resolve: {
            finalNiazsanjiReport: FinalNiazsanjiReportMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.finalNiazsanjiReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];
