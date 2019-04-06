import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { RunningStepMarineSuffix } from 'app/shared/model/running-step-marine-suffix.model';
import { RunningStepMarineSuffixService } from './running-step-marine-suffix.service';
import { RunningStepMarineSuffixComponent } from './running-step-marine-suffix.component';
import { RunningStepMarineSuffixDetailComponent } from './running-step-marine-suffix-detail.component';
import { RunningStepMarineSuffixUpdateComponent } from './running-step-marine-suffix-update.component';
import { RunningStepMarineSuffixDeletePopupComponent } from './running-step-marine-suffix-delete-dialog.component';
import { IRunningStepMarineSuffix } from 'app/shared/model/running-step-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class RunningStepMarineSuffixResolve implements Resolve<IRunningStepMarineSuffix> {
    constructor(private service: RunningStepMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((runningStep: HttpResponse<RunningStepMarineSuffix>) => runningStep.body));
        }
        return of(new RunningStepMarineSuffix());
    }
}

export const runningStepRoute: Routes = [
    {
        path: 'running-step-marine-suffix',
        component: RunningStepMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.runningStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'running-step-marine-suffix/:id/view',
        component: RunningStepMarineSuffixDetailComponent,
        resolve: {
            runningStep: RunningStepMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.runningStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'running-step-marine-suffix/new',
        component: RunningStepMarineSuffixUpdateComponent,
        resolve: {
            runningStep: RunningStepMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.runningStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'running-step-marine-suffix/:id/edit',
        component: RunningStepMarineSuffixUpdateComponent,
        resolve: {
            runningStep: RunningStepMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.runningStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const runningStepPopupRoute: Routes = [
    {
        path: 'running-step-marine-suffix/:id/delete',
        component: RunningStepMarineSuffixDeletePopupComponent,
        resolve: {
            runningStep: RunningStepMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.runningStep.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
