import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JobChangeMarineSuffix } from 'app/shared/model/job-change-marine-suffix.model';
import { JobChangeMarineSuffixService } from './job-change-marine-suffix.service';
import { JobChangeMarineSuffixComponent } from './job-change-marine-suffix.component';
import { JobChangeMarineSuffixDetailComponent } from './job-change-marine-suffix-detail.component';
import { JobChangeMarineSuffixUpdateComponent } from './job-change-marine-suffix-update.component';
import { JobChangeMarineSuffixDeletePopupComponent } from './job-change-marine-suffix-delete-dialog.component';
import { IJobChangeMarineSuffix } from 'app/shared/model/job-change-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class JobChangeMarineSuffixResolve implements Resolve<IJobChangeMarineSuffix> {
    constructor(private service: JobChangeMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<JobChangeMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    filter((response: HttpResponse<JobChangeMarineSuffix>) => response.ok),
                    map((jobChange: HttpResponse<JobChangeMarineSuffix>) => jobChange.body)
                );
        }
        return of(new JobChangeMarineSuffix());
    }
}

export const jobChangeRoute: Routes = [
    {
        path: 'job-change-marine-suffix',
        component: JobChangeMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.jobChange.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'job-change-marine-suffix/:id/view',
        component: JobChangeMarineSuffixDetailComponent,
        resolve: {
            jobChange: JobChangeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jobChange.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'job-change-marine-suffix/new',
        component: JobChangeMarineSuffixUpdateComponent,
        resolve: {
            jobChange: JobChangeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jobChange.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'job-change-marine-suffix/:id/edit',
        component: JobChangeMarineSuffixUpdateComponent,
        resolve: {
            jobChange: JobChangeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jobChange.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const jobChangePopupRoute: Routes = [
    {
        path: 'job-change-marine-suffix/:id/delete',
        component: JobChangeMarineSuffixDeletePopupComponent,
        resolve: {
            jobChange: JobChangeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jobChange.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
