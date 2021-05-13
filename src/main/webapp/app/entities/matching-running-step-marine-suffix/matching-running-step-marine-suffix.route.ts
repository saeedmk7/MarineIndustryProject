import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MatchingRunningStepMarineSuffix } from 'app/shared/model/matching-running-step-marine-suffix.model';
import { MatchingRunningStepMarineSuffixService } from './matching-running-step-marine-suffix.service';
import { MatchingRunningStepMarineSuffixComponent } from './matching-running-step-marine-suffix.component';
import { MatchingRunningStepMarineSuffixDetailComponent } from './matching-running-step-marine-suffix-detail.component';
import { MatchingRunningStepMarineSuffixUpdateComponent } from './matching-running-step-marine-suffix-update.component';
import { MatchingRunningStepMarineSuffixDeletePopupComponent } from './matching-running-step-marine-suffix-delete-dialog.component';
import { IMatchingRunningStepMarineSuffix } from 'app/shared/model/matching-running-step-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class MatchingRunningStepMarineSuffixResolve implements Resolve<IMatchingRunningStepMarineSuffix> {
    constructor(private service: MatchingRunningStepMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<MatchingRunningStepMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    filter((response: HttpResponse<MatchingRunningStepMarineSuffix>) => response.ok),
                    map((matchingRunningStep: HttpResponse<MatchingRunningStepMarineSuffix>) => matchingRunningStep.body)
                );
        }
        return of(new MatchingRunningStepMarineSuffix());
    }
}

export const matchingRunningStepRoute: Routes = [
    {
        path: 'matching-running-step-marine-suffix',
        component: MatchingRunningStepMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.matchingRunningStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'matching-running-step-marine-suffix/:id/view',
        component: MatchingRunningStepMarineSuffixDetailComponent,
        resolve: {
            matchingRunningStep: MatchingRunningStepMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.matchingRunningStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'matching-running-step-marine-suffix/new',
        component: MatchingRunningStepMarineSuffixUpdateComponent,
        resolve: {
            matchingRunningStep: MatchingRunningStepMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.matchingRunningStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'matching-running-step-marine-suffix/:id/edit',
        component: MatchingRunningStepMarineSuffixUpdateComponent,
        resolve: {
            matchingRunningStep: MatchingRunningStepMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.matchingRunningStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const matchingRunningStepPopupRoute: Routes = [
    {
        path: 'matching-running-step-marine-suffix/:id/delete',
        component: MatchingRunningStepMarineSuffixDeletePopupComponent,
        resolve: {
            matchingRunningStep: MatchingRunningStepMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.matchingRunningStep.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
