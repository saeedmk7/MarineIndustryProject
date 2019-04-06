import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { RunRunningStepMarineSuffix } from 'app/shared/model/run-running-step-marine-suffix.model';
import { RunRunningStepMarineSuffixService } from './run-running-step-marine-suffix.service';
import { RunRunningStepMarineSuffixComponent } from './run-running-step-marine-suffix.component';
import { RunRunningStepMarineSuffixDetailComponent } from './run-running-step-marine-suffix-detail.component';
import { RunRunningStepMarineSuffixUpdateComponent } from './run-running-step-marine-suffix-update.component';
import { RunRunningStepMarineSuffixDeletePopupComponent } from './run-running-step-marine-suffix-delete-dialog.component';
import { IRunRunningStepMarineSuffix } from 'app/shared/model/run-running-step-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class RunRunningStepMarineSuffixResolve implements Resolve<IRunRunningStepMarineSuffix> {
    constructor(private service: RunRunningStepMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((runRunningStep: HttpResponse<RunRunningStepMarineSuffix>) => runRunningStep.body));
        }
        return of(new RunRunningStepMarineSuffix());
    }
}

export const runRunningStepRoute: Routes = [
    {
        path: 'run-running-step-marine-suffix',
        component: RunRunningStepMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.runRunningStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'run-running-step-marine-suffix/:id/view',
        component: RunRunningStepMarineSuffixDetailComponent,
        resolve: {
            runRunningStep: RunRunningStepMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.runRunningStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'run-running-step-marine-suffix/new',
        component: RunRunningStepMarineSuffixUpdateComponent,
        resolve: {
            runRunningStep: RunRunningStepMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.runRunningStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'run-running-step-marine-suffix/:id/edit',
        component: RunRunningStepMarineSuffixUpdateComponent,
        resolve: {
            runRunningStep: RunRunningStepMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.runRunningStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const runRunningStepPopupRoute: Routes = [
    {
        path: 'run-running-step-marine-suffix/:id/delete',
        component: RunRunningStepMarineSuffixDeletePopupComponent,
        resolve: {
            runRunningStep: RunRunningStepMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.runRunningStep.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
