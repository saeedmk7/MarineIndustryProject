import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ApplicationProcessStepMarineSuffix } from 'app/shared/model/application-process-step-marine-suffix.model';
import { ApplicationProcessStepMarineSuffixService } from './application-process-step-marine-suffix.service';
import { ApplicationProcessStepMarineSuffixComponent } from './application-process-step-marine-suffix.component';
import { ApplicationProcessStepMarineSuffixDetailComponent } from './application-process-step-marine-suffix-detail.component';
import { ApplicationProcessStepMarineSuffixUpdateComponent } from './application-process-step-marine-suffix-update.component';
import { ApplicationProcessStepMarineSuffixDeletePopupComponent } from './application-process-step-marine-suffix-delete-dialog.component';
import { IApplicationProcessStepMarineSuffix } from 'app/shared/model/application-process-step-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class ApplicationProcessStepMarineSuffixResolve implements Resolve<IApplicationProcessStepMarineSuffix> {
    constructor(private service: ApplicationProcessStepMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ApplicationProcessStepMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    filter((response: HttpResponse<ApplicationProcessStepMarineSuffix>) => response.ok),
                    map((applicationProcessStep: HttpResponse<ApplicationProcessStepMarineSuffix>) => applicationProcessStep.body)
                );
        }
        return of(new ApplicationProcessStepMarineSuffix());
    }
}

export const applicationProcessStepRoute: Routes = [
    {
        path: 'application-process-step-marine-suffix',
        component: ApplicationProcessStepMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.applicationProcessStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'application-process-step-marine-suffix/:id/view',
        component: ApplicationProcessStepMarineSuffixDetailComponent,
        resolve: {
            applicationProcessStep: ApplicationProcessStepMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.applicationProcessStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'application-process-step-marine-suffix/new',
        component: ApplicationProcessStepMarineSuffixUpdateComponent,
        resolve: {
            applicationProcessStep: ApplicationProcessStepMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.applicationProcessStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'application-process-step-marine-suffix/:id/edit',
        component: ApplicationProcessStepMarineSuffixUpdateComponent,
        resolve: {
            applicationProcessStep: ApplicationProcessStepMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.applicationProcessStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const applicationProcessStepPopupRoute: Routes = [
    {
        path: 'application-process-step-marine-suffix/:id/delete',
        component: ApplicationProcessStepMarineSuffixDeletePopupComponent,
        resolve: {
            applicationProcessStep: ApplicationProcessStepMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.applicationProcessStep.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
