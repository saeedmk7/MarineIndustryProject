import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EffectivenessPhaseLevelMarineSuffix } from 'app/shared/model/effectiveness-phase-level-marine-suffix.model';
import { EffectivenessPhaseLevelMarineSuffixService } from './effectiveness-phase-level-marine-suffix.service';
import { EffectivenessPhaseLevelMarineSuffixComponent } from './effectiveness-phase-level-marine-suffix.component';
import { EffectivenessPhaseLevelMarineSuffixDetailComponent } from './effectiveness-phase-level-marine-suffix-detail.component';
import { EffectivenessPhaseLevelMarineSuffixUpdateComponent } from './effectiveness-phase-level-marine-suffix-update.component';
import { EffectivenessPhaseLevelMarineSuffixDeletePopupComponent } from './effectiveness-phase-level-marine-suffix-delete-dialog.component';
import { IEffectivenessPhaseLevelMarineSuffix } from 'app/shared/model/effectiveness-phase-level-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class EffectivenessPhaseLevelMarineSuffixResolve implements Resolve<IEffectivenessPhaseLevelMarineSuffix> {
    constructor(private service: EffectivenessPhaseLevelMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EffectivenessPhaseLevelMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EffectivenessPhaseLevelMarineSuffix>) => response.ok),
                map((effectivenessPhaseLevel: HttpResponse<EffectivenessPhaseLevelMarineSuffix>) => effectivenessPhaseLevel.body)
            );
        }
        return of(new EffectivenessPhaseLevelMarineSuffix());
    }
}

export const effectivenessPhaseLevelRoute: Routes = [
    {
        path: 'effectiveness-phase-level-marine-suffix',
        component: EffectivenessPhaseLevelMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.effectivenessPhaseLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'effectiveness-phase-level-marine-suffix/:id/view',
        component: EffectivenessPhaseLevelMarineSuffixDetailComponent,
        resolve: {
            effectivenessPhaseLevel: EffectivenessPhaseLevelMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.effectivenessPhaseLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'effectiveness-phase-level-marine-suffix/new',
        component: EffectivenessPhaseLevelMarineSuffixUpdateComponent,
        resolve: {
            effectivenessPhaseLevel: EffectivenessPhaseLevelMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.effectivenessPhaseLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'effectiveness-phase-level-marine-suffix/:id/edit',
        component: EffectivenessPhaseLevelMarineSuffixUpdateComponent,
        resolve: {
            effectivenessPhaseLevel: EffectivenessPhaseLevelMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.effectivenessPhaseLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const effectivenessPhaseLevelPopupRoute: Routes = [
    {
        path: 'effectiveness-phase-level-marine-suffix/:id/delete',
        component: EffectivenessPhaseLevelMarineSuffixDeletePopupComponent,
        resolve: {
            effectivenessPhaseLevel: EffectivenessPhaseLevelMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.effectivenessPhaseLevel.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
