import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RequestOtherNiazsanjiMarineSuffix } from 'app/shared/model/request-other-niazsanji-marine-suffix.model';
import { RequestOtherNiazsanjiMarineSuffixService } from './request-other-niazsanji-marine-suffix.service';
import { RequestOtherNiazsanjiMarineSuffixComponent } from './request-other-niazsanji-marine-suffix.component';
import { RequestOtherNiazsanjiMarineSuffixDetailComponent } from './request-other-niazsanji-marine-suffix-detail.component';
import { RequestOtherNiazsanjiMarineSuffixUpdateComponent } from './request-other-niazsanji-marine-suffix-update.component';
import { RequestOtherNiazsanjiMarineSuffixDeletePopupComponent } from './request-other-niazsanji-marine-suffix-delete-dialog.component';
import { IRequestOtherNiazsanjiMarineSuffix } from 'app/shared/model/request-other-niazsanji-marine-suffix.model';
import {RequestNiazsanjiFardiMarineSuffixCommentPopupComponent} from "app/entities/request-niazsanji-fardi-marine-suffix/request-niazsanji-fardi-marine-suffix-comment-dialog.component";
import {RequestNiazsanjiFardiMarineSuffixResolve} from "app/entities/request-niazsanji-fardi-marine-suffix";
import {RequestOtherNiazsanjiMarineSuffixCommentPopupComponent} from "app/entities/request-other-niazsanji-marine-suffix/request-other-niazsanji-marine-suffix-comment-dialog.component";

@Injectable({ providedIn: 'root' })
export class RequestOtherNiazsanjiMarineSuffixResolve implements Resolve<IRequestOtherNiazsanjiMarineSuffix> {
    constructor(private service: RequestOtherNiazsanjiMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RequestOtherNiazsanjiMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RequestOtherNiazsanjiMarineSuffix>) => response.ok),
                map((requestOtherNiazsanji: HttpResponse<RequestOtherNiazsanjiMarineSuffix>) => requestOtherNiazsanji.body)
            );
        }
        return of(new RequestOtherNiazsanjiMarineSuffix());
    }
}

export const requestOtherNiazsanjiRoute: Routes = [
    {
        path: 'request-other-niazsanji-marine-suffix',
        component: RequestOtherNiazsanjiMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.requestOtherNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'request-other-niazsanji-marine-suffix/:id/view',
        component: RequestOtherNiazsanjiMarineSuffixDetailComponent,
        resolve: {
            requestOtherNiazsanji: RequestOtherNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.requestOtherNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'request-other-niazsanji-marine-suffix/new',
        component: RequestOtherNiazsanjiMarineSuffixUpdateComponent,
        resolve: {
            requestOtherNiazsanji: RequestOtherNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.requestOtherNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'request-other-niazsanji-marine-suffix/:id/edit',
        component: RequestOtherNiazsanjiMarineSuffixUpdateComponent,
        resolve: {
            requestOtherNiazsanji: RequestOtherNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.requestOtherNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const requestOtherNiazsanjiPopupRoute: Routes = [
    {
        path: 'request-other-niazsanji-marine-suffix/:id/delete',
        component: RequestOtherNiazsanjiMarineSuffixDeletePopupComponent,
        resolve: {
            requestOtherNiazsanji: RequestOtherNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.requestOtherNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'request-other-niazsanji-marine-suffix/:id/:CommentType/comment',
        component: RequestOtherNiazsanjiMarineSuffixCommentPopupComponent,
        resolve: {
            requestOtherNiazsanji: RequestOtherNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.requestNiazsanjiFardi.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
