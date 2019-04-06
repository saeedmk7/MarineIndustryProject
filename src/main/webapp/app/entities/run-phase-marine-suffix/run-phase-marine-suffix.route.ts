import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { RunPhaseMarineSuffix } from 'app/shared/model/run-phase-marine-suffix.model';
import { RunPhaseMarineSuffixService } from './run-phase-marine-suffix.service';
import { RunPhaseMarineSuffixComponent } from './run-phase-marine-suffix.component';
import { RunPhaseMarineSuffixDetailComponent } from './run-phase-marine-suffix-detail.component';
import { RunPhaseMarineSuffixUpdateComponent } from './run-phase-marine-suffix-update.component';
import { RunPhaseMarineSuffixDeletePopupComponent } from './run-phase-marine-suffix-delete-dialog.component';
import { IRunPhaseMarineSuffix } from 'app/shared/model/run-phase-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class RunPhaseMarineSuffixResolve implements Resolve<IRunPhaseMarineSuffix> {
    constructor(private service: RunPhaseMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((runPhase: HttpResponse<RunPhaseMarineSuffix>) => runPhase.body));
        }
        return of(new RunPhaseMarineSuffix());
    }
}

export const runPhaseRoute: Routes = [
    {
        path: 'run-phase-marine-suffix',
        component: RunPhaseMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.runPhase.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'run-phase-marine-suffix/:id/view',
        component: RunPhaseMarineSuffixDetailComponent,
        resolve: {
            runPhase: RunPhaseMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.runPhase.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'run-phase-marine-suffix/new',
        component: RunPhaseMarineSuffixUpdateComponent,
        resolve: {
            runPhase: RunPhaseMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.runPhase.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'run-phase-marine-suffix/:id/edit',
        component: RunPhaseMarineSuffixUpdateComponent,
        resolve: {
            runPhase: RunPhaseMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.runPhase.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const runPhasePopupRoute: Routes = [
    {
        path: 'run-phase-marine-suffix/:id/delete',
        component: RunPhaseMarineSuffixDeletePopupComponent,
        resolve: {
            runPhase: RunPhaseMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.runPhase.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
