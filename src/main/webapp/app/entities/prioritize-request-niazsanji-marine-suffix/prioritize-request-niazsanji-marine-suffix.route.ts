import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PrioritizeRequestNiazsanjiMarineSuffix } from 'app/shared/model/prioritize-request-niazsanji-marine-suffix.model';
import { PrioritizeRequestNiazsanjiMarineSuffixService } from './prioritize-request-niazsanji-marine-suffix.service';
import { PrioritizeRequestNiazsanjiMarineSuffixComponent } from './prioritize-request-niazsanji-marine-suffix.component';
import { PrioritizeRequestNiazsanjiMarineSuffixDetailComponent } from './prioritize-request-niazsanji-marine-suffix-detail.component';
import { PrioritizeRequestNiazsanjiMarineSuffixUpdateComponent } from './prioritize-request-niazsanji-marine-suffix-update.component';
import { PrioritizeRequestNiazsanjiMarineSuffixDeletePopupComponent } from './prioritize-request-niazsanji-marine-suffix-delete-dialog.component';
import { IPrioritizeRequestNiazsanjiMarineSuffix } from 'app/shared/model/prioritize-request-niazsanji-marine-suffix.model';
import {
    PreJobNiazsanjiMarineSuffixCommentPopupComponent,
    PreJobNiazsanjiMarineSuffixResolve
} from "app/entities/pre-job-niazsanji-marine-suffix";
import {PrioritizeRequestNiazsanjiMarineSuffixCommentPopupComponent} from "app/entities/prioritize-request-niazsanji-marine-suffix/prioritize-request-niazsanji-marine-suffix-comment-dialog.component";

@Injectable({ providedIn: 'root' })
export class PrioritizeRequestNiazsanjiMarineSuffixResolve implements Resolve<IPrioritizeRequestNiazsanjiMarineSuffix> {
    constructor(private service: PrioritizeRequestNiazsanjiMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PrioritizeRequestNiazsanjiMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PrioritizeRequestNiazsanjiMarineSuffix>) => response.ok),
                map((prioritizeRequestNiazsanji: HttpResponse<PrioritizeRequestNiazsanjiMarineSuffix>) => prioritizeRequestNiazsanji.body)
            );
        }
        return of(new PrioritizeRequestNiazsanjiMarineSuffix());
    }
}

export const prioritizeRequestNiazsanjiRoute: Routes = [
    {
        path: 'prioritize-request-niazsanji-marine-suffix',
        component: PrioritizeRequestNiazsanjiMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.prioritizeRequestNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'prioritize-request-niazsanji-marine-suffix/:id/view',
        component: PrioritizeRequestNiazsanjiMarineSuffixDetailComponent,
        resolve: {
            prioritizeRequestNiazsanji: PrioritizeRequestNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.prioritizeRequestNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'prioritize-request-niazsanji-marine-suffix/new',
        component: PrioritizeRequestNiazsanjiMarineSuffixUpdateComponent,
        resolve: {
            prioritizeRequestNiazsanji: PrioritizeRequestNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.prioritizeRequestNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'prioritize-request-niazsanji-marine-suffix/:id/edit',
        component: PrioritizeRequestNiazsanjiMarineSuffixUpdateComponent,
        resolve: {
            prioritizeRequestNiazsanji: PrioritizeRequestNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.prioritizeRequestNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const prioritizeRequestNiazsanjiPopupRoute: Routes = [
    {
        path: 'prioritize-request-niazsanji-marine-suffix/:id/delete',
        component: PrioritizeRequestNiazsanjiMarineSuffixDeletePopupComponent,
        resolve: {
            prioritizeRequestNiazsanji: PrioritizeRequestNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.prioritizeRequestNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },{
        path: 'prioritize-request-niazsanji-marine-suffix/:id/:CommentType/:priorityValue/comment',
        component: PrioritizeRequestNiazsanjiMarineSuffixCommentPopupComponent,
        resolve: {
            prioritizeRequestNiazsanji: PrioritizeRequestNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.prioritizeRequestNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
