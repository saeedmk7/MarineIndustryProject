import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { JobMarineSuffix } from 'app/shared/model/job-marine-suffix.model';
import { JobMarineSuffixService } from './job-marine-suffix.service';
import { JobMarineSuffixComponent } from './job-marine-suffix.component';
import { JobMarineSuffixDetailComponent } from './job-marine-suffix-detail.component';
import { JobMarineSuffixUpdateComponent } from './job-marine-suffix-update.component';
import { JobMarineSuffixDeletePopupComponent } from './job-marine-suffix-delete-dialog.component';
import { IJobMarineSuffix } from 'app/shared/model/job-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class JobMarineSuffixResolve implements Resolve<IJobMarineSuffix> {
    constructor(private service: JobMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((job: HttpResponse<JobMarineSuffix>) => job.body));
        }
        return of(new JobMarineSuffix());
    }
}

export const jobRoute: Routes = [
    {
        path: 'job-marine-suffix',
        component: JobMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.job.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'job-marine-suffix/:id/view',
        component: JobMarineSuffixDetailComponent,
        resolve: {
            job: JobMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.job.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'job-marine-suffix/new',
        component: JobMarineSuffixUpdateComponent,
        resolve: {
            job: JobMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.job.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'job-marine-suffix/:id/edit',
        component: JobMarineSuffixUpdateComponent,
        resolve: {
            job: JobMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.job.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const jobPopupRoute: Routes = [
    {
        path: 'job-marine-suffix/:id/delete',
        component: JobMarineSuffixDeletePopupComponent,
        resolve: {
            job: JobMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.job.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
