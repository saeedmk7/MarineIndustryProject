import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { UsersRequestMarineSuffix } from 'app/shared/model/users-request-marine-suffix.model';
import { UsersRequestMarineSuffixService } from './users-request-marine-suffix.service';
import { UsersRequestMarineSuffixComponent } from './users-request-marine-suffix.component';
import { UsersRequestMarineSuffixDetailComponent } from './users-request-marine-suffix-detail.component';
import { UsersRequestMarineSuffixUpdateComponent } from './users-request-marine-suffix-update.component';
import { UsersRequestMarineSuffixDeletePopupComponent } from './users-request-marine-suffix-delete-dialog.component';
import { IUsersRequestMarineSuffix } from 'app/shared/model/users-request-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class UsersRequestMarineSuffixResolve implements Resolve<IUsersRequestMarineSuffix> {
    constructor(private service: UsersRequestMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((usersRequest: HttpResponse<UsersRequestMarineSuffix>) => usersRequest.body));
        }
        return of(new UsersRequestMarineSuffix());
    }
}

export const usersRequestRoute: Routes = [
    {
        path: 'users-request-marine-suffix',
        component: UsersRequestMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.usersRequest.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'users-request-marine-suffix/:id/view',
        component: UsersRequestMarineSuffixDetailComponent,
        resolve: {
            usersRequest: UsersRequestMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.usersRequest.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'users-request-marine-suffix/new',
        component: UsersRequestMarineSuffixUpdateComponent,
        resolve: {
            usersRequest: UsersRequestMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.usersRequest.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'users-request-marine-suffix/:id/edit',
        component: UsersRequestMarineSuffixUpdateComponent,
        resolve: {
            usersRequest: UsersRequestMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.usersRequest.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const usersRequestPopupRoute: Routes = [
    {
        path: 'users-request-marine-suffix/:id/delete',
        component: UsersRequestMarineSuffixDeletePopupComponent,
        resolve: {
            usersRequest: UsersRequestMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.usersRequest.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
