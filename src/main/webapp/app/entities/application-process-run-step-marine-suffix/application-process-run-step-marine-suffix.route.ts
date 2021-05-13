import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ApplicationProcessRunStepMarineSuffix } from 'app/shared/model/application-process-run-step-marine-suffix.model';
import { ApplicationProcessRunStepMarineSuffixService } from './application-process-run-step-marine-suffix.service';
import { ApplicationProcessRunStepMarineSuffixComponent } from './application-process-run-step-marine-suffix.component';
import { ApplicationProcessRunStepMarineSuffixDetailComponent } from './application-process-run-step-marine-suffix-detail.component';
import { ApplicationProcessRunStepMarineSuffixUpdateComponent } from './application-process-run-step-marine-suffix-update.component';
import { ApplicationProcessRunStepMarineSuffixDeletePopupComponent } from './application-process-run-step-marine-suffix-delete-dialog.component';
import { IApplicationProcessRunStepMarineSuffix } from 'app/shared/model/application-process-run-step-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class ApplicationProcessRunStepMarineSuffixResolve implements Resolve<IApplicationProcessRunStepMarineSuffix> {
    constructor(private service: ApplicationProcessRunStepMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ApplicationProcessRunStepMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    filter((response: HttpResponse<ApplicationProcessRunStepMarineSuffix>) => response.ok),
                    map((applicationProcessRunStep: HttpResponse<ApplicationProcessRunStepMarineSuffix>) => applicationProcessRunStep.body)
                );
        }
        return of(new ApplicationProcessRunStepMarineSuffix());
    }
}

export const applicationProcessRunStepRoute: Routes = [
    {
        path: 'application-process-run-step-marine-suffix',
        component: ApplicationProcessRunStepMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.applicationProcessRunStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'application-process-run-step-marine-suffix/:id/view',
        component: ApplicationProcessRunStepMarineSuffixDetailComponent,
        resolve: {
            applicationProcessRunStep: ApplicationProcessRunStepMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.applicationProcessRunStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'application-process-run-step-marine-suffix/new',
        component: ApplicationProcessRunStepMarineSuffixUpdateComponent,
        resolve: {
            applicationProcessRunStep: ApplicationProcessRunStepMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.applicationProcessRunStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'application-process-run-step-marine-suffix/:id/edit',
        component: ApplicationProcessRunStepMarineSuffixUpdateComponent,
        resolve: {
            applicationProcessRunStep: ApplicationProcessRunStepMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.applicationProcessRunStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const applicationProcessRunStepPopupRoute: Routes = [
    {
        path: 'application-process-run-step-marine-suffix/:id/delete',
        component: ApplicationProcessRunStepMarineSuffixDeletePopupComponent,
        resolve: {
            applicationProcessRunStep: ApplicationProcessRunStepMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.applicationProcessRunStep.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
