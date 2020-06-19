import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EffectivenessPhaseMarineSuffix } from 'app/shared/model/effectiveness-phase-marine-suffix.model';
import { EffectivenessPhaseMarineSuffixService } from './effectiveness-phase-marine-suffix.service';
import { EffectivenessPhaseMarineSuffixComponent } from './effectiveness-phase-marine-suffix.component';
import { EffectivenessPhaseLevelOneMarineSuffixComponent } from './effectiveness-phase-level-one-marine-suffix.component';
import { EffectivenessPhaseLevelTwoMarineSuffixComponent } from './effectiveness-phase-level-two-marine-suffix.component';
import { EffectivenessPhaseMarineSuffixDetailComponent } from './effectiveness-phase-marine-suffix-detail.component';
import { EffectivenessPhaseMarineSuffixUpdateComponent } from './effectiveness-phase-marine-suffix-update.component';
import { EffectivenessPhaseMarineSuffixDeletePopupComponent } from './effectiveness-phase-marine-suffix-delete-dialog.component';
import { IEffectivenessPhaseMarineSuffix } from 'app/shared/model/effectiveness-phase-marine-suffix.model';
import { EffectivenessPhaseLevelThreeMarineSuffixComponent } from './effectiveness-phase-level-three-marine-suffix.component';
import { EffectivenessPhaseLevelFourMarineSuffixComponent } from './effectiveness-phase-level-four-marine-suffix.component';
import { EffectivenessPhaseReportMarineSuffixComponent } from './effectiveness-phase-report-marine-suffix.component';

@Injectable({ providedIn: 'root' })
export class EffectivenessPhaseMarineSuffixResolve implements Resolve<IEffectivenessPhaseMarineSuffix> {
    constructor(private service: EffectivenessPhaseMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EffectivenessPhaseMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    filter((response: HttpResponse<EffectivenessPhaseMarineSuffix>) => response.ok),
                    map((effectivenessPhase: HttpResponse<EffectivenessPhaseMarineSuffix>) => effectivenessPhase.body)
                );
        }
        const finalNiazsanjiReportId = route.params['finalNiazsanjiReportId'] ? route.params['finalNiazsanjiReportId'] : null;
        if (finalNiazsanjiReportId) {
            let effectivenessPhase = new EffectivenessPhaseMarineSuffix();
            effectivenessPhase.finalNiazsanjiReportId = finalNiazsanjiReportId;
            return of(effectivenessPhase);
        }
        return of(new EffectivenessPhaseMarineSuffix());
    }
}

export const effectivenessPhaseRoute: Routes = [
    {
        path: 'effectiveness-phase-report-marine-suffix',
        component: EffectivenessPhaseReportMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams,
            effectivenessPhase: EffectivenessPhaseMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.effectivenessPhase.home.titleReport'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'effectiveness-phase-marine-suffix/:finalNiazsanjiReportId',
        component: EffectivenessPhaseMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams,
            effectivenessPhase: EffectivenessPhaseMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.effectivenessPhase.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'effectiveness-phase-marine-suffix/level-one/:finalNiazsanjiReportId',
        component: EffectivenessPhaseLevelOneMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams,
            effectivenessPhase: EffectivenessPhaseMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.effectivenessPhase.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'effectiveness-phase-marine-suffix/level-two/:finalNiazsanjiReportId',
        component: EffectivenessPhaseLevelTwoMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams,
            effectivenessPhase: EffectivenessPhaseMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.effectivenessPhase.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'effectiveness-phase-marine-suffix/level-three/:finalNiazsanjiReportId',
        component: EffectivenessPhaseLevelThreeMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams,
            effectivenessPhase: EffectivenessPhaseMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.effectivenessPhase.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'effectiveness-phase-marine-suffix/level-four/:finalNiazsanjiReportId',
        component: EffectivenessPhaseLevelFourMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams,
            effectivenessPhase: EffectivenessPhaseMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.effectivenessPhase.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'effectiveness-phase-marine-suffix/:id/view',
        component: EffectivenessPhaseMarineSuffixDetailComponent,
        resolve: {
            effectivenessPhase: EffectivenessPhaseMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.effectivenessPhase.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'effectiveness-phase-marine-suffix/new',
        component: EffectivenessPhaseMarineSuffixUpdateComponent,
        resolve: {
            effectivenessPhase: EffectivenessPhaseMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.effectivenessPhase.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'effectiveness-phase-marine-suffix/:id/edit',
        component: EffectivenessPhaseMarineSuffixUpdateComponent,
        resolve: {
            effectivenessPhase: EffectivenessPhaseMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.effectivenessPhase.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const effectivenessPhasePopupRoute: Routes = [
    {
        path: 'effectiveness-phase-marine-suffix/:id/delete',
        component: EffectivenessPhaseMarineSuffixDeletePopupComponent,
        resolve: {
            effectivenessPhase: EffectivenessPhaseMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.effectivenessPhase.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
