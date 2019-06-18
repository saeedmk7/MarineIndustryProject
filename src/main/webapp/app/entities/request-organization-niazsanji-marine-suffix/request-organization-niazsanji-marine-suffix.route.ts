import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { RequestOrganizationNiazsanjiMarineSuffix } from 'app/shared/model/request-organization-niazsanji-marine-suffix.model';
import { RequestOrganizationNiazsanjiMarineSuffixService } from './request-organization-niazsanji-marine-suffix.service';
import { RequestOrganizationNiazsanjiMarineSuffixComponent } from './request-organization-niazsanji-marine-suffix.component';
import { RequestOrganizationNiazsanjiMarineSuffixDetailComponent } from './request-organization-niazsanji-marine-suffix-detail.component';
import { RequestOrganizationNiazsanjiMarineSuffixUpdateComponent } from './request-organization-niazsanji-marine-suffix-update.component';
import { RequestOrganizationNiazsanjiMarineSuffixDeletePopupComponent } from './request-organization-niazsanji-marine-suffix-delete-dialog.component';
import { IRequestOrganizationNiazsanjiMarineSuffix } from 'app/shared/model/request-organization-niazsanji-marine-suffix.model';
import {RequestOrganizationNiazsanjiMarineSuffixCommentPopupComponent} from "./request-organization-niazsanji-marine-suffix-comment-dialog.component";

@Injectable({ providedIn: 'root' })
export class RequestOrganizationNiazsanjiMarineSuffixResolve implements Resolve<IRequestOrganizationNiazsanjiMarineSuffix> {
    constructor(private service: RequestOrganizationNiazsanjiMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    map(
                        (requestOrganizationNiazsanji: HttpResponse<RequestOrganizationNiazsanjiMarineSuffix>) =>
                            requestOrganizationNiazsanji.body
                    )
                );
        }
        return of(new RequestOrganizationNiazsanjiMarineSuffix());
    }
}

export const requestOrganizationNiazsanjiRoute: Routes = [
    {
        path: 'request-organization-niazsanji-marine-suffix',
        component: RequestOrganizationNiazsanjiMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'modifyDate,desc',
            pageTitle: 'marineindustryprojApp.requestOrganizationNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'request-organization-niazsanji-marine-suffix/:id/view',
        component: RequestOrganizationNiazsanjiMarineSuffixDetailComponent,
        resolve: {
            requestOrganizationNiazsanji: RequestOrganizationNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.requestOrganizationNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'request-organization-niazsanji-marine-suffix/new',
        component: RequestOrganizationNiazsanjiMarineSuffixUpdateComponent,
        resolve: {
            requestOrganizationNiazsanji: RequestOrganizationNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.requestOrganizationNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'request-organization-niazsanji-marine-suffix/:id/edit',
        component: RequestOrganizationNiazsanjiMarineSuffixUpdateComponent,
        resolve: {
            requestOrganizationNiazsanji: RequestOrganizationNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.requestOrganizationNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const requestOrganizationNiazsanjiPopupRoute: Routes = [
    {
        path: 'request-organization-niazsanji-marine-suffix/:id/delete',
        component: RequestOrganizationNiazsanjiMarineSuffixDeletePopupComponent,
        resolve: {
            requestOrganizationNiazsanji: RequestOrganizationNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.requestOrganizationNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'request-organization-niazsanji-marine-suffix/:id/:CommentType/comment',
        component: RequestOrganizationNiazsanjiMarineSuffixCommentPopupComponent,
        resolve: {
            requestOrganizationNiazsanji: RequestOrganizationNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.requestOrganizationNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
