import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RequestNiazsanjiFardiMarineSuffix } from 'app/shared/model/request-niazsanji-fardi-marine-suffix.model';
import { RequestNiazsanjiFardiMarineSuffixService } from './request-niazsanji-fardi-marine-suffix.service';
import { RequestNiazsanjiFardiMarineSuffixComponent } from './request-niazsanji-fardi-marine-suffix.component';
import { RequestNiazsanjiFardiMarineSuffixDetailComponent } from './request-niazsanji-fardi-marine-suffix-detail.component';
import { RequestNiazsanjiFardiMarineSuffixUpdateComponent } from './request-niazsanji-fardi-marine-suffix-update.component';
import { RequestNiazsanjiFardiMarineSuffixDeletePopupComponent } from './request-niazsanji-fardi-marine-suffix-delete-dialog.component';
import { IRequestNiazsanjiFardiMarineSuffix } from 'app/shared/model/request-niazsanji-fardi-marine-suffix.model';
import {RequestNiazsanjiFardiMarineSuffixCommentPopupComponent} from "app/entities/request-niazsanji-fardi-marine-suffix/request-niazsanji-fardi-marine-suffix-comment-dialog.component";

@Injectable({ providedIn: 'root' })
export class RequestNiazsanjiFardiMarineSuffixResolve implements Resolve<IRequestNiazsanjiFardiMarineSuffix> {
    constructor(private service: RequestNiazsanjiFardiMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RequestNiazsanjiFardiMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RequestNiazsanjiFardiMarineSuffix>) => response.ok),
                map((requestNiazsanjiFardi: HttpResponse<RequestNiazsanjiFardiMarineSuffix>) => requestNiazsanjiFardi.body)
            );
        }
        return of(new RequestNiazsanjiFardiMarineSuffix());
    }
}

export const requestNiazsanjiFardiRoute: Routes = [
    {
        path: 'request-niazsanji-fardi-marine-suffix',
        component: RequestNiazsanjiFardiMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.requestNiazsanjiFardi.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'request-niazsanji-fardi-marine-suffix/:id/view',
        component: RequestNiazsanjiFardiMarineSuffixDetailComponent,
        resolve: {
            requestNiazsanjiFardi: RequestNiazsanjiFardiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.requestNiazsanjiFardi.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'request-niazsanji-fardi-marine-suffix/new',
        component: RequestNiazsanjiFardiMarineSuffixUpdateComponent,
        resolve: {
            requestNiazsanjiFardi: RequestNiazsanjiFardiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.requestNiazsanjiFardi.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'request-niazsanji-fardi-marine-suffix/:id/edit',
        component: RequestNiazsanjiFardiMarineSuffixUpdateComponent,
        resolve: {
            requestNiazsanjiFardi: RequestNiazsanjiFardiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.requestNiazsanjiFardi.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const requestNiazsanjiFardiPopupRoute: Routes = [
    {
        path: 'request-niazsanji-fardi-marine-suffix/:id/delete',
        component: RequestNiazsanjiFardiMarineSuffixDeletePopupComponent,
        resolve: {
            requestNiazsanjiFardi: RequestNiazsanjiFardiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.requestNiazsanjiFardi.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'request-niazsanji-fardi-marine-suffix/:id/:CommentType/comment',
        component: RequestNiazsanjiFardiMarineSuffixCommentPopupComponent,
        resolve: {
            requestNiazsanjiFardi: RequestNiazsanjiFardiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.requestNiazsanjiFardi.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
