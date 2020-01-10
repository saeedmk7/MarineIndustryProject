import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JobNiazsanjiMarineSuffix } from 'app/shared/model/job-niazsanji-marine-suffix.model';
import { JobNiazsanjiMarineSuffixService } from './job-niazsanji-marine-suffix.service';
import { JobNiazsanjiMarineSuffixComponent } from './job-niazsanji-marine-suffix.component';
import { JobNiazsanjiMarineSuffixDetailComponent } from './job-niazsanji-marine-suffix-detail.component';
import { JobNiazsanjiMarineSuffixUpdateComponent } from './job-niazsanji-marine-suffix-update.component';
import { JobNiazsanjiMarineSuffixDeletePopupComponent } from './job-niazsanji-marine-suffix-delete-dialog.component';
import { IJobNiazsanjiMarineSuffix } from 'app/shared/model/job-niazsanji-marine-suffix.model';
import {JobNiazsanjiMarineSuffixCommentPopupComponent} from "app/entities/job-niazsanji-marine-suffix/job-niazsanji-marine-suffix-comment-dialog.component";

@Injectable({ providedIn: 'root' })
export class JobNiazsanjiMarineSuffixResolve implements Resolve<IJobNiazsanjiMarineSuffix> {
    constructor(private service: JobNiazsanjiMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<JobNiazsanjiMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<JobNiazsanjiMarineSuffix>) => response.ok),
                map((jobNiazsanji: HttpResponse<JobNiazsanjiMarineSuffix>) => jobNiazsanji.body)
            );
        }
        return of(new JobNiazsanjiMarineSuffix());
    }
}

export const jobNiazsanjiRoute: Routes = [
    {
        path: 'job-niazsanji-marine-suffix',
        component: JobNiazsanjiMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.jobNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'job-niazsanji-marine-suffix/:id/view',
        component: JobNiazsanjiMarineSuffixDetailComponent,
        resolve: {
            jobNiazsanji: JobNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jobNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'job-niazsanji-marine-suffix/new',
        component: JobNiazsanjiMarineSuffixUpdateComponent,
        resolve: {
            jobNiazsanji: JobNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jobNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'job-niazsanji-marine-suffix/:id/edit',
        component: JobNiazsanjiMarineSuffixUpdateComponent,
        resolve: {
            jobNiazsanji: JobNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jobNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const jobNiazsanjiPopupRoute: Routes = [
    {
        path: 'job-niazsanji-marine-suffix/:id/delete',
        component: JobNiazsanjiMarineSuffixDeletePopupComponent,
        resolve: {
            jobNiazsanji: JobNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jobNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'job-niazsanji-marine-suffix/:id/:CommentType/comment',
        component: JobNiazsanjiMarineSuffixCommentPopupComponent,
        resolve: {
            jobNiazsanji: JobNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jobNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
