import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { JobTypeMarineSuffix } from 'app/shared/model/job-type-marine-suffix.model';
import { JobTypeMarineSuffixService } from './job-type-marine-suffix.service';
import { JobTypeMarineSuffixComponent } from './job-type-marine-suffix.component';
import { JobTypeMarineSuffixDetailComponent } from './job-type-marine-suffix-detail.component';
import { JobTypeMarineSuffixUpdateComponent } from './job-type-marine-suffix-update.component';
import { JobTypeMarineSuffixDeletePopupComponent } from './job-type-marine-suffix-delete-dialog.component';
import { IJobTypeMarineSuffix } from 'app/shared/model/job-type-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class JobTypeMarineSuffixResolve implements Resolve<IJobTypeMarineSuffix> {
    constructor(private service: JobTypeMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((jobType: HttpResponse<JobTypeMarineSuffix>) => jobType.body));
        }
        return of(new JobTypeMarineSuffix());
    }
}

export const jobTypeRoute: Routes = [
    {
        path: 'job-type-marine-suffix',
        component: JobTypeMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.jobType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'job-type-marine-suffix/:id/view',
        component: JobTypeMarineSuffixDetailComponent,
        resolve: {
            jobType: JobTypeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jobType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'job-type-marine-suffix/new',
        component: JobTypeMarineSuffixUpdateComponent,
        resolve: {
            jobType: JobTypeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jobType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'job-type-marine-suffix/:id/edit',
        component: JobTypeMarineSuffixUpdateComponent,
        resolve: {
            jobType: JobTypeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jobType.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const jobTypePopupRoute: Routes = [
    {
        path: 'job-type-marine-suffix/:id/delete',
        component: JobTypeMarineSuffixDeletePopupComponent,
        resolve: {
            jobType: JobTypeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jobType.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
