import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MatchingRunRunningStepMarineSuffix } from 'app/shared/model/matching-run-running-step-marine-suffix.model';
import { MatchingRunRunningStepMarineSuffixService } from './matching-run-running-step-marine-suffix.service';
import { MatchingRunRunningStepMarineSuffixComponent } from './matching-run-running-step-marine-suffix.component';
import { MatchingRunRunningStepMarineSuffixDetailComponent } from './matching-run-running-step-marine-suffix-detail.component';
import { MatchingRunRunningStepMarineSuffixUpdateComponent } from './matching-run-running-step-marine-suffix-update.component';
import { MatchingRunRunningStepMarineSuffixDeletePopupComponent } from './matching-run-running-step-marine-suffix-delete-dialog.component';
import { IMatchingRunRunningStepMarineSuffix } from 'app/shared/model/matching-run-running-step-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class MatchingRunRunningStepMarineSuffixResolve implements Resolve<IMatchingRunRunningStepMarineSuffix> {
    constructor(private service: MatchingRunRunningStepMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<MatchingRunRunningStepMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    filter((response: HttpResponse<MatchingRunRunningStepMarineSuffix>) => response.ok),
                    map((matchingRunRunningStep: HttpResponse<MatchingRunRunningStepMarineSuffix>) => matchingRunRunningStep.body)
                );
        }
        return of(new MatchingRunRunningStepMarineSuffix());
    }
}

export const matchingRunRunningStepRoute: Routes = [
    {
        path: 'matching-run-running-step-marine-suffix',
        component: MatchingRunRunningStepMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.matchingRunRunningStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'matching-run-running-step-marine-suffix/:id/view',
        component: MatchingRunRunningStepMarineSuffixDetailComponent,
        resolve: {
            matchingRunRunningStep: MatchingRunRunningStepMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.matchingRunRunningStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'matching-run-running-step-marine-suffix/new',
        component: MatchingRunRunningStepMarineSuffixUpdateComponent,
        resolve: {
            matchingRunRunningStep: MatchingRunRunningStepMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.matchingRunRunningStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'matching-run-running-step-marine-suffix/:id/edit',
        component: MatchingRunRunningStepMarineSuffixUpdateComponent,
        resolve: {
            matchingRunRunningStep: MatchingRunRunningStepMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.matchingRunRunningStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const matchingRunRunningStepPopupRoute: Routes = [
    {
        path: 'matching-run-running-step-marine-suffix/:id/delete',
        component: MatchingRunRunningStepMarineSuffixDeletePopupComponent,
        resolve: {
            matchingRunRunningStep: MatchingRunRunningStepMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.matchingRunRunningStep.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
